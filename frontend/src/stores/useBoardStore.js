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
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "BOARD-002") {
            alert("이미 사용중인 제목입니다. 제목을 변경해주세요.");
          }
        }
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
        let response = await axios.get(backend + "/board/category/" + boardCategoryIdx + "/" + sortType + "?page=" + (page - 1));
        this.boardList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);
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
        this.totalPages = response.data.result.totalPages;
        this.totalCnt = response.data.result.totalCnt;
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
        return this.boardDetail;
      } catch (e) {
        console.log(e);
      }
    },
    async createBoardUp(token, requestBody) {
      try {
        let response = await axios.post(backend + "/boardup/create", requestBody, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          }
        })

        return response;
      } catch (e) {
        console.error("게시글 추천 실패", e);
        throw e;
      }
    },
    async createBoardScrap(token, requestBody) {
      try {
        let response = await axios.post(backend + "/boardscrap/create", requestBody, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          }
        })

        return response;
      } catch (e) {
        console.error("게시글 스크랩 실패", e);
        throw e;
      }
    },
    async checkBoardUp(token, boardIdx) {
      try {
        let response = await axios.get(`${backend}/boardup/check/${boardIdx}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response);

        this.isRecommended = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
    async checkBoardScrap(token, boardIdx) {
      try {
        let response = await axios.get(`${backend}/boardscrap/check/${boardIdx}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response);

        this.isScrapped = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
    async cancelBoardUp(token, boardUpIdx) {
      try {
        let response = await axios.patch(`${backend}/boardup/delete/${boardUpIdx}`, {}, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          },
        });
        console.log(response);
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
    async cancelBoardScrap(token, boardScrapIdx) {
      try {
        let response = await axios.patch(`${backend}/boardscrap/delete/${boardScrapIdx}`, {}, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          },
        });
        console.log(response);
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
    async getCategoryBoardListByQuery(boardCategoryIdx ,query, option, page = 1) {
      try {
        let response = await axios.get(
          backend +
            "/board/search/by/" +
            boardCategoryIdx +
            "?query=" +
            query +
            "&sortType=" +
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
    async findMyBoardListByCategory(boardCategoryIdx, option, page = 1){
      try {
        let response = await axios.get(
          backend + 
          "/board/mylist/" + 
          boardCategoryIdx + "/" +
          option + 
          "?page=" + (page-1), {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json"
          },
        });
        this.boardList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);
      } catch (error) {
        console.error(error);
      }
    },
    async findBoardScrapListByCategory(boardCategoryIdx, option, page = 1){
      try {
        let response = await axios.get(
          backend + 
          "/boardscrap/list/" + 
          boardCategoryIdx + "/" +
          option + 
          "?page=" + (page-1), {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json"
          },
        });
        this.boardList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);
      } catch (error) {
        console.error(error);
      }
    }
  },
});
