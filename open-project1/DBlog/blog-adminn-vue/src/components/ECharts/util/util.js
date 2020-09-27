import _ from 'lodash'

export function changeMarkerSize (marker, backgroundColor, size = '8px') {
  const PageDOM = document.createElement('div') // 创建一个容器
  PageDOM.innerHTML = marker
  const spanStyle = PageDOM.getElementsByTagName('span')[0].style
  spanStyle.width = size
  spanStyle.height = size
  if (backgroundColor) {
    spanStyle.backgroundColor = backgroundColor
  }
  return PageDOM.innerHTML
}

export function defaultFormatter (param, backGroundColor) {
  let firstParam = param
  if (param instanceof Array) {
    firstParam = param[0]
  }
  const marker = changeMarkerSize(firstParam.marker, backGroundColor)
  let formatter = firstParam.name + '<br>' + marker + firstParam.seriesName + '：' + firstParam.value
  if (firstParam.percent) {
    formatter += ' (' + param.percent + '%)'
  }
  return formatter
}

/** 合并option */
export function mergerOption (defaultOption, clientOption) {
  const defaultOptionClone = _.cloneDeep(defaultOption)
  return _.merge(defaultOptionClone, clientOption)
}

// 默认的toolTip配置
const defaultTooltip = {
  backgroundColor: 'rgba(255,255,255,0.8)', // 设置背景图片 rgba格式
  color: '#141414',
  borderWidth: '1', // 边框宽度设置1
  borderColor: '#b5f5ec', // 设置边框颜色
  extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);', // 设置阴影效果
  textStyle: {
    fontStyle: 'normal',
    fontFamily: 'Arial',
    color: '#595959', // 设置文字颜色
    fontSize: 10,
    lineHeight: 24
  }
}
const defaultSeriesColor = ['#40a9ff', '#f759ab', '#ffa940',
  '#bae637', '#9254de', '#36cfc9', '#ffec3d',
  '#ff7a45', '#597ef7', '#73d13d', '#ffc53d', '#ff4d4f',
  '#69c0ff', '#ff85c0', '#ffc069', '#d3f261',
  '#b37feb', '#5cdbd3', '#fff566', '#ff9c6e',
  '#85a5ff', '#95de64', '#ffd666', '#ff7875'
]

export { defaultTooltip, defaultSeriesColor }
