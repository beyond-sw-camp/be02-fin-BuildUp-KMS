<template>
  <div id="__next">
    <div class="css-1hnxdb7">
      <div class="css-mbwamd">
        <div class="css-1b9to7p">
          <div class="css-vsssfb">
            <div class="css-110bgim">
              <div class="css-28nsux">
                <div class="css-titlegms">계정 설정</div>
                <a href="/profile">
                  <div class="css-nw8p9d">프로필</div>
                </a>
                <div class="css-19831hi">나의 활동</div>
              </div>
            </div>
          </div>
          <div class="css-1rw3qt4">
            <div class="css-vmaf1p">
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
                  >
                    <img
                      :src="userStore.user.profileImage"
                      decoding="async"
                      data-nimg="fill"
                      sizes="100vw"
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
                    />
                  </span>
                </div>
                <div class="css-5zcuov">
                  <div class="css-1sika4i">{{ userStore.user.nickName }}</div>
                </div>
              </div>
            </div>
            <div class="css-164vtnj">
              <div
                class="css-cqjiz9"
                :class="{ 'css-2120cz': currentTab === '작성글' }"
                @click="changeTab('작성글')"
              >
                작성글
              </div>
              <div
                class="css-cqjiz9"
                :class="{ 'css-2120cz': currentTab === '스크랩' }"
                @click="changeTab('스크랩')"
              >
                스크랩
              </div>
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
            <div class="css-6ylcwl">
              <div class="css-1o94c7r" v-if="currentTab === '작성글'">
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
                        selectedTotalType === 'knowledge'
                          ? '#e8344e'
                          : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'knowledge'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('knowledge')"
                  >
                    지식 공유
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
                        selectedTotalType === 'qna' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'qna'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('qna')"
                  >
                    QnA
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
                        selectedTotalType === 'study' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'study'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('study')"
                  >
                    스터디
                  </div>
                </div>
                <div class="css-1kb98ja" v-if="userRole === 'ROLE_AUTHUSER'">
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
                        selectedTotalType === 'course' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1619ajl">과정 후기</div> -->
                  <div
                    v-if="userRole === 'ROLE_AUTHUSER'"
                    :class="
                      selectedTotalType === 'course'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('course')"
                  >
                    과정 후기
                  </div>
                </div>
                <div class="css-bewb21" v-if="userRole === 'ROLE_AUTHUSER'">
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
                        selectedTotalType === 'instructor'
                          ? '#e8344e'
                          : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    v-if="userRole === 'ROLE_AUTHUSER'"
                    :class="
                      selectedTotalType === 'instructor'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('instructor')"
                  >
                    강사 후기
                  </div>
                </div>
              </div>
              <div class="css-1o94c7r" v-if="currentTab === '스크랩'">
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
                        selectedTotalType === 'knowledge'
                          ? '#e8344e'
                          : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'knowledge'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('knowledge')"
                  >
                    지식 공유
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
                        selectedTotalType === 'qna' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'qna'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('qna')"
                  >
                    QnA
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
                        selectedTotalType === 'study' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'study'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('study')"
                  >
                    스터디
                  </div>
                </div>
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
                        selectedTotalType === 'course' ? '#e8344e' : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1619ajl">과정 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'course'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('course')"
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
                        selectedTotalType === 'instructor'
                          ? '#e8344e'
                          : '#B4BFC6'
                      "
                    ></circle>
                  </svg>
                  <!-- <div class="css-1j5hzn7">강사 후기</div> -->
                  <div
                    :class="
                      selectedTotalType === 'instructor'
                        ? 'css-1619ajl'
                        : 'css-1j5hzn7'
                    "
                    @click="selectTotalType('instructor')"
                  >
                    강사 후기
                  </div>
                </div>
              </div>
              <!-----기존 버전------->
            </div>
            <div class="css-1csvk83">
              <ul class="css-10c0kk0">
                <li
                  class="css-k59gj9"
                  v-for="totals in totalStore.totalList"
                  :key="totals.idx"
                >
                  <!-- Render the corresponding component based on the currentTab -->
                  <MyPageBoardComponent :totals="totals" />
                </li>
              </ul>
              <div class="css-6g4q8b" v-show="!totalStore.isBoardExist">
                <div class="css-aa80it">
                  <img src="@/assets/img/002.png" class="css-1baht8c" />
                  <div class="css-dhqp8i">
                    <div class="css-c7zvxr">해당되는 게시글이 없습니다.</div>
                    <div class="css-1mcux1f">
                      질문을 직접 작성하거나, 관심글을 스크랩 해보세요!
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--페이지네이션 자리-->
            <div class="d-flex justify-content-center py-0 py-md-4">
              <PaginationComponent
                :current-page="totalStore.currentPage"
                :total-pages="totalStore.totalPages"
                :isPageExist="totalStore.isPageExist"
                @change-page="changePage"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--푸터자리-->
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useUserStore } from "@/stores/useUserStore";
import { useBoardStore } from "@/stores/useBoardStore";
import { useTotalStore } from "../stores/useTotalStore";
import MyPageBoardComponent from "@/components/MyPageBoardComponent.vue";
import PaginationComponent from "@/components/PaginationComponent.vue";
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "MyPage",
  data() {
    return {
      userRole: "ROLE_USER",
      selectedSortType: "최신순",
      sortType: 1,
      selectedTotalType: "knowledge",
      totalCategoryIdx: "1",
      user: {},
      currentTab: "작성글",
    };
  },
  computed: {
    ...mapStores(useUserStore, useBoardStore, useTotalStore),
  },
  components: {
    MyPageBoardComponent,
    PaginationComponent,
  },
  mounted() {
    this.loadTotalList(1);
    this.userStore.getUserInfo();
    this.checkRole();
  },
  methods: {
    checkRole() {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        const role = VueJwtDecode.decode(accessToken).ROLE;
        this.userRole = role;
      }
    },
    async changeTab(tab) {
      this.currentTab = tab;
      this.selectedTotalType = "knowledge";
      this.loadTotalList(1);
    },

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
      this.loadTotalList(this.totalStore.currentPage);
    },
    selectTotalType(type) {
      this.selectedTotalType = type;
      const totalTypeToIdx = {
        course: "1",
        instructor: "2",
        knowledge: "1",
        qna: "2",
        study: "3",
      };
      // 전달된 타입에 해당하는 값을 가져와서 selectedTotalType에 할당합니다.
      this.totalCategoryIdx = totalTypeToIdx[type] || "1";
      this.loadTotalList(1); // 선택된 타입에 따라 리스트를 로드합니다..
    },

    loadTotalList(page) {
      if (this.currentTab === "작성글") {
        if (
          this.selectedTotalType === "course" ||
          this.selectedTotalType === "instructor"
        ) {
          this.totalStore.getMyReviewList(
            this.totalCategoryIdx,
            this.sortType,
            page
          );
        } else {
          this.totalStore.findMyBoardListByCategory(
            this.totalCategoryIdx,
            this.sortType,
            page
          );
        }
      } else {
        if (
          this.selectedTotalType === "course" ||
          this.selectedTotalType === "instructor"
        ) {
          this.totalStore.findReviewScrapListByCategory(
            this.totalCategoryIdx,
            this.sortType,
            page
          );
        } else {
          this.totalStore.findBoardScrapListByCategory(
            this.totalCategoryIdx,
            this.sortType,
            page
          );
        }
      }
    },

    changePage(page) {
      this.loadTotalList(page);
    },
    jumpForward() {
      // 현재 페이지에서 3페이지 앞으로 점프
      let nextPage = Math.min(
        this.totalStore.currentPage + 3,
        this.totalStore.totalPages
      );
      this.changePage(nextPage);

      this.totalStore.currentPage = nextPage;
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
}
.css-mbwamd {
  width: 100%;
  background-color: rgb(255, 255, 255);
  display: none;
}
@media (min-width: 820px) {
  .css-mbwamd {
    display: flex;
  }
}
.css-1b9to7p {
  width: 942px;
  padding-top: 60px;
  margin: 0px auto;
  display: flex;
  flex-direction: row;
  background-color: rgb(255, 255, 255);
  gap: 60px;
}
.css-1rw3qt4 {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  margin-top: 95px;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  background-color: #f6f9fa;
  border: 0.1rem solid rgb(221, 222, 228);
  border-radius: 1.5rem;
  padding: 4rem 4.8rem;
  margin-bottom: 4rem;
}
@media (min-width: 820px) {
  .css-1rw3qt4 {
    gap: 16px;
    background-color: #ffffff;
    min-height: 800px;
  }
}
.css-vmaf1p {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 12px;
  padding: 24px 16px;
  background-color: #ffffff;
}
@media (min-width: 820px) {
  .css-vmaf1p {
    padding: 0;
  }
}
.css-1fhge30 {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}
.css-aw18wm {
  width: 100px;
  height: 100px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}
