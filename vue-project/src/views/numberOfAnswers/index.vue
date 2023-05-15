<template>
    <div>number of answers</div>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id2" :option="option2"/>
    <BaseEchart :chart-id="id3" :option="option3"/>
</template>

<script setup>
import BaseEchart from "@/Echarts/BaseEchart.vue"

const id1 = 'chart1'
const option1 = {
    xAxis: {
        type: "category",
        data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
        type: "value",
    },
    series: [
        {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
        },
    ],
}

const id2 = 'chart2'
const option2 = {
    series: [
        {
            type: 'pie',
            data: [
                {
                    value: 335,
                    name: '直接访问'
                },
                {
                    value: 234,
                    name: '联盟广告'
                },
                {
                    value: 1548,
                    name: '搜索引擎'
                }
            ],
            radius: '20%',
        }
    ]
};

const id3 = 'chart3'
const option3 = {
    xAxis: {
        data: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
    },
    yAxis: {},
    series: [
        {
            type: 'scatter',
            data: [220, 182, 191, 234, 290, 330, 310]
        }
    ]
};
</script>