<template>
  <div>

    <div class="text-h6"><strong>查询表格</strong></div>
    <q-card square flat class="q-gutter-y-md q-mt-lg">
      <q-card-section class="row">
        <q-card-section class="col col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm">我的待办</q-item-label>
          <q-item-label class="text-h5 text-center">8个任务</q-item-label>
        </q-card-section>
        <q-separator vertical />
        <q-card-section class="col col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm">本周任务平均处理时间</q-item-label>
          <q-item-label class="text-h5 text-center">32分钟</q-item-label>
        </q-card-section>
        <q-separator vertical />
        <q-card-section class="col col-xs-12">
          <q-item-label class="text-body text-grey-6 text-center q-mb-sm">本周完成任务数</q-item-label>
          <q-item-label class="text-h5 text-center">24个任务</q-item-label>
        </q-card-section>
      </q-card-section>
    </q-card>
    <q-card square flat class="q-gutter-y-md q-mt-lg">
      <q-card-section>
        <q-item-label class="text-body1">基本列表</q-item-label>
        <q-btn-group unelevated class="no-border-radius">
          <q-btn color="primary" label="全部"></q-btn>
          <q-separator vertical/>
          <q-btn color="primary" label="进行中"></q-btn>
          <q-separator vertical/>
          <q-btn color="primary" label="等待中"></q-btn>
        </q-btn-group>
      </q-card-section>

      <q-separator inset=""/>
    </q-card>
  </div>
</template>

<script>
export default {
  name: 'BasicList'
}
</script>

<style scoped></style>
