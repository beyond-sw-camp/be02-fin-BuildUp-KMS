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
            <p>
              {{ boardStore.boardDetail.boardCategoryName }} 게시글 수정하기
            </p>
          </div>
          <div class="css-1iyoj2o">
            <div class="css-1odc90o">
              <textarea
                class="css-16kqrm"
                rows="1"
                :placeholder="boardStore.boardDetail.boardCategoryName"
                style="overflow: hidden; resize: none"
                readonly
              ></textarea>
              <div class="css-1iqxhyo">
                <!-- 제목 -->
                <textarea
                  rows="1"
                  class="css-16kqrm"
                  style="overflow: hidden; resize: none"
                  v-model="board.boardTitle"
                ></textarea>
              </div>

              <div class="css-1yfg4fi">
                <div class="css-1vitttd">
                  <!-- 태그 표시 영역 -->
                  <div
                    v-for="(tag, index) in tags"
                    :key="index"
                    class="css-170uj16"
                  >
                    <div class="css-dcsj63"># {{ tag }}</div>
                    <svg
                      @click="removeTag(index)"
                      width="16"
                      height="16"
                      viewBox="0 0 16 16"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        d="M3.33435 3.33435L12.6677 12.6677"
                        stroke="#9DA7AE"
                        stroke-width="1.5"
                        stroke-linecap="round"
                      ></path>
                      <path
                        d="M12.6656 3.33435L3.33232 12.6677"
                        stroke="#9DA7AE"
                        stroke-width="1.5"
                        stroke-linecap="round"
                      ></path>
                    </svg>
                  </div>
                </div>
                <input
                  v-model="inputValue"
                  @focus="onFocus"
                  placeholder="# 태그를 입력해주세요(최대 3개)"
                  class="css-ih6wu3"
                  @keyup.enter="addTag"
                />
              </div>
            </div>
          </div>
          <br />
          <div class="css-17wj0zk">
            <div class="">
              <div class="quill">
                <quill-editor
                  ref="quillEditor"
                  v-model:value="state.content"
                  :options="state.editorOption"
                  :disabled="false"
                  @change="onEditorChange($event)"
                ></quill-editor>
              </div>
            </div>
          </div>
          <div class="css-lycl0a">
            <button class="css-9ns22y" @click="cancel()">취소</button>
            <button class="css-1c8gn7d" @click="updateBoard()">수정하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useBoardStore } from "../stores/useBoardStore";
import Quill from "quill";
import axios from "axios";
import QuillImageUploader from "quill-image-uploader";
import hljs from "highlight.js";
import "highlight.js/styles/monokai-sublime.css";

Quill.register("modules/image-uploader", QuillImageUploader);

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

export function imageHandler() {
  const accessToken = localStorage.getItem("accessToken");
  const refreshToken = localStorage.getItem("refreshToken");
  const quill = this.quill;

  const input = document.createElement("input");

  input.setAttribute("type", "file");
  input.setAttribute("accept", "image/*");
  input.click();

  input.onchange = async () => {
    const file = input.files[0];
    const formData = new FormData();

    formData.append("image", file);

    const range = quill.getSelection(true);

    quill.insertEmbed(
      range.index,
      "image",
      "https://cdn.dribbble.com/users/1341307/screenshots/5377324/morph_dribbble.gif"
    );

    quill.setSelection(range.index + 1);

    quill.deleteText(range.index, 1);

    try {
      const backend = "http://www.bootshelf.kro.kr/api";
      // const backend = "http://localhost:8080";

      let response = await axios({
        method: "POST",
        url: backend + "/board/image/upload",
        headers: {
          Authorization: `Bearer ${accessToken}`,
          RefreshToken: `Bearer ${refreshToken}`,
          "Content-Type": "multipart/form-data",
        },
        data: formData,
      });

      if (response.headers["new-access-token"] != null) {
        if (
          response.headers["new-access-token"] !=
          localStorage.getItem("accessToken")
        ) {
          localStorage.setItem("accessToken", "");
          localStorage.setItem(
            "accessToken",
            response.headers["new-access-token"]
          );
        }
      }

      if (response.headers["new-refresh-token"] != null) {
        if (
          response.headers["new-refresh-token"] !=
          localStorage.getItem("refreshToken")
        ) {
          localStorage.setItem("refreshToken", "");
          localStorage.setItem(
            "refreshToken",
            response.headers["new-refresh-token"]
          );
        }
      }
      if (response.data.isSuccess === true) {
        let imageUrl = response.data.result.imageUrl;
        quill.insertEmbed(range.index, "image", imageUrl);
        this.board.boardImageList.push(imageUrl);
      }
    } catch (e) {
      console.log(e);
    }
  };
}

