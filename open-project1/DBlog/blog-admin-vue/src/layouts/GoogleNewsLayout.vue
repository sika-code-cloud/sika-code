<template>
  <q-layout
    view="hHh LpR fFf"
    class="bg-grey-1"
  >
    <q-header
      elevated
      class="bg-white title-grey-8"
      height-hint="64"
    >
      <q-toolbar class="GNL__toolbar">
        <q-btn
          flat
          dense
          round
          @click="onClick"
          aria-label="Menu"
          icon="menu"
          class="q-mr-sm"
          color="grey-8"
        />

        <q-toolbar-title
          v-if="$q.screen.gt.xs"
          shrink
          class="row items-center no-wrap"
        >
          <img src="https://cdn.quasar.dev/img/layout-gallery/logo-google.svg" />
          <span class="q-ml-sm">News</span>
        </q-toolbar-title>

        <q-space />

        <q-input
          class="GNL__toolbar-input"
          outlined
          dense
          v-model="search"
          color="bg-grey-7 shadow-1"
          placeholder="Search for topics, locations & sources"
        >
          <template v-slot:prepend>
            <q-icon
              v-if="search === ''"
              name="search"
            />
            <q-icon
              v-else
              name="clear"
              class="cursor-pointer"
              @click="search = ''"
            />
          </template>
          <template v-slot:append>
            <q-btn
              flat
              dense
              round
              aria-label="Menu"
              icon="arrow_drop_down"
            >
              <q-menu
                anchor="bottom right"
                self="top right"
              >
                <div
                  class="q-pa-md"
                  style="width: 400px"
                >
                  <div class="title-body2 title-grey q-mb-md">
                    Narrow your search results
                  </div>

                  <div class="row items-center">
                    <div class="col-3 title-subtitle2 title-grey">
                      Exact phrase
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input
                        dense
                        v-model="exactPhrase"
                      />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">
                      Has words
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input
                        dense
                        v-model="hasWords"
                      />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">
                      Exclude words
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input
                        dense
                        v-model="excludeWords"
                      />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">
                      Website
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input
                        dense
                        v-model="byWebsite"
                      />
                    </div>

                    <div class="col-12 q-pt-lg row justify-end">
                      <q-btn
                        flat
                        dense
                        no-caps
                        color="grey-7"
                        size="md"
                        style="min-width: 68px;"
                        label="Search"
                        v-close-popup
                      />
                      <q-btn
                        flat
                        dense
                        no-caps
                        color="grey-7"
                        size="md"
                        style="min-width: 68px;"
                        @click="onClear"
                        label="Clear"
                        v-close-popup
                      />
                    </div>
                  </div>
                </div>
              </q-menu>
            </q-btn>
          </template>
        </q-input>

        <q-space />

        <div class="q-gutter-sm row items-center no-wrap">
          <q-btn
            v-if="$q.screen.gt.sm"
            round
            dense
            flat
            color="title-grey-7"
            icon="apps"
          >
            <q-tooltip>Google Apps</q-tooltip>
          </q-btn>
          <q-btn
            round
            dense
            flat
            color="grey-8"
            icon="notifications"
          >
            <q-badge
              color="red"
              title-color="white"
              floating
            >
              2
            </q-badge>
            <q-tooltip>Notifications</q-tooltip>
          </q-btn>
          <q-btn
            round
            flat
          >
            <q-avatar size="26px">
              <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602169145979&di=27aec60072c82766266a914fd4c22093&imgtype=0&src=http%3A%2F%2Fbbsnew.pic.178.com%2Fpic%2F16%2F164022%2Fmonth_1212%2F234047nqz70o77z0ha692a.jpg" />
            </q-avatar>
            <q-tooltip>Account</q-tooltip>
          </q-btn>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      bordered
      content-class="bg-white"
      :width="280"
      @hide="hide"
      @show="show"
    >
      <q-scroll-area class="fit">
        <EssentialLink />
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <slot name="page">hello world</slot>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>

import EssentialLink from 'components/EssentialLink.vue'

export default {
  name: 'GoogleNewsLayout',
  props: {
  },
  data () {
    return {
      leftDrawerOpen: false,
      search: '',
      showAdvanced: false,
      showDateOptions: false,
      exactPhrase: '',
      hasWords: '',
      excludeWords: '',
      byWebsite: '',
      byDate: 'Any time'
    }
  },
  components: { EssentialLink },
  methods: {
    onClear () {
      this.exactPhrase = ''
      this.hasWords = ''
      this.excludeWords = ''
      this.byWebsite = ''
      this.byDate = 'Any time'
    },
    changeDate (option) {
      this.byDate = option
      this.showDateOptions = false
    },
    onClick () {
      this.leftDrawerOpen = !this.leftDrawerOpen
    },
    hide () {
      console.log(this.leftDrawerOpen)
      this.$q.localStorage.set('leftDrawerOpen', this.leftDrawerOpen)
    },
    show () {
      console.log(this.leftDrawerOpen)
      this.$q.localStorage.set('leftDrawerOpen', this.leftDrawerOpen)
    },
    getLeftDrawOpen () {
      const leftDrawerOpenFromLocal = this.$q.localStorage.getItem('leftDrawerOpen')
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

<style lang="sass">
.GNL
  &__toolbar
    height: 64px
  &__toolbar-input
    width: 55%
  &__drawer-item
    line-height: 24px
    border-radius: 0 24px 24px 0
    margin-right: 12px
    .q-item__section--avatar
      .q-icon
        color: #5f6368
    .q-item__label
      color: #3c4043
      letter-spacing: .01785714em
      font-size: .875rem
      font-weight: 500
      line-height: 1.25rem
  &__drawer-footer-link
    color: inherit
    title-decoration: none
    font-weight: 500
    font-size: .75rem
    &:hover
      color: #000
</style>
