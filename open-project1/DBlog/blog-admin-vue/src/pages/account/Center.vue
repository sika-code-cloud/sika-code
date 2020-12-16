<template>
  <div class="row q-px-sm">
    <div class="col-md-4 col-xs-12 q-px-sm">
      <q-card flat class="bg-white no-border-radius">
        <q-card-section class="text-center">
          <q-img
            :src="accountCenterData.introduceData.imgSrc"
            style="max-width: 140px"
          ></q-img>
          <q-item-label class="q-my-sm"
            ><strong>{{
              accountCenterData.introduceData.name
            }}</strong></q-item-label
          >
          <q-item-label>{{
            accountCenterData.introduceData.motto
          }}</q-item-label>
          <div class="row text-left q-mt-lg q-ml-sm text-body2 text-grey-9">
            <q-item-label class="col-12">
              <q-icon
                name="contact_mail"
                color="grey-9"
                size="16px"
                class="q-mr-xs"
              />
              {{ accountCenterData.introduceData.job }}
            </q-item-label>
            <q-item-label class="col-12">
              <q-icon
                name="contact_mail"
                color="grey-9"
                size="16px"
                class="q-mr-xs"
              />
              {{ accountCenterData.introduceData.department }}
            </q-item-label>
            <q-item-label class="col-12">
              <q-icon
                name="contact_mail"
                color="grey-9"
                size="16px"
                class="q-mr-xs"
              />
              {{ accountCenterData.introduceData.address }}
            </q-item-label>
          </div>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1">标签</q-item-label>
          <q-item-label class="text-body1 q-gutter-y-sm">
            <span
              :key="index"
              v-for="(item, index) in accountCenterData.labelDatas"
            >
              <q-chip
                v-if="item.outline"
                outline
                size="sm"
                :selected.sync="item.selected"
                :removable="item.removable"
                v-model="item.show"
                :color="item.color"
                :text-color="item.textColor"
                :icon="item.icon"
                :label="item.label"
                :square="item.square"
                :class="item.class"
              />
              <q-chip
                v-else
                size="sm"
                :selected.sync="item.selected"
                :removable="item.removable"
                v-model="item.show"
                :color="item.color"
                :text-color="item.textColor"
                :icon="item.icon"
                :label="item.label"
                :square="item.square"
                :class="item.class"
              />
            </span>
            <span>
              <q-chip size="sm" removable color="info" text-color="white">
                <q-avatar>
                  <q-img src="https://cdn.quasar.dev/img/avatar5.jpg" />
                </q-avatar>
                John
              </q-chip>
            </span>
          </q-item-label>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1">团队</q-item-label>
          <q-item-label class="q-gutter-sm">
            <q-chip
              size="16px"
              color="white"
              :key="index"
              v-for="(item, index) in accountCenterData.groupDatas"
            >
              <q-avatar size="sm">
                <q-img :src="item.imgSrc" />
              </q-avatar>
              {{ item.label }}
            </q-chip>
          </q-item-label>
        </q-card-section>
      </q-card>
    </div>
    <div class="col-md-8 col-xs-12 q-px-sm">
      <q-tabs
        v-model="centerTab"
        class="bg-white"
        active-color="primary"
        indicator-color="primary"
        align="left"
        content-class="text-grey-9"
        narrow-indicator
      >
        <q-tab name="titles">
          <span>
            <q-icon name="list_alt" size="sm" />
            文章({{ articlesData.articlesDatas.length }})
          </span>
        </q-tab>
        <q-tab name="applications">
          <span>
            <q-icon name="settings_applications" size="sm" />应用({{
              applicationsData.applicationsDatas.length
            }})
          </span>
        </q-tab>
        <q-tab name="projects">
          <span>
            <q-icon name="fact_check" size="sm" />项目({{
              projectsData.projectDatas.length
            }})
          </span>
        </q-tab>
      </q-tabs>
      <q-separator />
      <q-tab-panels
        v-model="centerTab"
        animated
        transition-prev="fade"
        transition-next="fade"
      >
        <q-tab-panel name="titles" class="q-px-sm q-pt-none">
          <sc-page :items="articlesData.articlesDatas" class="q-mt-sm">
            <template v-slot:item="props">
              <div class="q-pb-sm">
                <sc-shadow class="q-pa-sm">
                  <q-item-section>
                    <q-item-label class="text-body1 q-mb-sm">
                      <strong> Alipay-{{ props.item.index }} </strong>
                    </q-item-label>
                    <q-item-label class="q-mb-sm">
                      <q-chip
                        label="Sika Design"
                        class="cursor-pointer"
                        color="primary"
                        text-color="white"
                        square
                        size="12px"
                      ></q-chip>
                      <q-chip
                        label="设计语言"
                        class="cursor-pointer"
                        color="info"
                        text-color="white"
                        square
                        size="12px"
                      ></q-chip>
                      <q-chip
                        label="蚂蚁金服"
                        class="cursor-pointer"
                        color="deep-orange"
                        text-color="white"
                        square
                        size="12px"
                      ></q-chip>
                    </q-item-label>
                    <p class="text-grey-8 q-mb-sm q-pl-xs">
                      段落示意：蚂蚁金服设计平台
                      ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。蚂蚁金服设计平台
                      ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。
                    </p>
                    <q-item-label
                      class="row text-body2 text-grey-7 items-center q-gutter-x-xs"
                    >
                      <q-chip
                        color="white"
                        text-color="primary"
                        class="col-auto cursor-pointer"
                      >
                        <q-avatar size="18px">
                          <img :src="props.item.imgSrc" />
                        </q-avatar>
                        姜宇
                      </q-chip>
                      <span class="col-auto q-ml-none">发布在</span>
                      <span class="col-auto text-primary cursor-pointer"
                        >https://ant.design</span
                      >
                      <span class="col-sm-auto col-xs-12 q-pl-xs">
                        {{ props.item.publishTime }}
                      </span>
                    </q-item-label>
                    <div class="q-mt-sm row items-center" style="height: 24px">
                      <q-btn-group flat>
                        <q-btn
                          flat
                          dense
                          icon="star_border"
                          :label="props.item.collection"
                          size="12px"
                          class="q-mr-sm"
                          color="primary"
                        />
                        <q-separator vertical />
                        <q-btn
                          flat
                          dense
                          icon="thumb_up"
                          :label="props.item.star"
                          size="12px"
                          color="grey-6"
                          class="q-mx-sm"
                        />
                        <q-separator vertical />
                        <q-btn
                          flat
                          dense
                          icon="message"
                          :label="props.item.word"
                          size="12px"
                          color="grey-6"
                          class="q-mx-sm"
                        />
                      </q-btn-group>
                    </div>
                  </q-item-section>
                </sc-shadow>
              </div>
              <q-separator spaced="10px" />
            </template>
          </sc-page>
        </q-tab-panel>
        <q-tab-panel name="applications" class="q-px-sm q-pt-none">
          <sc-page
            :items="applicationsData.applicationsDatas"
            class="q-mt-sm"
            item-class="col-xl-3 col-sm-4 col-xs-12"
          >
            <template v-slot:item="props">
              <q-intersection once transition="scale">
                <sc-shadow>
                  <q-card square bordered flat>
                    <q-item>
                      <q-item-section avatar>
                        <q-avatar>
                          <img
                            style="width: 30px; height: 30px"
                            :src="props.item.imgSrc"
                          />
                        </q-avatar>
                      </q-item-section>
                      <q-item-section>
                        <q-item-label class="text-body1">
                          <strong>爱美丽-{{ props.item.index }}</strong>
                        </q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item class="block text-center q-pt-xs q-mb-sm">
                      <q-item-label class="row" caption>
                        <span class="col">活跃用户</span>
                        <span class="col">新增用户</span>
                      </q-item-label>
                      <q-item-label class="row text-body1 text-black">
                        <span class="col"
                          ><strong>{{ props.item.activeUser }}</strong
                          >万</span
                        >
                        <span class="col"
                          ><strong>{{ props.item.increaseUser }}</strong></span
                        >
                      </q-item-label>
                    </q-item>
                    <q-separator />
                    <q-item class="q-px-sm">
                      <q-btn-group flat spread class="full-width">
                        <q-btn
                          flat
                          dense
                          icon="save_alt"
                          size="sm"
                          class="q-mr-sm"
                          color="grey-6"
                        >
                          <q-tooltip>下载</q-tooltip>
                        </q-btn>
                        <q-separator vertical inset="" />
                        <q-btn
                          flat
                          dense
                          icon="edit"
                          size="sm"
                          color="grey-6"
                          class="q-mx-sm"
                        >
                          <q-tooltip>编辑</q-tooltip>
                        </q-btn>
                        <q-separator vertical inset="" />
                        <q-btn
                          flat
                          dense
                          icon="share"
                          size="sm"
                          color="grey-6"
                          class="q-mx-sm"
                        >
                          <q-tooltip>分享</q-tooltip>
                        </q-btn>
                        <q-separator vertical inset="" />
                        <q-btn
                          flat
                          dense
                          icon="more_horiz"
                          size="sm"
                          color="grey-6"
                          class="q-mx-sm"
                        >
                          <q-tooltip>更多</q-tooltip>
                        </q-btn>
                      </q-btn-group>
                    </q-item>
                  </q-card>
                </sc-shadow>
              </q-intersection>
            </template>
          </sc-page>
        </q-tab-panel>
        <q-tab-panel name="projects" class="q-px-sm q-pt-none">
          <sc-page
            :items="projectsData.projectDatas"
            class="q-mt-sm"
            item-class="col-xl-3 col-sm-4 col-xs-12"
          >
            <template v-slot:item="props">
              <q-intersection once transition="scale">
                <sc-shadow>
                  <q-card square bordered flat class="q-pb-sm">
                    <q-img :src="props.item.pictureSrc" :ratio="16 / 10" />
                    <q-list class="q-mt-md">
                      <q-item dense class="text-body1">
                        <strong>Alipay-{{ props.item.index }}</strong>
                      </q-item>
                      <q-item dense>
                        那是一种内在的东西， 他们到达不了，也无法
                      </q-item>
                      <q-item dense clickable>
                        <q-item-section style="font-size: 12px">
                          {{ props.item.hour }}小时前
                        </q-item-section>
                        <span>
                          <q-img
                            src="~assets/head.png"
                            style="width: 28px; height: 28px"
                          >
                            <q-tooltip>张三</q-tooltip>
                          </q-img>
                          <q-img
                            src="~assets/head_1.png"
                            style="
                              width: 28px;
                              height: 28px;
                              margin-left: -12px;
                            "
                          >
                            <q-tooltip>豆豆</q-tooltip>
                          </q-img>
                          <q-img
                            src="~assets/head.png"
                            style="
                              width: 28px;
                              height: 28px;
                              margin-left: -12px;
                            "
                          >
                            <q-tooltip>乐乐</q-tooltip>
                          </q-img>
                        </span>
                      </q-item>
                    </q-list>
                  </q-card>
                </sc-shadow>
              </q-intersection>
            </template>
          </sc-page>
        </q-tab-panel>
      </q-tab-panels>
    </div>
  </div>
</template>

<script>
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'
import ACCOUNT_CENTER_DATA from '@/mock/data/account/centerData'
import APPLICATIONS_DATA from '@/mock/data/list/search/applicationsData'
import PROJECTS_DATA from '@/mock/data/list/search/projectsData'
import ARTICLES_DATA from '@/mock/data/list/search/articlesData'

export default {
  name: 'Center',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      accountCenterData: ACCOUNT_CENTER_DATA,
      applicationsData: APPLICATIONS_DATA,
      projectsData: PROJECTS_DATA,
      articlesData: ARTICLES_DATA,
      centerTab: 'titles'
    }
  },
  computed: {},
  methods: {}
}
</script>

<style scoped></style>
