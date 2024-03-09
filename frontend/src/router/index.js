import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import AuthSignupPage from "@/pages/AuthSignupPage.vue";
import SignupPage from '@/pages/SignupPage.vue';
import MyActivePage from "@/pages/MyActivePage.vue";
import SearchResultPage from '@/pages/SearchResultPage.vue';
import BoardWritePage from '@/pages/BoardWritePage.vue';
import ReviewWritePage from '@/pages/ReviewWritePage.vue';
import StudyBoardPage from '@/pages/StudyBoardPage.vue'
import MainSignupPage from '@/pages/MainSignupPage.vue'
import BoardDetailsPage from '@/pages/BoardDetailsPage.vue'


const routes = [
  { path: "/", component: MainPage },
  { path: '/authsignup', component: AuthSignupPage },
  { path: '/signup', component: SignupPage},
  { path: '/myactive', component: MyActivePage},
  { path: '/result', component: SearchResultPage},
  { path: '/boardwrite', component: BoardWritePage},
  { path: '/reviewwrite', component: ReviewWritePage},
  { path: '/study', component: StudyBoardPage},
  { path: '/board/detail', component: BoardDetailsPage},
  { path: '/mainsignup', component: MainSignupPage},
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});


export default router;