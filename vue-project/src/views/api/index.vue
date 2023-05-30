<template>
    <div :id="id1" class="aaa"></div>
</template>

<script setup>
import {onMounted, onBeforeUnmount, getCurrentInstance, onBeforeMount, reactive, watch} from "vue";
import "echarts-wordcloud"
import * as echarts from "echarts";

const id1 = 'chart1'
const option1 = reactive({
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    tooltip: {
        show: true,
    },
    series: [
        {
            type: "wordCloud",
            gridSize: 3,
            sizeRange: [10, 40],
            rotationRange: [-30, 30],
            left: "center",
            top: "center",
            right: null,
            bottom: null,
            width: "200%",
            height: "200%",
            //数据
            data: [],
        },
    ],
})
let chart = null;

function initChart() {
    console.log("initChart")
    chart = echarts.init(document.getElementById(id1));
    chart.setOption(option1);
}

function renderChart() {
    chart.setOption(option1);
}

watch(() => option1, () => {
    renderChart();
}, {deep: true});

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get("/api")
        .then((response) => {
            let min = Infinity;
            let max = 0;
            console.log(response.data)
            for (let key in response.data) {
                option1.series[0].data.push({
                    name: key,
                    value: response.data[key]
                })
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option1.visualMap.max = max
            option1.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })
})

onMounted(() => {
    initChart();
});

window.addEventListener('resize', function () {
    chart.resize();
});

onBeforeUnmount(() => {
    if (!chart) return;
    chart.dispose();
    chart = null;
});
</script>

<style>
.aaa {
    //border: 2px solid red;
    width: 100%;
    max-width: 850px;
    height: 470px;
    overflow: visible;
}
</style>