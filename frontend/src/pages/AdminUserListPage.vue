<template>
  <div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
      <!-- Menu and Navbar components are inserted here, ensuring proper layout structure -->
      <AdminMenuComponent />
      <div class="layout-page">
        <div class="content-wrapper">
          <!-- Content -->
          <div class="container-xxl flex-grow-1 container-p-y">
            <h3 class="py-3 mb-4">
              <span class="text-muted fw-light">회원 /</span> 목록
            </h3>

            <!-- 회원 목록 -->
            <div class="card">
              <!-- <h5 class="card-header">회원</h5> -->
              <div class="table-responsive text-nowrap">
                <table class="table">
                  <thead>
                    <tr>
                      <th>idx</th>
                      <th>이메일</th>
                      <th>이름</th>
                      <th>닉네임</th>
                      <th>상태</th>
                      <th>수정/삭제</th>
                    </tr>
                  </thead>
                  <tbody class="table-border-bottom-0">
                    <tr v-for="users in userStore.userList" :key="users.userIdx">
                      <AdminUserListComponent :users="users" />
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <!--/ 회원 목록 -->
          </div>
          <div class="content-backdrop fade"></div>
          <div class="d-flex justify-content-center py-0 py-md-4">
            <PaginationComponent
              :current-page="userStore.currentPage"
              :total-pages="userStore.totalPages"
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
import { useUserStore } from "../stores/useUserStore";
import AdminUserListComponent from "@/components/AdminUserListComponent.vue";
import AdminMenuComponent from "@/components/AdminMenuComponent.vue";
import PaginationComponent from "@/components/PaginationComponent.vue";
import { useAdminStore } from "@/stores/useAdminStore";

export default {
  name: "AdminUserListPage",
  components: {
    AdminMenuComponent,
    AdminUserListComponent,
    PaginationComponent,
  },
  computed: {
    ...mapStores(useUserStore, useAdminStore),
    visiblePages() {
      // 최대 5개의 페이지 번호만 보이도록 계산
      let pages = [];
      const total = this.userStore.totalPages;

      // 현재 페이지에서 앞뒤로 2개씩 보이게 하되, 총 페이지 수를 초과하지 않도록 조정
      let start = Math.max(1, this.userStore.currentPage - 2);
      let end = Math.min(total, start + 4);

      // 시작점 재조정: end가 변경되었을 때, 5개 페이지를 유지하려면 start도 조정해야 함
      start = Math.max(1, Math.min(start, total - Math.min(total, 4)));

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
      return pages;
    },
  },
  mounted() {
    this.$root.hideHeaderAndFooter = true;
    this.userStore.getUserList();
  },
  methods: {
    changePage(page) {
      this.userStore.getUserList(page);
    },
    jumpForward() {
      // 현재 페이지에서 3페이지 앞으로 점프
      let nextPage = Math.min(
        this.userStore.currentPage + 3,
        this.userStore.totalPages
      );
      this.changePage(nextPage);
      // visiblePages를 업데이트하기 위해 currentPage를 설정
      this.userStore.currentPage = nextPage;
    },
  },
};
</script>

<style scoped>
@import url("@/assets/css/auth-boxicons.css");
@import url("@/assets/css/auth-theme-default.css");
@import url("@/assets/css/auth.css");
@import url("@/assets/css/auth-perfect-scrollbar.css");
@import url("@/assets/css/auth-page.css");

html:not(.layout-navbar-fixed) .layout-content-navbar .layout-page {
  background-color: rgba(245, 245, 249);
  padding-top: 0 !important;
}

.layout-navbar-fixed
  .layout-wrapper:not(.layout-horizontal)
  .layout-page:before {
  content: "";
  width: 100%;
  height: 0.75rem;
  position: fixed;
  top: 0px;
  z-index: 10;
}

.layout-navbar-fixed
  .layout-wrapper:not(.layout-horizontal)
  .layout-page:before {
  content: "";
  width: 100%;
  height: 0.75rem;
  position: fixed;
  top: 0px;
  z-index: 10;
}

