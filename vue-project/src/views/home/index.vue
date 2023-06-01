<template>
    <div class="home-container">
        <div class="home-text">Introduction to Project</div>
        <br/>
        <br/>
        <h2>统计范围：</h2>
        <el-row :gutter="12" class="container1">
            <el-col :span="8">
                <el-card shadow="always"> question number: {{ question_number }}</el-card>
            </el-col>
            <el-col :span="8">
                <el-card shadow="always"> answer number: {{ answer_number }}</el-card>
            </el-col>
            <el-col :span="8">
                <el-card shadow="always"> comment number: {{ comment_number }}</el-card>
            </el-col>
        </el-row>
        <p>
            观察到十五周部分答辩小组使用“发布时间”作为爬虫依据，得到大量answer非常少的问题，数据结果较差。<br/><br/>
            我们优先选择较为受欢迎的问题，这类问题回答数量较多（约20~30），且评论较多。该类问题爬取了500个。<br/><br/>
            因题目要求统计有回答的问题所占比例，为了使图表更直观，我们爬取了最近提出的100个问题。<br/><br/>
        </p>
        <h2>技术栈：</h2>
        <h3>爬虫</h3>
        <p>HttpRequest generator, Json & JsonObject Transfer</p>
        <h3>后端</h3>
        <p>SpringBoot, MyBatisPlus, Postgresql</p>
        <h3>前端</h3>
        <p>Vue3, Axios, Vue Router, Element Plus, Echarts</p>
        <br/><br/>
        <h3>架构</h3>
        <p>前后端分离架构，单页面应用，故全部api均为RESTful风格</p>
    </div>
</template>

<script setup>
import {getCurrentInstance, onBeforeMount, ref} from "vue";

const question_number = ref(0)
const answer_number = ref(0)
const comment_number = ref(0)

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get('/questions/getNum', {params: {status: 'all'}}).then((res) => {
        question_number.value = res.data
    })
    axios.get('/answers/getNum', {params: {status: 'all'}}).then((res) => {
        answer_number.value = res.data
    })
    axios.get('/comments/getNum', {params: {status: 'all'}}).then((res) => {
        comment_number.value = res.data
    })
})
</script>

<style>
.home-container {
    margin: 20px;
}

.home-text {
    font-size: 40px;
    line-height: 10px;
}

.container1 {
    margin: 20px;
}
</style>