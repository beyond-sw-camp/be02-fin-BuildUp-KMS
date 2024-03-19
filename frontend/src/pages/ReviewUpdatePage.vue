<template>
  <div class="css-1hnxdb7">
    <div class="css-130kwtj">
      <div class="css-1hwcs2h"></div>
    </div>
    <div height="120px" class="css-jbj5u0"></div>
    <div class="css-1g39gls">
      <div class="css-1f9h8vh">
        <div class="css-110bgim">
          <div class="css-28nsux"></div>
        </div>
      </div>
      <div class="css-1qjp6uf">
        <div class="css-axl4y">
          <div class="css-1yuvfju">
            <div class="css-k59gj9"></div>
            <div class="css-z2xt5y"></div>
          </div>
        </div>
        <div class="css-agejl7">
          <div class="css-r8q25b">
            <p>{{ reviewStore.reviewDetail.reviewCategoryName }} 수정하기</p>
          </div>
          <div class="css-1iyoj2o">
            <div class="css-1odc90o">
              <textarea
                class="css-16kqrm"
                rows="1"
                :placeholder="reviewStore.reviewDetail.reviewCategoryName"
                style="overflow: hidden; resize: none"
                readonly
              ></textarea>
              <!-- </div> -->
              <!-- 과정명 들어갈 곳 -->
              <div class="css-1iqxhyo">
                <textarea
                  rows="1"
                  :placeholder="reviewStore.reviewDetail.courseName"
                  class="css-16kqrm"
                  style="overflow: hidden; resize: none"
                  readonly
                ></textarea>
              </div>
              <div class="css-1iqxhyo">
                <!-- 제목 -->
                <textarea
                  rows="1"
                  class="css-16kqrm"
                  style="overflow: hidden; resize: none"
                  v-model="review.reviewTitle"
                ></textarea>
              </div>
              <div class="css-1t5srfes">
                <button
                  :class="selectedEvaluation ? 'css-1w5123ms' : 'css-pfhqzms'"
                  @click="showEvaluation()"
                >
                  {{ selectedEvaluation || "별점 선택"
                  }}<img
                    src="@/assets/img/9042707_nav_arrow_down_icon.svg"
                    :class="{
                      'css-1bhrqo4s': !isEvaluationClicked,
                      'css-yewqags': isEvaluationClicked,
                    }"
                  />
                </button>
                <ul class="css-l2t941s" v-show="isEvaluationClicked">
                  <li
                    v-for="(Evaluation, index) in Evaluations"
                    :key="index"
                    @click="selectEvaluation(Evaluation)"
                    :class="{
                      'css-nyb6vks': !Evaluation.selected,
                      'css-16uk8ors': Evaluation.selected,
                    }"
                  >
                    {{ Evaluation.name }}
                    <div v-if="Evaluation.selected">
                      <img
                        alt="체크"
                        width="16px"
                        height="16px"
                        src="https://static.spartacodingclub.kr/TeamSparta-Inc/scc-frontend/assets/images/icon-check-red.png"
                      />
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <br />
          <div class="css-17wj0zk">
            <div class="">
              <div class="quill">
                <div class="ql-container ql-snow">
                  <div class="d-flex justify-content-center py-0 py-md-4">
                    <div class="css-luqgif">
                      <!-- <div class="editedQ_QContent" v-for="(image, index) in boardDetail.boardImageList" :key="index"> -->
                      <!-- <p class="css-content">
                    {{ boardDetail.boardContent }}
                  </p> -->
                      <!-- <img alt="게시판 이미지" :data-src="image" /> -->
                    </div>
                    <!-----이미지 들어가는 곳----->
                    <div class="css-image" style="text-align: center">
                      <div
                        class="image-container"
                        style="max-width: 100%; display: inline-block"
                      >
                        <img
                          v-if="imageUrl"
                          :src="imageUrl"
                          alt="Uploaded Image"
                          style="max-width: 70%; height: auto"
                        />
                      </div>
                    </div>
                    <!-----이미지 들어가는 곳----->
                    <textarea
                      class="ql-editor ql-blank"
                      v-model="review.reviewContent"
                    ></textarea>
                    <div
                      class="ql-clipboard"
                      contenteditable="true"
                      tabindex="-1"
                    ></div>
                    <div class="ql-tooltip ql-hidden">
                      <a
                        class="ql-preview"
                        rel="noopener noreferrer"
                        target="_blank"
                        href="about:blank"
                      ></a
                      ><input
                        type="text"
                        data-formula="e=mc^2"
                        data-link="https://quilljs.com"
                        data-video="Embed URL"
                      /><a class="ql-action"></a><a class="ql-remove"></a>
                    </div>
                  </div>
                </div>
              </div>
              <div id="toolbar" class="ql-toolbar ql-snow">
                <span class="ql-formats"
                  ><button
                    content="이미지 첨부"
                    class="ql-image css-1qhzcav"
                    type="button"
                    @click="uploadImage()"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="20"
                      height="20"
                      viewBox="0 0 20 20"
                      fill="none"
                    >
                      <rect
                        x="3.336"
                        y="3.332"
                        width="13.333"
                        height="13.333"
                        rx="1.667"
                        stroke="#505254"
                        stroke-width="1.75"
                        stroke-linejoin="round"
                      ></rect>
                      <path
                        d="m16.664 12.499-5-4.167-7.5 7.5"
                        stroke="#505254"
                        stroke-width="1.75"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                      ></path>
                      <rect
                        x="5.836"
                        y="5.832"
                        width="2.5"
                        height="2.5"
                        rx="1.25"
                        fill="#505254"
                      ></rect>
                    </svg></button></span
                >
              </div>
            </div>
            <input
              id="input_file"
              type="file"
              class="css-38lglc"
              style="display: none"
              @change="handleImageUpload"
            />
          </div>
          <div class="css-lycl0a">
            <button class="css-9ns22y" @click="cancel()">취소</button>
            <button
              class="css-1c8gn7d"
              @click="updateReview()"
            >
              수정하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "../stores/useReviewStore";
