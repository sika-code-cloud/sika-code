<template>
  <div>
    <div class="bg-white q-pa-md" style="margin: -10px -16px 0 -16px">
      <div class="text-h6">
        <strong>基础表单</strong>
      </div>
      <div class="text-body1 q-mt-md">表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。</div>
    </div>
    <div class="bg-white q-mt-md row justify-center">
      <q-list style="max-width: 360px">
        <q-item class="row items-center">
          <span class="col-auto">标题：</span>
          <span class="col">
            <q-input dense placeholder="给目标起个名字" outlined square value="" />
          </span>
        </q-item>
        <q-item class="row items-center">
          起止日期：
          <q-input dense placeholder="给目标起个名字" outlined square value="" />
        </q-item>
        <q-item class="row items-center">
          起止日期：
          <q-input dense square outlined v-model="date" mask="date">
            <template v-slot:append>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
                  <q-date v-model="date" @input="() => $refs.qDateProxy.hide()" />
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </q-item>
        <q-item class="row">
          目标描述 ：
          <q-input type="textarea" dense placeholder="给目标起个名字" outlined square value="" />
        </q-item>
        <q-item class="row items-center">
          客户（选填）：
          <q-input dense placeholder="给目标起个名字" outlined square value="" />
        </q-item>
        <q-item class="row items-center">
          邀评人（选填）：
          <q-input dense placeholder="给目标起个名字" outlined square value="" />
        </q-item>
        <q-item class="row items-center">
          权重（选填）：
          <q-input dense placeholder="给目标起个名字" outlined square value="" />
        </q-item>
        <q-item class="row items-center">
          权重（选填）：
          <q-input dense placeholder="给目标起个名字" outlined square value="" suffix="%" />
        </q-item>
        <q-item class="row items-center">
          目标公开 ：
          <q-input dense placeholder="给目标起个名字" outlined square value="" suffix="%" />
        </q-item>
        <q-item class="row items-center">
          客户、邀评人默认被分享
        </q-item>
        <q-item class="row items-center">
          <q-btn class="q-mr-sm" color="primary" label="提交" unelevated />
          <q-btn color="info" label="保存" unelevated />
        </q-item>
      </q-list>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BasicForm',
  data() {
    return {
      date: '2019/02/01'
    }
  }
}
</script>

<style scoped></style>
