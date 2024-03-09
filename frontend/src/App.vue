<template>
  <div>
    <template v-if="!hideHeaderAndFooter">
      <HeaderComponent />
      <router-view />
      <FooterComponent />
      <QuickMenuComponent />
    </template>
    <template v-else>
      <router-view />
    </template>
  </div>
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
  data() {
    return {
      hideHeaderAndFooter: false,
    };
  },
};
</script>
<style></style>