<template>
  <div>
    <div class="bg-white q-pa-md">
      <div class="text-h6"><strong>用户列表</strong></div>
      <p class="q-mt-md text-body2 text-grey-8">
        包含用户信息的列表，带有常规操作。
      </p>
    </div>
    <div class="q-mt-md q-mx-md bg-white q-py-md text-grey-8">
      <sc-page :items="userListData.userListDatas">
        <template v-slot:item="props">
          <sc-shadow class="q-mb-md q-mx-md">
            <q-item>
              <q-item-section>
                <q-item-label class="q-mb-md">
                  <q-item-section avatar class="inline-block">
                    <q-avatar>
                      <img :src="props.item.imgSrc" />
                    </q-avatar>
                  </q-item-section>
                  <q-item-section
                    class="inline-block text-weight-bolder text-body1"
                  >
                    {{ props.item.name }}
                  </q-item-section>
                </q-item-label>
                <q-item-label>
                  <span> ID：{{ props.item.idCard }} </span>
                  <q-btn
                    icon="content_copy"
                    outline
                    label="复制"
                    style="font-size: 12px"
                    @click="copy(props.item)"
                    padding="2px 8px"
                  ></q-btn>
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  邮箱地址：{{ props.item.email }}
                </q-item-label>
                <q-item-label class="q-mb-sm">
                  <q-chip
                    v-if="props.item.verifyEmail"
                    label="已验证邮箱"
                    outline
                    text-color="green"
                    class="bg-green-1 q-ml-none"
                    style="font-size: 12px"
                    square
                  ></q-chip>
                  <q-chip
                    v-else
                    label="待验证邮箱"
                    outline
                    text-color="grey"
                    class="bg-grey-1 q-ml-none"
                    style="font-size: 12px"
                    square
                  ></q-chip>
                  <q-chip
                    :label="props.item.role"
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
                  <div class="row" v-for="(role, index) in userListData.userRoleDatas" :key="index">
                    <q-btn
                      color="primary"
                      dense
                      padding="1px 8px"
                      unelevated
                      :label="role"
                    ></q-btn>
                    <q-separator v-if="index < userListData.userRoleDatas.length - 1" inset="item-thumbnail" vertical
                                 spaced="10px" />
                  </div>
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
import { copyToClipboard } from 'quasar'
import ScShadow from 'components/shadow/ScShadow'
import ScPage from 'components/common/ScPage'
import USER_LIST_DATA from '@/mock/data/list/userListData'

export default {
  name: 'UserList',
  components: {
    ScPage,
    ScShadow
  },
  data() {
    return {
      userListData: USER_LIST_DATA
    }
  },
  methods: {
    copy(item) {
      copyToClipboard(item.idCard).then(() => {
        this.$q.notify({
          color: 'white',
          textColor: 'positive',
          icon: 'check_circle',
          position: 'top-right',
          message: 'ID【' + item.idCard + '】复制成功'
        })
      }).catch(() => {
        // 失败
      })
    }
  }
}
</script>

<style lang="sass" scoped></style>
