<template>
  <div>
    <DefaultLayout v-if="layout === 'default'">
      <template v-slot:page>
        <q-page-sticky
          position="right"
          :offset="[18, 18]"
          glossy="true"
        >
          <q-fab
            icon="add"
            direction="up"
            color="accent"
          >
            <q-fab-action
              @click="onClick('default')"
              color="primary"
              icon="person_add"
              :glossy="glossy"
            />
            <q-fab-action
              @click="onClick('google')"
              color="primary"
              icon="mail"
            />
          </q-fab>
        </q-page-sticky>
      </template>
    </DefaultLayout>
    <GoogleNewsLayout v-else-if="layout === 'google'">
      <template v-slot:page>
        <q-page-sticky
          position="right"
          :offset="[18, 18]"
          persistent
          flat
        >
          <q-fab
            icon="add"
            direction="up"
            color="accent"
          >
            <q-fab-action
              @click="onClick('default')"
              color="primary"
              icon="person_add"
              :glossy="glossy"
            />
            <q-fab-action
              @click="onClick('google')"
              color="primary"
              icon="mail"
            />
          </q-fab>
        </q-page-sticky>
      </template>
    </GoogleNewsLayout>
  </div>
</template>

<script>
import DefaultLayout from './DefaultLayout.vue'
import GoogleNewsLayout from './GoogleNewsLayout.vue'
const layoutKey = 'layout'
export default {
  name: 'MainLayout',
  components: { DefaultLayout, GoogleNewsLayout },
  data () {
    return {
      layout: '',
      glossy: true
    }
  },
  methods: {
    onClick (layout) {
      console.log(layout)
      this.layout = layout
      this.$q.localStorage.set(layoutKey, layout)
    }
  },
  created: function () {
    const layoutFromLocal = this.$q.localStorage.getItem(layoutKey)
    if (layoutFromLocal) {
      this.layout = layoutFromLocal
      return
    }
    this.layout = 'default'
  }
}
</script>

<style lang="sass" scoped>
.q-page-sticky
  z-index: 999
</style>
