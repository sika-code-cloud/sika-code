<template>
  <q-layout view="hHh LpR lfr" class="bg-grey-1" style="font-family: 微软雅黑">
    <q-resize-observer @resize="onResize" />
    <q-header class="bg-white title-grey-8" height-hint="64">
      <q-toolbar class="GNL__toolbar">
        <q-btn
          flat
          dense
          round
          v-show="$q.screen.lt.md"
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
        <q-list bordered padding class="rounded-borders text-primary">
          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="仪表盘"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>分析页</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>监控页</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>工作台</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="表单页"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>基础表单</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>分步表单</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>高级表单</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="列表页"
          >
            <q-expansion-item
              expand-separator
              :content-inset-level="0.5"
              icon="receipt"
              label="搜索列表"
            >
              <q-item
                clickable
                v-ripple
                :active="link === 'trash'"
                @click="link = 'trash'"
                active-class="my-menu-link"
              >
                <q-item-section avatar>
                  <q-icon name="delete" />
                </q-item-section>
                <q-item-section>搜索列表（文章）</q-item-section>
              </q-item>
              <q-item
                clickable
                v-ripple
                :active="link === 'trash'"
                @click="link = 'trash'"
                active-class="my-menu-link"
              >
                <q-item-section avatar>
                  <q-icon name="delete" />
                </q-item-section>
                <q-item-section>搜索列表（项目）</q-item-section>
              </q-item>
              <q-item
                clickable
                v-ripple
                :active="link === 'trash'"
                @click="link = 'trash'"
                active-class="my-menu-link"
              >
                <q-item-section avatar>
                  <q-icon name="delete" />
                </q-item-section>
                <q-item-section>搜索列表（应用）</q-item-section>
              </q-item>
            </q-expansion-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>查询列表</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>标准列表</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>卡片列表</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="结果页"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>成功页</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>失败页</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="异常页"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>403</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>404</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>500</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="个人页"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>个人中心</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>个人设置</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="编辑器"
          >
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>自定义编辑器</q-item-section>
            </q-item>
            <q-item
              clickable
              v-ripple
              :active="link === 'trash'"
              @click="link = 'trash'"
              active-class="my-menu-link"
            >
              <q-item-section avatar>
                <q-icon name="delete" />
              </q-item-section>
              <q-item-section>Markdown编辑器</q-item-section>
            </q-item>
          </q-expansion-item>

          <q-expansion-item
            :content-inset-level="0.5"
            expand-separator
            icon="mail"
            label="仪表盘"
          >
            <q-expansion-item
              expand-separator
              :content-inset-level="0.5"
              icon="receipt"
              label="Receipts"
            >
              <q-expansion-item label="Today" :content-inset-level="0.5">
                <q-item
                  clickable
                  v-ripple
                  :active="link === 'inbox'"
                  @click="link = 'inbox'"
                  active-class="my-menu-link"
                  to="/card"
                >
                  <q-item-section avatar>
                    <q-icon name="inbox" />
                  </q-item-section>

                  <q-item-section>Inbox</q-item-section>
                </q-item>

                <q-item
                  clickable
                  v-ripple
                  :active="link === 'outbox'"
                  @click="link = 'outbox'"
                  active-class="my-menu-link"
                >
                  <q-item-section avatar>
                    <q-icon name="send" />
                  </q-item-section>

                  <q-item-section>Outbox</q-item-section>
                </q-item>

                <q-item
                  clickable
                  v-ripple
                  :active="link === 'trash'"
                  @click="link = 'trash'"
                  active-class="my-menu-link"
                >
                  <q-item-section avatar>
                    <q-icon name="delete" />
                  </q-item-section>

                  <q-item-section>Trash</q-item-section>
                </q-item>
              </q-expansion-item>

              <q-expansion-item label="Yesterday" :content-inset-level="0.5">
              </q-expansion-item>
            </q-expansion-item>

            <q-expansion-item
              :content-inset-level="0.5"
              expand-separator
              icon="schedule"
              label="Postponed"
            >
              <q-item
                clickable
                v-ripple
                :active="link === 'settings'"
                @click="link = 'settings'"
                active-class="my-menu-link"
              >
                <q-item-section avatar>
                  <q-icon name="settings" />
                </q-item-section>

                <q-item-section>Settings</q-item-section>
              </q-item>

              <q-item
                clickable
                v-ripple
                :active="link === 'help'"
                @click="link = 'help'"
                active-class="my-menu-link"
              >
                <q-item-section avatar>
                  <q-icon name="help" />
                </q-item-section>

                <q-item-section>Help</q-item-section>
              </q-item>
            </q-expansion-item>
          </q-expansion-item>
        </q-list>
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
      link: 'inbox',
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
        width: '2px',
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
.my-menu-link
  color: white
  background: #F2C037
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
