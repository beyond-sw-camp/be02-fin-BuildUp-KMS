<template>
  <div class="loadingio-spinner-spinner" v-if="reviewStore.isLoading">
    <div class="ldio-f4nnk2ltl0v">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <div class="css-1hnxdb7">
    <div class="css-mbwamd">
      <div class="css-1b9to7p">
        <!-- 여기는 왼쪽 사이드바-->
        <div class="css-vsssfb">
          <div class="css-110bgim">
            <div class="css-28nsux">
              <!-- HOT TAG 컴포넌트 -->
              <div class="css-nw8p9d">
                <div class="css-19831his"># 인기태그</div>
              </div>
              <div
                class="css-nw8p9d"
                v-for="hotTags in boardTagStore.hotTagList"
                :key="hotTags.tagIdx"
              >
                <HotTagComponent :hotTags="hotTags" />
              </div>
            </div>
          </div>
        </div>
        <!--여기서부터는 게시판 쪽-->
        <div class="css-33zep3">
          <div class="css-17t7asl">
            <div class="css-1jibmi3">
              <div class="css-1mpmq0i">
                <div class="css-18vdxik">후기 게시판</div>
              </div>
              <!--부제-->
              <div class="css-1qzbd5x">
                부트캠프에 대한 후기를 자유롭게 이야기해보세요!
              </div>
            </div>
          </div>
          <!--여기서부터 우리거 적용-->
          <div class="css-1rw3qt4">
            <!--게시글 작성 버튼-->
            <div class="css-6cwwok">
              <div class="css-1hbxc4s">
                <div class="css-ogh6wd">
                  <div class="css-1tttep5">
                    <div class="css-1pbcmmt-001">
                      <div class="css-5ala5m-001">
                        <router-link to="/review/new">
                          <div class="css-nmdn6a-001">작성하기</div>
                        </router-link>
                      </div>
                    </div>
                  </div>
                  <!-- 검색창 -->
                  <div class="css-ogh6wd">
                    <div class="css-1tttep5">
                      <div class="css-1pbcmmt-002">
                        <div class="css-5ala5m-002">
                          <div class="css-nmdn6a-002">
                            <input
                              class="css-search-002"
                              type="text"
                              placeholder="제목과 내용으로 검색할 단어를 입력하세요."
                              v-model="searchTerm"
                              @keyup.enter="sendSearchData()"
                            />
                          </div>
                          <img
                            class="css-search-img"
                            src="https://img.icons8.com/ios-glyphs/30/search--v1.png"
                            alt="search--v1"
                            @click="sendSearchData()"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- 정렬 순서 셀렉터 -->
                  <div class="css-select000">
                    <select
                      class="css-select001"
                      v-model="selectedSortType"
                      @change="updateSortType"
                    >
                      <option value="최신순">최신순</option>
                      <option value="추천순">추천순</option>
                      <option value="조회순">조회순</option>
                      <option value="스크랩순">스크랩순</option>
                      <option value="댓글순">댓글순</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="css-6ylcwl">
                <div class="css-1o94c7r">
                  <div class="css-1kb98ja">
                    <svg
                      width="4"
                      height="4"
                      viewBox="0 0 4 4"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <circle
                        cx="2"
                        cy="2"
                        r="2"
                        :fill="
                          selectedReviewType === 'course'
                            ? '#e8344e'
                            : '#B4BFC6'
                        "
                      ></circle>
                    </svg>
                    <!-- <div class="css-1619ajl">과정 후기</div> -->
                    <div
                      :class="
                        selectedReviewType === 'course'
                          ? 'css-1619ajl'
                          : 'css-1j5hzn7'
                      "
                      @click="selectReviewType('course')"
                    >
                      과정 후기
                    </div>
                  </div>
                  <div class="css-bewb21">
                    <svg
                      width="4"
                      height="4"
                      viewBox="0 0 4 4"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <circle
                        cx="2"
                        cy="2"
                        r="2"
                        :fill="
                          selectedReviewType === 'course'
                            ? '#B4BFC6'
                            : '#e8344e'
                        "
                      ></circle>
                    </svg>
                    <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                    <div
                      :class="
                        selectedReviewType === 'instructor'
                          ? 'css-1619ajl'
                          : 'css-1j5hzn7'
                      "
                      @click="selectReviewType('instructor')"
                    >
                      강사 후기
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--여기서 본격 글 리스트-->
            <div class="css-1csvk83">
              <ul class="css-10c0kk0 e15eiqsa1">
                <div
                  class="css-k59gj9"
                  v-for="reviews in reviewStore.reviewList"
                  :key="reviews.reviewIdx"
                >
                  <ReviewBoardComponent :reviews="reviews" />
                </div>
              </ul>
              <!---검색결과 없을 때-->
              <div class="css-6g4q8b" v-show="!reviewStore.isReviewExist">
                <div class="css-aa80it">
                  <img src="@/assets/img/002.png" class="css-1baht8c" />
                  <div class="css-dhqp8i">
                    <div class="css-c7zvxr">검색 결과가 없습니다.</div>
                    <div class="css-1mcux1f">
                      질문을 직접 남겨서 궁금증을 해결해 보세요!
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- /본격 글 리스트 -->
          </div>
          <div class="d-flex justify-content-center py-0 py-md-4">
            <PaginationComponent
              :current-page="reviewStore.currentPage"
              :total-pages="reviewStore.totalPages"
              :isPageExist="reviewStore.isPageExist"
              @change-page="changePage"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "../stores/useReviewStore";
