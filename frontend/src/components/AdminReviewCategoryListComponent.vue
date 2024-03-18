<template>
  <td>
    <span class="fw-medium">{{ reviewCategories.idx }}</span>
  </td>
  <td>{{ reviewCategories.categoryName }}</td>
  <td>
    <div class="dropdown" @click.prevent="toggleUserMenu($event)">
      <button class="btn p-0">
        <i class="bx bx-dots-vertical-rounded"></i>
      </button>
      <div class="dropdown-menu" v-show="userMenuVisible">
        <router-link
          :to="{ name: 'AdminReviewCategoryUpdate', params: { categoryIdx: reviewCategories.idx, categoryName: reviewCategories.categoryName } }">
          <a class="dropdown-item"><i class="bx bx-edit-alt me-1"></i>수정</a>
        </router-link>

        <a class="dropdown-item" @click="deleteReviewCategory"><i class="bx bx-trash me-1"></i>삭제</a>
      </div>
    </div>
  </td>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue';
import { useReviewStore } from '@/stores/useReviewStore';
import { mapStores } from "pinia";

export default {
  name: 'AdminReviewCategoryListComponent',
  props: {
    reviewCategories: Object
  },
  setup(props) {
    const userMenuVisible = ref(false);
    const reviewStore = useReviewStore();

    const toggleUserMenu = (event) => {
      userMenuVisible.value = !userMenuVisible.value;
      event.stopPropagation();
    };

    const onBodyClick = (event) => {
      if (!event.target.closest('.dropdown')) {
        userMenuVisible.value = false;
      }
    };

    const deleteReviewCategory = async () => {
      try {
        await reviewStore.deleteReviewCategory(props.reviewCategories.idx);
        alert('카테고리가 삭제되었습니다.');
        // Here, you might want to refresh the list or navigate away
      } catch (error) {
        console.error('Failed to delete the review category:', error);
        alert('카테고리 삭제에 실패했습니다.');
      }
    };

    onMounted(() => {
      document.body.addEventListener('click', onBodyClick);
    });

    onUnmounted(() => {
      document.body.removeEventListener('click', onBodyClick);
    });

    return { userMenuVisible, toggleUserMenu, deleteReviewCategory };
  },
  computed: {
    ...mapStores(useReviewStore),
  }
};
</script>


<style scoped>
.container,
.container-fluid,
.container-xxl,
.container-xl,
.container-lg,
.container-md,
.container-sm {
  --bs-gutter-x: 1.625rem;
  --bs-gutter-y: 0;
  width: 100%;
  padding-right: calc(var(--bs-gutter-x) * 0.5);
  padding-left: calc(var(--bs-gutter-x) * 0.5);
  margin-right: auto;
  margin-left: auto;
}

.flex-grow-1 {
  flex-grow: 1 !important;
}

@media (min-width: 992px) {

  .container,
  .container-fluid,
  .container-sm,
  .container-md,
  .container-lg,
  .container-xl,
  .container-xxl {
    padding-right: 8rem;
    padding-left: 8rem;
  }
}

.layout-page,
.content-wrapper,
.content-wrapper>*,
.layout-menu {
  min-height: 1px;
}

.container-p-y:not([class^="pt-"]):not([class*=" pt-"]) {
  padding-top: 1.625rem !important;
}

