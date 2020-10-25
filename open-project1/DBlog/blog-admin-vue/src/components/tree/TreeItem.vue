<template>
  <li>
    <span @click="toggle">
      <em v-if="hasChild" class="icon">{{ open ? '-' : '+' }}</em>
      <em v-if="!hasChild" class="icon file-text"></em>
      {{ data.name }}
    </span>
    <ul v-show="open" v-if="hasChild">
      <tree-item
        v-for="(item, index) in data.children"
        :data="item"
        :key="index"
      ></tree-item>
    </ul>
  </li>
</template>

<script>

export default {
  name: 'TreeItem',
  props: {
    data: {
      type: [Object, Array],
      required: true
    }
  },
  data() {
    return {
      open: false
    }
  },
  computed: {
    hasChild() {
      return this.data.children && this.data.children.length
    }
  },
  methods: {
    toggle() {
      if (this.hasChild) {
        this.open = !this.open
      }
    }
  }
}
</script>

<style>
ul {
  list-style: none;
  margin: 10px 0;
}
li {
  padding: 3px 0;
}
li > span {
  cursor: pointer;
  font-size: 14px;
  line-height: 20px;
}
li > span:visited {
  background: #fff;
}
em.icon {
  display: inline-block;
  width: 20px;
  height: 20px;
  margin-right: 5px;
  background-repeat: no-repeat;
  vertical-align: middle;
}
.tree-menu li {
  line-height: 1.5;
}
</style>
