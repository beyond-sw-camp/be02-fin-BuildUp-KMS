import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import AuthSignupPage from "@/pages/AuthSignupPage.vue";
import SignupPage from '@/pages/SignupPage.vue';
import MyActivePage from "@/pages/MyActivePage.vue";

const routes = [
  { path: "/", component: MainPage },
  { path: '/authsignup', component: AuthSignupPage },
  { path: '/signup', component: SignupPage},
  { path: '/myactive', component: MyActivePage}
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});


export default router;