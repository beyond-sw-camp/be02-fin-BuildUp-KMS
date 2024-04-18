import { createPinia, setActivePinia } from 'pinia';
import { shallowMount } from '@vue/test-utils';
import SignupPage from '@/pages/SignupPage.vue';

describe("일반회원 회원가입 페이지 동의 버튼 클릭 테스트", () => {
  test("버튼 클릭 후 SVG 스타일 변화 확인", async () => {
    const pinia = createPinia();
    setActivePinia(pinia);

    const wrapper = shallowMount(SignupPage, {
      global: {
        plugins: [pinia]
      }
    });

    await wrapper.find("button").trigger("click");

    const rect = wrapper.find("rect");
    expect(rect.attributes("fill")).toBe("#141617");
    expect(rect.attributes("stroke")).toBe("#141617");
  });
});