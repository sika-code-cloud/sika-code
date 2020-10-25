<template>
  <div>
    <q-layout view="hHh LpR fFf" style="font-family: 微软雅黑">
      <q-header elevated>
        <q-toolbar>
          <q-btn
            flat
            dense
            round
            icon="menu"
            aria-label="Menu"
            @click="onClick"
          />

          <q-toolbar-title> Quasar App </q-toolbar-title>

          <div>Quasar v{{ $q.version }}</div>
        </q-toolbar>
      </q-header>
      <q-footer elevated>
        <q-toolbar>
          <q-toolbar-title>Footer</q-toolbar-title>
        </q-toolbar>
      </q-footer>
      <q-drawer
        v-model="leftDrawerOpen"
        bordered
        content-class="bg-grey-1"
        @hide="hide"
        @show="show"
      >
        <EssentialLink />
      </q-drawer>

      <q-page-container>
        <slot name="page">hello world</slot>
        <router-view />
      </q-page-container>
    </q-layout>
  </div>
</template>

<script>
import EssentialLink from 'components/EssentialLink.vue'

export default {
  name: 'MainLayout',
  components: { EssentialLink },
  props: {},
  data() {
    return {
      leftDrawerOpen: false
    }
  },
  methods: {
    onClick() {
      this.leftDrawerOpen = !this.leftDrawerOpen
    },
    hide() {
      console.log(this.leftDrawerOpen)
      this.$q.localStorage.set('leftDrawerOpen', this.leftDrawerOpen)
    },
    show() {
      console.log(this.leftDrawerOpen)
      this.$q.localStorage.set('leftDrawerOpen', this.leftDrawerOpen)
    },
    getLeftDrawOpen() {
      const leftDrawerOpenFromLocal = this.$q.localStorage.getItem(
        'leftDrawerOpen'
      )
      if (leftDrawerOpenFromLocal) {
        return leftDrawerOpenFromLocal
      }
      return false
    }
  },
  mounted: function () {
    this.leftDrawerOpen = this.getLeftDrawOpen()
  }
}
</script>
