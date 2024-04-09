<template>
  <HeaderComponent :showBorder="showBorder" :showMenu="showMenu" :showLogo="showLogo" />
  <div class="css-1hnxdb7">
    <div class="css-16z0wya">
      <section class="css-zea7jz0">
        <div class="ContentSearch_container__RFyCa">
          <div class="SectionTitle_container__PcXfF SectionTitle_center__bSviY SectionTitle_size-48__RYzIo">
            <p class="SectionTitle_label__cEYNl default SectionTitle_size-48__RYzIo"></p>
            <p class="SectionTitle_title__iqOHl default SectionTitle_size-48__RYzIo">
              <router-link to="/">
                <img src="../assets/img/logo.png" alt="BOOKSHELF" width="500px" />
              </router-link>
            </p>
            <p class="SectionTitle_description__3Deqx default SectionTitle_size-48__RYzIo"></p>
            <div class="SectionTitle_button__vZ_Mf SectionTitle_size-48__RYzIo"></div>
          </div>
          <div class="ContentSearch_searchWrapper__3xeqG">
            <div class="dropdown">
              <button type="button" @click="toggleDropdown" aria-haspopup="true"
                :aria-expanded="dropdownOpen.toString()" class="_1rMfp _3NZzgf btn btn-select btn-lg">
                <span>{{ selectedDropdownValue }}</span>
                <svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg"
                  class="_2-Js2">
                  <path fill-rule="evenodd" clip-rule="evenodd"
                    d="M7.99997 11.04L3.29797 6.33801L4.21697 5.41901L7.99997 9.20201L11.783 5.41901L12.702 6.33801L7.99997 11.04Z">
                  </path>
                </svg>
              </button>
              <div tabindex="-1" role="menu" :aria-hidden="!dropdownOpen.toString()" class="_20WMwO w-100 dropdown-menu"
                v-show="dropdownOpen">
                <button type="button" tabindex="0" role="menuitem" @click="selectDropdownValue('제목')"
                  class="_3CkPsH dropdown-item"><span>제목</span></button>
                <button type="button" tabindex="0" role="menuitem" @click="selectDropdownValue('제목+내용')"
                  class="_3CkPsH dropdown-item"><span>제목+내용</span></button>
              </div>
            </div>
            <input class="ContentSearch_searchInput__dVET9" v-model="searchQuery" v-on:keyup.enter="search" id="content-search" type="search"
              placeholder="후기글에서 검색 할 단어를 입력하세요" />
            <span class="Icon_container__KMOfZ rotate-0 Icon_l-large__pSyZb Icon_m-large__BQ2ze"
              style="--color: var(rgb(84, 29, 112))" @click="search">
              <svg width="40" height="40" viewBox="0 0 20 20" fill="purple" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd"
                  d="M2.45005 8.74999C2.45005 5.27059 5.27065 2.44999 8.75005 2.44999C12.2294 2.44999 15.05 5.27059 15.05 8.74999C15.05 12.2294 12.2294 15.05 8.75005 15.05C5.27065 15.05 2.45005 12.2294 2.45005 8.74999ZM8.75005 1.04999C4.49746 1.04999 1.05005 4.4974 1.05005 8.74999C1.05005 13.0026 4.49746 16.45 8.75005 16.45C10.6243 16.45 12.3421 15.7804 13.6774 14.6673L18.2551 19.245C18.5284 19.5183 18.9717 19.5183 19.245 19.245C19.5184 18.9716 19.5184 18.5284 19.245 18.255L14.6673 13.6773C15.7804 12.3421 16.45 10.6242 16.45 8.74999C16.45 4.4974 13.0026 1.04999 8.75005 1.04999Z"
                  fill="purple" stroke="purple" stroke-width="2"></path>
              </svg>
            </span>
          </div>
        </div>
      </section>
    </div>
  </div>

  <div class="css-1hnxdb7">
    <div class="css-16z0wya">
      <section class="css-zea7jz">
        <div class="css-143hej">
            <a onclick="location.href='/board/knowledge'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;지식 공유 서비스</p>
                  <p class="css-12j2poz">
                    집단 지성이 참여하고 <br />&nbsp;토론할 수 있는 문화
                  </p>
                </div>
                <img src="../assets/img/knowledge.png" loading="lazy" class="css-1522xt9" />
              </button></a>
            <a onclick="location.href='/board/qna'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;무엇이든 물어보세요</p>
                  <p class="css-12j2poz">
                    구성원이 서로 소통하며 <br />&nbsp;관심 분야에 대해<br />&nbsp;자연스럽게
                    공유하는 환경 제공
                  </p>
                </div>
                <img src="../assets/img/qna.png" loading="lazy" class="css-1522xt9" />
              </button></a>
            <a onclick="location.href='review'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;프로그램 참여 후기</p>
                  <p class="css-12j2poz">
                    실제 부트캠프에 참여한 <br />&nbsp;구성원들의 생생한 후기를
                    <br />&nbsp;공유하는 문화 형성
                  </p>
                </div>
                <img src="../assets/img/ddabong.png" loading="lazy" class="css-1522xt9" />
              </button></a>
        </div>
      </section>
      <section class="css-zea7jz2">
        <div class="css-143hej">
            <a onclick="location.href='/hot'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;인기 게시글</p>
                  <p class="css-12j2poz">&nbsp;회원들의 HOT ISSUE</p>
                </div>
                <img src="../assets/img/danger.png" loading="lazy" class="css-1522xt9" />
              </button></a>
            <a onclick="location.href='/study'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;스터디원 모집</p>
                  <p class="css-12j2poz">
                    함께 프로그래밍 공부를 할 <br />&nbsp;팀원을 모을 수 있는 환경
                  </p>
                </div>
                <img src="../assets/img/study.png" loading="lazy" class="css-1522xt9" />
              </button></a>
            <a onclick="location.href='/notice'">
              <button class="css-bcb9a9">
                <div class="css-197aby8">
                  <p class="css-1r2nemk">&nbsp;공지사항</p>
                  <p class="css-12j2poz">&nbsp;BOOTSHELF 공지사항</p>
                </div>
                <img src="../assets/img/notice.png" loading="lazy" class="css-1522xt9" />
              </button></a>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
  name: "MainPage",
  components: {
    HeaderComponent,
  },
  data() {
    return {
      showBorder: false,
      showMenu: false,
      showLogo: false,
      dropdownOpen: false,
      selectedDropdownValue: '제목', // 초기 선택값 (드랍다운)
      selectedDropdownOption: 1, // parameter로 전달할 값
      searchQuery: "",
    };
  },
  methods: {
    toggleDropdown() {
      this.dropdownOpen = !this.dropdownOpen;
    },
    selectDropdownValue(value) {
      this.selectedDropdownValue = value;
      this.selectedDropdownOption = value === "제목" ? 1 : 2;
      this.dropdownOpen = false; 
    },
    search() {
      this.$router.push({
        path: "/result",
        query: {
          query: this.searchQuery,
          option: this.selectedDropdownOption,
        },
      });
    },
  },
};
</script>

