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
  getAvailableCount
}
