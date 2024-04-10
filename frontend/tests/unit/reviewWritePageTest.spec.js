import { createPinia, setActivePinia } from "pinia";
import Quill from "quill";
import QuillImageUploader from "quill-image-uploader";
import { shallowMount } from "@vue/test-utils";
import ReviewWritePage from "@/pages/ReviewWritePage.vue";

const globalComponents = {
    Quill,
    QuillImageUploader
};

describe("후기 작성 페이지 테스트", () => {
  let wrapper;

  const pinia = createPinia();
  setActivePinia(pinia);

  beforeEach(() => {
    wrapper = shallowMount(ReviewWritePage, {
      global: {
        components: globalComponents,
        plugins: [pinia],
      },
    });
  });

  test("별점 선택 버튼 클릭 시 선택창이 표시되는지 확인", async () => {
    expect(wrapper.find(".css-l2t941s").isVisible()).toBe(false);
    await wrapper.find(".css-pfhqzms").trigger("click");
    await wrapper.vm.$nextTick();
    expect(wrapper.find(".css-l2t941s").isVisible()).toBe(true);
  });
  
  test("별점 선택 후 선택창이 닫히는지 확인", async () => {
    await wrapper.findAll("li").at(0).trigger("click");
    await wrapper.vm.$nextTick();
  
    expect(wrapper.vm.isEvaluationClicked).toBe(false);
    expect(wrapper.find(".css-l2t941s").isVisible()).toBe(false);
  });

  test("별점 선택 후 선택한 별점이 버튼에 표시되는지 확인", async () => {
    const expectedEvaluation = "5점";
    
    await wrapper.find(".css-pfhqzms").trigger("click");
    await wrapper.vm.$nextTick();
    
    const evaluationItems = wrapper.findAll("li");
    const evaluationToSelect = evaluationItems.filter(w => w.text() === expectedEvaluation).at(0);
    expect(evaluationToSelect).toBeTruthy();
    await evaluationToSelect.trigger("click");
    await wrapper.vm.$nextTick();
    
    expect(wrapper.vm.selectedEvaluation).toBe(expectedEvaluation);
    
    const selectedEvaluationButton = wrapper.find(".css-1w5123ms");
    expect(selectedEvaluationButton.exists()).toBeTruthy();
    const buttonText = selectedEvaluationButton.text();
    expect(buttonText.includes(expectedEvaluation)).toBe(true);
  });
});
