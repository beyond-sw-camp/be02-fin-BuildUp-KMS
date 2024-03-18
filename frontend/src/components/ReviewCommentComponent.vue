<template>
  <div v-for="reviewComment in reviewCommentStore.reviewCommentList" :key="reviewComment.idx">
    <div class="css-f7no94">
      <div class="css-3o2y5e">
        <div width="36px" height="36px" class="css-jg5tbe">
          <img alt="나의얼굴" width="34px" height="34px" :src="reviewComment.userImg" />
        </div>
      </div>
      <div class="css-14f8kx2">
        <div class="css-1psklmw">
          <div class="css-dyzp2y">
            <div class="css-wqf8ry">{{ reviewComment.userNickName }}</div>
            <div class="css-emxp16"></div>
            <div class="css-emxp16">{{ reviewComment.createAt }}</div>
          </div>
          <div class="css-dyzp2y-001" v-if="showBtn(reviewComment.userIdx)">
            <div class="css-emxp17" @click="toggleEditMode(reviewComment)">수정</div>
            <div class="css-emxp17" @click="deleteComment(reviewComment.idx)">삭제</div>
          </div>
          <div class="css-emxp17">
            <!-- 댓글 추천 -->
            <img width="18" height="18"
              src="https://img.icons8.com/sf-regular/48/facebook-like.png" alt="facebook-like"
              @click="reviewRecommend(reviewComment.idx)" />
            <img  width="18" height="18" src="https://img.icons8.com/sf-regular-filled/48/facebook-like.png"
              alt="facebook-like" @click="cancelReviewComment(reviewComment.idx)" />
          </div>
        </div>
        <div class="editedCommentContent">
          <input class="css-comment" type="text" v-model="updateReviewComment"
            :placeholder="reviewComment.reviewCommnetContent" :readonly="!reviewComment.editMode">
        </div>
        <div clas="css-btn">
          <p class="css-reply" @click="toggleReplyForm(reviewComment)"
            v-show="!reviewComment.editMode && !reviewComment.showReplyForm">대댓글쓰기</p>
          <p class="css-reply" @click="toggleReplyForm(reviewComment)"
            v-show="!reviewComment.editMode && reviewComment.showReplyForm">닫기</p>
          <button class="css-update" v-if="reviewComment.editMode" @click="saveComment(reviewComment.idx)">저장</button>
        </div>
      </div>
    </div>

    <!-- 대댓글 쓰기 -->
    <div>
      <div class="css-f7no94-reply" v-if="reviewComment.showReplyForm">
        <div class="css-3o2y5e">
          <div width="36px" height="36px" class="css-jg5tbe">
            <img alt="나의얼굴" width="34px" height="34px"
            :src="reviewStore.review.profileImage">
          </div>
        </div>
        <div class="css-14f8kx2">
          <div class="editedCommentContent">
            <input class="css-comment" type="text" placeholder="댓글을 남겨주세요" v-model="reviewReply">
          </div>
          <div clas="css-btn">
            <button class="css-update" @click="saveReply(reviewComment.idx)">저장</button>
          </div>
        </div>
      </div>
    </div>


    <!-- 대댓글 출력 -->
    <div v-if="reviewComment.children && reviewComment.children.length > 0">
      <div class="css-f7no94-reply" v-for="childComment in reviewComment.children" :key="childComment.idx">
        <div class="css-3o2y5e">
          <div width="36px" height="36px" class="css-jg5tbe">
            <img alt="나의얼굴" width="34px" height="34px"
            :src="childComment.userImg">
          </div>
        </div>
        <div class="css-14f8kx2-001">
          <div class="css-1psklmw">
            <div class="css-dyzp2y">
              <div class="css-wqf8ry-001">{{ childComment.userNickName }}</div>
              <div class="css-emxp16-001"></div>
              <div class="css-emxp16-001">2024-03-16 17:04:14</div>
            </div>
            <div class="css-dyzp2y-002" v-if="showBtn(childComment.userIdx)">
              <div class="css-emxp17-001" @click="toggleEditMode(childComment)">수정</div>
              <div class="css-emxp17-001" @click="deleteComment(childComment.idx)">삭제</div>
            </div>

            <!-- 대댓글 추천 -->
            <div class="css-emxp17">
              <img width="18" height="18"
              src="https://img.icons8.com/sf-regular/48/facebook-like.png" alt="facebook-like"
              @click="reviewRecommend(childComment.idx)" />
            <img  width="18" height="18" src="https://img.icons8.com/sf-regular-filled/48/facebook-like.png"
              alt="facebook-like" @click="cancelReviewComment(childComment.idx)" />
            </div>
          </div>
          <div class="editedCommentContent">
            <input class="css-comment" type="text" v-model="updateReviewComment"
              :placeholder="childComment.reviewCommnetContent" :readonly="!reviewComment.editMode">
          </div>
          <div clas="css-btn">
            <button class="css-update" v-if="reviewComment.editMode" @click="saveComment(childComment.idx)">저장</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewCommentStore } from '@/stores/useReviewCommentStore';