.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  -webkit-box-align: center;
  align-items: center;
}
.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 18px;
  color: rgb(58, 62, 65);
  padding-left: 10px;
}
.css-164vtnj {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  height: 52px;
  background-color: #ffffff;
}
@media (min-width: 820px) {
  .css-164vtnj {
    border-bottom: 1px solid #e4ebf0;
    gap: 18px;
  }
}
.css-cqjiz9 {
  width: 50%;
  height: 52px;
  border-bottom: none;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  line-height: 52px;
  text-align: center;
  color: #9da7ae;
}
@media (min-width: 820px) {
  .css-cqjiz9 {
    width: 57px;
    cursor: pointer;
  }
}
.css-2120cz {
  width: 50%;
  height: 52px;
  border-bottom: 2px solid #3a3e41;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  line-height: 52px;
  text-align: center;
  color: #3a3e41;
}
@media (min-width: 820px) {
  .css-2120cz {
    width: 57px;
    cursor: pointer;
  }
}
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
.css-vsssfb {
  display: flex;
  flex-direction: column;
  gap: 32px;
  position: sticky;
  top: 10rem;
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
  padding: 0px 50px 0px 16px;
  background-color: rgba(242, 246, 248, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 23px;
  line-height: 48px;
  font-weight: 700;
  color: rgb(84, 29, 112);
  cursor: pointer;
  text-align: right;
}
.css-nw8p9d {
  width: 100%;
  border-radius: 8px;
  padding: 0px 50px 0px 16px;
  background-color: rgba(255, 255, 255, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 23px;
  line-height: 48px;
  font-weight: 700;
  color: rgba(157, 167, 174, 0.758);
  cursor: pointer;
  text-align: right;
}
.css-titlegms {
  width: 100%;
  border-radius: 8px;
  padding: 0px 16px;
  background-color: rgba(255, 255, 255, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 32px;
  line-height: 48px;
  font-weight: 400;
  color: rgba(0, 0, 0, 0.905);
  font-weight: bold;
  cursor: pointer;
}
*,
::after,
::before {
  box-sizing: border-box;
}
* {
  margin: 0;
  line-height: 1.5;
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
.css-select000 {
  padding-top: 11px;
  margin-left: 290px;
  padding-left: 5px;
}
.css-select001 {
  padding-left: 10px;
  width: 95px;
  font-weight: 500;
  color: #747c82;
  font-size: 14px;
  height: 25px;
  font-family: Pretendard;
  border-radius: 5px;
  border: 1px solid rgb(227, 227, 227);
  letter-spacing: 2px; /* 글자 간격 추가 */
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
.total-selection {
  display: flex;
}

.total-type {
  font-family: Pretendard;
  padding-left: 3px;
  padding-right: 30px;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
}

.selected {
  color: rgb(150, 48, 202);
}
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
</style>
