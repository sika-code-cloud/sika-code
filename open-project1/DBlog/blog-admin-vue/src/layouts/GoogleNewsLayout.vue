<template>
  <q-layout view="hHh LpR fFf" class="bg-grey-1" style="font-family: 微软雅黑">
    <q-resize-observer @resize="onResize" />
    <q-header elevated class="bg-white title-grey-8" height-hint="64">
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
          class="row items-center no-wrap q-mr-sm"
        >
          <q-img src="~assets/sika-logo-1.png" style="width: 100px" />
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
            <q-icon v-if="search === ''" name="search" />
            <q-icon
              v-else
              name="clear"
              class="cursor-pointer"
              @click="search = ''"
            />
          </template>
          <template v-slot:append>
            <q-btn flat dense round aria-label="Menu" icon="arrow_drop_down">
              <q-menu anchor="bottom right" self="top right">
                <div class="q-pa-md" style="width: 400px">
                  <div class="title-body2 title-grey q-mb-md">
                    Narrow your search results
                  </div>

                  <div class="row items-center">
                    <div class="col-3 title-subtitle2 title-grey">
                      Exact phrase
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input dense v-model="exactPhrase" />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">
                      Has words
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input dense v-model="hasWords" />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">
                      Exclude words
                    </div>
                    <div class="col-9 q-pl-md">
                      <q-input dense v-model="excludeWords" />
                    </div>

                    <div class="col-3 title-subtitle2 title-grey">Website</div>
                    <div class="col-9 q-pl-md">
                      <q-input dense v-model="byWebsite" />
                    </div>

                    <div class="col-12 q-pt-lg row justify-end">
                      <q-btn
                        flat
                        dense
                        no-caps
                        color="grey-7"
                        size="md"
                        style="min-width: 68px"
                        label="Search"
                        v-close-popup
                      />
                      <q-btn
                        flat
                        dense
                        no-caps
                        color="grey-7"
                        size="md"
                        style="min-width: 68px"
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

        <div class="q-gutter-xs q-ml-sm row items-center no-wrap">
          <q-btn round dense flat size="sm" color="grey-7" icon="help_outline">
            <q-tooltip>帮助文档</q-tooltip>
          </q-btn>
          <q-btn round flat size="sm" color="grey-8" icon="notifications_none">
            <q-badge color="red" title-color="white" floating> 2 </q-badge>
            <q-tooltip>消息列表</q-tooltip>
          </q-btn>
          <span class="inline-block">
            <q-chip dense color="white" class="cursor-pointer q-ml-sm">
              <q-avatar size="sm">
                <q-img src="~assets/sikacode-logo.png" />
              </q-avatar>
              <span
                class="inline-block"
                style="
                  max-width: 50px;
                  white-space: nowrap;
                  text-overflow: ellipsis;
                  overflow: hidden;
                "
                >Emailseeeeee</span
              >
              <q-menu
                :offset="[0, 28]"
                transition-show="jump-down"
                transition-hide="jump-up"
              >
                <q-list style="min-width: 100px" dense>
                  <q-item clickable v-close-popup>
                    <q-item-section>个人中心</q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup>
                    <q-item-section>个人设置</q-item-section>
                  </q-item>
                  <q-separator color="grey-4" />
                  <q-item clickable v-close-popup>
                    <q-item-section>退出登录</q-item-section>
                  </q-item>
                </q-list>
              </q-menu>
            </q-chip>
          </span>
          <q-btn round dense flat color="grey-9" size="sm" icon="translate">
            <q-menu
              transition-show="jump-down"
              transition-hide="jump-up"
              :offset="[0, 28]"
            >
              <q-list style="min-width: 120px" dense>
                <q-item clickable v-close-popup>
                  <q-item-section>Us English</q-item-section>
                </q-item>
                <q-item clickable v-close-popup>
                  <q-item-section>CN 简体中文</q-item-section>
                </q-item>
                <q-item clickable v-close-popup>
                  <q-item-section>HK 繁体中文</q-item-section>
                </q-item>
              </q-list>
            </q-menu>
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
    <q-drawer
      side="right"
      v-model="rightDrawerOpen"
      :width="280"
      @before-show="rightShow"
      @before-hide="rightHide"
      behavior="mobile"
      :breakpoint="500"
      content-class="bg-grey-3"
    >
      <q-scroll-area class="fit">
        <div class="q-pa-sm">
          <q-list padding>
            <q-item>
              <q-item-section>
                <q-item-label>Page style setting</q-item-label>
                <q-item-label caption>
                  <div class="q-mt-sm q-gutter-x-md" style="height: 50px">
                    <div
                      class="inline-block shadow-2"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    ></div>
                    <div
                      class="inline-block shadow-2"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    >
                      <div
                        class="inline-block"
                        style="
                          width: 15px;
                          height: 100%;
                          background-color: #1d1d1d;
                          border-top-left-radius: 5px;
                          border-bottom-left-radius: 5px;
                        "
                      ></div>
                    </div>
                    <div
                      class="inline-block shadow-2"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: #1d1d1d;
                        border-radius: 5px;
                      "
                    ></div>
                  </div>
                </q-item-label>
              </q-item-section>
            </q-item>
            <q-item>
              <q-item-section>
                <q-item-label>Theme Color</q-item-label>
                <q-item-label class="q-gutter-sm">
                  <q-btn
                    style="background-color: rgb(24, 144, 255)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(245, 34, 45)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(250, 84, 28)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(250, 173, 20)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(19, 194, 194)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(82, 196, 26)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(47, 84, 235)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(114, 46, 209)"
                    text-color="white"
                    icon="done"
                    dense
                    size="xs"
                  />
                </q-item-label>
              </q-item-section>
            </q-item>
            <q-separator spaced />
            <q-item-label header>General</q-item-label>

            <q-item tag="label" v-ripple>
              <q-item-section side top>
                <q-checkbox v-model="check1" />
              </q-item-section>

              <q-item-section>
                <q-item-label>Notifications</q-item-label>
                <q-item-label caption>
                  Notify me about updates to apps or games that I downloaded
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item tag="label" v-ripple>
              <q-item-section side top>
                <q-checkbox v-model="check2" />
              </q-item-section>

              <q-item-section>
                <q-item-label>Sound</q-item-label>
                <q-item-label caption>
                  Auto-update apps at anytime. Data charges may apply
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item tag="label" v-ripple>
              <q-item-section side top>
                <q-checkbox v-model="check3" />
              </q-item-section>

              <q-item-section>
                <q-item-label>Auto-add widgets</q-item-label>
                <q-item-label caption>
                  Automatically add home screen widgets
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-separator spaced />
            <q-item-label header>Notifications</q-item-label>

            <q-item tag="label" v-ripple>
              <q-item-section>
                <q-item-label>Battery too low</q-item-label>
              </q-item-section>
              <q-item-section side>
                <q-toggle color="blue" v-model="notif1" val="battery" />
              </q-item-section>
            </q-item>

            <q-item tag="label" v-ripple>
              <q-item-section>
                <q-item-label>Friend request</q-item-label>
                <q-item-label caption>Allow notification</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="green" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>

            <q-item tag="label" v-ripple>
              <q-item-section>
                <q-item-label>Picture uploaded</q-item-label>
                <q-item-label caption
                  >Allow notification when uploading images</q-item-label
                >
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="red" v-model="notif3" val="picture" />
              </q-item-section>
            </q-item>

            <q-separator spaced />
            <q-item-label header>Other settings</q-item-label>

            <q-item>
              <q-item-section side>
                <q-icon color="teal" name="volume_down" />
              </q-item-section>
              <q-item-section>
                <q-slider
                  v-model="volume"
                  :min="0"
                  :max="10"
                  label
                  color="teal"
                />
              </q-item-section>
              <q-item-section side>
                <q-icon color="teal" name="volume_up" />
              </q-item-section>
            </q-item>

            <q-item>
              <q-item-section side>
                <q-icon color="deep-orange" name="brightness_medium" />
              </q-item-section>
              <q-item-section>
                <q-slider
                  v-model="brightness"
                  :min="0"
                  :max="10"
                  label
                  color="deep-orange"
                />
              </q-item-section>
            </q-item>

            <q-item>
              <q-item-section side>
                <q-icon color="primary" name="mic" />
              </q-item-section>
              <q-item-section>
                <q-slider v-model="mic" :min="0" :max="50" label />
              </q-item-section>
            </q-item>
          </q-list>
        </div>
      </q-scroll-area>
    </q-drawer>
    <q-page-container>
      <slot name="page">hello world</slot>
      <q-page padding>
        <router-view />
        <q-page-sticky
          position="top-right"
          style="z-index: 3000"
          :offset="rightOffset"
        >
          <q-fab
            @show="1"
            v-model="rightDrawerSetting"
            icon="settings"
            color="primary"
            square
            @click="rightDrawerOpen = !rightDrawerOpen"
          />
        </q-page-sticky>
        <q-page-scroller
          position="bottom-right"
          :scroll-offset="150"
          :offset="[5, 68]"
        >
          <q-btn fab icon="keyboard_arrow_up" color="primary" glossy />
        </q-page-scroller>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from 'components/EssentialLink.vue'

