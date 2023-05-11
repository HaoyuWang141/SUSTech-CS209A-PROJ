import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import routes from './router'

const app = createApp(App)

app.use(ElementPlus)
axios.defaults.baseURL = 'http://localhost:8080'
app.config.globalProperties.$http = axios
app.use(routes)
app.mount('#app')
