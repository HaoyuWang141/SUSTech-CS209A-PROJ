<template>
    <div>accepted answers</div>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id2" :option="option2"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {onMounted} from "vue";


onMounted(() => {
    init()
})

const id1 = 'chart1'
const data1 = [
    {
        name: 'A',
        children: [
            {
                name: 'C',
                value: 15,
            },
            {
                name: 'D',
                value: 10,
            }
        ]
    },
    {
        name: 'B',
        children: [
            {
                name: 'E',
                value: 10
            },
            {
                name: 'F',
                value: 6
            }
        ]

    }
];
const option1 = {
    series: {
        type: 'sunburst',
        data: data1,
        radius: [40, '60%'],
        itemStyle: {
            borderRadius: 7,
            borderWidth: 2
        },
        label: {
            show: false
        }
    }
};

const id2 = 'chart2'
const data2_xAxisData = [1,2,3]
const data2_1 = [4,5,6]
const data2_2 = [-1,-2,-3]
// const data2_xAxisData = []
// const data2_1 = []
// const data2_2 = []

function init() {
    for (let i = 0; i < 50; i++) {
        data2_xAxisData.push(i)
        data2_1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5)
        data2_2.push((Math.sin(i / 5) * (i / 5 + 10) + i / 6) * 3)
    }
}

const option2 = {
    backgroundColor: '#08263a',
    grid: {
        left: '5%',
        right: '5%'
    },
    xAxis: [{
        show: false,
        data: data2_xAxisData
    }, {
        show: false,
        data: data2_xAxisData
    }],
    visualMap: {
        show: false,
        min: 0,
        max: 50,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    yAxis: {
        axisLine: {
            show: false
        },
        axisLabel: {
            color: '#4a657a'
        },
        splitLine: {
            show: true,
            lineStyle: {
                color: '#08263f'
            }
        },
        axisTick: {
            show: false
        }
    },
    series: [{
        name: 'back',
        type: 'bar',
        data: data2_2,
        z: 1,
        itemStyle: {
            opacity: 0.4,
            borderRadius: 5,
            shadowBlur: 3,
            shadowColor: '#111'
        }
    }, {
        name: 'Simulate Shadow',
        type: 'line',
        data: data2_1,
        z: 2,
        showSymbol: false,
        animationDelay: 0,
        animationEasing: 'linear',
        animationDuration: 1200,
        lineStyle: {
            color: 'transparent'
        },
        areaStyle: {
            color: '#08263a',
            shadowBlur: 50,
            shadowColor: '#000'
        }
    }, {
        name: 'front',
        type: 'bar',
        data: data2_1,
        xAxisIndex: 1,
        z: 3,
        itemStyle: {
            borderRadius: 5
        }
    }],
    animationEasing: 'elasticOut',
    animationEasingUpdate: 'elasticOut',
    animationDelay(idx) {
        return idx * 20
    },
    animationDelayUpdate(idx) {
        return idx * 20
    }
};
</script>
