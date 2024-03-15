import { defineStore } from "pinia";
import axios from "axios";

// const backend = 'https://www.lonuashop.kro.kr/api';
const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useReviewStore = defineStore("review", {
  state: () => ({ reviewList: [], isOrderExist: true, review: "" }),
  actions: {
    async createReview(review, reviewImage) {
      const formData = new FormData();

      let json = JSON.stringify(review);
      formData.append("review", new Blob([json], { type: "application/json" }));
      formData.append("reviewImage", reviewImage);

      try {
        let response = await axios.post(backend + `/review/create`, formData, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "multipart/form-data",
          },
        });
        if (response.data.isSuccess === true) {
          this.isSuccess = true;
          alert("후기글이 등록되었습니다.");
          window.location.href = "/review/" + response.data.result.reviewIdx;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async getReviewList(reviewCategoryIdx, sortType) {
      try {
        let response = await axios.get(
          backend + `/review/list/${reviewCategoryIdx}/${sortType}`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async getSearchReviewList(searchTerm, sortType) {
      try {
        let response = await axios.get(
          backend +
            `/review/${sortType}/search?searchTerm=${encodeURIComponent(
              searchTerm
            )}`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async getReviewDetail(reviewIdx) {
      try {
        let response = await axios.get(backend + `/review/${reviewIdx}`, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        this.review = response.data.result;
      } catch (e) {
        console.log(e);
      }
    },
  },
});
