<template>
  <div>
    <div class="sc-base-list">
      <div class="bg-white text-h6 q-pa-md sc-design">
        <strong>搜索列表（项目）</strong>
        <div class="row full-width justify-center">
          <span class="col sc-design" style="max-width: 500px">
            <q-input
              class="q-ma-md"
              outlined
              v-model="queryData"
              dense
              placeholder="请输入"
              square
            >
              <template v-slot:append>
                <q-btn
                  class="no-border-radius full-height"
                  color="primary"
                  unelevated
                  label="搜索"
                  style="margin-right: -12px"
                >
                </q-btn>
              </template>
            </q-input>
          </span>
        </div>
      </div>
    </div>
    <div class="sc-design-min bg-white q-gutter-md q-mt-md q-mx-md q-pa-sm">
      <div class="row">
        <span class="col-auto q-mt-xs"> 所属类目：</span>
        <span class="col">
          <q-chip
            class="cursor-pointer no-border-radius"
            style="padding: 12px 12px"
            square
            dense
            color="primary"
            v-for="type in types"
            v-bind:key="type"
            :label="type"
            text-color="white"
          />
        </span>
      </div>
      <div class="row">
        <span class="col-sm-auto col-xs-2">owner： </span>
        <span class="col-sm-auto col-xs-9">
          <q-select
            class="col-auto inline-block"
            style="min-width: 100px"
            outlined
            v-model="owner"
            square
            multiple
            dense
            hide-dropdown-icon
            options-dense
            transition-show="jump-down"
            transition-hide="jump-up"
            behavior="menu"
            :options="owners"
            emit-value
            map-options
            @filter="filterFn"
          >
            <template v-slot:selected-item="scope">
              <q-chip
                removable
                dense
                @remove="scope.removeAtIndex(scope.index)"
                :tabindex="scope.tabindex"
                color="primary"
                text-color="white"
                class="q-ma-xs"
              >
                {{ scope.opt.label }}
              </q-chip>
            </template>
            <template v-slot:option="scope">
              <q-item
                class="q-pa-none"
                v-bind="scope.itemProps"
                v-on="scope.itemEvents"
              >
                <q-item-section side>
                  <q-checkbox
                    :disable="scope.opt.disable"
                    v-model="owner"
                    :val="scope.opt.value"
                  ></q-checkbox>
                </q-item-section>
                <q-item-section class="q-pr-sm">
                  <q-item-label v-html="scope.opt.label"></q-item-label>
                </q-item-section>
              </q-item>
            </template>
          </q-select>
        </span>
      </div>
      <div class="row">
        <span class="col-sm-auto q-mt-sm"> 其他选项：</span>
        <span class="col-sm-9 col-xs-8">
          <span class="row q-gutter-y-sm">
            <span class="col-sm-5 col-xs-9">
              活跃用户：
              <q-select
                class="inline-block"
                style="width: 80px"
                outlined
                v-model="activeUser"
                square
                dense
                options-dense
                hide-dropdown-icon
                behavior="menu"
                :options="['张三', '张小三', '李泽瑞', '朱元璋', '姚明']"
              />
            </span>
            <span class="col-sm-5 col-xs-9">
              好评度：<q-select
              class="inline-block"
              style="width: 80px"
              outlined
              v-model="goodPing"
              hide-dropdown-icon
              square
              dense
              options-dense
              behavior="menu"
              :options="['优秀', '普通', '一般']"
            />
            </span>
          </span>
        </span>
      </div>
    </div>
    <div class="q-px-sm">
      <sc-page
        :items="projectsData.projectDatas"
        class="q-mt-sm"
        item-class="col-xl-3 col-sm-4 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection
            once
            transition="scale">
            <sc-shadow>
              <q-card square bordered flat class="q-pb-sm">
                <q-img :src="props.item.pictureSrc" :ratio="16 / 10" />
                <q-list class="q-mt-md">
                  <q-item dense class="text-body1">
                    <strong>Alipay-{{ props.item.index }}</strong>
                  </q-item>
                  <q-item dense>
                    那是一种内在的东西， 他们到达不了，也无法
                  </q-item>
                  <q-item dense clickable>
                    <q-item-section style="font-size: 12px">
                      {{ props.item.hour }}小时前
                    </q-item-section>
                    <span>
                        <q-img
                          src="~assets/head.png"
                          style="width: 28px; height: 28px"
                        >
                          <q-tooltip>张三</q-tooltip>
                        </q-img>
                        <q-img
                          src="~assets/head_1.png"
                          style="width: 28px; height: 28px; margin-left: -12px"
                        >
                          <q-tooltip>豆豆</q-tooltip>
                        </q-img>
                        <q-img
                          src="~assets/head.png"
                          style="width: 28px; height: 28px; margin-left: -12px"
                        >
                          <q-tooltip>乐乐</q-tooltip>
                        </q-img>
                      </span>
                  </q-item>
                </q-list>
              </q-card>
            </sc-shadow>
          </q-intersection>
        </template>
      </sc-page>
    </div>
  </div>
</template>

<script>
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'
import PROJECTS_DATA from '@/mock/data/list/search/projectsData'

const types = []
types.push('全部')
for (let i = 0; i < 11; ++i) {
  types.push('类目' + (i + 1))
}
const ownersDefault = [
  {
    label: '我自己',
    value: '我自己',
    icon: 'mail'
  },
  {
    label: '张小三',
    value: '张小三',
    icon: 'bluetooth'
  },
  {
    label: '李泽瑞',
    value: '李泽瑞',
    icon: 'map'
  },
  {
    label: '朱元璋',
    value: '朱元璋',
    icon: 'golf_course'
  },
  {
    label: '姚明',
    value: '姚明',
    disable: true,
    icon: 'casino'
  }
]
export default {
  name: 'Projects',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      projectsData: PROJECTS_DATA,
      types,
      owner: [],
      activeUser: '张三',
      goodPing: '优秀',
      owners: ownersDefault,
      queryData: ''
    }
  },
  methods: {
    select(item) {
      this.items[item.id].select = true
    },
    clear(item) {
      this.items[item.id].select = false
    },
    filterFn(val, update) {
      update(() => {
        if (val === '') {
          this.owners = ownersDefault
        } else {
          const needle = val
          this.owners = ownersDefault.filter(
            (v) => v.value.indexOf(needle) > -1
          )
        }
      })
    }
  }
}
</script>

<style lang="sass" scoped>
.sc-card-height
  height: 250px
</style>
