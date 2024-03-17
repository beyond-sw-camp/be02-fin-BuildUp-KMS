<template>
    <div class="layout-wrapper layout-content-navbar">
        <div class="layout-container">
            <!-- Menu and Navbar components are inserted here, ensuring proper layout structure -->
            <AdminMenuComponent />
            <div class="layout-page">
                <div class="content-wrapper">
                    <!-- Content -->
                    <div class="container-xxl flex-grow-1 container-p-y">
                        <h3 class="py-3 mb-4"><span class="text-muted fw-light">관리자 /</span> 회원 탈퇴</h3>
                        <!-- Basic Layout -->
                        <div class="row">
                            <div class="col-xl">
                                <div class="card mb-4">
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <div>
                                            <h5 class="mb-0">[관리자] 회원 탈퇴에 동의합니다.</h5>
                                        </div>
                                        <div class="col-md">
                                            <div class="form-check mt-3">
                                                <input class="form-check-input" type="checkbox" value=""
                                                    id="defaultCheck1" />
                                                <label class="form-check-label" for="defaultCheck1"> 예 </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">저장</button>
                            </div>
                        </div>
                    </div>
                    <div class="content-backdrop fade"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import AdminMenuComponent from "@/components/AdminMenuComponent.vue";
import { mapStores } from "pinia";
import { useAdminStore } from "/src/stores/useAdminStore";

export default {
    name: "AdminWithdrawPage",
    components: {
        AdminMenuComponent,
    },
    mounted() {
        this.$root.hideHeaderAndFooter = true;
    },
    computed: {
        ...mapStores(useAdminStore),
    },
};
</script>

<style scoped>
@import url("@/assets/css/auth-boxicons.css");
@import url("@/assets/css/auth-theme-default.css");
@import url("@/assets/css/auth.css");
@import url("@/assets/css/auth-perfect-scrollbar.css");
@import url("@/assets/css/auth-page.css");

html:not(.layout-navbar-fixed) .layout-content-navbar .layout-page {
    background-color: rgba(245, 245, 249);
    padding-top: 0 !important;
}

.layout-navbar-fixed .layout-wrapper:not(.layout-horizontal) .layout-page:before {
    content: "";
    width: 100%;
    height: 0.75rem;
    position: fixed;
    top: 0px;
    z-index: 10;
}

.layout-navbar-fixed .layout-wrapper:not(.layout-horizontal) .layout-page:before {
    content: "";
    width: 100%;
    height: 0.75rem;
    position: fixed;
    top: 0px;
    z-index: 10;
}

.layout-navbar-fixed .layout-page:before {
    backdrop-filter: saturate(200%) blur(10px);
    background: rgba(245, 245, 249, 0.6);
}

.layout-navbar-fixed .layout-wrapper:not(.layout-horizontal):not(.layout-without-menu) .layout-page {
    padding-top: 76px !important;
}

/* Default navbar */
.layout-navbar-fixed .layout-wrapper:not(.layout-without-menu) .layout-page {
    padding-top: 64px !important;
}

.docs-page .layout-navbar-fixed.layout-wrapper:not(.layout-without-menu) .layout-page,
.docs-page .layout-menu-fixed.layout-wrapper:not(.layout-without-menu) .layout-page {
    padding-top: 62px !important;
}

html:not(.layout-navbar-fixed):not(.layout-menu-fixed):not(.layout-menu-fixed-offcanvas) .layout-page,
html:not(.layout-navbar-fixed) .layout-content-navbar .layout-page {
    padding-top: 0 !important;
}

@media (min-width: 1200px) {

    .layout-menu-fixed:not(.layout-menu-collapsed) .layout-page,
    .layout-menu-fixed-offcanvas:not(.layout-menu-collapsed) .layout-page {
        padding-left: 16.25rem;
    }
}


.layout-container {
    min-height: 100vh;
}

.app-brand,
.content-wrapper,
.content-wrapper>*,
.layout-menu,
.layout-page {
    min-height: 1px;
}

.layout-container,
.layout-wrapper {
    width: 100%;
    display: flex;
    flex: 1 1 auto;
    align-items: stretch;
}

.layout-navbar {
    flex: 0 0 auto;
}

.layout-page {
    flex: 1 1 auto;
    align-items: stretch;
    padding: 0;
}

