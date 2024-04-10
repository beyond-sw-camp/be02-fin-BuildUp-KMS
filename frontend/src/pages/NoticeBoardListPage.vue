<template>
  <div class="css-hhaf6z">
    <div class="css-1p269h3">
      <div class="css-ks7ago">
        <div class="__className_1fdc56 css-54xzm2">
          <div class="css-12nqfb0"></div>
          <div class="css-e15jw">공지사항</div>
          <div class="css-1g96mzr"></div>
          <div class="css-1ago99h">
            <!----------공지사항 게시글 컴포넌트 반복문---------------->
            <div
              :class="parentClass"
              v-for="notice in boardStore.boardList"
              :key="notice.boardIdx"
            >
              <NoticeBoardComponent
                @select="handleSelect($event)"
                :notice="notice"
              />
            </div>
            <!---검색결과 없을 때-->
            <div class="css-6g4q8b" v-show="!boardStore.isBoardExist">
              <div class="css-aa80it">
                <img src="@/assets/img/002.png" class="css-1baht8c" />
                <div class="css-dhqp8i">
                  <div class="css-c7zvxr">등록된 공지사항이 없습니다.</div>
                </div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center py-0 py-md-4">
            <PaginationComponent
              :current-page="boardStore.currentPage"
              :total-pages="boardStore.totalPages"
              :isPageExist="boardStore.isPageExist"
              @change-page="changePage"
            />
          </div>
          <div class="css-ry6wq5"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapStores } from "pinia";
import { useBoardStore } from "@/stores/useBoardStore.js";
import NoticeBoardComponent from "../components/NoticeBoardComponent.vue";
import PaginationComponent from "../components/PaginationComponent.vue";

export default {
  name: "NoticeBoardListPage",
  components: {
    NoticeBoardComponent,
    PaginationComponent,
  },
  data() {
    return {
      parentClass: "css-1wa49dz",
      sortType: 1,
      boardCategoryIdx: "4",
    };
  },
  computed: {
    ...mapStores(useBoardStore),
  },
  mounted() {
    this.loadBoardList(1);
  },
  methods: {
    handleSelect(isClicked) {
      this.parentClass = isClicked ? "css-1e49g7k" : "css-1wa49dz";
    },
    async loadBoardList(page) {
      await this.boardStore.findListByCategory(
        this.boardCategoryIdx,
        this.sortType,
        page
      );
    },
    changePage(page) {
      this.loadBoardList(page);
    },
    jumpForward() {
      // 현재 페이지에서 3페이지 앞으로 점프
      let nextPage = Math.min(
        this.boardStore.currentPage + 3,
        this.boardStore.totalPages
      );
      this.changePage(nextPage);

      this.boardStore.currentPage = nextPage;
    },
  },
};
</script>
<style scoped>
* {
  margin: 0;
}

body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
}

.css-hhaf6z {
  padding-top: 150px;
}

@media (min-width: 768px) {
  .css-hhaf6z {
    padding-top: 60px;
  }
}

@media (min-width: 768px) {
  .css-1p269h3 {
    display: unset;
  }
}

.css-ks7ago {
  width: 100%;
}

.__className_1fdc56 {
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
}

@media (min-width: 768px) {
  .css-54xzm2 {
    width: auto;
    max-width: 1100px;
    padding: 0 120px;
    margin: auto;
  }
}

.css-12nqfb0 {
  width: 100%;
  height: 0px;
  margin-bottom: 40px;
}

@media (min-width: 768px) {
  .css-12nqfb0 {
    margin-bottom: 100px;
  }
}

.css-e15jw {
  font-style: normal;
  font-weight: 600;
  font-size: 18px;
  line-height: 140%;
  color: rgb(20, 22, 23);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
}

@media (min-width: 768px) {
  .css-e15jw {
    font-style: normal;
    font-weight: 700;
    font-size: 40px;
    line-height: 140%;
    color: rgb(20, 22, 23);
    font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  }
}

.css-1g96mzr {
  width: 100%;
  height: 0px;
  margin-bottom: 36px;
}

@media (min-width: 768px) {
  .css-1g96mzr {
    margin-bottom: 40px;
  }
}

.css-1ago99h {
  width: 100%;
  margin-bottom: 50px;
}

