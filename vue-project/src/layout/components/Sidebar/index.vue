<template>
    <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            text-color="#fff"
    >
        <sidebar-item v-for="route in routes" :key="route.path" :route="route" :base-path="route.path"/>
    </el-menu>
</template>

<script setup>
import SidebarItem from './SidebarItem.vue'
import {useRouter} from "vue-router"
import {computed} from "vue"
import InlineSvg from "vue-inline-svg";

const router = useRouter()

const routes = computed(() => {
    return router.options.routes
})
</script>