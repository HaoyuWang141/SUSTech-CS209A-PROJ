<template>
    <div>
        <h1>{{ title }}</h1>
        <span>{{ rating }}</span>
        <button @click="fun" :disabled="isButtonDisabled">收藏</button>
    </div>
</template>

<script>
export default {
    name: "Movie",
    props: ["title", "rating"],
    data: function(){
        isButtonDisabled: true
    },
    methods:{
        fun(){
            alert("success")
        }
    }
}
</script>