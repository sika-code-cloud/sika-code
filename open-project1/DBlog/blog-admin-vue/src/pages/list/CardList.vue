<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>卡片列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        段落示意：sika设计平台 ant.design，用最小的工作量，无缝接入sika生态，
        提供跨越设计与开发的体验解决方案。
      </p>
    </div>
    <div class="q-px-sm q-mt-sm">
      <sc-page
        :items="itemsInit"
        :per-number="12"
        item-class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection once>
            <sc-shadow>
              <q-card square bordered flat>
                <q-item>
                  <q-item-section avatar>
                    <q-avatar>
                      <q-img src="~assets/head_1.png" />
                    </q-avatar>
                  </q-item-section>
                  <q-item-section>
                    <q-item-label class="text-body1">
                      <strong>爱美丽{{ props.item.id }}</strong>
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
        </template>
      </sc-page>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 100; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'CardList',
  components: { ScPage, ScShadow },
  data() {
    return {
      itemsInit,
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
