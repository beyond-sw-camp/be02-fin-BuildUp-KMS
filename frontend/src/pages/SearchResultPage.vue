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
  <section class="TkhYW1 _1etXfM">
    <div class="_2kpZbi">
      <div>
        <div class="d-none d-lg-block container-lg">
          <div class="_1anRgx _17fGk0 _2zXJMj" style="--box-gap: 2rem">
            <section class="bs_2vg flex-shrink-0">
              <div class="_1anRgx _2EZ57H" style="--box-gap: 1.25rem">
                <div class="bg-gray-050 card">
                  <div class="card-body">
                    <h6 class="_3ZcrIA text-center mb-0">
                      <span>질문과 답변을 주고 받으며 함께 성장하세요!</span>
                    </h6>
                    <router-link to="/review/new">
                      <a class="_2-sdMj btn btn-primary btn-lg btn-block">
                        <div class="_2pYHs _1isiv">
                          <svg
                            fill="currentColor"
                            width="16"
                            height="16"
                            viewBox="0 0 16 16"
                            xmlns="http://www.w3.org/2000/svg"
                            class="_1CkmD _1vN1F"
                          >
                            <path
                              fill-rule="evenodd"
                              clip-rule="evenodd"
                              d="M13.0498 7.3501V8.6501H8.65083V13.0501H7.34983V8.6501H2.94983V7.3501H7.34983V2.9501H8.65083V7.3501H13.0498Z"
                            ></path>
                          </svg>
                          <span>새 후기글 작성</span>
                        </div>
                      </a>
                    </router-link>
                  </div>
                </div>
              </div>
            </section>
            <section class="flex-grow-1">
              <div class="overflow-hidden">
                <div>
                  <div
                    class="_2kqp41 _2d5D_m _28qC73"
                    style="--box-gap: 0.75rem"
                  >
                    <button type="button" class="_3Jflz btn btn-link btn-xl">
                      <div class="_2pYHs ODppI">
                        <svg
                          fill="currentColor"
                          width="16"
                          height="16"
                          viewBox="0 0 16 16"
                          xmlns="http://www.w3.org/2000/svg"
                          class="_1CkmD _1S27j"
                        >
                          <path
                            fill-rule="evenodd"
                            clip-rule="evenodd"
                            d="M8 1.5L1.5 8L8 14.5L9.149 13.349L4.612 8.812H14.5V7.187H4.612L9.149 2.649L8 1.5Z"
                          ></path>
                        </svg>
                      </div>
                    </button>
                    <div class="_1YCmw flex-grow-1">
                      <input
                        type="text"
                        value=""
                        placeholder="제목, 내용으로 후기글을 찾아 보세요!"
                        v-model="query"
                        @keyup.enter="getSearchSortReviewList()"
                        class="FMUyj _1LD4c form-control-xl form-control"
                      /><svg
                        fill="currentColor"
                        width="16"
                        height="16"
                        viewBox="0 0 16 16"
                        xmlns="http://www.w3.org/2000/svg"
                        class="A-RPq _2vGEB"
                      >
                        <path
                          fill-rule="evenodd"
                          clip-rule="evenodd"
                          d="M11.46 10.54L10.54 11.46L8 8.92L5.46 11.46L4.54 10.54L7.08 8L4.54 5.46L5.46 4.54L8 7.08L10.54 4.54L11.46 5.46L8.92 8L11.46 10.54ZM8 1.5C4.412 1.5 1.5 4.412 1.5 8C1.5 11.588 4.412 14.5 8 14.5C11.588 14.5 14.5 11.588 14.5 8C14.5 4.412 11.588 1.5 8 1.5Z"
                        ></path>
                      </svg>
                    </div>
                    <button
                      type="button"
                      class="flex-shrink-0 btn btn-outline-basic btn-xl"
                      @click="getSearchSortReviewList()"
                    >
                      <div class="_2pYHs ODppI"><span>검색</span></div>
                    </button>
                  </div>
                </div>
                <div
                  class="d-flex justify-content-between mt-4 align-items-center"
                >
                  <h5 class="mb-0">
                    <div class="_2kqp41 _2d5D_m" style="--box-gap: 0.5rem">
                      <span><span>검색 결과</span></span
                      ><span class="text-blue-500">{{
                        reviewStore.totalCnt
                      }}</span>
                    </div>
                  </h5>
                  <div class="dropdown">
                    <button
                      @click="toggleDropdown"
                      type="button"
                      aria-haspopup="true"
                      :aria-expanded="dropdownOpen.toString()"
                      class="_1rMfp _3NZzgf btn btn-select btn-lg"
                    >
                      <span>{{ sortTypeTitle }}</span>
                      <svg
                        fill="currentColor"
                        width="16"
                        height="16"
                        viewBox="0 0 16 16"
                        xmlns="http://www.w3.org/2000/svg"
                        class="_2-Js2"
                      >
                        <path
                          fill-rule="evenodd"
                          clip-rule="evenodd"
                          d="M7.99997 11.04L3.29797 6.33801L4.21697 5.41901L7.99997 9.20201L11.783 5.41901L12.702 6.33801L7.99997 11.04Z"
                        ></path>
                      </svg>
                    </button>
                    <div
                      v-show="dropdownOpen"
                      tabindex="-1"
                      role="menu"
                      aria-hidden="false"
                      class="_20WMwO w-100 dropdown-menu"
                    >
                      <button
                        type="button"
                        tabindex="0"
                        role="menuitem"
                        :class="{
                          '_3CkPsH dropdown-item': true,
                          active: this.sortType === 1,
                        }"
                        @click="selectedSortType(1)"
                      >
                        <span>최신순</span>
                      </button>
                      <button
                        type="button"
                        tabindex="0"
                        role="menuitem"
                        :class="{
                          '_3CkPsH dropdown-item': true,
                          active: this.sortType === 2,
                        }"
                        @click="selectedSortType(2)"
                      >
                        <spa>추천순</spa>
                      </button>
                      <button
                        type="button"
                        tabindex="0"
                        role="menuitem"
                        :class="{
                          '_3CkPsH dropdown-item': true,
                          active: this.sortType === 3,
                        }"
                        @click="selectedSortType(3)"
                      >
                        <spa>조회순</spa>
                      </button>
                      <button
                        type="button"
                        tabindex="0"
                        role="menuitem"
                        :class="{
                          '_3CkPsH dropdown-item': true,
                          active: this.sortType === 4,
                        }"
                        @click="selectedSortType(4)"
                      >
                        <spa>스크랩순</spa>
                      </button>
                      <button
                        type="button"
                        tabindex="0"
                        role="menuitem"
                        :class="{
                          '_3CkPsH dropdown-item': true,
                          active: this.sortType === 5,
                        }"
                        @click="selectedSortType(5)"
                      >
                        <spa>댓글순</spa>
                      </button>
                    </div>
                  </div>
                </div>
                <ul class="_1PTI0R p-0 mb-4">
                  <span>
                    <div
                      v-for="review in reviewStore.reviewList"
                      :key="review.reviewIdx"
                    >
                      <li class="_3lQ639 _32Ay9Q" role="presentation">
                        <div
                          class="_2kqp41 flex-grow-1"
                          style="--box-gap: 0.75rem"
                        >
                          <div
                            class="_2kqp41 tttQ1F"
                            style="--box-gap: 0.375rem"
                          >
                            <router-link :to="`review/${review.reviewIdx}`">
                              <p class="_3yzn7O mb-0 _2Sv3IV">
                                {{ review.reviewTitle }}
                              </p>
                            </router-link>
                            <div class="_2U1dPx">
                              <nav class="" aria-label="breadcrumb">
                                <ol class="breadcrumb _3cKvl flex-nowrap">
                                  <li
                                    class="d-inline-flex align-items-center iFcHCl _1OTYG _2amVj _285DO active breadcrumb-item"
                                    aria-current="page"
                                  >
                                    <span class="_1t2_hP">{{
                                      review.reviewContent
                                    }}</span>
                                  </li>
                                </ol>
                              </nav>
                            </div>
                            <div>
                              <span class="d-none d-md-block">
                                <span
                                  class="d-flex Dhz06G align-items-center text-gray-600"
                                >
                                  <span
                                    class="text-gray-900 font-weight-normal flex-shrink-0"
                                    >{{ review.nickName }}</span
                                  >
                                  <span class="paragraph-sm">·</span>
                                  <span
                                    class="text-gray-600 flex-shrink-0"
                                    title="2024. 2. 17. 오후 7:25:30"
                                    >{{
                                      this.$moment.utc(review.createdAt).local().format(
                                        "YYYY-MM-DD HH:mm:ss"
                                      )
                                    }}
                                  </span>
                                </span>
                              </span>
                            </div>
                          </div>
                          <div class="d-flex justify-content-between">
                            <div class="d-flex align-items-center _1VWGjy">
                              <span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"
                                ><svg
                                  fill="currentColor"
                                  width="16"
                                  height="16"
                                  viewBox="0 0 16 16"
                                  xmlns="http://www.w3.org/2000/svg"
                                >
                                  <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M1 8.07129C3.24 4.73829 5.24 3.07129 8 3.07129C10.761 3.07129 12.761 4.73829 15 8.07129C12.761 11.4043 10.761 13.0713 8 13.0713C5.24 13.0713 3.24 11.4043 1 8.07129ZM8 12.0713C10.209 12.0713 12 10.2803 12 8.07129C12 5.86229 10.209 4.07129 8 4.07129C5.791 4.07129 4 5.86229 4 8.07129C4 10.2803 5.791 12.0713 8 12.0713ZM8 10.0713C6.896 10.0713 6 9.17629 6 8.07129C6 6.96729 6.896 6.07129 8 6.07129C9.104 6.07129 10 6.96729 10 8.07129C10 9.17629 9.104 10.0713 8 10.0713Z"
                                  ></path></svg
                                ><span class="paragraph-sm">{{
                                  review.viewCnt
                                }}</span></span
                              ><span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"
                                ><svg
                                  fill="currentColor"
                                  width="16"
                                  height="16"
                                  viewBox="0 0 16 16"
                                  xmlns="http://www.w3.org/2000/svg"
                                >
                                  <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M4 6.059H12V4.759H4V6.059ZM4 8.741H9.5V7.441H4V8.741ZM1.5 1.75V10.25V11.75V14.25L4 11.75H14.5V1.75H1.5Z"
                                  ></path></svg
                                ><span class="paragraph-sm">{{
                                  review.commentCnt
                                }}</span></span
                              ><span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"
                                ><svg
                                  fill="currentColor"
                                  width="16"
                                  height="16"
                                  viewBox="0 0 16 16"
                                  xmlns="http://www.w3.org/2000/svg"
                                >
                                  <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M9.95312 5.75011L10.4471 4.20511C10.7831 3.15311 10.4811 2.00111 9.67112 1.25011L5.30012 5.75011V13.7501H13.0001L15.0001 8.78811V5.75011H9.95312ZM1.50012 13.7501H4.00012V6.25011H1.50012V13.7501Z"
                                  ></path></svg
                                ><span class="paragraph-sm">{{
                                  review.upCnt
                                }}</span></span
                              >
                            </div>
                          </div>
                        </div>
                      </li>
                      <hr class="m-0 p-0" />
                    </div>
                  </span>
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
                </ul>
                <div class="d-flex justify-content-center py-0 py-md-4">
                  <PaginationComponent
                    :current-page="reviewStore.currentPage"
                    :total-pages="reviewStore.totalPages"
                    :isPageExist="reviewStore.isPageExist"
                    @change-page="changePage"
                  />
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
      <div class="Toastify"></div>
    </div>
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "/src/stores/useReviewStore";
import PaginationComponent from "@/components/PaginationComponent.vue";

