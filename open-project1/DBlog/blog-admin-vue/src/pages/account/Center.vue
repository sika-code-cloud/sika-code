<template>
  <div class="row">
    <div class="col-md-4 col-xs-12" :class="{'q-pr-md': $q.screen.gt.xs}">
      <q-card flat class="bg-white no-border-radius">
        <q-card-section class="text-center">
          <q-img src="~assets/head.png" style="max-width: 140px"></q-img>
          <q-item-label class="q-my-sm"><strong>Serati Ma</strong></q-item-label>
          <q-item-label>海纳百川，有容乃大</q-item-label>
          <div class="text-left q-mt-lg q-ml-md">
            <q-item-label class="q-my-sm">
              <q-icon name="contact_mail" size="16px" class="q-mr-xs" />
              交互专家
            </q-item-label>
            <q-item-label class="q-my-sm">
              <q-icon name="microwave" size="18px" class="q-mr-xs" />
              蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED
            </q-item-label>
            <q-item-label class="row items-center">
              <q-icon name="home" size="18px" class="q-mr-xs" />
              浙江省杭州市
            </q-item-label>
          </div>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1">标签</q-item-label>
          <q-item-label class="text-body1 q-gutter-sm">
            <q-chip size="sm" :selected.sync="desert.Cupcake" removable v-model="icecream" color="primary"
                    text-color="white" icon="event" label="很有想法的" square />
            <q-chip size="sm" color="teal" text-color="white" icon="bookmark" label="专注设计" />
            <q-chip size="sm" removable v-model="eclair" label="辣~" class="glossy" color="orange" text-color="white"
                    icon-right="star" />
            <q-chip size="sm" color="deep-orange" text-color="white" icon="directions" label="大长腿" />
            <q-chip size="sm">
              <q-avatar icon="bookmark" color="red" text-color="white" />
              川妹子
            </q-chip>
            <q-chip size="sm" removable v-model="cupcake" color="grey" text-color="white" class="glossy">
              <q-avatar color="red" text-color="white">50</q-avatar>
              海纳百川1
            </q-chip>
            <q-chip size="sm" :selected.sync="desert.Eclair" color="grey" text-color="white" class="glossy">
              <q-avatar color="red" text-color="white">50</q-avatar>
              海纳百川2
            </q-chip>
            <q-chip size="sm" :selected.sync="desert.Icecream" color="primary" text-color="white" class="glossy"
                    icon="event">
              海纳百川3
            </q-chip>
            <q-chip size="sm" removable v-model="gingerbread" color="info" text-color="white">
              <q-avatar>
                <q-img src="https://cdn.quasar.dev/img/avatar5.jpg" />
              </q-avatar>
              John
            </q-chip>
          </q-item-label>
        </q-card-section>
        <q-separator inset="" />
        <q-card-section>
          <q-item-label class="text-body1">团队</q-item-label>
          <q-item-label class="q-gutter-sm">
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png" />
              </q-avatar>
              科学搬砖组
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://cdn.quasar.dev/img/avatar5.jpg" />
              </q-avatar>
              科学搬砖组
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png" />
              </q-avatar>
              全组都是吴彦祖
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png" />
              </q-avatar>
              中二少女团
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png" />
              </q-avatar>
              程序员日常
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png" />
              </q-avatar>
              高逼格设计天团
            </q-chip>
            <q-chip size="16px" color="white">
              <q-avatar size="sm">
                <q-img src="https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png" />
              </q-avatar>
              骗你来学计算机
            </q-chip>
          </q-item-label>
        </q-card-section>
      </q-card>
    </div>
    <div class="col-md-8 col-xs-12">
        <q-tabs
          v-model="tab"
          class="bg-white"
          active-color="primary"
          indicator-color="primary"
          align="left"
          narrow-indicator
        >
          <q-tab name="mails" label="文章(8)" />
          <q-tab name="alarms" label="应用(8)" />
          <q-tab name="movies" label="项目(8)" />
        </q-tabs>
        <q-separator />
        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="mails" class="q-pa-sm">
            <q-list class="q-py-sm" >
              <q-intersection
                once
                v-for="index in 100"
                :key="index"
              >
                <q-item clickabl>
                  <q-item-section>
                    <q-item-label class="text-body1 q-mb-sm">
                      <strong>Alipay</strong>
                    </q-item-label>
                    <q-item-label class="q-mb-sm">
                      <q-chip label="Sika Design" color="primary" text-color="white" square size="12px"></q-chip>
                      <q-chip label="设计语言" color="info" text-color="white" square size="12px"></q-chip>
                      <q-chip label="蚂蚁金服" color="deep-orange" text-color="white" square size="12px"></q-chip>
                    </q-item-label>
                    <q-item-label class="text-body1 text-grey-8 q-mb-sm">
                      段落示意：蚂蚁金服设计平台 ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。蚂蚁金服设计平台 ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。
                    </q-item-label>
                    <q-item-label class="row text-body1 text-grey-7 items-center q-gutter-x-xs">
                      <q-chip color="white" text-color="primary" class="col-auto q-pa-sm cursor-pointer">
                        <q-avatar size="18px">
                          <q-img src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png" />
                        </q-avatar>
                        姜宇
                      </q-chip>
                      <span class="col-auto">发布在</span>
                      <span class="col-auto text-primary cursor-pointer">https://ant.design</span>
                      <span class="col-sm-auto col-xs-12">2020-11-14 16:15</span>
                    </q-item-label>
                    <q-item class="q-px-none">
                      <q-btn flat round dense icon="star_border" label="118" size="12px"  class="q-mr-sm" color="primary" />
                      <q-separator vertical inset="" />
                      <q-btn flat round dense icon="thumb_up" label="118" size="12px" color="grey-6" class="q-mx-sm" />
                      <q-separator vertical inset="" />
                      <q-btn flat round dense icon="message" label="118" size="12px" color="grey-6" class="q-mx-sm" />
                    </q-item>
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="15px"/>
              </q-intersection>
            </q-list>
          </q-tab-panel>
          <q-tab-panel name="alarms">
            <div class="row">
              <div
                class="col-xl-3 col-sm-4 col-xs-12"
                v-bind:key="item.id"
                v-for="item in items"
              >
                <q-intersection
                  once
                  class="q-ma-sm cursor-pointer"
                  :class="{
                  'shadow-blue text-primary ': item.select,
                  'text-grey-8': !item.select}"
                  @mouseover="select(item)"
                  @mouseleave="clear(item)"
                >
                  <q-card square bordered flat>
                    <q-img :src="item.src" :ratio="16 / 10" />
                    <q-list>
                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="primary" name="local_bar" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Bar XYZ</q-item-label>
                          <q-item-label caption>Have a drink.</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="red" name="local_gas_station" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Gas Station</q-item-label>
                          <q-item-label caption>Fill your gas tank.</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="amber" name="local_movies" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Cinema XYZ</q-item-label>
                          <q-item-label caption>Watch a movie.</q-item-label>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-card>
                </q-intersection>
              </div>
            </div>
          </q-tab-panel>
          <q-tab-panel name="movies">
            <div class="row">
              <div
                class="col-xl-3 col-sm-4 col-xs-12"
                v-bind:key="item.id"
                v-for="item in items"
              >
                <q-intersection
                  once
                  class="q-ma-sm cursor-pointer"
                  :class="{
                  'shadow-blue text-primary ': item.select,
                  'text-grey-8': !item.select}"
                  @mouseover="select(item)"
                  @mouseleave="clear(item)"
                >
                  <q-card square bordered flat>
                    <q-img :src="item.src" :ratio="16 / 10" />
                    <q-list>
                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="primary" name="local_bar" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Bar XYZ</q-item-label>
                          <q-item-label caption>Have a drink.</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="red" name="local_gas_station" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Gas Station</q-item-label>
                          <q-item-label caption>Fill your gas tank.</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item clickable>
                        <q-item-section avatar>
                          <q-icon color="amber" name="local_movies" />
                        </q-item-section>

                        <q-item-section>
                          <q-item-label>Cinema XYZ</q-item-label>
                          <q-item-label caption>Watch a movie.</q-item-label>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-card>
                </q-intersection>
              </div>
            </div>
          </q-tab-panel>
        </q-tab-panels>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'

