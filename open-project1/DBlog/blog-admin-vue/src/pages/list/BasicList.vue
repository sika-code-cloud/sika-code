<template>
  <div class="sc-base-list sc-design">
    <div class="bg-white text-h6 q-pa-md">
      <strong>标准列表</strong>
    </div>
    <div class="q-px-md">
      <q-card square flat class="q-gutter-y-md q-mt-md">
        <div class="row justify-center">
          <q-card-section class="col-sm col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >我的待办
            </q-item-label>
            <q-item-label class="text-h5 text-center">{{ basicListData.summaryData.waitDealTask }}个任务</q-item-label>
          </q-card-section>
          <q-separator vertical />
          <q-card-section class="col-sm col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周任务平均处理时间
            </q-item-label>
            <q-item-label class="text-h5 text-center">{{ basicListData.summaryData.weekDealDuration }}分钟</q-item-label>
          </q-card-section>
          <q-separator vertical />
          <q-card-section class="col-sm col-xs-12">
            <q-item-label class="text-body text-grey-6 text-center q-mb-sm"
            >本周完成任务数
            </q-item-label>
            <q-item-label class="text-h5 text-center">{{ basicListData.summaryData.completeTask }}个任务</q-item-label>
          </q-card-section>
        </div>
      </q-card>
      <q-card square flat class="q-gutter-y-md q-mt-md sc-base-list-query">
        <div class="row items-center q-px-md">
          <q-item-label class="text-body1 col-sm-3 col-xs-4">
            基本列表
          </q-item-label>
          <q-card-section class="col-sm-9 col-xs-8 q-px-none">
            <div class="row q-gutter-y-sm q-gutter-x-md justify-end">
              <div class="col-md-5 col-xs-12" style="max-width: 280px">
                <q-btn-toggle
                  style="height: 36px"
                  spread
                  class="no-border-radius bg-light-blue-1"
                  dense
                  v-model="listQueryData.matterState"
                  unelevated
                  toggle-color="primary"
                  @click="queryData"
                  :options="[
                    { label: '全部', value: '全部' },
                    { label: '待处理', value: '待处理' },
                    { label: '已完成', value: '已完成' }
                  ]"
                />
              </div>
              <div class="col-md-3 col-xs-12" style="max-width: 280px">
                <q-input
                  ref="ruleName"
                  placeholder="请输入事项名称"
                  outlined
                  v-model="listQueryData.matter"
                  dense
                  @keydown.enter="queryData"
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
                      @click="queryData"
                    ></q-btn>
                  </template>
                </q-input>
              </div>
              <div class="col-md-auto col-xs-12 q-pb-sm col-sm gt-sm">
                <q-btn
                  :ripple="false"
                  dense
                  flat
                  style="border: 1px darkgrey dashed; height: 36px"
                  unelevated
                  icon="add"
                  class="no-border-radius q-px-sm"
                  label="添加"
                  @click="addTaskDialog"
                />
              </div>
            </div>
          </q-card-section>
        </div>
        <div class="q-mt-none q-pb-md q-px-md lt-md">
          <q-btn
            :ripple="false"
            padding="xs"
            flat
            style="border: 1px darkgrey dashed; height: 36px"
            unelevated
            icon="add"
            class="full-width no-border-radius"
            label="添加"
            @click="addTaskDialog"
          />
        </div>
      </q-card>

      <div class="bg-white q-mt-md q-pb-md q-pt-md">
        <sc-page :items="filterListData" :per-number="10">
          <template v-slot:item="props">
            <q-item transition-show="flip-up">
              <q-item-section avatar top>
                <q-avatar square>
                  <img alt="" :src="props.item.imgSrc" />
                </q-avatar>
              </q-item-section>
              <div class="row items-center q-col-gutter-y-sm full-width">
                <div class="q-pt-xs col-xs-12">
                  <q-item-label lines="1" class="items-center row">
                    <span class="text-h6">{{ props.item.matter }}</span>
                    <span class="q-ml-xs">
                      <q-chip size="sm" v-if="props.item.state === '待处理'" outline color="negative"
                              class="bg-red-1 no-border-radius" square>
                        {{ props.item.state }}
                      </q-chip>
                      <q-chip size="sm" v-if="props.item.state === '已完成'" outline color="positive"
                              class="bg-green-1 no-border-radius" square>
                        {{ props.item.state }}
                      </q-chip>
                    </span>
                  </q-item-label>
                  <q-item-label class="q-mr-md text-body text-grey-7" lines="2">
                    {{ props.item.desc }}
                  </q-item-label>
                </div>
                <div class="col-sm-2 col-xs-6 text-body text-grey-7">
                  <q-item-label class="q-mb-xs"> Owner</q-item-label>
                  <q-item-label>
                    {{ props.item.name }}
                  </q-item-label>
                </div>
                <div class="col-sm-3 col-xs-6 text-body text-grey-7">
                  <q-item-label class="q-mb-xs"> 开始时间</q-item-label>
                  <q-item-label>
                    {{ props.item.startTime }}
                  </q-item-label>
                </div>
                <div class="col-md-2 col-sm-3 col-xs-10 q-pr-md">
                  <q-linear-progress
                    rounded
                    stripe
                    size="md"
                    :value="props.item.progress"
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
                    @click="editItem(props.item)"
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
                      <q-item
                        clickable
                        v-close-popup
                        @click="editItem(props.item)"
                      >
                        <q-item-section>
                          <q-item-label>编辑</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item
                        clickable
                        v-close-popup
                        @click="deleteItem(props.item)"
                      >
                        <q-item-section>
                          <q-item-label>删除</q-item-label>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-btn-dropdown>
                </div>
              </div>
            </q-item>
            <q-separator v-if="props.item.id !== 49" spaced="10px" inset="" />
          </template>
        </sc-page>
      </div>

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
                  v-model="waitEdit.matter"
                  dense
                  square
                  clearable
                  :rules="[
                    (val) => (val && val.length > 0) || '请输入任务名称'
                  ]"
                >
                </q-input>
                <q-input
                  ref="beginTime"
                  outlined
                  v-model="waitEdit.startTime"
                  label="上次调度时间"
                  dense
                  square
                >
                  <template v-slot:prepend>
                    <q-icon name="event" class="cursor-pointer">
                      <q-menu
                        square
                        :offset="[12, 10]"
                        transition-show="jump-down"
                        transition-hide="jump-up"
                      >
                        <q-date
                          today-btn
                          v-model="waitEdit.startTime"
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
                      </q-menu>
                    </q-icon>
                  </template>
                  <template v-slot:append>
                    <q-icon
                      v-if="waitEdit.startTime !== ''"
                      name="cancel"
                      @click="waitEdit.startTime = ''"
                      class="cursor-pointer"
                    />
                    <q-icon name="access_time" class="cursor-pointer">
                      <q-menu
                        square
                        :offset="[12, 10]"
                        transition-show="jump-down"
                        transition-hide="jump-up"
                      >
                        <q-time
                          v-model="waitEdit.startTime"
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
                      </q-menu>
                    </q-icon>
                  </template>
                </q-input>
                <q-input
                  class="q-pb-none"
                  ref="owner"
                  label="任务负责人"
                  placeholder="请输入"
                  outlined
                  v-model="waitEdit.name"
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
import { QSpinnerIos } from 'quasar'
import ScPage from 'components/common/ScPage'
import BASIC_LIST_DATA from '@/mock/data/list/basicListData'
import _ from 'lodash'
import Vue from 'vue'
import commonUtil from 'src/utils/commonUtil'

