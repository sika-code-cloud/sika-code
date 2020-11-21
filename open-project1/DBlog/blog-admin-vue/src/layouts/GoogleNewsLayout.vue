<template>
  <q-layout view="hHh LpR lfr" class="bg-grey-1" style="font-family: 微软雅黑">
    <q-resize-observer @resize="onResize" />
    <q-header class="bg-white title-grey-8" height-hint="64">
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
          <q-img src="~assets/sika-logo-1.png" style="width: 100px" />
        </q-btn>
        <q-space />
        <q-input
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
            @click="showMessage = true"
          >
            <q-badge
              color="negative"
              style="padding: 2px 4px"
              title-color="white"
              floating
              v-if="totalInformCount > 0"
              >{{ totalInformCount }}</q-badge
            >
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
                  <q-item clickable v-close-popup>
                    <q-item-section
                      avatar
                      class="q-mr-sm q-pa-none"
                      style="min-width: 0"
                    >
                      <q-icon name="person" size="xs" />
                    </q-item-section>
                    <q-item-section>个人中心</q-item-section>
                  </q-item>
                  <q-item clickable v-close-popup>
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
                <q-item clickable v-close-popup>
                  <q-item-section
                    avatar
                    class="q-mr-sm q-pa-none"
                    style="min-width: 20px"
                  >
                    <q-img
                      src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604953153845&di=75a450709d07f093810061e3e8ae0a45&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F7af40ad162d9f2d3faac68ffa9ec8a136327cc65.jpg"
                      :ratio="16 / 10"
                    />
                  </q-item-section>
                  <q-item-section>Us English</q-item-section>
                </q-item>
                <q-item clickable v-close-popup>
                  <q-item-section
                    avatar
                    class="q-mr-sm q-pa-none"
                    style="min-width: 20px"
                  >
                    <q-img
                      src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604954286966&di=ac7db27213c991c1982adad09f02abbf&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbaike%2Fs%3D220%2Fsign%3Db6ae7334978fa0ec7bc7630f1696594a%2Fb7003af33a87e950e154426b16385343fbf2b472.jpg"
                      :ratio="16 / 10"
                    />
                  </q-item-section>
                  <q-item-section>En English</q-item-section>
                </q-item>
                <q-item clickable v-close-popup>
                  <q-item-section
                    avatar
                    class="q-mr-sm q-pa-none"
                    style="min-width: 20px"
                  >
                    <q-img
                      src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101918221,2000267634&fm=26&gp=0.jpg"
                      :ratio="16 / 10"
                    />
                  </q-item-section>
                  <q-item-section>CN 简体中文</q-item-section>
                </q-item>
                <q-item clickable v-close-popup>
                  <q-item-section
                    avatar
                    class="q-mr-sm q-pa-none"
                    style="min-width: 20px"
                  >
                    <q-img
                      src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604953546889&di=4299987e8e6144411da56b8885a4ff73&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190331%2F1efebed7b2d948c2b3bbc3855651c120.jpeg"
                      :ratio="16 / 10"
                    />
                  </q-item-section>
                  <q-item-section>HK 繁体中文</q-item-section>
                </q-item>
              </q-list>
            </q-menu>
          </q-btn>
        </div>
        <div>
          <q-menu
            class="row"
            :offset="[0, 36]"
            transition-show="jump-down"
            transition-hide="jump-up"
            content-style="width: 400px"
            max-height="800px"
            v-model="showMessage"
          >
            <div class="q-gutter-y-md col">
              <q-card class="full-width">
                <q-tabs
                  v-model="tab"
                  class="text-grey"
                  active-color="primary"
                  indicator-color="primary"
                  align="justify"
                  narrow-indicator
                >
                  <q-tab name="mails" v-if="informCount > 0">
                    通知({{ informCount }})
                  </q-tab>
                  <q-tab name="mails" v-else>通知</q-tab>
                  <q-tab name="alarms" v-if="notifiesCount > 0">
                    消息({{ notifiesCount }})
                  </q-tab>
                  <q-tab name="alarms" v-else>消息</q-tab>
                  <q-tab name="movies" v-if="waitDealCount > 0">
                    待办({{ waitDealCount }})
                  </q-tab>
                  <q-tab name="movies" v-else>待办</q-tab>
                </q-tabs>
                <q-separator />
                <q-tab-panels v-model="tab" animated>
                  <q-tab-panel name="mails" class="q-pa-none">
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
                        <q-separator inset="item" />
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
                  <q-tab-panel name="alarms" class="q-pa-none">
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
                        <q-separator inset="item" />
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
                  <q-tab-panel name="movies" class="q-pa-none">
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
                        <q-separator inset="item" />
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
    <q-footer class="text-blue-grey-4" style="background-color: #f0f2f5">
      <div class="q-my-lg">
        <div class="text-center q-mb-sm">
          <span class="inline-block">Sika Design Pro</span>
          <q-icon name="ti-github  q-mx-md"></q-icon>
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
      @hide="hide"
      @show="show"
    >
      <q-scroll-area
        style="height: calc(100% - 50px); margin-top: 50px"
        class="fit"
        :thumb-style="thumbStyle"
        :content-style="contentStyle"
        :content-active-style="contentActiveStyle"
      >
        <q-list class="rounded-borders text-black">
          <menu-tree :data="menuData" />
        </q-list>
        <EssentialLink />
      </q-scroll-area>
      <div class="absolute-top" style="height: 50px">
        <div class="bg-transparent q-px-sm q-gutter-x-sm">
          <q-avatar class="q-mb-sm">
            <q-img
              style="width: 40px"
              :ratio="10 / 10"
              src="~assets/sika-head.png"
            />
          </q-avatar>
          <div class="text-body1 inline-block text-primary">
            <strong>Sika Design Pro</strong>
          </div>
        </div>
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
            <q-item>
              <q-item-section>
                <q-item-label>主题色</q-item-label>
                <q-item-label class="q-gutter-sm">
                  <q-btn
                    style="background-color: rgb(24, 144, 255)"
                    text-color="white"
                    flat
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(245, 34, 45)"
                    text-color="white"
                    flat
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(250, 84, 28)"
                    text-color="white"
                    flat
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(250, 173, 20)"
                    text-color="white"
                    icon="done"
                    flat
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(19, 194, 194)"
                    text-color="white"
                    flat
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(82, 196, 26)"
                    text-color="white"
                    flat
                    icon="done"
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(47, 84, 235)"
                    text-color="white"
                    icon="done"
                    flat
                    dense
                    size="xs"
                  />
                  <q-btn
                    style="background-color: rgb(114, 46, 209)"
                    text-color="white"
                    flat
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
    <q-page-container class="" style="background-color: #f0f2f5">
      <slot name="page">hello world</slot>
      <slot name="page-title"></slot>
      <q-page class="q-pa-md">
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
          <q-btn
            unelevated
            padding="8px"
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
          <q-btn fab-mini icon="keyboard_arrow_up" color="primary" glossy />
        </q-page-scroller>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from 'components/EssentialLink.vue'
