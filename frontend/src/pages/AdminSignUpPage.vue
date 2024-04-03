<template>
    <div class="container-xxl">
        <div class="authentication-wrapper authentication-basic container-p-y">
            <div class="authentication-inner">
                <!-- Register -->
                <div class="card">
                    <div class="card-body">
                        <!-- Logo -->
                        <div class="app-brand justify-content-center">
                            <router-link to="/admin/tag">
                                <span class="app-brand-logo demo">
                                    <img src="../assets/img/logo.png" alt="BOOKSHELF 로고" width="140px">
                                </span>
                            </router-link>
                        </div>
                        <!-- /Logo -->
                        <h4 class="mb-2">관리자 회원가입</h4>

                        <form @submit.prevent="submitForm" id="formAuthentication" class="mb-3">
                            <div class="mb-3">
                                <label for="name" class="form-label">성명</label>
                                <input type="text" class="form-control" id="name" name="name" v-model="formData.name"
                                    placeholder="홍길동" autofocus />
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">이메일</label>
                                <input type="text" class="form-control" id="email" name="email" v-model="formData.email"
                                    placeholder="abc123@test.com" autofocus />
                            </div>
                            <div class="mb-3 form-password-toggle">
                                <div class="d-flex justify-content-between">
                                    <label class="form-label" for="password">비밀번호</label>
                                </div>
                                <div class="input-group input-group-merge">
                                    <input type="password" id="password" class="form-control" name="password"
                                        v-model="formData.password"
                                        placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                                        aria-describedby="password" />
                                </div>
                            </div>
                            <div class="mb-3 form-password-toggle">
                                <div class="d-flex justify-content-between">
                                    <label class="form-label" for="password">비밀번호 확인</label>
                                </div>
                                <div class="input-group input-group-merge">
                                    <input type="password" id="password-check" class="form-control"
                                        name="password-check" v-model="formData.password_check"
                                        placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                                        aria-describedby="password" />
                                </div>
                            </div>
                            <div class="mb-3">
                                <button class="btn btn-primary d-grid w-100" type="submit">회원가입</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /Register -->
            </div>
        </div>
    </div>
</template>


<script>
import { mapStores } from "pinia";
import { useAdminStore } from "@/stores/useAdminStore";

export default {
    name: "AdminMainPage",
    data() {
        return {
            formData: {
                name: "",
                email: "",
                password: "",
                password_check: ""
            }
        }
    },
    computed: {
        ...mapStores(useAdminStore),
    },
    mounted() {
        this.$root.hideHeaderAndFooter = true;
    },
    methods: {
        async submitForm() {
            const adminStore = useAdminStore(); 
            try {
                if (this.formData.password === this.formData.password_check) {
                    await adminStore.adminSignUp(this.formData);
                } else {
                    alert("비밀번호가 일치하지 않습니다");
                }
            } catch (e) {
                console.log(e);
                throw e;
            }
        }
    }
};
</script>


<style scoped>
.container,
.container-fluid,
.container-sm,
.container-md,
.container-lg,
.container-xl,
.container-xxl {
    padding-right: 1rem;
    padding-left: 1rem;
}

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
    padding-right: calc(var(--bs-gutter-x)* 0.5);
    padding-left: calc(var(--bs-gutter-x)* 0.5);
    margin-right: auto;
    margin-left: auto;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

body {
    text-rendering: optimizeLegibility;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}

body {
    margin: 0;
    font-family: var(--bs-body-font-family);
    font-size: var(--bs-body-font-size);
    font-weight: var(--bs-body-font-weight);
    line-height: var(--bs-body-line-height);
    color: var(--bs-body-color);
    text-align: var(--bs-body-text-align);
    background-color: var(--bs-body-bg);
    -webkit-text-size-adjust: 100%;
    -webkit-tap-highlight-color: rgba(67, 89, 113, 0);
}

.container-p-y:not([class^='pb-']):not([class*=' pb-']) {
    padding-bottom: 1.625rem !important;
}

.container-p-y:not([class^='pt-']):not([class*=' pt-']) {
    padding-top: 1.625rem !important;
}

.authentication-wrapper.authentication-basic {
    align-items: center;
    justify-content: center;
    overflow: hidden;
}

