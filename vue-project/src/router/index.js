import { createRouter, createWebHashHistory } from 'vue-router'
const A = () => import('../components/Model_A.vue')
const B = () => import('../components/Model_B.vue')
const C = () => import('../components/Model_C.vue')

// const routes: Array<RouterRecordRaw> = [
//     { path: '/', redirect: '/A' },
//     { path: '/A', component: A },
//     { path: '/B', component: B },
//     { path: '/C', component: C }
// ]

// const router = createRouter({
//     history: createWebHashHistory(),
//     routes
// })


const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        { path: '/', redirect: '/A' },
        { path: '/A', component: A },
        { path: '/B', component: B },
        { path: '/C', name:'xxx', component: C }
    ]
})

export default router
