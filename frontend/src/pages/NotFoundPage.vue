<template>
  <div id="wrap">
    <div id="header">
      <h1>
        <router-link to="/">
          <img src="@/assets/img/002.png" alt="NAVER" width="145" height="90" />
        </router-link>
      </h1>
      <p class="menu">
        <router-link to="/">BOOTSHELF 홈</router-link> |
        <router-link to="/">고객센터</router-link>
      </p>
    </div>
    <div id="container">
      <h2>죄송합니다.<br />요청하신 페이지를 찾을 수 없습니다.</h2>
      <div class="content">
        <p>
          방문하시려는 페이지의 주소가 잘못 입력되었거나,<br />페이지의 주소가
          변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.
        </p>
        <p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
        <p>
          관련 문의사항은
          <router-link to="/">BOOTSHELF 고객센터</router-link>에 알려주시면 친절하게 안내해 드리겠습니다.
        </p>
        <p>감사합니다.</p>
      </div>
    </div>
    <div id="footer">
      <address>
        <em>Copyright ©</em>
        <router-link to="/"> BOOTSHELF Corp. </router-link>
        <span>All Rights Reserved.</span>
      </address>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFoundPage",
  mounted() {
    this.$root.hideHeaderAndFooter = true;
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

#wrap {
  width: 800px;
  _height: 618px;
  min-height: 618px;
  margin: 0 auto;
  background: url(@/assets/img/icon_trans.png) no-repeat 100% 109px;
  background-size: 20%;
  background-position: 500px 250px;
  text-align: left;
}

#header {
  overflow: hidden;
  position: relative;
  height: 150px;
}

#header h1 {
  margin: 36px 0 0 26px;
}

a {
  color: rgb(84, 29, 112);
  text-decoration: underline;
}

a {
  cursor: pointer;
}

img,
fieldset {
  border: none;
}

#header .menu {
  overflow: hidden;
  position: absolute;
  top: 23px;
  right: 28px;
  width: 100%;
  color: #d7d7d7;
  letter-spacing: 0px;
  text-align: right;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  color: rgb(28, 29, 30);
}

#header .menu a {
  margin: 0 3px;
  color: #444;
  text-decoration: none;
}

#container {
  padding: 75px 20px 0 81px;
}

#container h2 {
  margin-bottom: 24px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  letter-spacing: 2px;
}

#container .content p {
  margin-bottom: 10px;
}

#footer {
  margin-top: 80px;
  text-align: center;
}

#footer address {
  font: 9px / 14px Verdana;
}

#footer address a {
  font: bold 9px Tahoma;
  color: #333;
}

#footer a:visited {
  text-decoration: none;
  color: #666;
}

#footer address .logo {
  display: inline-block;
  vertical-align: top;
}

#footer address em {
  padding-left: 6px;
  font: 9px verdana;
}

#footer address span {
  padding-left: 2px;
  font: 9px / 14px Verdana;
}

a:hover {
 color: rgb(84, 29, 112);
}

</style>
