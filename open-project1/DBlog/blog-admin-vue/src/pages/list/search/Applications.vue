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
    <div class="q-mt-md q-mx-sm">
      <div class="row">
        <div
          class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-xs-12"
          :key="item.id"
          v-for="item in itemsInit"
        >
          <q-intersection once class="q-pa-sm">
            <sc-shadow>
              <q-card square bordered flat>
                <q-item>
                  <q-item-section avatar>
                    <q-avatar>
                      <img src="~assets/head_1.png" />
                    </q-avatar>
                  </q-item-section>
                  <q-item-section>
                    <q-item-label class="text-body1">
                      <strong>爱美丽</strong>
                    </q-item-label>
                  </q-item-section>
                </q-item>
                <q-item class="block text-center q-pt-xs q-mb-sm">
                  <q-item-label class="row" caption>
                    <span class="col">活跃用户</span>
                    <span class="col">新增用户</span>
                  </q-item-label>
                  <q-item-label class="row text-body1 text-black">
                    <span class="col"><strong>12</strong>万</span>
                    <span class="col"><strong>1900</strong></span>
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScShadow from 'components/shadow/ScShadow'
import _ from 'lodash'

const srcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/uMfMFlvUuceEyPpotzlq.png',
  'https://gw.alipayobjects.com/zos/rmsportal/iZBVOIhGJiAnhplqjvZW.png',
  'https://pic.rmb.bdstatic.com/fbb6939952e2e27c5cdfc5b706905768.jpeg@wm_2,t_55m+5a625Y+3L+WIhuS6q+e+juS4veeahOmbhuS4reWcsA==,fc_ffffff,ff_U2ltSGVp,sz_26,x_17,y_17',
  'https://gw.alipayobjects.com/zos/rmsportal/iXjVmWVHbCJAyqvDxdtx.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604603259601&di=98453cd17ded14a25ec805df888b2267&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191128%2F14%2F1574923905-WqopanrGSZ.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/gLaIAoVWTtLbBWZNYEMg.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939538&di=a457faa41c62fff3cbf0ab1fed08dcfc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201308%2F05%2F20130805105309_5E2zE.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939540&di=329ba6679a8ac3e9feb457c5dd24e3d5&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170415%2F20170415170933_d0304d15b93b7025b2a045c4ff65baf3_13.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602941634&di=be2c1a81c94d87340186413a8b1bd42e&imgtype=0&src=http%3A%2F%2Ft1.hxzdhn.com%2Fuploads%2Ftu%2F201807%2F9999%2Fb488d1a5fc.jpg'
]
const itemDefault = {
  select: false
}
const itemsInit = []
for (let i = 0; i < 10; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemTemp.src = srcs[i % srcs.length]
  itemsInit.push(itemTemp)
}
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
  components: { ScShadow },
  data() {
    return {
      itemsInit,
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
