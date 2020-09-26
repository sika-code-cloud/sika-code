<template>
  <div :id="chartId" :style="[{ 'width': '100%' }, { 'height': height + 'px' }]"></div>
</template>
<script>

export default {
  name: 'Epie',
  props: {
    height: {
      default: 400
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
      return 'Epie-' + Math.random().toString(36).substr(2)
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
      const optionLegend = this.option.legend
      const optionSeries = this.option.series
      const option = {
        title: {
          text: optionTitle.text,
          subtext: optionTitle.subtext,
          left: optionTitle.left || 'left'
        },
        tooltip: optionTooltip || {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
          backgroundColor: 'rgba(255,255,255,0.7)', // 设置背景图片 rgba格式
          color: 'black',
          borderWidth: '1', // 边框宽度设置1
          borderColor: 'green', // 设置边框颜色
          textStyle: {
            color: 'gray' // 设置文字颜色
          }
        },
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
            type: 'pie',
            radius: optionSeries.radius || '55%',
            center: optionSeries.center || ['50%', '50%'],
            data: optionSeries.data,
            itemStyle: optionSeries.itemStyle || {
              borderWidth: 0,
              borderColor: '#fff'
            },
            emphasis: optionSeries.emphasis || {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
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
