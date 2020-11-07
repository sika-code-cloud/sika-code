<template>
  <div class="row items-center " :class="{'error-height': $q.screen.lt.sm}" >
    <div class="col">
      <q-card-section class="text-center">
        <q-item-section>
          <q-item-label>
            <q-img width="250px" src="https://img.yzcdn.cn/public_files/2017/11/28/a1970f9f93bdf0bb6488e2245f41d8aa.png"/>
          </q-item-label>
        </q-item-section>
        <q-item-section class="q-mt-md">
          <q-item-label class="text-h5">403</q-item-label>
          <q-item-label class="text-body2 text-grey-6">
            Sorry, you don't have access to this page.
          </q-item-label>
        </q-item-section>
        <q-item-label class="q-mt-lg">
          <q-btn color="primary" unelevated class="no-border-radius" to="/" label="返回首页">
          </q-btn>
        </q-item-label>
      </q-card-section>
    </div>
  </div>
</template>

<script>
export default {
  name: '403'
}
</script>

<style scoped>
.error-height {
  min-height: 480px;
}
</style>
