<template>
  <div>
    <div v-for="(tag, index) in boardtags" :key="index" class="css-1kc14yj">
      {{ tag.name }}
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useBoardStore } from "@/stores/useBoardStore";

export default {
  name: "TagComponent",
  props: {
    // Board의 태그 목록을 전달받음
    boardtags: {
      type: Array,
      required: true
    }
  },
  computed: {
    ...mapStores(useBoardStore),
  },
};
</script>

<style scoped>
.css-1kc14yj {
  font-family: Pretendard;
  cursor: pointer;
  width: 45px;
  padding: 2px 0;
  border-radius: 5px;
  background-color: rgba(84, 29, 112, 0.218);
  font-size: 10px;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  color: #000;
}
</style>
