import { createRouter, createWebHistory } from 'vue-router'

import AuthSignupPage from "@/pages/AuthSignupPage.vue";
import SignupPage from '@/pages/SignupPage.vue';

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/authsignup', component: AuthSignupPage },
        { path: '/signup', component: SignupPage}
    ]
})

export default router;
