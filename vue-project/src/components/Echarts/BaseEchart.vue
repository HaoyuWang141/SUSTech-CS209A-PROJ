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
    initChart();
});

window.addEventListener('resize', function() {
    chart.resize();
});

watch(() => props.option, () => {
    renderChart();
},{deep: true});

onBeforeUnmount(() => {
    if (!chart) return;
    chart.dispose();
    chart = null;
});
</script>

<style scoped>
.chart-container {
    //border: 2px solid red;
    padding: 10px 0 20px 200px;
    width: 100%;
    max-width: 500px;
    height: 400px;
    overflow: visible;
}
</style>
