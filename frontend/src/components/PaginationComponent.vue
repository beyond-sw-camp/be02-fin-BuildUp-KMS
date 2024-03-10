<template>
  <div class="d-flex justify-content-center py-0 py-md-4">
    <nav class="" aria-label="pagination">
      <ul class="_1VYQb4 pagination">
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-prev"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              <svg
                fill="currentColor"
                width="16"
                height="16"
                viewBox="0 0 16 16"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M9.33838 12.7021L4.63538 8.0001L9.33838 3.2981L10.2574 4.2171L6.47438 8.0001L10.2574 11.7821L9.33838 12.7021Z"
                ></path>
              </svg>
            </button>
          </div>
        </li>
        <li class="page-item active">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-1"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              1
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-2"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              2
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-3"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              3
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-4"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              4
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-5"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              5
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-6"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              <svg
                fill="currentColor"
                preserveAspectRatio="xMidYMid meet"
                height="12"
                width="12"
                class=""
                id="abae7265-d86f-4afc-bc36-ad71c61cb971"
                data-name="레이어 1"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 18 18"
                style="
                  vertical-align: middle;
                  color: currentcolor;
                  transform: rotate(90deg);
                "
              >
                <path
                  d="M9,4.25a2,2,0,1,1,2-2A2,2,0,0,1,9,4.25ZM11,9a2,2,0,1,0-2,2A2,2,0,0,0,11,9Zm0,6.75a2,2,0,1,0-2,2A2,2,0,0,0,11,15.75Z"
                  fill="#90929e"
                ></path>
              </svg>
            </button>
          </div>
        </li>
        <li class="page-item">
          <div role="button" tabindex="-1">
            <button
              aria-label="pagination-next"
              class="_2Hgkt3 btn d-flex p-0 page-link"
            >
              <svg
                fill="currentColor"
                width="16"
                height="16"
                viewBox="0 0 16 16"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M6.65228 12.7021L5.73328 11.7821L9.51628 8.0001L5.73328 4.2171L6.65228 3.2981L11.3553 8.0001L6.65228 12.7021Z"
                ></path>
              </svg>
            </button>
          </div>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "PaginationComponent",
};
</script>

<style scoped>
* {
  margin: 0;
  line-height: 1.5;
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

*,
::after,
::before {
  box-sizing: border-box;
}
.page-item {
  margin: 0 0.125rem;
}
[role="button"] {
  cursor: pointer;
}
.btn {
  display: inline-block;
  font-weight: 500;
  color: #2b2d36;
  text-align: center;
  vertical-align: middle;
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
.page-item {
  margin: 0 0.125rem;
}
.page-item.active .page-link,
.page-item.active .page-link:hover,
.page-item.active .page-link:focus,
.pagination li.active > a:not(.page-link),
.pagination li.active > a:not(.page-link):hover,
.pagination li.active > a:not(.page-link):focus {
  border-color: #696cff;
  background-color: #541d7a; /* 마우스 포인터 올렸을 때 배경색 */
  color: #fff;
  box-shadow: 0 0.125rem 0.25rem rgba(105, 108, 255, 0.4);
}
.page-item.active .page-link {
  z-index: 3;
  color: white; /*글자색*/
  background-color: rgba(84, 29, 112, 0.426); /* 버튼 배경색 */
  border-color: transparent;
}
.page-item.active .page-link {
  font-weight: 500;
}
.page-item:not(:first-child) .page-link {
  margin-left: 0.1875rem;
}

.page-item .page-link {
  border-radius: var(--bs-pagination-border-radius);
}
.btn:not([class*="btn-"]):active,
.btn:not([class*="btn-"]).active,
.btn:not([class*="btn-"]).show,
.btn:not([class*="btn-"]) {
  border: none;
}
.page-item:last-child .page-link {
  border-top-right-radius: 0.5rem;
  border-bottom-right-radius: 0.5rem;
}
</style>
