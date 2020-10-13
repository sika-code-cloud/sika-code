<template>
  <div>
    <q-layout view="hHh LpR fFf">
      <q-header elevated>
        <q-toolbar>
          <q-toolbar-title> Quasar App </q-toolbar-title>
          <div>Quasar v{{ $q.version }}</div>
        </q-toolbar>
      </q-header>
      <q-footer elevated>
        <q-toolbar>
          <q-toolbar-title>Footer</q-toolbar-title>
        </q-toolbar>
      </q-footer>

      <q-page-container>
        <div class="q-pa-sm row justify-center">
          <div class="col text-center" style="width: 100%; max-width: 400px">
            <div>
              <h4 class="q-ma-md">
                <q-avatar class="q-mr-md"
                  ><img
                    alt="Sika logo"
                    src="~assets/sikacode-logo.png" /></q-avatar
                ><strong>Sika Design</strong>
              </h4>
              <p class="text-grey">
                Sika Design 是深圳大湾区最具影响力的Web设计样例
              </p>
            </div>
            <router-view />
          </div>
        </div>
      </q-page-container>
    </q-layout>
  </div>
</template>

<script>
export default {
  name: 'UserLayout',
  props: {},
  data() {
    return {
      leftDrawerOpen: false
    }
  },
  methods: {},
  mounted: function () {}
}
</script>