export default {
  name: 'GoogleNewsLayout',
  props: {},
  data() {
    return {
      leftDrawerOpen: false,
      rightDrawerOpen: false,
      rightDrawerSetting: false,
      rightOffset: [5, 88],
      search: '',
      showAdvanced: false,
      showDateOptions: false,
      exactPhrase: '',
      hasWords: '',
      excludeWords: '',
      byWebsite: '',
      gtSm: this.$q.screen.gt.sm,
      byDate: 'Any time',
      check1: true,
      check2: false,
      check3: false,

      notif1: true,
      notif2: true,
      notif3: false,

      volume: 6,
      brightness: 3,
      mic: 8
    }
  },
  components: { EssentialLink },
  methods: {
    onClear() {
      this.exactPhrase = ''
      this.hasWords = ''
      this.excludeWords = ''
      this.byWebsite = ''
      this.byDate = 'Any time'
    },
    changeDate(option) {
      this.byDate = option
      this.showDateOptions = false
    },
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
    rightHide() {
      this.rightOffset = [5, 88]
      this.rightDrawerSetting = false
    },
    rightShow() {
      this.rightOffset = [280, 88]
    },
    onResize(size) {
      // 监听容器大小变化
      console.log('----------' + JSON.stringify(size))
      // if (this.$q.screen.gt.xs) {
      //   this.rightOffset = [5, 88]
      // }
    },
    onResizeForRight(size) {
      console.log('-----onResizeForRight-----' + JSON.stringify(size))
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
  watch: {
    gtSm: function () {
      this.rightHide()
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
