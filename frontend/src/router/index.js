import { createRouter, createWebHistory } from 'vue-router'
import SignupPage from '@/pages/SignupPage.vue';
const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/signupage', component: SignupPage}
    ]
})
export default router;







