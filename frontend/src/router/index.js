import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import AuthSignupPage from "@/pages/AuthSignupPage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import MyActivePage from "@/pages/MyActivePage.vue";
import MyProfilePage from "@/pages/MyProfilePage.vue";
import SearchResultPage from "@/pages/SearchResultPage.vue";
import BoardWritePage from "@/pages/BoardWritePage.vue";
import ReviewWritePage from "@/pages/ReviewWritePage.vue";
import StudyBoardPage from "@/pages/StudyBoardPage.vue";
import SelectSignupPage from "@/pages/SelectSignupPage.vue";
import BoardDetailsPage from "@/pages/BoardDetailsPage.vue";
import BoardListPage from "@/pages/BoardListPage.vue";
import ReviewListPage from "@/pages/ReviewListPage.vue";
import EmailValidationPage from "@/pages/EmailValidationPage.vue";
import NoticeBoardPage from "@/pages/NoticeBoardPage.vue";
import AdminMainPage from "@/pages/AdminMainPage.vue";

import AdminWithdrawPage from "@/pages/AdminWithdrawPage.vue";
import AdminCategoryRegisterPage from "@/pages/AdminCategoryRegisterPage.vue";
import AdminTagRegisterPage from "@/pages/AdminTagRegisterPage.vue";

const routes = [
  { path: "/", component: MainPage },
  { path: "/auth/signup", component: AuthSignupPage },
  { path: "/signup", component: SignupPage },
  { path: "/profile", component: MyProfilePage },
  { path: "/mypage", component: MyActivePage },
  { path: "/result", component: SearchResultPage },
  { path: "/board/new", component: BoardWritePage },
  { path: "/review/new", component: ReviewWritePage },
  { path: "/study", component: StudyBoardPage },
  { path: "/board/detail", component: BoardDetailsPage },
  { path: "/board", component: BoardListPage },
  { path: "/review", component: ReviewListPage },
  { path: "/select/signup", component: SelectSignupPage },
  { path: "/email/verify", component: EmailValidationPage },
  { path: "/notice", component: NoticeBoardPage },
  { path: "/admin", component: AdminMainPage },
  { path: "/admin/withdraw", component: AdminWithdrawPage },
  { path: "/admin/category/register", component: AdminCategoryRegisterPage },
  { path: "/admin/tag/register", component: AdminTagRegisterPage },
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
