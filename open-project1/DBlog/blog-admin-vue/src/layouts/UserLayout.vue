<template>
  <div>
    <q-layout view="hHh LpR fff" style="font-family: 微软雅黑">
      <q-header class="bg-white text-primary shadow-1">
        <q-toolbar>
          <q-toolbar-title> Sika Code Design </q-toolbar-title>
          <div>Sika v1.0.0</div>
        </q-toolbar>
      </q-header>
      <q-footer class="bg-white text-blue-grey-4">
        <div class="q-mb-lg">
          <div class="text-center q-mb-sm">
            <span class="inline-block">Sika Design Pro</span>
            <q-icon name="ti-github  q-mx-md"></q-icon>
            <span class="inline-block">Sika Design</span>
          </div>
          <div class="text-center">Copyright@2019 Sika 体验技术部出品</div>
        </div>
      </q-footer>
      <q-page-container>
        <div class="q-pa-sm row justify-center" :class="topMarginClass">
          <div class="col text-center" style="width: 100%; max-width: 400px">
            <div class="q-mb-xl">
              <h4 class="q-ma-sm">
                <q-avatar class="q-mr-md">
                  <img alt="Sika logo" src="~assets/sikacode-logo.png" />
                </q-avatar>
                <strong>Sika Design</strong>
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
      topMarginClass: 'q-mt-lg'
    }
  },
  methods: {
    topMargin() {}
  },
  mounted: function () {}
}
</script>
