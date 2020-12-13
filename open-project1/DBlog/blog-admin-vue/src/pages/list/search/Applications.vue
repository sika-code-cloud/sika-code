<template>
  <div>
    <div class="sc-base-list">
      <div class="bg-white text-h6 q-pa-md sc-design">
        <strong>搜索列表（应用）</strong>
        <div class="row full-width justify-center">
          <span class="col" style="max-width: 500px">
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
        :items="applicationsData.applicationsDatas"
        class="q-mt-sm"
        item-class="col-xl-3 col-sm-4 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection
            once
            transition="scale">
            <sc-shadow>
              <q-card square bordered flat>
                <q-item>
                  <q-item-section avatar>
                    <q-avatar>
                      <img style="width: 30px; height: 30px" :src="props.item.imgSrc" />
                    </q-avatar>
                  </q-item-section>
                  <q-item-section>
                    <q-item-label class="text-body1">
                      <strong>爱美丽-{{ props.item.index }}</strong>
                    </q-item-label>
                  </q-item-section>
                </q-item>
                <q-item class="block text-center q-pt-xs q-mb-sm">
                  <q-item-label class="row" caption>
                    <span class="col">活跃用户</span>
                    <span class="col">新增用户</span>
                  </q-item-label>
                  <q-item-label class="row text-body1 text-black">
                    <span class="col"><strong>{{ props.item.activeUser }}</strong>万</span>
                    <span class="col"><strong>{{ props.item.increaseUser }}</strong></span>
                  </q-item-label>
                </q-item>
                <q-separator />
                <q-item class="q-px-sm">
                  <q-btn-group flat spread class="full-width">
                    <q-btn
                      flat
                      dense
                      icon="save_alt"
                      size="sm"
                      class="q-mr-sm"
                      color="grey-6"
                    >
                      <q-tooltip>下载</q-tooltip>
                    </q-btn>
                    <q-separator vertical inset="" />
                    <q-btn
                      flat
                      dense
                      icon="edit"
                      size="sm"
                      color="grey-6"
                      class="q-mx-sm"
                    >
                      <q-tooltip>编辑</q-tooltip>
                    </q-btn>
                    <q-separator vertical inset="" />
                    <q-btn
                      flat
                      dense
                      icon="share"
                      size="sm"
                      color="grey-6"
                      class="q-mx-sm"
                    >
                      <q-tooltip>分享</q-tooltip>
                    </q-btn>
                    <q-separator vertical inset="" />
                    <q-btn
                      flat
                      dense
                      icon="more_horiz"
                      size="sm"
                      color="grey-6"
                      class="q-mx-sm"
                    >
                      <q-tooltip>更多</q-tooltip>
                    </q-btn>
                  </q-btn-group>
                </q-item>
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
import APPLICATIONS_DATA from '@/mock/data/list/search/applicationsData'

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
  name: 'Applications',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      applicationsData: APPLICATIONS_DATA,
      types,
      owner: [],
      activeUser: '张三',
      goodPing: '优秀',
      owners: ownersDefault,
      queryData: ''
    }
  },
  methods: {
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

<style>
.q-item__section--side {
  padding-right: 0;
}
</style>
