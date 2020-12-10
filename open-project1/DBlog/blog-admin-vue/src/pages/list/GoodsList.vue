<template>
  <div class="sc-design-sm">
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>商品列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        以卡片的形式展现商品或图片信息。
      </p>
    </div>

    <div class="q-mx-sm q-mt-sm q-pb-md text-grey-8 text-center">
      <sc-page
        :items="itemsInit"
        :per-number="12"
        item-class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection once>
            <sc-shadow>
              <q-card flat class="no-border-radius q-pb-sm">
                <q-chip
                  v-if="props.item.id % 6 === 1"
                  size="sm"
                  label="缺货"
                  text-color="orange"
                  class="bg-orange-1 q-ma-sm absolute-top-right"
                  style="border: 1px solid orangered"
                  square
                ></q-chip>
                <q-chip
                  v-else-if="props.item.id % 6 === 2"
                  size="sm"
                  label="推荐"
                  text-color="green"
                  class="bg-green-1 q-ma-sm absolute-top-right"
                  style="border: 1px solid limegreen"
                  square
                ></q-chip>
                <q-img
                  v-if="props.item.id % 6 === 1"
                  src="https://dev-file.iviewui.com/yxsk0RFxdR0X3S0N7QN33mvwLnkfHEJV/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-img
                  v-else-if="props.item.id % 6 === 2"
                  src="https://dev-file.iviewui.com/PKCycgm3DWJOca5I2uAEqneuLFQAcKa7/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-img
                  v-else-if="props.item.id % 6 === 3"
                  src="https://dev-file.iviewui.com/8WwJ7tWv9uZ8hepq8pDTjLQiHZy2MB9I/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-img
                  v-else-if="props.item.id % 6 === 4"
                  src="https://dev-file.iviewui.com/KUa7CaC6m7vRtDCfY0SAXlp7dw9OvBrf/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-img
                  v-else-if="props.item.id % 6 === 5"
                  src="https://dev-file.iviewui.com/UTPgv8fkOHXszM8Xxm33C2ABuTw7AlRE/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-img
                  v-else-if="props.item.id % 6 === 0"
                  src="https://dev-file.iviewui.com/OYZqqiP1bbwN22Ai2HnwvSagxuSNchdD/middle"
                  width="50%"
                  class="q-mt-md"
                />
                <q-card-section>
                  <q-item-label>Vue实战</q-item-label>
                  <q-item-label class="text-grey q-py-sm"
                    >Vue.js 作者尤雨溪作序
                  </q-item-label>
                  <q-item-label class="text-pink">¥ 69 元</q-item-label>
                </q-card-section>
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
for (let i = 0; i < 50; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'GoodsList',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      current: 3,
      itemsInit
    }
  }
}
</script>

<style lang="sass">
.sc-design-sm
  .q-field--dense
    .q-field__control
      height: 28px

    .q-field__marginal
      height: 28px
</style>