import MenuTree from 'components/tree/MenuTree'

const myData = [
  {
    name: '仪表盘',
    icon: 'dashboard',
    group: '仪表盘',
    children: [
      {
        name: '分析页',
        group: '仪表盘',
        to: '/dashboard/analysis'
      },
      {
        name: '监控页',
        group: '仪表盘',
        to: '/dashboard/monitor'
      },
      {
        name: '工作台',
        group: '仪表盘',
        to: '/dashboard/workplace'
      }
    ]
  },
  {
    name: '表单页',
    icon: 'edit_road',
    group: '表单页',
    children: [
      {
        name: '基础表单',
        group: '表单页',
        to: '/form/basic-form'
      },
      {
        name: '分步表单',
        group: '表单页',
        to: '/form/step-form'
      },
      {
        name: '高级表单',
        group: '表单页',
        to: '/form/advanced-form'
      }
    ]
  },
  {
    name: '列表页',
    icon: 'table_view',
    group: '列表页',
    children: [
      {
        name: '搜索列表',
        group: '列表页-搜索列表',
        children: [
          {
            name: '搜索列表（文章）',
            group: '列表页-搜索列表',
            to: '/list/search/articles'
          },
          {
            name: '搜索列表（项目）',
            group: '列表页-搜索列表',
            to: '/list/search/projects'
          },
          {
            name: '搜索列表（应用）',
            group: '列表页-搜索列表',
            to: '/list/search/applications'
          }
        ]
      },
      {
        name: '查询表格',
        group: '列表页',
        to: '/list/table-list'
      },
      {
        name: '标准列表',
        group: '列表页',
        to: '/list/basic-list'
      },
      {
        name: '卡片列表',
        group: '列表页',
        to: '/list/card-list'
      }
    ]
  },
  {
    name: '详情页',
    icon: 'library_books',
    group: '详情页',
    children: [
      {
        name: '基础详情页',
        group: '详情页',
        to: '/profile/basic'
      },
      {
        name: '高级详情页',
        group: '详情页',
        to: '/profile/advanced'
      }
    ]
  },
  {
    name: '结果页',
    icon: 'check_circle_outline',
    group: '结果页',
    children: [
      {
        name: '成功页',
        group: '结果页',
        to: '/result/success'
      },
      {
        name: '失败页',
        group: '结果页',
        to: '/result/fail'
      }
    ]
  },
  {
    name: '异常页',
    icon: 'error_outline',
    group: '异常页',
    children: [
      {
        name: '403',
        group: '异常页',
        to: '/exception/403'
      },
      {
        name: '404',
        group: '异常页',
        to: '/exception/404'
      },
      {
        name: '500',
        group: '异常页',
        to: '/exception/500'
      }
    ]
  },
  {
    name: '个人页',
    icon: 'perm_identity',
    group: '个人页',
    children: [
      {
        name: '个人中心',
        group: '个人页',
        to: '/account/center'
      },
      {
        name: '个人设置',
        group: '个人页',
        to: '/account/settings'
      }
    ]
  },
  {
    name: '编辑器',
    icon: 'text_fields',
    group: '编辑器',
    children: [
      {
        name: '自定义编辑器',
        group: '编辑器',
        to: '/editor/customer'
      },
      {
        name: 'Markdown编辑器',
        group: '编辑器',
        to: '/editor/markdown'
      }
    ]
  },
  {
    name: '1编辑器',
    icon: 'text_fields',
    group: '1编辑器',
    top: true
  }
]
const informs = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  }
]
const notifies = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断222',
    desc: '3年前',
    color: 'blue',
    textColor: 'white',
    disable: false
  }
]
const waitDeals = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  }
]

