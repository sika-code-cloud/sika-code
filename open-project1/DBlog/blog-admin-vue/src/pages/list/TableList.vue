<template>
  <div class="sc-design">
    <div class="bg-white text-h6 q-pa-md">
      <strong>查询表格</strong>
    </div>
    <div class="q-pt-md q-mx-md">
      <q-card square flat class="q-gutter-y-md q-pb-md">
        <q-form>
          <div class="row q-gutter-y-sm q-pt-sm">
            <q-item class="col-xl-3 col-sm-6 col-xs-12">
              <q-item-section class="col-2 text-right gt-sm">
                <q-item-label>规则名称:</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-input
                  outlined
                  v-model="queryCondition.ruleName"
                  label="规则名称"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-section>
            </q-item>
            <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
              <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                <q-item-label>描述:</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-input
                  outlined
                  v-model="queryCondition.desc"
                  label="描述"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-section>
            </q-item>
            <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
              <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                调用次数:
              </q-item-section>
              <q-item-section v-show="showQuery" class="col">
                <q-input
                  outlined
                  v-model="queryCondition.callCount"
                  label="服务调用次数"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-section>
            </q-item>
            <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
              <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                <q-item-label>状态:</q-item-label>
              </q-item-section>
              <q-item-section v-show="showQuery" class="col">
                <q-select
                  behavior="menu"
                  outlined
                  options-dense
                  v-model="queryCondition.state"
                  :options="tableListData.stateValue"
                  label="状态"
                  dense
                  square
                  clearable
                >
                </q-select>
              </q-item-section>
            </q-item>
            <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
              <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                <q-item-label>时间:</q-item-label>
              </q-item-section>
              <q-item-section v-show="showQuery" class="col">
                <q-input
                  outlined
                  v-model="queryCondition.callNextTime"
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
                        <q-date v-model="queryDate" mask="YYYY-MM-DD HH:mm" today-btn>
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
                      v-if="queryDate !== ''"
                      name="cancel"
                      @click="queryDate = ''"
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
                      </q-menu>
                    </q-icon>
                  </template>
                </q-input>
              </q-item-section>
            </q-item>
            <q-item class="col-xl-3 col-sm-6 col-xs-12 q-pr-sm">
              <q-item-label class="col-12 text-right">
                <q-btn
                  outline
                  unelevated
                  label="重置"
                  class="q-mr-sm no-border-radius"
                  color="secondary"
                  @click="resetQuery"
                />
                <q-btn
                  unelevated
                  label="查询"
                  color="primary"
                  class="q-mr-sm no-border-radius"
                  :loading="queryLoad"
                  :style="queryBtnStyle"
                  @click="doQuery"
                >
                  <template v-slot:loading>
                    <q-spinner-ios class="on-center" />
                  </template>
                </q-btn>
                <q-btn-dropdown
                  v-model="showQuery"
                  persistent
                  dense
                  flat
                  color="primary"
                  :label="tableLabel"
                  @before-show="show"
                  @before-hide="hide"
                >
                </q-btn-dropdown>
              </q-item-label>
            </q-item>
          </div>
        </q-form>
        <q-card class="row q-mt-lg">
          <div class="col">
            <q-table
              class="my-sticky-header-table"
              square
              flat
              title-class="text-body1"
              color="primary"
              :data="filterListData"
              :columns="tableListData.tableListDatas.columns"
              :visible-columns="visibleColumns"
              row-key="id"
              :selected-rows-label="getSelectedString"
              selection="multiple"
              :selected.sync="selected"
              :pagination.sync="pagination"
              hide-selected-banner
              virtual-scroll
              :loading="loading"
            >
              <template v-slot:top-left>
                <div>
                  <q-btn
                    label="新建"
                    color="primary"
                    class="q-mr-sm no-border-radius"
                    icon="add"
                    unelevated
                    @click="addRow"
                  />
                </div>
              </template>
              <template v-slot:top-right="props">
                <div>
                  <q-btn rounded flat dense size="md" icon="refresh">
                    <q-tooltip>刷新</q-tooltip>
                  </q-btn>
                  <q-btn rounded flat dense size="md" icon="unfold_less">
                    <q-tooltip>密度</q-tooltip>
                  </q-btn>
                  <q-btn rounded flat dense size="md" icon="settings">
                    <q-menu :offset="[0, 12]">
                      <q-list dense>
                        <q-item
                          clickable
                          :active="column.check"
                          @click="select(column)"
                          v-bind:key="column.id"
                          v-for="column in columns"
                        >
                          <q-item-section>{{ column.label }}</q-item-section>
                        </q-item>
                      </q-list>
                    </q-menu>
                  </q-btn>
                  <q-btn
                    rounded
                    flat
                    round
                    dense
                    :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'"
                    @click="props.toggleFullscreen"
                  >
                    <q-tooltip>全屏</q-tooltip>
                  </q-btn>
                </div>
              </template>
              <template v-slot:pagination="scope">
                <div class="full-width">
                  <q-pagination
                    v-model="pagination.page"
                    color="primary"
                    :max="scope.pagesNumber"
                    size="sm"
                    :max-pages="4"
                    :boundary-numbers="false"
                    :boundary-links="true"
                    class="float-right"
                  />
                </div>
              </template>
            </q-table>

            <q-inner-loading :showing="queryLoad">
              <q-spinner-ios size="40px" color="primary" />
            </q-inner-loading>
          </div>
        </q-card>
        <q-dialog v-model="addData">
          <q-card square style="width: 600px">
            <q-toolbar>
              <q-icon name="post_add" size="md"></q-icon>
              <q-toolbar-title class="text-body1">新建规则</q-toolbar-title>
              <q-btn flat round dense icon="close" v-close-popup />
            </q-toolbar>
            <div class="q-px-md">
              <form
                ref="addDataForm"
                @submit.prevent.stop="onSubmit"
                @reset.prevent.stop="onReset"
              >
                <div class="q-gutter-sm q-ma-sm">
                  <q-item-label>
                    <span class="q-mr-xs text-red">*</span>规则名称
                  </q-item-label>
                  <q-input
                    ref="ruleName"
                    placeholder="请输入"
                    outlined
                    v-model="ruleName"
                    dense
                    square
                    :rules="[
                    (val) => (val && val.length > 0) || '请输入规则名称'
                  ]"
                    clearable
                  >
                  </q-input>
                </div>
                <div class="q-gutter-sm q-ma-sm">
                  <q-item-label>描述</q-item-label>
                  <q-input
                    type="textarea"
                    outlined
                    placeholder="请输入"
                    v-model="ruleName"
                    square
                  >
                  </q-input>
                </div>
                <div class="q-gutter-sm q-ma-sm q-mb-lg">
                  <q-btn
                    outline
                    unelevated
                    label="重置"
                    type="reset"
                    class="q-mr-sm no-border-radius"
                    color="secondary"
                  />
                  <q-btn
                    unelevated
                    type="submit"
                    label="提交"
                    color="primary"
                    class="no-border-radius"
                  />
                </div>
              </form>
            </div>
          </q-card>
        </q-dialog>
        <q-dialog v-model="seamless" full-width seamless position="bottom">
          <q-card class="q-mx-sm">
            <q-card-section class="row">
              <div class="col-sm-6 col-xs-12 q-mb-sm">
                <div>
                  已选择<span class="text-weight-bold text-blue-8 q-mx-xs"
                >{{ selected.length }} </span
                >项 服务调用次数总计<span class="q-mx-xs">{{ 567 }} </span>万
                </div>
              </div>
              <div class="text-right col-sm-6 col-xs-12 q-gutter-x-sm">
                <q-btn
                  unelevated
                  color="warning"
                  label="批量删除"
                  @click="deleteDatas"
                />
                <q-btn unelevated color="primary" label="批量审批" />
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>
      </q-card>
    </div>
  </div>