<style scoped>
html {
  font-size: 10px;
  scroll-behavior: smooth;
}

body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
  background-color: #fff;
}

@media screen and (max-width: 320px) {
  body {
    width: 352px;
    box-sizing: content-box;
  }
}

a,
button {
  -webkit-text-decoration: none;
  text-decoration: none;
  cursor: pointer;
}

input:-webkit-autofill,
input:-webkit-autofill:focus {
  -webkit-transition: background-color 600000s 0s, color 600000s 0s;
  transition: background-color 600000s 0s, color 600000s 0s;
}

h1 {
  font-size: 2.4rem;
}

@media (min-width: 1024px) {
  h1 {
    font-size: 4rem;
  }
}

h2 {
  font-size: 2.2rem;
}

@media (min-width: 1024px) {
  h2 {
    font-size: 3.2rem;
  }
}

h3 {
  font-size: 1.8rem;
}

@media (min-width: 1024px) {
  h3 {
    font-size: 2.2rem;
  }
}

h4 {
  font-size: 1.6rem;
}

@media (min-width: 1024px) {
  h4 {
    font-size: 2rem;
  }
}

h5 {
  font-size: 1.4rem;
}

@media (min-width: 1024px) {
  h5 {
    font-size: 1.8rem;
  }
}

h6 {
  font-size: 1.4rem;
}

@media (min-width: 1024px) {
  h6 {
    font-size: 1.4rem;
  }
}

ul {
  margin-block-start: 0;
  margin-block-end: 0;
  -webkit-padding-start: 0;
  padding-inline-start: 0;
}

@media (min-width: 1024px) {
  .mobile-only {
    display: none;
  }
}

