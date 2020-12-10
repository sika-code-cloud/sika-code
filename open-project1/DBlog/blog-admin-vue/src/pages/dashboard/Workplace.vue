<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6 q-mb-sm q-pl-sm"><strong>工作台</strong></div>
      <div class="row items-center q-gutter-y-sm">
        <div class="col-sm-auto col-xs-12">
          <q-avatar size="76px">
            <q-img ratio="1" :src="workplaceSata.currentUserData.headSrc" />
          </q-avatar>
        </div>
        <div class="col-xs-12 col-sm-9 col-md-6 q-pl-md">
          <div style="font-size: large" class="q-mb-xs">
            早安，{{ workplaceSata.currentUserData.name }}，祝你开心每一天！
          </div>
          <q-item-label class="text-grey-6 text-body"
          >{{ workplaceSata.currentUserData.job }} |
            {{ workplaceSata.currentUserData.department }}
          </q-item-label>
        </div>
        <div
          class="col-xs-12 col-md-5 text-grey-6 text-body q-gutter-x-lg"
          :class="{
            'text-right': $q.screen.gt.sm,
            'q-pl-md': $q.screen.lt.md
          }"
        >
          <q-item-label class="inline-block">
            <q-item-label>
              <q-avatar
                color="blue-1"
                size="sm"
                text-color="primary"
                class="q-mr-sm"
                icon="apps"
              ></q-avatar>
              <span>项目数</span>
            </q-item-label>
            <q-item-label
              class="text-center text-black q-pt-xs"
              style="font-size: x-large"
            >{{ workplaceSata.statisticsData.projectNum }}
            </q-item-label>
          </q-item-label>
          <q-item-label class="inline-block">
            <q-item-label>
              <q-avatar
                color="orange-1"
                size="sm"
                text-color="orange"
                class="q-mr-sm"
                icon="edit"
              ></q-avatar>
              <span>待办项</span>
            </q-item-label>
            <q-item-label
              class="text-center text-black q-pt-xs"
              style="font-size: x-large"
            >{{ workplaceSata.statisticsData.waitMatterNum }}/{{
                workplaceSata.statisticsData.allMatterNum
              }}
            </q-item-label>
          </q-item-label>
          <q-item-label class="inline-block">
            <q-item-label>
              <q-avatar
                color="green-1"
                size="sm"
                text-color="green"
                class="q-mr-sm"
                icon="visibility"
              ></q-avatar>
              <span>项目访问</span>
            </q-item-label>
            <q-item-label
              class="text-center text-black q-pt-xs"
              style="font-size: x-large"
            >
              {{
                workplaceSata.statisticsData.projectVisitNum | numeral('0,0')
              }}
            </q-item-label>
          </q-item-label>
        </div>
      </div>
    </div>
    <div class="q-mt-md row q-px-sm">
      <div class="col-lg-8 col-xs-12 q-mb-md q-px-sm">
        <q-card flat class="no-border-radius">
          <q-card-section class="bg-white">
            <q-avatar
              color="blue-1"
              size="sm"
              text-color="primary"
              class="q-mr-sm"
              icon="apps"
            ></q-avatar>
            <span> 我的项目 </span>
            <span class="float-right cursor-pointer text-primary">
              全部项目
            </span>
          </q-card-section>
          <q-separator />
          <div class="row q-pa-sm">
            <div
              class="col-xs-12 col-md-4 col-sm-6 q-pa-sm cursor-pointer"
              v-for="(item, index) in workplaceSata.myProjectDatas"
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
                        <img alt="" :src="item.imgSrc" />
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
                        {{ item.time }}
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-card>
              </sc-shadow>
            </div>
          </div>
        </q-card>

        <q-card flat class="no-border-radius q-mt-md q-pb-md">
          <q-card-section class="bg-white">
            <q-avatar
              color="purple-1"
              size="sm"
              text-color="purple"
              class="q-mr-sm"
              icon="highlight"
            ></q-avatar>
            <span> 动态 </span>
          </q-card-section>
          <q-separator />
          <q-list>
            <div
              v-for="(dynamicData, index) in workplaceSata.dynamicDatas"
              :key="index"
              class="q-mb-sm"
            >
              <q-item class="q-py-md">
                <q-item-section avatar>
                  <q-avatar>
                    <img alt="" :src="dynamicData.imgSrc" />
                  </q-avatar>
                </q-item-section>

                <q-item-section>
                  <q-item-label class="q-mb-xs">
                    <span class="text-weight-bold q-pr-xs">{{
                        dynamicData.name
                      }}</span>
                    <span>在</span>
                    <span class="text-primary q-px-xs">{{
                        dynamicData.group
                      }}</span>
                    <span>新建项目</span>
                    <span class="text-primary q-pl-xs">{{
                        dynamicData.matter
                      }}</span>
                  </q-item-label>
                  <q-item-label class="text-grey-6" lines="1">
                    {{ dynamicData.date }}
                  </q-item-label>
                </q-item-section>
              </q-item>
              <q-separator inset="" />
            </div>
          </q-list>
        </q-card>
      </div>
      <div class="col-lg-4 col-xs-12 q-px-sm">
        <q-card flat class="no-border-radius q-mb-md">
          <q-card-section class="bg-white">
            <q-avatar
              color="red-1"
              size="sm"
              text-color="red"
              class="q-mr-sm"
              icon="favorite"
            ></q-avatar>
            <span> 快捷操作 </span>
          </q-card-section>
          <q-separator />
          <q-list>
            <q-card-section class="q-mb-sm row">
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="dashboard"
                      text-color="info"
                    >
                    </q-avatar>
                    主控台
                  </q-item-section>
                </sc-shadow>
              </div>
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="list_alt"
                      text-color="primary"
                    >
                    </q-avatar>
                    <div>表单</div>
                  </q-item-section>
                </sc-shadow>
              </div>
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="grid_on"
                      text-color="warning"
                    >
                    </q-avatar>
                    列表
                  </q-item-section>
                </sc-shadow>
              </div>
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="search"
                      text-color="purple"
                    >
                    </q-avatar>
                    <div>搜索</div>
                  </q-item-section>
                </sc-shadow>
              </div>
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="settings_applications"
                      text-color="green"
                    >
                    </q-avatar>
                    设置
                  </q-item-section>
                </sc-shadow>
              </div>
              <div class="col-4">
                <sc-shadow class="q-py-md">
                  <q-item-section
                    avatar
                    style="align-items: center"
                    class="q-pr-none"
                  >
                    <q-avatar
                      font-size="28px"
                      icon="home_repair_service"
                      text-color="negative"
                    >
                    </q-avatar>
                    <div>工具</div>
                  </q-item-section>
                </sc-shadow>
              </div>
            </q-card-section>
            <q-separator inset="" />
            <div class="q-pa-md row text-center items-center q-gutter-y-sm">
              <span class="col-3">
                <q-chip square dense color="white" label="操作一" />
              </span>
              <span class="col-3">
                <q-chip square dense color="white" label="操作一" />
              </span>
              <span class="col-3">
                <q-chip square dense color="white" label="操作二" />
              </span>
              <span class="col-3">
                <q-chip square dense color="white" label="操作三" />
              </span>
              <span class="col-3">
                <q-chip square dense color="white" label="操作四" />
              </span>
              <span class="col-3">
                <q-chip square dense color="white" label="操作五" />
              </span>
              <span class="col-3">
                <q-btn
                  size="sm"
                  padding="2px 15px"
                  style="border: 1px solid deepskyblue"
                  color="primary"
                  flat
                  label="添加"
                  :ripple="false"
                ></q-btn>
              </span>
            </div>
          </q-list>
        </q-card>
        <q-card flat class="no-border-radius q-mb-md">
          <q-card-section class="bg-white">
            <q-avatar
              color="orange-1"
              size="sm"
              text-color="orange"
              class="q-mr-sm"
              icon="edit"
            ></q-avatar>
            <span> 待办事项 </span>
          </q-card-section>
          <q-separator />
          <q-table
            :data="workplaceSata.waitMatters.waitMatterDatas"
            :columns="workplaceSata.waitMatters.waitMatterColumns"
            row-key="id"
            selection="multiple"
            color="primary"
            :selected.sync="workplaceSata.waitMatters.selected"
            hide-pagination
          >
            <template v-slot:header-cell="props">
              <q-th :props="props">
                <q-icon name="lock_open" size="1.5em" />
                <strong>{{ props.col.label }}</strong>
              </q-th>
            </template>
          </q-table>
        </q-card>
        <q-card flat class="no-border-radius q-mb-md">
          <q-card-section class="bg-white">
            <q-avatar
              color="green-1"
              size="sm"
              text-color="green"
              class="q-mr-sm"
              icon="group"
            ></q-avatar>
            <span> 小组成员 </span>
          </q-card-section>
          <q-separator />
          <q-list>
            <q-item
              class="q-mb-sm"
              v-for="(item, index) in workplaceSata.teamMemberDatas"
              :key="index"
            >
              <q-item-section avatar>
                <q-avatar>
                  <q-img :src="item.hearSrc" />
                </q-avatar>
              </q-item-section>

              <q-item-section>
                <q-item-label>{{ item.name }}</q-item-label>
                <q-item-label caption lines="1">{{
                    item.nickName
                  }}
                </q-item-label>
              </q-item-section>

              <q-item-section side>
                <q-chip
                  v-if="index % 4 === 1"
                  outline
                  square
                  color="blue"
                  class="bg-blue-1"
                  :label="item.state"
                  style="width: 56px"
                />
                <q-chip
                  v-if="index % 4 === 2"
                  outline
                  square
                  color="green"
                  class="bg-green-1"
                  :label="item.state"
                  style="width: 56px"
                />
                <q-chip
                  v-if="index % 4 === 3"
                  outline
                  square
                  color="red"
                  class="bg-red-1"
                  :label="item.state"
                  style="width: 56px"
                />
                <q-chip
                  v-if="index % 4 === 0"
                  outline
                  square
                  color="purple"
                  class="bg-purple-1"
                  :label="item.state"
                  style="width: 56px"
                />
              </q-item-section>
            </q-item>
          </q-list>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import ScShadow from 'components/shadow/ScShadow'
import WORKPLACE_DATA from '@/mock/data/dashboard/workplaceData'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 6; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'Workplace',
  components: { ScShadow },
  data() {
    return {
      workplaceSata: WORKPLACE_DATA,
      items: itemsInit,
      activeClass: {
        border: '1px dashed dodgerblue'
      },
      unActiveClass: {
        border: '1px dashed lightgrey'
      },
      selected: [],
      columns: [
        {
          name: 'desc',
          required: true,
          label: '任务',
          align: 'left',
          field: (row) => row.name,
          format: (val) => `${val}`,
          sortable: true
        },
        {
          name: 'calories',
          align: 'left',
          label: '作者',
          field: 'calories',
          sortable: true
        }
      ],
      data: [
        {
          name: 'Card 支持点击，可以配置 to 等属性',
          calories: '张三'
        },
        {
          name: 'Tabs 新增属性，高度可以自适应其它高度',
          calories: '李四'
        },
        {
          name: 'Drawer 新增可拖拽调整宽度的属性',
          calories: '王大锤'
        },
        {
          name: 'AvatarList 支持配置 extra，不一定给全量数据',
          calories: '张三'
        }
      ]
    }
  }
}
</script>

<style lang="sass" scoped>
.sc-card-height
  height: 250px
</style>
