<template>
    <BaseEchart :option="option1" :chart-id="id1"/>
    <BaseEchart :option="option2" :chart-id="id2"/>
    <BaseEchart :option="option3" :chart-id="id3"/>
    <BaseEchart :option="option4" :chart-id="id4"/>
    <BaseEchart :option="option5" :chart-id="id5"/>
    <BaseEchart :option="option6" :chart-id="id6"/>
    <BaseEchart :option="option7" :chart-id="id7"/>
</template>

<script setup>

import BaseEchart from "@/components/Echarts/BaseEchart.vue";
import {getCurrentInstance, onBeforeMount, reactive} from "vue";
import "echarts-wordcloud"

const id1 = 'chart1'
const option1 = reactive({
    series: [
        {
            type: "wordCloud",
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 60],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 0],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
                color: function () {
                    return (
                        "rgb(" +
                        Math.round(Math.random() * 255) +
                        ", " +
                        Math.round(Math.random() * 255) +
                        ", " +
                        Math.round(Math.random() * 255) +
                        ")"
                    );
                },
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
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

const id2 = 'chart2'
const option2 = reactive({
    title: {
        text: '1 Tag - Upvotes (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            radius: ['50%'],
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
        }
    ]
})

const id3 = 'chart3';
const option3 = reactive({
    title: {
        text: 'Combination of 2 Tags - Upvotes (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            radius: ['50%'],
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
        }
    ]
})

const id4 = 'chart4'
const option4 = reactive({
    title: {
        text: 'Combination of 3 Tags - Upvotes (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {},
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
            stack: 'a',
            emphasis: {
                focus: 'series'
            }
        }
    ]
})

const id5 = 'chart5';
const option5 = reactive({
    title: {
        text: '1 Tag - Views (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            radius: ['50%'],
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
        }
    ]
})

const id6 = 'chart6';
const option6 = reactive({
    title: {
        text: 'Combination of 2 Tags - Views (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            radius: ['50%'],
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
        }
    ]
})

const id7 = 'chart7';
const option7 = reactive({
    title: {
        text: 'Combination of 3 Tags - Views (avg)',
        left: 'center'
    },
    angleAxis: {
        type: 'category',
        data: []
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {},
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
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
            radius: ['50%'],
            type: 'bar',
            data: [],
            coordinateSystem: 'polar',
        }
    ]
})

onBeforeMount(() => {
    const axios = getCurrentInstance().appContext.config.globalProperties.$http
    axios.get("/tags/QuestionNum-tag")
        .then((response) => {
            for (let key in response.data) {
                option1.series[0].data.push({
                    name: key,
                    value: response.data[key]
                })
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag" ,{
        params : {
            CombinationNum : "1"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option2.angleAxis.data.push(key)
                option2.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag" ,{
        params : {
            CombinationNum : "2"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option3.angleAxis.data.push(key)
                option3.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag" ,{
        params : {
            CombinationNum : "3"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option4.angleAxis.data.push(key)
                option4.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgViews-tag",{
        params : {
            CombinationNum : "1"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option5.angleAxis.data.push(key)
                option5.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgViews-tag",{
        params : {
            CombinationNum : "2"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option6.angleAxis.data.push(key)
                option6.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgViews-tag",{
        params : {
            CombinationNum : "3"
        }
    })
        .then((response) => {
            console.log(response.data)
            for (let key in response.data) {
                option7.angleAxis.data.push(key)
                option7.series[0].data.push(response.data[key])
            }
        })
        .catch((error) => {
            console.log(error)
        })

})
</script>