.layout-navbar-fixed .layout-page:before {
  backdrop-filter: saturate(200%) blur(10px);
  background: rgba(245, 245, 249, 0.6);
}

.layout-navbar-fixed
  .layout-wrapper:not(.layout-horizontal):not(.layout-without-menu)
  .layout-page {
  padding-top: 76px !important;
}

/* Default navbar */
.layout-navbar-fixed .layout-wrapper:not(.layout-without-menu) .layout-page {
  padding-top: 64px !important;
}

.docs-page
  .layout-navbar-fixed.layout-wrapper:not(.layout-without-menu)
  .layout-page,
.docs-page
  .layout-menu-fixed.layout-wrapper:not(.layout-without-menu)
  .layout-page {
  padding-top: 62px !important;
}

html:not(.layout-navbar-fixed):not(.layout-menu-fixed):not(
    .layout-menu-fixed-offcanvas
  )
  .layout-page,
html:not(.layout-navbar-fixed) .layout-content-navbar .layout-page {
  padding-top: 0 !important;
}

@media (min-width: 1200px) {
  .layout-menu-fixed:not(.layout-menu-collapsed) .layout-page,
  .layout-menu-fixed-offcanvas:not(.layout-menu-collapsed) .layout-page {
    padding-left: 16.25rem;
  }
}

@media (max-width: 768px) {
  .card,
  .form-control {
    /* 화면이 768px 이하일 때 적용될 스타일 */
    width: 100%;
    /* 전체 너비를 사용 */
    margin: 0;
    /* 기본 마진 제거 */
    padding: 10px;
    /* 내부 패딩 조정 */
  }

  .btn-primary {
    /* 버튼도 반응형으로 조정 */
    width: auto;
    /* 버튼 너비 자동 조정 */
    margin: 10px 0;
    /* 상하 마진 추가 */
  }

  .flex-end-container {
    flex-direction: column;
    /* 화면이 작을 때는 세로로 배열 */
  }
}

.layout-container {
  min-height: 100vh;
}

.app-brand,
.content-wrapper,
.content-wrapper > *,
.layout-menu,
.layout-page {
  min-height: 1px;
}

.layout-container,
.layout-wrapper {
  width: 100%;
  display: flex;
  flex: 1 1 auto;
  align-items: stretch;
}

.layout-navbar {
  flex: 0 0 auto;
}

.layout-page {
  flex: 1 1 auto;
  align-items: stretch;
  padding: 0;
}

.content-wrapper {
  display: flex;
  align-items: stretch;
  flex: 1 1 auto;
  flex-direction: column;
  justify-content: space-between;
}

.collapse:not(.show),
.dropdown-toggle-hide-arrow > .dropdown-toggle::after,
.dropdown-toggle-hide-arrow > .dropdown-toggle::before,
.dropdown-toggle.hide-arrow::after,
.dropdown-toggle.hide-arrow::before {
  display: none;
}

.layout-navbar-fixed
  .layout-wrapper:not(.layout-horizontal)
  .layout-page:before {
  content: "";
  width: 100%;
  height: 0.75rem;
  position: fixed;
  top: 0px;
  z-index: 10;
}

.layout-navbar-fixed .layout-page:before {
  backdrop-filter: saturate(200%) blur(10px);
  background: rgba(245, 245, 249, 0.6);
}

.layout-navbar-fixed
  .layout-wrapper:not(.layout-horizontal):not(.layout-without-menu)
  .layout-page {
  padding-top: 76px !important;
}

.layout-content-navbar .content-wrapper {
  background-color: rgba(245, 245, 249);
}

.btn-group-vertical > .btn,
.btn-group-vertical > .btn-group,
.card-img,
.card-img-bottom,
.card-img-top,
.layout-content-navbar .content-wrapper,
.menu .app-brand {
  width: 100%;
}

.py-0,
html:not(.layout-footer-fixed) .content-wrapper {
  padding-bottom: 0 !important;
}

