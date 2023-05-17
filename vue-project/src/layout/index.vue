<template>
    <div class="app-wrapper">
        <el-container>
            <el-aside>
                <sidebar class="sidebar-container"/>
            </el-aside>
            <el-container class="main-container">
                <el-header><navbar/></el-header>
                <el-main><app-main/></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import {Navbar, Sidebar, AppMain} from './components'
</script>


<style scoped>

.app-wrapper {
    //border: 2px solid red;
    position: relative;
    min-height: 95vh;
}

.sidebar-container {
    width: 250px;
    height: 100%;
    min-height: 95vh;
    background-color: #545c64;
}

.main-container {
    //border: 2px solid red;
    overflow: auto;
}

.el-main {
    //border: 2px solid red;
    overflow: visible;
}
</style>