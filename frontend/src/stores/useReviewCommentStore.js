import axios from "axios";
import { defineStore } from "pinia";
import VueJwtDecode from "vue-jwt-decode";

      // const backend = "http://192.168.0.61/api";
      const backend = "http://localhost:8080";

const accessToken = localStorage.getItem("accessToken");
const refreshToken = localStorage.getItem("refreshToken");

export const useReviewCommentStore = defineStore("reviewComment", {
  state: () => ({
    reviewCommentList: [],
    reviewReplyList: [],
    reviewCommentUpList: [],
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

    // 댓글 조회
    async getReviewCommentList(reviewIdx) {
      try {
        let response = await axios.get(
          backend + `/review/${reviewIdx}/comment`,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        this.reviewCommentList = response.data.result;
        console.log(this.reviewCommentList);
      } catch (e) {
        console.log(e);
      }
    },

    // 댓글 작성
    async createReviewComment(reviewCommentContent, reviewIdx) {
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

        const response = await axios.post(
          backend + `/review/${reviewIdx}/comment/create`,
          { reviewCommentContent: reviewCommentContent },
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
          window.location.href = `/review/${reviewIdx}`;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "COMMON-001") {
            alert("댓글 내용을 입력해주세요.");
          }
        }
      }
    },

    // 댓글 수정
    async updateReviewComment(reviewCommentContent, commentIdx, reviewIdx) {
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

        if (!accessToken) {
          throw new Error(
            "토큰이 없습니다. 사용자가 로그인되었는지 확인하세요."
          );
        }

        const response = await axios.patch(
          backend + `/review/${reviewIdx}/update/${commentIdx}`,
          { reviewCommentContent: reviewCommentContent },
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
          window.location.href = `/review/${reviewIdx}`;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "COMMON-001") {
            alert("수정할 내용을 입력해주세요.");
          }
        }
      }
    },

    // 댓글 삭제
    async deleteReviewComment(commentIdx, reviewIdx, userIdx) {
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

        if (!accessToken) {
          throw new Error(
            "토큰이 없습니다. 사용자가 로그인되었는지 확인하세요."
          );
        }

        const response = await axios.patch(
          `${backend}/review/${reviewIdx}/delete/${commentIdx}`,
          { userIdx: userIdx },
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
          window.location.href = `/review/${reviewIdx}`;
        }
      } catch (error) {
        console.error("삭제 실패 : ", error);
      }
    },

    //  대댓글 작성
    async createReviewReply(reviewReplyContent, reviewIdx, reviewCommentIdx) {
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

        const response = await axios.post(
          backend + `/review/${reviewIdx}/comment/create/${reviewCommentIdx}`,
          { reviewReplyContent: reviewReplyContent },
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
          window.location.href = `/review/${reviewIdx}`;
        }
      } catch (e) {
        if (e.response && e.response.data) {
          console.log(e.response.data);
          if (e.response.data.code === "COMMON-001") {
            alert("댓글을 입력해주세요.");
          }
        }
      }
    },

    // 댓글 추천
    async reviewRecommend(commentIdx) {
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
          `${backend}/reviewcomment/up/create`,
          { reviewCommentIdx: commentIdx },
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

        console.log("Comment recommendation successful");
      } catch (error) {
        if (error.response && error.response.data.code === "REVIEWUP-001") {
          // This specific error code indicates the comment is already recommended
          console.log(
            "Comment is already recommended, attempting to cancel recommendation"
          );
          await this.cancelReviewComment(commentIdx);
        } else {
          console.error("Error recommending comment:", error);
        }
      }
    },

    // 댓글 추천 삭제
    async cancelReviewComment(commentIdx) {
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
          `${backend}/reviewcomment/up/delete/${commentIdx}`,
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

        console.log("Comment recommendation cancellation successful");
        // You may want to update your store's state here to reflect the cancellation
      } catch (error) {
        console.error("Error canceling comment recommendation:", error);
      }
    },

    // Inside your useReviewCommentStore
    async updateCommentRecommendationStatus() {
      const accessToken = localStorage.getItem("accessToken");
      if (!accessToken) {
        console.error("Authentication accessToken not found.");
        return;
      }

      for (let comment of this.reviewCommentList) {
        await this.updateRecommendationStatusForCommentOrReply(comment);
        if (comment.children) {
          for (let reply of comment.children) {
            await this.updateRecommendationStatusForCommentOrReply(reply);
          }
        }
      }
    },

    async updateRecommendationStatusForCommentOrReply(item) {
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

        const response = await axios.get(
          `${backend}/reviewcomment/up/check/${item.idx}`,
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

        item.isReviewCommentRecommended = response.data.result.status;
      } catch (error) {
        console.error(
          `Error checking recommendation status for item ${item.idx}:`,
          error
        );
      }
    },

    async checkReviewCommentUp(reviewCommentIdx) {
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

        const accessToken = localStorage.getItem("accessToken"); // Ensure you have a accessToken
        if (!accessToken) {
          throw new Error("No authentication accessToken found");
        }
        const response = await axios.get(
          `${backend}/reviewcomment/up/check/${reviewCommentIdx}`,
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

        // Adjust based on actual API response structure
        return response.data.result.status;
      } catch (error) {
        console.error("Error checking review comment recommendation:", error);
        return false; // or handle error differently
      }
    },
  },
});
