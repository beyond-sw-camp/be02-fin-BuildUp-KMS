<template>
  <!-- <div class="css-19831hi">#백엔드</div> -->
  <div class="css-19831hi" :id="hotTags.tagIdx" @click="showTagBoardList">
    # {{ hotTags.tagName }}
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useBoardStore } from "@/stores/useBoardStore";

export default {
  name: "HotTagComponent",
  props: ["hotTags"],
  computed: {
    ...mapStores(useBoardStore),
  },
  methods: {
    showTagBoardList() {
      this.boardStore.tagIdx = this.hotTags.tagIdx;
      this.boardStore.tagName = this.hotTags.tagName;
      // window.location.href = "/board/tag"
      this.$router.push({ path: `/board/tag` });
    },
  },
};
</script>

<style>
.css-19831hi {
  width: 80%;
  border-radius: 8px;
  padding: 0px 16px;
  /* 클릭하면 배경이 아래 색상으로 변경됨 */
  background-color: rgb(242, 246, 248);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 16px;
  line-height: 48px;
  font-weight: 700;
  color: rgb(95, 102, 107);
  cursor: pointer;
}
.css-nw8p9d {
  width: 100%;
  border-radius: 8px;
  padding: 0px 16px;
  background-color: white;
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 16px;
  line-height: 48px;
  font-weight: 400;
  color: rgb(157, 167, 174);
  cursor: pointer;
}
</style>
