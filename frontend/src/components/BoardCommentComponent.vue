<template>
    <div v-for="comment in filteredBoardComments" :key="comment.idx">
  <!-- <div class="css-f7no94" v-for="comment in commentList" :key="comment.idx"> -->
    <div class="css-f7no94" v-if="comment.status">
      <div class="css-3o2y5e">
        <div width="36px" height="36px" class="css-jg5tbe">
          <img alt="나의얼굴" width="34px" height="34px" :src="comment.profileImage" />
        </div>
      </div>
      <div class="css-14f8kx2">
        <div class="css-1psklmw">
          <div class="css-dyzp2y">
            <div class="css-wqf8ry">{{ comment.nickName }}</div>
            <div class="css-emxp16"></div>
            <div class="css-emxp16">{{ comment.createAt }}</div>
          </div>
          <div class="css-dyzp2y-001" v-if="showBtn(comment.userIdx)">
            <div class="css-emxp17" @click="toggleEditMode(comment)">수정</div>
            <div class="css-emxp17" @click="deleteComment(comment.idx, comment.userIdx)">삭제</div>
          </div>
          <!-- 댓글 추천 -->
          <!-- <div @click="toggleRecommendation(comment)">
          <img width="20px" height="20px"
            :src="isCommentRecommended ? require('../assets/img/up_ok.png') : require('../assets/img/up.png')"
            alt="facebook-like"/>
          <p style="font-size: 10px; text-align: center">{{ comment.upCnt }}</p>
        </div> -->
        <!-- / 댓글 추천 -->
        </div>
        <div class="editedCommentContent">
          <input class="css-comment" type="text" v-model="updateComment" 
          :placeholder="comment.boardCommnetContent" :readonly="!comment.editMode">
        </div>
        <div clas="css-btn">
          <p class="css-reply" @click="toggleReplyForm(comment)"
          v-if="!comment.editMode && !comment.showReplyForm">대댓글쓰기</p>
          <p class="css-reply" @click="toggleReplyForm(comment)" 
          v-if="!comment.editMode && comment.showReplyForm">닫기</p>
          <button class="css-update" v-if="comment.editMode" @click="saveComment(comment.idx)">저장</button>
        </div>
      </div>
    </div>

    <!-- 대댓글 쓰기 -->
    <div>
      <div class="css-f7no94-reply" v-if="comment.showReplyForm">
        <div class="css-3o2y5e">
          <div width="36px" height="36px" class="css-jg5tbe">
            <img alt="나의얼굴" width="34px" height="34px" :src="userStore.user.profileImage">
          </div>
        </div>
        <div class="css-14f8kx2-0318">
          <div class="editedCommentContent">
            <input class="css-comment-0318" type="text" placeholder="댓글을 남겨주세요" v-model="boardReply">
          </div>
          <div clas="css-btn">
            <button class="css-update" @click="saveReply(comment.idx)">저장</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 대댓글 출력 -->
    <div v-if="comment.children && comment.children.length > 0">
      <div v-for="childComment in comment.children" :key="childComment.idx">
        <div class="css-f7no94-reply" v-if="childComment.status">
          <div class="css-3o2y5e">
            <div width="36px" height="36px" class="css-jg5tbe">
              <img alt="나의얼굴" width="34px" height="34px" :src="childComment.profileImage">
            </div>
          </div>
          <div class="css-14f8kx2-001">
            <div class="css-1psklmw">
              <div class="css-dyzp2y">
                <div class="css-wqf8ry-001">{{ childComment.nickName }}</div>
                <div class="css-emxp16-001"></div>
                <div class="css-emxp16-001">{{ childComment.createAt }}</div>
              </div>
              <div class="css-dyzp2y-002" v-if="showBtn(childComment.userIdx)">
                <div class="css-emxp17-001" @click="toggleEditMode(childComment)">수정</div>
                <div class="css-emxp17-001" @click="deleteComment(childComment.idx, childComment.userIdx)">삭제</div>
              </div>

              <!-- 대댓글 추천 -->
              <!-- <div class="css-emxp17">
                <img width="18" height="18" src="https://img.icons8.com/sf-regular/48/facebook-like.png"
                  alt="facebook-like" @click="reviewRecommend(childComment.idx)" />
                <img width="18" height="18" src="https://img.icons8.com/sf-regular-filled/48/facebook-like.png"
                  alt="facebook-like" @click="cancelReviewComment(childComment.idx)" />
              </div> -->
              <!-- /대댓글 추천 -->
            </div>
            <div class="editedCommentContent">
              <input class="css-comment" type="text" v-model="updateComment"
                :placeholder="childComment.boardCommnetContent" :readonly="!childComment.editMode">
            </div>
            <div clas="css-btn">
              <button class="css-update" v-if="childComment.editMode" @click="saveComment(childComment.idx)">저장</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useBoardCommentStore } from '@/stores/useBoardCommentStore';
