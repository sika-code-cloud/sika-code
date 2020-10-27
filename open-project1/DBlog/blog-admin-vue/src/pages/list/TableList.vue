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
          <q-item-section class="col-2 text-right"> 调用次数: </q-item-section>
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
        <q-table
          square
          card-class="shadow-1"
          title="查询表格"
          title-class="text-body1"
          :data="data"
          color="primary"
          :columns="columns"
          :visible-columns="visibleColumns"
          row-key="name"
          :selected-rows-label="getSelectedString"
          selection="multiple"
          :selected.sync="selected"
          :pagination.sync="pagination"
          hide-selected-banner
          separator="none"
        >
          <template v-slot:top-right="props">
            <q-btn
              label="新建"
              color="primary"
              class="q-mr-sm no-border-radius"
              icon="add"
              unelevated
            />
            <q-btn rounded flat dense size="md" icon="refresh">
              <q-tooltip>刷新</q-tooltip>
            </q-btn>
            <q-btn rounded flat dense size="md" icon="unfold_less">
              <q-tooltip>密度</q-tooltip>
            </q-btn>
            <q-btn rounded flat dense size="md" icon="settings">
              <q-tooltip>列设置</q-tooltip>
            </q-btn>
            <q-select
              behavior="menu"
              v-model="visibleColumns"
              multiple
              borderless
              dense
              options-dense
              :display-value="$q.lang.table.columns"
              emit-value
              map-options
              :options="columns"
              option-value="name"
              style="min-width: 150px"
            >
              <q-tooltip>列设置</q-tooltip>
            </q-select>
            <q-btn rounded flat round dense
                   :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'"
                   @click="props.toggleFullscreen"
                  >
              <q-tooltip>全屏</q-tooltip>
            </q-btn>
          </template>
          <template v-slot:bottom="scope">
            <div class="full-width ">
            <q-pagination
              v-model="pagination.page"
              color="primary"
              :max="scope.pagesNumber"
              size="sm"
              :max-pages="6"
              :boundary-numbers="false"
              :boundary-links="true"
              class="float-right"
            />
            </div>
          </template>
        </q-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TableList',
  data() {
    return {
      tableShow: true,
      tableLabel: '展开',
      ruleName: '',
      selected: [],
      pagination: {
        sortBy: 'calories',
        descending: false,
        page: 1,
        rowsPerPage: 4
        // rowsNumber: xx if getting data from a server
      },
      visibleColumns: ['calories', 'desc', 'fat', 'carbs', 'protein', 'sodium', 'calcium', 'iron'],
      columns: [
        {
          name: 'desc',
          required: true,
          label: 'Dessert (100g serving)',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`,
          sortable: true
        },
        {
          name: 'calories',
          align: 'center',
          label: 'Calories',
          field: 'calories',
          sortable: true
        },
        { name: 'fat', label: 'Fat (g)', field: 'fat', sortable: true },
        { name: 'carbs', label: 'Carbs (g)', field: 'carbs' },
        { name: 'protein', label: 'Protein (g)', field: 'protein' },
        { name: 'sodium', label: 'Sodium (mg)', field: 'sodium' },
        {
          name: 'calcium',
          label: 'Calcium (%)',
          field: 'calcium',
          sortable: true,
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        },
        {
          name: 'iron',
          label: 'Iron (%)',
          field: 'iron',
          sortable: true,
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        }
      ],
      data: [
        {
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
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          sodium: 54,
          calcium: '12%',
          iron: '6%'
        },
        {
          name: 'Frozen Yogurt1',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: '14%',
          iron: '1%'
        },
        {
          name: 'Ice cream sandwich1',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          sodium: 129,
          calcium: '8%',
          iron: '1%'
        },
        {
          name: 'Eclair1',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          sodium: 337,
          calcium: '6%',
          iron: '7%'
        },
        {
          name: 'Cupcake1',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: '3%',
          iron: '8%'
        },
        {
          name: 'Gingerbread1',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          sodium: 327,
          calcium: '7%',
          iron: '16%'
        },
        {
          name: 'Jelly bean1',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          sodium: 50,
          calcium: '0%',
          iron: '0%'
        },
        {
          name: 'Lollipop1',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          sodium: 38,
          calcium: '0%',
          iron: '2%'
        },
        {
          name: 'Honeycomb1',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          sodium: 562,
          calcium: '0%',
          iron: '45%'
        },
        {
          name: 'Donut1',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          sodium: 326,
          calcium: '2%',
          iron: '22%'
        },
        {
          name: 'KitKat1',
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
    }
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.data.length / this.pagination.rowsPerPage)
    }
  }
}
</script>

<style scoped></style>
