import { defineStore } from "pinia";
import axios from "axios";

// const backend = "http://frontend-svc:8080";
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
    isPageExist: true,
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
        }

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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
        }
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
        }

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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
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
          backend +
            `/reviewscrap/list/${reviewCategoryIdx}/${option}?${params}`,
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

        if (this.totalList.length === 0) {
          this.isBoardExist = false;
          this.isPageExist = false;
        } else {
          this.isBoardExist = true;
          this.isPageExist = true;
        }
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

        if (response.data.isSuccess === true) {
          alert("게시글이 삭제되었습니다.");
          window.location.href = "/mypage";
        }
      } catch (error) {
        console.error(error);
      }
    },
    async deleteReview(idx) {
      try {
        let response = await axios.delete(backend + "/review/delete/" + idx, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          alert("후기글이 삭제되었습니다.");
          window.location.href = "/mypage";
        }
      } catch (error) {
        console.error(error);
      }
    },
    async deleteBoardScrap(boardScrapIdx) {
      try {
        let response = await axios.patch(
          backend + `/boardscrap/delete/${boardScrapIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (response.data.isSuccess === true) {
          alert("게시글 스크랩을 취소하였습니다.");
          window.location.href = "/mypage";
        }
      } catch (error) {
        console.error(error);
      }
    },

    async deletReviewScrap(reviewScrapIdx) {
      try {
        let response = await axios.patch(
          backend + `/reviewscrap/delete/${reviewScrapIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (response.data.isSuccess === true) {
          alert("후기글 스크랩을 취소하였습니다.");
          window.location.href = "/mypage";
        }
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
  },
});
