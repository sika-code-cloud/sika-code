<template>
  <div class="sika-table-th">
    <div class="bg-white text-h6 q-pa-md" style="margin: -16px -16px 0 -16px">
      <strong>基础详情页</strong>
    </div>
    <div class="q-pt-md">
      <q-card flat class="q-gutter-y-lg no-border-radius">
        <q-card-section>
          <q-item-label class="text-body1">
            <strong>退款申请</strong>
          </q-item-label>
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-md-4 col-sm-6 col-xs-12">
              取货单号：1000000000
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">状态：已取货</div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              销售单号：1000000000
            </div>
            <div class="col col-md-12 col-sm-6 col-xs-12">
              子订单：3214321432
            </div>
          </q-item-label>
        </q-card-section>

        <q-card-section>
          <q-item-label class="text-body1">
            <strong>用户信息</strong>
          </q-item-label>
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-md-4 col-sm-6 col-xs-12">用户姓名：李大锤</div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              联系电话：18100000000
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              常用快递：菜鸟仓储
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12 q-pr-sm">
              取货地址：浙江省杭州市西湖区万塘路18号
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">备注：无</div>
          </q-item-label>
        </q-card-section>
        <q-card-section>
          <q-item-label class="text-body1 q-mb-lg"> 退货商品 </q-item-label>
          <q-table
            flat
            hide-bottom
            :data="data"
            :columns="columns"
            row-key="name"
            separator="none"
          >
            <template v-slot:bottom-row>
              <q-tr class="text-weight-bolder">
                <q-td colspan="4">总计</q-td>
                <q-td class="text-right"> 10 </q-td>
                <q-td class="text-right"> 61.5 </q-td>
              </q-tr>
            </template>
          </q-table>
        </q-card-section>
        <q-card-section>
          <q-item-label class="text-body1 q-mb-lg"> 退货进度 </q-item-label>
          <q-table
            flat
            hide-bottom
            :data="tuihuoData"
            :columns="tuihuoColumns"
            row-key="name"
            separator="none"
          >
          </q-table>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Basic',
  data() {
    return {
      tuihuoColumns: [
        {
          name: 'desc',
          required: true,
          label: '时间',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`,
          sortable: true
        },
        {
          name: 'calories',
          align: 'left',
          label: '当前进度',
          field: 'calories',
          sortable: true
        },
        {
          name: 'fat',
          align: 'left',
          label: '状态',
          field: 'fat',
          sortable: true
        },
        {
          name: 'sodium',
          label: '操作员ID',
          field: 'sodium',
          sortable: true,
          format: (val) => this.formatNumber(`${val}`),
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        },
        {
          name: 'calcium',
          label: '耗时',
          field: 'iron',
          sortable: true,
          format: (val) => this.formatNumber(`${val}`),
          sort: (a, b) => parseInt(a, 10) - parseInt(b, 10)
        }
      ],
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
      tuihuoData: [
        {
          name: '12345612',
          calories: '矿泉水 550ml',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '12345631',
          calories: '凉茶 300ml',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.42
        },
        {
          name: '12345161',
          calories: '好吃的薯片',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        },
        {
          name: '12344561',
          calories: '特别好吃的蛋卷',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '12354561',
          calories: '特别好吃的蛋卷2',
          fat: '12421432143214321',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        }
      ],
      data: [
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

<style>
.sika-table-th .q-table th {
  font-size: 15px;
}
</style>
