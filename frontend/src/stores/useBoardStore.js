import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useBoardStore = defineStore("board", {
  state: () => ({
    boardList: [],
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
    boardDetail: [],
    tagList: [],
    boardCategoryList: [],
  }),
  actions: {
    async createBoard(board, boardImage) {
      const formData = new FormData();

      let json = JSON.stringify(board);
      formData.append("board", new Blob([json], { type: "application/json" }));
      formData.append("boardImage", boardImage);

      try {
        let response = await axios.post(backend + `/board/create`, formData, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "multipart/form-data",
          },
        });
        if (response.data.isSuccess === true) {
          this.isSuccess = true;
          alert("게시글이 등록되었습니다.");
          window.location.href = "/bpard/" + response.data.result.boardIdx;
        }
      } catch (e) {
        console.log(e);
      }
    },
    async getBoardListByQuery(query, option, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/board/search?query=" +
            query +
            "&searchType=" +
            option +
            "&page=" +
            (page - 1)
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

    async findListByCategory(boardCategoryIdx, sortType, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/board/category/" +
            boardCategoryIdx +
            "/" +
            sortType +
            "?page=" +
            (page - 1)
        );
        this.boardList = response.data.result;
      } catch (error) {
        console.error(error);
      }
    },
    async getSearchBoardList(searchTerm, sortType) {
      try {
        let response = await axios.get(
          backend +
            `/board/${sortType}/search?searchTerm=${encodeURIComponent(
              searchTerm
            )}`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.boardList = response.data.result.list;
        if (response.data.result.length !== 0) {
          this.isBoardExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },
    async findBoard(boardIdx) {
      try {
        let response = await axios.get(backend + "/board/" + boardIdx);
        this.boardDetail = response.data.result;

        console.log(response);
      } catch (e) {
        console.log(e);
      }
    },
  },
});
