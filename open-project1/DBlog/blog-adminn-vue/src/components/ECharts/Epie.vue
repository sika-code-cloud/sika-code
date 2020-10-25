<template>
  <div :id="chartId" :style="[{ 'width': '100%' }, { 'height': height + 'px' }]"></div>
</template>
<script>
import { defaultPieOption, drawChart, watchOptionRefresh } from '@/components/ECharts/util/util'

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
      defaultOption: defaultPieOption,
      myChart: {}
    }
  },
  computed: {
    chartId: function () {
      return 'Epie-' + Math.random().toString(36).substr(2)
    }
  },
  methods: {
    resize () {
      const _this = this
      _this.myChart.resize()
    }
  },
  watch: {
    option: {
      handler (newVal, oldVal) {
        watchOptionRefresh(this, newVal, oldVal)
      },
      deep: true // 对象内部属性的监听，关键。
    }
  },
  created () {
    this.$nextTick(() => {
      drawChart(this)
    })
    window.addEventListener('resize', this.resize)
  },
  beforeDestroy: function () {
    window.removeEventListener('resize', this.resize)
  }
}
</script>
