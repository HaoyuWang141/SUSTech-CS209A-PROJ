import {createRouter, createWebHashHistory} from 'vue-router'

// 1. 定义路由组件.
// 也可以从其他文件导入
const layout = () => import('@/layout/index.vue')
const home = () => import('@/views/home/index.vue')
const NumberOfAnswers = () => import('@/views/NumberOfAnswers/index.vue')
const AcceptedAnswers = () => import('@/views/AcceptedAnswers/index.vue')
const Tags = () => import('@/views/Tags/index.vue')
const Users = () => import('@/views/Users/index.vue')
const Api = () => import('@/views/api/index.vue')

// 2. 定义一些路由
// 每个路由都需要映射到一个组件。
// 我们后面再讨论嵌套路由。
const routes = [
    {path: '/', redirect: '/home'},
    {
        path: '/home',
        component: layout,
        children: [{path: '', name: 'home', component: home, meta: {title: 'home', icon: 'home'}}]
    },
    {
        path: '/NumberOfAnswers',
        component: layout,
        children: [{
            path: '',
            name: 'numberOfAnswers',
            component: NumberOfAnswers,
            meta: {title: 'Number of Answers', icon: 'form'}
        }]
    },
    {
        path: '/AcceptedAnswers',
        component: layout,
        children: [{
            path: '',
            name: 'acceptedAnswers',
            component: AcceptedAnswers,
            meta: {title: 'Accepted Answers', icon: 'eye-open'}
        }]
    },
    {
        path: '/Tags',
        component: layout,
        children: [{path: '', name: 'tags', component: Tags, meta: {title: 'Tags', icon: 'example'}}]
    },
    {
        path: '/Users',
        component: layout,
        children: [{path: '', name: 'users', component: Users, meta: {title: 'Users', icon: 'user'}}]
    },
    {
        path: '/Api',
        component: layout,
        children: [{path: '', name: 'api', component: Api, meta: {title: 'Api', icon: 'link'}}]
    }
]

// 3. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})

export default router