import { useBoardTagStore } from "../stores/useBoardTagStore";
import ReviewBoardComponent from "@/components/ReviewBoardComponent.vue";
import HotTagComponent from "@/components/HotTagComponent.vue";
import PaginationComponent from "@/components/PaginationComponent.vue";
export default {
  name: "ReviewListPage",
  data() {
    return {
      selectedSortType: "최신순",
      sortType: 1,
      reviewCategoryIdx: "1",
      selectedReviewType: "course",
      searchTerm: "",
    };
  },
  computed: {
    ...mapStores(useReviewStore, useBoardTagStore),
  },
  components: {
    ReviewBoardComponent,
    HotTagComponent,
    PaginationComponent,
  },
  mounted() {
    this.loadReviewList(1);
    this.boardTagStore.getHotTagList();
  },
  methods: {
    updateSortType() {
      switch (this.selectedSortType) {
        case "최신순":
          this.sortType = 1;
          break;
        case "추천순":
          this.sortType = 2;
          break;
        case "조회순":
          this.sortType = 3;
          break;
        case "스크랩순":
          this.sortType = 4;
          break;
        case "댓글순":
          this.sortType = 5;
          break;
        default:
          this.sortType = 1; // 기본값 또는 예외 처리
      }
      this.loadReviewList(this.reviewStore.currentPage);
    },
    selectReviewType(type) {
      this.selectedReviewType = type;
      this.reviewCategoryIdx = type === "course" ? "1" : "2";
      this.loadReviewList(1); // 리뷰 카테고리 변경 시 첫 페이지로 돌아감.
    },
    async loadReviewList(page) {
      // 검색어가 있는 경우
      if (this.searchTerm) {
        await this.reviewStore.getSearchReviewList(
          this.reviewCategoryIdx,
          this.searchTerm,
          this.sortType,
          page
        );
      } else {
        // 검색어가 없는 경우
        await this.reviewStore.getReviewList(
          this.reviewCategoryIdx,
          this.sortType,
          page
        );
      }
    },
    sendSearchData() {
      this.loadReviewList(1); // 검색 실행 시 첫 페이지로 돌아감.
    },
    changePage(page) {
      this.loadReviewList(page);
    },
    jumpForward() {
      // 현재 페이지에서 3페이지 앞으로 점프
      let nextPage = Math.min(
        this.reviewStore.currentPage + 3,
        this.reviewStore.totalPages
      );
      this.changePage(nextPage);

      this.reviewStore.currentPage = nextPage;
    },
  },
};
</script>

