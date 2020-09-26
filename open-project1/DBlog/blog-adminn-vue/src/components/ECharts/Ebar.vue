<template>
  <div :id="chartId" :style="[{ 'width': '100%' }, { 'height': height + 'px' }]"></div>
</template>
<script>

export default {
  name: 'Ebar',
  props: {
    height: {
      default: 300
    },
    theme: {
      default: 'light'
    },
    option: {
      title: {
        text: {
          type: String,
          required: true
        }
      },
      xAxis: {
        data: {
          type: Array,
          required: true
        }
      },
      legend: {
        data: {
          type: Array,
          required: true
        }
      },
      series: {
        name: {
          type: String,
          required: true
        }
      }
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
      this.myChart = this.$echarts.init(dom, this.theme)
      // 指定图表的配置项和数据

      const optionTitle = this.option.title || {}
      const optionTooltip = this.option.tooltip
      const optionGrid = this.option.grid
      const optionXAxis = this.option.xAxis
      const optionYAxis = this.option.yAxis
      const optionLegend = this.option.legend
      const optionSeries = this.option.series
      const option = {
        title: {
          text: optionTitle.text,
          subtext: optionTitle.subtext,
          left: optionTitle.left || 'left'
        },
        tooltip: optionTooltip || {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          },
          backgroundColor: 'rgba(255,255,255,0.7)', // 设置背景图片 rgba格式
          color: 'black',
          borderWidth: '1', // 边框宽度设置1
          borderColor: 'green', // 设置边框颜色
          textStyle: {
            color: 'gray' // 设置文字颜色
          }
        },
        grid: optionGrid || {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: optionXAxis.type || 'category',
            data: optionXAxis.data,
            axisTick: optionXAxis.axisTick || {
              alignWithLabel: true
            }
          }
        ],
        yAxis: optionYAxis || [
          {
            type: 'value',
            splitLine: {
              show: false
            }
          }
        ],
        legend: {
          icon: optionLegend.icon || 'circle',
          bottom: optionLegend.bottom || 'bottom',
          tooltip: {
            show: true
          },
          data: optionLegend.data
        },
        series: [
          {
            name: optionSeries.name,
            type: 'bar',
            barWidth: optionSeries.barWidth || '35%',
            data: optionSeries.data
          }
        ]
      }
      // 使用刚指定的配置项和数据显示图表。
      this.myChart.setOption(option)
    },
    resize () {
      const _this = this
      _this.myChart.resize()
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
