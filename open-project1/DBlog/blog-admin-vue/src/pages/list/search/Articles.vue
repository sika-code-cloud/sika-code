<template>
  <div>
    <div class="sc-base-list">
      <div class="bg-white text-h6 q-pa-md sc-design">
        <strong>搜索列表（文章）</strong>
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
    <div class="bg-white q-mx-md q-mt-md q-pb-md q-pt-md">
      <sc-page :items="articlesData.articlesDatas" class="q-mt-sm">
        <template v-slot:item="props">
          <div class="q-pb-sm">
            <sc-shadow class="q-pa-sm">
              <q-item-section>
                <q-item-label class="text-body1 q-mb-sm">
                  <strong>
                    Alipay-{{ props.item.index }}
                  </strong>
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  <q-chip
                    label="Sika Design"
                    class="cursor-pointer"
                    color="primary"
                    text-color="white"
                    square
                    size="12px"
                  ></q-chip>
                  <q-chip
                    label="设计语言"
                    class="cursor-pointer"
                    color="info"
                    text-color="white"
                    square
                    size="12px"
                  ></q-chip>
                  <q-chip
                    label="蚂蚁金服"
                    class="cursor-pointer"
                    color="deep-orange"
                    text-color="white"
                    square
                    size="12px"
                  ></q-chip>
                </q-item-label>
                <p class="text-grey-8 q-mb-sm q-pl-xs">
                  段落示意：蚂蚁金服设计平台
                  ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。蚂蚁金服设计平台
                  ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。
                </p>
                <q-item-label
                  class="row text-body2 text-grey-7 items-center q-gutter-x-xs"
                >
                  <q-chip
                    color="white"
                    text-color="primary"
                    class="col-auto cursor-pointer"
                  >
                    <q-avatar size="18px">
                      <img
                        :src="props.item.imgSrc"
                      />
                    </q-avatar>
                    姜宇
                  </q-chip>
                  <span class="col-auto q-ml-none">发布在</span>
                  <span class="col-auto text-primary cursor-pointer"
                  >https://ant.design</span
                  >
                  <span class="col-sm-auto col-xs-12 q-pl-xs">
                        {{ props.item.publishTime }}
                      </span>
                </q-item-label>
                <div class="q-mt-sm row items-center" style="height: 24px">
                  <q-btn-group flat>
                    <q-btn
                      flat
                      dense
                      icon="star_border"
                      :label="props.item.collection"
                      size="12px"
                      class="q-mr-sm"
                      color="primary"
                    />
                    <q-separator vertical />
                    <q-btn
                      flat
                      dense
                      icon="thumb_up"
                      :label="props.item.star"
                      size="12px"
                      color="grey-6"
                      class="q-mx-sm"
                    />
                    <q-separator vertical />
                    <q-btn
                      flat
                      dense
                      icon="message"
                      :label="props.item.word"
                      size="12px"
                      color="grey-6"
                      class="q-mx-sm"
                    />
                  </q-btn-group>
                </div>
              </q-item-section>
            </sc-shadow>
          </div>
          <q-separator spaced="10px" />
        </template>
      </sc-page>
    </div>
  </div>
</template>

<script>
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'
import ARTICLES_DATA from '@/mock/data/list/search/articlesData'

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
  name: 'Articles',
  components: { ScPage, ScShadow },
  data() {
    return {
      articlesData: ARTICLES_DATA,
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
