import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import routes from './router'
import * as echarts from 'echarts';

const app = createApp(App)

app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
axios.defaults.baseURL = 'http://114.132.51.227:8080'
app.config.globalProperties.$http = axios
app.use(routes)
app.mount('#app')
