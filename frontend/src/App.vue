<template>
  <HeaderComponent/>
  <router-view />
  <FooterComponent />
  <QuickMenuComponent />
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
import FooterComponent from "./components/FooterComponent.vue";
import QuickMenuComponent from "./components/QuickMenuComponent.vue";

export default {
  name: "App",
  components: {
    HeaderComponent,
    FooterComponent,
    QuickMenuComponent,
  },
};
</script>

<style></style>
