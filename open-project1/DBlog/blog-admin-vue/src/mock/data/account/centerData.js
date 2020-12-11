import { date } from 'quasar'

const introduceData = {
  imgSrc: 'imgs/head.png',
  name: 'Serati Ma',
  motto: '海纳百川，有容乃大',
  job: '交互专家',
  department: '蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED',
  address: '浙江省杭州市'
}
const labelDatas = [
  {
    selected: true,
    removable: true,
    show: true,
    color: 'primary',
    textColor: 'white',
    icon: 'event',
    label: '很有想法的',
    class: 'glossy',
    square: true
  },
  {
    selected: true,
    removable: true,
    show: true,
    color: 'teal',
    textColor: 'white',
    icon: 'bookmark',
    label: '专注设计',
    square: false
  },
  {
    selected: true,
    removable: true,
    show: true,
    color: 'orange',
    textColor: 'white',
    icon: 'star',
    label: '辣~',
    square: true
  },
  {
    selected: true,
    removable: true,
    show: true,
    color: 'deep-orange',
    textColor: 'white',
    icon: 'directions',
    label: '大长腿',
    class: 'glossy',
    square: false
  },
  {
    selected: false,
    removable: true,
    show: true,
    color: 'red',
    textColor: 'white',
    icon: 'language',
    outline: true,
    class: 'bg-red-1',
    label: '川妹子',
    square: true
  },
  {
    selected: false,
    removable: true,
    show: true,
    color: 'info',
    textColor: 'white',
    icon: 'backup',
    label: '海纳百川',
    square: false
  },
  {
    selected: true,
    removable: true,
    show: true,
    color: 'green',
    textColor: 'white',
    icon: 'explore',
    label: '完美主义者',
    square: true
  }
]
const pictureSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/uMfMFlvUuceEyPpotzlq.png',
  'https://gw.alipayobjects.com/zos/rmsportal/iZBVOIhGJiAnhplqjvZW.png',
  'https://pic.rmb.bdstatic.com/fbb6939952e2e27c5cdfc5b706905768.jpeg@wm_2,t_55m+5a625Y+3L+WIhuS6q+e+juS4veeahOmbhuS4reWcsA==,fc_ffffff,ff_U2ltSGVp,sz_26,x_17,y_17',
  'https://gw.alipayobjects.com/zos/rmsportal/iXjVmWVHbCJAyqvDxdtx.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604603259601&di=98453cd17ded14a25ec805df888b2267&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191128%2F14%2F1574923905-WqopanrGSZ.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/gLaIAoVWTtLbBWZNYEMg.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939538&di=a457faa41c62fff3cbf0ab1fed08dcfc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201308%2F05%2F20130805105309_5E2zE.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939540&di=329ba6679a8ac3e9feb457c5dd24e3d5&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170415%2F20170415170933_d0304d15b93b7025b2a045c4ff65baf3_13.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602941634&di=be2c1a81c94d87340186413a8b1bd42e&imgtype=0&src=http%3A%2F%2Ft1.hxzdhn.com%2Fuploads%2Ftu%2F201807%2F9999%2Fb488d1a5fc.jpg'
]
const imgSrcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
  'https://cdn.quasar.dev/img/avatar5.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png',
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png',
  'https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png'
]
const groupLabels = [
  '科学搬砖组', '全组都是吴彦祖', '中二少女团', '程序员日常', '高逼格设计天团', '骗你来学计算机', '打死不学JS'
]
const gruopDatas = []
for (let i = 0; i < groupLabels.length; ++i) {
  gruopDatas.push({ imgSrc: imgSrcs[i], label: groupLabels[i] })
}
const titleDatas = []
for (let i = 0; i < 36; ++i) {
  const imgIndex = Math.floor(Math.random() * imgSrcs.length)
  const month = Math.floor(Math.random() * 12)
  const day = Math.floor(Math.random() * 30)
  const hour = Math.floor(Math.random() * 24)
  const minute = Math.floor(Math.random() * 60)
  const publishTimeTemp = date.formatDate(new Date(2020, month, day, hour, minute), 'YYYY-MM-DD HH:mm')
  titleDatas.push({
    index: i,
    imgSrc: imgSrcs[imgIndex],
    publishTime: publishTimeTemp,
    collection: Math.floor(Math.random() * 300),
    star: Math.floor(Math.random() * 200),
    word: Math.floor(Math.random() * 100)
  })
}
const applicationDatas = []
for (let i = 0; i < 18; ++i) {
  const imgIndex = Math.floor(Math.random() * imgSrcs.length)
  applicationDatas.push({
    index: i,
    imgSrc: imgSrcs[imgIndex],
    activeUser: Math.ceil(Math.random() * 20),
    increaseUser: Math.floor(Math.random() * 2000) + 1
  })
}
const projectDatas = []
for (let i = 0; i < 19; ++i) {
  const pictureIndex = Math.floor(Math.random() * pictureSrcs.length)
  projectDatas.push({
    pictureSrc: pictureSrcs[pictureIndex],
    index: i,
    hour: Math.ceil(Math.random() * 20)
  })
}
export default {
  introduceData,
  labelDatas,
  gruopDatas,
  titleDatas,
  applicationDatas,
  projectDatas
}
