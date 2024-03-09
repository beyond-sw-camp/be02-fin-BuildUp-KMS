<template>
  <div class="css-vsssfb">
    <div class="css-110bgim">
      <div class="css-28nsux">
        <div class="css-19831hi">#백엔드</div>
        <div class="css-nw8p9d">#프론트</div>
        <div class="css-nw8p9d">#css</div>
        <div class="css-nw8p9d">#vue</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "HotTagComponent",
};
</script>

<style>
.css-vsssfb {
    display: flex;
    flex-direction: column;
    gap: 32px;
    position: sticky;
    top: 140px;
    max-height: 490px;
    transition: all 0.4s ease 0s;
}
.css-110bgim {
    width: 180px;
    display: flex;
    flex-direction: column;
    gap: 16px;
    background-color: rgb(255, 255, 255);
    flex-shrink: 0;
}
.css-28nsux {
    display: flex;
    flex-direction: column;
    gap: 8px;
    width: 100%;
}
.css-19831hi {
  width: 100%;
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
  color: rgb(20, 22, 23);
  cursor: pointer;
}
.css-nw8p9d {
    width: 100%;
    border-radius: 8px;
    padding: 0px 16px;
    background-color: rgb(255, 255, 255);
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
