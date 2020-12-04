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
        <q-form class="q-px-md full-width">
          <div class="row q-gutter-y-sm q-my-md items-center">
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
                <ScStartEndDate ref="startEndDate" />
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
                  outlined
                  type="textarea"
                  v-model="ruleName"
                  placeholder="请输入你的阶段性工作目标"
                  dense
                  square
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
                  outlined
                  type="textarea"
                  v-model="ruleName"
                  placeholder="请输入衡量标准"
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
                  v-model="ruleName"
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
                  v-model="ruleName"
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
                  v-model="ruleName"
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
                  v-model="group"
                  :options="options"
                  color="primary"
                  inline
                />
              </q-item-label>
            </span>
            <span class="offset-sm-4 col-xl-4 col-md-5 col-sm-6 col-xs-12">
              <q-item-label v-show="group === 'op2'" class="q-mb-md sc-design">
                <q-input
                  outlined
                  v-model="ruleName"
                  placeholder="公开给"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
              <q-item-label class="text-grey-6">
                客户、邀评人默认被分享
              </q-item-label>
            </span>
          </div>
        </q-form>
      </q-card>
    </div>
  </div>
</template>

<script>
import ScStartEndDate from 'components/common/ScStartEndDate'

export default {
  name: 'BasicForm',
  components: { ScStartEndDate },
  data() {
    return {
      group: 'op1',
      options: [
        {
          label: '公开',
          value: 'op1'
        },
        {
          label: '部分公开',
          value: 'op2'
        },
        {
          label: '不公开',
          value: 'op3'
        }
      ],
      startAndEndDate: this.$refs.startEndDate,
      dateStr: '',
      queryBtnStyle: {},
      queryLoad: false,
      addData: false,
      showQuery: true,
      queryStatus: '',
      visible: false,
      seamless: false,
      tableShow: true,
      loading: false,
      tableLabel: '展开',
      ruleName: '',
      selected: []
    }
  },
  methods: {
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.data.length / this.pagination.rowsPerPage)
    }
  },
  mounted() {
  },
  watch: {
  }
}
</script>

<style lang="sass">
</style>
