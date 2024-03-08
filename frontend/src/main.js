window.__VUE_PROD_HYDRATION_MISMATCH_DETAILS__ = false;

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router';

const pinia = createPinia();
const app = createApp(App)

app.use(pinia);
app.use(router);
app.mount('#app')