<template>
  <div class="sc-design">
    <div class="bg-white q-px-lg q-pt-lg">
      <div class="row text-h6">
        <strong class="col">单号：{{ advancedData.headData.orderNo }}</strong>
        <div class="col-auto text-right">
          <span class="q-gutter-x-sm gt-xs">
            <q-btn-group unelevated>
              <q-btn
                color="secondary"
                padding="5px 15px"
                label="操作一"
                style="width: 80px"
              />
              <q-btn
                color="secondary"
                padding="5px 15px"
                style="width: 80px"
                label="操作二"
              />
              <q-btn
                padding="5px 15px"
                color="secondary"
                style="width: 60px"
                label=". . ."
              />
            </q-btn-group>
            <q-btn
              color="primary"
              padding="5px 15px"
              style="width: 80px"
              unelevated
              label="主操作"
            ></q-btn>
          </span>
          <span class="col lt-sm">
            <q-btn-dropdown
              auto-close
              unelevated
              padding="5px 15px"
              color="primary"
              label="主操作"
              split
            />
          </span>
        </div>
      </div>
      <div class="row q-gutter-y-md q-mt-sm">
        <div class="col-sm-8 col-xs-12">
          <div class="row q-gutter-y-sm">
            <span class="col-md-6 col-xs-12 q-pr-sm">创建人：{{ advancedData.headData.creator }}</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">订购产品：{{ advancedData.headData.orderProduct }}</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">创建时间：{{ advancedData.headData.createTime }}</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">关联单据：{{ advancedData.headData.relationOrderNo }}</span>
            <span class="col-md-6 col-xs-12 q-pr-sm">
              生效日期：{{ advancedData.headData.effectiveDate }}
            </span>
            <span class="col-md-6 col-xs-12 q-pr-sm">
              备注：{{ advancedData.headData.remark }}
            </span>
          </div>
        </div>
        <div class="col-sm-4 col-xs-12">
          <span class="row q-gutter-y-sm">
            <span class="col-sm-6 col-xs-4 text-grey-7">状态</span>
            <span class="col-sm-6 col-xs-8 text-grey-7">订单金额</span>
            <span class="col-sm-6 col-xs-4 text-h5">{{ advancedData.headData.state }}</span>
            <span class="col-sm-6 col-xs-8 text-h5">¥{{ advancedData.headData.orderAmount }}</span>
          </span>
        </div>
      </div>
      <q-tabs
        v-model="tab"
        class="text-primary q-ml-none q-mt-lg"
        dense
        narrow-indicator
        align="left"
        :breakpoint="0"
      >
        <q-tab name="details" class="q-px-none" label="详情" />
        <q-tab name="rule" class="q-px-none q-mx-md" label="规则" />
      </q-tabs>
    </div>
    <div class="q-pt-md q-px-md">
      <q-card flat class="q-px-sm q-gutter-y-lg no-border-radius">
        <q-card-section>
          <q-item-label class="text-body1">流程进度</q-item-label>
          <q-separator spaced="15px" />
          <q-stepper
            v-model="step"
            ref="stepper"
            color="primary"
            :vertical="$q.screen.lt.md"
            flat
          >
            <q-step
              v-for="(item, index) in advancedData.progressData"
              :key="index"
              :name="item.step"
              :class="{ 'step-height': $q.screen.lt.md }"
              :title="item.title"
              :caption="item.capital"
              :icon="item.icon"
              :done="step > item.step"
            >
            </q-step>
          </q-stepper>
        </q-card-section>
      </q-card>
      <q-card flat class="q-mt-md q-px-sm no-border-radius">
        <q-card-section>
          <q-item-label class="text-body1"> 用户信息</q-item-label>
          <q-separator spaced="15px" />
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-md-4 col-sm-6 col-xs-12">用户姓名： {{ advancedData.userInfoData.name }}</div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              会员卡号： {{ advancedData.userInfoData.membershipNo }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              身份证：{{ advancedData.userInfoData.idCard }}
            </div>
            <div class="col col-md-4 col-sm-6 col-xs-12">
              联系方式：{{ advancedData.userInfoData.phone }}
            </div>
            <div class="col col-md-8 col-sm-12 col-xs-12 q-pr-sm">
              <span class="row">
                <span class="col-auto">取货地址：</span>
                <q-item-label class="col">
                  {{ advancedData.userInfoData.pickAddress }}
                </q-item-label>
              </span>
            </div>
          </q-item-label>
        </q-card-section>
        <q-card-section>
          <q-item-label class="text-body1">
            <strong>信息组</strong>
          </q-item-label>
          <q-item-label class="row q-gutter-y-md">
            <div class="col col-sm-6 col-xs-12">某某数据： 725</div>
            <div class="col col-sm-6 col-xs-12">该数据更新时间：2017-08-08</div>
            <div class="col col-sm-6 col-xs-12">某某数据：725</div>
            <div class="col col-sm-6 col-xs-12">
              该数据更新时间：2017-08-08
            </div>
          </q-item-label>
        </q-card-section>
        <q-card-section>
          <q-item-label class="text-body2 q-mb-md"> 信息组</q-item-label>
          <q-card bordered flat class="q-mt-xs no-border-radius">
            <q-item-label class="q-pa-md bg-grey-3">
              多层级信息组
            </q-item-label>
            <q-card-section>
              <q-item-label class="text-body1">
                <strong>组名称</strong>
              </q-item-label>
              <q-item-label class="row q-gutter-y-md">
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  负责人： 林东东
                </div>
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  角色码： 1234567
                </div>
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  所属部门：XX公司 - YY部
                </div>
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  过期时间：2017-08-08
                </div>
                <div class="col col-md-8 col-sm-12 col-xs-12 q-pr-sm">
                  <span class="row">
                    <span class="col-auto">描述 ：</span>
                    <q-item-label lines="2" class="col"
                    >这段描述很长很长很长很长很长很长很长很长很长很长很长很长很长很长这段描述很长很长很长很长很长很长很长很长很长很长很长很长很长很长这段描述很长很长很长很长很长很长很长很长很长很长很长很长很长很长</q-item-label
                    >
                  </span>
                </div>
              </q-item-label>
            </q-card-section>
            <q-separator inset="" />
            <q-card-section>
              <q-item-label class="text-body1">
                <strong>组名称</strong>
              </q-item-label>
              <q-item-label class="row q-gutter-y-md">
                <div class="col q-pr-sm">
                  <span class="row">
                    <span class="col-auto">学名 ：</span>
                    <q-item-label lines="2" class="col"
                    >Citrullus lanatus (Thunb.) Matsum. et
                      Nakai一年生蔓生藤本；茎、枝粗壮，具明显的棱。卷须较粗</q-item-label
                    >
                  </span>
                </div>
              </q-item-label>
            </q-card-section>
            <q-separator inset="" />
            <q-card-section>
              <q-item-label class="text-body1">
                <strong>组名称</strong>
              </q-item-label>
              <q-item-label class="row q-gutter-y-md">
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  负责人： 林东东
                </div>
                <div class="col col-md-4 col-sm-6 col-xs-12">
                  角色码： 1234567
                </div>
              </q-item-label>
            </q-card-section>
          </q-card>
        </q-card-section>
      </q-card>
      <q-card flat class="q-mt-md q-px-sm no-border-radius">
        <q-card-section class="text-center">
          <q-item-label class="text-body1 q-mb-lg text-left">
            用户近半年来电记录
          </q-item-label>
          <q-img
            class="full-width"
            src="~assets/no_data_1.png"
            :ratio="16 / 10"
            style="max-width: 268px"
          ></q-img>
          <q-item-label>暂无数据</q-item-label>
        </q-card-section>
      </q-card>
      <q-card flat class="q-mt-md no-border-radius">
        <q-card-section>
          <q-tabs
            v-model="operatorLogTab"
            class="text-grey"
            active-color="primary"
            indicator-color="primary"
            align="left"
            narrow-indicator
          >
            <q-tab name="operatorLog1" label="操作日志一" />
            <q-tab name="operatorLog2" label="操作日志二" />
            <q-tab name="operatorLog3" label="操作日志三" />
          </q-tabs>
          <q-tab-panels v-model="operatorLogTab" animated>
            <q-tab-panel name="operatorLog1">
              <q-table
                flat
                square
                table-header-class="bg-grey-1"
                style="border-bottom: 1px solid lightgrey"
                :data="advancedData.operateLog1.datas"
                :columns="advancedData.operateLog1.columns"
                row-key="name"
              >
                <template v-slot:header="props">
                  <q-tr :props="props">
                    <q-th
                      v-for="col in props.cols"
                      :key="col.name"
                      :props="props"
                      class="text-primary"
                    >
                      <q-icon name="invert_colors" size="18px" v-if="col.name === 'operateType'"/>
                      <q-icon name="account_circle" size="18px" v-if="col.name === 'operator'"/>
                      <q-icon name="offline_pin" size="18px" v-if="col.name === 'executeResult'"/>
                      <q-icon name="av_timer" size="18px" v-if="col.name === 'operateTime'"/>
                      <q-icon name="bookmark_border" size="18px" v-if="col.name === 'remark'"/>
                      {{ col.label }}
                    </q-th>
                  </q-tr>
                </template>
                <template v-slot:body-cell-operateType="props">
                  <q-td :props="props">
                    <q-chip square outline color="negative" class="bg-red-1 no-border-radius" size="sm">
                      {{ props.value }}
                    </q-chip>
                  </q-td>
                </template>
                <template v-slot:body-cell-executeResult="props">
                  <q-td :props="props">
                     <span v-if="props.value === '通过'">
                      <q-icon
                        v-if="props.value === '通过'"
                        name="lens"
                        color="positive"
                        class="q-mr-sm"
                      />
                       <q-chip square outline color="positive" class="bg-green-1 no-border-radius" size="sm">
                        {{ props.value }}
                       </q-chip>
                     </span>
                    <span v-else-if="props.value === '驳回'">
                      <q-icon
                        name="lens"
                        color="negative"
                        class="q-mr-sm"
                      />
                      <q-chip square outline color="negative" class="bg-red-1 no-border-radius" size="sm">
                        {{ props.value }}
                      </q-chip>
                    </span>
                  </q-td>
                </template>
                <template v-slot:body-cell-remark="props">
                  <q-td :props="props">
                    <q-chip color="primary" class="bg-blue-1 no-border-radius" outline size="sm" square>
                      {{ props.value }}
                    </q-chip>
                  </q-td>
                </template>
              </q-table>
            </q-tab-panel>

            <q-tab-panel class="text-center" name="operatorLog2">
              <q-img
                class="full-width"
                src="~assets/no_data_1.png"
                :ratio="16 / 10"
                style="max-width: 268px"
              ></q-img>
              <q-item-label>暂无数据</q-item-label>
            </q-tab-panel>
            <q-tab-panel name="operatorLog3">
              <q-table
                flat
                square
                table-header-class="bg-grey-1"
                style="border-bottom: 1px solid lightgrey"
                :data="advancedData.operateLog3.datas"
                :columns="advancedData.operateLog3.columns"
                row-key="name"
              >
                <template v-slot:header="props">
                  <q-tr :props="props">
                    <q-th
                      v-for="col in props.cols"
                      :key="col.name"
                      :props="props"
                      class="text-primary"
                    >
                      <q-icon name="invert_colors" size="18px" v-if="col.name === 'operateType'"/>
                      <q-icon name="account_circle" size="18px" v-if="col.name === 'operator'"/>
                      <q-icon name="offline_pin" size="18px" v-if="col.name === 'executeResult'"/>
                      <q-icon name="av_timer" size="18px" v-if="col.name === 'operateTime'"/>
                      <q-icon name="bookmark_border" size="18px" v-if="col.name === 'remark'"/>
                      {{ col.label }}
                    </q-th>
                  </q-tr>
                </template>
                <template v-slot:body-cell-operateType="props">
                  <q-td :props="props">
                    <q-chip square outline color="primary" class="bg-blue-1 no-border-radius" size="sm">
                      {{ props.value }}
                    </q-chip>
                  </q-td>
                </template>
                <template v-slot:body-cell-executeResult="props">
                  <q-td :props="props">
                     <span v-if="props.value === '通过'">
                      <q-icon
                        v-if="props.value === '通过'"
                        name="lens"
                        color="positive"
                        class="q-mr-sm"
                      />
                       <q-chip square outline color="positive" class="bg-green-1 no-border-radius" size="sm">
                        {{ props.value }}
                       </q-chip>
                     </span>
                    <span v-else-if="props.value === '驳回'">
                      <q-icon
                        name="lens"
                        color="negative"
                        class="q-mr-sm"
                      />
                      <q-chip square outline color="negative" class="bg-red-1 no-border-radius" size="sm">
                        {{ props.value }}
                      </q-chip>
                    </span>
                  </q-td>
                </template>
                <template v-slot:body-cell-remark="props">
                  <q-td :props="props">
                    <q-chip color="primary" class="bg-blue-1 no-border-radius" outline size="sm" square>
                      {{ props.value }}
                    </q-chip>
                  </q-td>
                </template>
              </q-table>
            </q-tab-panel>
          </q-tab-panels>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script>
import ADVANCED_DATA from '@/mock/data/profile/advancedData'

export default {
  name: 'Advance',
  data() {
    return {
      advancedData: ADVANCED_DATA,
      step: 3,
      tab: 'details',
      operatorLogTab: 'operatorLog1'
    }
  }
}
</script>

<style>
</style>
