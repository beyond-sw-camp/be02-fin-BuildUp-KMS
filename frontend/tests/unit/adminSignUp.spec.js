import { shallowMount, createLocalVue } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import AdminMainPage from '@/pages/AdminMainPage.vue';

const localVue = createLocalVue();
localVue.use(Vuex);

describe('AdminMainPage.vue', () => {
  let wrapper;
  let store;
  let actions;

  beforeEach(() => {
    setActivePinia(createPinia());
    store = useAdminStore();
    
    actions = {
      adminSignUp: jest.fn(),
      adminLogin: jest.fn(),
    };
    store.adminSignUp = actions.adminSignUp;
    store.adminLogin = actions.adminLogin;

    wrapper = shallowMount(AdminMainPage, { store, localVue });
  });

  it('does not submit form when passwords do not match', async () => {
    wrapper.setData({
      formData: {
        name: 'Test User',
        email: 'test@example.com',
        password: 'password123',
        password_check: 'differentPassword'
      }
    });
    
    await wrapper.find('form').trigger('submit.prevent');
    expect(actions.adminSignUp).not.toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith("비밀번호가 일치하지 않습니다");
  });

  it('submits form when passwords match', async () => {
    wrapper.setData({
      formData: {
        name: 'Test User',
        email: 'test@example.com',
        password: 'password123',
        password_check: 'password123'
      }
    });

    await wrapper.find('form').trigger('submit.prevent');
    expect(actions.adminSignUp).toHaveBeenCalledWith(expect.any(Object));
  });
});