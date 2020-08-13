<template>
  <div class="app-container">
    这是进度条
    <div class="block">
      <div class="progress">
        线形进度条
      </div>
      <el-progress :percentage="50" class="progress" />
      <el-progress :percentage="100" :format="format" class="progress" />
      <el-progress :percentage="90" status="success" class="progress" />
      <el-progress :percentage="50" status="exception" class="progress" />
      <el-progress :percentage="50" status="warning" class="progress" />
    </div>
    <div class="progress">
      百分比内显
    </div>
    <div class="block">
      <el-progress :text-inside="true" :stroke-width="23" :percentage="70" class="progress" />
      <el-progress :text-inside="true" :stroke-width="24" :percentage="100" status="success" class="progress" />
      <el-progress :text-inside="true" :stroke-width="22" :percentage="80" status="exception" class="progress" />
      <el-progress :text-inside="true" :stroke-width="20" :percentage="50" status="info" class="progress" />
    </div>
    <div class="progress">
      自定义颜色
    </div>
    <div class="block">
      <el-progress :text-inside="true" :percentage="percentage" :color="customColor" class="progress" />
      <el-progress :percentage="percentage" :color="customColorMethod" class="progress" />
      <el-progress :percentage="percentage" :color="customColors" class="progress" />
      <div>
        <el-button-group>
          <el-button icon="el-icon-minus" @click="decrease" />
          <el-button icon="el-icon-plus" @click="increase" />
        </el-button-group>
      </div>
    </div>
    <div class="progress">
      环形进度条
    </div>
    <el-progress type="circle" :percentage="0" :color="customColor" class="progress" />
    <el-progress type="circle" :percentage="23" :color="customColor" class="progress" />
    <el-progress type="circle" :percentage="50" :color="customColor" class="progress" />
    <el-progress type="circle" :percentage="100" status="success" class="progress" />
    <el-progress type="circle" :percentage="70" status="warning" class="progress" />
    <el-progress type="dashboard" :percentage="70" status="warning" class="progress" />
  </div>
</template>

<script>
export default {
  name: 'Progress',
  data() {
    return {
      percentage: 20,
      customColor: '#409eff',
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ]
    }
  },
  methods: {
    format(percentage) {
      if (percentage === 100) {
        return '满'
      } else {
        return '${percentage} %'
      }
    },
    customColorMethod(percentage) {
      if (percentage < 30) {
        return '#909399'
      } else if (percentage < 70) {
        return '#e6a23c'
      } else {
        return '#67c23a'
      }
    },
    increase() {
      this.percentage += 8
      if (this.percentage > 100) {
        this.percentage = 100
      }
    },
    decrease() {
      this.percentage -= 8
      if (this.percentage < 0) {
        this.percentage = 0
      }
    }
  }
}
</script>

<style>
  .progress {
    margin: 10px;
  }
</style>
