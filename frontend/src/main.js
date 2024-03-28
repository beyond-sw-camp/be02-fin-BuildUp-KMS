window.__VUE_PROD_HYDRATION_MISMATCH_DETAILS__ = false;

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import LoadScript from 'vue-plugin-load-script';


import App from './App.vue'

import router from './router';

window.Kakao.init("191705327b9b9b8e00ae538d0b849015");
const pinia = createPinia();
const app = createApp(App)

app.use(pinia);
app.use(LoadScript);
app.use(router);
app.mount('#app')
