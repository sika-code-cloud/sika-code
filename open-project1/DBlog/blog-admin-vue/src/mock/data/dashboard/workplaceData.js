const currentUserData = {
  headSrc: 'imgs/head_1.png',
  greetings: '早安',
  name: 'Serati Ma',
  job: '交互专家',
  department: '蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED'
}
const statisticsData = {
  projectNum: 56,
  waitMatterNum: 8,
  allMatterNum: 24,
  projectVisitNum: 2223
}
const myProjectDatas = []
for (let i = 0; i < 6; ++i) {
  const myProjectData = {
    imgSrc: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
    title: 'Alipay',
    desc: '那是一种内在的东西，他们到达不了，也无法触及的',
    group: '科学搬家组',
    time: '3天前'
  }
  myProjectDatas.push(myProjectData)
}
const dynamicDatas = []
for (let i = 0; i < 6; ++i) {
  const dynamicData = {
    imgSrc: 'imgs/head.png',
    name: '钟小鱼' + i,
    group: '高逼格设计天团' + i,
    matter: '六月迭代' + i,
    date: '2020-12-21 21:11:21'
  }
  dynamicDatas.push(dynamicData)
}

const waitMatters = {
  waitMatterColumns: [
    {
      name: 'matter',
      required: true,
      label: '事项',
      align: 'left',
      field: (row) => row.matter,
      format: (val) => `${val}`,
      sortable: true
    },
    {
      name: 'dealer',
      align: 'left',
      label: '处理人',
      field: 'dealer',
      sortable: true
    }
  ],
  waitMatterDatas: [
    {
      id: 1,
      matter: 'Card 支持点击，可以配置 to 等属性',
      dealer: '张三'
    },
    {
      id: 2,
      matter: 'Tabs 新增属性，高度可以自适应其它高度',
      dealer: '李四'
    },
    {
      id: 3,
      matter: 'Drawer 新增可拖拽调整宽度的属性',
      dealer: '王大锤'
    },
    {
      id: 4,
      matter: 'AvatarList 支持配置 extra，不一定给全量数据',
      dealer: '张三'
    }],
  selected: []
}

const teamMemberDatas = []
for (let i = 0; i < 10; ++i) {
  let srcTemp = 'imgs/head_1.png'
  let nameTemp = '亮晶晶'
  let nickNameTemp = 'Designer'
  let stateTemp = '在'
  if (i % 3 === 0) {
    srcTemp = 'imgs/head.png'
    nameTemp = '黑糊糊'
    nickNameTemp = 'HeiHuHu'
    stateTemp = '不在'
  } else if (i % 3 === 1) {
    srcTemp = 'imgs/sika-head.png'
    nameTemp = '金灿灿'
    nickNameTemp = 'JinCanCan'
    stateTemp = '不在'
  }
  const teamMemberData = {
    hearSrc: srcTemp,
    name: nameTemp,
    nickName: nickNameTemp,
    state: stateTemp
  }
  teamMemberDatas.push(teamMemberData)
}
export default {
  currentUserData,
  statisticsData,
  myProjectDatas,
  dynamicDatas,
  waitMatters,
  teamMemberDatas
}