.app-brand,
.content-wrapper,
.content-wrapper > *,
.layout-menu,
.layout-page {
  min-height: 1px;
}

.content-wrapper {
  display: flex;
  align-items: stretch;
  flex: 1 1 auto;
  flex-direction: column;
  justify-content: space-between;
}

.container-p-y:not([class^="pb-"]):not([class*=" pb-"]) {
  padding-bottom: 1.625rem !important;
}

.container-p-y:not([class^="pt-"]):not([class*=" pt-"]) {
  padding-top: 1.625rem !important;
  padding: 140px;
}

.py-3 {
  padding-top: 1rem !important;
  padding-bottom: 1rem !important;
}

.mb-4 {
  margin-bottom: 1.5rem !important;
}

@media (min-width: 1200px) {
  h3,
  .h3 {
    font-size: 1.625rem;
  }
}

.card-header:first-child {
  border-radius: var(--bs-card-inner-border-radius)
    var(--bs-card-inner-border-radius) 0 0;
}

.align-items-center {
  align-items: center !important;
}

.justify-content-between {
  justify-content: space-between !important;
}

.d-flex {
  display: flex !important;
}

.card-header,
.card-footer {
  border-color: #d9dee3;
}

.card-header {
  padding: var(--bs-card-cap-padding-y) var(--bs-card-cap-padding-x);
  margin-bottom: 0;
  color: var(--bs-card-cap-color);
  background-color: var(--bs-card-cap-bg);
  border-bottom: var(--bs-card-border-width) solid var(--bs-card-border-color);
}

.card {
  --bs-card-spacer-y: 1.5rem;
  --bs-card-spacer-x: 1.5rem;
  --bs-card-title-spacer-y: 0.875rem;
  --bs-card-title-color: #566a7f;
  --bs-card-subtitle-color: ;
  --bs-card-border-width: 0;
  --bs-card-border-color: #d9dee3;
  --bs-card-border-radius: 0.5rem;
  --bs-card-box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
  --bs-card-inner-border-radius: 0.5rem;
  --bs-card-cap-padding-y: 1.5rem;
  --bs-card-cap-padding-x: 1.5rem;
  --bs-card-cap-bg: transparent;
  --bs-card-cap-color: ;
  --bs-card-height: ;
  --bs-card-color: ;
  --bs-card-bg: #fff;
  --bs-card-img-overlay-padding: 1.5rem;
  --bs-card-group-margin: 0.8125rem;
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  height: var(--bs-card-height);
  color: var(--bs-body-color);
  word-wrap: break-word;
  background-color: var(--bs-card-bg);
  background-clip: border-box;
  border: var(--bs-card-border-width) solid var(--bs-card-border-color);
  border-radius: var(--bs-card-border-radius);
}

.btn {
  --bs-btn-padding-x: 1.25rem;
  --bs-btn-padding-y: 0.4375rem;
  --bs-btn-font-family: ;
  --bs-btn-font-size: 0.9375rem;
  --bs-btn-font-weight: 400;
  --bs-btn-line-height: 1.53;
  --bs-btn-color: var(--bs-body-color);
  --bs-btn-bg: transparent;
  --bs-btn-border-width: var(--bs-border-width);
  --bs-btn-border-color: transparent;
  --bs-btn-border-radius: var(--bs-border-radius);
  --bs-btn-hover-border-color: transparent;
  --bs-btn-box-shadow: none;
  --bs-btn-disabled-opacity: 0.65;
  --bs-btn-focus-box-shadow: 0 0 0 0.05rem
    rgba(var(--bs-btn-focus-shadow-rgb), 0.5);
  display: inline-block;
  padding: var(--bs-btn-padding-y) var(--bs-btn-padding-x);
  font-family: var(--bs-btn-font-family);
  font-size: var(--bs-btn-font-size);
  font-weight: var(--bs-btn-font-weight);
  line-height: var(--bs-btn-line-height);
  color: var(--bs-btn-color);
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  user-select: none;
  border: var(--bs-btn-border-width) solid var(--bs-btn-border-color);
  border-radius: var(--bs-btn-border-radius);
  background-color: var(--bs-btn-bg);
  transition: all 0.2s ease-in-out;
}

