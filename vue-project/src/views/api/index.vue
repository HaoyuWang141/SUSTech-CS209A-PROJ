<template>
    <BaseEchart :option="option1" :chart-id="id1"/>
</template>

<script setup>

import BaseEchart from "@/components/Echarts/BaseEchart.vue";
import {getCurrentInstance, onBeforeMount, reactive} from "vue";
import "echarts-wordcloud"

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
            gridSize: 10,
            sizeRange: [14, 60],
            rotationRange: [0, 0],
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
</script>

<style>
</style>