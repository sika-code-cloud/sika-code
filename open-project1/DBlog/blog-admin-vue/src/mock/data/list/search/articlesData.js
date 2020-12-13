import { date } from 'quasar'
import dateUtil from 'src/utils/dateUtil'
import commonUtil from 'src/utils/commonUtil'

const imgSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
  'https://cdn.quasar.dev/img/avatar5.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png',
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png',
  'https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png'
]
const articlesDatas = []
for (let i = 0; i < 36; ++i) {
  const publishTimeTemp = date.formatDate(dateUtil.buildRandomDate(2020), 'YYYY-MM-DD HH:mm')
  articlesDatas.push({
    index: i,
    imgSrc: commonUtil.getRandomData(imgSrcs),
    publishTime: publishTimeTemp,
    collection: Math.floor(Math.random() * 300),
    star: Math.floor(Math.random() * 200),
    word: Math.floor(Math.random() * 100)
  })
}
export default {
  articlesDatas
}
