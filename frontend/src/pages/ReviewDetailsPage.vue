<template>
  <!-- 내 코드 -->
  <div class="css-1hnxdb7">
    <div class="css-130kwtj">
      <div class="css-1hwcs2h"></div>
    </div>
    <div height="120px" class="css-jbj5u0"></div>
    <div class="css-1g39gls">
      <div class="css-1qjp6uf">
        <div class="css-axl4y">
          <div class="css-1yuvfju">
            <div class="css-k59gj9">
              <div class="css-hmpurq">
                <div class="css-category">
                  {{ reviewStore.review.reviewCategoryName }}
                </div>
                <div class="css-kem115">
                  <div class="css-12i5occ">
                    <div class="css-1jibmi3">
                      <div class="css-n7izk0">
                        {{ reviewStore.review.reviewTitle }}
                      </div>
                    </div>
                    <div class="css-sebsp7"></div>
                  </div>
                  <div class="css-bt1qy">
                    <div class="css-1hqtm5a">
                      <div @click="createReviewUp()">
                        <img
                          width="23px"
                          height="23px"
                          :src="
                            isRecommended
                              ? require('../assets/img/up_ok.png')
                              : require('../assets/img/up.png')
                          "
                          alt="facebook-like"
                        />
                        <p style="font-size: 10px; text-align: center">
                          {{ reviewStore.review.upCnt }}
                        </p>
                      </div>
                    </div>
                    <div class="css-1hqtm5a">
                      <div @click="createReviewScrap()">
                        <img
                          width="23px"
                          height="23px"
                          :src="
                            isScrapped
                              ? require('../assets/img/scrap_ok.png')
                              : require('../assets/img/scrap.png')
                          "
                          alt="bookmark-ribbon--v1"
                        />
                        <p
                          class="css-scrap"
                          style="font-size: 10px; text-align: center"
                        >
                          {{ reviewStore.review.scrapCnt }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  v-show="isActive"
                  class="layer pop_product pop_shopping_bag"
                >
                  <div
                    class="layer_cont"
                    style="margin: -140.5px 0px 0px -225px"
                  >
                    <p>마이페이지로 이동하시겠습니까?</p>
                    <div class="btn_wrap">
                      <button
                        type="button"
                        class="btn gray small"
                        @click="dontMoveMyPage"
                      >
                        머무르기
                      </button>
                      <button
                        type="button"
                        class="btn black small"
                        @click="moveMyPage"
                      >
                        이동하기
                      </button>
                    </div>
                  </div>
                  <span></span>
                </div>
                <div class="css-z2xt5y">
                  <div class="css-5zcuovs">
                    <div class="css-1sika4is">
                      {{ reviewStore.review.courseName }}
                    </div>
                  </div>
                  <div class="css-5zcuovss">
                    <div class="css-1sika4iss">
                      평점 : {{ reviewStore.review.courseEvaluation }} 점
                    </div>
                  </div>
                </div>
                <div class="css-99cwur">
                  <div class="css-1fhge30">
                    <div class="css-aw18wm">
                      <span
                        style="
                          box-sizing: border-box;
                          display: block;
                          overflow: hidden;
                          width: initial;
                          height: initial;
                          background: none;
                          opacity: 1;
                          border: 0px;
                          margin: 0px;
                          padding: 0px;
                          position: absolute;
                          inset: 0px;
                        "
                        ><img
                          sizes="100vw"
                          :src="reviewStore.review.profileImage"
                          decoding="async"
                          data-nimg="fill"
                          style="
                            position: absolute;
                            inset: 0px;
                            box-sizing: border-box;
                            padding: 0px;
                            border: none;
                            margin: auto;
                            display: block;
                            width: 0px;
                            height: 0px;
                            min-width: 100%;
                            max-width: 100%;
                            min-height: 100%;
                            max-height: 100%;
                          "
                      /></span>
                    </div>
                    <div class="css-5zcuov">
                      <div class="css-1sika4i">
                        {{ reviewStore.review.userNickName }}
                      </div>
                      <div class="css-1tify6w">
                        <svg
                          width="2"
                          height="2"
                          viewBox="0 0 2 2"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <circle cx="1" cy="1" r="1" fill="#9DA7AE"></circle>
                        </svg>
                      </div>
                      <div class="css-1ry6usa">
                        {{
                          this.$moment
                            .utc(reviewStore.review.createdAt)
                            .local()
                            .format("YYYY-MM-DD HH:mm:ss")
                        }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="css-luqgif">
              <div
                v-dompurify-html="reviewStore.review.reviewContent"
                class="editedQ_QContent"
              ></div>
              <!-- <div class="editedQ_QContent">
                <p class="css-content">
                  {{ reviewStore.review.reviewContent }}
                </p>
                <div
                  v-if="
                    reviewStore.review.reviewImageList &&
                    reviewStore.review.reviewImageList.length > 0
                  "
                >
                  <img
                    v-for="(image, index) in reviewStore.review.reviewImageList"
                    :key="image.reviewImageIdx"
                    :alt="`이미지 ${index + 1}`"
                    :src="image.reviewImage"
                  />
                </div>
              </div> -->
              <div class="css-iqys2n"></div>
            </div>
            <div class="css-1k90lkz">
              <div class="css-lua631">
                <div class="css-13ku1qm">
                  <div
                    style="
                      display: inline-block;
                      width: 24px;
                      height: 24px;
                      text-align: center;
                    "
                  >
                    <img
                      width="16px"
                      height="16px"
                      src="https://img.icons8.com/tiny-glyph/32/000000/comments.png"
                      alt="comments"
                    />
                  </div>
                  댓글 {{ reviewStore.review.commentCnt }}
                </div>
              </div>
              <div class="css-qzobjv">
                <!-- 댓글 컴포넌트 -->
                <ReviewCommentComponent
                  :reviewCommentStore="reviewCommentStore"
                ></ReviewCommentComponent>
                <div class="css-jpe6jj">
                  <div class="css-3o2y5e">
                    <div width="36px" height="36px" class="css-jg5tbe">
                      <img
                        alt="프로필 이미지"
                        width="34px"
                        height="34px"
                        :src="userProfileImage"
                      />
                    </div>
                  </div>
                  <div class="css-13ljjbe">
                    <div class="commentEditor">
                      <div class="quill">
                        <quill-editor
                          ref="quillEditor"
                          v-model:value="state.content"
                          :options="state.editorOption"
                          :disabled="false"
                        ></quill-editor>
                      </div>
                      <!-- <input
                        class="css-001"
                        type="text"
                        placeholder="댓글을 남겨주세요"
                        v-model="reviewCommentContent"
                      /> -->
                    </div>
                    <div class="css-btn-div">
                      <button class="css-btn" @click="submitComment()">
                        저장
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 목록으로 돌아가기 버튼 -->
              <div class="css-back-div">
                <router-link to="/review">
                  <button class="css-board-back">목록가기</button>
                </router-link>
              </div>
              <!-- 돌아가기 버튼 끝 -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "../stores/useReviewStore";
import { useUserStore } from "../stores/useUserStore";
import { useReviewCommentStore } from "../stores/useReviewCommentStore";
import ReviewCommentComponent from "../components/ReviewCommentComponent.vue";
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
  name: "ReviewDetailsPage",
  components: {
    ReviewCommentComponent,
  },
  data() {
    return {
      state: {
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
      reviewIdx: null,
      showMyPageConfirmDialog: false,
      isRecommended: false,
      isScrapped: false,
      reviewUpIdx: null,
      reviewScrapIdx: null,
      isActive: false,
    };
  },
  computed: {
    ...mapStores(useReviewStore, useUserStore, useReviewCommentStore),
    isLoggedIn() {
      return !!localStorage.getItem("token");
    },
    userProfileImage() {
      // 사용자 정보 로딩 후 사용자 프로필 이미지 반환
      if (this.userStore.user && this.userStore.user.profileImage) {
        return this.userStore.user.profileImage;
      } else {
        // 기본 이미지 반환. 실제 경로로 변환 필요.
        return require("@/assets/img/profile.jpg");
      }
    },
  },
  created() {
    const reviewIdx = this.$route.params.idx;
    this.reviewStore.getReviewDetail(reviewIdx);
    this.reviewCommentStore.getReviewCommentList(reviewIdx);
    if (this.isLoggedIn) {
      this.userStore.getUserInfo();
    }

    this.reviewIdx = reviewIdx;
  },
  async mounted() {
    await this.checkReviewUp();
    await this.checkReviewScrap();

    this.highlightCode();
  },
  methods: {
    highlightCode() {
      this.$nextTick(() => {
        document.querySelectorAll("pre").forEach((block) => {
          hljs.highlightBlock(block);
        });
      });
    },

    async submitComment() {
      let accessToken = window.localStorage.getItem("accessToken");

      if (!accessToken) {
        alert("로그인 후 댓글을 작성할 수 있습니다.");
        return;
      }

      const reviewIdx = this.$route.params.idx;
      try {
        await this.reviewCommentStore.createReviewComment(
          this.state.content,
          reviewIdx
        );
      } catch (error) {
        console.error("댓글 작성 실패:", error);
      }
    },
    async createReviewUp() {
      let accessToken = window.localStorage.getItem("accessToken");

      if (accessToken == null) {
        alert("로그인 후 이용해주세요.");
      } else {
        let requestBody = {
          reviewIdx: this.reviewIdx,
        };

        try {
          if (this.isRecommended) {
            await this.reviewStore.cancelReviewUp(
              accessToken,
              this.reviewUpIdx
            );
            console.log("후기 추천 취소 성공");
            this.isRecommended = false;

            window.location.reload();
          } else {
            const response = await this.reviewStore.createReviewUp(
              accessToken,
              requestBody
            );

            if (response.status === 200 && response.data) {
              console.log("후기 추천 성공!");
              this.isRecommended = true;
              window.location.reload();
            } else {
              console.error("후기 추천 실패");
              alert("후기 추천 실패");
            }
          }
        } catch (e) {
          console.error("후기 추천 과정에서 문제가 발생했습니다!", e);
        }
      }
    },

    async createReviewScrap() {
      let accessToken = window.localStorage.getItem("accessToken");

      if (accessToken == null) {
        alert("로그인 후 이용해주세요.");
      } else {
        let requestBody = {
          reviewIdx: this.reviewIdx,
        };

        try {
          if (this.isScrapped) {
            await this.reviewStore.cancelReviewScrap(
              accessToken,
              this.reviewScrapIdx
            );
            console.log("후기 스크랩 취소 성공");
            this.isScrapped = false;

            window.location.reload();
          } else {
            const response = await this.reviewStore.createReviewScrap(
              accessToken,
              requestBody
            );

            if (response.status === 200 && response.data) {
              console.log("후기 스크랩 성공!");
              this.isScrapped = true;
              this.isActive = true;
            } else {
              console.error("후기 스크랩 실패");
              alert("후기 스크랩 실패");
            }
          }
        } catch (e) {
          console.error("후기 스크랩 과정에서 문제가 발생했습니다!", e);
        }
      }
    },

    moveMyPage() {
      this.isActive = false;
      this.$router.push("/mypage");
    },

    dontMoveMyPage() {
      this.isActive = false;
      window.location.reload();
    },

    async checkReviewUp() {
      try {
        let accessToken = window.localStorage.getItem("accessToken");
        if (accessToken) {
          let response = await this.reviewStore.checkReviewUp(
            accessToken,
            this.reviewIdx
          );

          if (response.data && response.data.result.status === true) {
            this.isRecommended = true;
            this.reviewUpIdx = response.data.result.reviewUpIdx;
          } else {
            this.isRecommended = false;
          }
        }
      } catch (e) {
        console.error(e);
      }
    },

    async checkReviewScrap() {
      try {
        let accessToken = window.localStorage.getItem("accessToken");
        if (accessToken) {
          let response = await this.reviewStore.checkReviewScrap(
            accessToken,
            this.reviewIdx
          );

          if (response.data && response.data.result.status === true) {
            this.isScrapped = true;
            this.reviewScrapIdx = response.data.result.reviewScrapIdx;
          } else {
            this.isScrapped = false;
          }
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style scoped>
body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
  background-color: #fff;
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

button {
  background: unset;
  border: unset;
  padding: unset;
}

a {
  text-decoration: none;
  cursor: pointer;
}

.css-1hnxdb7 {
  background-color: #fff;
}

.css-icon {
  font-size: 18px;
  color: #9da7ae;
}

.css-category {
  width: 65px;
  padding: 2px 0;
  border-radius: 40px;
  background-color: #541d70;
  color: #fff;
  font-size: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  font-family: Pretendard;
}

@media (min-width: 820px) {
  .css-1g39gls {
    display: flex;
    flex-direction: row;
    width: 100%;
    gap: 43px;
    max-width: 942px;
    margin: 0 auto;
    padding: 150px 0 120px;
  }
}

.css-1g39gls {
  background-color: #fff;
}

@media (min-width: 820px) {
  .css-1qjp6uf {
    max-width: 680px;
    border-radius: 8px;
    border: 1px solid #e4ebf0;
    background-color: #fff;
    padding: 40px;
    width: 100%;
    margin: auto;
  }
}

.css-1yuvfju {
  margin: auto;
  max-width: 942px;
  width: 100%;
}

.css-k59gj9 {
  display: flex;
  flex-direction: column;
  width: 100%;
}

@media ((min-width: 820px)) {
  .css-hmpurq {
    padding: 24px 0;
    border-bottom: 1px solid #e4ebf0;
    gap: 14px;
  }
}

.css-hmpurq {
  display: flex;
  flex-direction: column;
  width: 100%;
  background-color: #fff;
}

.css-kem115 {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
}

@media ((min-width: 820px)) {
  .css-12i5occ {
    gap: 4px;
    width: 100%;
  }
}

.css-12i5occ {
  display: flex;
  flex-direction: column;
}

.css-1jibmi3 {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

@media (min-width: 820px) {
  .css-n7izk0 {
    font-style: normal;
    line-height: 24px;
    color: #141617;
    max-width: 83%;
    font-size: 20px;
    font-weight: 700;
    font-family: Pretendard;
  }
}

.css-n7izk0 {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  word-wrap: break-word;
  word-break: break-all;
}

.css-sebsp7 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  height: 18px;
}

.css-bt1qy {
  display: flex;
  align-items: start;
  flex-direction: row;
  gap: 16px;
  height: 46px;
}

.css-1hqtm5a {
  cursor: pointer;
  width: 20px;
  height: 20px;
}

.css-99cwur {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 24px;
  margin: 0;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
  font-size: 1.4rem;
}

.css-1fhge30 {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 8px;
  margin: 0;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

.css-aw18wm {
  width: 24px;
  height: 24px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}

.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  align-items: center;
}

.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #3a3e41;
}

.css-1tify6w {
  width: 2px;
  height: 2px;
  display: flex;
}

.css-1ry6usa {
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #9da7ae;
}

@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}

.css-z2xt5y {
  flex-direction: row;
  align-items: center;
}

.css-luqgif {
  padding: 39px 20px;
  border-bottom: solid 1px #eaebed;
  background-color: #fff;
  border-top: 1px solid #e4ebf0;
}

.editedQ_QContent {
  min-height: 180px;
}

img {
  image-rendering: -moz-crisp-edges;
  image-rendering: -o-crisp-edges;
  image-rendering: -webkit-optimize-contrast;
  -ms-interpolation-mode: nearest-neighbor;
}

.editedQ_QContent * {
  font-stretch: normal;
  font-style: normal;
  font-weight: 400;
  letter-spacing: normal;
}

::v-deep .editedQ_QContent {
  font-size: 14px;
  color: #505254;
  line-height: 1.8;
  word-break: break-all;
  font-weight: 400;
  margin-top: 2px;
  margin-bottom: 2px;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
}

::v-deep .editedQ_QContent p {
  font-size: 14px;
  color: #505254;
  line-height: 1.8;
  word-break: break-all;
  font-weight: 400;
  margin-top: 2px;
  margin-bottom: 2px;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
}

::v-deep .editedQ_QContent strong {
  font-weight: 700;
}

::v-deep .editedQ_QContent img {
  margin-top: 20px;
  max-width: 300px;
}

::v-deep .editedQ_QContent li {
  margin-bottom: 4px;
  line-height: 1.3;
  font-size: 14px;
  color: #505254;
  font-weight: 400;
  list-style-position: inside;
}

::v-deep .editedQ_QContent pre.ql-syntax {
  overflow-x: auto;
  background-color: black;
  border: 1px solid #eaebed;
  padding: 14px 20px;
  border-radius: 8px;
  margin-top: 20px;
  max-width: 90vw;
  white-space: pre;
  line-height: 1.42;
  font-family: Monaco;
  letter-spacing: 0.07em;
  font-size: 12px;
  word-break: break-all;
}

::v-deep .editedQ_QContent .ql-align-center {
  text-align: center;
}
::v-deep .editedQ_QContent .ql-align-right {
  text-align: right;
}
::v-deep .editedQ_QContent .ql-align-justify {
  text-align: justify;
}

@media (min-width: 1024px) {
  .editedQ_QContent img {
    margin-top: 20px;
    max-width: 400px;
  }
}

.css-content {
  line-height: 30px;
  font-size: 14px;
  font-family: Pretendard;
}

.css-iqys2n {
  display: flex;
  align-items: center;
  justify-content: left;
  gap: 12px;
  padding-bottom: 20px;
  padding-top: 80px;
}

.css-1kc14yj {
  cursor: pointer;
  width: 100px;
  padding: 7px 0;
  border-radius: 40px;
  background-color: #eaeaea;
  font-size: 12px;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
}

.css-1k90lkz {
  padding: 39px 20px;
  border-bottom: none;
  background-color: #fff;
}

.css-lua631 {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 28px;
}

.css-13ku1qm {
  display: flex;
  align-items: center;
  gap: 6px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  line-height: 1.5;
  text-align: left;
  color: #1c1d1e;
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
  gap: 4px;
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

.css-khzu4u {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2px 6px;
  border-radius: 4px;
  background-color: #1c1d1e;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-size: 10px;
  font-weight: 700;
  line-height: 1.5;
  color: #fff;
}

.css-emxp16 {
  font-size: 12px;
  font-weight: 500;
  line-height: 1.3;
  text-align: left;
  color: #838689;
}

.css-emxp17 {
  font-size: 12px;
  font-weight: 500;
  line-height: 1.3;
  text-align: left;
  color: #838689;
  cursor: pointer;
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

.css-jpe6jj {
  gap: 16px;
  width: 100%;
  display: flex;
  align-items: start;
  justify-content: center;
}

.css-13ljjbe {
  width: 100%;
  border: 1px solid #eaebed;
  border-radius: 12px;
}

.css-001 {
  flex: 1;
  margin: 10px;
  outline: 0;
  border: none;
  font-family: Pretendard;
}

.css-btn-div {
  display: flex;
  justify-content: flex-end;
  margin-left: 10px;
}

.css-btn {
  background-color: rgb(52, 152, 219, 0.2);
  font-size: 12px;
  color: #000;
  font-weight: 700;
  width: 70px;
  height: 25px;
  border-radius: 10px;
  float: right;
  margin-right: 10px;
  cursor: pointer;
  font-family: Pretendard;
  margin-bottom: 10px;
}

.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  align-items: center;
}

.css-n4f4vi {
  width: 20px;
  height: 20px;
}

.css-n8w3ba {
  display: flex;
  justify-content: center;
  background-color: #1c1d1e;
  border-radius: 8px;
  padding: 14px;
  font-size: 14px;
  cursor: pointer;
  font-weight: 700;
  color: #f4f5f6;
  gap: 8px;
}

.css-1qam7gv {
  display: flex;
  flex-direction: row;
  gap: 8px;
}

.css-1vdbpsh {
  background-color: #1c1d1e;
  border-radius: 8px;
  text-align: center;
}

.css-board-back {
  cursor: pointer;
  width: 80px;
  height: 40px;
  padding: 10px 0;
  border-radius: 10px;
  background-color: #541d70;
  font-size: 13px;
  display: flex;
  justify-content: center;
  gap: 6px;
  color: #fff;
  font-family: Pretendard;
}

.css-back-div {
  text-align: center;
  margin-top: 50px;
}

.css-scrap {
  margin-left: 4px;
}
.css-5zcuovs {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-left: 0px;
  font-family: Pretendard;
  cursor: pointer;
  border-radius: 5px;
  background-color: rgba(84, 29, 112, 0.218);
  padding: 3px 10px;
}
.css-5zcuovss {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-left: 20px;
  font-family: Pretendard;
  cursor: pointer;
  border-radius: 5px;
  background-color: rgb(0 64 255 / 22%);
  padding: 3px 10px;
}
.css-1sika4is {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
.css-1sika4iss {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
/*------------확인 모달창-------------*/
.layer.active,
.layer_ov_ly.actve {
  display: block;
}
.layer *,
.layer_ov_ly * {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 40px 40px 50px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 330px;
  border-radius: 15px;
  border-color: white;
}
.layer.pop_shopping_bag p {
  padding: 40px 0 15px;
  font-family: "Pretendard";
  font-size: 16px;
  font-weight: 500px;
  line-height: 1.9;
  text-align: center;
}
.layer.pop_product .btn_wrap {
  margin-top: 15px;
  text-align: center;
  margin-bottom: 20px;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 10px 20px 10px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 330px;
}
a.btn,
button.btn,
input.btn,
span.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}
a.btn.gray,
button.btn.gray,
input.btn.gray,
span.btn.gray {
  border-color: #7d7d7d;
  background-color: #7d7d7d;
  color: #fff;
}
a.btn.small,
button.btn.small,
input.btn.small,
span.btn.small {
  height: 35px;
  font-size: 12px;
  line-height: 30px;
}
.layer.pop_product .btn_wrap .btn {
  min-width: 100px;
  margin: 0 8px;
}
a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}
.layer_cont .btn_close,
.layer_cont .pop_in_pop_close {
  overflow: hidden;
  position: absolute;
  top: 25px;
  right: 25px;
  width: 53px;
  height: 53px;
  padding: 15px;
  line-height: 99em;
  vertical-align: top;
  background-color: white;
  border-color: white;
}
.layer_cont .btn_close:before,
.layer_cont .pop_in_pop_close:before {
  display: block;
  width: 12px;
  height: 12px;
  background: url(//static.wconcept.co.kr/web/images/common/layer_close_23.png)
    no-repeat;
  background-size: 100%;
  content: "";
}
.layer > span,
.layer_ov_ly > span {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #0e0e0e;
  opacity: 0.4;
  filter: alpha(opacity=40);
  content: "";
  display: block;
}

.css-myjkxi {
  position: absolute;
  top: 18px;
  right: 16px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(58, 62, 65);
  display: flex;
  align-items: center;
  gap: 4px;
  cursor: pointer;
}
/* 코드 블럭*/
.editedQ_QContent pre.ql-syntax {
  overflow-x: auto;
  background-color: #f4f5f6;
  border: 1px solid #eaebed;
  padding: 14px 20px;
  border-radius: 8px;
  margin-top: 20px;
  max-width: 90vw;
  white-space: pre;
  line-height: 1.42;
  word-break: break-all;
}

/* editor */
.quill {
  min-height: 280px;
}

@media (min-width: 1024px) {
  .ql-editor,
  .quill {
    min-height: 280px;
  }
}

.commentEditor .quill {
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
::v-deep .ql-toolbar.ql-snow .ql-formats {
  margin-right: 15px;
  margin-left: 15px;
}
.ql-snow,
.ql-snow * {
  box-sizing: border-box;
}

@media (min-width: 1024px) {
  .ql-container {
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
    min-height: 520px;
  }
}

.ql-container {
  min-height: 200px;
}
.ql-container {
  border: 1px solid #ccc;
}
.ql-container.ql-snow {
  border: 1px solid #ccc;
}
.commentEditor .ql-container {
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
.commentEditor .ql-editor {
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
