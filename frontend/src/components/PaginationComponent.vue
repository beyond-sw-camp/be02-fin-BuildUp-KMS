<template>
  <nav aria-label="pagination">
    <ul class="_1VYQb4 pagination">
      <!-- 이전 페이지 버튼 -->
      <li class="page-item" v-if="currentPage > 1">
        <button @click.prevent="$emit('change-page', currentPage - 1)" aria-label="pagination-prev"
          class="_2Hgkt3 btn d-flex p-0 page-link">
          <svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M9.33838 12.7021L4.63538 8.0001L9.33838 3.2981L10.2574 4.2171L6.47438 8.0001L10.2574 11.7821L9.33838 12.7021Z">
            </path>
          </svg>
        </button>
      </li>

      <!-- 페이지 번호들 -->
      <li v-for="page in visiblePages" :key="page" :class="['page-item', { 'active': page === currentPage }]">
        <button @click.prevent="$emit('change-page', page)" class="_2Hgkt3 btn d-flex p-0 page-link">{{ page }}</button>
      </li>

      <!-- 점프 포워드 버튼 (현재 페이지가 마지막 페이지에서 3페이지 미만일 때는 표시하지 않음) -->
      <li class="page-item" v-if="showJumpForward">
        <button @click.prevent="jumpForward" aria-label="Jump forward" class="_2Hgkt3 btn d-flex p-0 page-link">
          <svg fill="currentColor" preserveAspectRatio="xMidYMid meet" height="12" width="12" class=""
            id="abae7265-d86f-4afc-bc36-ad71c61cb971" data-name="레이어 1" xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 18 18" style="vertical-align: middle; color: currentcolor; transform: rotate(90deg);">
            <path
              d="M9,4.25a2,2,0,1,1,2-2A2,2,0,0,1,9,4.25ZM11,9a2,2,0,1,0-2,2A2,2,0,0,0,11,9Zm0,6.75a2,2,0,1,0-2,2A2,2,0,0,0,11,15.75Z"
              fill="#90929e"></path>
          </svg>
        </button>
      </li>

      <!-- 다음 페이지 버튼 -->
      <li class="page-item" v-if="currentPage < totalPages">
        <button @click.prevent="$emit('change-page', currentPage + 1)" aria-label="pagination-next"
          class="_2Hgkt3 btn d-flex p-0 page-link">
          <svg fill="currentColor" width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M6.65228 12.7021L5.73328 11.7821L9.51628 8.0001L5.73328 4.2171L6.65228 3.2981L11.3553 8.0001L6.65228 12.7021Z">
            </path>
          </svg>
        </button>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "PaginationComponent",
  props: {
    currentPage: {
      type: Number,
      required: true
    },
    totalPages: {
      type: Number,
      required: true
    }
  },
  computed: {
    visiblePages() {
      let pages = [];
      let startPage = 1;
      let endPage = 5;

      // 마지막 페이지 선택 시 로직 조정
      if (this.currentPage === this.totalPages && this.totalPages > 5) {
        startPage = this.totalPages - 4;
        endPage = this.totalPages;
      } else if (this.currentPage > 3) {
        // 현재 페이지 주변 페이지 번호 표시
        startPage = Math.max(this.currentPage - 2, 1);
        endPage = Math.min(startPage + 4, this.totalPages);
      }

      for (let i = startPage; i <= endPage; i++) {
        pages.push(i);
      }
      return pages;
    },
    showJumpForward() {
      return this.currentPage + 3 < this.totalPages;
    },
  },
  methods: {
    jumpForward() {
      const nextPage = Math.min(this.currentPage + 3, this.totalPages - 1);
      this.$emit('change-page', nextPage);
    },
  }
};
</script>

<style scoped>
.justify-content-center {
  justify-content: center !important;
}

.py-0 {
  padding-top: 0 !important;
  padding-bottom: 0 !important;
}

@media (min-width: 768px) {
  .py-md-4 {
    padding-top: 1.5rem !important;
    padding-bottom: 1.5rem !important;
  }
}