.authentication-wrapper {
    display: flex;
    flex-basis: 100%;
    min-height: 100vh;
    width: 100%;
}

.authentication-wrapper.authentication-basic .authentication-inner {
    max-width: 400px;
    position: relative;
}

.authentication-wrapper .authentication-inner {
    width: 100%;
}

.authentication-wrapper.authentication-basic .authentication-inner:before {
    width: 148px;
    height: 148px;
    content: " ";
    position: absolute;
    top: -40px;
    right: -40px;
}

.authentication-wrapper.authentication-basic .authentication-inner .card {
    z-index: 1;
}

.card {
    background-clip: padding-box;
    box-shadow: 0 2px 6px 0 rgba(67, 89, 113, 0.12);
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

.authentication-wrapper.authentication-basic .authentication-inner .card .app-brand {
    margin-bottom: 2.5rem;
}

.app-brand {
    display: flex;
    flex-grow: 0;
    flex-shrink: 0;
    overflow: hidden;
    line-height: 1;
    min-height: 1px;
    align-items: center;
}

.justify-content-center {
    justify-content: center !important;
}

.app-brand-link {
    display: flex;
    align-items: center;
}

.gap-2 {
    gap: 0.5rem !important;
}

a {
    color: #696cff;
}

a:-webkit-any-link {
    color: -webkit-link;
    cursor: pointer;
    text-decoration: underline;
}

.app-brand-logo {
    display: block;
    flex-grow: 0;
    flex-shrink: 0;
    overflow: hidden;
    min-height: 1px;
}

.app-brand-logo img,
.app-brand-logo svg {
    display: block;
}

img,
svg {
    vertical-align: middle;
}

img {
    overflow-clip-margin: content-box;
    overflow: clip;
}

.mb-2 {
    margin-bottom: 3rem !important;
    text-align: center;
}

h4,
.h4 {
    font-size: calc(1.2625rem + 0.15vw);
}

h6,
.h6,
h5,
.h5,
h4,
.h4,
h3,
.h3,
h2,
.h2,
h1,
.h1 {
    margin-top: 0;
    margin-bottom: 1rem;
    font-weight: 500;
    line-height: 1.1;
    color: var(--bs-heading-color);
}

h4 {
    display: block;
    margin-block-start: 1.33em;
    margin-block-end: 1.33em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
}

form {
    display: block;
    margin-top: 0em;
}

.mb-3 {
    margin-bottom: 1rem !important;
}


.form-label,
.col-form-label {
    font-size: 0.75rem;
    text-transform: uppercase;
    letter-spacing: inherit;
}


.form-label {
    margin-bottom: 0.5rem;
    font-size: 0.75rem;
    font-weight: 500;
    color: #566a7f;
}

label {
    display: inline-block;
}

.form-control {
    display: block;
    width: 100%;
    padding: 0.4375rem 0.875rem;
    font-size: 0.9375rem;
    font-weight: 400;
    line-height: 1.53;
    color: #697a8d;
    appearance: none;
    background-color: #fff;
    background-clip: padding-box;
    border: var(--bs-border-width) solid #697a8d;
    border-radius: var(--bs-border-radius);
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

input,
button,
select,
optgroup,
textarea {
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
    border-color: #697a8d;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

input[type="text" i] {
    padding-block: 1px;
    padding-inline: 2px;
}

.card-body {
    flex: 1 1 auto;
    padding: var(--bs-card-spacer-y) var(--bs-card-spacer-x);
    color: var(--bs-card-color);
}

.mb-2 {
    margin-bottom: 3rem !important;
    text-align: center;
}


input,
button,
select,
optgroup,
textarea {
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
}


.input-group> :not(:first-child):not(.dropdown-menu):not(.valid-tooltip):not(.valid-feedback):not(.invalid-tooltip):not(.invalid-feedback) {
    margin-left: calc(var(--bs-border-width)* -1);
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}


.input-group-merge .input-group-text:last-child {
    border-left: 0;
}

.cursor-pointer {
    cursor: pointer !important;
}


.input-group-text {
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.input-group-text {
    background-clip: padding-box;
}

.input-group-text {
    display: flex;
    align-items: center;
    padding: 0.4375rem 0.875rem;
    font-size: 0.9375rem;
    font-weight: 400;
    line-height: 1.53;
    color: #697a8d;
    text-align: center;
    white-space: nowrap;
    background-color: #fff;
    border: var(--bs-border-width) solid #d9dee3;
    border-radius: var(--bs-border-radius);
}

:root {
    color-scheme: light;
}

:root {
    --bs-breakpoint-xs: 0;
    --bs-breakpoint-sm: 576px;
    --bs-breakpoint-md: 768px;
    --bs-breakpoint-lg: 992px;
    --bs-breakpoint-xl: 1200px;
    --bs-breakpoint-xxl: 1400px;
}

@media (prefers-reduced-motion: no-preference) {
    :root {
        scroll-behavior: smooth;
    }
}

:root {
    font-size: var(--bs-root-font-size);
}


:root,
[data-bs-theme='light'] {
    --bs-blue: #007bff;
    --bs-indigo: #6610f2;
    --bs-purple: #696cff;
    --bs-pink: #e83e8c;
    --bs-red: #ff3e1d;
    --bs-orange: #fd7e14;
    --bs-yellow: #ffab00;
    --bs-green: #71dd37;
    --bs-teal: #20c997;
    --bs-cyan: #03c3ec;
    --bs-black: #435971;
    --bs-white: #fff;
    --bs-gray: rgba(67, 89, 113, 0.6);
    --bs-gray-dark: rgba(67, 89, 113, 0.8);
    --bs-gray-25: rgba(67, 89, 113, 0.025);
    --bs-gray-50: rgba(67, 89, 113, 0.05);
    --bs-primary: #696cff;
    --bs-secondary: #8592a3;
    --bs-success: #71dd37;
    --bs-info: #03c3ec;
    --bs-warning: #ffab00;
    --bs-danger: #ff3e1d;
    --bs-light: #fcfdfd;
    --bs-dark: #233446;
    --bs-gray: rgba(67, 89, 113, 0.1);
    --bs-primary-rgb: 105, 108, 255;
    --bs-secondary-rgb: 133, 146, 163;
    --bs-success-rgb: 113, 221, 55;
    --bs-info-rgb: 3, 195, 236;
    --bs-warning-rgb: 255, 171, 0;
    --bs-danger-rgb: 255, 62, 29;
    --bs-light-rgb: 252, 253, 253;
    --bs-dark-rgb: 35, 52, 70;
    --bs-gray-rgb: 67, 89, 113;
    --bs-primary-text-emphasis: #2a2b66;
    --bs-secondary-text-emphasis: #353a41;
    --bs-success-text-emphasis: #2d5816;
    --bs-info-text-emphasis: #014e5e;
    --bs-warning-text-emphasis: #664400;
    --bs-danger-text-emphasis: #66190c;
    --bs-light-text-emphasis: rgba(67, 89, 113, 0.7);
    --bs-dark-text-emphasis: rgba(67, 89, 113, 0.7);
    --bs-primary-bg-subtle: #e1e2ff;
    --bs-secondary-bg-subtle: #e7e9ed;
    --bs-success-bg-subtle: #e3f8d7;
    --bs-info-bg-subtle: #cdf3fb;
    --bs-warning-bg-subtle: #ffeecc;
    --bs-danger-bg-subtle: #ffd8d2;
    --bs-light-bg-subtle: rgba(246, 247, 248, 0.55);
    --bs-dark-bg-subtle: rgba(67, 89, 113, 0.4);
}

button:not(:disabled),
[type='button']:not(:disabled),
[type='reset']:not(:disabled),
[type='submit']:not(:disabled) {
    cursor: pointer;
}

.btn-primary {
    color: #fff;
    background-color: #696cff;
    border-color: #696cff;
    box-shadow: 0 0.125rem 0.25rem 0 rgba(105, 108, 255, 0.4);
    float: right;
}

.w-100 {
    width: 100% !important;
}

.d-grid {
    display: grid !important;
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
    color: #fff;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    user-select: none;
    border: var(--bs-btn-border-width) solid var(--bs-btn-border-color);
    border-radius: var(--bs-btn-border-radius);
    transition: all 0.2s ease-in-out;
}

button,
select {
    text-transform: none;
}
</style>