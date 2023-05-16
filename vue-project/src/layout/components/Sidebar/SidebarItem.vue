<template>
    <router-link :to="resolvePath(item)" class="container">
        <el-menu-item v-if="show(route)" :index="item.path">
            <inline-svg :src="`src/icons/svg/${item.icon}.svg`" width="20" height="20" class="icon"/>
<!--            <img :src="`src/icons/svg/${item.icon}.svg`" class="icon" alt="svg image"/>-->
            <span slot="title">{{ item.title }}</span>
        </el-menu-item>
    </router-link>
</template>

<script setup>
import {defineProps, reactive} from "vue";
import InlineSvg from 'vue-inline-svg';

const item = reactive({
    path: '',
    icon: '',
    title: ''
})
const props = defineProps({
    route: {
        type: Object,
        required: true
    },
    basePath: {
        type: String,
        default: ''
    }
})

function show(route) {
    if (route.children && route.children.length > 0) {
        item.path = route.path + route.children[0].path
        item.icon = route.children[0].meta.icon
        item.title = route.children[0].meta.title
        return true
    }
    return false
}

function resolvePath(item) {
    return item.path
}
</script>

<style>
.container {
    text-decoration: none;
}

.icon {
    width: 20px;
    height: 20px;
    margin-right: 10px;
    fill: currentColor;
}
</style>