export default {
  name: "SearchResultPage",
  components: {
    PaginationComponent,
  },
  data() {
    return {
      dropdownOpen: false,
      query: "",
      searchType: "",
      title: "",
      sortType: 1,
      sortTypeTitle: "최신순",
    };
  },
  async mounted() {
    const link = document.createElement("link");
    link.rel = "stylesheet";
    link.href =
      "https://statics.goorm.io/gds/goormstrap/v1.29.0/goormstrap.v4.min.css";
    link.id = "goormstrap-css";
    document.head.appendChild(link);

    // URL에서 쿼리 매개변수 읽음
    const query = this.$route.query.query || "";
    const searchType = this.$route.query.option || "";

    // 상태 업데이트
    this.query = query;
    this.searchType = searchType;

    // 후기글 목록 불러오기
    if (query !== null && query !== "") {
      await this.reviewStore.searchReviewListByQuery(searchType, query);

    } else {
      alert("검색할 내용을 입력해주세요.");
      this.$router.push("/");
    }
  },

  beforeUnmount() {
    // 동적으로 추가한 스타일시트를 ID로 찾아 제거
    const goormstrapLink = document.getElementById("goormstrap-css");
    if (goormstrapLink) {
      goormstrapLink.parentNode.removeChild(goormstrapLink);
    }
  },
  computed: {
    ...mapStores(useReviewStore),
  },
  methods: {
    toggleDropdown() {
      this.dropdownOpen = !this.dropdownOpen;
    },
    selectedSortType(sortType) {
      this.sortType = sortType;
      if (sortType === 1) {
        this.sortTypeTitle = "최신순";
      } else if (sortType === 2) {
        this.sortTypeTitle = "추천순";
      } else if (sortType === 3) {
        this.sortTypeTitle = "조회순";
      } else if (sortType === 4) {
        this.sortTypeTitle = "스크랩순";
      } else if (sortType === 5) {
        this.sortTypeTitle = "댓글순";
      }
      this.reviewStore.searchReviewListBySortType(
        this.searchType,
        this.sortType,
        this.query
      );
      this.dropdownOpen = false;
    },
    changePage(page) {
      this.reviewStore.searchReviewListBySortType(
        this.searchType,
        this.sortType,
        this.query,
        page
      );
    },
    jumpForward() {
      // 현재 페이지에서 3페이지 앞으로 점프
      let nextPage = Math.min(
        this.reviewStore.currentPage + 3,
        this.reviewStore.totalPages
      );
      this.changePage(nextPage);
      // visiblePages를 업데이트하기 위해 currentPage를 설정
      this.reviewStore.currentPage = nextPage;
    },
    getSearchSortReviewList() {
      this.searchType = 2;
      this.reviewStore.searchReviewListBySortType(
        this.searchType,
        this.sortType,
        this.query
      );
    },
  },
};
</script>

