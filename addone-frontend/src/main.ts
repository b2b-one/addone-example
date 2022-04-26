import { createApp } from "vue";
import App from "./App.vue";
import sdk from '@one-commerce/sdk-backoffice-vue3'

createApp(App)
    .use(sdk, {
        origin: window.location.ancestorOrigins[0], // Set ONe Backoffice url, where plugin is embedded
        debug: true,
    })
    .mount("#app");
