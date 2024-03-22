import { shallowMount } from '@vue/test-utils';
import ReviewWritePage from '@/components/ReviewWritePage.vue';

jest.mock('frontend/src/stores/useReviewStore', () => ({
    useReviewStore: jest.fn().mockReturnValue({
        reviewWrite: jest.fn(() => Promise.resolve()),
    }),
}));



describe('ReviewWritePage', () => {

    let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(ReviewWritePage);
  });

  it('toggles isClicked when showCategory is called', async () => {
    expect(wrapper.vm.isClicked).toBe(false);
    await wrapper.vm.showCategory();
    expect(wrapper.vm.isClicked).toBe(true);
  });

  it('selects category and updates reviewCategoryIdx', async () => {
    await wrapper.vm.selectCategory({ name: '과정 후기', selected: false });
    expect(wrapper.vm.selectedCategory).toBe('과정 후기');
    expect(wrapper.vm.review.reviewCategoryIdx).toBe(1);
  });

  it('toggles isEvaluationClicked when showEvaluation is called', async () => {
    expect(wrapper.vm.isEvaluationClicked).toBe(false);
    await wrapper.vm.showEvaluation();
    expect(wrapper.vm.isEvaluationClicked).toBe(true);
  });

});

