<template>
  <section class="TkhYW1  _1etXfM">
    <div class="_2kpZbi">
      <div>
        <div class="d-none d-lg-block container-lg">
          <div class="_1anRgx _17fGk0 _2zXJMj" style="--box-gap:2rem">
            <section class="bs_2vg flex-shrink-0">
              <div class="_1anRgx _2EZ57H" style="--box-gap:1.25rem">
                <div class="bg-gray-050 card">
                  <div class="card-body">
                    <h6 class="_3ZcrIA text-center mb-0">
                      <span>질문과 답변을 주고 받으며 함께 성장하세요!</span>
                    </h6>
                    <router-link to="/board/new">
                      <a class="_2-sdMj btn btn-primary btn-lg btn-block">
                        <div class="_2pYHs _1isiv"><svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16"
                            xmlns="http://www.w3.org/2000/svg" class="_1CkmD _1vN1F">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                              d="M13.0498 7.3501V8.6501H8.65083V13.0501H7.34983V8.6501H2.94983V7.3501H7.34983V2.9501H8.65083V7.3501H13.0498Z">
                            </path>
                          </svg>
                          <span>새 질문 작성</span>
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
                  <div class="_2kqp41 _2d5D_m _28qC73" style="--box-gap:0.75rem"><button type="button"
                      class="_3Jflz btn btn-link btn-xl">
                      <div class="_2pYHs ODppI"><svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16"
                          xmlns="http://www.w3.org/2000/svg" class="_1CkmD _1S27j">
                          <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M8 1.5L1.5 8L8 14.5L9.149 13.349L4.612 8.812H14.5V7.187H4.612L9.149 2.649L8 1.5Z">
                          </path>
                        </svg></div>
                    </button>
                    <div class="_1YCmw flex-grow-1"><input type="text" value="" placeholder="제목, 내용으로 질문을 찾아 보세요!"
                        class="FMUyj _1LD4c form-control-xl form-control"><svg fill="currentColor" width="16"
                        height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg" class="A-RPq _2vGEB">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M11.46 10.54L10.54 11.46L8 8.92L5.46 11.46L4.54 10.54L7.08 8L4.54 5.46L5.46 4.54L8 7.08L10.54 4.54L11.46 5.46L8.92 8L11.46 10.54ZM8 1.5C4.412 1.5 1.5 4.412 1.5 8C1.5 11.588 4.412 14.5 8 14.5C11.588 14.5 14.5 11.588 14.5 8C14.5 4.412 11.588 1.5 8 1.5Z">
                        </path>
                      </svg></div><button type="button" class="flex-shrink-0 btn btn-outline-basic btn-xl">
                      <div class="_2pYHs ODppI"><span>검색</span></div>
                    </button>
                  </div>
                </div>
                <div class="d-flex justify-content-between mt-4 align-items-center">
                  <h5 class="mb-0">
                    <div class="_2kqp41 _2d5D_m" style="--box-gap: 0.5rem;"><span><span>검색
                          결과</span></span><span class="text-blue-500 ">265</span></div>
                  </h5>
                  <div class="dropdown">
                    <button @click="toggleDropdown" type="button" aria-haspopup="true"
                      :aria-expanded="dropdownOpen.toString()" class="_1rMfp _3NZzgf btn btn-select btn-lg">
                      <span>최신순</span>
                      <svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16"
                        xmlns="http://www.w3.org/2000/svg" class="_2-Js2">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M7.99997 11.04L3.29797 6.33801L4.21697 5.41901L7.99997 9.20201L11.783 5.41901L12.702 6.33801L7.99997 11.04Z">
                        </path>
                      </svg>
                    </button>
                    <div v-show="dropdownOpen" tabindex="-1" role="menu" aria-hidden="false"
                      class="_20WMwO w-100 dropdown-menu">
                      <button type="button" tabindex="0" role="menuitem"
                        class="_3CkPsH dropdown-item active"><span>최신순</span></button>
                      <button type="button" tabindex="0" role="menuitem"
                        class="_3CkPsH dropdown-item"><span>인기순</span></button>
                    </div>
                  </div>
                </div>
                <ul class="_1PTI0R p-0 mb-4">
                  <span>
                    <div>
                      <li class="_3lQ639 _32Ay9Q" role="presentation">
                        <div class="_2kqp41 flex-grow-1" style="--box-gap: 0.75rem;">
                          <div class="_2kqp41 tttQ1F" style="--box-gap: 0.375rem;">
                            <p class="_3yzn7O mb-0 _2Sv3IV">정답 코드입니다 참고하세요</p>
                            <div>
                              <span class="d-none d-md-block">
                                <span class="d-flex Dhz06G align-items-center text-gray-600">
                                  <span class="text-gray-900 font-weight-normal flex-shrink-0">jun
                                    Choi</span>
                                  <span class="paragraph-sm">·</span>
                                  <span class="text-gray-600 flex-shrink-0" title="2024. 2. 17. 오후 7:25:30">24.02.17
                                  </span>
                                </span>
                              </span>
                            </div>
                          </div>
                          <div class="_2U1dPx">
                            <nav class="" aria-label="breadcrumb">
                              <ol class="breadcrumb _3cKvl flex-nowrap">
                                <li
                                  class="d-inline-flex align-items-center iFcHCl _1OTYG _2amVj _285DO active breadcrumb-item"
                                  aria-current="page"><span class="_1t2_hP">[2차][한줄] 문제10)
                                    상품권 총 지급액구하기 -
                                    Python3</span></li>
                              </ol>
                            </nav>
                          </div>
                          <div class="d-flex justify-content-between">
                            <div class="d-block d-md-none"><span
                                class="d-flex Dhz06G align-items-center text-gray-600 _2V47lu"><span
                                  class="text-gray-900 font-weight-normal flex-shrink-0">jun
                                  Choi</span><span class="paragraph-sm">·</span><span
                                  class="text-gray-600 flex-shrink-0"
                                  title="2024. 2. 17. 오후 7:25:30">24.02.17</span></span>
                            </div>
                            <div class="d-flex align-items-center _1VWGjy"><span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"><svg fill="currentColor"
                                  width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
                                  <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M1 8.07129C3.24 4.73829 5.24 3.07129 8 3.07129C10.761 3.07129 12.761 4.73829 15 8.07129C12.761 11.4043 10.761 13.0713 8 13.0713C5.24 13.0713 3.24 11.4043 1 8.07129ZM8 12.0713C10.209 12.0713 12 10.2803 12 8.07129C12 5.86229 10.209 4.07129 8 4.07129C5.791 4.07129 4 5.86229 4 8.07129C4 10.2803 5.791 12.0713 8 12.0713ZM8 10.0713C6.896 10.0713 6 9.17629 6 8.07129C6 6.96729 6.896 6.07129 8 6.07129C9.104 6.07129 10 6.96729 10 8.07129C10 9.17629 9.104 10.0713 8 10.0713Z">
                                  </path>
                                </svg><span class="paragraph-sm">17</span></span><span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"><svg fill="currentColor"
                                  width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
                                  <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M4 6.059H12V4.759H4V6.059ZM4 8.741H9.5V7.441H4V8.741ZM1.5 1.75V10.25V11.75V14.25L4 11.75H14.5V1.75H1.5Z">
                                  </path>
                                </svg><span class="paragraph-sm">0</span></span><span
                                class="text-gray-600 d-inline-flex align-items-center _22s-QT"><svg fill="currentColor"
                                  width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
                                  <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M9.95312 5.75011L10.4471 4.20511C10.7831 3.15311 10.4811 2.00111 9.67112 1.25011L5.30012 5.75011V13.7501H13.0001L15.0001 8.78811V5.75011H9.95312ZM1.50012 13.7501H4.00012V6.25011H1.50012V13.7501Z">
                                  </path>
                                </svg><span class="paragraph-sm">0</span></span></div>
                          </div>
                        </div>
                      </li>
                      <hr class="m-0 p-0">
                    </div>
                  </span>
                </ul>
                <div class="d-flex justify-content-center py-0 py-md-4">
                  <nav class="" aria-label="pagination">
                    <ul class="_1VYQb4 pagination">
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-prev"
                            class="_2Hgkt3 btn d-flex p-0 page-link"><svg fill="currentColor" width="16" height="16"
                              viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
                              <path fill-rule="evenodd" clip-rule="evenodd"
                                d="M9.33838 12.7021L4.63538 8.0001L9.33838 3.2981L10.2574 4.2171L6.47438 8.0001L10.2574 11.7821L9.33838 12.7021Z">
                              </path>
                            </svg></button></div>
                      </li>
                      <li class="page-item active">
                        <div role="button" tabindex="-1"><button aria-label="pagination-1"
                            class="_2Hgkt3 btn d-flex p-0 page-link">1</button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-2"
                            class="_2Hgkt3 btn d-flex p-0 page-link">2</button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-3"
                            class="_2Hgkt3 btn d-flex p-0 page-link">3</button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-4"
                            class="_2Hgkt3 btn d-flex p-0 page-link">4</button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-5"
                            class="_2Hgkt3 btn d-flex p-0 page-link">5</button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-6"
                            class="_2Hgkt3 btn d-flex p-0 page-link"><svg fill="currentColor"
                              preserveAspectRatio="xMidYMid meet" height="12" width="12" class=""
                              id="abae7265-d86f-4afc-bc36-ad71c61cb971" data-name="레이어 1"
                              xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18"
                              style="vertical-align: middle; color: currentcolor; transform: rotate(90deg);">
                              <path
                                d="M9,4.25a2,2,0,1,1,2-2A2,2,0,0,1,9,4.25ZM11,9a2,2,0,1,0-2,2A2,2,0,0,0,11,9Zm0,6.75a2,2,0,1,0-2,2A2,2,0,0,0,11,15.75Z"
                                fill="#90929e"></path>
                            </svg></button></div>
                      </li>
                      <li class="page-item">
                        <div role="button" tabindex="-1"><button aria-label="pagination-next"
                            class="_2Hgkt3 btn d-flex p-0 page-link"><svg fill="currentColor" width="16" height="16"
                              viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
                              <path fill-rule="evenodd" clip-rule="evenodd"
                                d="M6.65228 12.7021L5.73328 11.7821L9.51628 8.0001L5.73328 4.2171L6.65228 3.2981L11.3553 8.0001L6.65228 12.7021Z">
                              </path>
                            </svg></button>
                        </div>
                      </li>
                    </ul>
                  </nav>
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
import { useBoardStore } from "/src/stores/useBoardStore";