* {
  margin: 0;
  line-height: 1.5;
}

.dropdown {
  position: relative;
  /* 상대 위치 지정 */
  display: inline-block;
  /* 필요에 따라 조정 */
  width: 150px;
}

/* 버튼 기본 스타일 */
._1rMfp._3NZzgf.btn.btn-select.btn-lg {
  background-color: #ffffff;
  /* 배경색 */
  color: #333;
  /* 글자색 */
  border: none;
  /* 테두리 제거 */
  padding: 15px 5px;
  /* 패딩 */
  border-radius: 8px;
  /* 모서리 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* 그림자 효과 */
  transition: all 0.3s ease;
  /* 부드러운 전환 효과 */
  width: 110px;
  margin-right: 20px;
}

/* 버튼 호버 스타일 */
._1rMfp._3NZzgf.btn.btn-select.btn-lg:hover {
  background-color: #ffffff;
  /* 호버 배경색 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  /* 호버 시 그림자 효과 강조 */
}

/* 드랍다운 메뉴 스타일 */
._20WMwO.w-100.dropdown-menu {
  background-color: #ffffff;
  /* 배경색 */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  /* 그림자 효과 */
  border-radius: 8px;
  /* 모서리 둥글게 */
  border: 1px solid #e0e0e0;
  /* 경계선 스타일 */
  margin-top: 5px;
  /* 버튼과의 간격 */
  width: 110px;
  position: absolute;
  top: 100%;
  left: 0;
  z-index: 1000;
}

/* 드랍다운 아이템 스타일 */
._3CkPsH.dropdown-item {
  padding: 8px 20px;
  /* 패딩 */
  color: #333;
  /* 글자색 */
  transition: background-color 0.2s ease;
  /* 배경색 전환 효과 */
  width: 110px;
  background: #ffffff;
  border: none;
}

/* 마지막 드랍다운 아이템에만 하단 모서리 둥글게 적용 */
._3CkPsH.dropdown-item:last-child {
  border-radius: 0 0 8px 8px;
}

/* 마지막 드랍다운 아이템 호버 */
._3CkPsH.dropdown-item:last-child:hover {
  border-radius: 0 0 8px 8px;
}

/* 드랍다운 아이템 호버 스타일 */
._3CkPsH.dropdown-item:hover {
  background-color: #f5f5f5;
  /* 호버 배경색 */
  border-radius: 8px 8px 0 0;
}

@media (max-width: 767px) {
  @supports (-webkit-touch-callout: none) {
    .css-1hnxdb7 {
      height: -webkit-fill-available;
    }
  }
}

@media (max-width: 767px) {
  .css-16z0wya {
    display: flex;
    flex-direction: column;
    padding: 0;
  }
}

.css-zea7jz0 {
  display: flex;
  width: 100vw;
  height: 20vw;
  padding-bottom: 0px;
  flex-direction: column;
  gap: 30px;
  background: white;
}

@media (min-width: 820px) {
  .css-zea7jz0 {
    width: 100%;
    height: 100%;
  }
}

.css-zea7jz2 {
  display: flex;
  width: 100vw;
  padding-top: 0px;
  padding-bottom: 200px;
  flex-direction: column;
  gap: 30px;
  background: white;
}

@media (min-width: 820px) {
  .css-zea7jz2 {
    align-items: center;
    gap: 60px;
    padding-top: 0px;
    padding-bottom: 200px;
  }
}

.css-143hej {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 16px;
  -webkit-align-self: stretch;
  -ms-flex-item-align: stretch;
  align-self: stretch;
}

@media (min-width: 820px) {
  .css-143hej {
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
    gap: 24px;
    max-width: 1152px;
    -webkit-align-self: center;
    -ms-flex-item-align: center;
    align-self: center;
  }
}

.css-bcb9a9 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  padding: 20px 16px;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: flex-end;
  -webkit-box-align: flex-end;
  -ms-flex-align: flex-end;
  align-items: flex-end;
  gap: 10px;
  -webkit-align-self: stretch;
  -ms-flex-item-align: stretch;
  align-self: stretch;
  border-radius: 8px;
  border: 1px solid #e4ebf0;
  background: #ffffff;
  box-shadow: 0px 8px 24px 0px rgba(129, 137, 143, 0.12);
}

