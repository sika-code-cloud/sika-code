<template>
  <div>
    <div class="text-h6"><strong>查询表格</strong></div>
    <q-form>
      <div class="row q-col-gutter-y-lg q-mt-lg">
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
          <q-item-section class="col-2 text-right">
            <q-item-label>规则名称:</q-item-label>
          </q-item-section>
          <q-item-section class="col">
            <q-input outlined v-model="ruleName" label="规则名称" dense square>
            </q-input>
          </q-item-section>
        </q-item>
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
          <q-item-section class="col-2 text-right">
            <q-item-label>描述:</q-item-label>
          </q-item-section>
          <q-item-section class="col">
            <q-input outlined v-model="ruleName" label="描述" dense square>
            </q-input>
          </q-item-section>
        </q-item>
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
          <q-item-section class="col-2 text-right"> 调用次数:</q-item-section>
          <q-item-section class="col">
            <q-input
              outlined
              v-model="ruleName"
              label="服务调用次数"
              dense
              square
            >
            </q-input>
          </q-item-section>
        </q-item>
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
          <q-item-section class="col-2 text-right">
            <q-item-label>状态:</q-item-label>
          </q-item-section>
          <q-item-section class="col">
            <q-input outlined v-model="ruleName" label="状态" dense square>
            </q-input>
          </q-item-section>
        </q-item>
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
          <q-item-section class="col-2 text-right">
            <q-item-label>时间:</q-item-label>
          </q-item-section>
          <q-item-section class="col">
            <q-input
              outlined
              v-model="ruleName"
              label="上次调度时间"
              dense
              square
            >
            </q-input>
          </q-item-section>
        </q-item>
        <q-item class="col-xl-3 col-md-4 col-sm-6 col-xs-12 q-pr-sm">
          <q-item-label class="col-12 text-right">
            <q-btn
              outline
              unelevated
              label="重置"
              class="q-mr-sm no-border-radius"
              color="secondary"
            />
            <q-btn
              unelevated
              label="查询"
              color="primary"
              class="q-mr-sm no-border-radius"
            />
            <q-btn-dropdown
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
    <div class="row q-mt-xl">
      <div class="col">
        <div></div>
        <q-table
          class="my-sticky-header-table"
          square
          flat
          bordered
          title="查询表格"
          title-class="text-body1"
          :data="data"
          color="primary"
          :columns="columns"
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
          <template v-slot:top-right="props">
            <div>
              <q-btn
                label="新建"
                color="primary"
                class="q-mr-sm no-border-radius"
                icon="add"
                unelevated
                @click="addRow"
              />
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
      </div>

      <q-inner-loading :showing="visible" style="z-index: 8000">
        <q-spinner-gears size="50px" color="primary" />
      </q-inner-loading>
    </div>
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
  </div>
</template>

<script>
import { QSpinnerFacebook } from 'quasar'
export default {
  name: 'TableList',
  data() {
    return {
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
      this.tableLabel = '关闭'
    },
    hide(evt) {
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
      const spinner = QSpinnerFacebook
      this.$q.loading.show({
        spinner,
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
    addRow() {
      this.loading = true
      setTimeout(() => {
        // const index = Math.floor(Math.random() * (this.data.length + 1))
        const row = this.data[Math.floor(Math.random() * this.data.length)]
        if (this.data.length === 0) {
          this.rowCount = 0
        }
        const addRow = { ...row } // extend({}, row, { name: `${row.name} (${row.__count})` })
        this.data.unshift(addRow)
        row.id = this.data.length
        this.loading = false
      }, 500)
    }
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.data.length / this.pagination.rowsPerPage)
    }
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
