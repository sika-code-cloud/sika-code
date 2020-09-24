<template>
  <div ref="container" class="content"></div>
</template>

<script>
    import echarts from 'echarts'
    require('echarts/theme/macarons')
    export default {
        name: "ELine",
        data(){
            return{

            }
        },
        methods:{
            // 初始化echart
            initChart () {
                var myChart = echarts.init(this.$refs.container);
                myChart.setOption({
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['08-07', '08-08', '08-09', '08-10', '08-11', '08-12', '08-13'],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'总共',
                            type:'bar',
                            barWidth: '60%',
                            data:[10, 52, 200, 334, 390, 330, 220]
                        }
                    ]
                });
            }
        },
        mounted () {
            this.$nextTick(() => {
                this.initChart()
            })
        }
    }
</script>

<style scoped>
  .content{
    display: flex;
    flex-direction: row;
    align-content: center;
    align-items: center;
    height: 500px;
    width: 500px;
  }
</style>