// import { useUserStore } from "../stores/useUserStore";

export default {
  name: "ReviewUpdatePage",
  computed: {
    ...mapStores(useReviewStore),
    courseEvaluation() {
      return this.getEvaluation(this.selectedEvaluation);
    },
  },
  data() {
    return {
      isEvaluationClicked: false,
      selectedEvaluation: "",
      Evaluations: [
        { name: "1점", selected: false },
        { name: "2점", selected: false },
        { name: "3점", selected: false },
        { name: "4점", selected: false },
        { name: "5점", selected: false },
      ],
      inputValue: "",
      reviewImage: null,
      imageUrl: null,
      buttonOpacity: 1,

      review: {
        reviewIdx: 0,
        reviewTitle: "",
        reviewContent: "",
        courseEvaluation: null,
      },
    };
  },
  async mounted() {
    const reviewIdx = this.$route.params.reviewIdx;

    this.review.reviewIdx = reviewIdx;

    this.reviewDetail = await this.reviewStore.findReviewDetailByUserIdx(
      reviewIdx
    );
    this.review.reviewContent = this.reviewDetail.reviewContent;
    this.review.reviewTitle = this.reviewDetail.reviewTitle;
    this.imageUrl = this.reviewDetail.reviewImageList[0].reviewImage;

    const loadedEvaluation = this.reviewDetail.courseEvaluation;
    this.Evaluations.forEach((evaluation) => {
      evaluation.selected = evaluation.name === loadedEvaluation;
    });

    this.selectedEvaluation = loadedEvaluation + "점";
  },
  methods: {
    async updateReview() {
      await this.reviewStore.updateReview(this.review, this.reviewImage);
    },
    // 이미지 업로드
    uploadImage() {
      const input = document.getElementById("input_file");
      input.click();
    },
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (!file) return;

      this.reviewImage = file;

      const reader = new FileReader();
      reader.onload = () => {
        this.imageUrl = reader.result;
      };
      reader.readAsDataURL(file);
    },
    cancel() {
      window.location.href = "/review";
    },
    showEvaluation() {
      this.isEvaluationClicked = !this.isEvaluationClicked;
    },
    selectEvaluation(Evaluation) {
      this.Evaluations.forEach((cat) => {
        cat.selected = false;
      });
      Evaluation.selected = true;
      this.selectedEvaluation = Evaluation.name;
      this.isEvaluationClicked = false;

      this.review.courseEvaluation = this.getEvaluation(
        this.selectedEvaluation
      );
    },
    getEvaluation(selectedEvaluation) {
      return selectedEvaluation === "1점"
        ? 1
        : selectedEvaluation === "2점"
        ? 2
        : selectedEvaluation === "3점"
        ? 3
        : selectedEvaluation === "4점"
        ? 4
        : selectedEvaluation === "5점"
        ? 5
        : null;
    },
  },
};
</script>

