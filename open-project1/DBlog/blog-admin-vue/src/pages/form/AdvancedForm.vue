<template>
  <div class="advanced-form">
    <div class="bg-white q-pa-md" style="margin: -16px -16px 0 -16px">
      <div class="text-h6">
        <strong>高级表单</strong>
      </div>
      <div class="text-body2">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </div>
    <q-card square flat class="q-gutter-y-md q-mt-md q-py-sm">
      <q-form class="full-width">
        <div class="row q-mx-md items-center">
          <q-item-label class="text-body1">仓库管理</q-item-label>
        </div>
        <q-separator spaced="15px" />
        <div class="row q-gutter-y-sm q-px-md items-center">
          <span
            class="col-sm-5 col-md-3"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md q-mb-sm"
            >付款账户:</q-item-label
            >
             <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="给目标起个名字"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
          <span
            class="offset-sm-2 col-sm-5 offset-md-1 col-md-3"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md q-mb-sm"
            >付款账户:</q-item-label
            >
             <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="给目标起个名字"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
          <span
            class="offset-sm-2 col-sm-5 offset-md-1 col-md-3"
            :class="{ 'justify-end': $q.screen.gt.xs }"
          >
            <q-item-label
              class="q-pr-md q-mb-sm"
            >付款账户:</q-item-label
            >
             <q-item-label>
              <q-input
                outlined
                v-model="ruleName"
                placeholder="给目标起个名字"
                dense
                square
              >
              </q-input>
            </q-item-label>
          </span>
        </div>
      </q-form>
    </q-card>
  </div>
</template>

<script>
export default {
  name: 'AdvanceForm',
  data() {
    return {
      ruleName: '',
      step: 1
    }
  }
}
</script>

<style lang="sass">
.q-field--dense
  .q-field__control
    height: 36px

.my-sticky-header-table
  /* height or max-height is important */
  max-height: 600px

  .q-table__top,
  .q-table__bottom
    /* bg color is important for th; just specify one */
    background-color: white

  thead tr:first-child th
    font-size: medium
    font-weight: bolder
    background-color: white

  thead tr th
    position: sticky
    z-index: 1

  thead tr:first-child th
    top: 0
</style>
