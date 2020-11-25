<template>
  <div class="sc-design">
    <div class="bg-white q-pa-md" style="margin: -16px -16px 0 -16px">
      <div class="text-h6">
        <strong>高级表单</strong>
      </div>
      <div class="text-body2">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </div>
    <div>
      <q-form>
        <q-card square flat class="q-gutter-y-md q-mt-lg q-pt-sm q-pb-lg">
          <div class="row q-px-sm q-mx-md items-center">
            <q-item-label class="text-body1">仓库管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="row q-gutter-y-lg q-px-lg items-center">
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">仓库名</q-item-label>
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
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">仓库域名</q-item-label>
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
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">仓库管理员</q-item-label>
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
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">审批人</q-item-label>
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
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">生效日期</q-item-label>
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
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">仓库类型</q-item-label>
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
        </q-card>
      </q-form>
      <q-form>
        <q-card square flat class="q-gutter-y-md q-mt-lg q-pt-sm q-pb-lg">
          <div class="row q-px-sm q-mx-md items-center">
            <q-item-label class="text-body1">任务管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="row q-gutter-y-lg q-px-lg items-center">
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">任务名</q-item-label>
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
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">任务描述</q-item-label>
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
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">执行人</q-item-label>
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
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">责任人</q-item-label>
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
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">生效日期</q-item-label>
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
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">任务类型</q-item-label>
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
        </q-card>
      </q-form>
      <q-form>
        <q-card square flat class="q-gutter-y-md q-mt-lg q-pt-sm q-pb-lg">
          <div class="q-mx-md q-px-sm">
            <q-item-label class="text-body1">成员管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="q-pt-sm q-px-lg">
            <q-table
              flat
              square
              table-header-class="bg-grey-1"
              style="border-bottom: 1px solid lightgrey"
              hide-bottom
              :data="columnData"
              :columns="columns"
              row-key="name"
            >
            </q-table>
            <q-btn
              unelevated
              :ripple="{ color: 'info' }"
              class="q-mt-md full-width"
              style="border: 1px dashed lightgrey"
              label="新增成员"
              icon="add"
            />
          </div>
        </q-card>
      </q-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdvanceForm',
  data() {
    return {
      ruleName: '',
      step: 1,
      columns: [
        {
          name: 'desc',
          required: true,
          label: '商品编号',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`
        },
        {
          name: 'calories',
          align: 'left',
          label: '商品名称',
          field: 'calories'
        },
        {
          name: 'fat',
          align: 'left',
          label: '商品条码',
          field: 'fat'
        },
        {
          name: 'sodium',
          label: '单价',
          field: 'sodium',
          sortable: true,
          format: (val) => this.formatNumber(`${val}`),
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        },
        {
          name: 'calcium',
          label: '数量 (件)',
          field: 'iron',
          sortable: true,
          format: (val) => this.formatNumber(`${val}`),
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        },
        {
          name: 'iron',
          label: '金额',
          field: 'iron',
          sortable: true,
          format: (val) => this.formatNumber(`${val}`),
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        }
      ],
      columnData: [
        {
          name: '12343561',
          calories: '矿泉水 550ml',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '12344561',
          calories: '凉茶 300ml',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.42
        },
        {
          name: '12314561',
          calories: '好吃的薯片',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        },
        {
          name: '12534561',
          calories: '特别好吃的蛋卷',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '123453561',
          calories: '特别好吃的蛋卷2',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        }
      ]
    }
  },
  methods: {
    formatNumber(num) {
      return parseFloat(num).toFixed(2)
      // return Math.floor(num * 100) / 100
      // return num.toFixed(2)
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