<style scoped>
@media (min-width: 820px) {
  .css-1g39gls {
    display: flex;
    flex-direction: row;
    width: 100%;
    gap: 43px;
    max-width: 942px;
    margin: 0 auto;
    padding: 40px 0 120px;
  }
}

@media (min-width: 820px) {
  .css-1qjp6uf {
    max-width: 680px;
    border-radius: 8px;
    border: 1px solid #e4ebf0;
    background-color: #ffffff;
    padding: 40px;
    width: 100%;
    margin: auto;
    margin-top: 60px;
    z-index: 5;
  }
}

@media ((min-width: 820px)) {
  .css-hmpurq {
    padding: 24px 0;
    border-bottom: 1px solid #e4ebf0;
    gap: 14px;
  }
}

@media ((min-width: 820px)) {
  .css-amlmv6 {
    display: none;
  }
}

@media ((min-width: 820px)) {
  .css-12i5occ {
    gap: 4px;
    width: 100%;
  }
}

@media (min-width: 820px) {
  .css-n7izk0 {
    font-style: normal;
    line-height: 24px;
    color: #141617;
    max-width: 83%;
    font-size: 20px;
    font-weight: 700;
  }
}

@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}

* {
  margin: 0;
  line-height: 1.5;
}

* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

div {
  display: block;
}

.css-r8q25b {
  display: flex;
  -webkit-box-pack: start;
  justify-content: flex-start;
  margin-bottom: 48px;
  font-size: 24px;
  font-weight: 700;
}

.css-1iyoj2o {
  display: flex;
  flex-direction: column;
}

.css-1odc90o {
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 12px;
  margin: 0px auto;
}

.css-lxrq3o {
  transform: none;
  width: 20px !important;
}

img {
  image-rendering: -moz-crisp-edges;
  image-rendering: -o-crisp-edges;
  image-rendering: -webkit-optimize-contrast;
  -ms-interpolation-mode: nearest-neighbor;
}

img {
  overflow-clip-margin: content-box;
  overflow: clip;
}

element.style {
  overflow: hidden;
  resize: none;
}

.css-16kqrm {
  width: 100%;
  padding: 18px 16px;
  border-radius: 12px;
  border: 1px solid rgb(228, 235, 240);
  background-color: rgb(255, 255, 255);
  font-family: Pretendard;
  line-height: 18px;
  margin-top: 8px;
}

.css-1iqxhyo {
  width: 100%;
}

.css-17wj0zk {
  border: 1px solid rgb(234, 235, 237);
  border-radius: 12px;
}

.quill {
  min-height: 280px;
}

.ql-container.ql-snow {
  border: none !important;
}

.ql-container.ql-snow {
  border: 1px solid #ccc;
}

.ql-container {
  min-height: 200px;
}

.ql-snow,
.ql-snow * {
  box-sizing: border-box;
}

.ql-container {
  box-sizing: border-box;
  font-family: Helvetica, Arial, sans-serif;
  font-size: 13px;
  height: 100%;
  margin-top: 6px;
  position: relative;
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
  resize: none;
  border: none;
}