<style scoped>
body,
html {
  padding: 0;
  margin: 0;
  line-height: 1.5;
  font-family: Pretendard, serif;
}
body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
}
div {
  display: block;
}
* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}
* {
  margin: 0;
  line-height: 1.5;
}

.css-1hnxdb7 {
}
.css-mbwamd {
  width: 100%;
  background-color: rgb(255, 255, 255);
}

.css-1b9to7p {
  width: 1100px;
  padding-top: 150px;
  padding-bottom: 100px;
  margin: 0px auto;
  display: flex;
  flex-direction: row;
  background-color: rgb(255, 255, 255);
  gap: 80px;
}

.css-vsssfb {
  display: flex;
  flex-direction: column;
  gap: 32px;
  position: sticky;
  top: 258x;
  max-height: 490px;
  transition: all 0.4s ease 0s;
}
.css-110bgim {
  width: 180px;
  display: flex;
  flex-direction: column;
  gap: 16px;
  background-color: rgb(255, 255, 255);
  flex-shrink: 0;
  margin-top: 100px;
}
.css-28nsux {
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
}
.css-19831hi {
  width: 100%;
  border-radius: 8px;
  padding: 0px 16px;
  /* 클릭하면 배경이 아래 색상으로 변경됨 */
  background-color: white;
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 16px;
  line-height: 48px;
  font-weight: 400;
  color: rgb(157, 167, 174);
  cursor: pointer;
}
.css-19831hi:hover {
  background-color: rgb(242, 246, 248);
}
.css-19831his {
  width: 100%;
  padding: 0px 16px;
  /* 클릭하면 배경이 아래 색상으로 변경됨 */
  background-color: white;
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 16px;
  line-height: 48px;
  font-weight: 700;
  color: rgb(20, 22, 23);
  cursor: pointer;
  border-bottom: 1px solid rgb(228, 235, 240);
}
.css-nw8p9d {
  width: 100%;
  border-radius: 8px;
  padding: 0px 6px;
  background-color: rgb(255, 255, 255);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 16px;
  line-height: 48px;
  font-weight: 400;
  color: rgb(157, 167, 174);
  cursor: pointer;
}

.css-17ta9kq {
  width: 100%;
  height: 1px;
  background-color: rgb(228, 235, 240);
  margin: 8px 0px;
}
.css-16bft0y {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.css-804v49 {
  width: 100%;
  height: 48px;
  background-color: rgb(84, 29, 112);
  border-radius: 8px;
  text-align: center;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  color: rgb(255, 255, 255);
  line-height: 48px;
  cursor: pointer;
}
.css-19klq5c {
  z-index: 1;
  position: absolute;
  left: 50%;
  bottom: 0px;
  transform: translate(-50%, 82%);
  width: 135px;
  border: 1px solid rgb(84, 29, 112);
  border-radius: 8px;
  background-color: white;
  text-align: center;
  word-break: keep-all;
  padding: 6px 10px;
  color: rgb(84, 29, 112);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  line-height: 150%;
  animation: 1s ease 0s infinite normal none running animation-p418qx;
  opacity: 0;
  visibility: hidden;
  transition: all 0.2s ease-in-out 0s;
}
.css-33zep3 {
  width: 100%;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
}
.css-17t7asl {
  /* width: 924px; */
  display: none;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
}
.css-1jibmi3 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  gap: 4px;
}
.css-1mpmq0i {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4px;
  position: relative;
  padding-left: 16px;
  margin-bottom: 10px;
}
.css-18vdxik {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 30px;
  line-height: 34px;
  color: #141617;
  line-height: 33px;
  color: #141617;
}
.css-1qzbd5x {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 22px;
  color: #141617;
  line-height: 21px;
  color: #9da7ae;
  padding-left: 16px;
}
.css-1rw3qt4 {
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: rgb(246, 249, 250);
  margin-bottom: 50px;
}
.css-6cwwok {
  background-color: rgb(255, 255, 255);
  width: 100%;
}
.css-1hbxc4s {
  display: flex;
  flex-direction: row;
  gap: 12px;
  padding: 12px 16px;
  padding-right: 0px;
  margin-bottom: 20px;
  align-items: center;
  position: relative;
}
.css-ogh6wd {
  display: flex;
  flex-direction: row;
  gap: 6px;
  overflow-x: auto;
  white-space: nowrap;
}
.css-1tttep5 {
  position: relative;
  display: flex;
}

