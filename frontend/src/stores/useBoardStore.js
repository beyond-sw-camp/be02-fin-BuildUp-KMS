import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080"; 

export const useBoardStore = defineStore("board", {
  state: () => ({
    boardList: [],
    currentPage: 1,
    totalPages: 0,
    totalCnt: 0
  }),
  actions: {
    async getBoardListByQuery(query, option, page = 1) {
      try {
        let response = await axios.get(
          backend + "/board/search?query=" + query + "&searchType=" + option
        );

        this.boardList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);

      } catch (error) {
        console.error(error);
      }
    },
  },
});
