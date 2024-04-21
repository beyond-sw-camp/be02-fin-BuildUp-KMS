<template>
  <div v-for="reviewComment in filteredReviewComments" :key="reviewComment.idx">
    <div class="css-f7no94" v-if="reviewComment.status">
      <div class="css-3o2y5e">
        <div width="36px" height="36px" class="css-jg5tbe">
          <img
            alt="나의얼굴"
            width="34px"
            height="34px"
            :src="reviewComment.userImg"
          />
        </div>
      </div>
      <div class="css-14f8kx2">
        <div class="css-1psklmw">
          <div class="css-dyzp2y">
            <div class="css-wqf8ry">{{ reviewComment.userNickName }}</div>
            <div class="css-emxp16"></div>
            <div class="css-emxp16">
              {{
                this.$moment
                  .utc(reviewComment.createAt)
                  .local()
                  .format("YYYY-MM-DD HH:mm:ss")
              }}
            </div>
          </div>
          <div class="css-dyzp2y-001" v-if="showBtn(reviewComment.userIdx)">
            <div
              class="css-emxp17"
              v-if="reviewComment.isEditing"
              @click="cancelUpdateComment(reviewComment)"
            >
              취소
            </div>
            <div
              class="css-emxp17"
              v-if="!reviewComment.isEditing"
              @click="toggleCommentEditMode(reviewComment)"
            >
              수정
            </div>
            <div
              class="css-emxp17"
              @click="deleteComment(reviewComment.idx, reviewComment.userIdx)"
            >
              삭제
            </div>
          </div>
          <!-- 댓글 추천 -->
          <!-- <div class="css-emxp17">
            <img width="18" height="18" src="https://img.icons8.com/sf-regular/48/facebook-like.png" alt="facebook-like"
              @click="reviewRecommend(reviewComment.idx)" />
            <img width="18" height="18" src="https://img.icons8.com/sf-regular-filled/48/facebook-like.png"
              alt="facebook-like" @click="cancelReviewComment(reviewComment.idx)" />
          </div> -->
          <!-- /댓글 추천 -->
        </div>
        <div v-if="!reviewComment.isEditing">
          <div
            v-dompurify-html="reviewComment.reviewCommnetContent"
            class="editedCommentContent"
          ></div>
        </div>
        <div v-if="reviewComment.isEditing">
          <quill-editor
            v-model:value="state.updateCommentContent"
            ref="quillUpdateEditor"
          >
          </quill-editor>
        </div>
        <div clas="css-btn">
          <p
            class="css-reply"
            @click="toggleReplyForm(reviewComment)"
            v-if="!reviewComment.isEditing"
          >
            대댓글 작성하기
          </p>
          <!-- <p
            class="css-reply"
            @click="toggleReplyForm(reviewComment)"
            v-if="!reviewComment.editMode && reviewComment.showReplyForm"
          >
            닫기
          </p> -->
          <button
            class="css-update"
            v-if="reviewComment.isEditing"
            @click="saveComment(reviewComment.idx)"
          >
            저장
          </button>
        </div>
      </div>
    </div>

    <!-- 대댓글 쓰기 -->
    <div>
      <div class="css-f7no94-reply" v-if="reviewComment.showReplyForm">
        <div class="css-3o2y5e">
          <div width="36px" height="36px" class="css-jg5tbe">
            <img
              alt="나의얼굴"
              width="34px"
              height="34px"
              :src="userStore.user.profileImage"
            />
          </div>
        </div>
        <div class="css-14f8kx2-0318">
          <div class="editedCommentContent">
            <div class="quill">
              <quill-editor
                ref="quillEditor"
                v-model:value="state.content"
                :options="state.editorOption"
                :disabled="false"
                @change="onEditorChange($event)"
              ></quill-editor>
            </div>
            <!-- <input
              class="css-comment-0318"
              type="text"
              placeholder="댓글을 남겨주세요"
              v-model="reviewReply"
            /> -->
          </div>
          <div clas="css-btn">
            <button class="css-update" @click="saveReply(reviewComment.idx)">
              저장
            </button>
            <button
              class="css-update1"
              @click="toggleReplyForm(reviewComment)"
              v-if="!reviewComment.editMode && reviewComment.showReplyForm"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 대댓글 출력 -->
    <div v-if="reviewComment.children && reviewComment.children.length > 0">
      <div
        v-for="childComment in reviewComment.children"
        :key="childComment.idx"
      >
        <div class="css-f7no94-reply" v-if="childComment.status">
          <div class="css-3o2y5e">
            <div width="36px" height="36px" class="css-jg5tbe">
              <img
                alt="나의얼굴"
                width="34px"
                height="34px"
                :src="childComment.userImg"
              />
            </div>
          </div>
          <div class="css-14f8kx2-001">
            <div class="css-1psklmw">
              <div class="css-dyzp2y">
                <div class="css-wqf8ry-001">
                  {{ childComment.userNickName }}
                </div>
                <div class="css-emxp16-001"></div>
                <div class="css-emxp16-001">
                  {{
                    this.$moment
                      .utc(childComment.createAt)
                      .local()
                      .format("YYYY-MM-DD HH:mm:ss")
                  }}
                </div>
              </div>
              <div class="css-dyzp2y-002" v-if="showBtn(childComment.userIdx)">
                <div
                  v-if="childComment.isEditing"
                  class="css-emxp17-001"
                  @click="cancelUpdateReply(childComment)"
                >
                  취소
                </div>
                <div
                  v-if="!childComment.isEditing"
                  class="css-emxp17-001"
                  @click="toggleReplyEditMode(childComment)"
                >
                  수정
                </div>
                <div
                  class="css-emxp17-001"
                  @click="deleteComment(childComment.idx, childComment.userIdx)"
                >
                  삭제
                </div>
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
            <div v-if="!childComment.isEditing">
              <div
                v-dompurify-html="childComment.reviewCommnetContent"
                class="editedCommentContent"
              ></div>
            </div>
            <div v-if="childComment.isEditing">
              <quill-editor
                v-model:value="state.updateReplyContent"
                ref="quillUpdateEditor"
              >
              </quill-editor>
            </div>
            <div clas="css-btn">
              <button
                class="css-update"
                v-if="childComment.isEditing"
                @click="saveReplyComment(childComment.idx)"
              >
                저장
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewCommentStore } from "@/stores/useReviewCommentStore";
import { useReviewStore } from "@/stores/useReviewStore";
import { useUserStore } from "../stores/useUserStore";
import VueJwtDecode from "vue-jwt-decode";
import hljs from "highlight.js";
import "highlight.js/styles/monokai-sublime.css";

