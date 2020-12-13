import commonUtil from 'src/utils/commonUtil'
import dateUtil from 'src/utils/dateUtil'
import { date } from 'quasar'

const summaryData = {
  waitDealTask: 8,
  weekDealDuration: 32,
  completeTask: 23
}
const namesValue = ['小小酥', '张豆豆', '王大锤', '嬴政', '诸葛亮']
const matterValue = ['投资蚂蚁', '建造摩天大楼', '修建高铁', '登月进程', '飞出银河系']
const stateValue = ['待处理', '已完成']
const imgSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png',
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png'
]

const basicListDatas = []

for (let i = 0; i < 35; ++i) {
  basicListDatas.push({
    id: i,
    imgSrc: commonUtil.getRandomData(imgSrcs),
    matter: commonUtil.getRandomData(matterValue),
    name: commonUtil.getRandomData(namesValue),
    startTime: date.formatDate(dateUtil.buildRandomDate(2020), 'YYYY-MM-DD HH:mm'),
    state: commonUtil.getRandomData(stateValue),
    progress: Math.random(),
    desc: '那是一种内在的东西，他们到达不了，也无法触及的，那是一种内在的东西，他们到达不了，也无法触及的 那是一种内在的东西，他们到达不了，也无法触及的，那是一种内在的东西，他们到达不了，也无法触及的'
  })
}

export default {
  summaryData,
  basicListDatas,
  imgSrcs
}
