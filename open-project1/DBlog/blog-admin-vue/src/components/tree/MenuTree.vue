<template>
  <div class="tree-menu">
    <span :key="item.name" v-for="item in data">
      <menu-tree-item :data="item" :link="link"></menu-tree-item>
    </span>
  </div>
</template>

<script>
import MenuTreeItem from './MenuTreeItem'
export default {
  props: {
    data: {
      type: [Object, Array],
      required: true
    }
  },
  data() {
    return {
      link: '',
      parentNode: {
        children: this.data
      }
    }
  },
  methods: {},
  components: {
    MenuTreeItem
  }
}
</script>
<style lang="sass"></style>