hljs.configure({
  languages: [
    "javascript",
    "java",
    "python",
    "html",
    "css",
    "c",
    "cpp",
    "csharp",
    "ruby",
    "php",
    "typescript",
    "kotlin",
    "bash",
  ],
});

export default {
  name: "ReviewCommentComponent",
  computed: {
    ...mapStores(useReviewCommentStore, useReviewStore, useUserStore),
    filteredReviewComments() {
      return this.reviewCommentStore.reviewCommentList.filter(
        (comment) => comment.status
      );
    },
  },
  data() {
    return {
      state: {
        updateCommentContent: "",
        updateReplyContent: "",
        content: "",
        _content: "",
        editorOption: {
          placeholder: "댓글을 남겨주세요",
          modules: {
            syntax: {
              highlight: (text) => hljs.highlightAuto(text).value,
            },
            toolbar: {
              container: [
                [
                  "bold",
                  "underline",
                  "code-block",
                  { header: 1 },
                  { header: 2 },
                  { color: [] },
                  { background: [] },
                ],
              ],
            },
          },
        },
        disabled: false,
      },
      isClicked: false,
    };
  },
  props: {
    reviewCommentStore: {
      type: Object,
      required: true,
    },
  },
  async mounted() {
    await this.reviewCommentStore.updateCommentRecommendationStatus();
  },
  methods: {
    cancelUpdateComment(reviewComment) {
      reviewComment.isEditing = !reviewComment.isEditing;
    },
    cancelUpdateReply(childComment) {
      childComment.isEditing = !childComment.isEditing;
    },
    onEditorChange({ html }) {
      this.state.content = html;
    },
    //  댓글 수정 후 저장
    async saveComment(reviewCommentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.updateReviewComment(
          this.state.updateCommentContent,
          reviewCommentIdx,
          reviewIdx
        );
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error("댓글 작성 실패:", error);
      }
    },

    async saveReplyComment(reviewCommentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.updateReviewComment(
          this.state.updateReplyContent,
          reviewCommentIdx,
          reviewIdx
        );
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error("댓글 작성 실패:", error);
      }
    },

    // 댓글 삭제
    async deleteComment(commentIdx, userIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.deleteReviewComment(
          commentIdx,
          reviewIdx,
          userIdx
        );
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error("댓글 삭제 실패:", error);
      }
    },

    //  대댓글 작성
    async saveReply(reviewCommentIdx) {
      try {
        const reviewIdx = this.reviewStore.review.reviewIdx;
        await this.reviewCommentStore.createReviewReply(
          this.state.content,
          reviewIdx,
          reviewCommentIdx
        );
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error("댓글 작성 실패:", error);
      }
    },

    // // 댓글 추천 기능
    // async reviewRecommend(reviewCommentIdx) {
    //   try {
    //     await this.reviewCommentStore.reviewRecommend(reviewCommentIdx);
    //   }
    //   catch (error) {
    //     console.error("ERROR : ", error);
    //   }
    // },

    // async cancelReviewComment(reviewCommentIdx) {
    //   try {
    //     await this.reviewCommentStore.cancelReviewComment(reviewCommentIdx);
    //   } catch (error) {
    //     console.error("ERROR : ", error);
    //   }
    // },

    // 버튼 관련
    showBtn(commentUserIdx) {
      let accessToken = localStorage.getItem("accessToken");
      if (!accessToken) return false; // 토큰이 없으면 버튼을 보이지 않음
      let decodedToken = VueJwtDecode.decode(accessToken).idx;
      return commentUserIdx === decodedToken;
    },

    toggleCommentEditMode(reviewComment) {
      this.filteredReviewComments.forEach((c) => {
        if (c.idx !== reviewComment.idx) {
          c.isEditing = false;
        }
      });

      reviewComment.isEditing = !reviewComment.isEditing;
      if (reviewComment.isEditing) {
        this.state.updateCommentContent = reviewComment.reviewCommnetContent;
      }
    },

    toggleReplyEditMode(childComment) {
      this.filteredReviewComments.forEach((c) => {
        if (c.idx !== childComment.idx) {
          c.isEditing = false;
        }
      });
      childComment.isEditing = !childComment.isEditing;
      if (childComment.isEditing) {
        this.state.updateReplyContent = childComment.reviewCommnetContent;
      }
    },

    // toggleEditMode(reviewComment) {
    //   reviewComment.editMode = !reviewComment.editMode;
    //   if (reviewComment.editMode) {
    //     reviewComment.updateComment = reviewComment.reviewCommnetContent;
    //   }
    // },

    toggleReplyForm(reviewComment) {
      let accessToken = window.localStorage.getItem("accessToken");

      if (!accessToken) {
        alert("로그인 후 대댓글을 작성할 수 있습니다.");
        return;
      }

      if (reviewComment.showReplyForm) {
        reviewComment.showReplyForm = false; // 닫기 버튼을 클릭하여 대댓글 입력 폼을 닫음
        this.state.content = "";
      } else {
        reviewComment.showReplyForm = true; // 대댓글쓰기 버튼을 클릭하여 대댓글 입력 폼을 엶
      }
    },

    async handleRecommendClick(idx) {
      if (!idx) {
        console.error("No idx provided for handleRecommendClick");
        return;
      }

      const commentOrReply = this.findCommentOrReply(idx);
      if (!commentOrReply) {
        console.error(`Comment or reply with idx ${idx} not found.`);
        return;
      }

      try {
        // Check if the comment or reply is already recommended
        if (commentOrReply.isReviewCommentRecommended) {
          // If yes, attempt to cancel the recommendation
          await this.reviewCommentStore.cancelReviewComment(idx);
        } else {
          // If not, attempt to add a recommendation
          await this.reviewCommentStore.reviewRecommend(idx);
        }
        // Update the local isReviewCommentRecommended status
        commentOrReply.isReviewCommentRecommended =
          !commentOrReply.isReviewCommentRecommended;
      } catch (error) {
        console.error(
          `Error handling recommendation click for idx ${idx}:`,
          error
        );
        // Optionally, refresh the recommendation status from the server
        // This can be helpful if the error occurred due to out-of-sync client-server states
        await this.reviewCommentStore.updateCommentRecommendationStatus();
      }
    },

    findCommentOrReply(idx) {
      // Find comment or reply by idx
      for (const comment of this.reviewCommentStore.reviewCommentList) {
        if (comment.idx === idx) {
          return comment;
        }
        const foundReply = comment.children?.find((reply) => reply.idx === idx);
        if (foundReply) {
          return foundReply;
        }
      }
      return null; // Item not found
    },
  },
};
</script>

