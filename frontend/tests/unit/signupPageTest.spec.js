import { createPinia, setActivePinia } from 'pinia';
import { shallowMount } from '@vue/test-utils';
import SignupPage from '@/pages/SignupPage.vue';

global.URL.createObjectURL = jest.fn(() => "test-file-url");

describe("일반회원 회원가입 페이지 테스트", () => {

  test("전체 약관 동의 버튼 클릭", async () => {
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

  test("프로필 이미지 업로드 테스트", async () => {
    const pinia = createPinia();
    setActivePinia(pinia);

    const wrapper = shallowMount(SignupPage, {
      global: {
        plugins: [pinia]
      }
    });
    
    const testFile = new File(['dummy content'], 'testImage.png', { type: 'image/png' });
    
    const readAsDataURL = jest.spyOn(FileReader.prototype, 'readAsDataURL');
    readAsDataURL.mockImplementation(function () {
      this.onload({
        target: {
          result: 'test-file-content',
        },
      });
    });
    
    await wrapper.vm.handleProfileImageChange({
      target: { files: [testFile] }
    });
  
    expect(global.URL.createObjectURL).toHaveBeenCalledWith(testFile);
    expect(wrapper.vm.selectedProfileImageURL).toBe('test-file-url');
    
    jest.restoreAllMocks();
  });
});