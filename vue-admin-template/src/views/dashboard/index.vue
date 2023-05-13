<template>
  <div class="dashboard-container">
    <div class="dashboard-text">123: {{ name }} 456 {{ a }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  data: function () {
    return {a: 'aaa'}
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 50px;
    line-height: 46px;
  }
}
</style>