.container-p-y:not([class^="pb-"]):not([class*=" pb-"]) {
  padding-bottom: 1.625rem !important;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

h4,
.h4 {
  font-size: calc(1.2625rem + 0.15vw);
}

@media (min-width: 1200px) {

  h4,
  .h4 {
    font-size: 1.375rem;
  }
}

.mb-4 {
  margin-bottom: 1.5rem !important;
}

.py-3 {
  padding-top: 1rem !important;
  padding-bottom: 1rem !important;
}

.fw-light {
  font-weight: 300 !important;
}

.text-muted {
  --bs-text-opacity: 1;
  color: #a1acb8 !important;
}

.card {
  --bs-card-spacer-y: 1.5rem;
  --bs-card-spacer-x: 1.5rem;
  --bs-card-title-spacer-y: 0.875rem;
  --bs-card-title-color: #566a7f;
  --bs-card-subtitle-color: ;
  --bs-card-border-width: 0;
  --bs-card-border-color: #d9dee3;
  --bs-card-border-radius: 0.5rem;
  --bs-card-box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
  --bs-card-inner-border-radius: 0.5rem;
  --bs-card-cap-padding-y: 1.5rem;
  --bs-card-cap-padding-x: 1.5rem;
  --bs-card-cap-bg: transparent;
  --bs-card-cap-color: ;
  --bs-card-height: ;
  --bs-card-color: ;
  --bs-card-bg: #fff;
  --bs-card-img-overlay-padding: 1.5rem;
  --bs-card-group-margin: 0.8125rem;
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  height: var(--bs-card-height);
  color: var(--bs-body-color);
  word-wrap: break-word;
  background-color: var(--bs-card-bg);
  background-clip: border-box;
  border: var(--bs-card-border-width) solid var(--bs-card-border-color);
  border-radius: var(--bs-card-border-radius);
}

.card {
  background-clip: padding-box;
  box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
}

.table-responsive {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
}

.text-nowrap {
  white-space: nowrap !important;
}

table {
  caption-side: bottom;
  border-collapse: collapse;
}

.table {
  --bs-table-color-type: initial;
  --bs-table-bg-type: initial;
  --bs-table-color-state: initial;
  --bs-table-bg-state: initial;
  --bs-table-color: var(--bs-body-color);
  --bs-table-bg: transparent;
  --bs-table-border-color: #d9dee3;
  --bs-table-accent-bg: transparent;
  --bs-table-striped-color: var(--bs-body-color);
  --bs-table-striped-bg: #f9fafb;
  --bs-table-active-color: var(--bs-body-color);
  --bs-table-active-bg: rgba(67, 89, 113, 0.1);
  --bs-table-hover-color: var(--bs-body-color);
  --bs-table-hover-bg: rgba(67, 89, 113, 0.06);
  width: 100%;
  margin-bottom: 1rem;
  vertical-align: middle;
  border-color: var(--bs-table-border-color);
}

.card .table {
  margin-bottom: 0;
}

.table>thead {
  vertical-align: bottom;
}

th {
  font-weight: 500;
  text-align: inherit;
  text-align: -webkit-match-parent;
}

thead,
tbody,
tfoot,
tr,
td,
th {
  border-color: inherit;
  border-style: solid;
  border-width: 0;
}

.table> :not(caption)>*>* {
  padding: 0.625rem 1.25rem;
  color: #697A8D;
  background-color: var(--bs-table-bg);
  border-bottom-width: 1px;
  box-shadow: inset 0 0 0 9999px var(--bs-table-bg-state, var(--bs-table-bg-type, var(--bs-table-accent-bg)));
  text-align: center;
}

.table> :not(caption)>*>* {
  background-clip: padding-box;
}

.table th {
  text-transform: uppercase;
  font-size: 16ㅔㅌ;
  letter-spacing: 1px;
  text-align: center;
}

.table:not(.table-dark) th {
  color: #566a7f;
}

.table>tbody {
  vertical-align: inherit;
}

/*--------조회 컴포넌트 CSS----------*/
.fw-medium {
  font-weight: 500 !important;
}

.dropup,
.dropend,
.dropdown,
.dropstart,
.dropup-center,
.dropdown-center {
  position: relative;
}

.table tr>td .dropdown {
  position: static;
}

.dropdown-toggle {
  white-space: nowrap;
}

.btn {
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.btn {
  --bs-btn-padding-x: 1.25rem;
  --bs-btn-padding-y: 0.4375rem;
  --bs-btn-font-family: ;
  --bs-btn-font-size: 0.9375rem;
  --bs-btn-font-weight: 400;
  --bs-btn-line-height: 1.53;
  --bs-btn-color: var(--bs-body-color);
  --bs-btn-bg: transparent;
  --bs-btn-border-width: var(--bs-border-width);
  --bs-btn-border-color: transparent;
  --bs-btn-border-radius: var(--bs-border-radius);
  --bs-btn-hover-border-color: transparent;
  --bs-btn-box-shadow: none;
  --bs-btn-disabled-opacity: 0.65;
  --bs-btn-focus-box-shadow: 0 0 0 0.05rem rgba(var(--bs-btn-focus-shadow-rgb), 0.5);
  display: inline-block;
  padding: var(--bs-btn-padding-y) var(--bs-btn-padding-x);
  font-family: var(--bs-btn-font-family);
  font-size: var(--bs-btn-font-size);
  font-weight: var(--bs-btn-font-weight);
  line-height: var(--bs-btn-line-height);
  color: var(--bs-btn-color);
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  user-select: none;
  border: var(--bs-btn-border-width) solid var(--bs-btn-border-color);
  border-radius: var(--bs-btn-border-radius);
  background-color: var(--bs-btn-bg);
  transition: all 0.2s ease-in-out;
}

.p-0 {
  padding: 0 !important;
}

button:not(:disabled),
[type='button']:not(:disabled),
[type='reset']:not(:disabled),
[type='submit']:not(:disabled) {
  cursor: pointer;
}

.btn:not([class*='btn-']):active,
.btn:not([class*='btn-']).active,
.btn:not([class*='btn-']).show,
.btn:not([class*='btn-']) {
  border: none;
  background-color: white;
}

.btn .bx {
  line-height: 1.15;
}

.dropdown-menu {
  --bs-dropdown-zindex: 1000;
  --bs-dropdown-min-width: 8rem;
  --bs-dropdown-padding-x: 0;
  --bs-dropdown-padding-y: 0.3125rem;
  --bs-dropdown-spacer: 0.125rem;
  --bs-dropdown-font-size: 0.9375rem;
  --bs-dropdown-color: var(--bs-body-color);
  --bs-dropdown-bg: #fff;
  --bs-dropdown-border-color: transparent;
  --bs-dropdown-border-radius: var(--bs-border-radius);
  --bs-dropdown-border-width: var(--bs-border-width);
  --bs-dropdown-inner-border-radius: 0;
  --bs-dropdown-divider-bg: #d9dee3;
  --bs-dropdown-divider-margin-y: 0.5rem;
  --bs-dropdown-box-shadow: 0 0.25rem 1rem rgba(161, 172, 184, 0.45);
  --bs-dropdown-link-color: #697a8d;
  --bs-dropdown-link-hover-color: #697a8d;
  --bs-dropdown-link-hover-bg: rgba(67, 89, 113, 0.04);
  --bs-dropdown-link-active-color: #fff;
  --bs-dropdown-link-active-bg: rgba(105, 108, 255, 0.08);
  --bs-dropdown-link-disabled-color: #c7cdd4;
  --bs-dropdown-item-padding-x: 1.25rem;
  --bs-dropdown-item-padding-y: 0.532rem;
  --bs-dropdown-header-color: #a1acb8;
  --bs-dropdown-header-padding-x: 1.25rem;
  --bs-dropdown-header-padding-y: 0.3125rem;
  position: absolute;
  right: 0;
  left: auto;
  z-index: var(--bs-dropdown-zindex);
  min-width: var(--bs-dropdown-min-width);
  padding: var(--bs-dropdown-padding-y) var(--bs-dropdown-padding-x);
  margin: 0;
  font-size: var(--bs-dropdown-font-size);
  color: var(--bs-dropdown-color);
  text-align: left;
  list-style: none;
  background-color: var(--bs-dropdown-bg);
  background-clip: padding-box;
  border: var(--bs-dropdown-border-width) solid var(--bs-dropdown-border-color);
  border-radius: var(--bs-dropdown-border-radius);
}

.dropdown-item:hover {
  background-color: #f5f5f5;
}

.dropdown-item {
  display: block;
  width: 100%;
  padding: var(--bs-dropdown-item-padding-y) var(--bs-dropdown-item-padding-x);
  clear: both;
  font-weight: 400;
  color: var(--bs-dropdown-link-color);
  text-align: inherit;
  white-space: nowrap;
  background-color: transparent;
  border: 0;
  border-radius: var(--bs-dropdown-item-border-radius, 0);
  text-decoration: none;
}

.dropdown-menu {
  box-shadow: 0 0.25rem 1rem rgba(161, 172, 184, 0.45);
}

.me-1 {
  margin-right: 0.25rem !important;
}
</style>
