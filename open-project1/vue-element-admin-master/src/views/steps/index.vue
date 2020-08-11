<template>
  <div class="app-container">
    <div class="margin">
      <p>默认的步骤</p>
      <el-steps :active="active" finish-status="success">
        <el-step title="步骤 1" />
        <el-step title="步骤 2" />
        <el-step title="步骤 3" />
      </el-steps>
    </div>
    <div class="margin">
      <p>含状态步骤条</p>
      <el-steps finish-status="success" :active="active">
        <el-step title="已完成" />
        <el-step title="进行中" />
        <el-step title="待处理" />
      </el-steps>
    </div>
    <div class="margin">
      <p>有描述的步骤条</p>
      <el-steps :active="active" align-center="true">
        <el-step title="步骤一" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤二" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤三" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤四" description="这是一段很长很长很长的描述性文字" />
      </el-steps>
    </div>
    <div class="margin" style="height: 300px;">
      <p>带图标的步骤条</p>
      <el-steps :active="active" finish-status="error" direction="vertical">
        <el-step title="步骤一" icon="el-icon-circle-plus" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤二" icon="el-icon-remove" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤三" icon="el-icon-success" description="这是一段很长很长很长的描述性文字" />
        <el-step title="步骤四" icon="el-icon-error" description="这是一段很长很长很长的描述性文字" />
      </el-steps>
    </div>
    <div style="margin-top: 50px;">
      <el-button class="margin" @click="next">下一步</el-button>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Rate',
  data() {
    return {
      active: 0
    }
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0
    }
  }
}
</script>

<style>
  .margin {
    margin: 10px;
  }
</style>
