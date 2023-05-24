<template>
    <BaseEchart :chart-id="id1" :option="option1"/>
    <BaseEchart :chart-id="id2" :option="option2"/>
    <BaseEchart :chart-id="id3" :option="option3"/>
    <el-table
            :data="tableData"
            :span-method="spanMethod"
            style="padding: 10px 0 100px 0;
                    display: flex;
                    justify-content: center;
                    align-items: center;"
            :header-cell-style="{'text-align':'center'}">
        class="table">
        <el-table-column
                prop="range"
                label="范围"
                width="180"
                align="center">
        </el-table-column>
        <el-table-column
                prop="item"
                label="条目"
                width="180"
                align="center">
        </el-table-column>
        <el-table-column
                prop="number"
                label="数量"
                width="180"
                align="center">
        </el-table-column>
    </el-table>
    <BaseEchart :chart-id="id5" :option="option5"/>

</template>
<script setup>
import BaseEchart from "@/components/Echarts/BaseEchart.vue";
import {onBeforeMount, onMounted, reactive, getCurrentInstance, ref} from "vue";

const id1 = 'chart1'
const option1 = reactive({
    title: {
        text: 'Distribution of All Users',
        left: 'center'
    },
    tooltip: {
        show: true,
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

const id2 = 'chart2'
const option2 = reactive({
    title: {
        text: 'Distribution of Answer Users',
        left: 'center'
    },
    tooltip: {
        show: true,
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

const id3 = 'chart3'
const option3 = reactive({
    title: {
        text: 'Distribution of Comment Users',
        left: 'center'
    },
    tooltip: {
        show: true,
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

const tableData = reactive([])

const id5 = 'chart5'
const option5 = reactive({
    title: {
        text: 'Active Users',
        left: 'center'
    },
    polar: {
        radius: ['10%', '70%']
    },
    angleAxis: {
        max: 4,
        startAngle: 90
    },
    radiusAxis: {
        type: 'category',
        data: [],
        axisLabel: {
            show: false
        },
    },
    visualMap: {
        show: false,
        min: 30,
        max: 100,
        dimension: 1,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    tooltip: {},
    series: {
        type: 'bar',
        data: [],
        coordinateSystem: 'polar',
        label: {
            show: true,
            position: 'middle',
            formatter: '{c}'
        }
    }
})

function spanMethod({row, column, rowIndex, columnIndex}) {
    if (columnIndex === 0) { //选择列
        if (rowIndex % 3 === 0) { //选择行
            return {
                rowspan: 3, //合并数量
                colspan: 1
            };
        } else {
            return {
                rowspan: 0,
                colspan: 0
            };
        }
    }
}

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get("/users/usersDistribution", {
        params: {
            type: 'all'
        }
    })
        .then((response) => {
            for (let key in response.data) {
                option1.xAxis.data.push(key)
                option1.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/usersDistribution", {
        params: {
            type: 'answer'
        }
    })
        .then((response) => {
            for (let key in response.data) {
                option2.xAxis.data.push(key)
                option2.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/usersDistribution", {
        params: {
            type: 'comment'
        }
    })
        .then((response) => {
            for (let key in response.data) {
                option3.xAxis.data.push(key)
                option3.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/participationAnalysis", {
        params: {
            type: 'all'
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                tableData.push({
                    range: '全部参与者',
                    item: key,
                    number: response.data[key]
                })
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/participationAnalysis", {
        params: {
            type: 'answer'
        }
    })
        .then((response) => {
            for (let key in response.data) {
                tableData.push({
                    range: '回答者角度',
                    item: key,
                    number: response.data[key]
                })
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/participationAnalysis", {
        params: {
            type: 'comment'
        }
    })
        .then((response) => {
            for (let key in response.data) {
                tableData.push({
                    range: '评论者角度',
                    item: key,
                    number: response.data[key]
                })
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/users/isActive")
        .then((response) => {
            option5.angleAxis.max = 0
            for (let key in response.data) {
                option5.radiusAxis.data.push(key)
                option5.series.data.push(response.data[key])
                if (response.data[key] > option5.angleAxis.max) {
                    option5.angleAxis.max = response.data[key]
                }
            }
        }).catch((error) => {
        console.log(error)
    })
})
</script>

<style scoped>
/*.chartContainer {
    display: flex;
    justify-content: center;
    align-items: center;
}*/
</style>