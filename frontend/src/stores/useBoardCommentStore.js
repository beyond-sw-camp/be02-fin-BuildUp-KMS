import { defineStore } from "pinia";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

const backend = "http://localhost:8080"; 


export const useBoardCommentStore = defineStore({
  id: "boardComment",
  state: () => ({
    // boardCommentContent 상태를 정의합니다.
    boardComments: [],
  }),
  actions: {
    // createBoardComment 액션을 정의합니다.
    async createBoardComment(boardCommentContent) {
      try {

        // const boardIdx = useBoardStore().boardIdx;
        const boardIdx = 1;

        // let token = VueJwtDecode.decode(sessionStorage.getItem("token")).id;
        let token = "토큰넣기";


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
      } catch (error) {
        console.error("ERROR : ", error);
      }
    },
  },
});
