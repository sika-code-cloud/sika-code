<template>
  <div>
    <div class="sc-base-list">
      <div class="bg-white text-h6 q-pa-md" style="margin: -10px -16px 0 -16px">
        <strong>搜索列表（项目）</strong>
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
    <div class="bg-white q-gutter-md q-mt-md q-ml-none q-pa-sm">
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
            use-input
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
    <div class="row q-gutter-y-sm q-mt-sm">
      <div
        class="col-xl-3 col-md-4 col-sm-6 col-xs-12"
        v-bind:key="item.id"
        v-for="item in items"
      >
        <div
          class="q-ma-xs cursor-pointer"
          :class="{
            'shadow-5 text-primary ': item.select,
            'text-grey-8': !item.select
          }"
          @mouseover="select(item)"
          @mouseleave="clear(item)"
        >
          <q-card square flat>
            <q-img :src="item.src" :ratio="1.618 / 1" />

            <q-list>
              <q-item clickable>
                <q-item-section avatar>
                  <q-icon color="primary" name="local_bar" />
                </q-item-section>

                <q-item-section>
                  <q-item-label>Bar XYZ</q-item-label>
                  <q-item-label caption>Have a drink.</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable>
                <q-item-section avatar>
                  <q-icon color="red" name="local_gas_station" />
                </q-item-section>

                <q-item-section>
                  <q-item-label>Gas Station</q-item-label>
                  <q-item-label caption>Fill your gas tank.</q-item-label>
                </q-item-section>
              </q-item>

              <q-item clickable>
                <q-item-section avatar>
                  <q-icon color="amber" name="local_movies" />
                </q-item-section>

                <q-item-section>
                  <q-item-label>Cinema XYZ</q-item-label>
                  <q-item-label caption>Watch a movie.</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
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
for (let i = 0; i < 30; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemTemp.src = srcs[i % srcs.length]
  itemsInit.push(itemTemp)
}
export default {
  name: 'Projects',
  data() {
    return {
      types,
      owner: [],
      activeUser: '张三',
      goodPing: '优秀',
      owners: ownersDefault,
      queryData: '',
      items: itemsInit,
      activeClass: {
        border: '1px dashed dodgerblue'
      },
      unActiveClass: {
        border: '1px dashed lightgrey'
      }
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
.active-class
  border: 1px dashed #1890ff
.un-active-class
  border: 1px dashed lightgrey
</style>
