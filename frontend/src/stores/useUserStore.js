import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

      // const backend = "http://192.168.0.61/api";
      const backend = "http://localhost:9999";

const accessToken = localStorage.getItem("accessToken");
const refreshToken = localStorage.getItem("refreshToken");

export const useUserStore = defineStore("user", {
  state: () => ({
    isAuthenticated: false,
    decodedToken: null,
    user: {},
    isSuccess: false,
    isFindEmailSuccess: false,
    isLoading: false,
    checkPasswordError: false,
    isPossibleUpdate: true,
    courseName: "",
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
    userEmail: "",
    userList: [],
    isTokenExpired: false,
  }),
  actions: {

    validateToken() {
      const decodedAccessToken = VueJwtDecode.decode(accessToken);
      const expirationTime = decodedAccessToken.exp;
      const currentTime = Math.floor(Date.now() / 1000);

      if (expirationTime - currentTime < 30) {
        this.isTokenExpired = true;
      } else {
        this.isTokenExpired = false;
      }
    },

    async login(email, password) {
      try {
        let loginUser = { email: email, password: password };

        let response = await axios.post(backend + "/main/user/login", loginUser);

        if (response.data.isSuccess && response.data.result.accessToken) {
          let accessToken = response.data.result.accessToken;
          let refreshToken = response.data.result.refreshToken;

          let userClaims = VueJwtDecode.decode(accessToken);

          window.localStorage.setItem("accessToken", accessToken);
          window.localStorage.setItem("refreshToken", refreshToken);
          this.setDecodedToken(userClaims);

          this.isAuthenticated = true;
        } else {
          console.error("토큰 발급 실패");
        }
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert(
              "이메일을 찾을 수 없습니다. 가입한 이메일인지 다시 확인해주세요."
            );
          } else if (e.response.data.code === "USER-004") {
            alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
          } else if (e.response.data.code === "USER-005") {
            alert("이메일 인증을 완료 해주세요.");
          } else if (e.response.data.code === "COMMON-001") {
            alert(
              "이메일과 비밀번호를 다시 확인해주세요. 입력양식이 잘못되었습니다."
            );
          }
        }
      }
    },

    setDecodedToken(decodedToken) {
      this.decodedToken = decodedToken;
    },

    logout() {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      this.isAuthenticated = false;
      this.decodedToken = null;

      this.$router.push("/");
    },

    async getUserInfo() {
      try {

        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.get(backend + `/main/user/read`, {
          headers,
        });

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        this.user = response.data.result;
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert("회원정보를 찾을 수 없습니다.");
          }
        }
      }
    },

    // 회원 회원가입.
    async signUpData(user, profileImage) {
      try {
        this.isLoading = true;

        let formData = new FormData();
        let json = JSON.stringify(user);
        formData.append("user", new Blob([json], { type: "application/json" }));
        formData.append("profileImage", profileImage);

        let response = await axios.post(backend + "/main/user/signup", formData, {
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
          if (e.response.data.code === "USER-001") {
            alert("이미 사용중인 이메일입니다.");
          } else if (e.response.data.code === "USER-002") {
            alert("이미 사용중인 닉네임입니다. 닉네임을 변경해주세요.");
          } else if (e.response.data.code === "COURSE-001") {
            alert(
              "수료한 과정이 존재하지 않습니다. 인증용 이미지를 다시 확인해주세요."
            );
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
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.post(
          backend + "/main/user/checkpw",
          currentPassword,
          {
            headers,
          }
        );
        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

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

          this.validateToken();

          const headers = this.isTokenExpired
            ? {
                Authorization: `Bearer ${accessToken}`,
                RefreshToken: `Bearer ${refreshToken}`,
                "Content-Type": "application/json",
              }
            : {
                Authorization: `Bearer ${accessToken}`,
                "Content-Type": "application/json",
              };

          let response = await axios.patch(backend + "/main/user/update", data, {
            headers
          });
          if (response.data.isSuccess === true) {
            alert("회원정보를 수정하였습니다.");
            window.location.href = "/profile";
          }

          if (response.headers["new-refresh-token"] != null) {
            if (
              response.headers["new-refresh-token"] !=
              localStorage.getItem("refreshToken")
            ) {
              localStorage.setItem("refreshToken", "");
              localStorage.setItem(
                "refreshToken",
                response.headers["new-refresh-token"]
              );
            }
          }

          if (response.headers["new-access-token"] != null) {
            if (
              response.headers["new-access-token"] !=
              localStorage.getItem("accessToken")
            ) {
              localStorage.setItem("accessToken", "");
              localStorage.setItem(
                "accessToken",
                response.headers["new-access-token"]
              );
            }
          }

        } catch (e) {
          if (e.response && e.response.data) {
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

        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "multipart/form-data",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "multipart/form-data",
            };

        let response = await axios.patch(
          backend + "/main/user/update/image",
          formData,
          {
            headers
          }
        );

        if (response.data.isSuccess === true) {
          alert("회원 프로필 이미지를 수정하였습니다.");
          window.location.href = "/profile";
        }

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }
      } catch (e) {
        if (e.response && e.response.data) {
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

        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.delete(backend + "/main/user/cancel", {
          headers
        });

        if (response.data.isSuccess === true) {
          alert(
            '회원 탈퇴가 성공적으로 처리되었습니다. 그동안 "BOOTSHELF" 를 이용해주셔서 감사합니다.'
          );
          localStorage.removeItem("accessToken");
          localStorage.removeItem("refreshToken");
          window.location.href = "/";
        }

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert("회원 정보를 찾을 수 없습니다.");
          }
        }
      }
    },

    // HRD-NET 훈련이력 확인
    async checkCourse(courseImage) {
      try {
        let formData = new FormData();
        formData.append("courseImage", courseImage);

        let response = await axios.post(
          backend + "/main/user/check/course",
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
          if (e.response.data.code === "OCR-001") {
            alert(
              "나의 훈련이력 이미지를 잘못 첨부하였습니다. 가이드를 확인해주세요!"
            );
          }
        }
      }
    },
    // 회원 이메일 찾기
    async findEmail(name, nickName) {
      try {
        let user = { name: name, nickName: nickName };

        let response = await axios.post(backend + "/main/user/find/email", user);

        if (response.data.isSuccess === true) {
          this.userEmail = response.data.result.email;
          this.isFindEmailSuccess = true;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert(
              "회원정보를 찾을 수 없습니다. 이름과 닉네임을 다시 확인해주세요."
            );
          } else if (e.response.data.code === "COMMON-001") {
            alert("이름과 닉네임은 필수 입력항목입니다.");
          }
        }
      }
    },
    // 회원 비밀번호 찾기
    async findPassword(email, name) {
      try {
        this.isLoading = true;
        let user = { email: email, name: name };

        let response = await axios.patch(backend + "/main/user/find/password", user);

        if (response.data.isSuccess === true) {
          this.isFindPasswordSuccess = true;
          this.isLoading = false;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert(
              "회원정보를 찾을 수 없습니다. 이메일과 이름을 다시 확인해주세요."
            );
            this.isLoading = false;
          } else if (e.response.data.code === "COMMON-001") {
            alert("이메일과 이름은 필수 입력항목입니다.");
            this.isLoading = false;
          }
        }
      }
    },

    // [관리자] 회원 목록 조회
    async getUserList(page = 1) {
      try {
        let response = await axios.get(
          backend + "/main/user/list/?page=" + (page - 1)
        );

        this.userList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;
      } catch (e) {
        console.log(e);
      }
    },

    async deleteUser(userIdx) {
      try {
        await axios.patch(backend + "/main/user/delete/" + userIdx);
      } catch (e) {
        if (e.response && e.response.data) {
          if (e.response.data.code === "USER-003") {
            alert("회원 정보를 찾을 수 없습니다.");
          }
        }
      }
    },
  },
});