.ql-editor {
  height: 100%;
  overflow-y: auto;
  padding: 12px 15px;
}
.quill > .ql-container > .ql-editor.ql-blank:before {
  color: #c7c9cb;
  font-style: normal;
  font-size: 14px;
  white-space: pre-wrap;
  line-height: 1.5;
  padding: 5px;
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

.ql-editor.ql-blank:before {
  left: 15px;
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

.ql-editor * {
  font-stretch: normal;
  font-style: normal;
  letter-spacing: normal;
  font-family: Pretendard;
}

.ql-editor * {
  box-sizing: border-box;
}

.ql-editor > * {
  cursor: text;
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

.ql-clipboard {
  left: -100000px;
  height: 1px;
  overflow-y: hidden;
  position: absolute;
  top: 50%;
}

.ql-snow .ql-tooltip {
  background-color: #fff;
  border: 1px solid #ccc;
  box-shadow: 0 0 5px #ddd;
  color: #444;
  padding: 5px 12px;
  white-space: nowrap;
}

.ql-snow .ql-hidden {
  display: none;
}

.ql-snow .ql-tooltip a.ql-preview {
  display: inline-block;
  max-width: 200px;
  overflow-x: hidden;
  text-overflow: ellipsis;
  vertical-align: top;
}

.ql-tooltip a.ql-preview {
  display: inline-block;
  max-width: 200px;
  overflow-x: hidden;
  text-overflow: ellipsis;
  vertical-align: top;
}

.ql-snow .ql-tooltip a {
  line-height: 26px;
}

.ql-snow .ql-tooltip a {
  cursor: pointer;
  text-decoration: none;
}

.ql-snow .ql-tooltip input[type="text"] {
  display: none;
  border: 1px solid #ccc;
  font-size: 13px;
  height: 26px;
  margin: 0;
  padding: 3px 5px;
  width: 170px;
}

.ql-tooltip input[type="text"] {
  display: none;
  border: 1px solid #ccc;
  font-size: 13px;
  height: 26px;
  margin: 0;
  padding: 3px 5px;
  width: 170px;
}

input[type="text" i] {
  padding-block: 1px;
  padding-inline: 2px;
}

input:not(
    [type="file" i],
    [type="image" i],
    [type="checkbox" i],
    [type="radio" i]
  ) {
}

.ql-snow .ql-tooltip {
  position: absolute;
  transform: translateY(10px);
}

a,
button {
  -webkit-text-decoration: none;
  text-decoration: none;
  cursor: pointer;
}

button {
  background: unset;
  border: unset;
  padding: unset;
}

.ql-snow .ql-toolbar button svg,
.ql-snow.ql-toolbar button svg {
  float: left;
  height: 100%;
}

.ql-snow.ql-toolbar button,
.ql-snow.ql-update-toolbar button {
  height: 30px;
  width: 30px;
  padding: 0 5px;
}

.ql-snow .ql-toolbar button,
.ql-snow.ql-toolbar button {
  background: none;
  border: none;
  cursor: pointer;
  display: inline-block;
  float: left;
  height: 24px;
  padding: 3px 5px;
  width: 28px;
}

.css-1qhzcav {
  position: relative;
  cursor: default;
}

.ql-toolbar .ql-formats {
  margin-right: 10px !important;
}

.ql-snow .ql-formats {
  display: inline-block;
  vertical-align: middle;
}

.ql-formats {
  display: inline-block;
  vertical-align: middle;
}

.ql-toolbar.ql-snow .ql-formats {
  margin-right: 15px;
}

.ql-snow .ql-formats:after {
  clear: both;
  content: "";
  display: table;
}

.ql-formats:after {
  clear: both;
  content: "";
  display: table;
}

.ql-toolbar.ql-snow {
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  padding: 8px;
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

.css-38lglc {
  display: none;
}

.css-1yfg4fi {
  display: flex;
  flex-flow: wrap;
  width: 100%;
  padding: 16px 12px;
  border-radius: 8px;
  border: 1px solid rgb(228, 235, 240);
  margin-top: 3px;
}

/*-------------태크------------------*/
/* .css-1vitttd {
  display: flex;
  flex-flow: wrap;
  gap: 8px;
  -webkit-box-align: center;
  align-items: center;
}
.css-170uj16 {
  height: 26px;
  padding: 4px 8px;
  display: flex;
  flex-shrink: 0;
  flex-direction: row;
  gap: 4px;
  -webkit-box-align: center;
  align-items: center;
  border-radius: 4px;
  background-color: rgb(242, 246, 248);
}
.css-dcsj63 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 150%;
  color: rgb(129, 137, 143);
}
.css-170uj16 svg {
  width: 12px;
  height: 12px;
}
.css-ih6wu3 {
  width: unset;
  border: none;
  outline: none;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 150%;
  color: rgb(95, 102, 107);
} */

/*-------------태크------------------*/

.css-9y3tf9 {
  width: 100%;
  border: none;
  outline: none;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 150%;
  color: rgb(95, 102, 107);
}

input {
  border: none;
  background: none;
  outline: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.css-lycl0a {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: center;
  justify-content: center;
  width: 100%;
  margin: 24px auto 0px;
}

.css-1c8gn7d {
  border-radius: 8px;
  font-size: 16px;
  height: 56px;
  border: none;
  font-weight: 700;
  color: rgb(255, 255, 255);
  background-color: #541d7a;
  width: inherit;
  opacity: 0.3;
  margin-right: 14px;
}
.css-1c8gn7d:hover {
  opacity: 1;
  background-color: #541d7a;
}

.css-agejl7 {
  max-width: 980px;
  width: 100%;
  min-height: 650px;
  position: relative;
  border-radius: 20px;
  margin-top: 0px;
  padding: 48px 20px;
  background-color: rgb(255, 255, 255);
  z-index: 22;
}

.css-wonqq0 {
  position: fixed;
  top: -72px;
  z-index: 30;
  width: 100%;
  min-width: 720px;
  height: calc(100% + 72px);
  display: flex;
  overflow: auto;
  align-items: self-start;
  -webkit-box-pack: center;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.64);
}

.css-pfhqzm {
  font-family: Pretendard;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 100%;
  height: inherit;
  color: rgb(199, 201, 203);
  border: 0px none;
  outline: none 0px;
  padding-left: 20px;
  padding-right: 20px;
  background: transparent;
  cursor: pointer;
}
.css-pfhqzms {
  font-family: Pretendard;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 100%;
  height: inherit;
  color: rgb(199, 201, 203);
  border: 0px none;
  outline: none 0px;
  padding-left: 20px;
  padding-right: 20px;
  background: transparent;
  cursor: pointer;
}
.css-1w5123m {
  font-family: Pretendard;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 100%;
  height: inherit;
  color: rgb(0, 0, 0);
  border: 0px none;
  outline: none 0px;
  padding-left: 20px;
  padding-right: 20px;
  background: transparent;
  cursor: pointer;
}
.css-1w5123ms {
  font-family: Pretendard;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  width: 100%;
  height: inherit;
  color: rgb(0, 0, 0);
  border: 0px none;
  outline: none 0px;
  padding-left: 20px;
  padding-right: 20px;
  background: transparent;
  cursor: pointer;
}
/* 카테고리 선택 */
ul {
  margin-block-start: 0;
  margin-block-end: 0;
  -webkit-padding-start: 0;
  padding-inline-start: 0;
}
.css-l2t941 {
  position: absolute;
  top: 63px;
  padding: 8px;
  border-radius: 12px;
  overflow: hidden;
  border: 1px solid rgb(234, 235, 237);
  box-shadow: rgba(0, 0, 0, 0.08) 0px 8px 20px 0px;
  z-index: 30;
  left: 0px;
  width: 100%;
  background: rgb(255, 255, 255);
  color: black;
  list-style-type: none;
  transition: all 0.3s ease-in 0s;
}
.css-l2t941s {
  position: absolute;
  top: 63px;
  padding: 8px;
  border-radius: 12px;
  overflow: hidden;
  border: 1px solid rgb(234, 235, 237);
  box-shadow: rgba(0, 0, 0, 0.08) 0px 8px 20px 0px;
  z-index: 30;
  left: 0px;
  width: 100%;
  background: rgb(255, 255, 255);
  color: black;
  list-style-type: none;
  transition: all 0.3s ease-in 0s;
}

.css-nyb6vk {
  padding: 14.5px 20px;
  height: 50px;
  flex-direction: row;
  gap: 10px;
  transition: all 0.1s ease 0s;
  display: flex;
  border-radius: 8px;
  font-weight: 500;
  color: rgb(80, 82, 84);
  background-color: rgb(255, 255, 255);
  cursor: pointer;
}
.css-nyb6vks {
  padding: 14.5px 20px;
  height: 50px;
  flex-direction: row;
  gap: 10px;
  transition: all 0.1s ease 0s;
  display: flex;
  border-radius: 8px;
  font-weight: 500;
  color: rgb(80, 82, 84);
  background-color: rgb(255, 255, 255);
  cursor: pointer;
}

.css-nyb6vk:hover {
  background-color: rgb(255, 241, 244);
  background-size: 100px 100%;
}
.css-nyb6vks:hover {
  background-color: rgb(255, 241, 244);
  background-size: 100px 100%;
}
.css-16uk8or {
  padding: 14.5px 20px;
  height: 50px;
  flex-direction: row;
  gap: 10px;
  transition: all 0.1s ease 0s;
  display: flex;
  border-radius: 8px;
  font-weight: 700;
  color: rgb(232, 52, 78);
  background-color: rgb(255, 255, 255);
  cursor: pointer;
}
.css-16uk8ors {
  padding: 14.5px 20px;
  height: 50px;
  flex-direction: row;
  gap: 10px;
  transition: all 0.1s ease 0s;
  display: flex;
  border-radius: 8px;
  font-weight: 700;
  color: rgb(232, 52, 78);
  background-color: rgb(255, 255, 255);
  cursor: pointer;
}
/* 카테고리 선택 */
.css-1bhrqo4 {
  transform: none;
  transition-duration: 0.5s;
}
.css-1bhrqo4s {
  transform: none;
  transition-duration: 0.5s;
}
.css-yewqag {
  transform: rotate(180deg);
  transition-duration: 0.5s;
}
.css-yewqags {
  transform: rotate(180deg);
  transition-duration: 0.5s;
}
.css-1q8adtw {
  display: flex;
  flex-direction: row;
  position: relative;
  -webkit-box-pack: justify;
  justify-content: space-between;
  font-size: 14px;
  padding: 0px 20px;
  line-height: 56px;
  width: 100%;
  height: 56px;
  border: 1px solid rgb(234, 235, 237);
  border-radius: 12px;
  background-color: rgb(255, 255, 255);
  color: rgb(199, 201, 203);
}

.css-1t5srfe {
  font-family: Pretendard;
  box-sizing: border-box;
  position: relative;
  width: 100%;
  height: 56px;
  border-radius: 12px;
  border: 1px solid rgb(234, 235, 237);
  background-color: rgb(255, 255, 255);
  background-size: 20px;
  cursor: pointer;
}
.css-1t5srfes {
  font-family: Pretendard;
  box-sizing: border-box;
  position: relative;
  width: 100%;
  height: 56px;
  border-radius: 12px;
  border: 1px solid rgb(234, 235, 237);
  background-color: rgb(255, 255, 255);
  background-size: 20px;
  cursor: pointer;
}

.css-9ns22y {
  border: 1px solid rgb(219, 221, 224);
  border-radius: 8px;
  margin-right: 12px;
  width: 364px;
  height: 56px;
  background-color: rgb(255, 255, 255);
  font-size: 16px;
  text-align: center;
  font-weight: 700;
}

.css-lycl0a {
  width: 400px;
}

.css-image {
  margin-top: 20px;
}

.css-16kqrm:focus-visible {
  outline: 2px solid rgb(58, 62, 65);
}
</style>
