<template>
  <div v-for="reviewComment in reviewCommentStore.reviewCommentList" :key="reviewComment.idx">
    <div class="css-f7no94">
      <div class="css-3o2y5e">
        <div width="36px" height="36px" class="css-jg5tbe">
          <img alt="나의얼굴" width="34px" height="34px" :src="reviewStore.review.profileImage" />
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
            <img v-if="!reviewComment.isClicked" width="18" height="18"
              src="https://img.icons8.com/sf-regular/48/facebook-like.png" alt="facebook-like"
              @click="handleRecommendationClick(reviewComment)" />
            <img v-else width="18" height="18" src="https://img.icons8.com/sf-regular-filled/48/facebook-like.png"
              alt="facebook-like" @click="handleRecommendationClick(reviewComment)" />
            <div>{{ reviewComment.upCnt }}</div>
          </div>
        </div>
        <div class="editedCommentContent">
          <input class="css-comment" type="text" v-model="updateReviewComment"
            :placeholder="reviewComment.reviewCommnetContent" :readonly="!reviewComment.editMode">
        </div>
        <div clas="css-btn">
          <p class="css-reply" v-if="!reviewComment.editMode">대댓글쓰기</p>
          <button class="css-update" v-if="reviewComment.editMode" @click="saveComment(reviewComment.idx)">저장</button>
        </div>
      </div>
    </div>

    <!-- 대댓글 -->
    <div v-if="reviewComment.children && reviewComment.children.length > 0">
      <div class="css-f7no94-reply" v-for="childComment in reviewComment.children" :key="childComment.idx">
        <div class="css-3o2y5e">
          <div width="36px" height="36px" class="css-jg5tbe">
            <img alt="나의얼굴" width="34px" height="34px"
              src="data:image/png;base64,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" />
          </div>
        </div>
        <div class="css-14f8kx2">
          <div class="css-1psklmw">
            <div class="css-dyzp2y">
              <div class="css-wqf8ry">{{ childComment.userNickName }}</div>
              <div class="css-emxp16"></div>
              <div class="css-emxp16">2024-03-16 17:04:14</div>
            </div>
            <div class="css-dyzp2y-001" v-if="showBtn(childComment.userIdx)">
              <div class="css-emxp17" @click="toggleEditMode(childComment)">수정</div>
              <div class="css-emxp17" @click="deleteComment(childComment.idx)">삭제</div>
            </div>
            <!-- 댓글 추천 -->
            <div class="css-emxp17">
              <img width="18" height="18" src="https://img.icons8.com/sf-regular/48/facebook-like.png"
                alt="facebook-like" />
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

    // 댓글 추천 기능
    async handleRecommendationClick(reviewComment) {
      try {
        if (!reviewComment.isClicked) {
          await this.reviewCommentStore.reviewRecommend(reviewComment.idx);
        } else {
          await this.reviewCommentStore.cancelReviewComment(reviewComment.idx);
        }
        // 서버 요청이 성공했을 때만 상태 변경
        reviewComment.isClicked = !reviewComment.isClicked; // 클릭된 상태를 토글합니다.
      } catch (error) {
        console.error("ERROR : ", error);
        // 서버 요청이 실패한 경우 상태 변경 없음
      }
    },

    async checkReviewCommentUp() {
      try {
        let response = await this.reviewStore.checkReviewCommentUp();
        console.log(response);

        if (response.data && response.data.result.status === true) {
          this.isRecommended = true;
          this.reviewUpIdx = response.data.result.reviewUpIdx;
        } else {
          this.isRecommended = false;
        }
      } catch (e) {
        console.error(e);
      }
    },

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

  }

};
</script>

<style scoped>
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

.css-f7no94-reply {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 16px;
  font-family: Pretendard;
  margin-top: 10px;
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