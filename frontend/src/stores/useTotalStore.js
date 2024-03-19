import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useTotalStore = defineStore("total", {
  state: () => ({
    totalList: [],
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
    isBoardExist: true,
    isLoading: false,
  }),
  actions: {
    async getHotReviewList(reviewCategoryIdx, sortType, page = 1) {
      try {
        this.isLoading = true;

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

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }
      } catch (e) {
        console.log(e);
      } finally {
        this.isLoading = false;
      }
    },

    async getSearchHotReviewList(
      reviewCategoryIdx,
      searchTerm,
      sortType,
      page = 1
    ) {
      try {
        this.isLoading = true;

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

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }
      } catch (e) {
        console.log(e);
      } finally {
        this.isLoading = false;
      }
    },

    async getHotBoardList(boardCategoryIdx, sortType, page = 1) {
      try {
        this.isLoading = true;

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

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }

        console.log(response);
      } catch (error) {
        console.error(error);
      } finally {
        this.isLoading = false;
      }
    },

    async getSearchHotBoardList(
      boardCategoryIdx,
      searchTerm,
      sortType,
      page = 1
    ) {
      try {
        this.isLoading = true;

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

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }
      } catch (e) {
        console.log(e);
      } finally {
        this.isLoading = false;
      }
    },
    async findMyBoardListByCategory(boardCategoryIdx, option, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/board/mylist/" +
            boardCategoryIdx +
            "/" +
            option +
            "?page=" +
            (page - 1),
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );
        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }

        console.log(response);
      } catch (error) {
        console.error(error);
      }
    },

    async findBoardScrapListByCategory(boardCategoryIdx, option, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/boardscrap/list/" +
            boardCategoryIdx +
            "/" +
            option +
            "?page=" +
            (page - 1),
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );
        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }

        console.log(response);
      } catch (error) {
        console.error(error);
      }
    },

    async deleteBoard(idx) {
      try {
        let response = await axios.delete(backend + "/board/delete/" + idx, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        console.log(response);

        return response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async getMyReviewList(reviewCategoryIdx, sortType, page = 1) {
      try {
        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend + `/review/mylist/${reviewCategoryIdx}/${sortType}?${params}`,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );

        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }
      } catch (e) {
        console.log(e);
      }
    },

    async findReviewScrapListByCategory(reviewCategoryIdx, option, page = 1) {
      try {
        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend + `/reviewscrap/list/${reviewCategoryIdx}/${option}?${params}`,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );
        this.totalList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isBoardExist = false;
        } else {
          this.isBoardExist = true;
        }
      } catch (error) {
        console.error(error);
      }
    },
    async cancelBoardScrap(boardScrapIdx) {
      try {
        let response = await axios.patch(
          `${backend}/boardscrap/delete/${boardScrapIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );
        console.log(response);
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
  },
});