.css-1wa49dz {
  max-height: 27px;
  overflow-y: clip;
  border-bottom: 1px solid rgb(228, 235, 240);
  padding: 26px 0px;
  transition: all 0.4s ease 0s;
}

@media (min-width: 768px) {
  .css-1wa49dz {
    padding: 28px 24px;
  }
}
.css-1e49g7k {
  max-height: unset;
  overflow-y: clip;
  border-bottom: 1px solid rgb(228, 235, 240);
  padding: 26px 0px;
  transition: all 0.4s ease 0s;
}
@media (min-width: 768px) {
  .css-1e49g7k {
    padding: 28px 24px;
  }
}
.css-obiuiw {
  display: flex;
  margin-top: 28px;
  gap: 10px;
}

@media (min-width: 768px) {
  .css-obiuiw {
    margin-top: 60px;
    gap: 20px;
  }
}

.css-1barg9w {
  opacity: 0.3;
  cursor: default;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 36px;
  height: 36px;
}

@media (min-width: 768px) {
  .css-1barg9w {
    width: 40px;
    height: 40px;
  }
}

.css-14wine1 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  gap: 8px;
  width: -webkit-fit-content;
  width: -moz-fit-content;
  width: fit-content;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
}

.css-16agcy4 {
  cursor: pointer;
  background: rgb(238, 243, 246);
  border-radius: 40px;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  color: rgb(58, 62, 65);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  font-weight: 700;
  font-size: 14px;
}

@media (min-width: 768px) {
  .css-16agcy4 {
    width: 40px;
    height: 40px;
    font-size: 16px;
  }
}

.css-na2d47 {
  cursor: pointer;
  background: none;
  border-radius: 40px;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  color: rgb(58, 62, 65);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  font-weight: 500;
  font-size: 14px;
}

@media (min-width: 768px) {
  .css-na2d47 {
    width: 40px;
    height: 40px;
    font-size: 16px;
  }
}

.css-1gdc2h7 {
  opacity: 1;
  cursor: pointer;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 36px;
  height: 36px;
}

@media (min-width: 768px) {
  .css-1gdc2h7 {
    width: 40px;
    height: 40px;
  }
}

.css-ry6wq5 {
  width: 100%;
  height: 0;
  margin-bottom: 60px;
}

@media (min-width: 768px) {
  .css-ry6wq5 {
    margin-bottom: 120px;
  }
}
/*-----------------------풋터------------------------*/
.css-88z093 {
  width: 100%;
  background: #ffffff;
  margin: 0 auto;
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
  -webkit-box-flex: 1;
  -webkit-flex-grow: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  max-width: 1230;
  border-top: 1px solid #e4ebf0;
}

.css-88z093 * {
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  line-height: 1.5;
}

.css-1h9xuna {
  -webkit-box-flex: 1;
  -webkit-flex-grow: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  max-width: 1100px;
  width: 100%;
  margin: 40px auto 100px;
  padding: 0 24px;
}

.css-vn5ffn {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  width: 100%;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
}

.css-1ccrqs4 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-pack: start;
  -ms-flex-pack: start;
  -webkit-justify-content: flex-start;
  justify-content: flex-start;
}

.css-14cspio {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: #141617;
  color: #5f666b;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  padding: 0 4px;
}

.css-tgyf2d {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  margin: 8px 0;
  gap: 6px;
}

.css-w6ze0h {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 20px;
  color: #141617;
  color: #81898f;
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
  gap: 4px;
}

.css-b54cxe {
  padding: 4px;
  cursor: pointer;
}

.css-n0efe5 {
  width: 6px;
}

.css-tkvg23 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  padding: 1px 4px;
  -webkit-align-items: flex-start;
  -webkit-box-align: flex-start;
  -ms-flex-align: flex-start;
  align-items: flex-start;
  gap: 10px;
  border-radius: 4px;
  background: #fff1f4;
  color: #ff6c7a;
  font-family: Pretendard;
  font-size: 10px;
  font-style: normal;
  font-weight: 600;
  line-height: 150%;
}

.css-ajaiss {
  height: 1px;
  -webkit-align-self: stretch;
  -ms-flex-item-align: stretch;
  align-self: stretch;
  width: 100%;
  background: #e4ebf0;
  margin: 24px 0;
}

