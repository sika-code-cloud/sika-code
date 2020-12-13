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
const namesValue = ['小小酥', '张豆豆', '王大锤', '嬴政', '诸葛亮']
const emailValue = ['4666054@qq.com', 'sikaCode@163.com', 'sikaDesign@163.com']
const userRoleDatas = ['普通用户', '管理员', '运营人员']
const verifyEmailValue = [true, false]
const userListDatas = []
for (let i = 0; i < 25; ++i) {
  userListDatas.push({
    index: i,
    imgSrc: commonUtil.getRandomData(imgSrcs),
    name: commonUtil.getRandomData(namesValue),
    idCard: commonUtil.getRandomRangeInt(100000, 1000),
    email: commonUtil.getRandomData(emailValue),
    role: commonUtil.getRandomData(userRoleDatas),
    verifyEmail: commonUtil.getRandomData(verifyEmailValue)
  })
}
export default {
  userListDatas,
  userRoleDatas
}
