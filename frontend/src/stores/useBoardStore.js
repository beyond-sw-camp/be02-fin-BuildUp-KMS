import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";

export const useBoardStore = defineStore("board", {
  state: () => ({
    boardList: [],
    isOrderExist: true,
  }),
  actions: {
    async findListByCategory(boardCategoryIdx, sortType) {
      try {
        let response = await axios.get(
          backend + `/board/category/${boardCategoryIdx}/${sortType}`
        );

        this.boardList = response.data.result;
        if (response.data.result.length !== 0) {
          this.isBoardExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
});