export default {
  name: "BoardUpdatePage",
  computed: {
    ...mapStores(useBoardStore),
  },
  watch: {
    inputValue(newValue) {
      if (newValue && !newValue.startsWith("#")) {
        this.inputValue = "# " + newValue.trim();
      }
    },
  },
  data() {
    return {
      state: {
        content: "",
        _content: "",
        editorOption: {
          placeholder: "내용을 입력해주세요",
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
                  "image",
                  { header: 1 },
                  { header: 2 },
                  { align: [] },
                  { list: "ordered" },
                  { list: "bullet" },
                  { color: [] },
                  { background: [] },
                  { font: [] },
                  { header: [1, 2, 3, 4, 5, 6, false] },
                ],
              ],
              handlers: {
                image: imageHandler,
              },
            },
          },
        },
        disabled: false,
      },
      tags: [],
      inputValue: "",
      buttonOpacity: 1,

      board: {
        boardTitle: "",
        boardContent: "",
        boardIdx: 0,
        tagList: [],
      },
    };
  },
  async mounted() {
    const boardIdx = this.$route.params.boardIdx;

    this.board.boardIdx = boardIdx;

    this.boardDetail = await this.boardStore.findBoardDetailByUserIdx(boardIdx);
    this.state.content = this.boardDetail.boardContent;
    this.board.boardTitle = this.boardDetail.boardTitle;

    if (this.boardDetail.tagList && this.boardDetail.tagList.length > 0) {
      this.tags = this.boardDetail.tagList;
    }
  },
  methods: {
    onEditorChange({ html }) {
      this.state._content = html;
    },
    async updateBoard() {
      this.board.tagList = this.tags;
      this.board.boardContent = this.state._content;
      await this.boardStore.updateBoard(this.board);
    },
    // 태그 추가
    addTag() {
      if (this.tags.length >= 3) {
        alert("태그는 최대 3개까지 추가할 수 있습니다.");
        return;
      }

      if (
        this.inputValue.startsWith("#") &&
        this.inputValue.length > 1 &&
        this.tags.length < 3
      ) {
        this.tags.push(this.inputValue.slice(1).trim());
        this.inputValue = "";
        console.log(this.tags);
      }
    },
    removeTag(index) {
      this.tags.splice(index, 1);
      console.log(this.tags);
    },
    onFocus() {
      if (!this.inputValue) {
        this.inputValue = "# ";
      }
    },
    cancel() {
      window.location.href = "/mypage";
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
  min-height: 350px;
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

::v-deep .ql-toolbar.ql-snow {
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  padding: 8px;
  border-radius: 10px;
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

.css-1vitttd {
  display: flex;
  flex-flow: wrap;
  gap: 8px;
  -webkit-box-align: center;
  align-items: center;
}

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
  margin-bottom: 60px;
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

.css-1bhrqo4 {
  transform: none;
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
.css-1vitttd {
  display: flex;
  flex-flow: wrap;
  gap: 8px;
  -webkit-box-align: center;
  align-items: center;
}
.css-170uj16 {
  height: 26px;
  padding: 0px 8px;
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
  margin-left: 8px;
}

/*-------------태크------------------*/

.css-1vitttd {
  display: flex;
  flex-flow: wrap;
  gap: 8px;
  -webkit-box-align: center;
  align-items: center;
}
.css-170uj16 {
  height: 26px;
  padding: 0px 8px;
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
.css-image {
  margin-top: 20px;
}

::v-deep .ql-snow .ql-editor img {
  max-width: 300px;
  max-height: 400px;
}

::v-deep .ql-snow .ql-editor pre.ql-syntax {
  background-color: #23241f;
  color: #f8f8f2;
  overflow: visible;
  font-family: Monaco;
  letter-spacing: 0.07em;
  font-size: 12px;
}
</style>
