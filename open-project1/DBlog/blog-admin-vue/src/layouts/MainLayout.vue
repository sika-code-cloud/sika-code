<template>
  <div>
    <StandardLayout />
  </div>
</template>

<script>
import StandardLayout from './StandardLayout.vue'
export default {
  name: 'MainLayout',
  components: { StandardLayout },
  data() {
    return {}
  },
  methods: {},
  created: function() {
    this.layout = 'standard'
  }
}
</script>

<style lang="sass" scoped>
</style>