.css-5ala5m {
  display: flex;
  flex-direction: row;
  gap: 4px;
  flex-shrink: 0;
  width: fit-content;
}

.css-bw1gsw {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 13px;
  line-height: 160%;
  flex-shrink: 0;
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: rgb(84, 29, 112);
}
.css-ogh6wd {
  display: flex;
  flex-direction: row;
  gap: 50px;
  overflow-x: auto;
  white-space: nowrap;
}
svg:not(:root) {
  overflow-clip-margin: content-box;
  overflow: hidden;
}
:not(svg) {
  transform-origin: 0px 0px;
}
.css-g70uuw {
  width: 90px;
  height: 30px;
  background-color: rgb(255, 255, 255);
  flex-shrink: 0;
}

/* 글 작성 버튼 추가 */
.css-1pbcmmt-001 {
  display: flex;
  flex-direction: row;
  padding: 8px 12px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 10px;
  background: #541d7a;
  flex: 0 0 auto;
}

.css-5ala5m-001 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  flex-shrink: 1;
  width: fit-content;
  align-items: center;
}

.css-nmdn6a-001 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 13px;
  line-height: 160%;
  color: #fff;
  flex-shrink: 0;
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  cursor: pointer;
}
/* 글 작성 버튼 끝 */

/* 검색창 */
.css-1pbcmmt-002 {
  display: flex;
  flex-direction: row;
  padding: 8px 12px;
  justify-content: center;
  align-items: center;
  gap: 4px;
  border-radius: 100px;
  background: rgb(255, 255, 255);
  border: 1px solid rgb(227, 227, 227);
  flex: 0 0 auto;
  margin-left: 20px;
  margin-right: 20px;
  box-shadow: 2px, 4px, 8px rgb(0, 0, 0, 0.1);
}

.css-5ala5m-002 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  flex-shrink: 1;
  /* width: 350px; */
  width: 480px;
  align-items: center;
  text-align: center;
  justify-content: center;
}

.css-nmdn6a-002 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 13px;
  line-height: 160%;
  color: #fff;
  flex-shrink: 0;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 420px;
  align-items: center;
}

.css-search-002 {
  width: 420px;
  outline: none;
  border: none;
}

.css-search-img {
  display: flex;
  width: 15px;
  height: 15px;
  cursor: pointer;
}
/* 검색창 끝 */

.css-1csvk83 {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
  background-color: rgb(246, 249, 250);
}
@media (min-width: 820px) {
  .css-1csvk83 {
    background-color: rgb(255, 255, 255);
  }
}
.css-k59gj9 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  width: 100%;
}
@media (min-width: 820px) {
  .css-aw1sgr {
    padding: 24px 0px;
    border-bottom: 1px solid rgb(228, 235, 240);
    gap: 14px;
    cursor: pointer;
  }
}
.css-aw1sgr {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 24px 16px;
  background-color: rgb(255, 255, 255);
  gap: 16px;
}
.css-amlmv6 {
  width: 100%;
  display: none;
  flex-direction: row;
  -webkit-box-pack: start;
  justify-content: start;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}
@media (min-width: 820px) {
  .css-amlmv6 {
    display: none;
  }
}
.css-1254q6y {
  display: inline-flex;
  -webkit-box-pack: start;
  justify-content: start;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  padding: 2px 12px 0px;
  border-radius: 40px;
  height: 28px;
  font-size: 12px;
  font-weight: 500;
  line-height: 1;
  color: rgb(58, 62, 65);
  background-color: rgb(238, 243, 246);
}

