import { createRouter, createWebHistory } from 'vue-router'

import AuthSignupPage from "@/pages/AuthSignupPage.vue";

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/authsignup', component: AuthSignupPage },
    ]
})

export default router;