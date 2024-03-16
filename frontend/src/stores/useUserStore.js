import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useUserStore = defineStore("user", {
  state: () => ({
    isAuthenticated: false,
    decodedToken: null,
    user: {},
    isSuccess: false,
    isLoading: false,
    checkPasswordError: false,
    isPossibleUpdate: true,
    courseName: "",
  }),
  actions: {
    async login(email, password) {
      try {
        let loginUser = { email: email, password: password };

        let response = await axios.post(backend + "/user/login", loginUser);

        if (response.data.isSuccess && response.data.result.token) {
          let token = response.data.result.token;
          let userClaims = VueJwtDecode.decode(token);

          window.localStorage.setItem("token", token);
          this.setDecodedToken(userClaims);

          this.isAuthenticated = true;
        } else {
          console.error("토큰 발급 실패");
        }
      } catch (error) {
        console.error("Login failed:", error);
      }
    },

    setDecodedToken(decodedToken) {
      this.decodedToken = decodedToken;
    },

    decodeToken() {
      const token = localStorage.getItem('token');
      if (token) {
        const decoded = VueJwtDecode.decode(token);
        if (decoded.exp < Date.now() / 1000) {
          this.logout();
        } else {
          this.isAuthenticated = true;
          this.decodedToken = decoded;
        }
      }
    },

    logout() {
      localStorage.removeItem("token");
      this.isAuthenticated = false;
      this.decodedToken = null;

      this.$router.push("/");
    },

    async getUserInfo() {
      try {
        let response = await axios.get(backend + `/user/read`, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });
        this.user = response.data.result;
      } catch (e) {
        console.log(e);
      }
    },

    // 회원 회원가입
    async signUpData(user, profileImage) {
      try {
        this.isLoading = true;

        let formData = new FormData();
        let json = JSON.stringify(user);
        formData.append("user", new Blob([json], { type: "application/json" }));
        formData.append("profileImage", profileImage);

        let response = await axios.post(backend + "/user/signup", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        if (response.data.isSuccess === true) {
          this.isSuccess = true;
        } else {
          this.isSuccess = false;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "USER-001") {
            alert("이미 사용중인 이메일입니다.");
          } else if (e.response.data.code === "USER-002") {
            alert("이미 사용중인 닉네임입니다. 닉네임을 변경해주세요.");
          } else if (e.response.data.code === "COURSE-001") {
            alert("수료한 과정이 존재하지 않습니다. 인증용 이미지를 다시 확인해주세요.");
          }
        }
        this.isSuccess = false;
      } finally {
        this.isLoading = false;
      }
    },

    // 회원정보 수정-1(비밀번호 확인)
    async checkPassword(currentPassword) {
      try {
        let response = await axios.post(
          backend + "/user/checkPw",
          currentPassword,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (response.data === true) {
          this.isSuccess = true;
          this.isPossibleUpdate = false;
          this.checkPasswordError = false;
        } else {
          this.checkPasswordError = true;
        }
      } catch (e) {
        console.log(e);
        this.isSuccess = false;
        this.user.password = "";
        this.checkPasswordError = true;
        // alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
      }
    },

    // 회원정보 수정-2(서버 요청)
    async updateProfile(user) {
      let data = {
        nickName: user.nickName || null,
        password: user.password || null,
        checkPassword: user.checkPassword || null,
      };
      if (data.nickName !== null || data.password !== null) {
        try {
          let response = await axios.patch(backend + "/user/update", data, {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          });
          if (response.data.isSuccess === true) {
            alert("회원정보를 수정하였습니다.");
            window.location.href = "/profile";
          }
        } catch (e) {
          if (e.response && e.response.data) {
            console.log(e.response.data);
            if (e.response.data.code === "USER-002") {
              alert("이미 사용중인 닉네임입니다.");
            } else if (e.response.data.code === "USER-003") {
              alert("회원 정보를 찾을 수 없습니다.");
            } else if (e.response.data.code === "USER-004") {
              alert("입력한 패스워드가 서로 다릅니다.");
            }
          } else {
            console.log(e);
          }
        }
      } else {
        alert("수정할 내용을 입력해주세요.");
      }
    },

    // 회원정보 수정-3(프로필 이미지 수정)
    async updateProfileImage(profileImage) {
      let formData = new FormData();
      formData.append("profileImage", profileImage);

      try {
        let response = await axios.patch(
          backend + "/user/update/image",
          formData,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "multipart/form-data",
            },
          }
        );
        if (response.data.isSuccess === true) {
          alert("회원 프로필 이미지를 수정하였습니다.");
          window.location.href = "/profile";
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "USER-003") {
            alert("회원 정보를 찾을 수 없습니다.");
          }
        } else {
          console.log(e);
        }
      }
    },

    // 회원탈퇴
    async submitCancel() {
      try {
        let response = await axios.delete(backend + "/user/cancel", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          alert(
            '회원 탈퇴가 성공적으로 처리되었습니다. 그동안 "BOOTSHELF" 를 이용해주셔서 감사합니다.'
          );
          sessionStorage.removeItem("token");
          window.location.href = "/";
        }
      } catch (e) {
        console.log(e);
        alert("회원정보를 찾을 수 없습니다.");
      }
    },

    // HRD-NET 훈련이력 확인
    async checkCourse(courseImage) {
      try {
        let formData = new FormData();
        formData.append("courseImage", courseImage);

        let response = await axios.post(
          backend + "/user/check/course",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );
        if (response.data.isSuccess === true) {
          this.courseName = response.data.result;
          alert("인증에 성공하였습니다. 회원가입을 이어서 진행해주세요!");
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "OCR-001") {
            alert(
              "나의 훈련이력 이미지를 잘못 첨부하였습니다. 가이드를 확인해주세요!"
            );
          }
        }
      }
    },
  },
});