export default {
  name: "SearchResultPage",
  mounted() {
    const link = document.createElement("link");
    link.rel = "stylesheet";
    link.href =
      "https://statics.goorm.io/gds/goormstrap/v1.29.0/goormstrap.v4.min.css";
    document.head.appendChild(link);
  },
  data() {
    return {
      dropdownOpen: false,
    };
  },
  computed: {
    ...mapStores(useBoardStore),
  },
  methods: {
    toggleDropdown() {
      this.dropdownOpen = !this.dropdownOpen;
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
  --toastify-color-progress-light: linear-gradient(90deg, #4cd964, #5ac8fa, #007aff, #34aadc, #5856d6, #ff2d55);
  --toastify-color-progress-dark: #bb86fc;
  --toastify-color-progress-info: var(--toastify-color-info);
  --toastify-color-progress-success: var(--toastify-color-success);
  --toastify-color-progress-warning: var(--toastify-color-warning);
  --toastify-color-progress-error: var(--toastify-color-error)
}

._1Bbxo._1Bbxo {
  display: flex;
  flex-direction: column;
  align-items: end;
  width: unset;
  max-width: 35rem
}

@media only screen and (max-width:991px) {
  ._1Bbxo._1Bbxo {
    width: 100%;
    max-width: 35rem
  }
}

@media only screen and (max-width:480px) {
  ._1Bbxo._1Bbxo {
    margin: unset;
    padding: 1rem
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

._2zXJMj>section {
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

._2zXJMj>section {
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
  transition: transform .3s, -webkit-transform .3s;
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
  font-size: .875rem;
  line-height: 1.375rem;
}

._2amVj {
  font-size: .875rem;
  line-height: 1.375rem;
}

.iFcHCl {
  flex-grow: 1;
  min-width: 0;
}

._2amVj {
  font-size: .875rem;
  line-height: 1.375rem;
}

._1OTYG+._1OTYG:before {
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
  font-size: .875rem;
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
  letter-spacing: -.006rem;
}


svg:not(:root) {
  overflow-clip-margin: content-box;
  overflow: hidden;
}

._1VYQb4 li+li {
  margin-left: 0.25rem;
}

._1VYQb4 .active .page-link {
  background: rgba(80, 148, 250, .24);
}

._1VYQb4 {
  margin-bottom: 0px;
}

._2zXJMj>section {
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
</style>