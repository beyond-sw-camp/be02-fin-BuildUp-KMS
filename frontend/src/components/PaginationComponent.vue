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
  mounted() {
    const link = document.createElement("link");
    link.rel = "stylesheet";
    link.href =
      "https://statics.goorm.io/gds/goormstrap/v1.29.0/goormstrap.v4.min.css";
    document.head.appendChild(link);
  },
};
</script>

<style scoped></style>