@media (min-width: 1024px) {
  .css-1254q6y {
    margin-left: 8px;
  }
}

.css-kem115 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  gap: 14px;
}
.css-12i5occ {
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
}
@media (min-width: 820px) {
  .css-12i5occ {
    gap: 4px;
    width: 100%;
  }
}
.css-1jibmi3 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  gap: 4px;
}
.css-cp47oo {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  word-break: break-all;
  font-family: Pretendard;
  font-style: normal;
  line-height: 21px;
  color: rgb(20, 22, 23);
  font-size: 14px;
  font-weight: 600;
}

@media (min-width: 820px) {
  .css-cp47oo {
    font-family: Pretendard;
    font-style: normal;
    line-height: 24px;
    color: rgb(20, 22, 23);
    max-width: 83%;
    font-size: 16px;
    font-weight: 600;
  }
}
@media (min-width: 820px) {
  .css-14bssip {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 21px;
    color: rgb(95, 102, 107);
  }
}

.css-14bssip {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 21px;
  color: rgb(95, 102, 107);
  word-break: keep-all;
  height: 42px;
}
.css-sebsp7 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  height: 18px;
}
.css-bt1qy {
  display: flex;
  flex-direction: row;
  gap: 16px;
  height: 46px;
  -webkit-box-align: start;
  align-items: start;
}
.css-99cwur {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  height: 24px;
  margin-right: 20px;
}
.css-1fhge30 {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}
.css-aw18wm {
  width: 24px;
  height: 24px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}
.css-5zcuov {
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
}
.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
.css-1tify6w {
  width: 2px;
  height: 2px;
  display: flex;
}
.css-1ry6usa {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
}

@media (min-width: 820px) {
  .css-o01lup {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    gap: 2px;
  }
}
.css-o01lup {
  display: none;
}
.css-ts29it {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
}
.css-1ry6usa {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
}
.css-1ry6usatag {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(84, 29, 112);
}
.css-dbc8ke {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
  margin: 0px 4px;
}

@media (min-width: 820px) {
  .css-1vkj2s1 {
    display: none;
  }
}
.css-1vkj2s1 {
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  display: flex;
}
.css-z2xt5y {
  width: 100%;
  display: none;
  flex-direction: row;
  -webkit-box-pack: end;
  justify-content: end;
  -webkit-box-align: center;
  align-items: center;
}

@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}
.css-k9ergi {
  width: 100%;
  height: 42px;
  background-color: rgb(255, 255, 255);
  border-top: 1px solid rgb(228, 235, 240);
  border-bottom: none;
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding: 0px 24px;
}
.css-192oc4s {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 96px;
  height: 42px;
}
.css-ts29it {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
}
.css-hr47l6 {
  width: 16px;
  height: 16px;
}
.css-i21m7n {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(180, 191, 198);
}
@media (min-width: 820px) {
  .css-17t7asl {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    margin-bottom: 24px;
    gap: 24px;
  }
}
@media (min-width: 820px) {
  .css-k9ergi {
    display: none;
  }
}
@media (min-width: 820px) {
  .css-o01lup {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    gap: 2px;
  }
}

@media (min-width: 820px) {
  .css-1vkj2s1 {
    display: none;
  }
}
@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}

ol,
ul {
  list-style: none;
}

.css-10c0kk0 {
  width: 100%;
  grid-gap: 1rem;
  grid-template-columns: repeat(3, minmax(15rem, 1fr));
  grid-auto-flow: dense row;
  align-items: center;
  justify-content: space-around;
  position: relative;
  padding-left: 0px;
}

.css-1myomkm {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 100px;
  background-color: #fff;
  border-radius: 20px;
  gap: 0.5rem;
  padding: 12px;
  /* border: 2px solid rgb(84, 29, 112, 0.3); */
  background-color: rgb(84, 29, 112, 0.1);
}

