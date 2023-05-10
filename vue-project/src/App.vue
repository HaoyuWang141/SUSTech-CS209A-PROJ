<template>
    <div id="app">
        <Movie v-for:></Movie>
    </div>
</template>

<script>
    import Movie from './components/Movie.vue'
    export default{
    name: 'App',
    data: function(){
        return {
            movies: [
                {id:1,title:"a",rating:1.0},
                {id:2,title:"b",rating:2.0},
                {id:3,title:"c",rating:3.0}
            ]
        }
    },
    components: {
        Movie
    }
}
</script>