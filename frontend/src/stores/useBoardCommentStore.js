import { defineStore } from "pinia";
import axios from "axios";
// import VueJwtDecode from "vue-jwt-decode";

const backend = "http://localhost:8080";
// boardStore를 사용하면 해당 idx 가져오기..
// const boardIdx = useBoardStore().boardIdx;

// 토큰값 가져오기
// let token = VueJwtDecode.decode(localStorage.getItem("token")).id;

let token = (localStorage.getItem("token"));


const boardIdx = 1;




export const useBoardCommentStore = defineStore({
  id: "boardComment",
  state: () => ({
    // boardCommentContent 상태를 정의합니다.
    boardComments: [],
  }),
  actions: {
    /** -------------------댓글 작성--------------------- **/
    async createBoardComment(boardCommentContent) {
      try {

        if (!token) {
          throw new Error(
            "토큰이 없습니다. 사용자가 로그인되었는지 확인하세요."
          );
        }

        const response = await axios.post(
          `${backend}/board/${boardIdx}/comment/create`,
          { boardCommentContent },
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        console.log(response);
        console.log("게시판 댓글 작성 성공");
        // window.location.href = backend + `/${boardIdx}`;
        window.location.href =`http://localhost:8081/board/detail`;

      } catch (error) {
        console.error("ERROR : ", error);
      }
    },

        /** -------------------댓글 수정--------------------- **/

    async updateBoardComment(boardCommentContent, commentIdx) {
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
        window.location.href =`http://localhost:8081/board/detail`;

      } catch (error) {
        console.error("수정 실패 : ", error);
      }
    },

            /** -------------------댓글 삭제--------------------- **/

            async deleteBoardComment(commentIdx) {
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
                window.location.href =`http://localhost:8081/board/detail`;
        
              } catch (error) {
                console.error("수정 실패 : ", error);
              }
            },
  },
});
