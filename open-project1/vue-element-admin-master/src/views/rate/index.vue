<template>
  <div class="app-container">
    <div class="margin ">
      <p>默认不区分颜色</p>
      <el-rate v-model="value1" disabled="false" />
    </div>
    <div class="margin">
      <p>区分颜色</p>
      <el-rate v-model="value2" :colors="colors" show-text="true" text-color="#F7BA2A" />
    </div>
    <div class="margin">
      <p>其他icon</p>
      <el-rate v-model="value3" :icon-classes="iconClasses" void-icon-class="el-icon-goods" :colors="colors" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Rate',
  data() {
    return {
      value1: 2.6,
      value2: 5,
      value3: 3.3,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      iconClasses: ['el-icon-s-goods', 'el-icon-s-goods', 'el-icon-s-goods']
    }
  }
}
</script>

<style>
  .margin {
    margin: 10px;
  }
</style>
