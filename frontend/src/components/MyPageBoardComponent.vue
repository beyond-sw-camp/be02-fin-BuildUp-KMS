<template>
  <div class="css-aw1sgr">
    <div class="css-amlmv6">
      <div class="css-1254q6y">답변 대기중</div>
    </div>
    <div class="css-kem115">
      <div class="css-12i5occ">
        <div class="css-1jibmi3">
          <a
            :href="
              totals.type === 'review'
                ? '/review/' + totals.idx
                : '/board/' + totals.idx
            "
            ><div class="css-cp47oo">
              {{ totals.title }}
            </div>
            <div class="css-14bssip">
              {{ totals.content }}
            </div>
          </a>
        </div>
        <!--태그 컴포넌트 자리-->
        <TagComponent
          :tagNameList="totals.tagNameList"
          v-if="totals.tagNameList && totals.tagNameList.length > 0"
        />
      </div>
      <button
        class="updateboardgms"
        v-show="totals.boardType === 'write'"
        @click="moveToUpdate()"
      >
        ⚙ 수정
      </button>
      <button
        class="deleteboardgms"
        @click="deleteBoard()"
        v-show="totals.boardType === 'write'"
      >
        삭제
      </button>
      <button
        class="deleteboardgms"
        @click="deleteScrap()"
        v-show="totals.boardType === 'scrap'"
      >
        취소
      </button>
    </div>
    <div class="css-99cwur">
      <div class="css-1ry6usa">{{ this.$moment.utc(totals.createdAt).local().format('YYYY-MM-DD HH:mm:ss') }}</div>
      <div class="css-o01lup">
        <div class="css-ts29it">
          <div class="css-1ry6usa">댓글</div>
          <div class="css-1ry6usa">{{ totals.commentCnt }}</div>
        </div>
        <div class="css-1yw6m61-1"></div>
        <div class="css-ts29it">
          <div class="css-1ry6usa">추천</div>
          <div class="css-1ry6usa">{{ totals.upCnt }}</div>
        </div>
        <div class="css-1yw6m61-1"></div>
        <div class="css-ts29it">
          <div class="css-1ry6usa">조회수</div>
          <div class="css-1ry6usa">{{ totals.viewCnt }}</div>
        </div>
      </div>
    </div>
  </div>
  <div v-show="isActive" class="layer pop_product pop_shopping_bag">
    <div class="layer_cont" style="margin: -140.5px 0px 0px -225px">
      <p>
        게시글을 정말로 삭제하시겠습니까?
        <br />
        삭제한 게시글은 되돌릴 수 없습니다.
      </p>
      <div class="btn_wrap">
        <button type="button" class="btn gray small" @click="cancelDelete">
          취소하기
        </button>
        <button type="button" class="btn black small" @click="checkDelete">
          삭제하기
        </button>
      </div>
      <div data-v-5c833af0="" class="css-myjkxi" @click="closeModal">
        <svg
          data-v-5c833af0=""
          width="20"
          height="20"
          viewBox="0 0 20 20"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            data-v-5c833af0=""
            d="M4.16663 4.16669L15.8333 15.8334"
            stroke="#3A3E41"
            stroke-width="1.75"
            stroke-linecap="round"
          ></path>
          <path
            data-v-5c833af0=""
            d="M15.8334 4.16669L4.16671 15.8334"
            stroke="#3A3E41"
            stroke-width="1.75"
            stroke-linecap="round"
          ></path>
        </svg>
      </div>
    </div>
    <span></span>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useTotalStore } from "@/stores/useTotalStore";
import TagComponent from "@/components/TagComponent.vue";

export default {
  name: "MyPageBoardComponent",
  props: ["totals"],
  data() {
    return {
      showMyPageConfirmDialog: false,
      isModalOpen: false,
      isActive: false,
    };
  },
  computed: {
    ...mapStores(useTotalStore, ["tagNameList"]), // mapStores 함수 사용 수정
  },
  methods: {
    openModal() {
      this.isActive = true;
    },
    closeModal() {
      this.isActive = false;
    },
    moveToUpdate() {
      if (this.totals.type === "review") {
        this.$router.push("/review/mywrite/" + this.totals.idx);
      } else {
        this.$router.push("/board/mywrite/" + this.totals.idx);
      }
    },
    async checkDelete() {
      if (this.totals.type === "board") {
        await this.totalStore.deleteBoard(this.totals.idx);
      } else {
        await this.totalStore.deleteReview(this.totals.idx);
      }
      this.showMyPageConfirmDialog = false;
    },
    cancelDelete() {
      this.isActive = false;
    },
    deleteBoard() {
      this.isActive = true;
    },
    async deleteScrap() {
      if (this.totals.type === "board") {
        await this.totalStore.deleteBoardScrap(this.totals.scrapIdx);
      } else {
        await this.totalStore.deletReviewScrap(this.totals.scrapIdx);
      }
    },
  },
  components: {
    TagComponent,
  },
};
</script>

