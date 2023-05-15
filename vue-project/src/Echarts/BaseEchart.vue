<template>
    <div :id="chartId" class="chart-container">111</div>
</template>

<script setup>
import * as echarts from "echarts";
import {onMounted} from "vue";

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

function renderChart() {
    const chart = echarts.init(document.getElementById(props.chartId));
    chart.setOption(props.option);
}

onMounted(() => {
    renderChart();
});
</script>

<style scoped>
.chart-container {
    border: 2px solid red;
    width: 500px;
    height: 300px;
    overflow: hidden;
}
</style>
