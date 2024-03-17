import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
// const storedToken = localStorage.getItem("token");

export const useTotalStore = defineStore("total", {
  state: () => ({
    totalList: [],
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
  }),
  actions: {

    async getHotReviewList(reviewCategoryIdx, sortType, page = 1) {
      try {
        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend + `/review/hotlist/${reviewCategoryIdx}/${sortType}?${params}`
        );

        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async getSearchHotReviewList(
      reviewCategoryIdx,
      searchTerm,
      sortType,
      page = 1
    ) {
      try {
        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend +
            `/review/hotlist/${reviewCategoryIdx}/${sortType}/search?searchTerm=${encodeURIComponent(
              searchTerm
            )}&${params}`
        );

        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async getHotBoardList(boardCategoryIdx, sortType, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/board/hotlist/" +
            boardCategoryIdx +
            "/" +
            sortType +
            "?page=" +
            (page - 1)
        );

        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);
      } catch (error) {
        console.error(error);
      }
    },

    async getSearchHotBoardList(
        boardCategoryIdx,
        searchTerm,
        sortType,
        page = 1
      ) {
        try {
          const params = new URLSearchParams({
            page: page - 1,
          }).toString();
  
          let response = await axios.get(
            backend +
              `/board/hotlist/${boardCategoryIdx}/${sortType}/search?searchTerm=${encodeURIComponent(
                searchTerm
              )}&${params}`
          );
  
          this.totalList = response.data.result.list;
          this.totalPages = response.data.result.totalPages;
          this.currentPage = page;
          this.totalCnt = response.data.result.totalCnt;
  
          if (response.data.result.length !== 0) {
            this.isReviewExist = false;
          }
        } catch (e) {
          console.log(e);
        }
      },
  },
});
