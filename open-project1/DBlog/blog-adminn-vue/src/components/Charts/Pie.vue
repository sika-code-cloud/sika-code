<template>
  <div>
    <v-chart :forceFit="true" :height="height" :style="{marginLeft: '-10'}" :data="data" :scale="scale">
      <v-tooltip :showTitle="false" dataKey="item*percent" />
      <v-axis />

      <v-legend dataKey="item"/>
      <v-pie position="percent" color="item" :v-style="pieStyle" :label="labelConfig" />
      <v-coord type="theta" :radius="0.9"/>
    </v-chart>
  </div>
</template>

<script>
const DataSet = require('@antv/data-set')

const data = []
const scale = [{
  dataKey: 'percent',
  min: 0,
  formatter: '.0%'
}]

export default {
  name: 'Pie',
  props: {
    dataSource: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      data,
      scale,
      height: 400,
      pieStyle: {
        stroke: '#fff',
        lineWidth: 1
      },
      labelConfig: []
    }
  },
  created () {
    const dv = new DataSet.View().source(this.dataSource)
    dv.transform({
      type: 'percent',
      field: 'count',
      dimension: 'item',
      as: 'percent'
    })
    this.data = dv.rows
  }
}
</script>
