import { date } from 'quasar'
import commonUtil from 'src/utils/commonUtil'
import dateUtil from 'src/utils/dateUtil'

const ruleNameValue = ['配置收付款', '配置费用', '配置服务器', '配置权限']
const stateValue = ['关闭', '运行中', '已上线', '异常']
const descValue = ['这条规则不错', '等待确认', '运行完美']
const queryCondition = {
  ruleName: null,
  desc: null,
  callCount: null,
  state: null,
  callNextTime: null
}
const tableListDatas = {
  columns: [{
    check: true,
    name: 'ruleName',
    required: true,
    label: '规则名称',
    align: 'left',
    field: 'ruleName',
    format: (val) => `${val}`
  }, {
    check: true,
    name: 'state',
    align: 'left',
    label: '状态',
    field: 'state'
  }, {
    check: true,
    name: 'callCount',
    align: 'left',
    label: '调用次数(万)',
    field: 'callCount',
    sortable: true,
    sort: (a, b) => a - b
  }, {
    check: true,
    name: 'callNextTime',
    label: '上次调用时间',
    align: 'left',
    field: 'callNextTime',
    sortable: true,
    sort: (a, b) => date.extractDate(a, 'YYYY-MM-DD HH:mm:ss') - date.extractDate(b, 'YYYY-MM-DD HH:mm:ss')
  }, {
    check: true,
    name: 'desc',
    align: 'left',
    label: '描述',
    field: 'desc'
  }],
  datas: []
}
for (let i = 0; i < 35; ++i) {
  tableListDatas.datas.push({
    id: i,
    ruleName: commonUtil.getRandomData(ruleNameValue),
    state: commonUtil.getRandomData(stateValue),
    callCount: commonUtil.getRandomCeilInt(100),
    callNextTime: date.formatDate(dateUtil.buildRandomDate(2020), 'YYYY-MM-DD HH:mm:ss'),
    desc: commonUtil.getRandomData(descValue)
  })
}
export default {
  queryCondition,
  stateValue,
  tableListDatas
}
