<template>
  <div :id="chartId" :style="[{ 'width': '100%' }, { 'height': height + 'px' }]"></div>
</template>
<script>
import { defaultFormatter, mergerOption, defaultTooltip, defaultSeriesColor } from '@/components/ECharts/util/util'
import echarts from 'echarts'

const defaultOption = {
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
      center: ['50%', '50%'],
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

export default {
  name: 'Epie',
  props: {
    height: {
      type: Number,
      default: 400
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
      return 'Epie-' + Math.random().toString(36).substr(2)
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
