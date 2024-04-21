<template>
  <!-- 내 코드 -->
  <div class="css-1hnxdb7">
    <div class="css-130kwtj">
      <div class="css-1hwcs2h">
        <!-- <div class="css-xbcdvo">자유게시판</div>
          <div class="css-s5p19z">학습 질문</div>
          <div class="css-s5p19z">개발일지</div>
          <div class="css-s5p19z">나의 활동</div> -->
      </div>
    </div>
    <div height="120px" class="css-jbj5u0"></div>
    <div class="css-1g39gls" v-if="boardDetail">
      <div class="css-1qjp6uf">
        <div class="css-axl4y">
          <div class="css-1yuvfju">
            <div class="css-k59gj9">
              <div class="css-hmpurq">
                <div class="css-category">
                  {{ boardDetail.boardCategoryName }}
                </div>
                <div class="css-kem115">
                  <div class="css-12i5occ">
                    <div class="css-1jibmi3">
                      <div class="css-n7izk0">
                        {{ boardDetail.boardTitle }}
                      </div>
                    </div>
                    <div class="css-sebsp7"></div>
                  </div>
                  <div class="css-bt1qy">
                    <div class="css-1hqtm5a">
                      <div @click="createBoardUp()">
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
                          {{ boardDetail.upCnt }}
                        </p>
                      </div>
                    </div>

                    <div class="css-1hqtm5a">
                      <div @click="createBoardScrap()">
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
                          {{ boardDetail.scrapCnt }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <ConfirmDialogComponent
                  v-if="showMyPageConfirmDialog"
                  :isVisible="showMyPageConfirmDialog"
                  message="마이페이지로 이동하시겠습니까?"
                  :onConfirm="moveMyPage"
                  :onCancel="dontMoveMyPage"
                />
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
                          :src="boardDetail.userProfileImage"
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
                      <div class="css-1sika4i">{{ boardDetail.nickName }}</div>
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
                      <div class="css-1ry6usa">{{ this.$moment.utc(boardDetail.createdAt).local().format('YYYY-MM-DD HH:mm:ss') }}</div>
                    </div>
                  </div>
                </div>
                <div class="css-z2xt5y"></div>
              </div>
            </div>
            <div class="css-luqgif">
              <div class="editedQ_QContent">
                <p class="css-content">
                  {{ boardDetail.boardContent }}
                </p>
                <div
                  v-if="
                    boardDetail.boardImageList &&
                    boardDetail.boardImageList.length > 0
                  "
                >
                  <img
                    v-for="(image, index) in boardDetail.boardImageList"
                    :key="image.boardImageIdx"
                    :alt="`이미지 ${index + 1}`"
                    :src="image.boardImage"
                  />
                </div>
              </div>
              <div class="css-iqys2n">
                <!-- 태그 컴포넌트 자리-->
                <TagComponent :tagNameList="boardDetail.tagList" />
              </div>
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
                  댓글 {{ boardDetail.commentCnt }}
                </div>
              </div>
              <div class="css-qzobjv">
                <!-- 댓글 컴포넌트 -->
                <BoardCommentComponent
                  :commentList="commentList"
                  :boardIdx="boardIdx"
                  :isCommentRecommended="isCommentRecommended"
                  :boardCommentIdx="boardCommentIdx"
                />
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
                      <input
                        class="css-001"
                        type="text"
                        placeholder="댓글을 남겨주세요"
                        v-model="boardCommentContent"
                      />
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
                <button class="css-board-back" @click="goBack()">
                  목록가기
                </button>
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
import BoardCommentComponent from "@/components/BoardCommentComponent.vue";
import TagComponent from "../components/TagComponent.vue";
import { useBoardCommentStore } from "../stores/useBoardCommentStore";
import { useBoardStore } from "@/stores/useBoardStore";
import { useUserStore } from "@/stores/useUserStore";
import { mapStores } from "pinia";
import ConfirmDialogComponent from "/src/components/ConfirmDialogComponent.vue";

export default {
  name: "StudyDetailsPage",
  components: {
    BoardCommentComponent,
    TagComponent,
    ConfirmDialogComponent,
  },
  data() {
    return {
      boardCommentContent: "",
      boardDetail: null,
      boardIdx: null,
      commentList: null,
      isAuthenticated: null,
      showMyPageConfirmDialog: false,
      isRecommended: false,
      isScrapped: false,
      boardUpIdx: null,
      boardScrapIdx: null,
    };
  },
  computed: {
    ...mapStores(useBoardStore, useBoardCommentStore, useUserStore, ["tagList"]),
    isLoggedIn() {
      return !!localStorage.getItem("accessToken");
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
    if (this.isLoggedIn) {
      this.userStore.getUserInfo();
    }
  },
  async mounted() {
    const boardIdx = this.$route.params.boardIdx;

    this.boardIdx = boardIdx;

    this.boardDetail = await this.boardStore.findBoard(boardIdx);

    await this.boardCommentStore.getBoardCommentList(boardIdx);
    this.commentList = this.boardCommentStore.commentList;

    // 게시글 추천 및 스크랩 상태 확인
    await this.checkBoardUp();
    await this.checkBoardScrap();

    const previousPath = localStorage.getItem("previousPath");
    if (previousPath) {
      this.boardStore.setPreviousPath(previousPath);
    }
  },
  methods: {
    goBack() {
        this.$router.go(-1);
    },
    async submitComment() {
      let isAuthenticated = this.userStore.isAuthenticated;
      try {
        await useBoardCommentStore().createBoardComment(
          this.boardCommentContent,
          this.boardIdx,
          isAuthenticated
        );
        // 댓글 생성 후 필요한 작업 작성
      } catch (error) {
        console.error("댓글 작성 실패:", error);
      }
    },
    async createBoardUp() {
      let accessToken = window.localStorage.getItem("accessToken");
      let requestBody = {
        boardIdx: this.boardIdx,
      };

      try {
        if (this.isRecommended) {
          await this.boardStore.cancelBoardUp(accessToken, this.boardUpIdx);
          console.log("게시글 추천 취소 성공");
          this.isRecommended = false;

          window.location.reload();
        } else {
          const response = await this.boardStore.createBoardUp(
            accessToken,
            requestBody
          );

          if (response.status === 200 && response.data) {
            console.log("게시글 추천 성공!");
            this.isRecommended = true;
            this.showMyPageConfirmDialog = true;
          } else {
            console.error("게시글 추천 실패");
            alert("게시글 추천 실패");
          }
        }
      } catch (e) {
        console.error("게시글 추천 과정에서 문제가 발생했습니다!", e);
      }
    },
    async createBoardScrap() {
      let accessToken = window.localStorage.getItem("accessToken");
      let requestBody = {
        boardIdx: this.boardIdx,
      };

      try {
        if (this.isScrapped) {
          await this.boardStore.cancelBoardScrap(accessToken, this.boardScrapIdx);
          console.log("게시글 스크랩 취소 성공");
          this.isScrapped = false;

          window.location.reload();
        } else {
          const response = await this.boardStore.createBoardScrap(
            accessToken,
            requestBody
          );

          if (response.status === 200 && response.data) {
            console.log("게시글 스크랩 성공!");
            this.isScrapped = true;
            this.showMyPageConfirmDialog = true;
          } else {
            console.error("게시글 스크랩 실패");
            alert("게시글 스크랩 실패");
          }
        }
      } catch (e) {
        console.error("게시글 스크랩 과정에서 문제가 발생했습니다!", e);
      }
    },
    moveMyPage() {
      this.showMyPageConfirmDialog = false;
      this.$router.push("/mypage");
    },
    dontMoveMyPage() {
      this.showMyPageConfirmDialog = false;
      window.location.reload();
    },
    async checkBoardUp() {
      try {
        let accessToken = window.localStorage.getItem("accessToken");
        let response = await this.boardStore.checkBoardUp(accessToken, this.boardIdx);

        if (response.data && response.data.result.status === true) {
          this.isRecommended = true;
          this.boardUpIdx = response.data.result.boardUpIdx;
        } else {
          this.isRecommended = false;
        }
      } catch (e) {
        console.error(e);
      }
    },
    async checkBoardScrap() {
      try {
        let accessToken = window.localStorage.getItem("accessToken");
        let response = await this.boardStore.checkBoardScrap(
          accessToken,
          this.boardIdx
        );

        if (response.data && response.data.result.status === true) {
          this.isScrapped = true;
          this.boardScrapIdx = response.data.result.boardScrapIdx;
        } else {
          this.isScrapped = false;
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

.editedQ_QContent img {
  margin-top: 20px;
  max-width: 89vw;
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
  height: 90px;
}

.commentEditor {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
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
</style>
