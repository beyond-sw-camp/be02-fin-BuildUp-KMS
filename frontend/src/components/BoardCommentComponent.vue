<template>
  <div class="css-f7no94" v-for="comment in commentList" :key="comment.idx">
    <!-- <div class="css-f7no94"> -->
    <div class="css-3o2y5e">
      <div width="36px" height="36px" class="css-jg5tbe">
        <img alt="나의얼굴" width="34px" height="34px"
          :src="comment.profileImage" />
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
          <div class="css-emxp17" @click="toggleEditMode(comment)">수정</div>
          <div class="css-emxp17" @click="deleteComment(comment.idx)">삭제</div>
        </div>
        <div @click="toggleRecommendation(comment)">
          <img width="20px" height="20px"
            :src="isCommentRecommended ? require('../assets/img/up_ok.png') : require('../assets/img/up.png')"
            alt="facebook-like"/>
          <p style="font-size: 10px; text-align: center">{{ comment.upCnt }}</p>
        </div>
      </div>
      <div class="editedCommentContent">
        <input class="css-comment" type="text" v-model="updateComment" :placeholder="comment.boardCommnetContent" :readonly="!comment.editMode">
      </div>
      <div clas="css-btn">
        <p class="css-reply"  v-if="!comment.editMode">대댓글쓰기</p>
        <button class="css-update"  v-if="comment.editMode" @click="saveComment(comment.idx)">저장</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useBoardCommentStore } from '@/stores/useBoardCommentStore';
import { useBoardStore } from '@/stores/useBoardStore';
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "CommentComponent",
  computed: {
    ...mapStores(useBoardCommentStore, useBoardStore),
  },
  props: {
    commentList: {
      type: Array,
      required: true
    },
    boardIdx: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      editable: false,
      updateComment: '',
    }
  },

  created() {
    this.checkBoardCommentUp();
  },

  methods: {
    async saveComment(commentIdx) {
      try {
        await this.boardCommentStore.updateBoardComment(this.updateComment, commentIdx, this.boardIdx);
      } catch (error) {
        console.error('댓글 작성 실패:', error);
      }
    },

    async deleteComment(commentIdx) {
      try {
        await this.boardCommentStore.deleteBoardComment(commentIdx, this.boardIdx);
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      }
    },

    showBtn(commentUserIdx) {
      let token = localStorage.getItem("token");
      if (!token) return false; // 토큰이 없으면 버튼을 보이지 않음
      let decodedToken = VueJwtDecode.decode(token).idx;
      return commentUserIdx === decodedToken;
    },

    toggleEditMode(comment) {
      // 수정 모드를 전환
      comment.editMode = !comment.editMode;
      // 수정 모드가 활성화되면 편집 상자에 원래 댓글 내용을 설정
      if (comment.editMode) {
        comment.updatedContent = comment.boardCommnetContent;
      }
    },

    async toggleRecommendation(comment) {
    try {
      await this.createBoardCommentUp(comment.idx);
      console.log('댓글이 성공적으로 추천되었습니다.');
    } catch (error) {
      console.error('댓글 추천 실패:', error);
    }
  },

    // async createBoardCommentUp(comment) {
    //   let token = window.localStorage.getItem("token");
    //   let requestBody = {
    //     boardCommentIdx: comment.idx
    //   };

    //   try {
    //     if (this.isCommentRecommended) {
    //       await this.boardCommentStore.cancelBoardCommentUp(token, comment.idx);
    //       console.log("게시글 댓글 추천 취소 성공");
    //       comment.isCommentRecommended = false;

    //       window.location.reload();
    //     } else {
    //       const response = await this.boardCommentStore.createBoardCommentUp(token, requestBody);

    //       if (response.status === 200 && response.data) {
    //         console.log("게시글 댓글 추천 성공!");
    //         this.isCommentRecommended = true;
    //       } else {
    //         console.error("게시글 댓글 추천 실패");
    //         alert("게시글 댓글 추천 실패");
    //       }
    //     }
    //   } catch (e) {
    //     console.error("게시글 댓글 추천 과정에서 문제가 발생했습니다!", e);
    //   }
    // },

    async createBoardCommentUp(commentIdx) {
      try {
        await this.boardCommentStore.createBoardCommentUp(commentIdx);
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      }
    },

    async checkBoardCommentUp(comment) {
      try {
        let token = window.localStorage.getItem("token");
        let response = await this.boardCommentStore.checkBoardCommentUp(token, comment.idx);
        console.log(response);

        if (response.data && response.data.result.status === true) {
          this.isCommentRecommended = true;
          this.boardCommentIdx = response.data.result.boardCommentIdx;
        } else {
          this.isCommentRecommended = false;
        }
      } catch (e) {
        console.error(e);
      }
    }

  },
};
</script>


<style scoped>
input{
  border: none;
  background-color: #f4f5f6;
  outline: none;
  color: black;
}

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
  width: 34px;
  height: 34px;
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
