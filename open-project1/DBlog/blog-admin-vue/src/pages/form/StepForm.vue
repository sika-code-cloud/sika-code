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
    <q-card square flat class="q-gutter-y-md q-mt-lg q-pb-md">
      <q-form class="full-width">
        <q-stepper
          flat
          v-model="step"
          header-nav
          ref="stepper"
          color="primary"
          animated
          :vertical="$q.screen.lt.sm"
        >
          <q-step
            :name="1"
            title="添加转账信息"
            icon="settings"
            :done="step > 1"
            :header-nav="step > 1"
          >
            <div class="row q-gutter-y-sm q-py-md items-center">
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
              <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
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
            <div class="row q-gutter-y-sm q-py-md items-center">
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
              <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
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
            <div class="row q-gutter-y-sm q-py-md items-center">
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
              <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
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
            <div class="row q-gutter-y-sm q-py-md items-center">
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
              <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
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
                <span
                  class="offset-sm-4 col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12"
                >
                  <q-btn
                    unelevated
                    class="no-border-radius"
                    @click="
                      () => {
                        done1 = true
                        step = 2
                      }
                    "
                    color="primary"
                    label="下一步"
                  />
                </span>
              </div>
            </q-stepper-navigation>
            <q-separator spaced="20px" />
            <q-item-label class="text-grey-6 q-mt-md">
              <div class="text-body1 q-mb-md">说明</div>
              <div class="text-body2 q-my-xs">转账到支付宝账户</div>
              <div class="text-body2">
                如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。
              </div>
              <div class="text-body2 q-mt-md q-mb-xs">转账到银行卡</div>
              <div class="text-body2">
                如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。
              </div>
            </q-item-label>
          </q-step>

          <q-step
            :name="2"
            title="确认转账信息"
            icon="create_new_folder"
            :done="step > 2"
            :header-nav="step > 2"
          >
            <div class="row q-gutter-y-md">
              <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                付款账户: ant-design@alipay.com
              </q-item-label>
              <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                收款账户: test@example.com
              </q-item-label>
              <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12"
                >收款人姓名: Alex
              </q-item-label>
              <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12"
                >转账金额: 500元
              </q-item-label>
            </div>
            <div class="row items-center q-gutter-y-sm">
              <span
                class="offset-sm-3 col-sm-auto col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                  >支付密码:</q-item-label
                >
              </span>
              <span class="col-sm-auto col-xs-12">
                <q-item-label>
                  <q-input
                    type="password"
                    outlined
                    v-model="ruleName"
                    dense
                    square
                  >
                  </q-input>
                </q-item-label>
              </span>
            </div>
            <q-stepper-navigation>
              <div class="row items-center">
                <span class="offset-sm-3 col-sm-auto col-xs-12">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    @click="
                      () => {
                        done2 = true
                        step = 3
                      }
                    "
                    color="primary"
                    label="提交"
                  />
                  <q-btn
                    flat
                    unelevated
                    @click="step = 1"
                    color="secondary"
                    label="上一步"
                    class="q-ml-sm no-border-radius"
                  />
                </span>
              </div>
            </q-stepper-navigation>
          </q-step>

          <q-step
            :name="3"
            title="完成"
            icon="add_comment"
            :header-nav="step > 3"
          >
            <q-card-section class="row items-center justify-center q-gutter-md">
              <div class="inline-block text-center col-sm-auto col-xs-12">
                <q-item-label class="q-pb-sm">
                  <q-icon name="check_circle" color="positive" size="70px" />
                </q-item-label>
                <q-item-label class="text-h6 q-py-sm">操作成功</q-item-label>
                <q-item-label caption>预计两小时内到账</q-item-label>
                <q-item-label class="q-pt-lg">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    color="primary"
                    @click="step = 1"
                    label="再转一笔"
                  />
                  <q-btn
                    unelevated
                    @click="step = 1"
                    color="secondary"
                    label="查看账单"
                    class="q-ml-sm no-border-radius"
                  />
                </q-item-label>
              </div>
              <div class="text-left inline-block q-ml-xl col-sm-auto col-xs-12">
                <q-item-label class="q-py-sm"
                  >付款账户: ant-design@alipay.com
                </q-item-label>
                <q-item-label class="q-py-sm"
                  >收款账户: test@example.com
                </q-item-label>
                <q-item-label class="q-py-sm">收款人姓名: Alex </q-item-label>
                <q-item-label class="q-py-sm">转账金额: 500元 </q-item-label>
              </div>
            </q-card-section>
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
