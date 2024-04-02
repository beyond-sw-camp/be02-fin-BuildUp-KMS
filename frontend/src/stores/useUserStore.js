import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

// const backend = "http://192.168.0.61/api";
const backend = "http://localhost:8080";

const storedToken = localStorage.getItem("accessToken");
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
  }),
  actions: {
    // async refreshTokenIfNeeded() {
    //   const accessToken = localStorage.getItem("accessToken");
    //   const refreshToken = localStorage.getItem("refreshToken");
    //   if (!accessToken || !refreshToken) return;

    //   const decodedAccessToken = VueJwtDecode.decode(accessToken);
    //   const expirationTime = decodedAccessToken.exp * 1000; // ms로 변환

    //   // Access 토큰 만료 30초 전이면 리프레시 토큰을 이용하여 새로운 Access 토큰 발급
    //   if (expirationTime - Date.now() < 30000) {
    //     try {
    //       const response = await axios.get(backend + '/user/read', {
    //         headers: {
    //           Authorization: `Bearer ${accessToken}`,
    //           RefreshToken: `Bearer ${refreshToken}`
    //         }
    //       });
    //       const newAccessToken = response.data.result.accessToken;
        
    //       // 새로운 Access 토큰과 만료 시간을 저장
    //       localStorage.setItem("accessToken", newAccessToken);

    //       // 새로운 토큰으로 인증 상태 업데이트
    //       this.isAuthenticated = true;

    //     } catch (error) {
    //       console.error("토큰 발급 실패:", error);
    //       // 토큰 발급 실패 처리
    //     }
    //   }
    // },

    async login(email, password) {
      try {
        let loginUser = { email: email, password: password };

        let response = await axios.post(backend + "/user/login", loginUser);

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

    decodeToken() {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        const decoded = VueJwtDecode.decode(accessToken);
        if (decoded.exp < Date.now() / 1000) {
          this.logout();
        } else {
          this.isAuthenticated = true;
          this.decodedToken = decodeURIComponent(escape(decoded));
        }
      }
    },

    logout() {
      localStorage.removeItem("accessToken");
      this.isAuthenticated = false;
      this.decodedToken = null;

      this.$router.push("/");
    },

    async getUserInfo() {
      try {
        let response = await axios.get(backend + `/user/read`, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            RefreshToken: `Bearer ${refreshToken}`
          },
        });

        console.log(response.headers["New-Access-Token"]);
        if(response.headers["New-Access-Token"] != null) {
          if(response.headers ["New-Access-Token"] != localStorage.getItem("accessToken")) {
            localStorage.setItem("accessToken", "")
            localStorage.setItem("accessToken", response.headers["New-Access-Token"])
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
        let response = await axios.post(
          backend + "/user/checkpw",
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
          sessionStorage.removeItem("accessToken");
          window.location.href = "/";
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

        let response = await axios.post(backend + "/user/find/email", user);

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

        let response = await axios.patch(backend + "/user/find/password", user);

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
          backend + "/user/list/?page=" + (page - 1)
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
        await axios.patch(backend + "/user/delete/" + userIdx);
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
