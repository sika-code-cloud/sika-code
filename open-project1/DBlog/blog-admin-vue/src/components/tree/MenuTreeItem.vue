<template>
  <q-expansion-item
    :group="data.groupName"
    :content-inset-level="0.5"
    :label="data.name"
    :icon="data.icon"
    :header-style="headerStyleActive"
    v-if="hasChild"
  >
    <menu-tree-item
      v-for="(item, index) in data.children"
      :data="item"
      :key="index"
    ></menu-tree-item>
  </q-expansion-item>
  <q-item
    v-else-if="!hasChild && data.top"
    clickable
    v-ripple
    :style="itemStyleActive"
    :key="data.name"
    @click="onclick(data)"
    :to="data.to"
  >
    <q-item-section avatar>
      <q-icon :name="data.icon" />
    </q-item-section>
    <q-item-section>{{ data.name }}</q-item-section>
  </q-item>
  <q-item
    clickable
    v-ripple
    :key="data.name"
    :style="itemStyleActive"
    @click="onclick(data)"
    :to="data.to"
    v-else
  >
    <q-item-section>{{ data.name }}</q-item-section>
  </q-item>
</template>

<script>
import EventBus from 'components/tree/EventBus'

const headerStyleDefault = { color: '#1890ff' }
const itemStyleDefault = {
  color: '#1890ff',
  backgroundColor: '#e6f7ff',
  borderRight: '0.2em solid #1890ff'
}
const itemStyleUnActiveDefault = { color: 'black' }
// const itemClassDefault = 'active-item-class'
export default {
  name: 'MenuTreeItem',
  props: {
    data: {
      type: [Object, Array],
      required: true
    },
    activeHeaderStyle: {
      type: Object,
      required: false
    },
    activeItemStyle: {
      type: Object,
      required: false
    }
  },
  data() {
    return {
      active: false,
      headerStyleActive: {},
      itemStyleActive: {}
    }
  },
  computed: {
    hasChild() {
      return this.data.children && this.data.children.length
    },
    currentPath() {
      return this.$route.path
    }
  },
  methods: {
    onclick(nodeData) {
      console.log('---------------' + JSON.stringify(this.currentPath))
      EventBus.$emit('activeItem', nodeData)
    },
    activeItem(currentItem) {
      this.active = this.data.name === currentItem.name
    },
    changeActiveHeaderStyle(currentItem) {
      this.activeItem(currentItem)
      if (
        currentItem.group &&
        this.data.group &&
        currentItem.group.startsWith(this.data.group)
      ) {
        if (this.activeHeaderStyle) {
          this.headerStyleActive = this.activeHeaderStyle
        } else {
          this.headerStyleActive = headerStyleDefault
        }
      } else {
        this.headerStyleActive = {}
      }
    },
    changeActiveItemStyle(currentItem) {
      this.activeItem(currentItem)
      if (this.active) {
        if (this.activeItemStyle) {
          this.itemStyleActive = this.activeItemStyle
        } else {
          this.itemStyleActive = itemStyleDefault
        }
      } else {
        this.itemStyleActive = itemStyleUnActiveDefault
      }
    }
  },
  mounted() {
    EventBus.$on('activeItem', (currentItem) => {
      this.changeActiveItemStyle(currentItem)
      this.changeActiveHeaderStyle(currentItem)
    })
  },
  created() {
    this.itemStyleActive = itemStyleUnActiveDefault
  }
}
</script>

<style lang="sass">
.active-item-class
  color: #1890ff
  background-color: #e6f7ff
</style>
