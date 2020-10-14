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
                v-model="name"
                dense
                label="邮箱"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '邮箱']"
              >
                <template v-slot:prepend>
                  <q-icon name="event" />
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
                v-model="password"
                dense
                label="确认密码"
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
                v-model="name"
                dense
                label="手机号"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '邮箱']"
              >
                <template v-slot:prepend>
                  <q-icon name="event" />
                </template>
              </q-input>
              <q-input
                outlined
                :type="isPwd ? 'password' : 'text'"
                v-model="password"
                dense
                label="验证码"
                lazy-rules
                square
                :rules="[(val) => (val && val.length > 0) || '请输入密码']"
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
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      tab: 'mails',
      name: null,
      password: null,
      accept: false,
      isPwd: true,
      autoLogin: true,
      dense: false
    }
  }
}
</script>

<style scoped></style>
