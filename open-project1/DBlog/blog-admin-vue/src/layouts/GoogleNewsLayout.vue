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
                max-width="100vw"
                :content-class="menuContentClass"
                content-style="width:100%"
              >
                <q-list dense>
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
      <q-scroll-area
        class="fit"
        :thumb-style="thumbStyle"
        :content-style="contentStyle"
        :content-active-style="contentActiveStyle"
      >
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
      content-class="bg-white"
    >
      <q-scroll-area
        class="fit"
        :thumb-style="thumbStyle"
        :content-style="contentStyle"
        :content-active-style="contentActiveStyle"
      >
        <div class="q-pa-sm">
          <q-list padding>
            <q-item>
              <q-item-section>
                <q-item-label>整体风格设置</q-item-label>
                <q-item-label caption>
                  <div class="q-mt-sm q-gutter-x-md" style="height: 50px">
                    <div
                      class="inline-block shadow-2 cursor-pointer"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    ></div>
                    <div
                      class="inline-block shadow-2 cursor-pointer"
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
                      class="inline-block shadow-2 cursor-pointer"
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
                <q-item-label>主题色</q-item-label>
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

            <q-separator spaced="12px" />

            <q-item>
              <q-item-section>
                <q-item-label>导航模式</q-item-label>
                <q-item-label caption>
                  <div class="q-mt-sm q-gutter-x-md" style="height: 50px">
                    <div
                      class="inline-block shadow-2 cursor-pointer"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    ></div>
                    <div
                      class="inline-block shadow-2 cursor-pointer"
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
                      class="inline-block shadow-2 cursor-pointer"
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
            <q-item tag="label" class="q-mt-md">
              <q-item-section>
                <q-item-label>内容区域宽度</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-select
                  behavior="menu"
                  dense
                  color="primary"
                  outlined
                  v-model="rightSelect"
                  options-dense
                  :options="['流式']"
                >
                </q-select>
              </q-item-section>
            </q-item>
            <q-item tag="label">
              <q-item-section>
                <q-item-label>固定Header</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>

            <q-item tag="label">
              <q-item-section>
                <q-item-label>固定侧边菜单</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>

            <q-separator spaced="12px" />
            <q-item tag="label">
              <q-item-section>
                <q-item-label>内容区域</q-item-label>
              </q-item-section>
            </q-item>
            <q-item tag="label">
              <q-item-section>
                <q-item-label>顶栏</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>
            <q-item tag="label">
              <q-item-section>
                <q-item-label>页脚</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>
            <q-item tag="label">
              <q-item-section>
                <q-item-label>菜单</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>
            <q-item tag="label">
              <q-item-section>
                <q-item-label>菜单头</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>

            <q-separator spaced="“12px" />
            <q-item-label header>其他设置</q-item-label>

            <q-item tag="label">
              <q-item-section>
                <q-item-label>色弱模式</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="notif2" val="friend" />
              </q-item-section>
            </q-item>
            <q-separator spaced="“12px" />
            <q-item>
              <div
                class="row q-pa-md bg-blue-1 cursor-pointer"
                style="border: 1px solid yellowgreen"
              >
                <div class="col-1">
                  <q-icon name="alarm" />
                </div>
                <div class="col-11">
                  配置栏只在开发环境用于预览，生产环境不会展现，请拷贝后手动修改配置文件
                </div>
              </div>
            </q-item>
            <q-item tag="label">
              <q-btn
                label="拷贝设置"
                class="full-width"
                outline
                color="secondary"
                icon="lightbulb_outline"
              ></q-btn>
            </q-item>
          </q-list>
        </div>
      </q-scroll-area>
    </q-drawer>
    <q-page-container>
      <slot name="page">hello world</slot>
      <q-page padding>
        <router-view />
        <q-page-scroller
          class="fit"
          :thumb-style="thumbStyle"
          :content-style="contentStyle"
          :content-active-style="contentActiveStyle"
        >
        </q-page-scroller>
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
      leftMini: true,
      leftBehavior: 'default',
      rightDrawerOpen: false,
      rightDrawerSetting: false,
      rightOffset: [5, 88],
      rightSelect: '流式',
      search: '',
      showAdvanced: false,
      showDateOptions: false,
      exactPhrase: '',
      hasWords: '',
      excludeWords: '',
      byWebsite: '',
      gtSm: this.$q.screen.gt.sm,
      byDate: 'Any time',
      contentStyle: {},
      contentActiveStyle: {},
      thumbStyle: {
        right: '2px',
        borderRadius: '5px',
        backgroundColor: '#027be3',
        width: '5px',
        opacity: 0.75
      },
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
  computed: {
    menuContentClass: function () {
      if (this.$q.screen.lt.sm) {
        return 'q-mx-xs'
      }
      return ''
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
