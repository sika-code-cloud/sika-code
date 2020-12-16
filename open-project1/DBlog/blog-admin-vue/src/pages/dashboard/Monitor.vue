<template>
  <div class="q-px-sm">
    <div class="row">
      <div class="col-md-3 col-xs-6 text-center q-px-sm q-pb-md">
        <sc-shadow>
          <q-card flat square class="q-pa-md">
            <q-item-label class="q-mb-sm">
              <q-avatar
                color="blue-1"
                size="md"
                text-color="primary"
                class="q-mr-sm"
                icon="groups"
              ></q-avatar>
            </q-item-label>
            <q-item-label
              class="text-grey-7 q-mb-sm"
              style="font-size: x-large"
            >
              <strong>{{
                monitorData.visitNumData.visitTotal | numeral('0.0a')
              }}</strong>
            </q-item-label>
            <q-item-label caption class="q-mb-sm"> 总访问人数</q-item-label>
            <q-item-label>
              <span
                v-for="(user, index) in monitorData.visitNumData.users"
                :key="index"
              >
                <q-avatar size="22px" v-if="index === 0">
                  <img :alt="user.name" :src="user.head" />
                </q-avatar>
                <q-avatar v-else size="22px" style="margin-left: -8px">
                  <img :alt="user.name" :src="user.head" />
                </q-avatar>
                <q-tooltip
                  transition-show="flip-right"
                  transition-hide="flip-left"
                  content-class="bg-primary"
                  content-style="font-size: 10px"
                >
                  {{ user.name }}
                </q-tooltip>
              </span>
            </q-item-label>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-xs-6 text-center q-px-sm q-pb-md">
        <sc-shadow>
          <q-card flat square class="q-pa-md">
            <q-item-label class="q-mb-sm">
              <q-avatar
                color="purple-1"
                size="md"
                text-color="purple"
                class="q-mr-sm"
                icon="gps_fixed"
              ></q-avatar>
            </q-item-label>
            <q-item-label
              class="text-grey-7 q-mb-sm"
              style="font-size: x-large"
            >
              <strong>
                {{ monitorData.pointNumData.pointTotalNum | numeral('0.0a') }}
              </strong>
            </q-item-label>
            <q-item-label caption class="q-mb-sm">
              点击量（近30天）
            </q-item-label>
            <q-item-label class="text-green">
              <q-avatar
                size="sm"
                font-size="24px"
                class="q-mr-sm"
                icon="keyboard_arrow_up"
              ></q-avatar>
              <strong>{{
                monitorData.pointNumData.upRate | numeral('(0.0 %)')
              }}</strong>
            </q-item-label>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-xs-6 text-center q-px-sm q-pb-md">
        <sc-shadow>
          <q-card flat square class="q-pa-md">
            <q-item-label class="q-mb-sm">
              <q-avatar
                color="orange-1"
                size="md"
                text-color="orange"
                class="q-mr-sm"
                icon="opacity"
              ></q-avatar>
            </q-item-label>
            <q-item-label
              class="text-grey-7 q-mb-sm"
              style="font-size: x-large"
            >
              <strong>{{
                monitorData.arrivalNumData.arrivalTotalNum | numeral('0.0a')
              }}</strong>
            </q-item-label>
            <q-item-label caption class="q-mb-sm">
              到达量（近30天）
            </q-item-label>
            <q-item-label class="text-negative">
              <q-avatar
                size="sm"
                font-size="24px"
                class="q-mr-sm"
                icon="keyboard_arrow_down"
              ></q-avatar>
              <strong>{{
                monitorData.arrivalNumData.upRate | numeral('(0.0 %)')
              }}</strong>
            </q-item-label>
          </q-card>
        </sc-shadow>
      </div>
      <div class="col-md-3 col-xs-6 text-center q-px-sm q-pb-md">
        <sc-shadow>
          <q-card flat square class="q-pa-md">
            <q-item-label class="q-mb-sm">
              <q-avatar
                color="green-1"
                size="md"
                text-color="green"
                class="q-mr-sm"
                icon="filter_list"
              ></q-avatar>
            </q-item-label>
            <q-item-label
              class="text-grey-7 q-mb-sm"
              style="font-size: x-large"
            >
              <strong>{{
                monitorData.conversionData.conversionRate | numeral('(0.0 %)')
              }}</strong>
            </q-item-label>
            <q-item-label caption class="q-mb-sm">
              转化率（近30天）
            </q-item-label>
            <q-item-label class="text-green">
              <q-avatar
                size="sm"
                font-size="24px"
                class="q-mr-sm"
                icon="keyboard_arrow_up"
              ></q-avatar>
              <strong>{{
                monitorData.conversionData.upRate | numeral('(0.0 %)')
              }}</strong>
            </q-item-label>
          </q-card>
        </sc-shadow>
      </div>
    </div>
    <div class="row q-gutter-y-md">
      <div class="col-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="blue-1"
              size="sm"
              text-color="primary"
              class="q-mr-sm"
              icon="insights"
            ></q-avatar>
            <span> 最近1小时访问情况 </span>
          </q-card-section>
          <q-separator />
          <div class="row q-pa-sm">
            <div
              class="col-xs-12 col-sm-6 col-lg-3 q-pa-sm cursor-pointer"
              v-for="(item, index) in monitorData.visitForHourData"
              :key="index"
            >
              <sc-shadow>
                <q-card
                  class="no-border-radius"
                  flat
                  style="border: 1px solid lightskyblue"
                >
                  <q-item>
                    <q-item-section avatar style="min-width: 32px">
                      <q-avatar size="sm">
                        <img alt="" :src="item.img" />
                      </q-avatar>
                    </q-item-section>
                    <q-item-section>
                      <q-item-label class="text-weight-bold">
                        {{ item.title }}
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                  <q-item class="text-grey-6">
                    <q-item-label>
                      {{ item.desc }}
                    </q-item-label>
                  </q-item>
                  <q-item>
                    <q-item-section>
                      <q-item-label
                        class="text-grey-6"
                        style="font-size: small"
                      >
                        {{ item.group }}
                      </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                      <q-item-label
                        class="text-grey-6"
                        style="font-size: small"
                      >
                        {{ item.visitTime }}
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-card>
              </sc-shadow>
            </div>
          </div>
        </q-card>
      </div>
      <div class="col-sm-6 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="orange-1"
              size="sm"
              text-color="orange"
              class="q-mr-sm"
              icon="highlight"
            ></q-avatar>
            <span> 当前在线 </span>
          </q-card-section>
          <q-separator />
          <q-card
            flat
            square
            class="q-pa-md text-center"
            style="min-height: 184px"
          >
            <q-item-label caption class="q-mb-md"> 20:45:43</q-item-label>
            <q-item-label
              class="text-grey-7 q-mb-md"
              style="font-size: xx-large"
            >
              <strong>109</strong>
            </q-item-label>
            <q-item-label class="q-mb-md"> 在线访客数</q-item-label>
            <q-item-label caption class="q-mb-md">
              <q-spinner-rings color="primary" size="xs" />
              <span>1 秒后更新</span>
            </q-item-label>
          </q-card>
        </q-card>
      </div>
      <div class="col-sm-6 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="red-1"
              size="sm"
              text-color="negative"
              class="q-mr-sm"
              icon="local_cafe"
            ></q-avatar>
            <span> 当前活跃度 </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="text-center">
            <div class="row items-center">
              <q-card flat class="col-xs-6 q-ma-none" style="min-height: 184px">
                <q-knob
                  readonly
                  v-model="stepData[0]"
                  size="60px"
                  :thickness="0.22"
                  color="red"
                  track-color="red-1"
                  class="text-orange absolute-center"
                >
                </q-knob>
                <q-knob
                  readonly
                  v-model="stepData[1]"
                  size="90px"
                  :thickness="0.16"
                  color="blue"
                  track-color="blue-1"
                  class="text-orange absolute-center"
                ></q-knob>
                <q-knob
                  readonly
                  v-model="stepData[2]"
                  size="120px"
                  :thickness="0.12"
                  color="green"
                  track-color="green-1"
                  class="text-orange absolute-center"
                ></q-knob>
              </q-card>
              <q-card-section class="col-auto">
                <q-item-label>
                  <q-icon
                    class="q-mr-sm"
                    size="1em"
                    name="lens"
                    color="green"
                  />
                  <span style="font-size: small"
                    >活跃率：{{ stepData[2] }}%</span
                  >
                </q-item-label>
                <q-item-label>
                  <q-icon size="1em" class="q-mr-sm" name="lens" color="blue" />
                  <span style="font-size: small"
                    >留存率：{{ stepData[1] }}%</span
                  >
                </q-item-label>
                <q-item-label>
                  <q-icon size="1em" class="q-mr-sm" name="lens" color="red" />
                  <span style="font-size: small"
                    >跳槽率：{{ stepData[0] }}%</span
                  >
                </q-item-label>
              </q-card-section>
            </div>
          </q-card>
        </q-card>
      </div>
      <div class="col-md-6 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="orange-1"
              size="sm"
              text-color="orange"
              class="q-mr-sm"
              icon="thumb_up"
            ></q-avatar>
            <span> 平均评价 </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md" style="min-height: 251px">
            <q-item-section caption class="q-mb-md">
              <q-item-label class="inline-block" style="font-size: 48px">
                <span class="q-mr-lg">4.5</span>
                <q-rating
                  v-model="rating"
                  :max="5"
                  size="sm"
                  color="deep-orange"
                  icon="star_border"
                  icon-selected="star"
                  icon-half="star_half"
                />
              </q-item-label>
              <q-item-label class="row text-grey-6 items-center">
                <span class="text-weight-bold text-h5 q-pr-sm">- 0.0%</span>
                <span>当前评价没有任何波动</span>
              </q-item-label>
              <q-item-label>
                <div class="row">
                  <q-item-section>
                    <q-linear-progress
                      :value="0.6"
                      stripe
                      color="info"
                      size="5px"
                      rounded
                      class="q-my-sm"
                    />
                  </q-item-section>
                  <q-item-label
                    caption
                    class="q-px-sm col-sm-auto"
                    style="width: 80px"
                  >
                    <q-icon name="star"></q-icon>
                    5：368
                  </q-item-label>
                </div>
                <div class="row">
                  <q-item-section>
                    <q-linear-progress
                      :value="0.5"
                      stripe
                      color="primary"
                      size="5px"
                      rounded
                      class="q-my-sm"
                    />
                  </q-item-section>
                  <q-item-label
                    caption
                    class="q-px-sm col-sm-auto"
                    style="width: 80px"
                  >
                    <q-icon name="star"></q-icon>
                    4：256
                  </q-item-label>
                </div>
                <div class="row">
                  <q-item-section>
                    <q-linear-progress
                      :value="0.4"
                      stripe
                      color="warning"
                      size="5px"
                      rounded
                      class="q-my-sm"
                    />
                  </q-item-section>
                  <q-item-label
                    caption
                    class="q-px-sm col-sm-auto"
                    style="width: 80px"
                  >
                    <q-icon name="star"></q-icon>
                    4：49
                  </q-item-label>
                </div>
                <div class="row">
                  <q-item-section>
                    <q-linear-progress
                      :value="0.2"
                      stripe
                      color="negative"
                      size="5px"
                      rounded
                      class="q-my-sm"
                    />
                  </q-item-section>
                  <q-item-label
                    caption
                    class="q-px-sm col-sm-auto"
                    style="width: 80px"
                  >
                    <q-icon name="star"></q-icon>
                    2：14
                  </q-item-label>
                </div>
                <div class="row">
                  <q-item-section>
                    <q-linear-progress
                      :value="0.1"
                      stripe
                      color="accent"
                      size="5px"
                      rounded
                      class="q-my-sm"
                    />
                  </q-item-section>
                  <q-item-label
                    caption
                    class="q-px-sm col-sm-auto"
                    style="width: 80px"
                  >
                    <q-icon name="star"></q-icon>
                    1：0
                  </q-item-label>
                </div>
              </q-item-label>
            </q-item-section>
          </q-card>
        </q-card>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="purple-1"
              size="sm"
              text-color="purple"
              class="q-mr-sm"
              icon="tag_faces"
            ></q-avatar>
            <span> 科目满意度 </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md text-center" style="height: 251px">
            <div class="row">
              <q-item-section class="col">
                <q-item-label class="text-h5 text-grey-8"> 856</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-item-label>
                  <q-icon color="orange" name="mood" size="64px"></q-icon>
                </q-item-label>
                <q-item-label class="text-grey-7"> 正面评论</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-item-label class="text-h5 text-info"> 82%</q-item-label>
              </q-item-section>
            </div>
            <q-separator spaced="22px" />
            <div class="row">
              <q-item-section class="col">
                <q-item-label class="text-h5 text-grey-8"> 856</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-item-label>
                  <q-icon color="orange" name="mood_bad" size="64px"></q-icon>
                </q-item-label>
                <q-item-label class="text-grey-7"> 反面评论</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-item-label class="text-h5 text-negative"> 9%</q-item-label>
              </q-item-section>
            </div>
          </q-card>
        </q-card>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="red-1"
              size="sm"
              text-color="red"
              class="q-mr-sm"
              icon="track_changes"
            ></q-avatar>
            <span> 本月目标 </span>
          </q-card-section>
          <q-separator />
          <q-card flat square class="q-pa-md text-center" style="height: 251px">
            <q-item-label caption class="q-mb-md q-mt-sm">
              <q-knob
                :angle="220"
                readonly
                :max="110"
                v-model="stepData[2]"
                size="160px"
                show-value
                :thickness="0.1"
                color="blue"
                track-color="blue-1"
                class="text-primary"
              >
                <div class="row">
                  <div class="col-12">
                    <q-avatar
                      class="q-mb-sm"
                      size="md"
                      color="blue-1"
                      text-color="blue"
                      icon="military_tech"
                    ></q-avatar>
                  </div>
                  <div class="text-grey-6 col-12">{{ stepData[2] }}</div>
                </div>
              </q-knob>
            </q-item-label>
            <q-item-label class="text-grey-7 q-mb-md">
              恭喜，本月目标已达标！
            </q-item-label>
          </q-card>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script>
import ScShadow from 'components/shadow/ScShadow'
import MONITOR_DATA from '@/mock/data/dashboard/monitorData'

export default {
  name: 'Monitor',
  components: { ScShadow },
  data() {
    return {
      monitorData: MONITOR_DATA,
      rating: 4.5,
      stepData: [20, 40, 80]
    }
  }
}
</script>

<style lang="sass" scoped>
.sc-card-height
  height: 250px
</style>
