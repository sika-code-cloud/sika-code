<template>
  <q-layout :view="view" class="bg-grey-2" style="font-family: 微软雅黑">
    <q-resize-observer :debounce="300" @resize="onResize" />
    <q-header class="bg-white title-grey-8 shadow-1 sc-design" height-hint="64">
      <q-toolbar class="GNL__toolbar">
        <q-btn
          flat
          dense
          round
          @click="onClick"
          aria-label="Menu"
          icon="format_indent_increase"
          color="grey-8"
        />
        <q-btn dense flat size="sm" class="q-mr-xs" v-show="$q.screen.gt.xs">
          <q-img src="imgs/logo/sika-logo-1.png" style="width: 100px" />
        </q-btn>
        <q-space />
        <q-input
          square
          class="GNL__toolbar-input"
          outlined
          dense
          v-model="search"
          color="bg-grey-7"
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
        </q-input>
        <q-space />
        <div class="q-gutter-xs q-ml-sm row items-center no-wrap">
          <q-btn round dense flat size="sm" color="grey-7" icon="help_outline">
            <q-tooltip>帮助文档</q-tooltip>
          </q-btn>
          <q-btn
            round
            flat
            size="sm"
            color="grey-8"
            icon="notifications_none"
            @click="openMessage"
          >
            <q-badge
              color="negative"
              style="padding: 2px 4px"
              title-color="white"
              floating
              v-if="totalInformCount > 0"
            >{{ totalInformCount }}
            </q-badge>
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
                :offset="[0, 26]"
                transition-show="jump-down"
                transition-hide="jump-up"
                :content-class="menuContentClass"
              >
                <q-list dense>
                  <q-item clickable v-close-popup to="/account/center">
                    <q-item-section
                      avatar
                      class="q-mr-sm q-pa-none"
                      style="min-width: 0"
                    >
                      <q-icon name="person" size="xs" />
                    </q-item-section>
                    <q-item-section>个人中心</q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup to="/account/settings">
                    <q-item-section
                      avatar
                      class="q-mr-sm q-pa-none"
                      style="min-width: 0"
                    >
                      <q-icon name="settings" size="xs" />
                    </q-item-section>
                    <q-item-section>个人设置</q-item-section>
                  </q-item>
                  <q-separator color="grey-4" />
                  <q-item clickable v-close-popup to="/user/login">
                    <q-item-section
                      avatar
                      class="q-mr-sm q-pa-none"
                      style="min-width: 0"
                    >
                      <q-icon name="logout" size="xs" />
                    </q-item-section>
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
              :offset="[0, 24]"
            >
              <q-list style="min-width: 120px" dense>
                <q-item
                  clickable
                  v-close-popup
                  v-for="(item, index) in languageDatas"
                  :key="index"
                >
                  <q-item-section
                    avatar
                    class="q-mr-sm q-pa-none"
                    style="min-width: 20px"
                  >
                    <q-img :src="item.nationalFlag" :ratio="16 / 10" />
                  </q-item-section>
                  <q-item-section>{{ item.label }}</q-item-section>
                </q-item>
              </q-list>
            </q-menu>
          </q-btn>
        </div>
        <div>
          <q-menu
            :offset="[0, 36]"
            transition-show="jump-down"
            transition-hide="jump-up"
            content-style="width: 400px;"
            max-height="800px"
            v-model="showMessage"
          >
            <div>
              <q-card class="full-width">
                <q-tabs
                  v-model="messageTab"
                  class="text-grey"
                  active-color="primary"
                  indicator-color="primary"
                  align="justify"
                  narrow-indicator
                >
                  <q-tab name="informs">
                    <span v-if="informCount > 0">
                      通知({{ informCount }})
                    </span>
                    <span v-else>通知</span>
                  </q-tab>
                  <q-tab name="notifies">
                    <span v-if="notifiesCount > 0">
                      消息({{ notifiesCount }})
                    </span>
                    <span v-else>消息</span>
                  </q-tab>
                  <q-tab name="waitDeals">
                    <span v-if="waitDealCount > 0">
                      待办({{ waitDealCount }})
                    </span>
                    <span v-else>待办</span>
                  </q-tab>
                </q-tabs>
                <q-separator />
                <q-tab-panels v-model="messageTab" animated>
                  <q-tab-panel name="informs" class="q-pa-none">
                    <q-list>
                      <div
                        v-for="(inform, index) in informs"
                        v-bind:key="index"
                        @click="look('inform', index)"
                      >
                        <q-item
                          class="cursor-pointer q-ma-xs"
                          :disable="inform.disable"
                        >
                          <q-item-section avatar>
                            <q-avatar
                              :color="inform.color"
                              :text-color="inform.textColor"
                              :icon="inform.icon"
                            />
                          </q-item-section>
                          <q-item-section class="q-gutter-xs">
                            <q-item-label :lines="1">
                              {{ inform.title }}
                            </q-item-label>
                            <q-item-label caption>
                              {{ inform.desc }}
                            </q-item-label>
                          </q-item-section>
                        </q-item>
                        <q-separator
                          v-if="index < informs.length - 1"
                          inset="item"
                        />
                      </div>
                      <q-separator />
                      <q-item class="row q-pa-none text-center cursor-pointer">
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="清空通知"
                            flat
                            :ripple="{ color: 'info' }"
                            class="q-ma-none full-width full-height no-border-radius"
                            @click="clearMessage('informs')"
                          ></q-btn>
                        </q-item-section>
                        <q-separator vertical />
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="查看更多"
                            flat
                            :ripple="{ color: 'info' }"
                            class="q-ma-none full-width full-height no-border-radius"
                            @click="lookMore('查看通知')"
                          ></q-btn>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-tab-panel>
                  <q-tab-panel name="notifies" class="q-pa-none">
                    <q-list>
                      <div
                        v-for="(notify, index) in notifies"
                        v-bind:key="index"
                        @click="look('notify', index)"
                      >
                        <q-item
                          class="cursor-pointer q-ma-xs"
                          :disable="notify.disable"
                        >
                          <q-item-section avatar>
                            <q-avatar
                              :color="notify.color"
                              :text-color="notify.textColor"
                              :icon="notify.icon"
                            />
                          </q-item-section>
                          <q-item-section class="q-gutter-xs">
                            <q-item-label :lines="1">
                              {{ notify.title }}
                            </q-item-label>
                            <q-item-label caption>
                              {{ notify.desc }}
                            </q-item-label>
                          </q-item-section>
                        </q-item>
                        <q-separator
                          v-if="index < informs.length - 1"
                          inset="item"
                        />
                      </div>
                      <q-separator />
                      <q-item class="row q-pa-none text-center cursor-pointer">
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="清空通知"
                            flat
                            :ripple="{ color: 'info' }"
                            class="q-ma-none full-width full-height no-border-radius"
                            @click="clearMessage('notifies')"
                          ></q-btn>
                        </q-item-section>
                        <q-separator vertical />
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="查看更多"
                            flat
                            :ripple="{ color: 'info' }"
                            class="q-ma-none full-width full-height no-border-radius"
                            @click="lookMore('查看通知')"
                          ></q-btn>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-tab-panel>
                  <q-tab-panel name="waitDeals" class="q-pa-none">
                    <q-list>
                      <div
                        v-for="(waitDeal, index) in waitDeals"
                        v-bind:key="index"
                        @click="look('waitDeal', index)"
                      >
                        <q-item
                          class="cursor-pointer q-ma-xs"
                          :disable="waitDeal.disable"
                        >
                          <q-item-section avatar>
                            <q-avatar
                              :color="waitDeal.color"
                              :text-color="waitDeal.textColor"
                              :icon="waitDeal.icon"
                            />
                          </q-item-section>
                          <q-item-section class="q-gutter-xs">
                            <q-item-label :lines="1">
                              {{ waitDeal.title }}
                            </q-item-label>
                            <q-item-label caption>
                              {{ waitDeal.desc }}
                            </q-item-label>
                          </q-item-section>
                        </q-item>
                        <q-separator
                          v-if="index < informs.length - 1"
                          inset="item"
                        />
                      </div>
                      <q-separator />
                      <q-item class="row q-pa-none text-center cursor-pointer">
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="清空通知"
                            flat
                            :ripple="{ color: 'info' }"
                            @click="clearMessage('waitDeals')"
                            class="q-ma-none full-width full-height no-border-radius"
                          ></q-btn>
                        </q-item-section>
                        <q-separator vertical />
                        <q-item-section class="col q-pa-none q-ma-none">
                          <q-btn
                            label="查看更多"
                            flat
                            :ripple="{ color: 'info' }"
                            class="q-ma-none full-width full-height no-border-radius"
                            @click="lookMore('查看通知')"
                          ></q-btn>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-tab-panel>
                </q-tab-panels>
              </q-card>
            </div>
          </q-menu>
        </div>
      </q-toolbar>
    </q-header>
    <q-footer class="text-blue-grey-4 bg-grey-2">
      <div class="q-my-lg">
        <div class="text-center q-mb-sm">
          <span class="inline-block">Sika Design Pro</span>
          <q-icon name="ti-github q-mx-md"></q-icon>
          <span class="inline-block">Sika Design</span>
        </div>
        <div class="text-center">Copyright@2019 Sika 体验技术部出品</div>
      </div>
    </q-footer>
    <q-drawer
      v-model="leftDrawerOpen"
      bordered
      content-class="bg-white"
      :width="240"
    >
      <div class="absolute-top bg-white q-mt-sm">
        <div class="bg-transparent q-px-sm q-gutter-x-sm">
          <q-avatar class="q-mb-sm">
            <q-img style="width: 40px" src="~assets/sika-head.png" />
          </q-avatar>
          <div class="text-body1 inline-block text-primary">
            <strong>Sika Design Pro</strong>
          </div>
        </div>
      </div>
      <div style="height: calc(100% - 55px); margin-top: 55px">
        <q-scroll-area
          class="fit"
          :thumb-style="scrollStyleData.thumbStyle"
          :content-style="scrollStyleData.contentStyle"
          :content-active-style="scrollStyleData.contentActiveStyle"
        >
          <q-list class="rounded-borders text-black">
            <menu-tree :data="menuData" />
          </q-list>
        </q-scroll-area>
      </div>
    </q-drawer>
    <q-drawer
      side="right"
      v-model="rightDrawerOpen"
      :width="280"
      @before-show="rightShow"
      @before-hide="rightHide"
      behavior="mobile"
      content-class="bg-white"
    >
      <q-scroll-area
        class="fit"
        :thumb-style="scrollStyleData.thumbStyle"
        :content-style="scrollStyleData.contentStyle"
        :content-active-style="scrollStyleData.contentActiveStyle"
      >
        <div class="q-pa-sm">
          <q-list padding>
            <q-item v-if="false">
              <q-item-section>
                <q-item-label>整体风格设置</q-item-label>
                <q-item-label>
                  <div class="q-mt-sm q-gutter-x-md row" style="height: 50px">
                    <div
                      class="col-auto shadow-1 cursor-pointer"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    >
                      <q-icon
                        size="sm"
                        name="done"
                        color="primary"
                        style="margin-top: 15px; margin-left: 15px"
                      />
                    </div>
                    <div
                      class="col-auto shadow-1 cursor-pointer"
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
                      class="col-auto shadow-1 cursor-pointer"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: #1d1d1d;
                        border-radius: 5px;
                      "
                      @click="changeOverallStyle('dark')"
                    ></div>
                  </div>
                </q-item-label>
              </q-item-section>
            </q-item>
            <q-item>
              <q-item-section>
                <q-item-label>主题色</q-item-label>
                <q-item-label class="q-gutter-sm">
                  <span
                    style="width: 20px; height: 20px"
                    :key="index"
                    v-for="(item, index) in styleSettingsData.themeColorSetting"
                    @click="changeThemeColor(index)"
                  >
                    <q-btn
                      :style="item.style"
                      text-color="white"
                      flat
                      icon="done"
                      dense
                      v-if="item.checked"
                      style="width: 18px; height: 18px"
                      size="xs"
                    />
                    <q-btn
                      :style="item.style"
                      text-color="white"
                      flat
                      dense
                      v-else
                      style="width: 18px; height: 18px"
                      size="xs"
                    />
                  </span>
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-separator spaced="12px" />

            <q-item v-if="false">
              <q-item-section>
                <q-item-label>导航模式</q-item-label>
                <q-item-label caption>
                  <div class="q-mt-sm q-gutter-x-md" style="height: 50px">
                    <div
                      class="inline-block shadow-1 cursor-pointer"
                      style="
                        width: 50px;
                        height: 100%;
                        background-color: white;
                        border-radius: 5px;
                      "
                    ></div>
                    <div
                      class="inline-block shadow-1 cursor-pointer"
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
                      class="inline-block shadow-1 cursor-pointer"
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
            <q-item tag="div" class="q-mt-md">
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
                <q-toggle color="primary" v-model="styleSettingsData.fixed.fixedHead" val="friend"
                          @input="changeFixed" />
              </q-item-section>
            </q-item>

            <q-item tag="label">
              <q-item-section>
                <q-item-label>固定Footer</q-item-label>
              </q-item-section>
              <q-item-section side top>
                <q-toggle color="primary" v-model="styleSettingsData.fixed.fixedFooter" val="friend"
                          @input="changeFixed" />
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
                class="row q-pa-md cursor-pointer"
                style="
                  border: 1px solid #ffe58f;
                  background-color: #fffbe6;
                  border-radius: 2px;
                "
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
      <q-page>
        <transition mode="out-in">
          <router-view />
        </transition>
        <q-page-sticky
          position="top-right"
          style="z-index: 3000"
          :offset="rightOffset"
        >
          <q-btn
            ref="morphedElement2"
            unelevated
            padding="8px"
            @show="1"
            v-model="rightDrawerSetting"
            icon="settings"
            color="primary"
            square
            @click="triggerRightDrawer"
          />
        </q-page-sticky>
        <q-page-scroller
          position="bottom-right"
          :scroll-offset="150"
          :offset="[5, 68]"
        >
          <q-btn fab-mini icon="keyboard_arrow_up" color="primary" glossy />
        </q-page-scroller>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import MenuTree from 'components/tree/MenuTree'
