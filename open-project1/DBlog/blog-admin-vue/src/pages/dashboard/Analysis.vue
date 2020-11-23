<template>
  <div>
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
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm">
                  <q-img src="~assets/head.png"></q-img>
                </q-avatar>
                <q-avatar size="sm" color="orange-1" text-color="orange">
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
      <div class="col-12">
        <q-card
          flat
          square
          class="no-border-radius row bg-grey-2 q-gutter-y-md"
        >
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="group" text-color="primary"></q-avatar>
              <q-item-label>用户</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="leaderboard" text-color="green"></q-avatar>
              <q-item-label>分析</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="shopping_cart" text-color="orange"></q-avatar>
              <q-item-label>商品</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="assignment" text-color="purple"></q-avatar>
              <q-item-label>订单</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="card_travel" text-color="yellow"></q-avatar>
              <q-item-label>票据</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="email" text-color="info"></q-avatar>
              <q-item-label>消息</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar icon="bookmarks" text-color="accent"></q-avatar>
              <q-item-label>标签</q-item-label>
            </q-card-section>
          </div>
          <div class="col-md-2 col-sm-3 col-4 text-center q-px-sm">
            <q-card-section class="bg-white">
              <q-avatar
                icon="design_services"
                text-color="deep-orange"
              ></q-avatar>
              <q-item-label>配置</q-item-label>
            </q-card-section>
          </div>
        </q-card>
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
          name: 'desc',
          required: true,
          label: '任务',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`,
          sortable: true
        },
        {
          name: 'calories',
          align: 'left',
          label: '作者',
          field: 'calories',
          sortable: true
        }
      ],
      data: [
        {
          name: 'Card 支持点击，可以配置 to 等属性',
          calories: '张三'
        },
        {
          name: 'Tabs 新增属性，高度可以自适应其它高度',
          calories: '李四'
        },
        {
          name: 'Drawer 新增可拖拽调整宽度的属性',
          calories: '王大锤'
        },
        {
          name: 'AvatarList 支持配置 extra，不一定给全量数据',
          calories: '张三'
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
    }
  }
}
</script>

<style lang="sass" scoped>
.sc-card-height
  height: 250px

.active-class
  border: 1px dashed #1890ff

.un-active-class
  border: 1px dashed lightgrey

.shadow-blue
  box-shadow: 0 0 8px -1px #31CCEC
</style>
