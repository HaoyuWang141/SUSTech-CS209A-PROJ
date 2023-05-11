<template>
    <div>
        <h1>{{ title }}</h1>
        <span>{{ rating }}</span>
        <button @click="fun" :disabled="isButtonDisabled">收藏</button>
        <font-awesome-icon icon="fal fa-universal-access" />
        <i class="fa fa-camera-retro"></i>
    </div>
</template>

<script>

export default {
    name: "Movie",
    props: ["title", "rating"],
    data: function(){
        return {
            isButtonDisabled: false
        }
    },
    methods:{
        fun(){
            alert("success")
        }
    }
}
</script>