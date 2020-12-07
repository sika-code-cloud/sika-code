<template>
  <div class="sc-design">
    <div class="bg-white q-pa-md">
      <div class="text-h6">
        <strong>高级表单</strong>
      </div>
      <div class="text-body2">
        将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。
      </div>
    </div>
    <div class="q-px-md">
      <q-form>
        <q-card square flat class="q-mt-md q-pt-md q-pb-lg">
          <div class="row q-px-sm q-mx-md items-center">
            <q-item-label class="text-body1">仓库管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="row q-gutter-y-lg q-px-lg items-center">
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">仓库名</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.name"
                  placeholder="仓库名"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">仓库域名</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.domain"
                  placeholder="仓库域名"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">仓库管理员</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.admin"
                  placeholder="仓库管理员"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">审批人</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.approval"
                  placeholder="审批人"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">生效日期</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.effectiveDate"
                  placeholder="生效日期"
                  dense
                  square
                  clearable
                >
                  <template v-slot:prepend>
                    <q-icon name="event" class="cursor-pointer" color="primary">
                      <q-menu
                        square
                        :offset="[12, 10]"
                        transition-show="jump-down"
                        transition-hide="jump-up"
                      >
                        <q-date color="primary" v-model="advancedFormData.storehouseData.effectiveDate" today-btn square
                                mask="YYYY-MM-DD" />
                      </q-menu>
                    </q-icon>
                  </template>
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">仓库类型</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.storehouseData.type"
                  placeholder="仓库类型"
                  dense
                  square
                  clearable
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
        </q-card>
      </q-form>
      <q-form>
        <q-card square flat class="q-mt-lg q-pt-md q-pb-lg">
          <div class="row q-px-sm q-mx-md items-center">
            <q-item-label class="text-body1">任务管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="row q-gutter-y-lg q-px-lg items-center">
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">任务名</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.name"
                  placeholder="任务名"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">任务描述</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.desc"
                  placeholder="任务描述"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">执行人</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.executor"
                  placeholder="执行人"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 col-sm-5 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">责任人</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.personLiable"
                  placeholder="责任人"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-sm-2 col-sm-5 offset-md-1 col-md-3">
              <q-item-label class="q-pr-md q-mb-sm">生效日期</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.effectiveDate"
                  placeholder="生效日期"
                  dense
                  square
                  clearable
                >
                  <template v-slot:prepend>
                    <q-icon name="event" class="cursor-pointer" color="primary">
                      <q-menu
                        square
                        :offset="[12, 10]"
                        transition-show="jump-down"
                        transition-hide="jump-up"
                      >
                        <q-date v-model="advancedFormData.taskData.effectiveDate" today-btn square mask="YYYY-MM-DD" />
                      </q-menu>
                    </q-icon>
                  </template>
                </q-input>
              </q-item-label>
            </span>
            <span class="col-xs-12 offset-md-1 col-md-4">
              <q-item-label class="q-pr-md q-mb-sm">任务类型</q-item-label>
              <q-item-label>
                <q-input
                  outlined
                  v-model="advancedFormData.taskData.type"
                  placeholder="任务类型"
                  dense
                  square
                >
                </q-input>
              </q-item-label>
            </span>
          </div>
        </q-card>
      </q-form>
      <q-form class="sc-edit">
        <q-card square flat class="q-mt-lg q-pt-md q-pb-lg">
          <div class="q-mx-md q-px-sm">
            <q-item-label class="text-body1">成员管理</q-item-label>
          </div>
          <q-separator spaced="15px" />
          <div class="q-pt-sm q-px-lg">
            <q-table
              flat
              square
              table-header-class="bg-grey-1"
              style="border-bottom: 1px solid lightgrey"
              hide-bottom
              :data="advancedFormData.memberData.columnData"
              :columns="advancedFormData.memberData.columns"
              row-key="index"
            >
              <template v-slot:body="props">
                <q-tr :props="props">
                  <q-td key="name" :props="props" style="font-size: 10px">
                    <q-input v-if="props.row.edit" v-model="props.row.name" dense autofocus outlined />
                    <span v-else>{{ props.row.name }}</span>
                  </q-td>
                  <q-td key="jobNumber" :props="props" style="font-size: 10px">
                    <q-badge color="primary" outline class="bg-blue-1">
                      {{ props.row.jobNumber }}
                    </q-badge>
                  </q-td>
                  <q-td key="department" :props="props" style="font-size: 10px">
                    {{ props.row.department }}
                  </q-td>
                  <q-td key="operation" :props="props">
                    <q-btn v-if="props.row.edit" color="primary"  unelevated flat dense label="保存" @click="edit(props.row.index)" />
                    <q-btn v-else color="primary" unelevated flat dense label="编辑" @click="edit(props.row.index)" />
                    <q-btn color="primary" unelevated flat dense label="删除" />
                  </q-td>
                </q-tr>
              </template>
            </q-table>
            <q-btn
              unelevated
              :ripple="{ color: 'info' }"
              class="q-mt-md full-width"
              style="border: 1px dashed lightgrey"
              label="新增成员"
              icon="add"
            />
          </div>
        </q-card>
      </q-form>
    </div>
  </div>
</template>

<script>
import ADVANCED_FORM_DATA from '@/mock/data/form/advancedFormData'

export default {
  name: 'AdvancedForm',
  data() {
    return {
      advancedFormData: ADVANCED_FORM_DATA,
      editDatas: [true, true]
    }
  },
  methods: {
    edit(index) {
      this.advancedFormData.memberData.columnData[index].edit = true
    },
    save(index) {
      this.advancedFormData.memberData.columnData[index].edit = true
    }
  }
}
</script>

<style lang="sass">
.sc-design
  .sc-edit
    .q-item__section--side
      padding-right: 0

    .q-field--dense
      .q-field__control
        padding: 0 5px
        height: 24px
        font-size: 10px

      .q-field__marginal
        height: 24px

    .q-field--auto-height.q-field--dense
      .q-field__control, .q-field__native
        min-height: 24px
</style>
