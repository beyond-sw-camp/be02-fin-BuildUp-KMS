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
      <router-link to="/board/new">
        <button class="updateboardgms" v-show="totals.boardType === 'write'">⚙ 수정</button>
      </router-link>
      <button class="deleteboardgms" @click="deleteB" v-show="totals.boardType === 'write'">삭제</button>
      <button class="deleteboardgms" @click="deleteB" v-show="totals.boardType === 'scrap'">취소</button>
      <ConfirmDialogComponent
        v-if="showMyPageConfirmDialog"
        :isVisible="showMyPageConfirmDialog"
        message="For real?"
        :onConfirm="moveMyPage"
        :onCancel="dontMoveMyPage"
      />
      <!--사진-->
    </div>
    <div class="css-99cwur">
      <div class="css-1ry6usa">{{ totals.updatedAt }}</div>
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
</template>

<script>
import { mapStores } from "pinia";
import { useTotalStore } from "@/stores/useTotalStore";
import TagComponent from "@/components/TagComponent.vue";
import ConfirmDialogComponent from "./ConfirmDialogComponent.vue";

export default {
  name: "MyPageBoardComponent",
  props: ["totals"],
  data() {
    return {
      showMyPageConfirmDialog: false,
      isModalOpen: false,
      totalStore: useTotalStore(), // totalStore 객체 초기화
    };
  },
  computed: {
    ...mapStores(useTotalStore, ["tagNameList"]), // mapStores 함수 사용 수정
  },
  methods: {
    async moveMyPage() {
      this.showMyPageConfirmDialog = false;
      try {
        let response = await this.totalStore.deleteBoard(this.totals.idx);
        if (response) {
          console.log("Board deleted successfully:", response);
          window.location.reload();
        } else {
          console.error("Delete request failed: No response data");
        }
      } catch (error) {
        console.error("An error occurred while deleting:", error);
      }
    },
    dontMoveMyPage() {
      this.showMyPageConfirmDialog = false;
      window.location.reload();
    },
    deleteB() {
      this.showMyPageConfirmDialog = true;
    },
  },
  components: {
    TagComponent,
    ConfirmDialogComponent,
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
  margin-bottom: 10px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 21px;
  color: rgb(95, 102, 107);
  word-break: keep-all;
  height: 38px;
}
@media (min-width: 820px) {
  .css-14bssip {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 21px;
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
  width: 52px;
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
</style>
