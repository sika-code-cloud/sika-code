import { date } from 'quasar'
import dateUtil from 'src/utils/dateUtil'

const returnGoodsApplyData = {
  returnOrderNo: 'TKDH1000000001',
  returnState: '已取货',
  saleOrderNo: 'XSDH1000000001',
  subOrderNo: 'SUBO000000001'
}
const userData = {
  name: '张三丰',
  phone: '19038786529',
  usedExpress: '顺丰物流',
  pickupAddress: '深圳市滨海大道一路18号',
  remark: '东风快递，使命必达'
}
const returnGoodsData = {
  columns: [{
    name: 'goodsNo',
    required: true,
    label: '商品编号',
    align: 'left',
    field: 'goodsNo',
    format: (val) => `${val}`
  }, {
    name: 'goodsName',
    align: 'left',
    label: '商品名称',
    field: 'goodsName'
  }, {
    name: 'goodsBarcode',
    align: 'left',
    label: '商品条码',
    field: 'goodsBarcode'
  }, {
    name: 'unitPrice',
    label: '单价',
    field: 'unitPrice',
    sortable: true,
    sort: (a, b) => a - b
  }, {
    name: 'goodsNum',
    label: '数量 (件)',
    field: 'goodsNum',
    sortable: true,
    sort: (a, b) => a - b
  }, {
    name: 'goodsAmount',
    label: '金额',
    field: 'goodsAmount',
    sortable: true,
    sort: (a, b) => a - b
  }],
  datas: [],
  totalData: {
    totalNumber: 0,
    totalAmount: parseFloat('0')
  }
}
const goodsNames = ['矿泉水55ml', '王老吉350ml', '乐事薯片', '蛋黄派', '康师傅牛肉面']
for (let i = 0; i < 6; ++i) {
  const goodsNoT = 'GN' + (Math.floor(Math.random() * 100000) + 1000000)
  const goodsBarcodeT = 'GB' + (Math.floor(Math.random() * 100000) + 2000000)
  const unitPriceT = Math.floor(Math.random() * 10 * 100) / 100
  const goodsNumT = Math.ceil(Math.random() * 10)
  const goodsAmountT = Math.floor(unitPriceT * goodsNumT * 100) / 100
  const goodsNameT = goodsNames[Math.floor(Math.random() * goodsNames.length)]
  returnGoodsData.datas.push({
    goodsNo: goodsNoT,
    goodsName: goodsNameT,
    goodsBarcode: goodsBarcodeT,
    unitPrice: unitPriceT,
    goodsNum: goodsNumT,
    goodsAmount: goodsAmountT
  })
  returnGoodsData.totalData.totalNumber += goodsNumT
  returnGoodsData.totalData.totalAmount = Math.floor((returnGoodsData.totalData.totalAmount + goodsAmountT) * 100) / 100
}

const returnGoodsProgressData = {
  columns: [{
    name: 'applyTime',
    required: true,
    label: '申请时间',
    align: 'left',
    field: 'applyTime',
    format: (val) => `${val}`
  }, {
    name: 'currentProgress',
    align: 'left',
    label: '当前进度',
    field: 'currentProgress'
  }, {
    name: 'state',
    align: 'center',
    label: '状态',
    field: 'state'
  }, {
    name: 'operator',
    label: '操作员ID',
    align: 'left',
    field: 'operator',
    sortable: true,
    format: (val) => `${val}`,
    sort: (a, b) => a - b
  }, {
    name: 'costTime',
    label: '耗时',
    align: 'left',
    field: 'costTime',
    format: (val) => `${val}`
  }],
  datas: []
}
const currentProgressValue = ['联系客户', '取货员出发', '取货员接单', '申请审批通过', '发起退货申请']
const stateValue = ['未开始', '进行中', '已完成', '已延期']
const costTimeValue = ['13mins', '5mins', '3mins', '1h', '2h', '3d', '5d']
for (let i = 0; i < 6; ++i) {
  const applyTimeT = date.formatDate(dateUtil.buildRandomDate(), 'YYYY-MM-DD HH:mm')
  const currentProgressT = currentProgressValue[Math.floor(Math.random() * currentProgressValue.length)]
  const stateT = stateValue[Math.floor(Math.random() * stateValue.length)]
  const operatorT = Math.floor(Math.random() * 100) + 10000
  const costTimeT = costTimeValue[Math.floor(Math.random() * costTimeValue.length)]
  returnGoodsProgressData.datas.push({
    applyTime: applyTimeT,
    currentProgress: currentProgressT,
    state: stateT,
    operator: operatorT,
    costTime: costTimeT
  })
}

export default {
  returnGoodsApplyData,
  userData,
  returnGoodsData,
  returnGoodsProgressData
}
