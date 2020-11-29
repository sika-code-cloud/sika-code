<template>
  <div class="row bg-white">
    <div class="col q-pb-xl text-center">
      <q-card-section class="q-mt-xl">
        <q-icon
          name="cancel"
          color="negative"
          size="70px"
        />
      </q-card-section>
      <q-card-section class="q-pt-sm">
        <p class="text-h5 q-mb-sm">
          提交失败
        </p>
        <p class="text-body1 text-grey-6">
          请核对并修改以下信息后，再重新提交。
        </p>
      </q-card-section>
      <q-card-section class="q-gutter-sm q-pt-xs">
        <q-btn unelevated color="primary" class="no-border-radius" label="返回修改"></q-btn>
      </q-card-section>
      <q-card flat square class="text-left q-ma-md q-pa-sm no-border-radius bg-grey-1">
        <q-card-section>
          <q-item-label class="text-body1 q-mb-sm">
            <strong>您提交的内容有如下错误：</strong>
          </q-item-label>
          <q-item-label class="row items-center q-my-sm">
            <q-icon name="cancel" color="negative" size="xs"/>
            <span class="q-mx-xs">您的账户已被冻结</span>
            <q-btn dense flat color="primary" label="立即解冻" unelevated icon-right="keyboard_arrow_right"/>
          </q-item-label>
          <q-item-label class="row items-center">
            <q-icon name="cancel" color="negative" size="xs"/>
            <span class="q-mx-xs">您的账户还不具备申请资格</span>
            <q-btn dense flat color="primary" label="立即升级" unelevated icon-right="keyboard_arrow_right"/>
          </q-item-label>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Fail',
  data() {
    return {
      step: '3'
    }
  }
}
</script>

<style scoped>
.step-height {
  height: 70px;
}
</style>
