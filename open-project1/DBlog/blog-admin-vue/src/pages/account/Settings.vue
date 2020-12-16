<template>
  <div class="q-px-md">
    <q-card flat square>
      <q-card-section class="q-pa-none q-py-md row">
        <div class="col-sm-2 col-xs-12 q-pb-sm">
          <q-tabs
            v-model="settingsTab"
            align="left"
            active-color="primary"
            active-bg-color="blue-1"
            class="text-grey-10"
            vertical
          >
            <q-tab
              name="basicSettings"
              label="基本设置"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="safeSettings"
              label="安全设置"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="accountBind"
              label="账号绑定"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="newMsg"
              label="新消息通知"
              style="justify-content: left"
              content-class="q-pl-md"
            />
          </q-tabs>
        </div>
        <q-separator :vertical="$q.screen.gt.xs" v-show="$q.screen.gt.xs" />
        <div class="col-sm col-xs-12 q-px-md q-pt-none">
          <q-tab-panels
            v-model="settingsTab"
            animated
            transition-prev="fade"
            transition-next="fade"
          >
            <q-tab-panel name="basicSettings" class="row q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">基本设置</div>
              <div class="lt-sm col-xs-12 q-mb-md">
                <span class="text-center block">
                  <q-img
                    src="~assets/head.png"
                    width="180px"
                    :ratio="10 / 10"
                  />
                </span>
                <span class="text-center block">
                  <q-btn
                    unelevated
                    color="primary"
                    label="更换头像"
                    icon="unarchive"
                  />
                </span>
              </div>
              <div
                class="col-md-4 col-sm-5 col-xs-12 q-gutter-y-md q-pt-md q-pl-md q-pb-md"
              >
                <q-input
                  outlined
                  dense
                  square
                  label="邮箱"
                  v-model="accountSettingsData.basicSetting.email"
                />
                <q-input
                  outlined
                  dense
                  square
                  label="昵称"
                  v-model="accountSettingsData.basicSetting.nickName"
                />
                <q-input
                  type="textarea"
                  outlined
                  dense
                  square
                  label="个人简介"
                  v-model="accountSettingsData.basicSetting.personDesc"
                />
                <q-select
                  outlined
                  dense
                  square
                  behavior="menu"
                  label="国家\地区"
                  options-dense
                  :options="['中国', '韩国']"
                  v-model="accountSettingsData.basicSetting.country"
                />
                <span class="row q-gutter-x-sm">
                  <q-select
                    class="col"
                    outlined
                    dense
                    square
                    behavior="menu"
                    label="所在省份"
                    options-dense
                    :options="['湖北省', '广东省']"
                    v-model="accountSettingsData.basicSetting.province"
                  />
                  <q-select
                    class="col"
                    outlined
                    dense
                    square
                    behavior="menu"
                    label="所在城市"
                    options-dense
                    :options="['深圳市', '佛山市']"
                    v-model="accountSettingsData.basicSetting.city"
                  />
                </span>
                <q-input
                  type="text"
                  outlined
                  dense
                  square
                  label="详细地址"
                  v-model="accountSettingsData.basicSetting.address"
                />
                <span class="row q-gutter-x-sm">
                  <q-select
                    class="col-3"
                    outlined
                    dense
                    square
                    behavior="menu"
                    label="前缀"
                    options-dense
                    :options="['+86', '+87']"
                    v-model="accountSettingsData.basicSetting.phonePrefix"
                  />
                  <q-input
                    class="col"
                    outlined
                    dense
                    square
                    label="联系电话"
                    v-model="accountSettingsData.basicSetting.phone"
                  />
                </span>
                <q-btn label="更新基本信息" color="primary" unelevated />
              </div>
              <div class="gt-xs col-md-8 col-sm-7">
                <span class="text-center block">
                  <q-img
                    src="~assets/head.png"
                    width="180px"
                    :ratio="10 / 10"
                  />
                </span>
                <span class="text-center block">
                  <q-btn
                    unelevated
                    color="primary"
                    label="更换头像"
                    icon="unarchive"
                  />
                </span>
              </div>
            </q-tab-panel>
            <q-tab-panel name="safeSettings" class="q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">安全设置</div>
              <q-list class="text-body2">
                <q-item>
                  <q-item-section>
                    <q-item-label>账户密码</q-item-label>
                    <q-item-label class="text-grey-6"
                      >当前密码强度：强
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>密保手机</q-item-label>
                    <q-item-label class="text-grey-6">
                      已绑定手机：{{ accountSettingsData.safeData.phone }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>密保问题</q-item-label>
                    <q-item-label class="text-grey-6"
                      >{{ accountSettingsData.safeData.passwordQuestion }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="设置" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>备用邮箱</q-item-label>
                    <q-item-label class="text-grey-6">
                      <q-chip
                        outline
                        color="info"
                        square
                        class="bg-green-1"
                        size="sm"
                        >已绑定</q-chip
                      >
                      {{ accountSettingsData.safeData.slaveEmail }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label><strong>MFA 设备</strong></q-item-label>
                    <q-item-label class="text-grey-6"
                      >未绑定 MFA 设备，绑定后，可以进行二次确认
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
              </q-list>
            </q-tab-panel>
            <q-tab-panel name="accountBind" class="q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">账号绑定</div>
              <q-list class="text-body2">
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="orange"
                      style="cursor: pointer"
                      class="iconfont icontaobao q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定淘宝</q-item-label>
                    <q-item-label class="text-grey-6"
                      >{{ accountSettingsData.accountBindData.bindTaoBaoNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="primary"
                      style="cursor: pointer"
                      class="iconfont iconzhifubao q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定支付宝</q-item-label>
                    <q-item-label class="text-grey-6">
                      {{ accountSettingsData.accountBindData.bindZfbNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="info"
                      style="cursor: pointer"
                      class="iconfont iconweixin q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定微信</q-item-label>
                    <q-item-label class="text-grey-6"
                      >{{ accountSettingsData.accountBindData.bindWechatNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
              </q-list>
            </q-tab-panel>
            <q-tab-panel name="newMsg">
              <div class="text-h5 col-12 q-mb-md">新消息通知</div>
              <q-list class="text-body2">
                <q-item>
                  <q-item-section>
                    <q-item-label>账户密码</q-item-label>
                    <q-item-label class="text-grey-6"
                      >其他用户的消息将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.passwordMsg"
                      checked-icon="check"
                      color="primary"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>系统消息</q-item-label>
                    <q-item-label class="text-grey-6">
                      系统消息将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.systemMsg"
                      checked-icon="check"
                      color="primary"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>待办任务</q-item-label>
                    <q-item-label class="text-grey-6"
                      >待办任务将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.waitTaskMsg"
                      checked-icon="check"
                      color="red"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
              </q-list>
            </q-tab-panel>
          </q-tab-panels>
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import ACCOUNT_SETTINGS_DATA from '@/mock/data/account/settingsData'

export default {
  name: 'Settings',
  data() {
    return {
      accountSettingsData: ACCOUNT_SETTINGS_DATA,
      settingsTab: 'basicSettings'
    }
  }
}
</script>

<style scoped>
@import 'http://at.alicdn.com/t/font_2136554_1fgggi4y4wt.css';
</style>