<style scoped>
:root {
  --toastify-color-light: #fff;
  --toastify-color-dark: #121212;
  --toastify-color-info: #3498db;
  --toastify-color-success: #07bc0c;
  --toastify-color-warning: #f1c40f;
  --toastify-color-error: #e74c3c;
  --toastify-color-transparent: hsla(0, 0%, 100%, 0.7);
  --toastify-icon-color-info: var(--toastify-color-info);
  --toastify-icon-color-success: var(--toastify-color-success);
  --toastify-icon-color-warning: var(--toastify-color-warning);
  --toastify-icon-color-error: var(--toastify-color-error);
  --toastify-toast-width: 320px;
  --toastify-toast-background: #fff;
  --toastify-toast-min-height: 64px;
  --toastify-toast-max-height: 800px;
  --toastify-font-family: sans-serif;
  --toastify-z-index: 9999;
  --toastify-text-color-light: #757575;
  --toastify-text-color-dark: #fff;
  --toastify-text-color-info: #fff;
  --toastify-text-color-success: #fff;
  --toastify-text-color-warning: #fff;
  --toastify-text-color-error: #fff;
  --toastify-spinner-color: #616161;
  --toastify-spinner-color-empty-area: #e0e0e0;
  --toastify-color-progress-light: linear-gradient(
    90deg,
    #4cd964,
    #5ac8fa,
    #007aff,
    #34aadc,
    #5856d6,
    #ff2d55
  );
  --toastify-color-progress-dark: #bb86fc;
  --toastify-color-progress-info: var(--toastify-color-info);
  --toastify-color-progress-success: var(--toastify-color-success);
  --toastify-color-progress-warning: var(--toastify-color-warning);
  --toastify-color-progress-error: var(--toastify-color-error);
}

