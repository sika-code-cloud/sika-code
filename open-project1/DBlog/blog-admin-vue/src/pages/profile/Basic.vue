<template>
  <div class="sc-design">
    <div class="bg-white text-h6 q-pa-md">
      <strong>基础详情页</strong>
    </div>
    <div class="q-pt-md q-px-md">
      <q-card flat class="q-px-sm q-gutter-y-lg no-border-radius">
        <q-card-section>
          <q-item-label class="text-body1">
            <strong>退款申请</strong>
          </q-item-label>
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-md-4 col-sm-6 col-xs-12">
              取货单号：{{ basicData.returnGoodsApplyData.returnOrderNo }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">状态：{{ basicData.returnGoodsApplyData.returnState }}</div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              销售单号：{{ basicData.returnGoodsApplyData.saleOrderNo }}
            </div>
            <div class="col col-md-12 col-sm-6 col-xs-12">
              子订单：{{ basicData.returnGoodsApplyData.subOrderNo }}
            </div>
          </q-item-label>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1">
            <strong>用户信息</strong>
          </q-item-label>
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-md-4 col-sm-6 col-xs-12">用户姓名：{{ basicData.userData.name }}</div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              联系电话：{{ basicData.userData.phone }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              常用快递：{{ basicData.userData.usedExpress }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12 q-pr-sm">
              取货地址：{{ basicData.userData.pickupAddress }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">备注：{{ basicData.userData.remark }}</div>
          </q-item-label>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1 q-mb-lg"> 退货商品</q-item-label>
          <q-table
            flat
            square
            table-header-class="bg-grey-1"
            style="border-bottom: 1px solid lightgrey"
            hide-bottom
            :data="basicData.returnGoodsData.datas"
            :columns="basicData.returnGoodsData.columns"
            row-key="name"
          >
            <template v-slot:bottom-row>
              <q-tr class="text-weight-bolder">
                <q-td colspan="4">总计</q-td>
                <q-td class="text-right"> {{ basicData.returnGoodsData.totalData.totalNumber }}</q-td>
                <q-td class="text-right"> {{ basicData.returnGoodsData.totalData.totalAmount }}</q-td>
              </q-tr>
            </template>
          </q-table>
        </q-card-section>
        <q-card-section>
          <q-item-label class="text-body1 q-mb-lg"> 退货进度</q-item-label>
          <q-table
            flat
            square
            table-header-class="bg-grey-1"
            style="border-bottom: 1px solid lightgrey"
            hide-bottom
            :data="basicData.returnGoodsProgressData.datas"
            :columns="basicData.returnGoodsProgressData.columns"
            row-key="name"
          >
            <template v-slot:body-cell-state="props">
              <q-td :props="props">
                <span
                  v-if="props.value === '进行中'">
                  <q-spinner-rings
                    color="primary"
                    size="sm"
                  />
                  <q-chip square outline color="primary" class="bg-blue-1 no-border-radius" size="sm">
                   {{ props.value }}
                  </q-chip>
                </span>
                <span v-else-if="props.value === '已完成'">
                  <q-icon
                    name="lens"
                    color="positive"
                    class="q-mx-sm"
                  />
                  <q-chip square outline color="positive" class="bg-green-1 no-border-radius" size="sm">
                   {{ props.value }}
                  </q-chip>
                </span>
                <span v-else-if="props.value === '已延期'">
                  <q-icon
                    name="radio_button_checked"
                    color="warning"
                    class="q-mx-sm"
                  />
                  <q-chip square outline color="warning" class="bg-orange-1 no-border-radius" size="sm">
                   {{ props.value }}
                  </q-chip>
                </span>
                <span v-else-if="props.value === '未开始'">
                  <q-icon
                    name="lens"
                    color="grey-5"
                    class="q-mx-sm"
                  />
                  <q-chip square outline color="grey-5" class="bg-grey-1 no-border-radius" size="sm">
                   {{ props.value }}
                  </q-chip>
                </span>
              </q-td>
            </template>
          </q-table>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
import BASIC_DATA from '@/mock/data/profile/basicData'

export default {
  name: 'Basic',
  data() {
    return {
      basicData: BASIC_DATA
    }
  },
  methods: {}
}
</script>

<style>
</style>
