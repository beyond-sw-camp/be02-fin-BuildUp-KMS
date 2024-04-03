<template>
  <div class="css-1agvh3p" @click="showContent()">
    <div class="css-1x8gxrp">
      <div :class="{ 'css-s3qw6k': !isClicked, 'css-1b9opqm': isClicked }">
        <span class="css-0">{{ notice.title }}</span
        ><span class="css-1wp0bsh"></span><span class="css-1hkyyfd">N</span>
      </div>
    </div>
    <div class="css-ktviac">
      <div class="css-1p269h3">
        <span class="css-1d3nptd">24.02.16</span>
      </div>
      <div :class="{ 'css-wh2oxb': !isClicked, 'css-1ks2stu': isClicked }">
        <svg
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M6.70711 9.29289C6.31658 8.90237 5.68342 8.90237 5.29289 9.29289C4.90237 9.68342 4.90237 10.3166 5.29289 10.7071L6.70711 9.29289ZM12 16L11.2929 16.7071C11.6834 17.0976 12.3166 17.0976 12.7071 16.7071L12 16ZM18.7071 10.7071C19.0976 10.3166 19.0976 9.68342 18.7071 9.29289C18.3166 8.90237 17.6834 8.90237 17.2929 9.29289L18.7071 10.7071ZM5.29289 10.7071L11.2929 16.7071L12.7071 15.2929L6.70711 9.29289L5.29289 10.7071ZM12.7071 16.7071L18.7071 10.7071L17.2929 9.29289L11.2929 15.2929L12.7071 16.7071Z"
            fill="#141617"
          ></path>
        </svg>
      </div>
    </div>
  </div>
  <div :class="{ 'css-173r1bj': !isClicked, 'css-gx45j9': isClicked }">
    {{ notice.content }}
  </div>
</template>
<script>
export default {
  name: "NoticeBoardComponent",
  props: ["notice"],
  data() {
    return {
      isClicked: false,
    };
  },
  methods: {
    showContent() {
      this.isClicked = !this.isClicked;
      this.$emit("select", this.isClicked); // 클릭 상태를 함께 전달
    },
  },
};
</script>
<style scoped>
* {
  margin: 0;
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
}

.css-1agvh3p {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  gap: 24px;
  width: 100%;
  cursor: pointer;
}

@media (min-width: 768px) {
  .css-1agvh3p {
    gap: 40px;
  }
}
.css-1x8gxrp {
  display: flex;
  flex-direction: column;
  gap: 4px;
  width: calc(100% - 44px);
  margin-left: -20px;
}

.css-s3qw6k {
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 150%;
  color: rgb(58, 62, 65);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  white-space: pre-wrap;
  align-items: center;
  display: inline-block;
}

@media (min-width: 768px) {
  .css-s3qw6k {
    display: unset;
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    line-height: 150%;
    color: rgb(58, 62, 65);
    font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  }
}
.css-1b9opqm {
  font-style: normal;
  font-size: 14px;
  line-height: 150%;
  color: rgb(58, 62, 65);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  white-space: pre-wrap;
  -webkit-box-align: center;
  align-items: center;
  display: inline-block;
  font-weight: 700;
}

@media (min-width: 768px) {
  .css-1b9opqm {
    display: unset;
    font-style: normal;
    font-size: 16px;
    line-height: 150%;
    color: rgb(58, 62, 65);
    font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
    font-weight: 700;
  }
}

.css-1wp0bsh {
  width: 0px;
  height: 100%;
  margin-right: 6px;
}

@media (min-width: 768px) {
  .css-1wp0bsh {
    margin-right: 12px;
  }
}

.css-1hkyyfd {
  background: rgb(232, 52, 78);
  display: inline-block;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  border-radius: 4px;
  color: rgb(255, 255, 255);
  width: 18px;
  height: 18px;
  font-size: 10px;
  font-weight: 800;
  flex-shrink: 0;
  line-height: 18px;
  text-align: center;
}

@media (min-width: 768px) {
  .css-1hkyyfd {
    font-size: 12px;
    width: 20px;
    height: 20px;
    line-height: 20px;
  }
}

.css-ktviac {
  display: flex;
  gap: 40px;
  width: fit-content;
  align-items: center;
  justify-content: space-between;
}

.css-1d3nptd {
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 150%;
  color: rgb(180, 191, 198);
  font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
}

@media (min-width: 768px) {
  .css-1d3nptd {
    font-style: normal;
    font-weight: 400;
    font-size: 16px;
    line-height: 150%;
    color: rgb(180, 191, 198);
    font-family: __pretendard_1fdc56, __pretendard_Fallback_1fdc56;
  }
}

.css-wh2oxb {
  cursor: pointer;
  transition: all 0.4s ease 0s;
}

.css-173r1bj {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;
  color: rgb(129, 137, 143);
  height: fit-content;
  overflow: hidden;
  transition: all 0.4s ease 0s;
  max-height: 0px;
  padding: 24px 44px 0px 0px;
  font-size: 14px;
}

@media (min-width: 768px) {
  .css-173r1bj {
    padding: 32px 64px 0px 0px;
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    line-height: 150%;
    color: rgb(129, 137, 143);
    font-size: 16px;
  }
}

.css-173r1bj p {
  color: rgb(95, 102, 107);
  line-height: 150%;
  font-weight: 500;
}

.css-173r1bj strong {
  font-weight: 700;
  color: rgb(58, 62, 65) !important;
}

.css-1ks2stu {
  cursor: pointer;
  transform: rotate(-180deg);
  transition: all 0.4s ease 0s;
}
.css-gx45j9 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  line-height: 150%;
  color: rgb(129, 137, 143);
  height: fit-content;
  overflow: hidden;
  transition: all 0.4s ease 0s;
  max-height: unset;
  padding: 24px 44px 0px 0px;
  font-size: 14px;
}
@media (min-width: 768px) {
  .css-gx45j9 {
    padding: 32px 64px 0px 0px;
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    line-height: 150%;
    color: rgb(129, 137, 143);
    font-size: 16px;
  }
}
.css-gx45j9 p {
  color: rgb(95, 102, 107);
  line-height: 150%;
  font-weight: 500;
}
.css-gx45j9 strong {
  font-weight: 700;
  color: rgb(58, 62, 65) !important;
}
</style>
