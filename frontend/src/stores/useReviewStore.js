import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

const backend = "http://192.168.0.61/api";
// const backend = "http://localhost:9999";

const accessToken = localStorage.getItem("accessToken");
const refreshToken = localStorage.getItem("refreshToken");

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
    isPageExist: true,
    isTokenExpired: false,
  }),
  actions: {
    validateToken() {
      const decodedAccessToken = VueJwtDecode.decode(accessToken);
      const expirationTime = decodedAccessToken.exp;
      const currentTime = Math.floor(Date.now() / 1000);

      if (expirationTime - currentTime < 30) {
        this.isTokenExpired = true;
      } else {
        this.isTokenExpired = false;
      }
    },

    async createReview(review) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.post(
          backend + `/main/review/create`,
          review,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

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
          backend +
            `/main/review/list/${reviewCategoryIdx}/${sortType}?${params}`,
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
            "/search/review/list?categoryIdx=" +
            reviewCategoryIdx +
            "&sortType=" +
            sortType +
            "&title=" +
            searchTerm +
            "&" +
            params,
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

    async getSearchTotalReviewList(selectedDropdownValue, title, page = 1) {
      try {
        this.isLoading = true;

        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend +
            "/search/review/total/list?selectedDropdownValue=" +
            selectedDropdownValue +
            "&title=" +
            title +
            "&" +
            params,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        // this.totalCnt = response.data.result.totalCnt;
        this.totalCnt = response.data.result.totalHits;

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

    async getSearchSortReviewList(searchType, sortType, title, page = 1) {
      try {
        this.isLoading = true;

        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(
          backend +
            "/search/review/sort/list?searchType=" +
            searchType +
            "&sortType=" +
            sortType +
            "&title=" +
            title +
            "&" +
            params,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        // this.totalCnt = response.data.result.totalCnt;
        this.totalCnt = response.data.result.totalHits;

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
        let response = await axios.get(backend + `/main/review/${reviewIdx}`, {
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

    async createReviewUp(accessToken, requestBody) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.post(
          backend + "/main/reviewup/create",
          requestBody,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        return response;
      } catch (e) {
        console.error("후기 추천 실패", e);
        throw e;
      }
    },

    async createReviewScrap(accessToken, requestBody) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.post(
          backend + "/main/reviewscrap/create",
          requestBody,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        return response;
      } catch (e) {
        console.error("후기 스크랩 실패", e);
        throw e;
      }
    },

    async checkReviewUp(accessToken, reviewIdx) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.get(
          `${backend}/main/reviewup/check/${reviewIdx}`,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        this.isRecommended = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async checkReviewScrap(accessToken, reviewIdx) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.get(
          `${backend}/main/reviewscrap/check/${reviewIdx}`,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        this.isScrapped = response.data.result.status;

        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async cancelReviewUp(accessToken, reviewUpIdx) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.patch(
          `${backend}/main/reviewup/delete/${reviewUpIdx}`,
          {},
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async cancelReviewScrap(accessToken, reviewScrapIdx) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.patch(
          `${backend}/main/reviewscrap/delete/${reviewScrapIdx}`,
          {},
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async createReviewCategory(categoryName) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.post(
          backend + "/main/admin/review/create",
          { categoryName: categoryName },
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        if (response.data.isSuccess === true) {
          alert(`${categoryName} 생성 완료!`);
          this.$router.push({ path: `/admin/review/category/register` });
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
          backend + "/main/admin/review/delete/" + reviewCategoryIdx
        );

        if (response.data.isSuccess === true) {
          window.location.href = "/admin/review/category";
        }
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async updateReviewCategory(reviewCategoryIdx, newCategoryName) {
      try {
        let response = await axios.patch(
          backend + "/main/admin/review/update/" + reviewCategoryIdx,
          { categoryName: newCategoryName },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        if (response.data.isSuccess === true) {
          alert(`카테고리 이름 수정 완료!`);
          this.$router.push({ path: `/admin/review/update` });
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
    async findReviewDetailByUserIdx(reviewIdx) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.get(
          `${backend}/main/review/mywrite/${reviewIdx}`,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

        this.reviewDetail = response.data.result;

        return this.reviewDetail;
      } catch (e) {
        console.log(e);
      }
    },

    async updateReview(review) {
      try {
        this.validateToken();

        const headers = this.isTokenExpired
          ? {
              Authorization: `Bearer ${accessToken}`,
              RefreshToken: `Bearer ${refreshToken}`,
              "Content-Type": "application/json",
            }
          : {
              Authorization: `Bearer ${accessToken}`,
              "Content-Type": "application/json",
            };

        let response = await axios.patch(
          `${backend}/main/review/update`,
          review,
          {
            headers,
          }
        );

        if (response.headers["new-refresh-token"] != null) {
          if (
            response.headers["new-refresh-token"] !=
            localStorage.getItem("refreshToken")
          ) {
            localStorage.setItem("refreshToken", "");
            localStorage.setItem(
              "refreshToken",
              response.headers["new-refresh-token"]
            );
          }
        }

        if (response.headers["new-access-token"] != null) {
          if (
            response.headers["new-access-token"] !=
            localStorage.getItem("accessToken")
          ) {
            localStorage.setItem("accessToken", "");
            localStorage.setItem(
              "accessToken",
              response.headers["new-access-token"]
            );
          }
        }

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
          } else if (e.response.data.code === "REVIEW-001") {
            alert("수정하고자 하는 후기글을 찾을 수 없습니다.");
          }
        }
      }
    },

    /* es search after 적용 (첫 페이지) */
    async getReviewListByQuery(query, option) {
      try {
        this.isLoading = true;

        let response = await axios.get(
          backend +
          "/es/review/v2/search/main?selectedDropdownValue=" +
          + option +
          "&title=" +
          query
        );
        this.reviewList = response.data.result.list;
        this.totalCnt = response.data.result.totalHits;
        this.lastSearchAfter = response.data.result.lastSearchAfter;
        console.log(this.totalCnt);

        if (this.reviewList.length === 0) {
          this.isReviewExist = false;
          this.isPageExist = false;
        } else {
          this.isReviewExist = true;
          this.isPageExist = true;
        }
      } catch (error) {
        console.error(error);
      } finally {
        this.isLoading = false;
      }
    },

    /* es search after 적용 (더보기) */
    async getReviewListByQueryNext(query, option, searchAfterStr) {
      try {
        this.isLoading = true;

        // `searchAfterStr` 배열을 쿼리 문자열로 변환
        const searchAfterQueryParam = searchAfterStr;

        let response = await axios.get(
          `${backend}/es/review/v2/search/main?selectedDropdownValue=${option}&title=${query}&searchAfterStr=${searchAfterQueryParam}`
        );

        if (response.data.result.list.length === 0) {
          this.noMoreData = true; // 데이터가 더 이상 없음을 표시
        } else {
          // 새로운 검색 결과를 기존 목록에 추가
          this.reviewList = [...this.reviewList, ...response.data.result.list];
          this.totalCnt = response.data.result.totalHits;
          // `lastSearchAfter` 값을 새로운 검색 결과에 기반하여 업데이트
          this.lastSearchAfter = response.data.result.lastSearchAfter;
          this.noMoreData = false; // 더 불러올 데이터가 있으므로 메시지 숨김
        }
      } catch (error) {
        console.error(error);
        this.isReviewExist = false;
        this.isPageExist = false;
      } finally {
        this.isLoading = false;
      }
    },

    /* es search after 적용 by order (첫 페이지) */
    async getReviewListByQueryWithOrder(query, option, order) {
      try {
        this.isLoading = true;

        let response = await axios.get(
          backend +
          "/search/review/es/review/v2/search/order?selectedDropdownValue=" +
          + option +
          "&title=" +
          query + 
          "&sortType=" + 
          order
        );

        this.reviewList = response.data.result.list;
        this.totalCnt = response.data.result.totalHits;
        this.lastSearchAfter = response.data.result.lastSearchAfter;
        console.log(this.totalCnt);

        if (this.reviewList.length === 0) {
          this.isReviewExist = false;
          this.isPageExist = false;
        } else {
          this.isReviewExist = true;
          this.isPageExist = true;
        }
      } catch (error) {
        console.error(error);
      } finally {
        this.isLoading = false;
      }
    },

    /* es search after 적용 by order (더보기) */
    async getReviewListByQueryNextWithOrder(query, option, order, searchAfterStr) {
      try {
        this.isLoading = true;

        let response = await axios.get(
          `${backend}/search/review/es/review/v2/search/order?selectedDropdownValue=${option}&title=${query}&sortType=${order}&searchAfterStr=${searchAfterStr}`
        );

        if (response.data.result.list.length === 0) {
          this.noMoreData = true; // 데이터가 더 이상 없음을 표시
        } else {
          // 새로운 검색 결과를 기존 목록에 추가
          this.reviewList = [...this.reviewList, ...response.data.result.list];
          this.totalCnt = response.data.result.totalHits;
          // `lastSearchAfter` 값을 새로운 검색 결과에 기반하여 업데이트
          this.searchAfterStr = `${response.data.result.lastSearchAfter[0]}, "${String(response.data.result.lastSearchAfter[1])}"`;
          this.noMoreData = false; // 더 불러올 데이터가 있으므로 메시지 숨김
        }
      } catch (error) {
        console.error(error);
        this.isReviewExist = false;
        this.isPageExist = false;
      } finally {
        this.isLoading = false;
      }
    },

  },
});