export default {
  name: 'GoogleNewsLayout',
  props: {},
  data() {
    return {
      informs,
      notifies,
      waitDeals,
      showMessage: false,
      tab: 'mails',
      menuData: myData,
      link: 'inbox',
      leftDrawerOpen: false,
      leftMini: true,
      leftBehavior: 'default',
      rightDrawerOpen: false,
      rightDrawerSetting: false,
      rightOffset: [5, 168],
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
        width: '0px',
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
  components: {
    EssentialLink,
    MenuTree
  },
  methods: {
    look(type, index) {
      if (type === 'inform') {
        informs[index].disable = true
      } else if (type === 'notify') {
        notifies[index].disable = true
      } else {
        waitDeals[index].disable = true
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
    onClear() {
      this.exactPhrase = ''
      this.hasWords = ''
      this.excludeWords = ''
      this.byWebsite = ''
      this.byDate = 'Any time'
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
      this.rightOffset = [5, 5]
      this.rightDrawerSetting = false
    },
    rightShow() {
      this.rightOffset = [280, 5]
    },
    menuHeadStyle(startWith) {
      if (this.link.startsWith(startWith)) {
        return { color: '#1890ff' }
      }
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
    },
    informCount: function () {
      let count = 0
      for (let i = 0; i < this.informs.length; ++i) {
        if (informs[i].disable === false) {
          count++
        }
      }
      return count
    },
    notifiesCount: function () {
      let count = 0
      for (let i = 0; i < this.notifies.length; ++i) {
        if (notifies[i].disable === false) {
          count++
        }
      }
      return count
    },
    waitDealCount: function () {
      let count = 0
      for (let i = 0; i < this.waitDeals.length; ++i) {
        if (waitDeals[i].disable === false) {
          count++
        }
      }
      return count
    },
    totalInformCount: function () {
      return this.informCount + this.notifiesCount + this.waitDealCount
    }
  },
  mounted: function () {
    this.leftDrawerOpen = this.getLeftDrawOpen()
  }
}
</script>

<style lang="sass">
.my-menu-link
  color: #1890ff
  background: #e6f7ff

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
