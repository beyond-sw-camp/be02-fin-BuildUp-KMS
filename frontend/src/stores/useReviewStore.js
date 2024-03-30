import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://192.168.0.61/api";
// const backend = "http://localhost:8080";
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
    isPageExist: true
  }),
  actions: {
    async createReview(review) {
      // const formData = new FormData();

      // let json = JSON.stringify(review);
      // formData.append("review", new Blob([json], { type: "application/json" }));
      // formData.append("reviewImage", reviewImage);

      try {
        let response = await axios.post(backend + `/review/create`, review, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
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
          } else if(e.response.data.code === "COMMON-001") {
            alert("카테고리, 제목, 별점은 필수로 입력하셔야 합니다.")
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

        if (this.reviewList.length === 0) {
          this.isReviewExist = false;
          this.isPageExist = false;
        } else {
          this.isReviewExist = true;
          this.isPageExist = true;
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

        if (this.reviewList.length === 0) {
          this.isReviewExist = false;
          this.isPageExist = false;
        } else {
          this.isReviewExist = true;
          this.isPageExist = true;
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

        this.isScrapped = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async cancelReviewUp(token, reviewUpIdx) {
      try {
        await axios.patch(
          `${backend}/reviewup/delete/${reviewUpIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async cancelReviewScrap(token, reviewScrapIdx) {
      try {
        await axios.patch(
          `${backend}/reviewscrap/delete/${reviewScrapIdx}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async createReviewCategory(categoryName) {
      try {
        let response =
        await axios.post(
          backend + "/admin/review/create",
          { categoryName: categoryName },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        if(response.data.isSuccess === true){
          alert(`${categoryName} 생성 완료!`);
          this.$router.push({ path: `/admin/review/category/register`});
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "REVIEW-CATEGORY-002") {
            alert("동일한 이름의 카테고리가 이미 존재합니다.");
          } 
        }
      }
    },

    async deleteReviewCategory(reviewCategoryIdx) {
      try {
        let response = await axios.delete(
          backend + "/admin/review/delete/" + reviewCategoryIdx
        );

        if(response.data.isSuccess === true) {
          window.location.href="/admin/review/category"
        }
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async updateReviewCategory(reviewCategoryIdx, newCategoryName) {
      try {
        let response =
        await axios.patch(
          backend + "/admin/review/update/" + reviewCategoryIdx,
          {categoryName: newCategoryName},
          {headers: {
            "Content-Type": "application/json",
          },
        }
        );
        if(response.data.isSuccess === true) {
          alert(`카테고리 이름 수정 완료!`);
          this.$router.push({ path: `/admin/review/update`});
        }
      } catch (e) {
        if(e.response && e.response.data){
          console.log(e.response.data);
          if (e.response.data.code === "REVIEW-CATEGORY-002"){
            alert("동일한 이름의 카테고리가 이미 존재합니다.");
          }
        }
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

        return this.reviewDetail;
      } catch (e) {
        console.log(e);
      }
    },

    async updateReview(review) {
      // const formData = new FormData();

      // let json = JSON.stringify(review);
      // formData.append("review", new Blob([json], { type: "application/json" }));
      // formData.append("reviewImage", reviewImage);

      try {
        let response = await axios.patch(`${backend}/review/update`, review, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
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
