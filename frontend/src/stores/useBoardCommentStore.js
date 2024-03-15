import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
// import VueJwtDecode from "vue-jwt-decode";

const backend = "http://localhost:8080";
// boardStore를 사용하면 해당 idx 가져오기..
// const boardIdx = useBoardStore().boardIdx;

// 토큰값 가져오기
// let token = VueJwtDecode.decode(localStorage.getItem("token")).id;

let token = (localStorage.getItem("token"));


export const useBoardCommentStore = defineStore({
  id: "boardComment",
  state: () => ({
    // boardCommentContent 상태를 정의합니다.
    boardComments: [],
    commentList: null,
  }),
  actions: {
    /** -------------------댓글 작성--------------------- **/
    async createBoardComment(boardCommentContent, boardIdx) {
      try {
        console.log(token);
        const response = await axios.post(
        backend + `/board/${boardIdx}/comment/create`,
          { boardCommentContent: boardCommentContent },
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        console.log(response);
        console.log("게시판 댓글 작성 성공");
        window.location.href = `http://localhost:8081/board/${boardIdx}`;

      } catch (error) {
        console.error("ERROR : ", error);
      }
    },

    /** -------------------댓글 수정--------------------- **/

    async updateBoardComment(boardCommentContent, commentIdx, boardIdx) {
      try {
        if (!token) {
          throw new Error(
            "토큰이 없습니다. 사용자가 로그인되었는지 확인하세요."
          );
        }

        const response = await axios.patch(`${backend}/board/${boardIdx}/update/${commentIdx}`,
          { boardCommentContent: boardCommentContent },
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        console.log(commentIdx);
        console.log(boardCommentContent);

        console.log(response);
        console.log("게시판 댓글 수정 성공");
        router.push(`http://localhost:8081/board/${boardIdx}`);

      } catch (error) {
        console.error("수정 실패 : ", error);
      }
    },

    /** -------------------댓글 삭제--------------------- **/

    async deleteBoardComment(commentIdx, boardIdx) {
      try {
        if (!token) {
          throw new Error(
            "토큰이 없습니다. 사용자가 로그인되었는지 확인하세요."
          );
        }

        const response = await axios.delete(`${backend}/board/${boardIdx}/delete/${commentIdx}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        console.log(response);
        console.log("게시판 댓글 수정 성공");
        router.push(`http://localhost:8081/board/${boardIdx}`);

      } catch (error) {
        console.error("수정 실패 : ", error);
      }
    },

    async getBoardCommentList(boardIdx) {
      try {
        let response = await axios.get(`${backend}/board/${boardIdx}/comment`);
        console.log(response);
        this.commentList = response.data.result;

        console.log(this.commentList);

      } catch (error) {
        console.error(error);
      }
    },
  },
});