.css-1qzhyb0 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.css-zkzuyb {
  padding: 0;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  width: 100%;
  font-size: 12px;
  color: #d4d4d4;
  margin-bottom: 12px;
}

.css-t0ptnf {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-box-pack: start;
  -ms-flex-pack: start;
  -webkit-justify-content: flex-start;
  justify-content: flex-start;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.css-516vln {
  padding: 0;
  margin-right: 24px;
  list-style: none;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.css-516vln a {
  font-size: 12px;
}

.css-y8zw8d {
  color: #5f666b;
  font-family: Pretendard;
  font-size: 13px;
  font-style: normal;
  font-weight: 700;
  line-height: 150%;
  white-space: nowrap;
}

.css-1vh48ao {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 20px;
  color: #141617;
  color: #81898f;
  width: 100%;
  white-space: nowrap;
}

.css-k50gal {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
}

.css-16omddv {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #141617;
  height: 32px;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  font-size: 12px;
  color: #9da7ae;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-box-pack: start;
  -ms-flex-pack: start;
  -webkit-justify-content: flex-start;
  justify-content: flex-start;
  white-space: nowrap;
}

.css-hdat01 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #141617;
  font-size: 12px;
  color: #9da7ae;
  display: none;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-box-flex-wrap: wrap;
  -webkit-flex-wrap: wrap;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  gap: 4px;
}

.css-5znsbo {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
}

.css-184cnz3 {
  line-height: 20px;
}

.css-17y1dcm {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  line-height: 20px;
}

.css-v0a2uo {
  font-size: 12px;
  line-height: 20px;
}

.css-4mb2gv {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #141617;
  font-size: 12px;
  color: #9da7ae;
  padding-top: 16px;
}

@media (min-width: 320px) and (max-width: 819px) {
  .css-zkzuyb {
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    gap: 24px;
  }

  .css-w6ze0h {
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
    gap: 6px;
  }

  .css-tgyf2d {
    padding: 16px 12px;
    margin: 0;
  }

  .css-1h9xuna {
    margin: 16px auto 100px;
    padding: 0 16px;
  }

  .css-ajaiss {
    height: 0;
  }

  .css-1qzhyb0 {
    gap: 24px;
  }

  .css-1ccrqs4 {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    padding: 16px 0;
    -webkit-box-pack: start;
    -ms-flex-pack: start;
    -webkit-justify-content: flex-start;
    justify-content: flex-start;
    border-bottom: 1px solid #e4ebf0;
  }

  .css-b54cxe {
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
    gap: 4px;
  }
}

@media (min-width: 820px) {
  .css-hdat01 {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    width: 700px;
  }

  .css-zkzuyb {
    margin-bottom: 0;
    font-size: 14px;
  }
}

@media (min-width: 820px) and (max-width: 1023px) {
  .css-1h9xuna {
    margin: 40px auto 100px;
  }

  .css-vn5ffn {
    display: grid;
    grid-template-rows: repeat(2, 1fr);
    grid-template-columns: repeat(4, 1fr);
  }

  .css-vn5ffn > :first-child {
    grid-row: 1 / span 1;
    grid-column: 1 / span 4;
  }

  .css-vn5ffn > :nth-child(2) {
    grid-row: 2;
    grid-column: 1;
  }

  .css-vn5ffn > :nth-child(3) {
    grid-row: 2;
    grid-column: 2;
  }

  .css-vn5ffn > :nth-child(4) {
    grid-row: 2;
    grid-column: 3;
  }

  .css-vn5ffn > :nth-child(5) {
    grid-row: 2;
    grid-column: 4;
  }
}

@media (min-width: 1024px) {
  .css-1h9xuna {
    margin: 32px auto 100px;
  }

  .css-4mb2gv {
    text-align: right;
  }

  .css-5znsbo {
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
  }

  .css-k50gal {
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    -webkit-align-items: flex-start;
    -webkit-box-align: flex-start;
    -ms-flex-align: flex-start;
    align-items: flex-start;
  }

  .css-vn5ffn {
    display: grid;
    gap: 24px;
    grid-template-columns: 2fr 1fr 1fr 1fr 1fr;
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