._1Bbxo._1Bbxo {
  display: flex;
  flex-direction: column;
  align-items: end;
  width: unset;
  max-width: 35rem;
}

@media only screen and (max-width: 991px) {
  ._1Bbxo._1Bbxo {
    width: 100%;
    max-width: 35rem;
  }
}

@media only screen and (max-width: 480px) {
  ._1Bbxo._1Bbxo {
    margin: unset;
    padding: 1rem;
  }
}

._2kpZbi {
  padding-top: 8rem;
  padding-bottom: 5rem;
  background: var(--white);
}

*,
::after,
::before {
  box-sizing: border-box;
}

div {
  display: block;
}

._1etXfM {
  padding-bottom: 0;
  background-color: var(--white);
}

.TkhYW1 {
  position: relative;
  flex-grow: 1;
  flex-shrink: 1;
  padding-bottom: 5rem;
}

@media (min-width: 992px) {
  .container-lg {
    max-width: 940px;
  }
}

.container-lg {
  width: 100%;
  padding-right: 16px;
  padding-left: 16px;
  margin-right: auto;
  margin-left: auto;
}

element.style {
  --box-gap: 2rem;
}

._17fGk0 {
  flex-direction: row;
}

._1anRgx {
  display: flex;
  gap: var(--box-gap);
}

