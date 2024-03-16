import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useCategoryStore = defineStore("category", {
  state: () => ({
    boardCategoryList: [],
    reviewCategoryList: []
  }),
  actions: {
    async getBoardCategoryList() {
      try {

        let response = await axios.get(
          backend + `/admin/board/list`,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
            },
          }
        );

        this.boardCategoryList = response.data.result;

      } catch (e) {
        console.log(e);
      }
    },
    async getReviewCategoryList() {
        try {
  
          let response = await axios.get(
            backend + `/admin/review/list`,
            {
              headers: {
                Authorization: `Bearer ${storedToken}`,
              },
            }
          );
  
          this.reviewCategoryList = response.data.result;
  
        } catch (e) {
          console.log(e);
        }
      }
  },
});
