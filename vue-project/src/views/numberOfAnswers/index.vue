<template>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <el-table
            :data="tableData"
            style="width: 100% ;padding: 10px 0 20px 280px;"
            class="table"
            :header-cell-style="{'text-align':'center'}">
        <el-table-column
                prop="item"
                label="条目"
                width="180">
        </el-table-column>
        <el-table-column
                prop="number"
                label="数量"
                width="180"
                align="center">
        </el-table-column>
    </el-table>
    <br/><br/>
    <BaseEchart :chart-id="id3" :option="option3"/>
    <BaseEchart :chart-id="id4" :option="option4"/>
    <BaseEchart :chart-id="id5" :option="option5"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {onBeforeMount, onMounted, reactive, getCurrentInstance, ref} from "vue";

const id1 = ref('chart1')
const option1 = reactive({
    title: {
        text: 'Number of Questions with Answer',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}: {c} ({d}%)',
    },
    legend: {
        top: '5%',
        left: 'center'
    },
    series: [
        {
            name: 'Questions',
            type: 'pie',
            radius: ['45%', '75%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 20,
                borderColor: '#fff',
                borderWidth: 3
            },
            label: {
                show: false,
                position: 'center',
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
                {value: 0, name: 'with answers'},
                {value: 0, name: 'without answers'}
            ]
        }
    ]
})

const tableData = reactive([
    {
        item: '每个问题的平均回答数',
        number: 0
    },
    {
        item: '最少回答数',
        number: 0
    },
    {
        item: '最多回答数',
        number: 0
    }
])

const id3 = 'chart3'
const option3 = reactive({
    title: {
        text: 'Distribution of Answer Number',
        left: 'center'
    },
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
    xAxis: {
        type: 'category',
        data: [],
        axisLine: {
            show: true,
        },
        axisTick: {
            show: false
        },
    },
    yAxis: {
        type: 'value',
        axisLine: {
            show: true,
        },
        splitLine: {
            show: false
        }
    },
    visualMap: {
        show: false,
        min: 0,
        max: 30,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    series: [
        {
            data: [],
            type: 'bar',
            itemStyle: {
                // opacity: 0.4,
                borderRadius: 30,
                // shadowBlur: 3,
                // shadowColor: 'red'
            },
        }
    ]
})

const id4 = 'chart4'
const option4 = reactive({
    title: {
        text: 'Question Number Over Time',
        left: 'center'
    },
    grid: {
        left: '10%',
        right: '5%',
        top: '20%',
        bottom: '20%',
        // containLabel: true
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value',
        axisLine: {
            show: true,
        },
        splitLine: {
            show: false
        }
    },
    series: [
        {
            data: [],
            type: 'line'
        }
    ],
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
});

const id5 = 'chart5'
const option5 = reactive({
    title: {
        text: 'Answer Number Over Time',
        left: 'center'
    },
    grid: {
        left: '10%',
        right: '5%',
        top: '20%',
        bottom: '20%',
        // containLabel: true
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value',
        axisLine: {
            show: true,
        },
        splitLine: {
            show: false
        }
    },
    series: [
        {
            data: [],
            type: 'line'
        }
    ],
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
});

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get("/questions/getNum", {params: {status: 'hasAnswer'}})
        .then((response) => {
            option1.series[0].data[0].value = response.data
            return axios.get("/questions/getNum", {params: {status: 'all'}})
        })
        .then((response) => {
            option1.series[0].data[1].value = response.data - option1.series[0].data[0].value
            return axios.get("/answers/getNum", {params: {status: 'avg'}})
        })
        .then((response) => {
            tableData[0].number = response.data
            return axios.get("/answers/getNum", {params: {status: 'min'}})
        })
        .then((response) => {
            tableData[1].number = response.data
            return axios.get("/answers/getNum", {params: {status: 'max'}})
        })
        .then((response) => {
            tableData[2].number = response.data
            return axios.get("/answers/QuestionNum-AnswerNum")
        })
        .then((response) => {
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option3.xAxis.data.push(key)
                option3.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option3.visualMap.max = max
            option3.visualMap.min = min
            return axios.get("/questions/QuestionNum-Time")
        })
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option4.xAxis.data.push(key)
                option4.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option4.visualMap.max = max
            option4.visualMap.min = min
            return axios.get("/answers/AnswerNum-Time")
        })
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option5.xAxis.data.push(key)
                option5.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option5.visualMap.max = max
            option5.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })
})

</script>

<style scoped>
.table {
    padding: 50px 50px 50px 50px;
}
</style>