import LAYOUT_DATA from '@/mock/data/layout/layoutData'
import { morph, colors } from 'quasar'

export default {
  name: 'StandardLayout',
  props: {},
  data() {
    return {
      informs: LAYOUT_DATA.informsData,
      notifies: LAYOUT_DATA.notifyDatas,
      menuData: LAYOUT_DATA.routeDatas,
      waitDeals: LAYOUT_DATA.waitDealDatas,
      languageDatas: LAYOUT_DATA.languageDatas,
      scrollStyleData: LAYOUT_DATA.scrollStyleData,
      styleSettingsData: LAYOUT_DATA.styleSettingsData,
      showMessage: false,
      messageTab: 'informs',
      leftDrawerOpen: false,
      rightDrawerOpen: false,
      rightDrawerSetting: false,
      rightOffset: LAYOUT_DATA.rightOffset.rightOffsetInit,
      rightSelect: '流式',
      search: '',
      gtSm: this.$q.screen.gt.sm,
      notif2: true
    }
  },
  components: {
    MenuTree
  },
  methods: {
    changeFixed() {
      const fixedData = this.styleSettingsData.fixed
      if (fixedData.fixedHead) {
        fixedData.viewHead = this.replaceView(fixedData.viewHead, 'H')
      } else {
        fixedData.viewHead = this.replaceView(fixedData.viewHead, 'h')
      }
      if (this.styleSettingsData.fixed.fixedFooter) {
        fixedData.viewFoot = this.replaceView(fixedData.viewFoot, 'F')
      } else {
        fixedData.viewFoot = this.replaceView(fixedData.viewFoot, 'f')
      }
    },
    replaceView(sourceStr, str) {
      return sourceStr.substr(0, 1) + str + sourceStr.substr(2)
    },
    changeThemeColor(index) {
      for (
        let i = 0;
        i < this.styleSettingsData.themeColorSetting.length;
        ++i
      ) {
        this.styleSettingsData.themeColorSetting[i].checked = false
      }
      colors.setBrand(
        'primary',
        this.styleSettingsData.themeColorSetting[index].color
      )
      this.styleSettingsData.themeColorSetting[index].checked = true
    },
    look(type, index) {
      if (type === 'inform') {
        this.informs[index].disable = true
      } else if (type === 'notify') {
        this.notifies[index].disable = true
      } else {
        this.waitDeals[index].disable = true
      }
    },
    lookMore(message) {
      this.$q.notify({
        progress: true,
        color: 'primary',
        group: false,
        icon: 'check_circle',
        position: 'top',
        timeout: 2000,
        message: message
      })
    },
    openMessage() {
      this.showMessage = !this.showMessage
    },
    clearMessage(items) {
      if (items === 'informs') {
        this.disable(this.informs)
      } else if (items === 'notifies') {
        this.disable(this.notifies)
      } else {
        this.disable(this.waitDeals)
      }
    },
    disable(items) {
      for (let i = 0; i < items.length; ++i) {
        items[i].disable = true
      }
    },
    onClick() {
      this.leftDrawerOpen = !this.leftDrawerOpen
    },
    rightHide() {
      this.rightOffset = LAYOUT_DATA.rightOffset.rightOffsetInit
      this.rightDrawerSetting = false
    },
    rightShow() {
      this.rightOffset = LAYOUT_DATA.rightOffset.rightOffsetShow
    },
    onResize(size) {
      // 监听容器大小变化
      console.log('----------' + JSON.stringify(size))
    },
    triggerRightDrawer() {
      this.rightDrawerOpen = !this.rightDrawerOpen
      morph({
        from: this.$refs.morphedElement2.$el,
        duration: 30,
        tween: true,
        tweenFromOpacity: 0.8,
        tweenToOpacity: 0.4
      })
    }
  },
  watch: {
    gtSm: function() {
      this.rightHide()
    }
  },
  computed: {
    view: function() {
      return this.styleSettingsData.fixed.viewHead + ' ' +
        this.styleSettingsData.fixed.viewBody + ' ' +
        this.styleSettingsData.fixed.viewFoot
    },
    menuContentClass: function() {
      if (this.$q.screen.lt.sm) {
        return 'q-mx-xs'
      }
      return ''
    },
    informCount: function() {
      return LAYOUT_DATA.getAvailableCount(this.informs)
    },
    notifiesCount: function() {
      return LAYOUT_DATA.getAvailableCount(this.notifies)
    },
    waitDealCount: function() {
      return LAYOUT_DATA.getAvailableCount(this.waitDeals)
    },
    totalInformCount: function() {
      return this.informCount + this.notifiesCount + this.waitDealCount
    }
  },
  mounted: function() {
  }
}
</script>

<style lang="sass">
.v-enter
  opacity: 0
  transform: translate3d(0, -100px, 0)

.v-leave-to
  opacity: 0
  transform: translate3d(0, 100px, 0)

.v-enter-active, .v-leave-active
  transition: all 0.4s ease

.GNL
  &__toolbar
    height: 64px

  &__toolbar-input
    width: 55%
</style>
