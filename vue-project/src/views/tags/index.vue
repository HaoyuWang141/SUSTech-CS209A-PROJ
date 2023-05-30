<template>
    <BaseEchart :option="option1" :chart-id="id1"/>
    <br/> <br/>
    <BaseEchart :option="option2" :chart-id="id2"/>
    <p>
        <br />离群值解释：<br /><br />
        我们恰收集到了一个具有 26979 upvotes 的问题，这三个标签仅在该问题上出现，因此其均值同为该值。 <br /><br />
        其他问题最多仅有 7680 upvotes，故与这三个标签有较大差距。 <br /><br />
        performance 标签也在含有 26979 upvotes 的问题中，但也在其他问题中出现，因此它的 upvotes 在平均后处于四千多，处于领先地位，我们暂且将它保留下来。<br /><br />
    </p>
    <BaseEchart :option="option2_1" :chart-id="id2_1"/>
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
            // textStyle: {
            //     color: function () {
            //         return (
            //             "rgb(" +
            //             Math.round(Math.random() * 255) +
            //             ", " +
            //             Math.round(Math.random() * 255) +
            //             ", " +
            //             Math.round(Math.random() * 255) +
            //             ")"
            //         );
            //     },
            // },
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
    grid: {
        left: '3%',
        right: '4%',
        bottom: '0%',
        containLabel: false
    },
    angleAxis: {
        type: 'category',
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    xAxis: {
        type: 'value',
        show: false,
    },
    yAxis: {
        type: 'category',
        data: [],
        show: false,
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
        inRange: {
            color: ['#4a657a', '#308e92', '#b1cfa5', '#f5d69f', '#f5898b', '#ef5055']
        }
    },
    series: [
        {
            type: 'bar',
            data: [],
            itemStyle: {
                borderRadius: 20,
                borderColor: '#fff',
                borderWidth: 3
            },
        }
    ]
})

const id2_1 = 'chart2_1'
const option2_1 = reactive({
    title: {
        text: '1 Tag - Upvotes (avg) [去除前三]',
        left: 'center'
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '0%',
        containLabel: false
    },
    angleAxis: {
        type: 'category',
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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
        data: [],
        show: false,
    },
    tooltip: {},
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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
        data: [],
        show: false,
    },
    tooltip: {
        trigger: 'item'
    },
    radiusAxis: {
        show: false,
    },
    polar: {},
    itemStyle: {
        borderRadius: 20,
        borderColor: '#fff',
        borderWidth: 3
    },
    visualMap: {
        show: false,
        min: 0,
        max: 0,
        dimension: 0,
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

    axios.get("/tags/avgUpvotes-tag", {params: {CombinationNum: "1"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option2.yAxis.data.unshift(key)
                option2.series[0].data.unshift(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option2.visualMap.max = max
            option2.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag", {params: {CombinationNum: "1"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                if (response.data[key] > 10000) {
                    continue
                }
                option2_1.angleAxis.data.push(key)
                option2_1.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option2_1.visualMap.max = max
            option2_1.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag", {params: {CombinationNum: "2"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option3.angleAxis.data.push(key)
                option3.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option3.visualMap.max = max
            option3.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgUpvotes-tag", {
        params: {
            CombinationNum: "3"
        }
    })
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option4.angleAxis.data.push(key)
                option4.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option4.visualMap.max = max
            option4.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgViews-tag", {params: {CombinationNum: "1"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option5.angleAxis.data.push(key)
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

    axios.get("/tags/avgViews-tag", {params: {CombinationNum: "2"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option6.angleAxis.data.push(key)
                option6.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option6.visualMap.max = max
            option6.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })

    axios.get("/tags/avgViews-tag", {params: {CombinationNum: "3"}})
        .then((response) => {
            console.log(response.data)
            let min = Infinity;
            let max = 0;
            for (let key in response.data) {
                option7.angleAxis.data.push(key)
                option7.series[0].data.push(response.data[key])
                min = Math.min(min, response.data[key])
                max = Math.max(max, response.data[key])
            }
            option7.visualMap.max = max
            option7.visualMap.min = min
        })
        .catch((error) => {
            console.log(error)
        })
})
</script>

<style>
</style>