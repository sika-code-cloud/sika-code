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
                        <q-date
                          color="primary"
                          v-model="
                            advancedFormData.storehouseData.effectiveDate
                          "
                          today-btn
                          square
                          mask="YYYY-MM-DD"
                        />
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
                        <q-date
                          v-model="advancedFormData.taskData.effectiveDate"
                          today-btn
                          square
                          mask="YYYY-MM-DD"
                        />
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
              table-style="max-height: 400px"
              flat
              square
              table-header-class="bg-grey-1"
              style="border-bottom: 1px solid lightgrey"
              :data="advancedFormData.memberData.columnDatas"
              :columns="advancedFormData.memberData.columns"
              row-key="index"
              :pagination.sync="pagination"
              virtual-scroll
              hide-bottom
            >
              <template v-slot:body="props">
                <q-tr :props="props">
                  <q-td key="name" :props="props">
                    <q-input
                      style="max-width: 80px"
                      v-if="props.row.edit"
                      v-model="props.row.name"
                      dense
                      outlined
                    />
                    <span v-else>{{ props.row.name }}</span>
                  </q-td>
                  <q-td key="jobNumber" :props="props">
                    <q-input
                      v-if="props.row.edit"
                      v-model="props.row.jobNumber"
                      dense
                      outlined
                      style="max-width: 50px"
                    />
                    <q-badge v-else color="primary" outline class="bg-blue-1">
                      {{ props.row.jobNumber }}
                    </q-badge>
                  </q-td>
                  <q-td key="department" :props="props">
                    <q-input
                      style="max-width: 80px"
                      v-if="props.row.edit"
                      v-model="props.row.department"
                      dense
                      outlined
                    />
                    <span v-else>{{ props.row.department }}</span>
                  </q-td>
                  <q-td key="operation" :props="props">
                    <q-btn
                      v-if="props.row.edit"
                      color="primary"
                      unelevated
                      flat
                      dense
                      label="保存"
                      @click="save(props.row.index)"
                    />
                    <q-btn
                      v-else
                      color="primary"
                      unelevated
                      flat
                      dense
                      label="编辑"
                      @click="edit(props.row.index)"
                    />
                    <q-btn class="sc-design" color="primary" unelevated flat dense label="删除">
                      <q-menu
                        transition-show="jump-up"
                        transition-hide="jump-down"
                        content-class="text-center"
                        self="bottom left"
                        anchor="top left"
                        :offset="[60, 5]"
                      >
                        <div class="q-px-sm q-pt-md">
                          <q-icon name="info" color="warning" class="q-mr-sm" />
                          是否要删除此行?
                        </div>
                        <div class="q-gutter-x-sm q-pt-sm q-pb-md">
                          <q-btn
                            color="grey-6"
                            unelevated
                            class="no-border-radius text-body2"
                            padding="2px sm"
                            dense
                            label="取消"
                            v-close-popup
                          ></q-btn>
                          <q-btn
                            color="primary"
                            unelevated
                            class="no-border-radius text-body2"
                            padding="2px sm"
                            dense
                            label="确认"
                            @click="deleteData(props.row.index)"
                            v-close-popup
                          ></q-btn>
                        </div>
                      </q-menu>
                    </q-btn>
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
              @click="add"
            />
          </div>
        </q-card>
      </q-form>
    </div>
  </div>
</template>

<script>
import ADVANCED_FORM_DATA from '@/mock/data/form/advancedFormData'
import _ from 'lodash'

export default {
  name: 'AdvancedForm',
  data() {
    return {
      advancedFormData: ADVANCED_FORM_DATA,
      show: false,
      pagination: {
        page: 1,
        rowsNumber: 10
      }
    }
  },
  methods: {
    edit(index) {
      this.advancedFormData.memberData.columnDatas[index].edit = true
    },
    save(index) {
      this.advancedFormData.memberData.columnDatas[index].edit = false
    },
    add() {
      const columnDataTemp = _.clone(
        this.advancedFormData.memberData.columnDataDefault
      )
      columnDataTemp.index = this.advancedFormData.memberData.columnDatas.length

      console.log(JSON.stringify(columnDataTemp))
      this.advancedFormData.memberData.columnDatas.push(columnDataTemp)
    },
    deleteData(index) {
      this.advancedFormData.memberData.columnDatas.splice(index, 1)
      for (let i = 0; i < this.advancedFormData.memberData.columnDatas.length; ++i) {
        this.advancedFormData.memberData.columnDatas[i].index = i
      }
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
        height: 28px

      .q-field__marginal
        height: 28px

    .q-field--auto-height.q-field--dense
      .q-field__control, .q-field__native
        min-height: 28px

</style>
