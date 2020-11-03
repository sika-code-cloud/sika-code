<template>
  <div class="sc-base-list">
    <div class="bg-white text-h6 q-pa-md"><strong>标准列表</strong></div>
    <div class="q-px-md">
      <q-card square flat class="q-gutter-y-md q-mt-lg">
        <div class="row justify-center">
          <q-card-section class="col-sm-3 col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >我的待办
            </q-item-label
            >
            <q-item-label class="text-h5 text-center">8个任务</q-item-label>
          </q-card-section>
          <q-separator vertical />
          <q-card-section class="col-sm-4 col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周任务平均处理时间
            </q-item-label
            >
            <q-item-label class="text-h5 text-center">32分钟</q-item-label>
          </q-card-section>
          <q-separator vertical />
          <q-card-section class="col-sm-3 col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周完成任务数
            </q-item-label
            >
            <q-item-label class="text-h5 text-center">24个任务</q-item-label>
          </q-card-section>
        </div>
      </q-card>
      <q-card square flat class="q-gutter-y-md q-mt-lg sc-base-list-query">
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
        <div
          v-for="obj in baseListObjs"
          :key="obj.id"
          v-intersection="onIntersection"
        >
          <q-item transition-show="flip-up">
            <q-item-section avatar top>
              <q-avatar square>
                <img alt="" :src="obj.imgSrc" />
              </q-avatar>
            </q-item-section>
            <div class="row items-center q-col-gutter-y-sm full-width">
              <div class="q-pt-xs col-md-4 col-xs-12">
                <q-item-label lines="1">
                  <span class="text-h6">{{ obj.title }}</span>
                </q-item-label>
                <q-item-label class="q-mr-md text-body text-grey-7" lines="2">
                  {{ obj.desc }}
                </q-item-label>
              </div>
              <div class="col-md-2 col-sm-2 col-xs-6 text-body text-grey-7">
                <q-item-label class="q-mb-xs"> Owner</q-item-label>
                <q-item-label>
                  {{ obj.owner }}
                </q-item-label>
              </div>
              <div class="col-md-2 col-sm-3 col-xs-6 text-body text-grey-7">
                <q-item-label class="q-mb-xs"> 开始时间</q-item-label>
                <q-item-label>
                  {{ obj.beginTime }}
                </q-item-label>
              </div>
              <div class="col-md-2 col-sm-3 col-xs-10 q-pr-md">
                <q-linear-progress
                  rounded
                  stripe
                  size="md"
                  :value="obj.progress"
                />
              </div>
              <div class="col-md-2 col-sm-4 col-xs-12 q-gutter-sm">
                <q-btn
                  class="no-border-radius"
                  padding="5px 10px"
                  size="12px"
                  unelevated
                  dense
                  icon="edit"
                  color="primary"
                  label="编辑"
                  @click="editItem(obj)"
                />
                <q-btn-dropdown
                  unelevated
                  size="12px"
                  class="no-border-radius"
                  padding="5px 9px"
                  color="primary"
                  dense
                  label="更多"
                >
                  <q-list dense>
                    <q-item clickable v-close-popup @click="editItem(obj)">
                      <q-item-section>
                        <q-item-label>编辑</q-item-label>
                      </q-item-section>
                    </q-item>

                    <q-item clickable v-close-popup @click="deleteItem(obj)">
                      <q-item-section>
                        <q-item-label>删除</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
            </div>
          </q-item>
          <q-separator v-if="obj.id !== 49" spaced="10px" inset="" />
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
                  ref="title"
                  label="任务名称"
                  placeholder="请输入"
                  outlined
                  v-model="waitEdit.title"
                  dense
                  square
                  clearable
                  :rules="[(val) => (val && val.length > 0) || '请输入任务名称']"
                >
                </q-input>
                <q-input
                  ref="beginTime"
                  outlined
                  v-model="waitEdit.beginTime"
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
                        <q-date
                          v-model="waitEdit.beginTime"
                          mask="YYYY-MM-DD HH:mm"
                        >
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
                      v-if="waitEdit.beginTime !== ''"
                      name="cancel"
                      @click="waitEdit.beginTime = ''"
                      class="cursor-pointer"
                    />
                    <q-icon name="access_time" class="cursor-pointer">
                      <q-popup-proxy
                        transition-show="scale"
                        transition-hide="scale"
                      >
                        <q-time
                          v-model="waitEdit.beginTime"
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
                  ref="owner"
                  label="任务负责人"
                  placeholder="请输入"
                  outlined
                  v-model="waitEdit.owner"
                  dense
                  square
                  clearable
                >
                </q-input>
                <q-input
                  label="产品描述"
                  type="textarea"
                  ref="desc"
                  outlined
                  placeholder="请输入最少五个字符"
                  v-model="waitEdit.desc"
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
  </div>