element.style {
  --box-gap: 2rem;
}

._17fGk0 {
  flex-direction: row;
}

._2zXJMj > section {
  min-width: 0;
}

.bs_2vg {
  width: 19.9rem;
}

._2EZ57H {
  position: sticky;
  top: 1.5rem;
}

._3ZcrIA {
  white-space: pre;
  word-break: keep-all;
}

._2-sdMj {
  margin-top: 1.25rem;
  margin-bottom: 1.25rem;
}

._1isiv,
.ODppI {
  grid-gap: var(--spacer-sm);
  gap: var(--spacer-sm);
}

._2pYHs {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-pack: center;
  justify-content: center;
  pointer-events: none;
}

._1vN1F {
  width: 1.25rem;
  height: 1.25rem;
}

._1CkmD {
  -ms-flex-negative: 0;
  flex-shrink: 0;
}

._2pYHs {
  pointer-events: none;
}

._2zXJMj > section {
  min-width: 0;
}

._2d5D_m {
  flex-direction: row;
}

._2kqp41 {
  display: flex;
  flex-direction: column;
  gap: var(--box-gap);
  min-width: 0;
}

.btn-xl._3Jflz {
  width: 3.25rem;
}

.btn-primary {
  color: var(--white);
  background-color: rgba(84, 29, 112);
  border-color: transparent;
}

.text-blue-500 {
  color: rgba(84, 29, 112) !important;
}

._1isiv,
.ODppI {
  grid-gap: var(--spacer-sm);
  gap: var(--spacer-sm);
}

._2pYHs {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-pack: center;
  justify-content: center;
  pointer-events: none;
}

._1S27j {
  width: 1.5rem;
  height: 1.5rem;
}

._1CkmD {
  -ms-flex-negative: 0;
  flex-shrink: 0;
}

._1YCmw {
  position: relative;
  display: flex;
  align-items: center;
}

._1LD4c {
  padding-left: 1.5rem;
  padding-right: 4rem;
}

.FMUyj {
  padding-left: 4rem;
}

._2vGEB {
  right: 1.5rem;
  width: 1.5rem;
  height: 1.5rem;
}

.A-RPq {
  position: absolute;
  cursor: pointer;
  color: var(--gray-600);
  width: 1rem;
  height: 1rem;
}

