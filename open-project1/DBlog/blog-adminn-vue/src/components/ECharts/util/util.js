import _ from 'lodash'
import echarts from 'echarts'

// --------------------------------- 默认值begin ------------------------------

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
    fontSize: 12,
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

// 默认的柱状图配置
const defaultBarOption = {
  title: {
    left: 'left'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: { // 坐标轴指示器，坐标轴触发有效
      type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    },
    backgroundColor: defaultTooltip.backgroundColor, // 设置背景图片 rgba格式
    color: defaultTooltip.color,
    borderWidth: defaultTooltip.borderWidth, // 边框宽度设置1
    borderColor: defaultTooltip.borderColor, // 设置边框颜色
    textStyle: defaultTooltip.textStyle,
    extraCssText: defaultTooltip.extraCssText,
    formatter: function (param) {
      return defaultFormatter(param, '#40a9ff')
    }
  },
  grid: {
    left: '3%',
    right: '3%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'category',
    axisTick: {
      alignWithLabel: true
    }
  },
  yAxis: {
    show: true,
    type: 'value',
    axisLine: { // y轴
      show: false
    },
    axisTick: { // y轴刻度线
      show: false
    },
    splitLine: {
      show: true,
      lineStyle: {
        type: 'dashed',
        color: '#d9d9d9'
      }
    }
  },
  legend: {
    icon: 'circle',
    bottom: 'bottom',
    tooltip: {
      show: true
    },
    data: []
  },
  series: {
    name: '',
    type: 'bar',
    color: defaultSeriesColor,
    barWidth: '45%',
    center: ['50%', '50%'],
    data: []
  }
}

// 默认的折线图配置
const defaultLineOption = {
  title: {
    left: 'left'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'line', // 'line' 直线指示器 'shadow' 阴影指示器'none' 无指示器'cross' 十字准星指示器。其实是种简写，表示启用两个正交的轴的 axisPointer
      label: {
        backgroundColor: '#6a7985'
      }
    },
    backgroundColor: defaultTooltip.backgroundColor, // 设置背景图片 rgba格式
    color: defaultTooltip.color,
    borderWidth: defaultTooltip.borderWidth, // 边框宽度设置1
    borderColor: defaultTooltip.borderColor, // 设置边框颜色
    textStyle: defaultTooltip.textStyle,
    extraCssText: defaultTooltip.extraCssText,
    formatter: function (param) {
      return defaultFormatter(param, '#40a9ff')
    }
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'category',
    boundaryGap: false
  },
  yAxis: {
    type: 'value',
    splitLine: {
      show: false
    }
  },
  legend: {
    data: []
  },
  series: {
    type: 'line',
    color: defaultSeriesColor,
    areaStyle: {},
    smooth: 0.6,
    symbol: 'emptyCircle',
    symbolSize: 1,
    itemStyle: {
      normal: {
        lineStyle: { // 线的颜色
          color: '#40a9ff'
        },
        // 以及在折线图每个日期点顶端显示数字
        label: {
          show: false,
          position: 'top',
          textStyle: {
            color: 'white'
          }
        }
      }
    },
    data: []
  }
}

// 默认的饼图配置
const defaultPieOption = {
  title: {
    left: 'left'
  },
  tooltip: {
    trigger: 'item',
    backgroundColor: defaultTooltip.backgroundColor, // 设置背景图片 rgba格式
    color: defaultTooltip.color,
    borderWidth: defaultTooltip.borderWidth, // 边框宽度设置1
    borderColor: defaultTooltip.borderColor, // 设置边框颜色
    extraCssText: defaultTooltip.extraCssText,
    textStyle: defaultTooltip.textStyle,
    formatter: function (param) {
      return defaultFormatter(param)
    }
  },
  legend: {
    icon: 'circle',
    bottom: 'bottom',
    itemHeight: 10,
    textStyle: {
      fontStyle: 'normal',
      fontFamily: 'Arial',
      color: '#595959', // 设置文字颜色
      fontSize: 13
    },
    tooltip: {
      show: true
    },
    data: []
  },
  series: {
    name: '',
    type: 'pie',
    color: defaultSeriesColor,
    radius: '55%',
    center: ['50%', '45%'],
    data: [],
    hoverAnimation: false,
    selectedMode: 'single',
    itemStyle: {
      borderWidth: 0.5,
      padding: [2, 4],
      borderColor: '#fff'
    },
    emphasis: {
      itemStyle: {
        shadowBlur: 10,
        shadowOffsetX: 0,
        shadowColor: 'rgba(0, 0, 0, 0.5)'
      }
    },
    label: {
      normal: {
        position: 'inner',
        show: false
      }
    }
  }
}

export { defaultTooltip, defaultSeriesColor, defaultBarOption, defaultLineOption, defaultPieOption }
// --------------------------------- 常量End ------------------------------

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

// 监听Option的变化进行刷新
export function watchOptionRefresh (self, newVal, oldVal) {
  if (self.myChart) {
    if (newVal) {
      self.myChart.setOption(newVal)
    } else {
      self.myChart.setOption(oldVal)
    }
  } else {
    drawChart(self)
  }
}

/** 模板方法画图 */
export function drawChart (self) {
  // 基于准备好的dom，初始化echarts实例
  const dom = document.getElementById(self.chartId)
  self.myChart = echarts.init(dom, self.theme)
  const mergeOption = mergerOption(self.defaultOption, self.option)
  self.myChart.setOption(mergeOption)
}

/** 合并option */
export function mergerOption (defaultOption, clientOption) {
  const defaultOptionClone = _.cloneDeep(defaultOption)
  return _.merge(defaultOptionClone, clientOption)
}
