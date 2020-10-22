<template>
  <div class="tree-menu">
    <q-expansion-item
      :content-inset-level="0.5"
      icon="dashboard"
      label="仪表盘"
      :header-style="menuHeadStyle('yibiaopan')"
      :key="item.name"
      v-for="item in data"
    >
      <menu-tree-item :data="item"></menu-tree-item>
    </q-expansion-item>
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
      link: ''
    }
  },
  methods: {
    toggle() {
      if (this.hasChild) {
        this.open = !this.open
      }
    },
    menuHeadStyle(startWith) {
      if (this.link.startsWith(startWith)) {
        return { color: '#1890ff' }
      }
    }
  },
  components: {
    MenuTreeItem
  }
}
</script>