.content-wrapper {
    display: flex;
    align-items: stretch;
    flex: 1 1 auto;
    flex-direction: column;
    justify-content: space-between;
}


.collapse:not(.show),
.dropdown-toggle-hide-arrow>.dropdown-toggle::after,
.dropdown-toggle-hide-arrow>.dropdown-toggle::before,
.dropdown-toggle.hide-arrow::after,
.dropdown-toggle.hide-arrow::before {
    display: none;
}

.layout-navbar-fixed .layout-wrapper:not(.layout-horizontal) .layout-page:before {
    content: "";
    width: 100%;
    height: 0.75rem;
    position: fixed;
    top: 0px;
    z-index: 10;
}


.layout-navbar-fixed .layout-page:before {
    backdrop-filter: saturate(200%) blur(10px);
    background: rgba(245, 245, 249, 0.6);
}

.layout-navbar-fixed .layout-wrapper:not(.layout-horizontal):not(.layout-without-menu) .layout-page {
    padding-top: 76px !important;
}

.layout-content-navbar .content-wrapper {
    background-color: rgba(245, 245, 249);
}

.btn-group-vertical>.btn,
.btn-group-vertical>.btn-group,
.card-img,
.card-img-bottom,
.card-img-top,
.layout-content-navbar .content-wrapper,
.menu .app-brand {
    width: 100%;
}


.py-0,
html:not(.layout-footer-fixed) .content-wrapper {
    padding-bottom: 0 !important;
}

.app-brand,
.content-wrapper,
.content-wrapper>*,
.layout-menu,
.layout-page {
    min-height: 1px;
}

.content-wrapper {
    display: flex;
    align-items: stretch;
    flex: 1 1 auto;
    flex-direction: column;
    justify-content: space-between;
}

.container-p-y:not([class^='pb-']):not([class*=' pb-']) {
    padding-bottom: 1.625rem !important;
}

.container-p-y:not([class^='pt-']):not([class*=' pt-']) {
    padding-top: 1.625rem !important;
    padding: 100px;
}

.py-3 {
    padding-top: 1rem !important;
    padding-bottom: 1rem !important;
}

.mb-4 {
    margin-bottom: 1.5rem !important;
}

@media (min-width: 1200px) {

    h3,
    .h3 {
        font-size: 1.625rem;
    }
}

.card-header:first-child {
    border-radius: var(--bs-card-inner-border-radius) var(--bs-card-inner-border-radius) 0 0;
}

.align-items-center {
    align-items: center !important;
}

.justify-content-between {
    justify-content: space-between !important;
}

.d-flex {
    display: flex !important;
}

.card-header,
.card-footer {
    border-color: #d9dee3;
}

.card-header {
    padding: var(--bs-card-cap-padding-y) var(--bs-card-cap-padding-x);
    margin-bottom: 0;
    color: var(--bs-card-cap-color);
    background-color: var(--bs-card-cap-bg);
    border-bottom: var(--bs-card-border-width) solid var(--bs-card-border-color);
    box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
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

button,
select {
    text-transform: none;
}

.btn-primary {
    color: #fff;
    background-color: #696cff;
    border-color: #696cff;
    box-shadow: 0 0.125rem 0.25rem 0 rgba(105, 108, 255, 0.4);
    border-radius: 10px;
    float: right;
}

.mb-0 {
    margin-bottom: 0 !important;
}

h5, .h5 {
    font-size: 1.125rem;
}

h6, .h6, h5, .h5, h4, .h4, h3, .h3, h2, .h2, h1, .h1 {
    margin-top: 0;
    margin-bottom: 1rem;
    font-weight: 300;
    line-height: 1.1;
    color: #566a7f;
}

.d-flex {
    display: flex !important;
}

.card-header, .card-footer {
    border-color: #d9dee3;
}

.card-header {
    padding: var(--bs-card-cap-padding-y) var(--bs-card-cap-padding-x);
    margin-bottom: 0;
    color: var(--bs-card-cap-color);
    background-color: var(--bs-card-cap-bg);
    border-bottom: var(--bs-card-border-width) solid var(--bs-card-border-color);
}

.py-3 {
    padding-top: 1rem !important;
    padding-bottom: 1rem !important;
}

.text-muted {
    --bs-text-opacity: 1;
    color: #a1acb8 !important;
}

.fw-light {
    font-weight: 300 !important;
}

</style>