</template>

<script>
import { date, QSpinnerIos } from 'quasar'
import _ from 'lodash'

const baseListObj = {
  title: 'Alipay',
  desc:
    '那是一种内在的东西，他们到达不了，也无法触及的，那是一种内在的东西，他们到达不了，也无法触及的 那是一种内在的东西，他们到达不了，也无法触及的，那是一种内在的东西，他们到达不了，也无法触及的',
  owner: '付小小',
  beginTime: date.formatDate(new Date(), 'YYYY-MM-DD HH:MM'),
  progress: 1
}
const imgSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png',
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png'
]
const baseListObjsDefault = []
for (let i = 0; i < 50; ++i) {
  const obj = _.clone(baseListObj)
  obj.id = i
  obj.title = obj.title + i
  obj.owner = obj.owner + i
  obj.progress = Math.random()
  obj.imgSrc = imgSrcs[i % 5]
  baseListObjsDefault.push(obj)
}

export default {
  name: 'BasicList',
  data() {
    return {
      baseListObjs: baseListObjsDefault,
      stepValue: 0.6,
      queryDate: date.formatDate(new Date(), 'YYYY-MM-DD HH:MM'),
      addTask: false,
      visible: false,
      model: 'all',
      title: '',
      beginTime: '',
      owner: '',
      desc: '',
      ruleName: '',
      waitEdit: {}
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
      this.waitEdit.title = null
      this.waitEdit.owner = null
      this.waitEdit.beginTime = null
      this.waitEdit.desc = null
    },
    addTaskDialog() {
      this.addTask = true
    },
    editItem(item) {
      console.log(JSON.stringify(item))
      this.addTask = true
      this.waitEdit = _.clone(item)
    },
    deleteItem(item) {
      for (let i = 0; i < this.baseListObjs.length; ++i) {
        if (this.baseListObjs[i].id === item.id) {
          this.baseListObjs.splice(i, 1)
        }
      }
    },
    onSubmit() {
      if (!this.$refs.title.validate()) {
        return
      }
      const addModel = _.clone(this.waitEdit)
      addModel.progress = Math.random()
      addModel.imgSrc = imgSrcs[1]
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
        let message = '添加成功'
        if (addModel.id !== undefined) {
          message = '编辑成功'
          for (let i = 0; i < this.baseListObjs.length; ++i) {
            if (this.baseListObjs[i].id === addModel.id) {
              this.baseListObjs[i] = addModel
            }
          }
        } else {
          addModel.id = this.baseListObjs.length + 1
          this.baseListObjs.unshift(addModel)
        }
        this.$q.loading.hide()
        this.$refs.addDataForm.reset()
        this.addTask = false
        this.$q.notify({
          color: 'white',
          textColor: 'positive',
          icon: 'check_circle',
          position: 'top',
          message: message
        })
      }, 2000)
    }
  }
}
</script>

<style>
.sc-base-list-query .q-field--dense .q-field__control,
.sc-base-list-query .q-field--dense .q-field__marginal {
  height: 32px;
}
</style>
