<template>
  <div class="antv-chart-mini">
    <div class="chart-wrapper" :style="{ height: 46 }">
      <v-chart :force-fit="true" :height="height" :data="dataSource" :padding="[36, 0, 18, 0] ">
        <v-tooltip :tooltip="tooltip" />
        <v-line position="month*value" :size="2" />
        <v-smooth-area :scale="scale1" position="month*value" />
      </v-chart>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
const data = []
const beginDay = new Date().getTime()

for (let i = 0; i < 10; i++) {
  data.push({
    x: moment(new Date(beginDay + 1000 * 60 * 60 * 24 * i)).format('YYYY-MM-DD'),
    y: Math.round(Math.random() * 100)
  })
}

const tooltip = [
  'month*value',
  (month, value) => ({
    name: month,
    value: value
  })
]
const scale1 = [{
  dataKey: 'value',
  min: 2
}, {
  dataKey: 'month',
  title: '时间',
  min: 1,
  max: 22
}]

export default {
  name: 'MiniArea',
  props: {
    prefixCls: {
      type: String,
      default: 'ant-pro-smooth-area'
    },
    scale: {
      type: [Object, Array],
      required: false
    },
    dataSource: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      data,
      tooltip,
      scale1,
      height: 100
    }
  }
}
</script>

<style lang="less" scoped>
  @import "chart";
</style>
