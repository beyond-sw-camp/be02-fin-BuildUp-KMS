<template>
  <div></div>
</template>

<script>
import router from "@/router";

export default {
  created() {
    const receivedToken = this.$route.params.token;
    // const currentUrl = window.location.href;
    // const urlParams = new URLSearchParams(currentUrl);
    // const receivedToken = urlParams.get("token");

    console.log("token", receivedToken);
    if (receivedToken) {
      localStorage.setItem("token", receivedToken);
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
