<template>
    <div>number of answers</div>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <el-table
            :data="tableData"
            style="width: 100%"
            class="table">
        <el-table-column
                prop="item"
                label="条目"
                width="180">
        </el-table-column>
        <el-table-column
                prop="number"
                label="数量"
                width="180">
        </el-table-column>
    </el-table>
    <BaseEchart :chart-id="id3" :option="option3"/>
    <BaseEchart :chart-id="id4" :option="option4"/>
</template>

<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue"
import {onBeforeMount, onMounted, reactive, getCurrentInstance, ref} from "vue";

const id1 = ref('chart1')
const option1 = reactive({
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
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
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
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
        item: '平均每个问题的回答数',
        number: 0
    },
    {
        item: '最多回答数',
        number: 0
    }
])

const id3 = 'chart3'
const option3 = reactive({
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            data: [],
            type: 'bar'
        }
    ]
})

const id4 = 'chart4'
const option4 = reactive({
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

        type: 'value'
    },
    series: [
        {
            data: [],
            type: 'line'
        }
    ],
    visualMap: {
        show: false,
        min: 10,
        max: 30,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
});

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get("/answers/getNum", {
        params: {
            status: 'hasAnswer'
        }
    }).then((response) => {
        option1.series[0].data[0].value = response.data
        console.log(option1.series[0].data[0].value)
        return axios.get("/answers/getNum",
            {
                params: {
                    status: 'all'
                }
            })
    }).then((response) => {
        option1.series[0].data[1].value = response.data - option1.series[0].data[0].value
        console.log(option1.series[0].data[1].value)
    }).catch((error) => {
        console.log(error)
    })

    axios.get("/answers/getNum", {params: {status: 'avgAnswerNum'}})
        .then((response) => {
            tableData[0].number = response.data
        }).catch((error) => {
        console.log(error)
    })

    axios.get("/answers/getNum", {params: {status: 'maxAnswerNum'}})
        .then((response) => {
            tableData[1].number = response.data
        }).catch((error) => {
        console.log(error)
    })

    axios.get("/answers/ThreadNum-AnswerNum")
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option3.xAxis.data.push(key)
                option3.series[0].data.push(response.data[key])
            }
        }).catch((error) => {
        console.log(error)
    })

    axios.get("/answers/AnswerNum-Time")
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option4.xAxis.data.push(key)
                option4.series[0].data.push(response.data[key])
            }
        }).catch((error) => {
        console.log(error)
    })
})

onMounted(() => {
    console.log('mounted')
})

</script>

<style scoped>
.table {
    padding: 50px 50px 50px 50px;
    border: 1px solid red;
}
</style>