@media (min-width: 820px) {
  .css-bcb9a9 {
    width: 348px;
    height: 418px;
    gap: 12px;
    padding: 28px;
    border-radius: 10px;
  }

  .css-bcb9a9:hover {
    transform: translateY(-8px);
    -webkit-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
  }
}

.css-197aby8 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: flex-start;
  -webkit-box-align: flex-start;
  -ms-flex-align: flex-start;
  align-items: flex-start;
  gap: 10px;
  -webkit-align-self: stretch;
  -ms-flex-item-align: stretch;
  align-self: stretch;
}

@media (min-width: 820px) {
  .css-197aby8 {
    gap: 12px;
  }
}

.css-18zxnkv {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  padding: 2px 6px;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 10px;
  border-radius: 8px;
  background: #f5b200;
}

.css-187mfrw {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 20px;
  color: #ffffff;
}

.css-1r2nemk {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 18px;
  line-height: 25px;
  color: #141617;
  color: #000;
}

@media (min-width: 820px) {
  .css-1r2nemk {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 24px;
    line-height: 34px;
    color: #141617;
  }
}

.css-12j2poz {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #9da7ae;
  white-space: pre-wrap;
  text-align: left;
}

@media (min-width: 820px) {
  .css-12j2poz {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 20px;
    line-height: 26px;
    color: #141617;
    color: #9da7ae;
  }
}

.css-1522xt9 {
  width: 100px;
  height: 100px;
  text-align: center;
}

@media (min-width: 820px) {
  .css-1522xt9 {
    width: 80px;
    height: 80px;
  }
}

.css-crc739 {
  display: flex;
  padding: 2px 6px;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  background: #57b094;
}

.css-zea7jz {
  display: flex;
  width: 100vw;
  padding-top: 60px;
  padding-bottom: 0px;
  flex-direction: column;
  gap: 30px;
  background: white;
}

@media (min-width: 820px) {
  .css-zea7jz {
    align-items: center;
    gap: 60px;
    padding-top: 240px;
    padding-bottom: 30px;
  }
}

.ContentSearch_container__RFyCa {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--spacing-32);
}

.SectionTitle_container__PcXfF.SectionTitle_center__bSviY {
  padding-top: 120px;
  align-items: center;
  text-align: center;
}

.SectionTitle_container__PcXfF {
  padding-bottom: 100px;
  display: flex;
  flex-direction: column;
  word-break: keep-all;
}

:root {
  --border-inner: #0000ff;
  /* Blue for inner border */
  --border-outer: #add8e6;
  /* Light Blue for outer border */
  --background-inner: #ffffff;
  /* White for inner background */
}

.ContentSearch_container__RFyCa {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--spacing-32);
}

/* Ensuring custom font is applied across all input types */
button,
input,
textarea {
  font-family: "SpoqaHanSansNeo", "Apple SD Gothic Neo", "Noto Sans KR",
    sans-serif;
}

.SectionTitle_button__vZ_Mf:empty {
  display: none;
}

.SectionTitle_button__vZ_Mf.SectionTitle_size-48__RYzIo,
.SectionTitle_button__vZ_Mf.SectionTitle_size-68__B3JEs,
.SectionTitle_button__vZ_Mf.SectionTitle_size-82__UtCjt {
  margin-top: var(--spacing-32);
}

.SectionTitle_button__vZ_Mf {
  margin-top: var(--spacing-24);
}

@media (min-width: 48em) and (max-width: 75em) {
  .Icon_container__KMOfZ.Icon_m-large__BQ2ze {
    --size: 2.4rem;
  }
}

.Icon_container__KMOfZ {
  color: rgb(82, 29, 112);
  display: inline-block;
  position: relative;
  line-height: 0;
}

.ContentSearch_searchWrapper__3xeqG {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 40rem;
  height: 7.6rem;
  padding: 0;
  /* Adjusted for direct child input styling */
  border: 0.6rem solid var(rgb(84, 29, 112));
  /* Light Blue outer border */
  border-radius: 8.4rem;
  background: transparent;
  /* Ensuring background doesn't interfere with outer border */
  z-index: 10;
}

.ContentSearch_searchInput__dVET9 {
  margin-right: 1rem;
  display: flex;
  align-items: center;
  width: calc(100% - 2.4rem);
  /* Adjusting width to fit within the wrapper */
  height: 6.4rem;
  /* Adjusting height to fit within the wrapper */
  padding: 1.7rem 2rem;
  gap: 1.5rem;
  font-size: 1.4rem;
  border: 0.2rem solid var(--border-inner);
  /* Blue inner border */
  border-radius: 8.5rem;
  background: white;
  /* White inner background */
  /* Text color */
  box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
  /* 그림자 색상과 크기 설정 */
}

