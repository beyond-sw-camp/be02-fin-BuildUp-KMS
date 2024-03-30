import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://192.168.0.61/api";
// const backend = "http://localhost:8080";

export const useCategoryStore = defineStore("category", {
  state: () => ({
    boardCategoryList: [],
    reviewCategoryList: [],
    backend: process.env.VUE_APP_BACKEND_URL,
  }),
  actions: {
    async getBoardCategoryList() {
      try {
        let response = await axios.get(backend + "/admin/board/list");

        this.boardCategoryList = response.data.result;
      } catch (e) {
        console.log(e);
      }
    },
    async getReviewCategoryList() {
      try {
        let response = await axios.get(backend + "/admin/review/list");

        this.reviewCategoryList = response.data.result;
      } catch (e) {
        console.log(e);
      }
    },
  },
});
