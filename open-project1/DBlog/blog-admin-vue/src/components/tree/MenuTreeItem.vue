<template>
  <span>
    <q-expansion-item
      :content-inset-level="0.5"
      :label="data.name"
      :header-style="menuHeadStyle('yibiaopan')"
      v-if="hasChild"
    >
      <menu-tree-item
        v-for="(item, index) in data.children"
        :data="item"
        :key="index"
      ></menu-tree-item>
      {{ data.name }}
    </q-expansion-item>
    <q-item
      clickable
      v-ripple
      :key="data.name"
      :active="link === data.name"
      @click="link = data.name"
      active-class="my-menu-link"
      v-else
    >
      <q-item-section>{{ data.name }}</q-item-section>
    </q-item>
  </span>
</template>

<script>
export default {
  name: 'MenuTreeItem',
  props: {
    data: {
      type: [Object, Array],
      required: true
    }
  },
  data() {
    return {
      open: false,
      link: ''
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
    },
    menuHeadStyle(startWith) {
      if (this.link.startsWith(startWith)) {
        return { color: '#1890ff' }
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
