<template>
  <div class="css-fortagcomponentgms">
    <div v-for="(tagName, index) in tagNameList" :key="index" class="css-1kc14yj" :id="tagName.idx">
      <!-- 객체인 경우 tagname 속성 사용 -->
      <template v-if="typeof tagName === 'object'">
        # {{ tagName.tagname }}
      </template>
      <!-- 문자열인 경우 tagName 직접 렌더링 -->
      <template v-else>
        # {{ tagName }}
      </template>
    </div>
  </div>
</template>


<script>
export default {
  name: "TagComponent",
  props: {
    tagNameList: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>
.css-1kc14yj {
  font-family: Pretendard;
  cursor: pointer;
  width: auto;
  padding: 3px 8px;
  /* margin-top: 10px; */
  border-radius: 5px;
  background-color: rgba(84, 29, 112, 0.148);
  font-size: 11px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  color: #000000cf;
}
.css-fortagcomponentgms{
  display: flex;
  gap: 10px;
  min-height: 22px;
}
</style>
