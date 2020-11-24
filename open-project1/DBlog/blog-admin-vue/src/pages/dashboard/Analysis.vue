<template>
  <div class="sc-design">
    <div class="row q-px-sm" style="margin: -16px -16px 0 -16px">
      <div
        class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md"
        :class="{ 'q-pr-sm': $q.screen.gt.xs }"
      >
        <q-card flat class="no-border-radius text-grey-9">
          <q-card-section class="bg-white">
            <q-avatar
              color="green-1"
              size="sm"
              text-color="green"
              class="q-mr-sm"
              icon="visibility"
            ></q-avatar>
            <span> 访问量 </span>
            <span>
              <q-chip
                size="sm"
                square
                outline
                color="green"
                class="bg-green-1 float-right"
                label="日"
              ></q-chip>
            </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md">
            <div style="height: 70px">
              <q-item-label
                class="text-grey-9 q-mb-md"
                style="font-size: xx-large"
              >
                5,848
              </q-item-label>
              <q-item-label class="q-mb-md">
                <span class="q-mr-md">
                  日同比 12.5%
                  <q-icon size="xs" name="arrow_drop_up" color="red" />
                </span>
                <span
                  >周同比 5%
                  <q-icon size="xs" name="arrow_drop_down" color="info" />
                </span>
              </q-item-label>
            </div>
            <q-separator spaced="15px" />
            <q-item-label>
              <span>总访问量</span>
              <span class="float-right">280 万</span>
            </q-item-label>
          </q-card>
        </q-card>
      </div>
      <div
        class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md"
        :class="{ 'q-pr-sm': $q.screen.gt.xs }"
      >
        <q-card flat class="no-border-radius text-grey-9">
          <q-card-section class="bg-white">
            <q-avatar
              color="green-1"
              size="sm"
              text-color="green"
              class="q-mr-sm"
              icon="visibility"
            ></q-avatar>
            <span> 销售额 </span>
            <span>
              <q-chip
                size="sm"
                square
                outline
                color="blue"
                class="bg-blue-1 float-right"
                label="月"
              ></q-chip>
            </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md">
            <div style="height: 70px">
              <q-item-label
                class="text-grey-9 q-mb-md"
                style="font-size: xx-large"
              >
                ¥12,000
              </q-item-label>
              <q-item-label class="q-pb-sm">
                <q-linear-progress :value="0.7" rounded stripe size="8px" />
              </q-item-label>
            </div>
            <q-separator spaced="15px" />
            <q-item-label>
              <span>总销售额</span>
              <span class="float-right">68 万</span>
            </q-item-label>
          </q-card>
        </q-card>
      </div>
      <div
        class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md"
        :class="{ 'q-pr-sm': $q.screen.gt.xs }"
      >
        <q-card flat class="no-border-radius text-grey-9">
          <q-card-section class="bg-white">
            <q-avatar
              color="green-1"
              size="sm"
              text-color="green"
              class="q-mr-sm"
              icon="visibility"
            ></q-avatar>
            <span> 订单量 </span>
            <span>
              <q-chip
                size="sm"
                square
                outline
                color="red"
                class="bg-red-1 float-right"
                label="周"
              ></q-chip>
            </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md">
            <div style="height: 70px">
              <q-item-label
                class="text-grey-9 q-mb-md"
                style="font-size: xx-large"
              >
                1,680
              </q-item-label>
              <q-item-label class="q-mb-md">
                <span class="q-mr-md">
                  日同比 12.5%
                  <q-icon size="xs" name="arrow_drop_up" color="red" />
                </span>
                <span
                  >周同比 5%
                  <q-icon size="xs" name="arrow_drop_down" color="info" />
                </span>
              </q-item-label>
            </div>
            <q-separator spaced="15px" />
            <q-item-label>
              <span>转化率</span>
              <span class="float-right">60%</span>
            </q-item-label>
          </q-card>
        </q-card>
      </div>
      <div
        class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md"
        :class="{ 'q-pr-sm': $q.screen.gt.xs }"
      >
        <q-card flat class="no-border-radius text-grey-9">
          <q-card-section class="bg-white">
            <q-avatar
              color="green-1"
              size="sm"
              text-color="green"
              class="q-mr-sm"
              icon="visibility"
            ></q-avatar>
            <span> 新增用户 </span>
            <span>
              <q-icon
                name="help_outline"
                color="grey-7"
                class="float-right"
              ></q-icon>
            </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md">
            <div style="height: 70px">
              <q-item-label
                class="text-grey-9 q-mb-md"
                style="font-size: xx-large"
              >
                128 位
              </q-item-label>
              <q-item-label class="q-mb-md">
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" style="margin-left: -12px">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar
                  size="sm"
                  color="orange-1"
                  text-color="orange"
                  style="margin-left: -12px"
                >
                  +3
                </q-avatar>
              </q-item-label>
            </div>
            <q-separator spaced="15px" />
            <q-item-label>
              <span>总用户</span>
              <span class="float-right">10800 人</span>
            </q-item-label>
          </q-card>
        </q-card>
      </div>
      <div class="col-12 q-mb-md">
        <div class="no-border-radius row q-gutter-y-md">
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="group" text-color="primary"></q-avatar>
              <q-item-label>用户</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="leaderboard" text-color="green"></q-avatar>
              <q-item-label>分析</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="shopping_cart" text-color="orange"></q-avatar>
              <q-item-label>商品</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="assignment" text-color="purple"></q-avatar>
              <q-item-label>订单</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="card_travel" text-color="yellow"></q-avatar>
              <q-item-label>票据</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="email" text-color="info"></q-avatar>
              <q-item-label>消息</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="bookmarks" text-color="accent"></q-avatar>
              <q-item-label>标签</q-item-label>
            </q-card-section>
          </div>
          <div class="col-lg col-md-2 col-xs-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar
                icon="design_services"
                text-color="deep-orange"
              ></q-avatar>
              <q-item-label>配置</q-item-label>
            </q-card-section>
          </div>
        </div>
      </div>
      <div class="col-12">
        <div class="q-px-sm q-mb-md" :class="{ 'q-pr-sm': $q.screen.gt.xs }">
          <q-card flat class="no-border-radius text-grey-9">
            <q-card-section class="bg-whit row items-center">
              <q-item-label class="col-auto">
                <q-avatar
                  color="green-1"
                  size="sm"
                  text-color="green"
                  class="q-mr-sm"
                  icon="leaderboard"
                ></q-avatar>
                <span> 访问量 </span>
              </q-item-label>
              <q-item-label class="col q-gutter-sm text-right">
                <q-btn-toggle
                  style="height: 36px"
                  v-model="visitQuery"
                  unelevated
                  toggle-color="primary"
                  :options="[
                    { label: '今日', value: 'one' },
                    { label: '当月', value: 'two' },
                    { label: '当年', value: 'three' }
                  ]"
                />
                <q-input
                  class="float-right"
                  v-model="data"
                  dense
                  outlined
                  style="width: 181px"
                >
                  <template v-slot:append>
                    <q-icon name="event" color="orange" />
                  </template>
                </q-input>
              </q-item-label>
            </q-card-section>
            <q-separator />
            <div class="row q-pa-md">
              <q-item-label
                class="col-md-8 col-sm-6 col-xs-12 q-mb-sm"
                :class="{ 'q-pr-sm': $q.screen.gt.xs }"
              >
                <q-item-label class="text-weight-bold">访问量趋势</q-item-label>
                <q-banner class="bg-red-2 q-mt-sm" style="height: 248px">
                  You have lost connection to the internet. This app is offline.
                  <template v-slot:action>
                    <q-btn flat label="Turn ON Wifi" />
                  </template>
                </q-banner>
              </q-item-label>
              <q-item-label
                class="col-md-4 col-sm-6 col-xs-12"
                :class="{ 'q-pl-sm': $q.screen.gt.xs }"
              >
                <q-item-label class="text-weight-bold q-mb-sm"
                  >访问量排行</q-item-label
                >
                <q-list>
                  <q-item
                    class="q-pl-none q-py-sm"
                    dense
                    v-for="item in 7"
                    :key="item"
                  >
                    <q-item-section
                      avatar
                      class="q-pr-none"
                      style="min-width: 32px"
                    >
                      <q-avatar
                        v-if="item < 4"
                        color="blue-1"
                        text-color="blue"
                        size="sm"
                      >
                        {{ item }}</q-avatar
                      >
                      <q-avatar
                        v-else
                        color="grey-2"
                        text-color="grey-10"
                        size="sm"
                      >
                        {{ item }}</q-avatar
                      >
                    </q-item-section>
                    <q-item-section>东直门 {{ item }} 号</q-item-section>
                    <q-item-section side>456,789</q-item-section>
                  </q-item>
                </q-list>
              </q-item-label>
            </div>
          </q-card>
        </div>
      </div>
      <div class="col-12">
        <div class="row">
          <div
            class="col-md-6 col-xs-12 q-px-sm q-mb-md"
            :class="{ 'q-pr-sm': $q.screen.gt.xs }"
          >
            <q-card flat class="no-border-radius text-grey-9">
              <q-card-section class="bg-white">
                <q-avatar
                  color="purple-1"
                  size="sm"
                  text-color="purple"
                  class="q-mr-sm"
                  icon="search"
                ></q-avatar>
                <span> 热门搜索 </span>
                <span>
                  <q-icon
                    name="more_vert"
                    color="grey-7"
                    class="float-right"
                  ></q-icon>
                </span>
              </q-card-section>
              <q-separator />
              <q-table :data="data" :columns="columns" row-key="key"> </q-table>
            </q-card>
          </div>
          <div
            class="col-md-6 col-xs-12 q-px-sm"
            :class="{ 'q-pr-sm': $q.screen.gt.xs }"
          >
            <q-card flat class="no-border-radius">
              <q-card-section>
                <q-avatar
                  color="orange-1"
                  size="sm"
                  text-color="orange"
                  class="q-mr-sm"
                  icon="groups"
                ></q-avatar>
                <span> 用户画像 </span>
                <span>
                  <q-icon
                    name="more_vert"
                    color="grey-7"
                    class="float-right"
                  ></q-icon>
                </span>
              </q-card-section>
              <q-separator />
              <q-item-label class="row text-center q-px-md q-pt-md">
                <div class="col text-blue">
                  <q-avatar icon="accessibility" color="blue-1"></q-avatar>
                  <q-item-label class="q-mt-sm">男性 65%</q-item-label>
                </div>
                <div class="col text-purple-4">
                  <q-avatar icon="accessibility" color="purple-1"></q-avatar>
                  <q-item-label class="q-mt-sm">男性 25%</q-item-label>
                </div>
                <div class="col text-grey">
                  <q-avatar icon="accessibility" color="grey-2"></q-avatar>
                  <q-item-label class="q-mt-sm">未知 10%</q-item-label>
                </div>
              </q-item-label>
              <q-item-label class="row q-pl-sm q-gutter-sm">
                <q-avatar
                  :color="color(item)"
                  :size="size"
                  square
                  rounded
                  v-for="item in 100"
                  :key="item"
                ></q-avatar>
              </q-item-label>
              <q-item-label>
                <q-img
                  :ratio="3 / 1"
                  src="https://file.iviewui.com/admin-pro-dist/img/user-preference.11401619.png"
                ></q-img>
              </q-item-label>
            </q-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 2; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'Analysis',
  data() {
    return {
      visitQuery: 'one',
      rating: 4.5,
      stepData: [20, 40, 80],
      items: itemsInit,
      activeClass: {
        border: '1px dashed dodgerblue'
      },
      unActiveClass: {
        border: '1px dashed lightgrey'
      },
      selected: [],
      columns: [
        {
          name: 'rank',
          required: true,
          label: '排名',
          align: 'left',
          field: 'rank',
          sortable: true
        },
        {
          name: 'searchKey',
          align: 'left',
          label: '搜索关键词',
          field: 'searchKey',
          sortable: true
        },
        {
          name: 'userCount',
          align: 'left',
          label: '用户数',
          field: 'userCount',
          sortable: true
        },
        {
          name: 'zhangfu',
          align: 'right',
          label: '周涨幅',
          field: 'zhangfu',
          sortable: true
        }
      ],
      data: [
        {
          rank: 1,
          searchKey: '搜索关键词-0',
          userCount: 257,
          zhangfu: '20%'
        },
        {
          rank: 2,
          searchKey: '搜索关键词-1',
          userCount: 2537,
          zhangfu: '20%'
        },
        {
          rank: 3,
          searchKey: '搜索关键词-2',
          userCount: 2527,
          zhangfu: '20%'
        },
        {
          rank: 4,
          searchKey: '搜索关键词-3',
          userCount: 3257,
          zhangfu: '20%'
        },
        {
          rank: 5,
          searchKey: '搜索关键词-4',
          userCount: 4257,
          zhangfu: '20%'
        }
      ]
    }
  },
  methods: {
    select(item) {
      this.items[item.id].select = true
    },
    clear(item) {
      this.items[item.id].select = false
    },
    color(item) {
      if (item < 65) {
        return 'blue'
      } else if (item >= 65 && item < 90) {
        return 'purple-4'
      } else {
        return 'grey'
      }
    }
  },
  computed: {
    size() {
      if (this.$q.screen.gt.md) {
        return 'sm'
      }
      if (this.$q.screen.gt.xs) {
        return 'xs'
      }
      return '1em'
    }
  }
}
</script>

<style lang="sass">
.sc-design
  .q-field--dense
    .q-field__control
      height: 36px

.sc-design
  .q-field__append
    height: 36px

.sc-card-height
  height: 250px

.active-class
  border: 1px dashed #1890ff

.un-active-class
  border: 1px dashed lightgrey

.shadow-blue
  box-shadow: 0 0 8px -1px #31CCEC
</style>
