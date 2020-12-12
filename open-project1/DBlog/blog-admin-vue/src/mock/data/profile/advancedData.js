import { date } from 'quasar'
import commonUtil from 'src/utils/commonUtil'
import dateUtil from 'src/utils/dateUtil'

const headData = {
  orderNo: '202012120987324535',
  creator: '小小舒',
  orderProduct: '每日一笑',
  createTime: '2020-12-12 12:21',
  relationOrderNo: 'RO2020123435546546',
  effectiveDate: '2020-10-01 ~ 2020-12-31',
  remark: '请于两个工作日内确认',
  state: '已确认',
  orderAmount: '645.45'
}
const progressData = [
  {
    step: 1,
    title: '创建项目',
    capital: '张豆豆 2020-12-10 08:21:21',
    icon: 'source'
  },
  {
    step: 2,
    title: '部门初审',
    capital: '张乐乐 2020-12-10 08:21:21',
    icon: 'supervised_user_circle'
  },
  {
    step: 3,
    title: '财务复核',
    icon: 'visibility'
  },
  {
    step: 4,
    title: '完成',
    icon: 'verified'
  }
]
const userInfoData = {
  name: '小小酥',
  membershipNo: '20201298021309809423',
  idCard: '3321944288191034921',
  phone: '18112345678',
  pickAddress: '曲丽丽 18100000000 浙江省杭州市西湖区黄姑山路工专路交叉路口'
}

const operateTypeValue = ['订购关系生效', '财务复审', '部门初审', '提交订单', '创建订单']
const operatorValue = ['小小酥', '张豆豆', '周小天', '林正天', '汗丫丫']
const executeResultValue = ['通过', '驳回']
const remarkValue = ['还不错', '原因不详', '需要完善', '查漏补缺', '无']
const operateLogColumns = [
  {
    name: 'operateType',
    required: true,
    label: '操作类型',
    align: 'left',
    field: 'operateType',
    format: (val) => `${val}`
  },
  {
    name: 'operator',
    align: 'left',
    label: '操作人',
    field: 'operator'
  },
  {
    name: 'executeResult',
    align: 'left',
    label: '执行结果',
    field: 'executeResult'
  },
  {
    name: 'operateTime',
    align: 'left',
    label: '操作时间',
    field: 'operateTime',
    sortable: true,
    sort: (a, b) => date.extractDate(a, 'YYYY-MM-DD HH:mm:ss') - date.extractDate(b, 'YYYY-MM-DD HH:mm:ss')
  },
  {
    name: 'remark',
    align: 'left',
    label: '备注',
    field: 'remark'
  }
]

function buildOperateDatas(count) {
  const operateLogDatas = []
  for (let i = 0; i < count; ++i) {
    operateLogDatas.push({
      operateType: commonUtil.getRandomData(operateTypeValue),
      operator: commonUtil.getRandomData(operatorValue),
      executeResult: commonUtil.getRandomData(executeResultValue),
      operateTime: date.formatDate(dateUtil.buildRandomDate(2020), 'YYYY-MM-DD HH:mm:ss'),
      remark: commonUtil.getRandomData(remarkValue)
    })
  }
  return operateLogDatas
}

const operateLog1 = {
  columns: operateLogColumns,
  datas: buildOperateDatas(6)
}
const operateLog3 = {
  columns: operateLogColumns,
  datas: buildOperateDatas(8)
}

export default {
  headData,
  progressData,
  userInfoData,
  operateLog1,
  operateLog3
}
