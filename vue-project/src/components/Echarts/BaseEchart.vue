<template>
    <div :id="chartId" class="chart-container">111</div>
</template>

<script setup>
import * as echarts from "echarts";
import {onMounted, onBeforeUnmount, watch, onUpdated, onBeforeUpdate, watchEffect} from "vue";

const props = defineProps({
    chartId: {
        type: String,
        required: true,
    },
    option: {
        type: Object,
        required: true,
    }
})
let chart = null;

function initChart() {
    chart = echarts.init(document.getElementById(props.chartId));
    chart.setOption(props.option);
}

function renderChart() {
    chart.setOption(props.option);
}

onMounted(() => {
    console.log("onMounted in BaseEchart.vue")
    console.log(props.option)
    initChart();
});

window.addEventListener('resize', function() {
    chart.resize();
});

watch(() => props.option, (newValue,oldValue) => {
    console.log("watch option in BaseEchart.vue")
    console.log(newValue)
    renderChart();
});

watchEffect(() => {
    console.log("watchEffect option in BaseEchart.vue")
    console.log(props.option)
});

watch(() => props.chartId, (newValue,oldValue) => {
    console.log("watch id in BaseEchart.vue")
    console.log(newValue)
});

onUpdated(() => {
    console.log("onUpdated in BaseEchart.vue")
    console.log(props.option)
    renderChart();
});

onBeforeUpdate(() => {
    console.log("onBeforeUpdate in BaseEchart.vue")
    console.log(props.option)
    renderChart();
});

onBeforeUnmount(() => {
    if (!chart) return;
    chart.dispose();
    chart = null;
});
</script>

<style scoped>
.chart-container {
    border: 2px solid red;
    width: 100%;
    max-width: 500px;
    height: 500px;
    overflow: hidden;
}
</style>
