
<template>
    <div id="app">
        <Movie v-for="movie in movies" :key="movie.id" :title="movie.title" :rating="movie.rating"></Movie>
    </div>
    <div>
        <Buttons></Buttons>
    </div>
    <div>
        <Table></Table>
    </div>
    <div>
        <p>
            <router-link to="/A">AAA</router-link>
            <router-link to="/B">BBB</router-link>
            <router-link to="/C">CCC</router-link>
        </p>
        <router-view></router-view>
    </div>
    <div>
        <button @click="toC">to C</button>
    </div>
</template>

<script>
import Movie from './components/Movie.vue'
import Buttons from './components/ElementPlusButton.vue'
import Table from './components/table.vue'
import A from './components/Model_A.vue'
import B from './components/Model_B.vue'
import C from './components/Model_C.vue'
import { useRouter } from 'vue-router'

export default {
    // setup(){
    //     const router = useRouter()
    //     const toC = (()=>{
    //         router.push({
    //             name:'home'
    //         })
    //     })
    //     return {
    //         toC
    //     }
    // },
    name: 'App',
    data: function () {
        return {
            movies: [
                { id: 1, title: "a", rating: 1.0 },
                { id: 2, title: "b", rating: 2.0 },
                { id: 3, title: "c", rating: 3.0 }
            ]
        }
    },
    created: function () {

    },
    mounted: function () {

    },
    methods: {
        toC () {
            console.log("CCC")
        }
    },
    components: {
        Movie,
        Buttons,
        Table,
        A, B, C
    }
}
</script>