button,
select {
  text-transform: none;
}

.btn-primary {
  color: #fff;
  background-color: #696cff;
  border-color: #696cff;
  box-shadow: 0 0.125rem 0.25rem 0 rgba(105, 108, 255, 0.4);
  border-radius: 10px;
  margin-right: 20px;
  margin-bottom: 20px;
  float: right;
}

.mb-0 {
  margin-bottom: 0 !important;
}

h5,
.h5 {
  font-size: 1.125rem;
}

h6,
.h6,
h5,
.h5,
h4,
.h4,
h3,
.h3,
h2,
.h2,
h1,
.h1 {
  margin-top: 0;
  margin-bottom: 1rem;
  font-weight: 300;
  line-height: 1.1;
  color: #566a7f;
}

.d-flex {
  display: flex !important;
}

.card-header,
.card-footer,
.card-body {
  border-color: #d9dee3;
}

.card-header {
  padding: var(--bs-card-cap-padding-y) var(--bs-card-cap-padding-x);
  margin-bottom: 0;
  color: var(--bs-card-cap-color);
  background-color: var(--bs-card-cap-bg);
}

.py-3 {
  padding-top: 1rem !important;
  padding-bottom: 1rem !important;
}

.text-muted {
  --bs-text-opacity: 1;
  color: #a1acb8 !important;
}

.fw-light {
  font-weight: 300 !important;
}

input[type="text" i] {
  padding-block: 10px;
  padding-inline: 10px;
}

input {
  text-rendering: auto;
  color: fieldtext;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: auto;
  -webkit-rtl-ordering: logical;
  cursor: text;
  background-color: field;
  margin: 0em;
  padding: 1px 0px;
  border-width: 2px;
  border-style: inset;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
  padding-block: 1px;
  padding-inline: 2px;
}

.card mb-4 .card-body {
  flex: 1 1 auto;
  color: var(--bs-card-color);
  padding: 1.5rem;
  /* Adjust padding as needed */
  background-color: #ffffff;
  border-bottom: var(--bs-card-border-width) solid var(--bs-card-border-color);
  box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: var(--bs-card-border-radius);
}

.card .card-header + .card-body,
.card .card-header + .card-content > .card-body:first-of-type {
  padding-top: 0;
}

.form-control {
  display: block;
  width: 92%;
  font-size: 0.9375rem;
  font-weight: 400;
  line-height: 1.53;
  color: #697a8d;
  appearance: none;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #d9dee3;
  /* Update border to match card */
  border-radius: 0.5rem;
  /* Match card's border radius */
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  /* Ensure no extra margin */
  margin-left: 20px;
  margin-bottom: 20px;
}

/* Additional CSS */
.card-header + .card-body,
.card-content > .card-body:first-of-type {
  padding-top: 0;
  /* Adjust spacing between header and body */
}

.flex-end-container {
  display: flex;
  justify-content: flex-end;
  /* Aligns children (input and button) to the right */
  gap: 10px;
  /* Adds some space between the input and the button */
  flex-direction: column;
}

/*------------------조회----------------*/
.container,
.container-fluid,
.container-xxl,
.container-xl,
.container-lg,
.container-md,
.container-sm {
  --bs-gutter-x: 1.625rem;
  --bs-gutter-y: 0;
  width: 100%;
  padding-right: calc(var(--bs-gutter-x) * 0.5);
  padding-left: calc(var(--bs-gutter-x) * 0.5);
  margin-right: auto;
  margin-left: auto;
}

.flex-grow-1 {
  flex-grow: 1 !important;
}

@media (min-width: 992px) {
  .container,
  .container-fluid,
  .container-sm,
  .container-md,
  .container-lg,
  .container-xl,
  .container-xxl {
    padding-right: 8rem;
    padding-left: 8rem;
  }
}

