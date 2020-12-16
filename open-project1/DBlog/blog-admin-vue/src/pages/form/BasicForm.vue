<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6">
        <strong>基础表单</strong>
      </div>
      <div class="text-body2">
        表单页用于向用户收集或验证信息，基础表单常见于数据项较少的表单场景。
      </div>
    </div>
    <div class="q-px-md">
      <q-card square flat class="q-gutter-y-md q-my-md q-py-sm">
        <q-form class="q-px-md full-width" @reset="onReset" @submit="onSubmit">
          <div class="row q-gutter-y-sm items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >标题:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12 sc-design">
              <q-item-label>
                <q-input
                  class="q-pb-none"
                  outlined
                  v-model="basicFormData.inputData.title"
                  placeholder="给目标起个名字"
                  dense
                  square
                  :rules="[(val) => (val && val.length > 0) || '请输入名字']"
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >起止日期:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12 sc-design">
              <q-item-label>
                <sc-date-range ref="startEndDate" />
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-start">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >目标描述:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
              <q-item-label>
                <q-input
                  class="q-pb-none"
                  outlined
                  type="textarea"
                  v-model="basicFormData.inputData.targetDesc"
                  placeholder="请输入你的阶段性工作目标"
                  dense
                  square
                  :rules="[
                    (val) => (val && val.length > 0) || '请输入阶段性工作目标'
                  ]"
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-start">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >衡量标准:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
              <q-item-label>
                <q-input
                  class="q-pb-none"
                  outlined
                  type="textarea"
                  v-model="basicFormData.inputData.measurementCriteria"
                  placeholder="请输入衡量标准"
                  dense
                  square
                  :rules="[
                    (val) => (val && val.length > 0) || '请输入衡量标准'
                  ]"
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
              >
                <span>客户</span>
                <span class="text-grey-6">
                  （选填）<q-icon name="error_outline" />
                </span>
                <span>:</span>
              </q-item-label>
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12 sc-design">
              <q-item-label>
                <q-input
                  outlined
                  v-model="basicFormData.inputData.customer"
                  placeholder="请描述你服务的客户，内部客户直接 @姓名／工号"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >邀评人<span class="text-grey-6">（选填）</span>:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12 sc-design">
              <q-item-label>
                <q-input
                  outlined
                  v-model="basicFormData.inputData.evaluator"
                  placeholder="请直接 @姓名／工号，最多可邀请 5 人"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >权重<span class="text-grey-6">（选填）</span>:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12 sc-design">
              <q-item-label>
                <q-input
                  style="width: 60%; max-width: 280px"
                  type="number"
                  outlined
                  v-model="basicFormData.inputData.weight"
                  placeholder="请输入"
                  dense
                  square
                >
                  <template v-slot:after>
                    <span class="text-body1">%</span>
                  </template>
                </q-input>
              </q-item-label>
            </span>
          </div>
          <div class="row q-gutter-y-sm q-my-md items-center">
            <span
              class="col-sm-4 col-xs-12"
              :class="{ 'justify-end': $q.screen.gt.xs }"
            >
              <q-item-label
                class="q-pr-md"
                :class="{ 'text-right': $q.screen.gt.xs }"
                >目标公开:</q-item-label
              >
            </span>
            <span class="col-xl-4 col-md-5 col-sm-6 col-xs-12">
              <q-item-label>
                <q-option-group
                  size="sm"
                  v-model="targetOption"
                  :options="basicFormData.targetOptions"
                  color="primary"
                  inline
                />
              </q-item-label>
            </span>
            <span class="offset-sm-4 col-xl-4 col-md-5 col-sm-6 col-xs-12">
              <q-item-label
                v-if="targetOption === 'partPublic'"
                class="q-mb-md sc-design"
              >
                <q-input
                  outlined
                  v-model="basicFormData.inputData.publics"
                  placeholder="公开给"
                  dense
                  square
                  :rules="[
                    (val) => (val && val.length > 0) || '请输入公开人姓名'
                  ]"
                >
                </q-input>
              </q-item-label>
              <q-item-label class="text-grey-6">
                客户、邀评人默认被分享
              </q-item-label>
            </span>
            <div
              class="q-pt-sm offset-sm-4 col-xl-4 col-md-5 col-sm-6 col-xs-12"
            >
              <div class="row q-col-gutter-x-md">
                <div class="col text-left">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    type="submit"
                    :loading="loading"
                    color="primary full-width"
                    label="保 存"
                    size="md"
                  >
                    <template v-slot:loading>
                      <q-spinner-hourglass class="on-left" />
                      保存...
                    </template>
                  </q-btn>
                </div>
                <div class="col">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    type="reset"
                    color="grey full-width"
                    label="清 除"
                    size="md"
                  >
                  </q-btn>
                </div>
              </div>
            </div>
          </div>
        </q-form>
      </q-card>
    </div>
  </div>
</template>

<script>
import ScDateRange from 'components/common/ScDateRange'
import BASIC_FORM_DATA from '@/mock/data/form/basicFormData'

export default {
  name: 'BasicForm',
  components: { ScDateRange },
  data() {
    return {
      loading: false,
      basicFormData: BASIC_FORM_DATA,
      targetOption: 'public'
    }
  },
  methods: {
    onSubmit() {
      this.$q.notify({
        position: 'top',
        color: 'info',
        textColor: 'white',
        icon: 'cloud_done',
        group: false,
        html: true,
        message: '保存成功！！'
      })
    },
    onReset() {
      for (const key in this.basicFormData.inputData) {
        this.basicFormData.inputData[key] = null
      }
      this.$refs.startEndDate.startAndEndDate = null
    }
  },
  computed: {},
  mounted() {},
  watch: {}
}
</script>

<style lang="sass"></style>
