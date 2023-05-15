<template>
    <div>
        <el-menu
                active-text-color="#ffd04b"
                background-color="#545c64"
                class="el-menu-vertical-demo"
                default-active="2"
                text-color="#fff">
            <sidebar-item v-for="route in routes" :key="route.path" :route="route" :base-path="route.path"/>
        </el-menu>
    </div>
</template>

<script setup>
import SidebarItem from './SidebarItem.vue'
import {useRouter} from "vue-router"
import {computed} from "vue"

const router = useRouter()

const routes = computed(() => {
    return router.options.routes
})
</script>