<style scoped>
.css-aw1sgr {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 24px 16px;
  background-color: rgb(255, 255, 255);
  gap: 16px;
}
@media (min-width: 820px) {
  .css-aw1sgr {
    padding: 24px 0px;
    border-bottom: 1px solid rgb(228, 235, 240);
    gap: 14px;
    cursor: pointer;
  }
}
.css-aw1sgr:hover {
  background-color: rgb(242, 246, 248);
}
.css-1728m7d {
  font-family: Pretendard;
  font-style: bold;
  font-weight: 400;
  font-size: 10px;
  line-height: 18px;
  display: flex;
  gap: 4px;
  margin-top: 6px;
  color: rgb(129, 137, 143);
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
.css-kem115 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
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
.css-14bssip {
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-all;
  margin-bottom: 10px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  color: rgb(95, 102, 107);
  word-break: keep-all;
  height: 38px;
}
@media (min-width: 820px) {
  .css-14bssip {
    overflow: hidden;
    word-break: break-all;
    text-overflow: ellipsis;
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 20px;
    color: rgb(95, 102, 107);
  }
}
.css-sebsp7 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  height: 18px;
}
.css-1ry6usa {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
}
.css-99cwur {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  height: 24px;
}
.css-o01lup {
  display: none;
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
.css-ts29it {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
}
.css-1yw6m61-1 {
  width: 1px;
  height: 12px;
  background-color: #c7d2d8;
  margin-left: 2px;
  margin-right: 2px;
}
.css-1728m7d span {
  background: rgba(84, 29, 112, 0.426);
  font-family: Pretendard;
  font-style: normal;
  font-size: 12px;
  line-height: 18px;
  display: inline-block;
  border-radius: 5px;
  padding: 2px 5px;
  gap: 10px;
  font-weight: 500;
  color: rgb(255, 255, 255) !important;
}
.updateboardgms {
  font-family: Pretendard;
  cursor: pointer;
  width: 60px;
  padding: 3px 1px;
  margin-left: 5px;
  border-radius: 5px;
  border-color: rgba(0, 0, 0, 0.285);
  background-color: rgba(84, 29, 112, 0);
  font-size: 11px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  color: #000000cf;
}
.deleteboardgms {
  font-family: Pretendard;
  cursor: pointer;
  width: 60px;
  padding: 3px 8px;
  border-radius: 5px;
  border-color: rgba(0, 0, 0, 0.285);
  background-color: rgba(255, 0, 0, 0.185);
  font-size: 11px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  color: #000000cf;
}
.modal {
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 어둡게 함 */
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
}

a {
  text-decoration: none;
}

/*------------확인 모달창-------------*/
.layer.active,
.layer_ov_ly.actve {
  display: block;
}
.layer *,
.layer_ov_ly * {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 40px 40px 50px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 330px;
  border-radius: 15px;
  border-color: white;
}
.layer.pop_shopping_bag p {
  padding: 40px 0 15px;
  font-family: "Pretendard";
  font-size: 16px;
  font-weight: 500px;
  line-height: 1.9;
  text-align: center;
}
.layer.pop_product .btn_wrap {
  margin-top: 0px;
  text-align: center;
  margin-bottom: 20px;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 10px 20px 10px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 330px;
}
a.btn,
button.btn,
input.btn,
span.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}
a.btn.gray,
button.btn.gray,
input.btn.gray,
span.btn.gray {
  border-color: #7d7d7d;
  background-color: #7d7d7d;
  color: #fff;
}
a.btn.small,
button.btn.small,
input.btn.small,
span.btn.small {
  height: 40px;
  font-size: 12px;
  line-height: 38px;
}
.layer.pop_product .btn_wrap .btn {
  min-width: 100px;
  margin: 0 8px;
}
a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}
.layer_cont .btn_close,
.layer_cont .pop_in_pop_close {
  overflow: hidden;
  position: absolute;
  top: 25px;
  right: 25px;
  width: 53px;
  height: 53px;
  padding: 15px;
  line-height: 99em;
  vertical-align: top;
  background-color: white;
  border-color: white;
}
.layer_cont .btn_close:before,
.layer_cont .pop_in_pop_close:before {
  display: block;
  width: 12px;
  height: 12px;
  background: url(//static.wconcept.co.kr/web/images/common/layer_close_23.png)
    no-repeat;
  background-size: 100%;
  content: "";
}
.layer > span,
.layer_ov_ly > span {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #0e0e0e;
  opacity: 0.4;
  filter: alpha(opacity=40);
  content: "";
  display: block;
}

.css-myjkxi {
  position: absolute;
  top: 18px;
  right: 16px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(58, 62, 65);
  display: flex;
  align-items: center;
  gap: 4px;
  cursor: pointer;
}
</style>