</template>

<script>
import { date, QSpinnerIos } from 'quasar'
import _ from 'lodash'
import TABLE_LIST_DATA from '@/mock/data/list/tableListData'

export default {
  name: 'TableList',
  data() {
    return {
      tableListData: TABLE_LIST_DATA,
      filterListData: [],
      queryCondition: TABLE_LIST_DATA.queryCondition,
      queryBtnStyle: {},
      queryLoad: false,
      addData: false,
      queryDate: date.formatDate(Date.now(), 'YYYY-MM-DD HH:mm'),
      showQuery: true,
      queryStatus: '',
      visible: false,
      seamless: false,
      tableShow: true,
      loading: false,
      tableLabel: '展开',
      ruleName: '',
      selected: [],
      pagination: {
        // sortBy: 'calories',
        descending: false,
        page: 1,
        rowsPerPage: 5
        // rowsNumber: xx if getting data from a server
      },
      visibleColumns: [
        'ruleName',
        'state',
        'callCount',
        'callNextTime',
        'desc'
      ],
      columns: [
        {
          check: true,
          name: 'desc',
          required: true,
          label: 'Dessert(100g serving)',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`,
          sortable: true
        },
        {
          check: true,
          name: 'calories',
          align: 'center',
          label: 'Calories',
          field: 'calories',
          sortable: true
        },
        {
          check: true,
          name: 'fat',
          label: 'Fat (g)',
          field: 'fat',
          sortable: true
        },
        {
          check: true,
          name: 'carbs',
          label: 'Carbs (g)',
          field: 'carbs'
        },
        {
          check: true,
          name: 'protein',
          label: 'Protein (g)',
          field: 'protein'
        },
        {
          check: true,
          name: 'sodium',
          label: 'Sodium (mg)',
          field: 'sodium'
        },
        {
          check: true,
          name: 'calcium',
          label: 'Calcium (%)',
          field: 'calcium',
          sortable: true,
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        },
        {
          check: true,
          name: 'iron',
          label: 'Iron (%)',
          field: 'iron',
          sortable: true,
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        }
      ],
      data: [
        {
          id: 1,
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: '14%',
          iron: '1%'
        },
        {
          id: 2,
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          sodium: 129,
          calcium: '8%',
          iron: '1%'
        },
        {
          id: 3,
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          sodium: 337,
          calcium: '6%',
          iron: '7%'
        },
        {
          id: 4,
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: '3%',
          iron: '8%'
        },
        {
          id: 5,
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          sodium: 327,
          calcium: '7%',
          iron: '16%'
        },
        {
          id: 6,
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          sodium: 50,
          calcium: '0%',
          iron: '0%'
        },
        {
          id: 7,
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          sodium: 38,
          calcium: '0%',
          iron: '2%'
        },
        {
          id: 8,
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          sodium: 562,
          calcium: '0%',
          iron: '45%'
        },
        {
          id: 9,
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          sodium: 326,
          calcium: '2%',
          iron: '22%'
        },
        {
          id: 10,
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          sodium: 54,
          calcium: '12%',
          iron: '6%'
        }
      ]
    }
  },
  methods: {
    show(evt) {
      this.showQuery = true
      this.tableLabel = '收起'
    },
    hide(evt) {
      this.showQuery = false
      this.tableLabel = '展开'
    },
    getSelectedString() {
      if (this.selected.length === 0) {
        return ''
      } else {
        return '选择：' + `${this.selected.length} / ${this.data.length}`
      }
    },
    deleteDatas() {
      const spinner = QSpinnerIos
      this.$q.loading.show({
        spinner,
        spinnerSize: '40px',
        spinnerColor: 'blue',
        backgroundColor: 'white',
        message: '正在删除...',
        messageColor: 'blue'
      })

      setTimeout(() => {
        this.$q.loading.hide()
        this.$q.notify({
          color: 'white',
          textColor: 'positive',
          icon: 'check_circle',
          position: 'top',
          message: '删除成功，即将刷新'
        })
      }, 2000)
    },
    select(columnFromClient) {
      this.visibleColumns = []
      const columns = this.columns
      for (let i = 0; i < columns.length; ++i) {
        if (columns[i].name === columnFromClient.name) {
          columns[i].check = !columns[i].check
        }
        if (columns[i].check) {
          this.visibleColumns[i] = columns[i].name
        }
      }
    },
    onSubmit() {
      if (!this.$refs.ruleName.validate()) {
        return
      }
      const spinner = QSpinnerIos
      this.$q.loading.show({
        spinner,
        spinnerColor: 'blue',
        spinnerSize: '40px',
        backgroundColor: 'white',
        message: '添加规则...',
        messageColor: 'blue'
      })

      setTimeout(() => {
        this.$q.loading.hide()
        this.addData = false
        this.$refs.addDataForm.reset()
        this.$q.notify({
          color: 'white',
          textColor: 'positive',
          icon: 'check_circle',
          position: 'top',
          message: '添加成功'
        })
      }, 2000)
    },

    onReset() {
      this.ruleName = null
    },
    resetQuery() {
      this.queryCondition = {}
    },
    doQuery() {
      this.queryLoad = true
      setTimeout(() => {
        this.queryLoad = false
        this.filterListData = []
        const datas = this.tableListData.tableListDatas.datas
        for (let i = 0; i < datas.length; ++i) {
          const data = datas[i]
          if (this.isMatchData(data)) {
            this.filterListData.push(data)
          }
        }
      }, 2000)
    },
    isMatchData(data) {
      const listQueryData = this.queryCondition
      let ruleNameFlag = false
      console.log(ruleNameFlag)
      if (!listQueryData.ruleName || data.ruleName.search(listQueryData.ruleName) !== -1) {
        ruleNameFlag = true
      }
      let descFlag = false
      if (!listQueryData.desc || data.desc.search(listQueryData.desc) !== -1) {
        descFlag = true
      }
      let stateFlag = false
      if (!listQueryData.state || data.state === listQueryData.state) {
        stateFlag = true
      }
      return ruleNameFlag && descFlag && stateFlag
    },
    addRow() {
      this.addData = true
      // this.loading = true
      // setTimeout(() => {
      //   // const index = Math.floor(Math.random() * (this.data.length + 1))
      //   const row = this.data[Math.floor(Math.random() * this.data.length)]
      //   if (this.data.length === 0) {
      //     this.rowCount = 0
      //   }
      //   const addRow = { ...row } // extend({}, row, { name: `${row.name} (${row.__count})` })
      //   this.data.unshift(addRow)
      //   row.id = this.data.length
      //   this.loading = false
      // }, 500)
    }
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.data.length / this.pagination.rowsPerPage)
    }
  },
  mounted() {
    this.filterListData = _.cloneDeep(TABLE_LIST_DATA.tableListDatas.datas)
    this.showQuery = this.$q.screen.gt.xs
    this.tableLabel = this.$q.screen.gt.xs ? '收起' : '展开'
  },
  watch: {
    selected(newSelected, oldSelected) {
      this.seamless = newSelected.length > 0
    }
  }
}
</script>

<style lang="sass">
.my-sticky-header-table
  /* height or max-height is important */
  max-height: 600px

  .q-table__top,
  .q-table__bottom
    /* bg color is important for th; just specify one */
    background-color: white

  thead tr:first-child th
    font-size: medium
    font-weight: bolder
    background-color: white

  thead tr th
    position: sticky
    z-index: 1

  thead tr:first-child th
    top: 0
</style>
