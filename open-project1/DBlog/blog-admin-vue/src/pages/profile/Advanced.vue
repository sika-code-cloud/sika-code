<template>
  <div class="sika-table-th">
    <div class="bg-white q-px-lg q-pt-lg" style="margin: -10px -16px 0 -16px">
      <div class="row text-h6">
        <strong class="col">单号：234231029431</strong>
        <div class="col-auto text-right">
          <span class="q-gutter-x-sm gt-xs">
            <q-btn-group unelevated>
              <q-btn
                color="secondary"
                padding="5px 15px"
                label="操作一"
                style="width: 80px"
              />
              <q-btn
                color="secondary"
                padding="5px 15px"
                style="width: 80px"
                label="操作二"
              />
              <q-btn
                padding="5px 15px"
                color="secondary"
                style="width: 60px"
                label=". . ."
              />
            </q-btn-group>
            <q-btn
              color="primary"
              padding="5px 15px"
              style="width: 80px"
              unelevated
              label="主操作"
            ></q-btn>
          </span>
          <span class="col lt-sm">
            <q-btn-dropdown
              auto-close
              unelevated
              padding="5px 15px"
              color="primary"
              label="主操作"
              split
            />
          </span>
        </div>
      </div>
      <div class="row q-gutter-y-sm q-mt-sm">
        <div class="col-sm-8 col-xs-12">
          <div class="row q-gutter-y-sm">
            <span class="col-md-6 col-xs-12 q-pr-sm">创建人：曲丽丽</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">订购产品：XX服务</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">创建时间：2017-07-07</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">关联单据：12321</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">
              生效日期：2017-07-07 ~ 2017-08-08
            </span>
            <span class="col-md-6 col-xs-12 q-pr-sm">
              备注：请于两个工作日内确认
            </span>
          </div>
        </div>
        <div class="col-sm-4 col-xs-12">
          <span class="row q-gutter-y-sm">
            <span class="col-sm-6 col-xs-4 text-grey-7">状态</span>
            <span class="col-sm-6 col-xs-8 text-grey-7">订单金额</span>
            <span class="col-sm-6 col-xs-4 text-h5">待审批</span>
            <span class="col-sm-6 col-xs-8 text-h5">¥568.08</span>
          </span>
        </div>
      </div>
      <q-tabs
        v-model="tab"
        class="text-primary q-ml-none q-mt-lg"
        dense
        narrow-indicator
        align="left"
        :breakpoint="0"
      >
        <q-tab name="details" class="q-px-none" label="详情" />
        <q-tab name="rule" class="q-px-none q-mx-md" label="规则" />
      </q-tabs>
    </div>
    <div class="q-pt-md">
      <q-card flat class="q-px-sm q-gutter-y-lg no-border-radius">
        <q-card-section>
          <q-item-label class="text-body1">流程进度</q-item-label>
          <q-stepper
            v-model="step"
            ref="stepper"
            color="primary"
            :vertical="$q.screen.lt.md"
            flat
          >
            <q-step
              :name="1"
              :class="{ 'step-height': $q.screen.lt.md }"
              title="创建项目"
              caption="李大锤 2016-12-12 12:32"
              icon="settings"
              :done="step > 1"
            >
            </q-step>
            <q-step
              :name="2"
              title="部门初审"
              :class="{ 'step-height': $q.screen.lt.md }"
              caption="周毛毛 2016-12-12 12:32"
              icon="create_new_folder"
              :done="step > 2"
            >
            </q-step>
            <q-step
              :name="3"
              :class="{ 'step-height': $q.screen.lt.md }"
              title="财务复核"
              icon="assignment"
              :done="step > 3"
            >
            </q-step>
            <q-step
              :name="4"
              :class="{ 'step-height': $q.screen.lt.md }"
              title="完成"
              icon="add_comment"
            >
            </q-step>
          </q-stepper>
        </q-card-section>
      </q-card>
      <q-card flat class="q-mt-xs q-gutter-y-lg no-border-radius">
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
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1 q-mb-lg"> 退货商品 </q-item-label>
          <q-table
            flat
            square
            table-header-class="bg-grey-1"
            style="border-bottom: 1px solid lightgrey"
            hide-bottom
            :data="data"
            :columns="columns"
            row-key="name"
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
            square
            table-header-class="bg-grey-1"
            style="border-bottom: 1px solid lightgrey"
            hide-bottom
            :data="tuihuoData"
            :columns="tuihuoColumns"
            row-key="name"
          >
            <template v-slot:body-cell-fat="props">
              <q-td :props="props">
                <q-spinner-rings
                  color="primary"
                  size="2em"
                  v-if="props.value === '进行中'"
                />
                <span
                  v-else
                  style="width: 8px; height: 8px; border-radius: 50%"
                  class="bg-info inline-block q-mx-sm"
                />
                <span>{{ props.value }}</span>
              </q-td>
            </template>
          </q-table>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Advance',
  data() {
    return {
      step: '3',
      tab: 'details',
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
          fat: '进行中',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '12345631',
          calories: '凉茶 300ml',
          fat: '成功',
          sodium: 2.1,
          calcium: 1,
          iron: 2.42
        },
        {
          name: '12345161',
          calories: '好吃的薯片',
          fat: '成功',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        },
        {
          name: '12344561',
          calories: '特别好吃的蛋卷',
          fat: '成功',
          sodium: 2.1,
          calcium: 1,
          iron: 2.12
        },
        {
          name: '12354561',
          calories: '特别好吃的蛋卷2',
          fat: '成功',
          sodium: 2.1,
          calcium: 1,
          iron: 2.32
        }
      ],
      data: [
        {
          name: '12343561',
          calories: '矿泉水 550ml',
          fat: '成功',
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
  font-size: 16px;
}
.sika-table-th .q-tab__label {
  font-size: 16px;
}
</style>
