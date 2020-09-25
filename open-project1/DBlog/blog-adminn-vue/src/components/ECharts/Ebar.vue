<template>
  <div :id="ebarId" style="width: 100%;height:200px;"></div>
</template>
<script>

export default {
  name: 'Ebar',
  data () {
    return {
      myChart: {},
      seriesData: [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220],
      xAxisData: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    }
  },
  computed: {
    ebarId: function () {
      return 'main-' + Math.random().toString(36).substr(2)
    }
  },
  methods: {
    drawChart () {
// 基于准备好的dom，初始化echarts实例
      const dom = document.getElementById(this.ebarId)
      this.myChart = this.$echarts.init(dom, 'light')
// 指定图表的配置项和数据
      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: this.xAxisData,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '直接访问',
            type: 'bar',
            barWidth: '60%',
            data: this.seriesData
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
  mounted () {
    // const _this = this
    window.onresize = function () {
      // _this.myChart
    }
  },
  beforeDestroy: function () {
    window.removeEventListener('resize', this.resize)
  }
}
</script>