._1isiv,
.ODppI {
  grid-gap: var(--spacer-sm);
  gap: var(--spacer-sm);
}

._2pYHs {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-pack: center;
  justify-content: center;
  pointer-events: none;
}

._2d5D_m {
  flex-direction: row;
}

._1rMfp {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
}

._2-Js2 {
  margin-left: var(--spacer-xs, 0.25rem);
  display: inline-block;
  transition: transform 0.3s, -webkit-transform 0.3s;
}

._20WMwO {
  min-width: unset;
}

._1PTI0R {
  list-style: none;
}

ul {
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}

._1PTI0R {
  list-style: none;
}

div {
  display: block;
}

._1PTI0R {
  list-style: none;
}

._32Ay9Q {
  cursor: pointer;
}

._32Ay9Q {
  cursor: pointer;
}

._32Ay9Q {
  cursor: pointer;
}

li {
  text-align: -webkit-match-parent;
}

._3yzn7O {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: 500;
  font-size: 1.125rem;
  color: var(--gray-900);
}

._32Ay9Q {
  cursor: pointer;
}

._3lQ639 {
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  padding: 1.5rem 0;
  list-style: none;
}

.Dhz06G {
  gap: 0.25rem;
}

._2U1dPx {
  min-width: 0;
}

._3cKvl {
  padding: 0;
  margin-bottom: 0;
}

.iFcHCl {
  flex-grow: 1;
  min-width: 0;
  max-width: fit-content;
}

._2amVj {
  font-size: 0.875rem;
  line-height: 1.375rem;
}

._2amVj {
  font-size: 0.875rem;
  line-height: 1.375rem;
}

.iFcHCl {
  flex-grow: 1;
  min-width: 0;
}

._2amVj {
  font-size: 0.875rem;
  line-height: 1.375rem;
}

._1OTYG + ._1OTYG:before {
  width: 1rem;
  height: 1rem;
  transform: none;
}

._1t2_hP {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

._2amVj {
  font-size: 0.875rem;
  line-height: 1.375rem;
}

._285DO {
  cursor: pointer;
}

.Dhz06G {
  gap: 0.25rem;
}

._1VYQb4 {
  margin-bottom: 0px;
}

._2Hgkt3 {
  align-items: center;
  justify-content: center;
  width: 2.25rem;
  height: 2.25rem;
  background: rgba(0, 0, 0, 0);
  border: 0;
  color: #525463;
}

._22s-QT {
  gap: var(--spacer-xs);
}

._1VWGjy {
  gap: var(--spacer-sm);
}

.Dhz06G {
  gap: 0.25rem;
}

.align-items-center {
  align-items: center !important;
}

.h6,
.text-button-lg,
h6 {
  font-size: 1rem;
  font-weight: 500;
  line-height: 1.5rem;
  letter-spacing: -0.006rem;
}

svg:not(:root) {
  overflow-clip-margin: content-box;
  overflow: hidden;
}

._1VYQb4 li + li {
  margin-left: 0.25rem;
}

._1VYQb4 .active .page-link {
  background: rgba(80, 148, 250, 0.24);
}

._1VYQb4 {
  margin-bottom: 0px;
}

._2zXJMj > section {
  min-width: 0;
}

._17fGk0 {
  flex-direction: row;
}

@media (min-width: 992px) {
  .container-lg {
    max-width: 940px;
  }
}

.container-lg {
  width: 100%;
  padding-right: 16px;
  padding-left: 16px;
  margin-right: auto;
  margin-left: auto;
}

._3ZcrIA {
  white-space: pre;
  word-break: keep-all;
}

._2-sdMj {
  margin-top: 1.25rem;
}

._1isiv,
.ODppI {
  grid-gap: var(--spacer-sm);
  gap: var(--spacer-sm);
}

._2pYHs {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-pack: center;
  justify-content: center;
  pointer-events: none;
}

._1vN1F {
  width: 1.25rem;
  height: 1.25rem;
}

._1CkmD {
  -ms-flex-negative: 0;
  flex-shrink: 0;
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
</style>
