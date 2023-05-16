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
    border: 2px solid red;
    padding: 2px;
    position: relative;
}
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
    opacity: 0;
}
</style>