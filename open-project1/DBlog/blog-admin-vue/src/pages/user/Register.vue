<template>
  <div>
    <div class="q-px-md">
      <q-form @submit="onSubmit" @reset="onReset">
        <div class="q-gutter-y-md">
          <div class="text-left text-body1">注册</div>
          <div class="row">
            <div class="col-12 q-gutter-y-sm">
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                v-model="email"
                dense
                label="邮箱"
                maxlength="128"
                type="email"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '请输入邮箱']"
              >
                <template v-slot:prepend>
                  <q-icon name="mail" />
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                :type="isPwd ? 'password' : 'text'"
                v-model="password"
                dense
                label="密码"
                maxlength="32"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '请输入密码']"
              >
                <template v-slot:prepend>
                  <q-icon name="event" />
                </template>
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                :type="isPwd ? 'password' : 'text'"
                v-model="confirmPassword"
                dense
                label="确认密码"
                maxlength="32"
                lazy-rules
                square
                :rules="[
                  (val) => (val && val.length > 0) || '请输入确认密码',
                  (val) => passwordValida || '两次密码不一致'
                ]"
              >
                <template v-slot:prepend>
                  <q-icon name="event" />
                </template>
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                v-model="phone"
                maxlength="11"
                type="tel"
                dense
                label="手机号"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '请输入手机号']"
              >
                <template v-slot:prepend>
                  <q-btn-dropdown
                    color="secondary"
                    flat
                    :label="phonePrefix"
                    style="margin-left: -12px"
                  >
                    <q-list>
                      <q-item
                        clickable
                        v-close-popup
                        @click="onItemClick('+86')"
                      >
                        <q-item-section>
                          <q-item-label>+86</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item
                        clickable
                        v-close-popup
                        @click="onItemClick('+87')"
                      >
                        <q-item-section>
                          <q-item-label>+87</q-item-label>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-btn-dropdown>
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                type="text"
                maxlength="6"
                v-model="validateCode"
                dense
                label="验证码"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '请输入验证码']"
              >
                <template v-slot:prepend>
                  <q-icon name="event" />
                </template>
                <template v-slot:after>
                  <q-btn glossy color="secondary" label="获取验证码" />
                </template>
              </q-input>
              <div class="row">
                <div class="col text-left">
                  <q-btn
                    glossy
                    type="submit"
                    color="primary full-width"
                    label="注 册"
                    size="md"
                  />
                </div>
                <div class="col text-right">
                  <q-btn
                    to="/user/login"
                    color="primary"
                    flat
                    label="使用已有账户登录"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </q-form>
      <q-dialog v-model="card" persistent >
        <q-card class="my-card text-center q-col-gutter-y-lg q-px-md" style="width: 600px; max-width: 80vw;" >
          <q-icon class="q-mt-md text-h4" name="check_circle" color="positive" size="60px"/>
          <div>
            <div class="text-h6">账户：<span>{{email}} </span> 注册成功</div>
            <div class="text-grey" > 注册验证短信已经发送至该邮箱中，邮件有效期为24小时。请及时登录邮箱，点击邮件中的链接验证账户。</div>
          </div>
          <q-card-actions align="center" class="q-mb-md">
            <q-btn v-close-popup glossy color="primary" label="查看邮箱" />
            <q-btn v-close-popup glossy color="secondary" label="返回首页" />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      tab: 'mails',
      email: null,
      password: null,
      confirmPassword: null,
      phone: null,
      validateCode: null,
      accept: false,
      isPwd: true,
      autoLogin: true,
      dense: false,
      phonePrefix: '+86',
      card: false
    }
  },
  methods: {
    onSubmit() {
      this.$q.notify({
        color: 'white',
        textColor: 'positive',
        icon: 'check_circle',
        position: 'top',
        message: '注册成功'
      })
      this.card = true
      // this.$router.push({
      //   path: '/user/registerResult',
      //   query: { email: this.email }
      // })
    },
    onReset() {
      this.name = null
      this.age = null
      this.accept = false
    },
    onItemClick(value) {
      this.phonePrefix = value
    }
  },
  computed: {
    passwordValida: function () {
      return this.password === this.confirmPassword
    }
  }
}
</script>

<style scoped>
.q-field__control {
  padding-left: 0;
}
</style>
