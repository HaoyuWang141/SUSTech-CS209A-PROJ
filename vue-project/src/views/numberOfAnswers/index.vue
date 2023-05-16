<template>
    <div>number of answers</div>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id3" :option="option3"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {onBeforeMount, onMounted, reactive, getCurrentInstance, ref} from "vue";

const id1 = ref('chart1')
const option1 = reactive({
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },
    series: [
        {
            name: 'Questions',
            type: 'pie',
            radius: ['45%', '80%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: 25,
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
                {value: null, name: 'with answers'},
                {value: null, name: 'without answers'}
            ]
        }
    ]
})

const id3 = 'chart3'
const option3 = {
    backgroundColor: '#08263a',
    grid: {
        left: '10%',
        right: '5%',
        top: '20%',
        bottom: '20%',
        // containLabel: true
    },
    xAxis: {
        type: 'value',
        data: [10, 20, 30]
    },
    yAxis: {

        type: 'value'
    },
    series: [
        {
            data: [
                [0, 10],
                [10, 20],
                [20, 120],
                [40, 200],
                [50, 50]
            ],
            type: 'line'
        }
    ],
    visualMap: {
        show: false,
        min: 0,
        max: 50,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
};

onBeforeMount(() => {
    console.log('before mount')
    getCurrentInstance().appContext.config.globalProperties.$http
        .get("/answers/getNum", {
            params: {
                status: 'hasAnswer'
            }
        }).then((response) => {

        option1.series[0].data[0].value = response.data
        console.log(option1.series[0].data[0].value)
    })

    getCurrentInstance().appContext.config.globalProperties.$http
        .get("/answers/getNum", {
            params: {
                status: 'all'
            }
        }).then((response) => {
        option1.series[0].data[1].value = response.data - option1.series[0].data[0].value
        console.log(option1.series[0].data[1].value)
        id1.value = 'chart111111'
    })
})

onMounted(() => {
    console.log('mounted')
})


</script>