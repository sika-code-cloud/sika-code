const routeDatas = [
  {
    name: '仪表盘',
    icon: 'dashboard',
    group: '/dashboard',
    groupName: 'first',
    children: [
      {
        name: '分析页',
        group: '/dashboard',
        to: '/dashboard/analysis'
      },
      {
        name: '监控页',
        group: '/dashboard',
        to: '/dashboard/monitor'
      },
      {
        name: '工作台',
        group: '/dashboard',
        to: '/dashboard/workplace'
      }
    ]
  },
  {
    name: '表单页',
    icon: 'edit_road',
    group: '/form',
    groupName: 'first',
    children: [
      {
        name: '基础表单',
        group: '/form',
        to: '/form/basic-form'
      },
      {
        name: '分步表单',
        group: '/form',
        to: '/form/step-form'
      },
      {
        name: '高级表单',
        group: '/form',
        to: '/form/advanced-form'
      }
    ]
  },
  {
    name: '列表页',
    icon: 'table_view',
    group: '/list',
    groupName: 'first',
    children: [
      {
        name: '搜索列表',
        group: '/list/search',
        children: [
          {
            name: '搜索列表（文章）',
            group: '/list/search',
            to: '/list/search/articles'
          },
          {
            name: '搜索列表（项目）',
            group: '/list/search',
            to: '/list/search/projects'
          },
          {
            name: '搜索列表（应用）',
            group: '/list/search',
            to: '/list/search/applications'
          }
        ]
      },
      {
        name: '用户列表',
        group: '/list',
        to: '/list/user-list'
      },
      {
        name: '商品列表',
        group: '/list',
        to: '/list/goods-list'
      },
      {
        name: '查询表格',
        group: '/list',
        to: '/list/table-list'
      },
      {
        name: '标准列表',
        group: '/list',
        to: '/list/basic-list'
      },
      {
        name: '卡片列表',
        group: '/list',
        to: '/list/card-list'
      }
    ]
  },
  {
    name: '详情页',
    icon: 'library_books',
    group: '/profile',
    groupName: 'first',
    children: [
      {
        name: '基础详情页',
        group: '/profile',
        to: '/profile/basic'
      },
      {
        name: '高级详情页',
        group: '/profile',
        to: '/profile/advanced'
      }
    ]
  },
  {
    name: '结果页',
    icon: 'check_circle_outline',
    group: '/result',
    groupName: 'first',
    children: [
      {
        name: '成功页',
        group: '/result',
        to: '/result/success'
      },
      {
        name: '失败页',
        group: '/result',
        to: '/result/fail'
      }
    ]
  },
  {
    name: '异常页',
    icon: 'error_outline',
    group: '/exception',
    groupName: 'first',
    children: [
      {
        name: '403',
        group: '/exception',
        to: '/exception/403'
      },
      {
        name: '404',
        group: '/exception',
        to: '/exception/404'
      },
      {
        name: '500',
        group: '/exception',
        to: '/exception/500'
      }
    ]
  },
  {
    name: '个人页',
    icon: 'perm_identity',
    group: '/account',
    groupName: 'first',
    children: [
      {
        name: '个人中心',
        group: '/account',
        to: '/account/center'
      },
      {
        name: '个人设置',
        group: '/account',
        to: '/account/settings'
      }
    ]
  },
  {
    name: '编辑器',
    icon: 'text_fields',
    group: '/editor',
    groupName: 'first',
    children: [
      {
        name: '自定义编辑器',
        group: '/editor',
        to: '/editor/customer'
      },
      {
        name: 'Markdown编辑器',
        group: '/editor',
        to: '/editor/markdown'
      }
    ]
  }
]
const informsData = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  }
]
const notifyDatas = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断222',
    desc: '3年前',
    color: 'blue',
    textColor: 'white',
    disable: false
  }
]
const waitDealDatas = [
  {
    icon: 'email',
    title: '你推荐的 曲妮妮 已通过第三轮面试',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'bluetooth',
    title: '你收到了 14 份新周报',
    desc: '3年前',
    color: 'primary',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '这种模板可以区分多种通知类型',
    desc: '3年前',
    color: 'teal',
    textColor: 'white',
    disable: true
  },
  {
    icon: 'email',
    title: '左侧图标用于区分不同的类型',
    desc: '3年前',
    color: 'yellow-10',
    textColor: 'white',
    disable: false
  },
  {
    icon: 'email',
    title: '内容不要超过两行字，超出时自动截断',
    desc: '3年前',
    color: 'orange',
    textColor: 'white',
    disable: false
  }
]
const languageDatas = [
  {
    nationalFlag:
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604953153845&di=75a450709d07f093810061e3e8ae0a45&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F7af40ad162d9f2d3faac68ffa9ec8a136327cc65.jpg',
    label: 'Us English'
  },
  {
    nationalFlag:
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604954286966&di=ac7db27213c991c1982adad09f02abbf&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fbaike%2Fs%3D220%2Fsign%3Db6ae7334978fa0ec7bc7630f1696594a%2Fb7003af33a87e950e154426b16385343fbf2b472.jpg',
    label: 'En English'
  },
  {
    nationalFlag:
      'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101918221,2000267634&fm=26&gp=0.jpg',
    label: 'CN 简体中文'
  },
  {
    nationalFlag:
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604953546889&di=4299987e8e6144411da56b8885a4ff73&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190331%2F1efebed7b2d948c2b3bbc3855651c120.jpeg',
    label: 'HK 繁体中文'
  }
]
const scrollStyleData = {
  contentStyle: {},
  contentActiveStyle: {},
  thumbStyle: {
    right: '2px',
    borderRadius: '5px',
    backgroundColor: '#027be3',
    width: '0px',
    opacity: 0.75
  },
  barStyle: {
    right: '2px',
    borderRadius: '9px',
    backgroundColor: '#027be3',
    width: '9px',
    opacity: 0.2
  }
}

const rightOffset = {
  rightOffsetInit: [5, 168],
  rightOffsetShow: [280, 168]
}

const styleSettingsData = {
  themeColorSetting: [
    {
      style: 'background-color: rgb(24, 144, 255)',
      color: 'rgb(24, 144, 255)',
      checked: true
    },
    {
      style: 'background-color: rgb(245, 34, 45)',
      color: 'rgb(245, 34, 45)',
      checked: false
    },
    {
      style: 'background-color: rgb(250, 84, 28)',
      color: 'rgb(250, 84, 28)',
      checked: false
    },
    {
      style: 'background-color: rgb(250, 173, 20)',
      color: 'rgb(250, 173, 20)',
      checked: false
    },
    {
      style: 'background-color: rgb(19, 194, 194)',
      color: 'rgb(19, 194, 194)',
      checked: false
    },
    {
      style: 'background-color: rgb(82, 196, 26)',
      color: 'rgb(82, 196, 26)',
      checked: false
    },
    {
      style: 'background-color: rgb(47, 84, 235)',
      color: 'rgb(47, 84, 235)',
      checked: false
    },
    {
      style: 'background-color: rgb(114, 46, 209)',
      color: 'rgb(114, 46, 209)',
      checked: false
    }
  ]
}

// 获取可用的数量
function getAvailableCount(datas) {
  if (!datas) {
    return 0
  }
  let count = 0
  for (let i = 0; i < datas.length; ++i) {
    if (datas[i].disable === false) {
      count++
    }
  }
  return count
}

export default {
  routeDatas,
  informsData,
  waitDealDatas,
  notifyDatas,
  languageDatas,
  scrollStyleData,
  rightOffset,
  styleSettingsData,
  getAvailableCount
}
