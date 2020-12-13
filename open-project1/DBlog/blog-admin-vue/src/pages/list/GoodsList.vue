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
        :items="goodsListData.goodsListDatas"
        :per-number="12"
        item-class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection
            once
            transition="scale">
            <sc-shadow>
              <q-card flat class="no-border-radius q-pb-sm">
                <q-chip
                  outline
                  size="sm"
                  :label="props.item.labelData.label"
                  :color="props.item.labelData.color"
                  :class="props.item.labelData.bgColor"
                  :style="props.item.labelData.style"
                  class="q-ma-sm absolute-top-right"
                  square
                ></q-chip>
                <q-img
                  :src="props.item.imgSrc"
                  width="50%"
                  class="q-mt-md"
                />
                <q-card-section>
                  <q-item-label class="text-bold">{{ props.item.name }}</q-item-label>
                  <q-item-label class="text-grey q-py-sm"
                  >{{ props.item.desc }}
                  </q-item-label>
                  <q-item-label class="text-pink">¥ {{ props.item.price }} 元</q-item-label>
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
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'
import GOODS_LIST_DATA from '@/mock/data/list/goodsListData'

export default {
  name: 'GoodsList',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      goodsListData: GOODS_LIST_DATA
    }
  },
  computed: {}
}
</script>

<style lang="sass">
.example-item
  height: 289px
  width: 100%
</style>