a {
  cursor: pointer;
  text-decoration: none;
  font-family: Pretendard;
}

.css-1myomkm h4 {
  font-family: Pretendard;
  font-size: 0.75rem;
  font-weight: 700;
  color: rgb(255, 158, 45);
  margin-bottom: 0.25rem;
}

.css-1myomkm h2 {
  overflow: hidden;
  text-overflow: ellipsis;
  justify-content: flex-start;
  color: rgb(0, 0, 0);
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 700;
  line-height: 8px;
  opacity: 1;
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  min-height: 2.5rem;
  word-break: keep-all;
}

.css-1myomkm h3 {
  color: #777777;
  font-family: Pretendard;
  font-weight: 400;
  line-height: 140%;
  opacity: 1;
  display: -webkit-box;
  font-size: 13px;
  text-overflow: ellipsis;
  overflow: hidden;
  word-break: keep-all;
  height: 70px;
}

.css-k57yxr {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 0.5rem;
  margin: 0.5rem 0px;
}

.css-k57yxr img {
  width: 22px;
  height: 22px;
  border-radius: 50%;
  background-color: white;
  overflow: hidden;
  object-fit: contain;
  flex-shrink: 0;
}

.css-k57yxr > p {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 0.125rem;
  width: calc(100% - 2.25rem);
}

.css-1bf50wt {
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  color: rgb(0, 0, 0);
  font-family: Pretendard;
  font-size: 0.89rem;
  font-weight: 400;
  line-height: 120%;
  font-size: 11px;
  opacity: 1;
  width: 100%;
}

.css-1mmbkao {
  color: rgb(119, 119, 119);
  font-family: Pretendard;
  font-size: 10px;
  font-weight: 400;
  line-height: 120%;
  opacity: 1;
}

.css-k57yxr > p {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 0.125rem;
  width: calc(100% - 2.25rem);
}

.css-k57yxr {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 0.5rem;
  margin: 0.5rem 0px;
}

.css-1k3qs23 {
  display: flex;
  justify-content: flex-start;
  gap: 0.5rem;
  padding-left: 0px;
  align-items: center;
  justify-content: baseline;
}

.css-17j46fn {
  display: flex;
  justify-content: flex-start;
  /* color: rgb(38, 55, 71); */
  color: #a3a3a3;
  font-family: Pretendard;
  font-size: 10px;
  font-weight: 400;
  line-height: 120%;
  opacity: 1;
  align-items: center;
  gap: 0.2rem;
}

/* li{
      letter-spacing: -0.009em;
      font-family: Pretendard;
      line-height: 1.6;
      list-style: none;
  } */

.css-li-001 {
  letter-spacing: -0.009em;
  font-family: Pretendard;
  line-height: 1.6;
  list-style: none;
}

.css-li-001:hover {
  border: 2px solid rgb(84, 29, 112, 0.3);
  border-radius: 20px;
}
/* 스터디 포스트잇 끝 */

/* 정렬 순서 셀렉터 */

.css-select001 {
  padding-left: 10px;
  width: 95px;
  font-size: 14px;
  height: 35px;
  font-family: Pretendard;
  border-radius: 5px;
  border: 1px solid rgb(227, 227, 227);
  letter-spacing: 2px; /* 글자 간격 추가 */
}

/* 정렬 순서 끝 */

.css-6ylcwl {
  max-width: 942px;
  width: 100%;
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  padding: 12px 16px;
  background-color: rgb(255, 255, 255);
  margin: 0px auto;
}

@media (min-width: 1024px) {
  .css-6ylcwl {
    padding: 10px 17px 30px;
  }
}
.css-f7kuwm {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: start;
  justify-content: flex-start;
  -webkit-box-align: center;
  align-items: center;
  gap: 12px;
}

.css-1o94c7r {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 30px;
  background-color: #ffffff;
}

@media (min-width: 820px) {
  .css-1o94c7r {
    width: 100%;
  }
}

