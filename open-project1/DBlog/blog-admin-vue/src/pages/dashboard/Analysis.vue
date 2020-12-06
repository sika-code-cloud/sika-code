<template>
  <div class="sc-design q-my-md">
    <div class="row q-px-sm">
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md">
        <sc-shadow>
          <q-card flat class="no-border-radius text-grey-9">
            <q-card-section class="bg-white">
              <q-avatar
                color="green-1"
                size="sm"
                text-color="green"
                class="q-mr-sm"
                :icon="analysisData.visitData.icon"
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
                  {{ analysisData.visitData.dayVisitCount }}
                </q-item-label>
                <q-item-label class="q-mb-md">
                  <span class="q-mr-md">
                    日同比 {{ analysisData.visitData.dayForCompare }}
                    <q-icon size="xs" name="arrow_drop_up" color="red" />
                  </span>
                  <span
                  >周同比 {{ analysisData.visitData.weekForCompare }}
                    <q-icon size="xs" name="arrow_drop_down" color="info" />
                  </span>
                </q-item-label>
              </div>
              <q-separator spaced="15px" />
              <q-item-label>
                <span>总访问量</span>
                <span class="float-right">{{
                    analysisData.visitData.visitTotal
                  }}</span>
              </q-item-label>
            </q-card>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md">
        <sc-shadow>
          <q-card flat class="no-border-radius text-grey-9">
            <q-card-section class="bg-white">
              <q-avatar
                color="red-1"
                size="sm"
                text-color="red"
                class="q-mr-sm"
                :icon="analysisData.saleData.icon"
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
                  ¥{{ analysisData.saleData.saleMoney }}
                </q-item-label>
                <q-item-label class="q-pb-sm">
                  <q-linear-progress :value="0.7" rounded stripe size="8px" />
                </q-item-label>
              </div>
              <q-separator spaced="15px" />
              <q-item-label>
                <span>总销售额</span>
                <span class="float-right">{{
                    analysisData.saleData.saleTotal
                  }}</span>
              </q-item-label>
            </q-card>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md">
        <sc-shadow>
          <q-card flat class="no-border-radius text-grey-9">
            <q-card-section class="bg-white">
              <q-avatar
                color="orange-1"
                size="sm"
                text-color="orange"
                class="q-mr-sm"
                :icon="analysisData.orderData.icon"
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
                  {{ analysisData.orderData.dayOrderCount }}
                </q-item-label>
                <q-item-label class="q-mb-md">
                  <span class="q-mr-md">
                    日同比 {{ analysisData.orderData.dayForCompare }}
                    <q-icon size="xs" name="arrow_drop_up" color="red" />
                  </span>
                  <span
                  >周同比 {{ analysisData.orderData.weekForCompare }}
                    <q-icon size="xs" name="arrow_drop_down" color="info" />
                  </span>
                </q-item-label>
              </div>
              <q-separator spaced="15px" />
              <q-item-label>
                <span>转化率</span>
                <span class="float-right">{{
                    analysisData.orderData.conversionRate
                  }}</span>
              </q-item-label>
            </q-card>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm q-mb-md">
        <sc-shadow>
          <q-card flat class="no-border-radius text-grey-9">
            <q-card-section class="bg-white">
              <q-avatar
                color="purple-1"
                size="sm"
                text-color="purple"
                class="q-mr-sm"
                :icon="analysisData.userData.icon"
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
                  {{ analysisData.userData.dayIncrease }} 位
                </q-item-label>
                <q-item-label class="q-mb-md">
                  <span
                    v-for="(user, index) in analysisData.userData.users"
                    :key="index"
                  >
                    <q-avatar size="sm" v-if="index === 0">
                      <q-img :src="user.head" />
                      <q-tooltip
                        transition-show="flip-right"
                        transition-hide="flip-left"
                        content-class="bg-primary"
                        content-style="font-size: 10px"
                      >
                        {{ user.name }}
                      </q-tooltip>
                    </q-avatar>
                    <q-avatar v-else size="sm" style="margin-left: -8px">
                      <q-img :src="user.head" />
                      <q-tooltip
                        transition-show="flip-right"
                        transition-hide="flip-left"
                        content-class="bg-primary"
                        content-style="font-size: 10px"
                      >
                        {{ user.name }}
                      </q-tooltip>
                    </q-avatar>
                  </span>
                  <q-avatar
                    size="sm"
                    color="orange-2"
                    text-color="deep-orange"
                    style="margin-left: -8px"
                  >
                    +3
                  </q-avatar>
                </q-item-label>
              </div>
              <q-separator spaced="15px" />
              <q-item-label>
                <span>总用户</span>
                <span class="float-right"
                >{{ analysisData.userData.userTotal }} 人</span
                >
              </q-item-label>
            </q-card>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-12 q-mb-md">
        <div class="no-border-radius row q-gutter-y-md">
          <div
            class="col-lg col-md-2 col-xs-4 text-center q-px-sm"
            v-for="(data, index) in analysisData.linkData"
            :key="index"
          >
            <sc-shadow>
              <q-card-section class="bg-white">
                <q-avatar
                  :icon="data.icon"
                  :text-color="data.textColor"
                ></q-avatar>
                <q-item-label>{{ data.desc }}</q-item-label>
              </q-card-section>
            </sc-shadow>
          </div>
        </div>
      </div>
      <div class="col-12">
        <div class="q-px-sm q-mb-md">
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
                  @input="changeQueryDate(visitQuery)"
                  style="height: 36px"
                  v-model="visitQuery"
                  unelevated
                  toggle-color="primary"
                  :options="[
                    { label: '今日', value: 'currentDay' },
                    { label: '当月', value: 'currentMonth' },
                    { label: '当年', value: 'currentYear' }
                  ]"
                />
                <sc-date-range class="float-right" ref="startEndDate" :field-style="{'min-width': '253px'}" />
              </q-item-label>
            </q-card-section>
            <q-separator />
            <div class="row q-pa-md">
              <q-item-label
                class="col-md-8 col-xs-12 q-mb-sm"
                :class="{ 'q-pr-md': $q.screen.gt.sm }"
              >
                <q-item-label class="text-weight-bold">访问量趋势</q-item-label>
                <q-banner class="bg-red-2 q-mt-sm" style="height: 248px">
                  You have lost connection to the internet. This app is offline.
                  <template v-slot:action>
                    <q-btn flat label="Turn ON Wifi" />
                  </template>
                </q-banner>
              </q-item-label>
              <q-item-label class="col-md-4 col-xs-12">
                <q-item-label class="text-weight-bold q-mb-sm"
                >访问量排行
                </q-item-label>
                <q-list>
                  <q-item
                    class="q-pl-none q-py-sm"
                    dense
                    v-for="item in analysisData.visitRankData"
                    :key="item.rank"
                  >
                    <q-item-section
                      avatar
                      class="q-pr-none"
                      style="min-width: 32px"
                    >
                      <q-avatar
                        v-if="item.rank < 4"
                        color="blue-1"
                        text-color="blue"
                        size="sm"
                      >
                        {{ item.rank }}
                      </q-avatar>
                      <q-avatar
                        v-else
                        color="grey-2"
                        text-color="grey-10"
                        size="sm"
                      >
                        {{ item.rank }}
                      </q-avatar>
                    </q-item-section>
                    <q-item-section>{{ item.desc }}</q-item-section>
                    <q-item-section side
                    >{{ item.visitNum | numeral('0,0') }}
                    </q-item-section>
                  </q-item>
                </q-list>
              </q-item-label>
            </div>
          </q-card>
        </div>
      </div>
      <div class="col-12">
        <div class="row">
          <div class="col-md-6 col-xs-12 q-px-sm q-mb-md">
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
              <q-table
                :data="analysisData.hotSearchData.data"
                :columns="analysisData.hotSearchData.columns"
                row-key="key"
              ></q-table>
            </q-card>
          </div>
          <div class="col-md-6 col-xs-12 q-px-sm">
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
              <q-item-label class="row text-center q-px-md q-pt-lg q-pb-sm">
                <div class="col text-blue">
                  <q-avatar icon="accessibility" color="blue-1"></q-avatar>
                  <q-item-label class="q-mt-sm">男性 65%</q-item-label>
                </div>
                <div class="col text-purple-4">
                  <q-avatar icon="accessibility" color="purple-1"></q-avatar>
                  <q-item-label class="q-mt-sm">女性 25%</q-item-label>
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
import ANALYSIS_DATA from '@/mock/data/dashboard/analysisData'
import ScShadow from 'components/shadow/ScShadow'
import { date } from 'quasar'
import ScDateRange from 'components/common/ScDateRange'

export default {
  name: 'Analysis',
  components: { ScDateRange, ScShadow },
  data() {
    return {
      analysisData: ANALYSIS_DATA,
      visitQuery: 'currentDay'
    }
  },
  methods: {
    color(item) {
      if (item < 65) {
        return 'blue'
      } else if (item >= 65 && item < 90) {
        return 'purple-4'
      } else {
        return 'grey'
      }
    },
    changeQueryDate(value) {
      const currentDate = new Date()
      let lastDate = new Date()
      if (value === 'currentMonth') {
        lastDate = date.subtractFromDate(currentDate, { days: 30 })
      } else if (value === 'currentYear') {
        lastDate = date.subtractFromDate(currentDate, { days: 365 })
      }
      this.$refs.startEndDate.buildStartAndEndDate(lastDate, currentDate)
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
<style lang="sass"></style>
