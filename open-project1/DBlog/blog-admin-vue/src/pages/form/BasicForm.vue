<template>
  <div>
    <div class="bg-white q-pa-md" style="margin: -16px -16px 0 -16px">
      <div class="text-h6">
        <strong>基础表单</strong>
      </div>
      <div class="text-body2">
        表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。
      </div>
    </div>
    <q-card
      square
      flat
      class="q-gutter-y-md q-my-md"
      :class="{ 'q-mt-md': $q.screen.gt.xs }"
    >
      <q-form class="q-px-md full-width">
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >标题:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="给目标起个名字"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >起止日期:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                v-model="dateStr"
                placeholder="起止时间"
                mask="####-##-## -> ####-##-##"
                hint="YYYY-MM-DD -> YYYY-MM-DD"
                dense
                square
              >
                <template v-slot:append>
                  <q-icon name="event" class="cursor-pointer">
                    <q-menu
                      square
                      :offset="[12, 10]"
                      transition-show="jump-down"
                      transition-hide="jump-up"
                    >
                      <q-date
                        v-model="beginAndEndDate"
                        square
                        mask="YYYY-MM-DD"
                        range
                      />
                    </q-menu>
                  </q-icon>
                </template>
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-start">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >目标描述:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                type="textarea"
                v-model="ruleName"
                placeholder="请输入你的阶段性工作目标"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-start">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >衡量标准:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                type="textarea"
                v-model="ruleName"
                placeholder="请输入衡量标准"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
            >
              <span>客户</span>
              <span class="text-grey-6">
                （选填）<q-icon name="error_outline" />
              </span>
              <span>:</span>
            </q-item-label>
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="请描述你服务的客户，内部客户直接 @姓名／工号"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >邀评人<span class="text-grey-6">（选填）</span>:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="请直接 @姓名／工号，最多可邀请 5 人"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >权重<span class="text-grey-6">（选填）</span>:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-input
                style="width: 60%; max-width: 280px"
                type="number"
                outlined
                v-model="ruleName"
                placeholder="请输入"
                dense
                square
              >
                <template v-slot:after>
                  <span class="text-body1">%</span>
                </template>
              </q-input>
            </q-item-label>
          </span>
        </div>
        <div class="row q-gutter-y-sm q-my-md items-center">
          <span
            class="col-sm-4 col-xs-12"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md"
              :class="{ 'text-right': $q.screen.gt.xs }"
              >目标公开:</q-item-label
            >
          </span>
          <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label>
              <q-option-group
                size="sm"
                v-model="group"
                :options="options"
                color="primary"
                inline
              />
            </q-item-label>
          </span>
          <span class="offset-sm-4 col-xl-4 col-md-5 col-sm-6 col-xs-12">
            <q-item-label v-show="group === 'op2'" class="q-mb-md">
              <q-input
                outlined
                v-model="ruleName"
                placeholder="公开给"
                dense
                square
              >
              </q-input>
            </q-item-label>
            <q-item-label class="text-grey-6">
              客户、邀评人默认被分享
            </q-item-label>
          </span>
        </div>
      </q-form>
    </q-card>
  </div>
</template>

<script>
import { date, QSpinnerIos } from 'quasar'

export default {
  name: 'BasicForm',
  data() {
    return {
      group: 'op1',
      options: [
        {
          label: '公开',
          value: 'op1'
        },
        {
          label: '部分公开',
          value: 'op2'
        },
        {
          label: '不公开',
          value: 'op3'
        }
      ],
      beginAndEndDate: {},
      dateStr: '',
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
        rowsPerPage: 20
        // rowsNumber: xx if getting data from a server
      },
      visibleColumns: [
        'calories',
        'desc',
        'fat',
        'carbs',
        'protein',
        'sodium',
        'calcium',
        'iron'
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
    doQuery() {
      this.queryLoad = true
      setTimeout(() => {
        this.queryLoad = false
      }, 2000)
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
    this.showQuery = this.$q.screen.gt.xs
    this.tableLabel = this.$q.screen.gt.xs ? '收起' : '展开'
  },
  watch: {
    selected(newSelected, oldSelected) {
      this.seamless = newSelected.length > 0
    },
    beginAndEndDate(newSelected, oldSelected) {
      if (newSelected) {
        this.dateStr = newSelected.from + '->' + newSelected.to
      } else {
        this.dateStr = ''
      }
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
