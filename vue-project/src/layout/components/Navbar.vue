<template>
    <div class="navbar">
        CS209A Project
        <!--      <hamburger />-->
        <!--      <breadcrumb />-->
    </div>
</template>

<script setup>
import Breadcrumb from '@/components/Breadcrumb/index.vue'
import Hamburger from '@/components/Hamburger/index.vue'
</script>

<style scoped>

.navbar {
    height: 50px;
    overflow: hidden;
    position: relative;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

    /* 头部布局*/
    color: #606266;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center; /*文字居中*/
    font-size: 20px;
    /*左边logo 和 标题*/

    .left_box {
        display: flex; /*流式布局 */
        align-items: center;
        /*logo*/

        img {
            width: 60px;
            height: 60px;
            margin: 0px 0px 10px 10px; /*上下左右*/
        }

        /*标题*/

        span {
            margin-left: 15px;
        }
    }

    /*右边的登录头像*/

    .right_box {
        .el-dropdown > img {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background-color: #FFFFFF;
            padding-left: 5px;
            margin: 0px 15px 0px 0px;
            background-size: contain;
        }
    }


    .hamburger-container {
        line-height: 46px;
        height: 100%;
        float: left;
        cursor: pointer;
        transition: background .3s;
        -webkit-tap-highlight-color: transparent;

        &:hover {
            background: rgba(0, 0, 0, .025)
        }
    }

    .breadcrumb-container {
        float: left;
    }
}
</style>