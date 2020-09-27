<template>
  <div :id="chartId" :style="[{ 'width': '100%' }, { 'height': height + 'px' }]"></div>
</template>
<script>

import { defaultFormatter, mergerOption, defaultSeriesColor, defaultTooltip } from '@/components/ECharts/util/util'
import echarts from 'echarts'

const defaultOption = {
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
      itemStyle: {
        normal: {
          lineStyle: { // 线的颜色
            color: '#40a9ff'
          },
          // 以及在折线图每个日期点顶端显示数字
          label: {
            show: true,
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

export default {
  name: 'Eline',
  props: {
    height: {
      type: Number,
      default: 200
    },
    theme: {
      type: String,
      default: 'light'
    },
    option: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      myChart: {}
    }
  },
  computed: {
    chartId: function () {
      return 'Ebar-' + Math.random().toString(36).substr(2)
    }
  },
  methods: {
    drawChart () {
      // 基于准备好的dom，初始化echarts实例
      const dom = document.getElementById(this.chartId)
      this.myChart = echarts.init(dom, this.theme)
      const mergeOption = mergerOption(defaultOption, this.option)
      this.myChart.setOption(mergeOption)
    },
    resize () {
      const _this = this
      _this.myChart.resize()
    }
  },
  watch: {
    option: {
      handler (newVal, oldVal) {
        if (this.myChart) {
          if (newVal) {
            this.myChart.setOption(newVal)
          } else {
            this.myChart.setOption(oldVal)
          }
        } else {
          this.drawChart()
        }
      },
      deep: true // 对象内部属性的监听，关键。
    }
  },
  created () {
    this.$nextTick(() => {
      this.drawChart()
    })
    window.addEventListener('resize', this.resize)
  },
  beforeDestroy: function () {
    window.removeEventListener('resize', this.resize)
  }
}
</script>
