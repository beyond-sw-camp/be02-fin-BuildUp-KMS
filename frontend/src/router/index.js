import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import AuthSignupPage from "@/pages/AuthSignupPage.vue";
import SignupPage from '@/pages/SignupPage.vue';
import BoardWritePage from '@/pages/BoardWritePage.vue';
import ReviewWritePage from '@/pages/ReviewWritePage.vue';


const routes = [
  { path: "/", component: MainPage },
  { path: '/authsignup', component: AuthSignupPage },
  { path: '/signup', component: SignupPage},
  { path: '/boardwrite', component: BoardWritePage},
  { path: '/reviewwrite', component: ReviewWritePage},

];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});


export default router;