.Icon_container__KMOfZ+.ContentSearch_searchInput__dVET9 {
  flex-grow: 1;
  color: rgb(84, 29, 112);
  /* 아이콘 색상 변경 */
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  /* 그림자 색상과 크기 설정 */
  margin-right: 1rem;
  /* Adding space between icon and input field */
}

.Icon_container__KMOfZ svg {
  width: 2rem;
  /* 아이콘의 너비를 3.2rem으로 조정합니다. */
  height: 2rem;
  /* 아이콘의 높이를 3.2rem으로 조정합니다. */
  transition: all 0.2s ease-in-out;
}

.Icon_container__KMOfZ {
  color: rgb(84, 29, 112);
  display: inline-block;
  position: relative;
  line-height: 0;
}

:lang(ko) .ContentSearch_searchInput__dVET9 {
  line-height: 3rem;
}

:lang(en) .ContentSearch_searchInput__dVET9,
:lang(ko) .ContentSearch_searchInput__dVET9 {
  font-weight: 500;
}

button:lang(ko),
input:lang(ko),
textarea:lang(ko) {
  font-family: SpoqaHanSansNeo, Apple SD Gothic Neo, Noto Sans KR, sans-serif;
}

.ContentSearch_searchInput__dVET9 {
  border: none;
  font-size: 1rem;
  color: var(--gray80);
  caret-color: var(--gray80);
  width: 100%;
  height: 30%;
}

.css-194lkm4 {
  display: block;
  padding: 32px 0px 0px;
  border: 1px solid rgb(215, 224, 230);
  border-radius: 10px;
  position: absolute;
  top: calc(40px);
  right: -151px;
  background-color: white;
  opacity: 1;
  visibility: visible;
  z-index: 1000;
  width: 282px;
  transform: translateX(-50%);
  box-shadow: rgba(95, 102, 107, 0.12) 0px 8px 24px;
  font-family: Pretendard;
  overflow: scroll;
  scrollbar-width: none;
}

.css-194lkm4::before {
  border-color: rgb(215, 224, 230) transparent;
  border-style: solid;
  border-width: 0px 6px 8px 6.5px;
  content: "";
  display: block;
  right: 5%;
  position: absolute;
  top: -8px;
  width: 0px;
  z-index: 0;
}

.css-194lkm4::after {
  border-color: rgb(255, 255, 255) transparent;
  border-style: solid;
  border-width: 0px 6px 8px 6.5px;
  content: "";
  display: block;
  right: 5%;
  position: absolute;
  top: -7px;
  width: 0px;
  z-index: 1;
}

@media (min-width: 820px) {
  .css-17uj2d3 {
    height: 18px;
  }
}

@media (min-width: 768px) {
  .css-fazohs {
    height: 0;
  }
}

