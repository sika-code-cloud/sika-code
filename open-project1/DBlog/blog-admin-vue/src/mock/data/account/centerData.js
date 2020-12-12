
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
const groupDatas = []
for (let i = 0; i < groupLabels.length; ++i) {
  groupDatas.push({ imgSrc: imgSrcs[i], label: groupLabels[i] })
}
export default {
  introduceData,
  labelDatas,
  groupDatas
}
