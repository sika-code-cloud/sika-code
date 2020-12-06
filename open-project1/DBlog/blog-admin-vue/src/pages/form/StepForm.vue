<template>
  <div class="sc-design">
    <div class="bg-white q-pa-md">
      <div class="text-h6">
        <strong>分步表单</strong>
      </div>
      <div class="text-body2">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </div>
    <div class="q-px-md">
      <q-card square flat class="q-gutter-y-md q-mt-md q-pb-md">

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
            <q-form class="full-width" @submit="oneOnSubmit">
              <div class="row q-gutter-y-sm q-pb-sm">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md q-pt-sm"
                  :class="{ 'text-right': $q.screen.gt.xs }"
                >
                  付款账户:
                </q-item-label>
              </span>
                <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    outlined
                    v-model="stepFormData.transferData.payerAccount"
                    placeholder="付款账户"
                    dense
                    square
                    clearable
                    :rules="[ val => val && val.length > 0 || '请输入付款账户']"
                  >
                  </q-input>
                </q-item-label>
              </span>
              </div>
              <div class="row q-gutter-y-sm q-pb-sm">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right q-pt-sm': $q.screen.gt.xs }"
                >
                  收款账户:
                </q-item-label>
              </span>
                <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    outlined
                    v-model="stepFormData.transferData.payeeAccount"
                    placeholder="收款账户"
                    :rules="[ val => val && val.length > 0 || '请输入收款账户']"
                    dense
                    clearable
                    square
                  >
                  </q-input>
                </q-item-label>
              </span>
              </div>
              <div class="row q-gutter-y-sm q-pb-sm">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right q-pt-sm': $q.screen.gt.xs }"
                >
                  收款人姓名:
                </q-item-label>
              </span>
                <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    outlined
                    v-model="stepFormData.transferData.payeeName"
                    placeholder="收款人姓名"
                    :rules="[ val => val && val.length > 0 || '请输入收款人姓名']"
                    dense
                    clearable
                    square
                  >
                  </q-input>
                </q-item-label>
              </span>
              </div>
              <div class="row q-gutter-y-sm q-pb-sm">
              <span
                class="col-sm-4 col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right q-pt-sm': $q.screen.gt.xs }"
                >转账金额:</q-item-label
                >
              </span>
                <span class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <q-item-label>
                  <q-input
                    outlined
                    type="number"
                    v-model="stepFormData.transferData.amount"
                    placeholder="转账金额"
                    :rules="[ val => val && val > 0  || '转账金额应大于0']"
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
                      color="primary"
                      type="submit"
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
            </q-form>
          </q-step>
          <q-step
            :name="2"
            title="确认转账信息"
            icon="published_with_changes"
            :done="step > 2"
            :header-nav="step > 2"
          >
            <q-form class="full-width" @submit="towOnSubmit">
              <div class="row q-gutter-y-md">
                <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                  付款账户: {{ stepFormData.transferData.payerAccount }}
                </q-item-label>
                <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                  收款账户: {{ stepFormData.transferData.payeeAccount }}
                </q-item-label>
                <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                  收款人姓名: {{ stepFormData.transferData.payeeName }}
                </q-item-label>
                <q-item-label class="q-my-md offset-sm-3 col-sm-8 col-xs-12">
                  转账金额: {{ stepFormData.transferData.amount }}元
                </q-item-label>
              </div>
              <div class="row q-gutter-y-sm">
              <span
                class="offset-sm-3 col-sm-auto col-xs-12"
                :class="{ 'justify-end': $q.screen.gt.xs }"
              >
                <q-item-label
                  class="q-pr-md"
                  :class="{ 'text-right q-pt-sm': $q.screen.gt.xs }"
                >
                  支付密码:
                </q-item-label>
              </span>
                <span class="col-sm-auto col-xs-12">
                <q-item-label>
                  <q-input
                    type="password"
                    outlined
                    :rules="[
                      val => val && val.length > 0 || '请输入密码',
                      val => val && val === '123456' || '密码不正确'
                    ]"
                    v-model="stepFormData.transferData.payPassword"
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
                    type="submit"
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
            </q-form>
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
                    @click="againTransfer"
                    label="再转一笔"
                  />
                  <q-btn
                    unelevated
                    @click="searchOrder"
                    color="secondary"
                    label="查看账单"
                    class="q-ml-sm no-border-radius"
                  />
                </q-item-label>
              </div>
              <div class="text-left inline-block q-ml-xl col-sm-auto col-xs-12">
                <q-item-label class="q-py-sm">
                  付款账户: {{ stepFormData.transferData.payerAccount }}
                </q-item-label>
                <q-item-label class="q-py-sm">
                  收款账户: {{ stepFormData.transferData.payeeAccount }}
                </q-item-label>
                <q-item-label class="q-py-sm">收款人姓名: {{ stepFormData.transferData.payeeName }}</q-item-label>
                <q-item-label class="q-py-sm">转账金额: {{ stepFormData.transferData.amount }}元</q-item-label>
              </div>
            </q-card-section>
          </q-step>
        </q-stepper>
      </q-card>
    </div>
  </div>
</template>

<script>
import STEP_FORM_DATA from '@/mock/data/form/stepFormData'

export default {
  name: 'StepForm',
  data() {
    return {
      stepFormData: STEP_FORM_DATA,
      step: 1
    }
  },
  methods: {
    oneOnSubmit() {
      this.step = 2
    },
    towOnSubmit() {
      this.step = 3
    },
    againTransfer() {
      for (const key in this.stepFormData.transferData) {
        this.stepFormData.transferData[key] = null
      }
      this.step = 1
    },
    searchOrder() {
      this.$q.notify({
        position: 'top',
        color: 'info',
        textColor: 'white',
        icon: 'cloud_done',
        group: false,
        html: true,
        message: '提交成功！！'
      })
    }
  }
}
</script>

<style lang="sass">
</style>