const types = []
types.push('全部')
for (let i = 0; i < 11; ++i) {
  types.push('类目' + (i + 1))
}
const ownersDefault = [
  {
    label: '我自己',
    value: '我自己',
    icon: 'mail'
  },
  {
    label: '张小三',
    value: '张小三',
    icon: 'bluetooth'
  },
  {
    label: '李泽瑞',
    value: '李泽瑞',
    icon: 'map'
  },
  {
    label: '朱元璋',
    value: '朱元璋',
    icon: 'golf_course'
  },
  {
    label: '姚明',
    value: '姚明',
    disable: true,
    icon: 'casino'
  }
]
const srcs = [
  'https://gw.alipayobjects.com/zos/rmsportal/uMfMFlvUuceEyPpotzlq.png',
  'https://gw.alipayobjects.com/zos/rmsportal/iZBVOIhGJiAnhplqjvZW.png',
  'https://pic.rmb.bdstatic.com/fbb6939952e2e27c5cdfc5b706905768.jpeg@wm_2,t_55m+5a625Y+3L+WIhuS6q+e+juS4veeahOmbhuS4reWcsA==,fc_ffffff,ff_U2ltSGVp,sz_26,x_17,y_17',
  'https://gw.alipayobjects.com/zos/rmsportal/iXjVmWVHbCJAyqvDxdtx.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604603259601&di=98453cd17ded14a25ec805df888b2267&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191128%2F14%2F1574923905-WqopanrGSZ.jpg',
  'https://gw.alipayobjects.com/zos/rmsportal/gLaIAoVWTtLbBWZNYEMg.png',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939538&di=a457faa41c62fff3cbf0ab1fed08dcfc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201308%2F05%2F20130805105309_5E2zE.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602939540&di=329ba6679a8ac3e9feb457c5dd24e3d5&imgtype=0&src=http%3A%2F%2F00.minipic.eastday.com%2F20170415%2F20170415170933_d0304d15b93b7025b2a045c4ff65baf3_13.jpeg',
  'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604602941634&di=be2c1a81c94d87340186413a8b1bd42e&imgtype=0&src=http%3A%2F%2Ft1.hxzdhn.com%2Fuploads%2Ftu%2F201807%2F9999%2Fb488d1a5fc.jpg'
]
const itemDefault = {
  select: false
}
const itemsInit = []
for (let i = 0; i < 30; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemTemp.src = srcs[i % srcs.length]
  itemsInit.push(itemTemp)
}
export default {
  name: 'Center',
  data() {
    return {
      icecream: true,
      eclair: true,
      cupcake: true,
      gingerbread: true,
      desert: {
        Icecream: false,
        Eclair: true,
        Cupcake: false,
        Gingerbread: false
      },
      tab: 'mails',
      types,
      owner: [],
      activeUser: '张三',
      goodPing: '优秀',
      owners: ownersDefault,
      queryData: '',
      items: itemsInit,
      activeClass: {
        border: '1px dashed dodgerblue'
      },
      unActiveClass: {
        border: '1px dashed lightgrey'
      }
    }
  },
  computed: {
    selection() {
      return Object.keys(this.desert)
        .filter(type => this.desert[type] === true)
        .join(', ')
    }
  },
  methods: {
    select(item) {
      this.items[item.id].select = true
    },
    clear(item) {
      this.items[item.id].select = false
    },
    filterFn(val, update) {
      update(() => {
        if (val === '') {
          this.owners = ownersDefault
        } else {
          const needle = val
          this.owners = ownersDefault.filter(
            (v) => v.value.indexOf(needle) > -1
          )
        }
      })
    }
  }
}
</script>

<style scoped>
.shadow-blue {
  box-shadow: 0 0 8px -1px #31CCEC;
}
</style>
