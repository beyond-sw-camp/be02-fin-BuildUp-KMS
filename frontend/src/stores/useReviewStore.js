import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

export const useReviewStore = defineStore("review", {
  state: () => ({
    reviewList: [],
    review: "",
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
    isReviewExist: true,
    reviewIdx: 0,
    reviewDetail: [],
    isLoading: false,
  }),
  actions: {
    async createReview(review, reviewImage) {
      const formData = new FormData();

      let json = JSON.stringify(review);
      formData.append("review", new Blob([json], { type: "application/json" }));
      formData.append("reviewImage", reviewImage);

      try {
        let response = await axios.post(backend + `/review/create`, formData, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "multipart/form-data",
          },
        });
        if (response.data.isSuccess === true) {
          this.isSuccess = true;
          alert("후기글이 등록되었습니다.");
          window.location.href = "/review/" + response.data.result.reviewIdx;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "REVIEW-002") {
            alert(
              "후기글 제목이 이미 등록되어 있는 제목입니다. 제목을 변경해주세요."
            );
          }
        }
      }
    },

    async getReviewList(reviewCategoryIdx, sortType, page = 1) {
      try {
        this.isLoading = true;

        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend + `/review/list/${reviewCategoryIdx}/${sortType}?${params}`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isReviewExist = false;
        } else {
          this.isReviewExist = true;
        }
      } catch (e) {
        console.log(e);
      } finally {
        this.isLoading = false;
      }
    },

    async getSearchReviewList(
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
            `/review/${reviewCategoryIdx}/${sortType}/search?searchTerm=${encodeURIComponent(
              searchTerm
            )}&${params}`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        if (this.totalCnt === 0) {
          this.isReviewExist = false;
        } else {
          this.isReviewExist = true;
        }
      } catch (e) {
        console.log(e);
      } finally {
        this.isLoading = false;
      }
    },

    async getReviewDetail(reviewIdx) {
      try {
        let response = await axios.get(backend + `/review/${reviewIdx}`, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        this.review = response.data.result;
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "REVIEW-001") {
            alert("해당하는 후기글을 찾을 수 없습니다.");
          }
        }
      }
    },

    async createReviewUp(token, requestBody) {
      try {
        let response = await axios.post(
          backend + "/reviewup/create",
          requestBody,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );

        return response;
      } catch (e) {
        console.error("후기 추천 실패", e);
        throw e;
      }
    },

    async createReviewScrap(token, requestBody) {
      try {
        let response = await axios.post(
          backend + "/reviewscrap/create",
          requestBody,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );

        return response;
      } catch (e) {
        console.error("후기 스크랩 실패", e);
        throw e;
      }
    },

    async checkReviewUp(token, reviewIdx) {
      try {
        let response = await axios.get(
          `${backend}/reviewup/check/${reviewIdx}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        console.log(response);

        this.isRecommended = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async checkReviewScrap(token, reviewIdx) {
      try {
        let response = await axios.get(
          `${backend}/reviewscrap/check/${reviewIdx}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        console.log(response);

        this.isScrapped = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async cancelReviewUp(token, reviewUpIdx) {
      try {
        let response = await axios.patch(
          `${backend}/reviewup/delete/${reviewUpIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
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

    async cancelReviewScrap(token, reviewScrapIdx) {
      try {
        let response = await axios.patch(
          `${backend}/reviewscrap/delete/${reviewScrapIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
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

    async createReviewCategory(categoryName) {
      try {
        await axios.post(
          backend + "/admin/review/create",
          { categoryName: categoryName },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async deleteReviewCategory(reviewCategoryIdx) {
      try {
        await axios.delete(
          backend + "/admin/review/delete" + reviewCategoryIdx
        );
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async updateReviewCategory(reviewCategoryIdx, newCategoryName) {
      try {
        await axios.patch(
          backend + "/admin/review/update/" + reviewCategoryIdx,
          {
            categoryName: newCategoryName,
          }
        );
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
    async findReviewDetailByUserIdx(reviewIdx) {
      try {
        let response = await axios.get(
          `${backend}/review/mywrite/${reviewIdx}`,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
            },
          }
        );
        this.reviewDetail = response.data.result;

        console.log(response);
        return this.reviewDetail;
      } catch (e) {
        console.log(e);
      }
    },

    async updateReview(review, reviewImage) {
      const formData = new FormData();

      let json = JSON.stringify(review);
      formData.append("review", new Blob([json], { type: "application/json" }));
      formData.append("reviewImage", reviewImage);

      try {
        let response = await axios.patch(`${backend}/review/update`, formData, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "multipart/form-data",
          },
        });
        if (response.data.isSuccess === true) {
          alert("후기글을 수정하였습니다.");
          window.location.href = "/review/" + review.reviewIdx;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "REVIEW-002") {
            alert(
              "후기글 제목이 이미 등록되어 있는 제목입니다. 제목을 변경해주세요."
            );
          } else if(e.response.data.code === "REVIEW-001") {
            alert("수정하고자 하는 후기글을 찾을 수 없습니다.")
          }
        }
      }
    },
  },
});
