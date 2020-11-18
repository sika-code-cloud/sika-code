<template>
  <div class="step-form">
    <div class="bg-white q-pa-md" style="margin: -16px -16px 0 -16px">
      <div class="text-h6">
        <strong>分步表单</strong>
      </div>
      <div class="text-body2">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </div>
    <q-card square flat class="q-gutter-y-md q-mt-lg q-py-lg">
      <q-form class="q-px-md full-width">
        <q-stepper
          flat
          v-model="step"
          header-nav
          ref="stepper"
          color="primary"
          animated
        >
          <q-step
            :name="1"
            title="添加转账信息"
            icon="settings"
            :done="step > 1"
            :header-nav="step > 1"
          >
            <div class="row q-gutter-y-sm q-py-lg items-center">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                >付款账户:</q-item-label
                >
              </span>
              <span class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    input-style="margin: 0px"
                    style="height: 10px"
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
            <div class="row q-gutter-y-sm q-py-lg items-center">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                >收款账户:</q-item-label
                >
              </span>
              <span class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    input-style="margin: 0px"
                    style="height: 10px"
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
            <div class="row q-gutter-y-sm q-py-lg items-center">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                >收款人姓名:</q-item-label
                >
              </span>
              <span class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    input-style="margin: 0px"
                    style="height: 10px"
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
            <div class="row q-gutter-y-sm q-py-lg items-center">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                >转账金额:</q-item-label
                >
              </span>
              <span class="col-xl-3 col-md-4 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    input-style="margin: 0px"
                    style="height: 10px"
                    outlined
                    v-model="ruleName"
                    placeholder="给目标起个名字"
                    dense
                    square
                    prefix="￥"
                  >
                  </q-input>
                </q-item-label>
              </span>
            </div>
            <q-stepper-navigation class="q-pt-sm">
              <div class="row items-center">
                <span class="offset-4 col-xl-3 col-md-4 col-sm-6 col-xs-12">
                 <q-btn unelevated class="no-border-radius" @click="() => { done1 = true; step = 2 }" color="primary"
                        label="下一步" />
                </span>
              </div>
            </q-stepper-navigation>
          </q-step>

          <q-step
            :name="2"
            title="确认转账信息"
            icon="create_new_folder"
            :done="step > 2"
            :header-nav="step > 2"
          >
            An ad group contains one or more ads which target a shared set of keywords.

            <q-stepper-navigation>
              <q-btn @click="() => { done2 = true; step = 3 }" color="primary" label="Continue" />
              <q-btn flat @click="step = 1" color="primary" label="Back" class="q-ml-sm" />
            </q-stepper-navigation>
          </q-step>

          <q-step
            :name="3"
            title="完成"
            icon="add_comment"
            :header-nav="step > 3"
          >
            Try out different ad text to see what brings in the most customers, and learn how to
            enhance your ads using features like ad extensions. If you run into any problems with
            your ads, find out how to tell if they're running and how to resolve approval issues.

            <q-stepper-navigation>
              <q-btn color="primary" @click="done3 = true" label="Finish" />
              <q-btn flat @click="step = 2" color="primary" label="Back" class="q-ml-sm" />
            </q-stepper-navigation>
          </q-step>
        </q-stepper>
      </q-form>
    </q-card>
  </div>
</template>

<script>

export default {
  name: 'StepForm',
  data() {
    return {
      ruleName: '',
      step: 1
    }
  }
}
</script>

<style lang="sass">
.step-form
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