.css-1kb98ja {
  cursor: pointer;
  display: flex;
  gap: 4px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  color: rgb(20, 22, 23);
  line-height: 18px;
  -webkit-box-align: center;
  align-items: center;
}

.css-1619ajl {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 16px;
  line-height: 18px;
  color: rgb(20, 22, 23);
  border-bottom: 1px solid black;
}

@media (min-width: 820px) {
  .css-1619ajl {
    font-family: Pretendard;
    font-style: normal;
    font-weight: bold;
    font-size: 16px;
    line-height: 19.5px;
    color: rgb(20, 22, 23);
    border-bottom: 1px solid black;
  }
}

.css-bewb21 {
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  gap: 4px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #141617;
  line-height: 18px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  color: #3a3e41;
}

.css-1j5hzn7 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;
  color: #141617;
  line-height: 18px;
  color: #81898f;
}

@media (min-width: 820px) {
  .css-1j5hzn7 {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    line-height: 20px;
    color: #141617;
    line-height: 19.5px;
    color: #81898f;
  }
}
.css-select000 {
  padding-left: 5px;
}
.d-flex {
  display: flex !important;
}
.justify-content-center {
  justify-content: center !important;
}
@media (min-width: 768px) {
  .pt-md-4,
  .py-md-4 {
    padding-top: 1.5rem !important;
  }
}
@media (min-width: 768px) {
  .pb-md-4,
  .py-md-4 {
    padding-bottom: 1.5rem !important;
  }
}
/* 검색 결과 없을 떄 */

.css-6g4q8b {
  width: 100%;
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  min-height: 400px;
  background-color: white;
  margin-bottom: 50px;
}
.css-aa80it {
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  gap: 16px;
}
img {
  image-rendering: -moz-crisp-edges;
  image-rendering: -o-crisp-edges;
  image-rendering: -webkit-optimize-contrast;
  -ms-interpolation-mode: nearest-neighbor;
}
.css-1baht8c {
  width: 160px;
  height: 88px;
}
.css-dhqp8i {
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  gap: 6px;
  text-align: center;
}
.css-c7zvxr {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 20px;
  color: rgb(28, 29, 30);
}
.css-1mcux1f {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 20px;
  color: rgb(131, 134, 137);
  white-space: pre-wrap;
}
/*--------로딩창-------------*/
.loadingio-spinner-spinner {
  position: fixed;
  top: 0;
  left: 0%;
  z-index: 1000; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  width: 100%; /* 전체 화면을 커버 */
  height: 100%;
  justify-content: center; /* 가운데 정렬 */
  align-items: center; /* 세로 가운데 정렬 */
}
@keyframes ldio-f4nnk2ltl0v {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.ldio-f4nnk2ltl0v div {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  animation: ldio-f4nnk2ltl0v linear 1s infinite;
  background: #fe718d;
  width: 18.240000000000002px;
  height: 36.480000000000004px;
  border-radius: 9.120000000000001px / 18.240000000000002px;
  transform-origin: 9.120000000000001px 79.04px;
}
.ldio-f4nnk2ltl0v div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -0.9166666666666666s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -0.8333333333333334s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.75s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.6666666666666666s;
  background: #abbd81;
}
.ldio-f4nnk2ltl0v div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.5833333333333334s;
  background: #849b87;
}
.ldio-f4nnk2ltl0v div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.5s;
  background: #6492ac;
}
.ldio-f4nnk2ltl0v div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.4166666666666667s;
  background: #637cb5;
}
.ldio-f4nnk2ltl0v div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.3333333333333333s;
  background: #6a63b6;
}
.ldio-f4nnk2ltl0v div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.25s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.16666666666666666s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.08333333333333333s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
  background: #abbd81;
}
.loadingio-spinner-spinner-pz89b3jiaad {
  width: 304px;
  height: 304px;
  display: inline-block;
  overflow: hidden;
  background: #ffffff;
}
.ldio-f4nnk2ltl0v div {
  box-sizing: content-box;
}
</style>