<style scoped>
.css-14f8kx2-0318 {
  width: 100%;
  max-width: 460px;
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
  max-width: 450px;
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
  margin-left: 180px; /* 수정된 부분 */
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
  margin-bottom: 10px;
}

.css-update1 {
  background-color: rgb(219 52 52 / 20%);
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
  margin-bottom: 10px;
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
  margin-left: 200px;
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

::v-deep .editedCommentContent p {
  font-size: 12px;
  color: #505254;
  line-height: 1.8;
  word-break: break-all;
  font-weight: 400;
  margin-top: 2px;
  margin-bottom: 2px;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
}

/* editor */
.quill {
  min-height: 150px;
}

@media (min-width: 1024px) {
  .ql-editor,
  .quill {
    min-height: 150px;
  }
}

.editedCommentContent .quill {
  min-height: 57px;
}

.ql-snow,
.ql-snow * {
  box-sizing: border-box;
}

.ql-toolbar {
  position: relative;
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
  border: none !important;
  background-color: #f4f5f6;
  box-sizing: border-box;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  padding: 12px 15px !important;
  height: 52px;
}

@media (min-width: 1024px) {
  .ql-toolbar {
    position: relative;
    border: none !important;
    display: block;
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
    background-color: #f4f5f6;
    box-sizing: border-box;
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    padding: 13px 15px !important;
    height: 56px;
    border-radius: 10px;
  }
}

.ql-toolbar {
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-family: "Helvetica Neue", "Helvetica", "Arial", sans-serif;
  padding: 8px;
  border-radius: 10px;
}

::v-deep .ql-toolbar.ql-snow {
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  padding: 0px;
  border-radius: 10px;
}

.ql-snow,
.ql-snow * {
  box-sizing: border-box;
}

@media (min-width: 1024px) {
  .ql-container {
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
    min-height: 60px;
  }
}

.ql-container {
  min-height: 60px;
}
.ql-container {
  border: 1px solid #ccc;
}
.ql-container.ql-snow {
  border: 1px solid #ccc;
}
.editedCommentContent .ql-container {
  min-height: 56px;
}
.ql-container.ql-snow {
  border: none !important;
}
.ql-editor {
  height: 100%;
  overflow-y: auto;
  padding: 12px 15px;
}
.ql-snow,
.ql-snow * {
  box-sizing: border-box;
}
@media (min-width: 1024px) {
  .ql-editor,
  .quill {
    min-height: 280px;
  }
}
@media (min-width: 1024px) {
  .ql-editor {
    color: #6b6e72;
    font-stretch: normal;
    font-style: normal;
    line-height: 1.3;
    letter-spacing: normal;
    font-weight: 500;
    width: 100%;
    margin: auto;
    padding: 24px 20px !important;
  }
}
.ql-editor {
  color: #6b6e72;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.3;
  letter-spacing: normal;
  font-weight: 500;
  min-height: 200px;
  width: 100%;
  margin: auto;
  padding: 24px 20px !important;
}
.ql-editor {
  box-sizing: border-box;
  line-height: 1.42;
  outline: none;
  padding: 12px 0;
  tab-size: 4;
  -moz-tab-size: 4;
  text-align: left;
  white-space: pre-wrap;
  word-wrap: break-word;
}
.editedCommentContent .ql-editor {
  min-height: 56px;
  padding: 14px 20px !important;
}
.ql-editor.ql-blank::before {
  color: rgba(0, 0, 0, 0.6);
  content: attr(data-placeholder);
  font-style: italic;
  left: 15px;
  pointer-events: none;
  position: absolute;
  right: 15px;
}
.ql-editor.ql-blank:before {
  left: 15px;
}
.ql-editor.ql-blank:before {
  color: rgba(0, 0, 0, 0.6);
  content: attr(data-placeholder);
  font-style: italic;
  left: 20px;
  pointer-events: none;
  position: absolute;
  right: 15px;
  font-weight: 350;
}
.ql-editor.ql-blank::before {
  color: rgba(0, 0, 0, 0.6);
  content: attr(data-placeholder);
  font-style: italic;
  left: 15px;
  pointer-events: none;
  position: absolute;
  right: 15px;
}
.quill > .ql-container > .ql-editor.ql-blank:before {
  color: #c7c9cb;
  font-style: normal;
  font-size: 14px;
  white-space: pre-wrap;
  line-height: 1.5;
  padding: 5px;
}
.ql-editor * {
  font-stretch: normal;
  font-style: normal;
  letter-spacing: normal;
  font-family: Pretendard;
}
.ql-editor blockquote,
.ql-editor h1,
.ql-editor h2,
.ql-editor h3,
.ql-editor h4,
.ql-editor h5,
.ql-editor h6,
.ql-editor ol,
.ql-editor p,
.ql-editor pre,
.ql-editor ul {
  margin: 0;
  padding: 0;
  counter-reset: list-1 list-2 list-3 list-4 list-5 list-6 list-7 list-8 list-9;
}
.ql-editor *,
.ql-editor p {
  font-weight: 400;
  line-height: 1.5;
}
.ql-editor p {
  color: #505254;
  margin-top: 2px;
  margin-bottom: 2px;
  font-size: 14px;
  word-break: break-word;
  width: 100%;
  overflow-x: clip;
}
.ql-editor > * {
  cursor: text;
}

::v-deep .ql-snow.ql-toolbar button,
.ql-snow .ql-toolbar button {
  background: none;
  border: none;
  cursor: pointer;
  display: inline-block;
  float: left;
  height: 24px;
  padding: 3px 5px;
  width: 28px;
  margin-right: 10px;
}

::v-deep .ql-snow .ql-picker {
  color: #444;
  display: inline-block;
  float: left;
  font-size: 14px;
  font-weight: 500;
  height: 24px;
  position: relative;
  vertical-align: middle;
  margin-right: 10px;
}

::v-deep .editedCommentContent pre.ql-syntax {
  background-color: #23241f;
  color: #f8f8f2;
  overflow: visible;
  font-family: Monaco;
  letter-spacing: 0.07em;
  font-size: 10px;
  white-space: pre-wrap;
  word-break: break-all;
}

::v-deep .ql-container {
  box-sizing: border-box;
  font-family: Helvetica, Arial, sans-serif;
  font-size: 12px;
  height: 100%;
  margin: 0px;
  position: relative;
}

::v-deep .ql-snow .ql-editor pre.ql-syntax {
  background-color: #23241f;
  color: #f8f8f2;
  overflow: visible;
  font-family: Monaco;
  letter-spacing: 0.07em;
  font-size: 10px;
  word-break: break-all;
}
</style>
