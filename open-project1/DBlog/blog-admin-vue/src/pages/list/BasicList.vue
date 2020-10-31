<template>
  <div>
    <div class="text-h6"><strong>查询表格</strong></div>
    <q-card square flat class="q-gutter-y-md q-mt-lg">
      <div class="row justify-center">
        <q-card-section class="col-sm-3 col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >我的待办</q-item-label
          >
          <q-item-label class="text-h5 text-center">8个任务</q-item-label>
        </q-card-section>
        <q-separator vertical />
        <q-card-section class="col-sm-4 col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周任务平均处理时间</q-item-label
          >
          <q-item-label class="text-h5 text-center">32分钟</q-item-label>
        </q-card-section>
        <q-separator vertical />
        <q-card-section class="col-sm-3 col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周完成任务数</q-item-label
          >
          <q-item-label class="text-h5 text-center">24个任务</q-item-label>
        </q-card-section>
      </div>
    </q-card>
    <q-card square flat class="q-gutter-y-md q-mt-lg">
      <q-card-section class="row items-center q-pb-none">
        <q-card-section class="text-body1 col-sm-3 col-xs-4">
          基本列表
        </q-card-section>
        <q-card-section class="col-sm-9 col-xs-8 q-px-none">
          <div class="row q-gutter-y-sm q-gutter-x-md justify-end">
            <div class="col-md-5 col-xs-12" style="max-width: 280px">
              <q-btn-toggle
                spread
                class="no-border-radius"
                style="border: lightgrey solid 1px; height: 40px"
                v-model="model"
                unelevated
                toggle-color="primary"
                :options="[
                  { label: '全部', value: 'one' },
                  { label: '进行中', value: 'two' },
                  { label: '等待中', value: 'three' }
                ]"
              />
            </div>
            <div class="col-md-5 col-xs-12" style="max-width: 280px">
              <q-input
                ref="ruleName"
                placeholder="请输入"
                outlined
                v-model="ruleName"
                dense
                square
              >
                <template v-slot:append>
                  <q-btn
                    style="
                      margin-right: -12px;
                      border-left: solid 1px lightgrey;
                      height: 100%;
                    "
                    class="no-border-radius"
                    padding="0 10px"
                    flat
                    color="lightgrey"
                    icon="search"
                    @click="ruleName = new Date()"
                  ></q-btn>
                </template>
              </q-input>
            </div>
          </div>
        </q-card-section>
      </q-card-section>
      <q-card-section class="q-mt-none">
        <q-btn
          color="primary"
          unelevated
          icon="add"
          class="full-width no-border-radius"
          label="添加"
        />
      </q-card-section>
    </q-card>

    <q-scroll-area class="bg-white q-mt-sm" style="height: 500px">
      <q-item-label header>Google Inbox style</q-item-label>

      <div v-for="n in 50" :key="n" v-intersection="onIntersection">
        <q-item>
          <q-item-section avatar top>
            <q-icon name="account_tree" color="black" size="34px" />
          </q-item-section>

          <q-item-section top class="col-2 gt-sm">
            <q-item-label class="q-mt-sm">GitHub</q-item-label>
          </q-item-section>

          <q-item-section top>
            <q-item-label lines="1">
              <span class="text-weight-medium">[quasarframework/quasar]</span>
              <span class="text-grey-8"> - GitHub repository</span>
            </q-item-label>
            <q-item-label caption lines="1">
              @rstoenescu in #3: > Generic type parameter for props
            </q-item-label>
            <q-item-label
              lines="1"
              class="q-mt-xs text-body2 text-weight-bold text-primary text-uppercase"
            >
              <span class="cursor-pointer">Open in GitHub</span>
            </q-item-label>
          </q-item-section>

          <q-item-section top side>
            <div class="text-grey-8 q-gutter-xs">
              <q-btn class="gt-xs" size="12px" flat dense round icon="delete" />
              <q-btn class="gt-xs" size="12px" flat dense round icon="done" />
              <q-btn size="12px" flat dense round icon="more_vert" />
            </div>
          </q-item-section>
        </q-item>
        <q-separator v-if="n !== 50" spaced="10px" inset="" />
      </div>
    </q-scroll-area>
  </div>
</template>

<script>
export default {
  name: 'BasicList',
  data() {
    return {
      visible: false,
      model: '',
      ruleName: ''
    }
  },
  computed: {
    visibleClass() {
      return `bg-${this.visible ? 'positive' : 'negative'}`
    }
  },
  methods: {
    onIntersection(entry) {
      this.visible = entry.isIntersecting
    }
  }
}
</script>

<style scoped></style>