import { useReviewStore } from "@/stores/useReviewStore";
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "ReviewCommentComponent",
  computed: {
    ...mapStores(useReviewCommentStore, useReviewStore),
  },
  data() {
    return {
      isClicked: false
    }
  },
  props: {
    reviewCommentStore: {
      type: Object,
      required: true
    },
  },

  methods: {
    //  댓글 수정 후 저장
    async saveComment(reviewCommentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.updateReviewComment(this.updateReviewComment, reviewCommentIdx, reviewIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 작성 실패:', error);
      }
    },

    // 댓글 삭제 
    async deleteComment(commentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.deleteReviewComment(commentIdx, reviewIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 삭제 실패:', error);
      }
    },

    //  대댓글 작성
    async saveReply(reviewCommentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.createReviewReply(this.reviewReply, reviewIdx, reviewCommentIdx);
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error('댓글 작성 실패:', error);
      }
    },

    // 댓글 추천 기능
    async reviewRecommend(reviewCommentIdx) {
      try {
          await this.reviewCommentStore.reviewRecommend(reviewCommentIdx);
        } 
       catch (error) {
        console.error("ERROR : ", error);
      }
    },

    async cancelReviewComment(reviewCommentIdx){
      try{
        await this.reviewCommentStore.cancelReviewComment(reviewCommentIdx);
      }       catch (error) {
        console.error("ERROR : ", error);
      }
    },

    // 버튼 관련
    showBtn(commentUserIdx) {
      let token = localStorage.getItem("token");
      if (!token) return false; // 토큰이 없으면 버튼을 보이지 않음
      let decodedToken = VueJwtDecode.decode(token).idx;
      return commentUserIdx === decodedToken;
    },


    toggleEditMode(reviewComment) {
      reviewComment.editMode = !reviewComment.editMode;
      if (reviewComment.editMode) {
        reviewComment.updateComment = reviewComment.reviewCommnetContent;
      }
    },

    toggleReplyForm(reviewComment) {
      if (reviewComment.showReplyForm) {
        reviewComment.showReplyForm = false; // 닫기 버튼을 클릭하여 대댓글 입력 폼을 닫음
      } else {
        reviewComment.showReplyForm = true; // 대댓글쓰기 버튼을 클릭하여 대댓글 입력 폼을 엶
      }
    },

  },

};
</script>

<style scoped>
.css-14f8kx2-001 {
  width: 100%;
  max-width: 545px;
  padding: 20px;
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
  margin-left: 233px;
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
  display: flex;
  flex-direction: row;
  width: 90%;
  gap: 16px;
  font-family: Pretendard;
}

.css-f7no94-reply {
  display: flex;
  flex-direction: row;
  width: 95%;
  gap: 16px;
  font-family: Pretendard;
  margin-top: 11px;
  margin-left: 29px;
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