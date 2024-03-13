import { defineStore } from "pinia";
import axios from "axios";

// const backend = 'https://www.lonuashop.kro.kr/api';
const backend = "http://localhost:8080";
// const storedToken = sessionStorage.getItem("token");

export const useUserStore = defineStore("user", {
  state: () => ({
    user: {},
    isSuccess: false,
    isLoading: false,
  }),
  actions: {
    // 회원 회원가입
    async sendSignUpData(user, profileImage) {
  
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
          console.log(e);
          this.isSuccess = false; 
          alert("잘못된 요청입니다.");
        } finally {
          this.isLoading = false;
        }
    },
  },
});
