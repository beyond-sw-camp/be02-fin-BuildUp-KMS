import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

const backend = "http://www.bootshelf.kro.kr/api";
// const backend = "http://localhost:8080";

const accessToken = localStorage.getItem("accessToken");
const refreshToken = localStorage.getItem("refreshToken");

export const useBoardCommentStore = defineStore({
  id: "boardComment",
  state: () => ({
    // boardCommentContent 상태를 정의합니다.
    boardComments: [],
    commentList: null,
    isTokenExpired: false,
  }),
  actions: {
    validateToken() {
      if (accessToken) {
        const decodedAccessToken = VueJwtDecode.decode(accessToken);
        const expirationTime = decodedAccessToken.exp;
        const currentTime = Math.floor(Date.now() / 1000);

        if (expirationTime - currentTime < 30) {
          this.isTokenExpired = true;
        } else {
          this.isTokenExpired = false;
        }
      }
    },

    /** -------------------댓글 작성--------------------- **/
    async createBoardComment(boardCommentContent, boardIdx) {
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

        console.log(accessToken);
        const response = await axios.post(
          backend + `/board/${boardIdx}/comment/create`,
          { boardCommentContent: boardCommentContent },
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
          window.location.href = `/board/${boardIdx}`;
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

    /** -------------------댓글 수정--------------------- **/

    async updateBoardComment(boardCommentContent, commentIdx, boardIdx) {
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
          `${backend}/board/${boardIdx}/update/${commentIdx}`,
          { boardCommentContent: boardCommentContent },
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
          window.location.href = `/board/${boardIdx}`;
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

    /** -------------------댓글 삭제--------------------- **/

    async deleteBoardComment(commentIdx, boardIdx, userIdx) {
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
          `${backend}/board/${boardIdx}/delete/${commentIdx}`,
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
          window.location.href = `/board/${boardIdx}`;
        }
      } catch (error) {
        console.error("삭제 실패 : ", error);
      }
    },

    async getBoardCommentList(boardIdx) {
      try {
        let response = await axios.get(`${backend}/board/${boardIdx}/comment`);
        this.commentList = response.data.result;

        console.log(this.commentList);

      } catch (error) {
        console.error(error);
      }
    },

    //  대댓글 작성
    async createBoardReply(boardReplyContent, commentIdx, boardIdx) {
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
          backend + `/board/${boardIdx}/comment/create/${commentIdx}`,
          { boardReplyContent: boardReplyContent },
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
          window.location.href = `/board/${boardIdx}`;
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

    // async createBoardCommentUp(token, requestBody) {
    //   try {
    //     let response = await axios.post(backend + "/boardcomment/up/create", requestBody, {
    //       headers: {
    //         "Content-Type": "application/json",
    //         Authorization: `Bearer ${token}`,
    //       }
    //     })
    //     return response;

    //   } catch (e) {
    //     console.error("게시글 댓글 추천 실패");
    //     throw e;
    //   }
    // },

    // async createBoardCommentUp(commentIdx) {
    //   try {
    //     let response = await axios.post(backend + "/boardcomment/up/create",
    //     { boardCommentIdx: commentIdx }, {
    //       headers: {
    //         "Content-Type": "application/json",
    //         Authorization: `Bearer ${token}`,
    //       }
    //     })
    //     return response;

    //   } catch (e) {
    //     console.error("게시글 댓글 추천 실패", e);
    //     throw e;
    //   }
    // },

    // async checkBoardCommentUp(token, boardCommentIdx) {
    //   try {
    //     let response = await axios.get(`${backend}/boardcomment/check/${boardCommentIdx}`, {
    //       headers: {
    //         Authorization: `Bearer ${token}`,
    //       },
    //     });
    //     console.log(response);

    //     this.isCommentRecommend = response.data.result.status;

    //     return response;
    //   } catch (e) {
    //     console.error(e);
    //     throw e;
    //   }
    // },

    // async cancelBoardCommentUp(token, boardCommentIdx) {
    //   try {
    //     let response = await axios.patch(`${backend}/boardcomment/delete/${boardCommentIdx}`, {}, {
    //       headers: {
    //         Authorization: `Bearer ${token}`,
    //         "Content-Type": "application/json"
    //       },
    //     });
    //     console.log(response);
    //   } catch (e) {
    //     console.error(e);
    //     throw e;
    //   }
    // }
  },
});
