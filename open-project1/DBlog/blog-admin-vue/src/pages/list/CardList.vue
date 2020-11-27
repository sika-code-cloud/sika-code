<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>卡片列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        段落示意：sika设计平台 ant.design，用最小的工作量，无缝接入sika生态，
        提供跨越设计与开发的体验解决方案。
      </p>
    </div>
    <div class="row q-gutter-y-md q-mt-sm q-px-sm">
      <div
        class="col-xl-2 col-md-3 col-sm-4 col-xs-12 q-px-sm"
        v-bind:key="item.id"
        v-for="item in items"
      >
        <sc-shadow></sc-shadow>
        <sc-shadow
          class="sc-card-height"
        >
          <q-card
            class="full-height row items-center"
            square
            flat
            :class="{
              'active-class': item.select,
              'un-active-class': !item.select
            }"
            v-if="item.id === 0"
          >
            <div class="col text-body1 text-center">
              <q-icon name="add" />新增产品
            </div>
          </q-card>
          <q-card class="sc-card-height" square flat v-else>
            <q-card-section horizontal>
              <q-img class="col sc-card-height" :src="item.src" />
              <q-card-actions vertical class="justify-around q-px-md">
                <q-btn flat round color="red" icon="favorite" />
                <q-btn flat round color="accent" icon="bookmark" />
                <q-btn flat round color="primary" icon="share" />
              </q-card-actions>
            </q-card-section>
          </q-card>
        </sc-shadow>
      </div>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import ScShadow from 'components/shadow/ScShadow'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 10; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'CardList',
  components: { ScShadow },
  data() {
    return {
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
