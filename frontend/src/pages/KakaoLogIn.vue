<template>
  <div></div>
</template>

<script>
export default {
  created() {
    const accessToken = this.$route.query.accessToken;
    const refreshToken = this.$route.query.refreshToken;

    if (accessToken && refreshToken) {
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      // window.location.href="/";
      // this.$router.push('/');
      const returnUrl = localStorage.getItem("preLoginUrl") || "/";
      localStorage.removeItem("preLoginUrl");
      window.location.href=returnUrl;
    } else {
      window.alert("로그인에 실패하였습니다.");
      // window.location.href="/";
    }
  },
};
</script>

<style scoped></style>
