<template>
    <section class="app-main">
        <router-view v-slot="{ Component }">
            <transition name="fade">
                <component :is="Component"/>
            </transition>
        </router-view>
    </section>
</template>

<script setup>
</script>

<style scoped>
.app-main {
    height: 95%;
    border: 2px solid red;
    padding: 2px;
    position: relative;
    overflow: hidden;
}
</style>