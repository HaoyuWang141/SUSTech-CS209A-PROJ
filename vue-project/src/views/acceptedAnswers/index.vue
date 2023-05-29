<template>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id2" :option="option2" class="row"/>
    <BaseEchart :chart-id="id3" :option="option3" class="row"/>
    <BaseEchart :chart-id="id4" :option="option4"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {getCurrentInstance, onBeforeMount, reactive} from "vue";

const id1 = 'chart1'
const option1 = reactive({
    title: {
        text: 'Analysis of Accepted Answers',
        left: 'center'
    },
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
    title: {
        text: 'Proportion of Accepted Answers',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}: {c} ({d}%)',
    },
    legend: {
        orient: 'horizontal',
        left: 'center',
        top: '10%'
    },
    grid: {
        left: 'center',
        top: 'center',
        width: '100%',
        height: '100%'
    },
    series: {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: [
            {value: 0, name: 'Accepted'},
            {value: 0, name: 'Non-accepted'}
        ],
        label: {
            show: false,
        },
        emphasis: {
            itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        itemStyle: {
            normal: {
                color: function (colors) {
                    const colorList = ['#fc8251', '#5470c6'];
                    return colorList[colors.dataIndex];
                }
            },
        }
    }
})

const id3 = 'chart3'
const option3 = reactive({
    title: {
        text: 'Proportion of Special Accepted Answers',
        subtext: 'another answer has more upvotes than the accepted answer',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}: {c} ({d}%)',
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        top: '12%'
    },
    grid: {
        left: 'center',
        top: 'center',
        width: '100%',
        height: '100%'
    },
    series: {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: [
            {value: 0, name: 'Accepted Answer More Upvotes'},
            {value: 0, name: 'Non-accepted Answer More Upvotes'}
        ],
        label: {
            show: false,
        },
        emphasis: {
            itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        itemStyle: {
            normal: {
                color: function (colors) {
                    const colorList = ['#91cd77', '#ef6567'];
                    return colorList[colors.dataIndex];
                }
            },
        }
    }
})

const id4 = 'chart4'
const option4 = reactive({
    title: {
        text: 'Distribution of Resolution Time',
        left: 'center'
    },
    backgroundColor: '#fff',
    tooltip: {
        show: true,
    },
    grid: {
        left: '10%',
        right: '5%',
        top: '20%',
        bottom: '20%',
        // containLabel: true
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
        min: 5,
        max: 30,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    series: [{
        name: 'number',
        type: 'bar',
        data: [],
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
        .get("/questions/getNum", {params: {status: 'hasAnswer'}})
        .then((response) => {
            hasAnswerNum = response.data
            return axios.get("/questions/getNum", {params: {status: 'hasAcceptedAnswer'}})
        })
        .then((response) => {
            hasAcceptedAnswerNum = response.data
            option1.series.data[1].value = hasAnswerNum - hasAcceptedAnswerNum
            option2.series.data[0].value = hasAcceptedAnswerNum
            option2.series.data[1].value = hasAnswerNum - hasAcceptedAnswerNum
            return axios.get("/questions/getNum", {params: {status: 'moreUpvotes'}})
        })
        .then((response) => {
            moreUpvotesNum = response.data
            option1.series.data[0].children[0].value = moreUpvotesNum
            option1.series.data[0].children[1].value = hasAcceptedAnswerNum - moreUpvotesNum
            option3.series.data[0].value = moreUpvotesNum
            option3.series.data[1].value = hasAcceptedAnswerNum - moreUpvotesNum
        })
        .catch((error) => {
            console.log(error)
        })

    axios
        .get("/answers/ThreadNum-ResolutionTime")
        .then((response) => {
            let min = Infinity
            let max = 0
            for (let key in response.data) {
                option4.xAxis[0].data.push(key)
                option4.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option4.visualMap.min = min
            option4.visualMap.max = max
        })
})
</script>

<style>
.row {
//display: flex;
}
</style>
