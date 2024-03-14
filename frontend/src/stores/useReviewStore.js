import { defineStore } from "pinia";
import axios from "axios";

// const backend = 'https://www.lonuashop.kro.kr/api';
const backend = "http://localhost:8080";
// const storedToken = sessionStorage.getItem("token");

export const useReviewStore = defineStore("review", {
  state: () => ({ reviewList: [], isOrderExist: true }),
  actions: {
    async getReviewList(reviewCategoryIdx, sortType) {
      try {
        let response = await axios.get(backend + `/review/list/${reviewCategoryIdx}/${sortType}`, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        this.reviewList = response.data.result;
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

        this.reviewList = response.data.result;
        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
});
