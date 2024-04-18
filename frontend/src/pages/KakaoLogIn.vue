<template>
  <div></div>
</template>

<script>
import router from "@/router";

export default {
  created() {
    const accessToken = this.$route.query.accessToken;
    const refreshToken = this.$route.query.refreshToken;


    if (accessToken && refreshToken) {
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      // window.location.href="/";
      router.push({ path: "/" });
    } else {
      window.alert("로그인에 실패하였습니다.");
      // window.location.href="/";
    }
  },
};
</script>

<style scoped></style>
