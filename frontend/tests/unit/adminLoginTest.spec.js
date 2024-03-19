import { shallowMount, flushPromises } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import { createRouter, createWebHistory } from 'vue-router';
import AdminLoginPage from '@/pages/AdminLoginPage.vue'; 
import { useAdminStore } from '@/stores/useAdminStore'; 


jest.mock('frontend/src/stores/useAdminStore', () => ({
    useAdminStore: jest.fn().mockReturnValue({
        adminLogin: jest.fn(() => Promise.resolve()),
    }),
}));


const routes = [
    { path: '/', component: { template: '<div>Home</div>' } },
    { path: '/admin/tag/register', name: 'admin-tag-register', component: { template: '<div>관리자 태그 등록</div>' } }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

describe('AdminLoginPage', () => {
    beforeEach(async () => {
        setActivePinia(createPinia());
        router.push('/');
        await router.isReady(); 
    });

    it('헤더 & 푸터 숨김 true mount 설정', async () => {
        const wrapper = shallowMount(AdminLoginPage, {
            global: {
                plugins: [router],
            },
        });
        expect(wrapper.vm.$root.hideHeaderAndFooter).toBe(true);
    });

    it('form 형식으로 email, password 입력을 통한 관리자 로그인', async () => {
        const email = 'test@example.com';
        const password = 'password123';
        const wrapper = shallowMount(AdminLoginPage, {
            global: {
                plugins: [router],
            },
        });

        await wrapper.setData({ email, password });
        await wrapper.find('form').trigger('submit.prevent');
        await flushPromises(); 

        const adminStore = useAdminStore();
        expect(adminStore.adminLogin).toHaveBeenCalledWith(email, password);
    });

    it('관리자 로그인 성공 시 /admin/tag/register 경로로 이동', async () => {
        const wrapper = shallowMount(AdminLoginPage, {
            global: {
                plugins: [router],
            },
        });

        await wrapper.vm.onLoginFormSubmit();
        await flushPromises(); 

        expect(router.currentRoute.value.path).toBe('/admin/tag/register');
    });
});