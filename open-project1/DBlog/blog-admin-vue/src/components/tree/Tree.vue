<template>
  <div class="tree-menu">
    <ul :key="item.name" v-for="item in data">
      <tree-item :data="item"></tree-item>
    </ul>
  </div>
</template>

<script>
import TreeItem from './TreeItem'
export default {
  props: {
    data: {
      type: [Object, Array],
      required: true
    }
  },
  components: {
    TreeItem
  }
}
</script>
