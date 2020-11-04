<template>
  <div>
    <div class="sc-base-list">
      <div class="bg-white text-h6 q-pa-md" style="margin: -16px -16px 0 -16px">
        <strong>搜索列表（文章）</strong>
      </div>
    </div>
    <div class="bg-white q-gutter-md q-mt-md q-ml-none q-pa-sm">
      <div class="row items-center">
        <span class="col-auto"> 所属类目：</span>
        <span class="col">
          <q-chip
            class="cursor-pointer no-border-radius"
            style="padding: 12px 12px"
            square
            dense
            color="primary"
            v-for="type in types"
            v-bind:key="type"
            :label="type"
            text-color="white"
          />
        </span>
      </div>
      <div class="row items-center">
        <span class="col-sm-auto col-xs-2">owner： </span>
        <span class="col-sm-auto col-xs-9">
          <q-select
            class="col-auto inline-block"
            outlined
            v-model="owner"
            square
            multiple
            dense
            options-dense
            behavior="menu"
            :options="['我自己', '张小三', '李泽瑞', '朱元璋', '姚明']"
            use-chips
            stack-label
          />
        </span>
      </div>
      <div class="row items-center">
        <span class="col-sm-auto"> 其他选项：</span>
        <span class="col-sm-9 col-xs-8">
          <span class="row q-gutter-y-sm">
            <span class="col-sm-5 col-xs-9">
              活跃用户：
              <q-select
                class="inline-block"
                style="width: 100px"
                outlined
                v-model="activeUser"
                square
                dense
                options-dense
                behavior="menu"
                :options="['张三', '张小三', '李泽瑞', '朱元璋', '姚明']"
              />
            </span>
            <span class="col-sm-5 col-xs-9">
              好评度：<q-select
                class="inline-block"
                style="width: 100px"
                outlined
                v-model="goodPing"
                square
                dense
                options-dense
                behavior="menu"
                :options="['优秀', '普通', '一般']"
              />
            </span>
          </span>
        </span>
      </div>
    </div>
    <div class="bg-white q-mt-md">
      <q-list class="no-border-radius no-border">
        <span v-for="n in 20" v-bind:key="n">
          <q-item clickable>
            <q-item-section avatar>
              <q-avatar>
                <img
                  src="https://cdn.quasar.dev/img/avatar2.jpg"
                  v-if="n % 2 === 0"
                />
                <img src="https://cdn.quasar.dev/img/avatar4.jpg" v-else />
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label lines="1">Brunch this weekend?</q-item-label>
              <q-item-label caption lines="2">
                <span class="text-weight-bold">Janet</span>
                -- I'll be in your neighborhood doing errands this weekend. Do
                you want to grab brunch?
              </q-item-label>
            </q-item-section>
            <q-item-section side top> 1 min ago </q-item-section>
          </q-item>
          <q-separator inset="item" v-show="n !== 20" />
        </span>
      </q-list>
    </div>
  </div>
</template>

<script>
const types = []
types.push('全部')
for (let i = 0; i < 11; ++i) {
  types.push('类目' + (i + 1))
}
export default {
  name: 'Articles',
  data() {
    return {
      types,
      owner: ['张小三', '李泽瑞'],
      activeUser: '张三',
      goodPing: '优秀'
    }
  }
}
</script>

<style scoped></style>
