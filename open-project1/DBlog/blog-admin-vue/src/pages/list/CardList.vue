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
        :items="applicationsData.applicationsDatas"
        class="q-mt-sm"
        item-class="col-xl-3 col-sm-4 col-xs-12"
      >
        <template v-slot:item="props">
          <q-intersection once>
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

export default {
  name: 'CardList',
  components: { ScPage, ScShadow },
  data() {
    return {
      applicationsData: APPLICATIONS_DATA
    }
  },
  methods: {}
}
</script>

<style lang="sass" scoped>
</style>
