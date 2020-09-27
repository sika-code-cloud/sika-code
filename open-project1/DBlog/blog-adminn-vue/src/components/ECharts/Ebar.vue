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
    barWidth: '35%',
    center: ['50%', '50%'],
    data: []
  }
}

export default {
  name: 'Ebar',
  props: {
    height: {
      type: Number,
      default: 300
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
      // 指定图表的配置项和数据
      const mergeOption = mergerOption(defaultOption, this.option)
      // 使用刚指定的配置项和数据显示图表。
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