export default {
  name: 'BasicList',
  data() {
    return {
      basicListData: BASIC_LIST_DATA,
      listQueryData: {
        matterState: '全部',
        matter: null
      },
      addTask: false,
      filterListData: [],
      waitEdit: {}
    }
  },
  computed: {
  },
  methods: {
    queryData() {
      this.filterListData = []
      for (let i = 0; i < this.basicListData.basicListDatas.length; ++i) {
        const data = this.basicListData.basicListDatas[i]
        if (this.isMatchData(data)) {
          this.filterListData.push(data)
        }
      }
    },
    isMatchData(data) {
      const listQueryData = this.listQueryData
      let matterFlag = false
      if (!listQueryData.matter || data.matter.search(listQueryData.matter) !== -1) {
        matterFlag = true
      }
      let matterStateFlag = false
      if (listQueryData.matterState === '全部' || data.state === listQueryData.matterState) {
        matterStateFlag = true
      }
      return matterFlag && matterStateFlag
    },
    onReset() {
      this.waitEdit.matter = null
      this.waitEdit.name = null
      this.waitEdit.startTime = null
      this.waitEdit.desc = null
      this.waitEdit.id = null
      this.waitEdit.state = null
    },
    addTaskDialog() {
      this.waitEdit = {}
      this.addTask = true
    },
    editItem(item) {
      console.log(JSON.stringify(item))
      this.addTask = true
      this.waitEdit = _.clone(item)
    },
    deleteItem(item) {
      for (let i = 0; i < this.basicListData.basicListDatas.length; ++i) {
        if (this.basicListData.basicListDatas[i].id === item.id) {
          this.basicListData.basicListDatas.splice(i, 1)
        }
      }
    },
    onSubmit() {
      if (!this.$refs.title.validate()) {
        return
      }
      const addModel = _.clone(this.waitEdit)
      addModel.progress = Math.random()
      addModel.imgSrc = commonUtil.getRandomData(this.basicListData.imgSrcs)
      const spinner = QSpinnerIos
      this.$q.loading.show({
        spinner,
        spinnerColor: 'blue',
        spinnerSize: '40px',
        backgroundColor: 'white',
        message: '添加任务...',
        messageColor: 'blue'
      })

      const basicListDatas = this.basicListData.basicListDatas
      setTimeout(() => {
        let message = '添加成功'
        if (addModel.id !== undefined) {
          message = '编辑成功'
          for (let i = 0; i < basicListDatas.length; ++i) {
            if (basicListDatas[i].id === addModel.id) {
              Vue.set(this.basicListData.basicListDatas, i, addModel)
            }
          }
        } else {
          addModel.id = basicListDatas.length + 1
          addModel.state = '待处理'
          basicListDatas.unshift(addModel)
        }
        this.$forceUpdate()
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
  },
  components: {
    ScPage
  },
  mounted() {
    this.filterListData = _.cloneDeep(BASIC_LIST_DATA.basicListDatas)
  },
  watch: {
    basicListData: {
      handler(newName, oldName) {
        this.queryData()
      },
      immediate: true,
      deep: true
    }
  }
}
</script>

<style>
</style>
