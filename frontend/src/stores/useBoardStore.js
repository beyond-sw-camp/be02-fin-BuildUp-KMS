import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080"; 

export const useBoardStore = defineStore("board", {
  state: () => ({
    boardList: [],
  }),
  actions: {
    async getHouseList(page, size) {
      try {
        let response = await axios.get(
          backend + "/house/list?page=" + page + "&size=" + size
        );

        console.log("Total items in response:", response.data.length);

        const totalCountHeader = response.headers["x-total-count"];
        this.totalPages = totalCountHeader
          ? Math.ceil(totalCountHeader / size)
          : 1;

        if (page > this.totalPages) {
          page = this.totalPages;
          this.currentPage = page;
        } else {
          this.currentPage = page;
        }

        this.houseList = response.data;

        console.log("Current page:", this.currentPage);
        console.log("Total pages:", this.totalPages);
        console.log(response);

        return response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
});
