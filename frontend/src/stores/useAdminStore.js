import axios from "axios";
import { defineStore } from "pinia";
import VueJwtDecode from "vue-jwt-decode";

// const backend = "http://192.168.0.61/api";
const backend = "http://localhost:8080";

export const useAdminStore = defineStore("admin", {
  state: () => ({
    isAdminAuthenticated: false,
    adminDecodedToken: null,
    isTokenExpired: false,
  }),

  actions: {
    async adminSignUp(postSignUpAdminReq) {
        try {
            let response = await axios.post(`${backend}/admin/signup`, postSignUpAdminReq, {
                headers: {
                    "Content-Type": "application/json",
                }
            });

            if (response.data.isSuccess === true) {
                alert("관리자 회원가입 성공!");
                this.$router.push("/admin/login");
            }
        } catch (e) {
            if (e.response && e.response.data) {
                console.log(e.response.data);
                if (e.response.data.code === "USER-001") {
                    alert("이미 사용중인 이메일입니다.");
                }
            }
        }
    },

    async adminLogin(email, password) {
        try {
          let loginUser = { email: email, password: password };
  
          let response = await axios.post(backend + "/admin/login", loginUser);
  
          if (response.data.isSuccess && response.data.result.accessToken) {
            let accessToken = response.data.result.accessToken;
            let refreshToken = response.data.result.refreshToken;
            
            let userClaims = VueJwtDecode.decode(accessToken);
  
            window.localStorage.setItem("accessToken", accessToken);
            window.localStorage.setItem("refreshToken", refreshToken);
            this.setDecodedToken(userClaims);
  
            this.isAdminAuthenticated = true;
          } else {
            console.error("토큰 발급 실패");
          }
        } catch (e) {
          if (e.response && e.response.data) {
            console.log(e.response.data);
            if (e.response.data.code === "USER-003") {
              alert("이메일을 찾을 수 없습니다. 가입한 이메일인지 다시 확인해주세요.");
            } else if (e.response.data.code === "USER-004") {
              alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
            }
          }
        }
      },
  
      setDecodedToken(adminDecodedToken) {
        this.adminDecodedToken = adminDecodedToken;
      },

  
      logout() {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        this.isAdminAuthenticated = false;
        this.adminDecodedToken = {};
  
        this.$router.push("/admin/login");
      },
  },
});
