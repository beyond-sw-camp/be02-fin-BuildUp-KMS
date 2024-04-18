import { shallowMount } from '@vue/test-utils';
import QuickMenuComponent from '@/components/QuickMenuComponent.vue';

window.scrollTo = jest.fn();

describe('퀵메뉴 컴포넌트 테스트', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(QuickMenuComponent);
    window.scrollTo.mockClear();
  });

  test('위로 가기 버튼 클릭 테스트', async () => {
    await wrapper.find('.btn-top').trigger('click');
    expect(window.scrollTo).toHaveBeenCalledWith({ top: 0, behavior: "smooth" });
  });

  test('아래로 가기 버튼 클릭 테스트', async () => {
    await wrapper.find('.btn-bottom').trigger('click');
    expect(window.scrollTo).toHaveBeenCalledWith({ top: document.body.scrollHeight, behavior: "smooth" });
  });
});