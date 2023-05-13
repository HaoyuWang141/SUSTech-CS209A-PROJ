<template>
    <el-breadcrumb class="app-breadcrumb" separator="/">
        <transition-group name="breadcrumb">
            <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">
                <span v-if="">{{ item.meta.title }}</span>
            </el-breadcrumb-item>
        </transition-group>
    </el-breadcrumb>
</template>

<script>


export default {
    data() {
        return {
            levelList: null
        }
    },

    methods: {
        getBreadcrumb() {
            let matched = this.$route.matched.filter(item => item.meta && item.meta.title);
            const first  = matched[0];
            
        }
    }
}
</script>