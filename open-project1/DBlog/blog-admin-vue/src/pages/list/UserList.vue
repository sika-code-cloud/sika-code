<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>用户列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        包含用户信息的列表，带有常规操作。
      </p>
    </div>
    <div class="q-ma-md bg-white q-py-md text-grey-8">
      <q-list>
        <div v-for="item in 10" :key="item">
          <sc-shadow class="q-mx-md q-my-md">
            <q-item>
              <q-item-section>
                <q-item-label class="q-mb-md">
                  <q-item-section avatar class="inline-block">
                    <q-avatar>
                      <img src="https://cdn.quasar.dev/img/avatar2.jpg">
                    </q-avatar>
                  </q-item-section>
                  <q-item-section class="inline-block text-weight-bolder text-body1">
                    Alipay
                  </q-item-section>
                </q-item-label>
                <q-item-label>
              <span>
                ID：123456700
              </span>
                  <q-btn icon="content_copy" outline label="复制" size="sm" style="font-size: 12px"
                         padding="2px 8px"></q-btn>
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  邮箱地址：user@iview.design
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  <q-chip size="sm" label="已验证邮箱" outline text-color="green" class="bg-green-1 q-ml-none"
                          style="font-size: 12px"
                          square></q-chip>
                  <q-chip size="sm" label="管理员" outline text-color="purple" class="bg-purple-1" style="font-size: 12px"
                          square></q-chip>
                  <q-btn icon="sort" outline label="展开操作" size="sm" style="font-size: 12px" padding="2px 8px"
                         class="q-ml-xs"></q-btn>
                </q-item-label>
                <q-item-label class="row items-center">
                  <q-btn color="primary" dense padding="1px 8px" unelevated label="普通用户"></q-btn>
                  <q-separator inset="item-thumbnail" vertical spaced="10px" />
                  <q-btn color="primary" dense padding="1px 8px" unelevated label="管理员"></q-btn>
                  <q-separator inset="item-thumbnail" vertical spaced="10px" />
                  <q-btn color="primary" dense padding="1px 8px" unelevated label="运营人员"></q-btn>
                </q-item-label>
              </q-item-section>
            </q-item>
          </sc-shadow>
          <q-separator inset="" spaced="5px" />
        </div>
      </q-list>
    </div>
    <div class="justify-center row items-center gt-xs">
      <div class="col-auto">
        <q-pagination
          v-model="current"
          :max="10"
          :max-pages="3"
          :direction-links="true"
        >
        </q-pagination>
      </div>
      <div class="col-auto">
        跳至：
        <span class="inline-block q-mr-sm">
            <q-input v-model="current" outlined dense style="width: 46px" />
          </span>页
      </div>
    </div>
    <div class="justify-center row items-center lt-sm">
      <div class="col-auto">
        <q-pagination
          v-model="current"
          :max="5"
          :input="true"
        >
        </q-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import ScShadow from 'components/shadow/ScShadow'

const itemDefault = {
  src:
    'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604435817316&di=acd6edbc1c306906444d22cfa51bccb4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Ff%2F33%2F648011013.jpg',
  select: false
}
const itemsInit = []
for (let i = 0; i < 10; ++i) {
  const itemTemp = _.clone(itemDefault)
  itemTemp.id = i
  itemsInit.push(itemTemp)
}
export default {
  name: 'UserList',
  components: { ScShadow },
  data() {
    return {
      current: 2,
      items: itemsInit
    }
  }
}
</script>

<style lang="sass" scoped>

</style>
