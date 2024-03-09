<template>
  <HeaderComponent/>
  <router-view />
  <FooterComponent />
  <QuickMenuComponent />
  <HeaderComponent v-if="$route.name !== 'MainSignupPage'"></HeaderComponent>
  <FooterComponent v-if="$route.name !== 'MainSignupPage'"></FooterComponent>
 
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