.css-fazohs {
  background: linear-gradient(90deg, #fff6dd 0%, #fff1f4 100%);
  margin-top: -1px;
  white-space: nowrap;
  height: 0;
  overflow: hidden;
  -webkit-transition: height 0.4s;
  transition: height 0.4s;
  position: relative;
  z-index: 0;
  min-width: 360px;
}

@media (min-width: 768px) {
  .css-1klcaje {
    padding: 16px;
    max-width: 1184px;
    margin: auto;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
  }
}

.css-1klcaje {
  height: 100%;
  -webkit-box-pack: justify;
  justify-content: space-between;
  color: white;
}

.css-17uj2d3 {
  display: flex;
  padding: 1px 4px;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  border-radius: 4px;
  background: white;
  color: white;
  cursor: pointer;
  font-size: 10px;
  font-style: normal;
  font-weight: 600;
  line-height: 160%;
}

.css-3u8r53 {
  cursor: pointer;
  width: 100%;
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  font-weight: 700;
  line-height: 1.5;
  color: white;
}

@media (min-width: 768px) {
  .css-3u8r53 {
    font-size: 16px;
    gap: 6px;
  }
}

@media (min-width: 768px) {
  .css-1hkxqrx {
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
    gap: 16px;
  }
}

.css-1hkxqrx {
  display: flex;
  align-items: center;
}

@media (min-width: 768px) {
  .css-5oxg9r {
    padding: 2px 12px;
  }
}

.css-5oxg9r {
  border-radius: 16px;
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

@media (min-width: 768px) {
  .css-1u9hu8i {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 18px;
    line-height: 29px;
    color: #141617;
    color: #3a3e41;
  }
}

.css-1u9hu8i {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 22px;
  color: #141617;
  color: #3a3e41;
}

@media (min-width: 768px) {
  .css-1hd57dg {
    padding: auto 0 auto undefined;
    -webkit-box-pack: right;
    -ms-flex-pack: right;
    -webkit-justify-content: right;
    justify-content: right;
  }
}

.css-1hd57dg {
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-right: 0;
}

.css-nbvgys {
  height: 102px;
  white-space: initial;
  content: " ";
  opacity: 0;
  flex: 0 0 102px;
  -webkit-box-flex: 0;
}

.css-16z0wya {
  display: flex;
  flex-direction: column;
}

@media (min-width: 820px) {
  .css-ppdf1p {
    display: inline-flex;
    padding-bottom: 100px;
    -ms-flex-align: center;
    gap: 100px;
    width: 100vw;
  }
}

.css-ppdf1p {
  flex-direction: column;
}

.css-p22c6q {
  position: relative;
  height: fit-content;
  background-color: white;
  width: 100%;
}

@media (min-width: 820px) {
  .css-1bj2jr5 {
    padding-top: 234px;
  }
}

.css-1bj2jr5 {
  display: flex;
  width: 100%;
  padding-top: 88px;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  background-color: white;
  position: absolute;
  top: 0;
}

@media (min-width: 820px) {
  .css-130d6tq {
    height: 136.214px;
  }
}

.css-130d6tq {
  width: 100%;
  height: 48px;
  -webkit-flex-shrink: 0;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 0%, #fff 100%);
  z-index: 1;
}

@media (min-width: 820px) {
  .css-gme0ob {
    padding: 108px 24px 0;
  }
}

.css-gme0ob {
  padding: 32px 16px 0;
  position: relative;
}

@media (min-width: 820px) {
  .css-1mzwlld {
    gap: 88px;
  }
}

.css-1mzwlld {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

@media (min-width: 820px) and (max-width: 1023px) {
  .css-1slt03f {
    gap: 16px;
    -webkit-align-self: flex-start;
    -ms-flex-item-align: flex-start;
    align-self: flex-start;
  }
}

.css-1slt03f {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 8px;
  z-index: 2;
}

.css-1bku0gi {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

@media (min-width: 820px) {
  .css-1dr1vfe {
    height: 59px;
  }
}

.css-1dr1vfe {
  height: 28px;
}

.css-tp2tac {
  display: flex;
  flex-direction: column;
  overflow: hidden;
  position: relative;
  height: 56px;
}

.css-vhme28 {
  display: flex;
  flex-direction: column;
  position: relative;
  height: 56px;
  transform: translateY(-100%);
  transition: transform 0.3s ease-in-out 0s;
}

.css-12cqpub {
  display: flex;
  flex-direction: column;
  position: relative;
  height: 56px;
  transform: translateY(-300%);
  transition: transform 0.3s ease-in-out;
}

.css-1aokzse {
  display: flex;
  flex-direction: column;
  position: relative;
  height: 56px;
  transform: translateY(0%);
  transition: transform 0.3s ease-in-out 0s;
}

.css-gbr1j8 {
  display: flex;
  flex-direction: column;
  position: relative;
  height: 56px;
  transform: translateY(-400%);
  transition: transform 0.3s ease-in-out 0s;
}

.css-1cvbm22 {
  display: flex;
  flex-direction: column;
  position: relative;
  height: 56px;
  transform: translateY(-200%);
  transition: transform 0.3s ease-in-out 0s;
}

@media (min-width: 820px) {
  .css-16wypob {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 700;
    font-size: 40px;
    line-height: 56px;
    color: rgb(20, 22, 23);
  }
}

@media (min-width: 820px) {
  .css-mm6ge2 {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 700;
    font-size: 40px;
    line-height: 56px;
    color: #141617;
  }
}

.css-mm6ge2 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 700;
  font-size: 20px;
  line-height: 28px;
  color: #141617;
  color: #141617;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  color: #e8344e !important;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 4px;
}
</style>
