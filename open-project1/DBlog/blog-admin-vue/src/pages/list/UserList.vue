<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>用户列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        包含用户信息的列表，带有常规操作。
      </p>
    </div>
    <div class="q-mt-md q-mx-md bg-white q-py-md text-grey-8">
      <sc-page :items="itemsInit">
        <template v-slot:item="props">
          <sc-shadow class="q-mb-md q-mx-md">
            <q-item>
              <q-item-section>
                <q-item-label class="q-mb-md">
                  <q-item-section avatar class="inline-block">
                    <q-avatar>
                      <img src="https://cdn.quasar.dev/img/avatar2.jpg" />
                    </q-avatar>
                  </q-item-section>
                  <q-item-section
                    class="inline-block text-weight-bolder text-body1"
                  >
                    Alipay{{ props.item.id }}
                  </q-item-section>
                </q-item-label>
                <q-item-label>
                  <span> ID：123456700 </span>
                  <q-btn
                    icon="content_copy"
                    outline
                    label="复制"
                    style="font-size: 12px"
                    padding="2px 8px"
                  ></q-btn>
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  邮箱地址：user@iview.design
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  <q-chip
                    label="已验证邮箱"
                    outline
                    text-color="green"
                    class="bg-green-1 q-ml-none"
                    style="font-size: 12px"
                    square
                  ></q-chip>
                  <q-chip
                    label="管理员"
                    outline
                    text-color="purple"
                    class="bg-purple-1"
                    style="font-size: 12px"
                    square
                  ></q-chip>
                  <q-btn
                    icon="sort"
                    outline
                    label="展开操作"
                    style="font-size: 12px"
                    padding="2px 8px"
                    class="q-ml-xs"
                  ></q-btn>
                </q-item-label>
                <q-item-label class="row items-center">
                  <q-btn
                    color="primary"
                    dense
                    padding="1px 8px"
                    unelevated
                    label="普通用户"
                  ></q-btn>
                  <q-separator inset="item-thumbnail" vertical spaced="10px" />
                  <q-btn
                    color="primary"
                    dense
                    padding="1px 8px"
                    unelevated
                    label="管理员"
                  ></q-btn>
                  <q-separator inset="item-thumbnail" vertical spaced="10px" />
                  <q-btn
                    color="primary"
                    dense
                    padding="1px 8px"
                    unelevated
                    label="运营人员"
                  ></q-btn>
                </q-item-label>
              </q-item-section>
            </q-item>
          </sc-shadow>
          <q-separator inset="" spaced="5px" />
        </template>
      </sc-page>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 110; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'UserList',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      current: 2,
      itemsInit
    }
  }
}
</script>

<style lang="sass" scoped></style>
