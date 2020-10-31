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
                class="no-border-radius bg-light-blue-1"
                dense
                v-model="model"
                unelevated
                toggle-color="primary"
                :options="[
                  { label: '全部', value: 'all' },
                  { label: '进行中', value: 'ing' },
                  { label: '等待中', value: 'wait' }
                ]"
              />
            </div>
            <div class="col-md-3 col-xs-12" style="max-width: 280px">
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
                    dense
                    padding="xs"
                    flat
                    color="lightgrey"
                    icon="search"
                    @click="ruleName = new Date()"
                  ></q-btn>
                </template>
              </q-input>
            </div>
            <div class="col-md-auto col-xs-12 col-sm gt-sm">
              <q-btn
                color="primary"
                dense
                unelevated
                icon="add"
                class="no-border-radius q-px-sm"
                label="添加"
                @click="addTaskDialog"
              />
            </div>
          </div>
        </q-card-section>
      </q-card-section>
      <q-card-section class="q-mt-none lt-md">
        <q-btn
          padding="xs"
          color="primary"
          unelevated
          icon="add"
          class="full-width no-border-radius"
          label="添加"
          @click="addTaskDialog"
        />
      </q-card-section>
    </q-card>

    <q-scroll-area class="bg-white q-mt-sm" style="height: 500px">
      <q-item-label header>Google Inbox style</q-item-label>
      <div v-for="n in 50" :key="n" v-intersection="onIntersection">
        <q-item>
          <q-item-section v-if="n % 5 === 1" avatar top>
            <q-avatar square>
              <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"/>
            </q-avatar>
          </q-item-section>
          <q-item-section  v-if="n % 5 === 2" avatar top>
            <q-avatar square>
              <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png"/>
            </q-avatar>
          </q-item-section>
          <q-item-section v-if="n % 5 === 3" avatar top>
            <q-avatar square>
              <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png"/>
            </q-avatar>
          </q-item-section>
          <q-item-section v-if="n % 5 === 4" avatar top>
            <q-avatar square>
              <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png"/>
            </q-avatar>
          </q-item-section>
          <q-item-section  v-if="n % 5 === 0" avatar top>
            <q-avatar square>
              <img alt="" src="https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png"/>
            </q-avatar>
          </q-item-section>
          <div class="row items-center q-col-gutter-y-sm q-mr-none">
            <div class="col-md-4 col-xs-12">
              <q-item-label lines="1">
                <span class="text-weight-medium">Alipay</span>
              </q-item-label>
              <q-item-label caption lines="2" class="q-mr-xl">
                那是一种内在的东西， 他们到达不了，也无法触及的，那是一种内在的东西， 他们到达不了，也无法触及的
              </q-item-label>
            </div>
            <div class="col-md-2 col-sm-2 col-xs-6">
              <q-item-label class="q-mb-xs" caption lines="1">
                Owner
              </q-item-label>
              <q-item-label caption lines="2">
                付小小
              </q-item-label>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
              <q-item-label class="q-mb-xs" caption lines="1">
                开始时间
              </q-item-label>
              <q-item-label caption lines="2">
                2020-10-31 21:23
              </q-item-label>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
              <q-linear-progress style="width: 85%" rounded stripe size="md" :value="stepValue" />
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 q-gutter-x-sm" >
              <q-btn class="no-border-radius" padding="6px 10px" size="12px" style="font-size: 8px"  unelevated dense icon="delete" color="primary" label="编辑" />
              <q-btn class="no-border-radius" padding="6px 10px" size="12px" style="font-size: 8px" unelevated dense icon="done" color="primary" label="更多" />
            </div>
          </div>
        </q-item>
        <q-separator v-if="n !== 50" spaced="10px" inset="" />
      </div>
    </q-scroll-area>

    <q-dialog v-model="addTask">
      <q-card style="width: 600px" square class="q-pb-md">
        <q-card-section class="row items-center">
          <div class="text-h6">任务添加</div>
          <q-space />
          <q-btn icon="close" flat round dense v-close-popup />
        </q-card-section>
        <form
          ref="addDataForm"
          @submit.prevent.stop="onSubmit"
          @reset.prevent.stop="onReset"
        >
          <q-card-section class="row justify-center q-mt-sm">
            <div class="col-sm-9 col-xs-11 q-gutter-md">
              <q-input
                class="q-pb-none"
                ref="taskName"
                label="任务名称"
                placeholder="请输入"
                outlined
                v-model="taskName"
                dense
                square
                clearable
                :rules="[(val) => (val && val.length > 0) || '请输入任务名称']"
              >
              </q-input>
              <q-input
                outlined
                v-model="queryDate"
                label="上次调度时间"
                dense
                square
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy
                      transition-show="scale"
                      transition-hide="scale"
                    >
                      <q-date v-model="queryDate" mask="YYYY-MM-DD HH:mm">
                        <div class="row items-center justify-end">
                          <q-btn
                            v-close-popup
                            label="Close"
                            color="primary"
                            flat
                          />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
                <template v-slot:append>
                  <q-icon
                    v-if="queryDate !== ''"
                    name="cancel"
                    @click="queryDate = ''"
                    class="cursor-pointer"
                  />
                  <q-icon name="access_time" class="cursor-pointer">
                    <q-popup-proxy
                      transition-show="scale"
                      transition-hide="scale"
                    >
                      <q-time
                        v-model="queryDate"
                        mask="YYYY-MM-DD HH:mm"
                        format24h
                      >
                        <div class="row items-center justify-end">
                          <q-btn
                            v-close-popup
                            label="Close"
                            color="primary"
                            flat
                          />
                        </div>
                      </q-time>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
              <q-input
                class="q-pb-none"
                ref="ruleName"
                label="任务负责人"
                placeholder="请输入"
                outlined
                v-model="taskName"
                dense
                square
                clearable
              >
              </q-input>
              <q-input
                label="产品描述"
                type="textarea"
                outlined
                placeholder="请输入最少五个字符"
                v-model="ruleName"
                square
              >
              </q-input>
              <div class="text-right">
                <q-btn
                  outline
                  unelevated
                  label="取消"
                  v-close-popup
                  type="reset"
                  class="q-mr-sm no-border-radius"
                  color="grey-6"
                />
                <q-btn
                  unelevated
                  type="submit"
                  label="保存"
                  color="primary"
                  class="no-border-radius"
                />
              </div>
            </div>
          </q-card-section>
        </form>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { QSpinnerIos } from 'quasar'

export default {
  name: 'BasicList',
  data() {
    return {
      stepValue: 0.6,
      queryDate: new Date(),
      addTask: false,
      visible: false,
      model: 'all',
      taskName: '',
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
    },
    onReset() {
      this.taskName = null
    },
    addTaskDialog() {
      this.addTask = true
    },
    onSubmit() {
      if (!this.$refs.taskName.validate()) {
        return
      }
      const spinner = QSpinnerIos
      this.$q.loading.show({
        spinner,
        spinnerColor: 'blue',
        spinnerSize: '40px',
        backgroundColor: 'white',
        message: '添加任务...',
        messageColor: 'blue'
      })

      setTimeout(() => {
        this.$q.loading.hide()
        this.$refs.addDataForm.reset()
        this.addTask = false
        this.$q.notify({
          color: 'white',
          textColor: 'positive',
          icon: 'check_circle',
          position: 'top',
          message: '添加成功'
        })
      }, 2000)
    }
  }
}
</script>

<style>
.q-field--dense .q-field__control, .q-field--dense .q-field__marginal {
  height: 32px;
}
</style>
