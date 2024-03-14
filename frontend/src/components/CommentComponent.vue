<template>
  <div class="css-f7no94" v-for="(comment, index) in commentList" :key="index">
    <!-- <div class="css-f7no94"> -->
    <div class="css-3o2y5e">
      <div width="36px" height="36px" class="css-jg5tbe">
        <img alt="나의얼굴" width="34px" height="34px"
          src="https://blog.kakaocdn.net/dn/5UYz8/btq4diRXkGE/HkHufR4G8X4bIX3h3lNjck/img.jpg" />
      </div>
    </div>
    <div class="css-14f8kx2">
      <div class="css-1psklmw">
        <div class="css-dyzp2y">
          <div class="css-wqf8ry">{{ comment.nickName }}</div>
          <div class="css-emxp16"></div>
          <div class="css-emxp16">{{ comment.createAt }}</div>
        </div>
        <div class="css-dyzp2y-001">
          <div class="css-emxp17">수정</div>
          <div class="css-emxp17" @click="deleteComment(comment.idx)">삭제</div>
        </div>
        <div class="css-emxp17">
          <img width="18px" height="17px" src="https://img.icons8.com/pastel-glyph/64/facebook-like--v1.png"
            alt="facebook-like--v1" />
        </div>
      </div>
      <div class="editedCommentContent">
        <input type="text" class="css-comment" v-model="updateComment">
        <p>{{ comment.boardCommnetContent }}</p>
      </div>
      <div clas="css-btn">
        <p class="css-reply">대댓글쓰기</p>
        <button class="css-update" @click="saveComment(comment.idx)">저장</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useBoardCommentStore } from '@/stores/useBoardCommentStore';
// import VueJwtDecode from "vue-jwt-decode";




import axios from "axios";
const backend = "http://localhost:8080";
let boardIdx = 1;

export default {
  name: "CommentComponent",

  data() {
    return {
      commentList: [],
      editable: false,
    }
  },

  methods: {
    async getBoardCommentList() {
      try {
        let response = await axios.get(backend + `/board/${boardIdx}/comment`);
        this.commentList = response.data.result;
        console.log(this.commentList);
      } catch (error) {
        console.error(error);
      }
    },
    // async getBoardCommentList() {
    //   try {
    //     await useBoardCommentStore.getBoardCommentList();
    //     this.commentList = useBoardCommentStore.boardComments;
    //     console.log(this.commentList);
    //   } catch (error) {
    //     console.error(error);
    //   }
    // }

    async saveComment(commentIdx) {
      try {
        await useBoardCommentStore().updateBoardComment(this.updateComment, commentIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 작성 실패:', error);
      }
    },

    async deleteComment(commentIdx) {
      try {
        await useBoardCommentStore().deleteBoardComment(commentIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      }

    }

  },

  mounted() {
    this.getBoardCommentList();
  }
};
</script>


<style scoped>
.css-update {
  background-color: rgb(52, 152, 219, 0.2);
  font-size: 12px;
  font-weight: 700;
  width: 70px;
  height: 25px;
  border-radius: 10px;
  float: right;
  margin-right: 10px;
  cursor: pointer;
  font-family: Pretendard;
  border: none;
}

.css-btn {
  display: flex;
}

textarea {
  width: 100%;
  height: 100px;
  border: none;
  resize: none;
  outline: none;
  font-family: Pretendard;

}

* {
  margin: 0;
  line-height: 1.5;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

div {
  display: block;
}

html {
  font-size: 10px;
  scroll-behavior: smooth;
  display: block;
}

.css-1k90lkz {
  padding: 39px 20px;
  border-bottom: none;
  background-color: #fff;
}

.css-qzobjv {
  display: flex;
  flex-direction: column;
  align-items: start;
  justify-content: center;
  gap: 12px;
}

.css-f7no94 {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 16px;
  font-family: Pretendard;
}

.css-3o2y5e {
  margin-top: 10px;
  display: block;
}

.css-jg5tbe {
  width: 36px;
  height: 36px;
  border: solid 1px #adb5bd;
  background-color: #f1f1f1;
  border-radius: 100px;
  overflow: hidden;
}

.css-14f8kx2 {
  width: 100%;
  max-width: 545px;
  padding: 20px;
  border-radius: 12px;
  background-color: #f4f5f6;
}

.css-1psklmw {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 8px;
}

.css-dyzp2y {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 3px;
}

.css-dyzp2y-001 {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  margin-left: 233px;
}

.css-wqf8ry {
  font-size: 14px;
  font-weight: 700;
  line-height: 1.5;
  text-align: left;
  color: #1c1d1e;
}

.css-emxp16 {
  font-size: 10px;
  line-height: 1.3;
  text-align: left;
  color: #9da7ae;
}

.css-emxp17 {
  font-size: 12px;
  line-height: 1.3;
  text-align: left;
  color: #838689;
  cursor: pointer;
}

.editedCommentContent {
  color: #838689;
}

.css-comment {
  font-size: 14px;
}

.css-reply {
  font-size: 12px;
  margin-top: 15px;
  cursor: pointer;
  color: #9da7ae;
}

.css-f7no94 {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 16px;
}

.css-3o2y5e {
  margin-top: 10px;
  display: block;
}

.css-1psklmw {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 8px;
}

.css-dyzp2y {
  display: flex;
  align-items: center;
  justify-content: start;
  gap: 4px;
}

.css-001 {
  flex: 1;
  margin: 10px;
  outline: 0;
  border: none;
}
</style>
