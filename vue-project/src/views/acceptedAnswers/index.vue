<template>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id2" :option="option2"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {getCurrentInstance, onBeforeMount, onMounted, reactive} from "vue";

const id1 = 'chart1'
const option1 = reactive({
    name: 'Questions',
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },

    series: {
        type: 'sunburst',
        radius: ['30%', '75%'],
        avoidLabelOverlap: false,
        itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
        },
        emphasis: {
            label: {
                show: false,
                fontSize: 25,
                fontWeight: 'bold'
            }
        },
        data: [
            {
                name: 'has Accepted Answer',
                children: [
                    {
                        name: 'Non-accepted Answer has more upvotes',
                        value: 0,
                    },
                    {
                        name: 'Accepted Answer has more upvotes',
                        value: 0,
                    }
                ]
            },
            {
                name: 'has No Accepted Answer',
                value: 0,
            }
        ],
        label: {
            show: false,
            position: 'center'
        },
    }
})

const id2 = 'chart2'
const option2 = reactive({
    backgroundColor: '#fff',
    grid: {
        left: '5%',
        right: '5%'
    },
    xAxis: [{
        type: 'category',
        data: [],
        axisLine: {
            show: true
        },
        axisTick: {
            show: false
        },
    }],
    yAxis: {
        axisLine: {
            show: true
        },
        axisLabel: {
            color: '#4a657a'
        },
        splitLine: {
            show: false,
            lineStyle: {
                color: '#08263f'
            }
        },
        axisTick: {
            show: false
        }
    },
    visualMap: {
        show: false,
        min: 0,
        max: 50,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    series: [{
        name: 'number',
        type: 'bar',
        data: [],
        z: 3,
        itemStyle: {
            borderRadius: 5
        }
    }],
})

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    let hasAnswerNum = 0
    let hasAcceptedAnswerNum = 0
    let moreUpvotesNum = 0
    axios
        .get("/answers/getNum", {
            params: {
                status: 'hasAnswer'
            }
        })
        .then((response) => {
            hasAnswerNum = response.data
            return axios.get("/answers/getNum", {
                params: {
                    status: 'hasAcceptedAnswer'
                }
            })
        })
        .then((response) => {
            hasAcceptedAnswerNum = response.data
            option1.series.data[1].value = hasAnswerNum - hasAcceptedAnswerNum
            return axios.get("/answers/getNum", {
                params: {
                    status: 'moreUpvotes'
                }
            })
        })
        .then((response) => {
            moreUpvotesNum = response.data
            option1.series.data[0].children[0].value = moreUpvotesNum
            option1.series.data[0].children[1].value = hasAcceptedAnswerNum - moreUpvotesNum
        })
        .catch((error) => {
            console.log(error)
        })
})
</script>
