window.__VUE_PROD_HYDRATION_MISMATCH_DETAILS__ = false;

import { createApp } from "vue";
import { createPinia } from "pinia";
import LoadScript from "vue-plugin-load-script";
import { quillEditor } from "vue3-quill";
import VueDOMPurifyHTML from "vue-dompurify-html";
import moment from "moment";

import App from "./App.vue";

import router from "./router";

const pinia = createPinia();
const app = createApp(App);

app.config.globalProperties.$moment = moment;

app.use(pinia);
app.use(LoadScript);
app.use(router);
app.use(quillEditor);
app.use(VueDOMPurifyHTML);
app.mount("#app");
