import { shallowMount, flushPromises } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import { createRouter, createWebHistory } from 'vue-router';
import ReviewWritePage from '@/pages/ReviewWritePage.vue'; 
import { useReviewStore } from '@/stores/useReviewStore'; 

jest.mock('frontend/src/stores/useReviewStore', () => ({
    useReviewStore: jest.fn().mockReturnValue({
        reviewWrite: jest.fn(() => Promise.resolve()),
    }),
}));

const routes = [
    { path: '/', component: { template: '<div>Home</div>' } },
    { path: '/review/new/register', name: 'admin-tag-register', component: { template: '<div>관리자 태그 등록</div>' } }
];