.layout-page,
.content-wrapper,
.content-wrapper > *,
.layout-menu {
  min-height: 1px;
}

.container-p-y:not([class^="pt-"]):not([class*=" pt-"]) {
  padding-top: 1.625rem !important;
}

.container-p-y:not([class^="pb-"]):not([class*=" pb-"]) {
  padding-bottom: 1.625rem !important;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

h4,
.h4 {
  font-size: calc(1.2625rem + 0.15vw);
}

@media (min-width: 1200px) {
  h4,
  .h4 {
    font-size: 1.375rem;
  }
}

.mb-4 {
  margin-bottom: 1.5rem !important;
}

.py-3 {
  padding-top: 1rem !important;
  padding-bottom: 1rem !important;
}

.fw-light {
  font-weight: 300 !important;
}

.text-muted {
  --bs-text-opacity: 1;
  color: #a1acb8 !important;
}

.card {
  --bs-card-spacer-y: 1.5rem;
  --bs-card-spacer-x: 1.5rem;
  --bs-card-title-spacer-y: 0.875rem;
  --bs-card-title-color: #566a7f;
  --bs-card-subtitle-color: ;
  --bs-card-border-width: 0;
  --bs-card-border-color: #d9dee3;
  --bs-card-border-radius: 0.5rem;
  --bs-card-box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
  --bs-card-inner-border-radius: 0.5rem;
  --bs-card-cap-padding-y: 1.5rem;
  --bs-card-cap-padding-x: 1.5rem;
  --bs-card-cap-bg: transparent;
  --bs-card-cap-color: ;
  --bs-card-height: ;
  --bs-card-color: ;
  --bs-card-bg: #fff;
  --bs-card-img-overlay-padding: 1.5rem;
  --bs-card-group-margin: 0.8125rem;
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  height: var(--bs-card-height);
  color: var(--bs-body-color);
  word-wrap: break-word;
  background-color: var(--bs-card-bg);
  background-clip: border-box;
  border: var(--bs-card-border-width) solid var(--bs-card-border-color);
  border-radius: var(--bs-card-border-radius);
}

.card {
  background-clip: padding-box;
  box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
}

.table-responsive {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
}

.text-nowrap {
  white-space: nowrap !important;
}

table {
  caption-side: bottom;
  border-collapse: collapse;
}

.table {
  --bs-table-color-type: initial;
  --bs-table-bg-type: initial;
  --bs-table-color-state: initial;
  --bs-table-bg-state: initial;
  --bs-table-color: var(--bs-body-color);
  --bs-table-bg: transparent;
  --bs-table-border-color: #d9dee3;
  --bs-table-accent-bg: transparent;
  --bs-table-striped-color: var(--bs-body-color);
  --bs-table-striped-bg: #f9fafb;
  --bs-table-active-color: var(--bs-body-color);
  --bs-table-active-bg: rgba(67, 89, 113, 0.1);
  --bs-table-hover-color: var(--bs-body-color);
  --bs-table-hover-bg: rgba(67, 89, 113, 0.06);
  width: 100%;
  margin-bottom: 1rem;
  vertical-align: middle;
  border-color: var(--bs-table-border-color);
}

.card .table {
  margin-bottom: 0;
}

.table > thead {
  vertical-align: bottom;
}

th {
  font-weight: 500;
  text-align: inherit;
  text-align: -webkit-match-parent;
}
thead,
tbody,
tfoot,
tr,
td,
th {
  border-color: inherit;
  border-style: solid;
  border-width: 0;
}

.table > :not(caption) > * > * {
  padding: 0.625rem 1.25rem;
  color: #697a8d;
  background-color: var(--bs-table-bg);
  border-bottom-width: 1px;
  box-shadow: inset 0 0 0 9999px
    var(--bs-table-bg-state, var(--bs-table-bg-type, var(--bs-table-accent-bg)));
  text-align: center;
}

.table > :not(caption) > * > * {
  background-clip: padding-box;
}

.table th {
  text-transform: uppercase;
  font-size: 16ㅔㅌ;
  letter-spacing: 1px;
  text-align: center;
}

.table:not(.table-dark) th {
  color: #566a7f;
}

.table > tbody {
  vertical-align: inherit;
}

/*--------조회 컴포넌트 CSS----------*/
.fw-medium {
  font-weight: 500 !important;
}

.dropup,
.dropend,
.dropdown,
.dropstart,
.dropup-center,
.dropdown-center {
  position: relative;
}

.table tr > td .dropdown {
  position: static;
}

.dropdown-toggle {
  white-space: nowrap;
}
.btn {
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}
.p-0 {
  padding: 0 !important;
}
button:not(:disabled),
[type="button"]:not(:disabled),
[type="reset"]:not(:disabled),
[type="submit"]:not(:disabled) {
  cursor: pointer;
}
.btn:not([class*="btn-"]):active,
.btn:not([class*="btn-"]).active,
.btn:not([class*="btn-"]).show,
.btn:not([class*="btn-"]) {
  border: none;
}
.btn .bx {
  line-height: 1.15;
}
.dropdown-menu {
  --bs-dropdown-zindex: 1000;
  --bs-dropdown-min-width: 12rem;
  --bs-dropdown-padding-x: 0;
  --bs-dropdown-padding-y: 0.3125rem;
  --bs-dropdown-spacer: 0.125rem;
  --bs-dropdown-font-size: 0.9375rem;
  --bs-dropdown-color: var(--bs-body-color);
  --bs-dropdown-bg: #fff;
  --bs-dropdown-border-color: transparent;
  --bs-dropdown-border-radius: var(--bs-border-radius);
  --bs-dropdown-border-width: var(--bs-border-width);
  --bs-dropdown-inner-border-radius: 0;
  --bs-dropdown-divider-bg: #d9dee3;
  --bs-dropdown-divider-margin-y: 0.5rem;
  --bs-dropdown-box-shadow: 0 0.25rem 1rem rgba(161, 172, 184, 0.45);
  --bs-dropdown-link-color: #697a8d;
  --bs-dropdown-link-hover-color: #697a8d;
  --bs-dropdown-link-hover-bg: rgba(67, 89, 113, 0.04);
  --bs-dropdown-link-active-color: #fff;
  --bs-dropdown-link-active-bg: rgba(105, 108, 255, 0.08);
  --bs-dropdown-link-disabled-color: #c7cdd4;
  --bs-dropdown-item-padding-x: 1.25rem;
  --bs-dropdown-item-padding-y: 0.532rem;
  --bs-dropdown-header-color: #a1acb8;
  --bs-dropdown-header-padding-x: 1.25rem;
  --bs-dropdown-header-padding-y: 0.3125rem;
  position: absolute;
  z-index: var(--bs-dropdown-zindex);
  display: none;
  min-width: var(--bs-dropdown-min-width);
  padding: var(--bs-dropdown-padding-y) var(--bs-dropdown-padding-x);
  margin: 0;
  font-size: var(--bs-dropdown-font-size);
  color: var(--bs-dropdown-color);
  text-align: left;
  list-style: none;
  background-color: var(--bs-dropdown-bg);
  background-clip: padding-box;
  border: var(--bs-dropdown-border-width) solid var(--bs-dropdown-border-color);
  border-radius: var(--bs-dropdown-border-radius);
}
.dropdown-menu {
  box-shadow: 0 0.25rem 1rem rgba(161, 172, 184, 0.45);
}
.dropdown-item {
  display: block;
  width: 100%;
  padding: var(--bs-dropdown-item-padding-y) var(--bs-dropdown-item-padding-x);
  clear: both;
  font-weight: 400;
  color: var(--bs-dropdown-link-color);
  text-align: inherit;
  white-space: nowrap;
  background-color: transparent;
  border: 0;
  border-radius: var(--bs-dropdown-item-border-radius, 0);
}
.dropdown-item {
  line-height: 1.54;
}
.me-1 {
  margin-right: 0.25rem !important;
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