import { useBoardStore } from '@/stores/useBoardStore';
import { useUserStore } from '../stores/useUserStore';
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "CommentComponent",
  computed: {
    ...mapStores(useBoardCommentStore, useBoardStore, useUserStore),
    filteredBoardComments() {
  if (!this.boardCommentStore.commentList) {
    return []; 
  }
  return this.boardCommentStore.commentList.filter(comment => comment.status);
}
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
      // updateComment: '',
    }
  },

  created() {
    // this.checkBoardCommentUp();
  },

  methods: {
    async saveComment(commentIdx) {
      try {
        await this.boardCommentStore.updateBoardComment(this.updateComment, commentIdx, this.boardIdx);
      } catch (error) {
        console.error('댓글 수정 실패:', error);
      }
    },

    async deleteComment(commentIdx, userIdx) {
      try {
        await this.boardCommentStore.deleteBoardComment(commentIdx, this.boardIdx, userIdx);
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      }
    },

    async saveReply(commentIdx) {
      try {
        await this.boardCommentStore.createBoardReply(this.boardReply, commentIdx, this.boardIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 작성 실패:', error);
      }
    },

    showBtn(commentUserIdx) {
      let token = localStorage.getItem("token");
      if (!token) return false; // 토큰이 없으면 버튼을 보이지 않음
      let decodedToken = VueJwtDecode.decode(token).idx;
      return commentUserIdx === decodedToken;
    },

    toggleEditMode(comment) {
      comment.editMode = !comment.editMode;
      if (comment.editMode) {
        comment.updateComment = comment.boardCommnetContent;
      }
    },

    toggleReplyForm(comment) {
      if (comment.showReplyForm) {
        comment.showReplyForm = false; // 닫기 버튼을 클릭하여 대댓글 입력 폼을 닫음
      } else {
        comment.showReplyForm = true; // 대댓글쓰기 버튼을 클릭하여 대댓글 입력 폼을 엶
      }
    },

    // async toggleRecommendation(comment) {
    //   try {
    //     await this.createBoardCommentUp(comment.idx);
    //     console.log('댓글이 성공적으로 추천되었습니다.');
    //   } catch (error) {
    //     console.error('댓글 추천 실패:', error);
    //   }
    // },

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

    // async createBoardCommentUp(commentIdx) {
    //   try {
    //     await this.boardCommentStore.createBoardCommentUp(commentIdx);
    //   } catch (error) {
    //     console.error('댓글 삭제 실패:', error);
    //   }
    // },

    // async checkBoardCommentUp(comment) {
    //   try {
    //     let token = window.localStorage.getItem("token");
    //     let response = await this.boardCommentStore.checkBoardCommentUp(token, comment.idx);
    //     console.log(response);

    //     if (response.data && response.data.result.status === true) {
    //       this.isCommentRecommended = true;
    //       this.boardCommentIdx = response.data.result.boardCommentIdx;
    //     } else {
    //       this.isCommentRecommended = false;
    //     }
    //   } catch (e) {
    //     console.error(e);
    //   }
    // }

  },
};
</script>


<style scoped>
.css-14f8kx2-0318 {
  width: 100%;
  max-width: 545px;
  padding: 15px;
  border-radius: 12px;
  background-color: #fff;
  border: 2px solid #f4f5f6;
}

.css-comment-0318 {
  font-size: 14px;
  width: 100%;
  background-color: #fff;
  font-family: Pretendard;
}

.css-14f8kx2-001 {
  width: 100%;
  max-width: 545px;
  padding: 15px;
  border-radius: 12px;
  background-color: #f4f5f6;
  font-size: 10px;
}

.css-wqf8ry-001 {
  font-size: 12px;
  font-weight: 700;
  line-height: 1.5;
  text-align: left;
  color: #1c1d1e;
}

.css-emxp17-001 {
  font-size: 12px;
  line-height: 1.3;
  text-align: left;
  color: #838689;
  cursor: pointer;
}

.css-dyzp2y-002 {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  margin-left: 200px; /* 수정된 부분 */
}

input {
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

.css-emxp16-001 {
  line-height: 1.3;
  text-align: left;
  color: #9da7ae;
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
  gap: 16px;
  width: 100%;
  display: flex;
  align-items: start;
  justify-content: center;
  font-family: Pretendard;
}

.css-f7no94-reply {
  display: flex;
  flex-direction: row;
  width: 92%;
  gap: 16px;
  font-family: Pretendard;
  margin-top: 11px;
  margin-left: 42px;
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
  font-family: Pretendard;
  width: 500px;
  max-width: 545px;
  padding: 15px;
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
  font-family: Pretendard;
  font-size: 14px;
  width: 100%;
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

