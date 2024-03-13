import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

const backend = "http://localhost:8080"; 

export const useUserStore = defineStore("user", {
  state: () => ({
    isAuthenticated: false,
    decodedToken: null,
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

    logout() {
      window.localStorage.removeItem("token");
      this.isAuthenticated = false;
      this.decodedToken = null;
    },
  },
});
