<template>
  <div class="row bg-white">
    <div class="col q-pb-xl text-center">
      <q-card-section class="q-mt-xl">
        <q-icon
          name="check_circle"
          color="positive"
          size="70px"
        />
      </q-card-section>
      <q-card-section class="q-pt-sm">
        <p class="text-h5 q-mb-sm">
          提交成功
        </p>
        <p class="text-body1 text-grey-6">
          提交结果页用于反馈一系列操作任务的处理结果， 如果仅是简单操作，使用 Message 全局提示反馈即可。 本文字区域可以展示简单的补充说明，如果有类似展示 “单据”的需求，下面这个灰色区域可以呈现比较复杂的内容。
        </p>
      </q-card-section>
      <q-card-section class="q-gutter-sm q-pt-xs">
        <q-btn unelevated color="primary" class="no-border-radius" label="返回列表"></q-btn>
        <q-btn unelevated color="info" class="no-border-radius" label="查看项目"></q-btn>
        <q-btn unelevated color="info" class="no-border-radius" label="打印"></q-btn>
      </q-card-section>
      <q-card flat square class="text-left q-ma-md q-pa-sm no-border-radius bg-grey-1">
        <q-card-section class="text-h6">
          <strong>项目名称</strong>
        </q-card-section>
        <q-card-section class=" text-body1">
          <div class="row q-gutter-xs">
            <span class="col-md-3 col-xs-5 text-left">
              项目ID: 23421
            </span>
            <span class="col-md-3 col-xs-5 text-left">
              负责人: 曲丽丽
            </span>
            <span class="col-md-5 col-sm-12 text-left">
              生效时间: 2016-12-12 ~ 2017-12-12
            </span>
          </div>
          <q-stepper
            v-model="step"
            class="bg-grey-1"
            ref="stepper"
            color="primary"
            :vertical="$q.screen.lt.md"
            flat
          >
            <q-step
              :name="1"
              :class="{'step-height': $q.screen.lt.md}"
              title="创建项目"
              caption="李大锤 2016-12-12 12:32"
              icon="settings"
              :done="step > 1"
            >
            </q-step>
            <q-step
              :name="2"
              title="部门初审"
              :class="{'step-height': $q.screen.lt.md}"
              caption="周毛毛 2016-12-12 12:32"
              icon="create_new_folder"
              :done="step > 2"
            >
            </q-step>
            <q-step
              :name="3"
              :class="{'step-height': $q.screen.lt.md}"
              title="财务复核"
              icon="assignment"
              :done="step > 3"
            >
            </q-step>
            <q-step
              :name="4"
              :class="{'step-height': $q.screen.lt.md}"
              title="完成"
              icon="add_comment"
            >
            </q-step>
          </q-stepper>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Success',
  data() {
    return {
      step: '3'
    }
  }
}
</script>

<style scoped>
  .step-height {
    height: 80px;
  }
</style>
