<template>
  <HeaderComponent />
  <router-view />
  <QuickMenuComponent />
  <FooterComponent />
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
import QuickMenuComponent from "./components/QuickMenuComponent.vue";
import FooterComponent from "./components/FooterComponent.vue";

export default {
  name: "App",
  components: {
    HeaderComponent,
    QuickMenuComponent,
    FooterComponent,
  },
};
</script>

<style></style>