:not(svg) {
  transform-origin: 0px 0px;
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

.d-flex {
  display: flex !important;
}

* {
  margin: 0;
  line-height: 1.5;
}

*,
::after,
::before {
  box-sizing: border-box;
}

article,
aside,
figcaption,
figure,
footer,
header,
hgroup,
main,
nav,
section {
  display: block;
}

.pagination {
  --bs-pagination-padding-x: 0.5125rem;
  --bs-pagination-padding-y: 0.625rem;
  --bs-pagination-font-size: 0.9375rem;
  --bs-pagination-color: #697a8d;
  --bs-pagination-bg: #f0f2f4;
  --bs-pagination-border-width: 0px;
  --bs-pagination-border-color: #d9dee3;
  --bs-pagination-border-radius: 0.25rem;
  --bs-pagination-hover-color: #697a8d;
  --bs-pagination-hover-bg: #e1e4e8;
  --bs-pagination-hover-border-color: rgba(67, 89, 113, 0.3);
  --bs-pagination-focus-color: #697a8d;
  --bs-pagination-focus-bg: #e1e4e8;
  --bs-pagination-focus-box-shadow: none;
  --bs-pagination-active-color: #fff;
  --bs-pagination-active-bg: rgba(105, 108, 255, 0.08);
  --bs-pagination-active-border-color: rgba(105, 108, 255, 0.08);
  --bs-pagination-disabled-color: #a1acb8;
  --bs-pagination-disabled-bg: #f7f8f9;
  --bs-pagination-disabled-border-color: var(--bs-border-color);
  display: flex;
  padding-left: 0;
  list-style: none;
}

dl,
ol,
ul {
  margin-top: 0;
  margin-bottom: 1rem;
}

.pagination {
  display: flex;
  padding-left: 0;
  list-style: none;
  border-radius: 0.5rem;
}

.pagination {
  margin-left: -0.125rem;
}

.page-item {
  margin: 0 0.125rem;
}

[role="button"] {
  cursor: pointer;
}

button,
select {
  text-transform: none;
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
  --bs-btn-focus-box-shadow: 0 0 0 0.05rem rgba(var(--bs-btn-focus-shadow-rgb), 0.5);
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
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border: var(--bs-btn-border-width) solid var(--bs-btn-border-color);
  border-radius: var(--bs-btn-border-radius);
  background-color: var(--bs-btn-bg);
  transition: all 0.2s ease-in-out;
}

.page-link {
  position: relative;
  display: block;
  padding: var(--bs-pagination-padding-y) var(--bs-pagination-padding-x);
  font-size: var(--bs-pagination-font-size);
  color: var(--bs-pagination-color);
  background-color: var(--bs-pagination-bg);
  border: var(--bs-pagination-border-width) solid var(--bs-pagination-border-color);
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
    border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.btn {
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.page-link,
.page-link>a {
  border-radius: 0.375rem;
  line-height: 1;
  text-align: center;
  min-width: calc(2.1875rem + calc(0px * 2));
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

.btn {
  display: inline-block;
  font-weight: 500;
  color: #2b2d36;
  text-align: center;
  vertical-align: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  background-color: transparent;
  border: 0 solid transparent;
  padding: 0.3125rem 0.75rem;
  font-size: 0.875rem;
  line-height: 1.375rem;
  border-radius: var(--border-radius-300);
  transition: color 0.12s ease, background-color 0.12s ease,
    border-color 0.12s ease, box-shadow 0.12s ease;
}

.btn {
  height: 2rem;
  padding: 0.3125rem 0.75rem;
  font-size: 0.875rem;
  line-height: 1.375rem;
  border-radius: var(--border-radius-300);
  transition: color 0.12s ease, background-color 0.12s ease,
    border-color 0.12s ease, box-shadow 0.12s ease;
}

.page-link {
  position: relative;
  display: block;
  padding: 0.3125rem 0.75rem;
  margin-left: 0;
  line-height: 1.5;
  color: var(--text-alternative);
  background-color: transparent;
  border: 0 solid transparent;
}

.page-link {
  font-weight: 400;
  border-radius: 0.5rem;
}

.d-flex {
  display: flex !important;
}

.p-0 {
  padding: 0 !important;
}

.page-item .page-link {
  border-radius: 5px;
}

.btn:not([class*="btn-"]):active,
.btn:not([class*="btn-"]).active,
.btn:not([class*="btn-"]).show,
.btn:not([class*="btn-"]) {
  border: none;
}

.btn:not([class*="btn-"]):active,
.btn:not([class*="btn-"]).active,
.btn:not([class*="btn-"]).show,
.btn:not([class*="btn-"]) {
  border: none;
}

.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.page-item:first-child .page-link {
  margin-left: 0;
  border-top-left-radius: 0.5rem;
  border-bottom-left-radius: 0.5rem;
}

svg {
  overflow: hidden;
  vertical-align: middle;
}

.page-item.active .page-link {
  margin: 0 0.1rem 0 0.3rem;
}

.page-item.active .page-link,
.page-item.active .page-link:hover,
.page-item.active .page-link:focus {
  z-index: 3;
  border-color: #696cff;
  background-color: #541d7a;
  color: #fff;
  box-shadow: 0 0.125rem 0.25rem rgba(105, 108, 255, 0.4);
}


.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.page-link:hover {
  z-index: 2;
  color: var(--bs-pagination-hover-color);
  background-color: #e1e4e8;
  border-color: var(--bs-pagination-hover-border-color);
}

.page-item.active .page-link,
.page-item.active .page-link:hover,
.page-item.active .page-link:focus,
.pagination li.active>a:not(.page-link),
.pagination li.active>a:not(.page-link):hover,
.pagination li.active>a:not(.page-link):focus {
  border-color: #696cff;
  background-color: #541d7a;
  color: #fff;
  box-shadow: 0 0.125rem 0.25rem rgba(105, 108, 255, 0.4);
}

.page-item.active .page-link {
  z-index: 3;
  color: white;
  background-color: rgba(84, 29, 112, 0.426);
  border-color: transparent;
}

.page-item.active .page-link {
  font-weight: 500;
}

.page-item:not(:first-child) .page-link {
  margin-left: 0.1875rem;
}

.page-item:last-child .page-link[data-v-0caa4f03] {
  border-top-right-radius: 0.5rem;
  border-bottom-right-radius: 0.5rem;
}
</style>
