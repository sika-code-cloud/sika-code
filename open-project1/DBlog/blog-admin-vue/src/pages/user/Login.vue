<template>
  <div>
    <div>
      <q-form @submit="onSubmit" @reset="onReset" ref="loginForm">
        <q-tabs
          v-model="tab"
          active-color="primary"
          indicator-color="primary"
          align="left"
          :breakpoint="0"
          narrow-indicator
          class="text-black"
        >
          <q-tab name="mails" label="用户密码登录" />
          <q-tab name="alarms" label="手机号登录" />
        </q-tabs>
        <div class="q-gutter-y-sm">
          <q-tab-panels v-model="tab" class="text-center">
            <q-tab-panel name="mails" class="q-col-gutter-y-sm">
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    v-model="name"
                    dense
                    debounce="500"
                    label="用户名:admin"
                    lazy-rules
                    square
                    :rules="[
                      (val) => (val && val.length > 0) || '请输入用户名'
                    ]"
                  >
                    <template v-slot:prepend>
                      <q-icon name="event" />
                    </template>
                  </q-input>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    :type="isPwd ? 'password' : 'text'"
                    v-model="password"
                    dense
                    debounce="500"
                    label="密码:sika"
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
                </div>
              </div>
            </q-tab-panel>

            <q-tab-panel name="alarms" class="q-col-gutter-y-sm">
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    v-model="name"
                    dense
                    debounce="500"
                    label="手机号"
                    lazy-rules
                    square
                    :rules="[
                      (val) => (val && val.length > 0) || '请输入用户名'
                    ]"
                  >
                    <template v-slot:prepend>
                      <q-icon name="event" />
                    </template>
                  </q-input>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    :type="isPwd ? 'password' : 'text'"
                    v-model="password"
                    dense
                    debounce="500"
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
                </div>
              </div>
            </q-tab-panel>
          </q-tab-panels>
          <div class="q-mx-md">
            <div class="row">
              <div class="col text-left">
                <q-checkbox v-model="autoLogin" label="自动登录" />
              </div>
              <div class="col text-right">
                <q-btn color="primary" flat label="忘记密码" />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <q-btn
                  label="登 录"
                  glossy
                  size="17px"
                  color="primary q-mt-sm"
                  class="full-width"
                  type="submit"
                />
              </div>
            </div>
            <div class="row q-pt-md">
              <div class="col-auto text-left q-pt-sm">
                <span>其他登录方式</span>
                <transition
                  v-bind:css="false"
                  v-on:before-enter="beforeEnter"
                  v-on:enter="enter"
                  v-on:leave="leave"
                >
                  <q-btn
                    v-if="show"
                    class="q-ml-sm"
                    push
                    round
                    dense
                    size="8px"
                    icon="ti-skype"
                    text-color="primary"
                  />
                </transition>
                <q-btn
                  class="q-ml-sm"
                  push
                  round
                  dense
                  size="8px"
                  icon="ti-github"
                  text-color="primary"
                />
                <q-btn
                  class="q-ml-sm"
                  push
                  round
                  dense
                  size="8px"
                  icon="ti-linux"
                  text-color="primary"
                />
                <q-btn
                  class="q-ml-sm"
                  push
                  dense
                  round
                  size="8px"
                  icon="img:statics/icons/atm-away.svg"
                />
              </div>
              <div class="col text-right">
                <q-btn
                  to="/user/register"
                  color="primary"
                  flat
                  label="注册用户"
                />
              </div>
              Fade In: <input type="range" v-model="fadeInDuration" min="0" v-bind:max="maxFadeDuration">
              Fade Out: <input type="range" v-model="fadeOutDuration" min="0" v-bind:max="maxFadeDuration">
              <transition
                v-bind:css="false"
                v-on:before-enter="beforeEnter"
                v-on:enter="enter"
                v-on:leave="leave"
              >
                <p v-if="show">hello</p>
              </transition>
              <button
                v-if="stop"
                v-on:click="stop = false; show = false"
              >Start animating</button>
              <button
                v-else
                v-on:click="stop = true"
              >Stop it!</button>
            </div>
          </div>
          <q-btn @click="shuffle">Shuffle</q-btn>
          <transition-group name="flip-list" tag="ul">
            <li v-for="item in items" v-bind:key="item">
              {{ item }}
            </li>
          </transition-group>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import Velocity from 'velocity-animate'
import _ from 'lodash'
export default {
  name: 'Login',
  data() {
    return {
      tab: 'mails',
      name: null,
      password: null,
      accept: false,
      isPwd: true,
      autoLogin: true,
      dense: false,
      show: true,
      fadeInDuration: 1000,
      fadeOutDuration: 1000,
      maxFadeDuration: 1500,
      stop: true,
      items: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
  },
  methods: {
    onSubmit() {
      this.$q.notify({
        color: 'green-4',
        textColor: 'white',
        icon: 'cloud_done',
        message: 'Submitted'
      })
    },
    onReset() {
      this.name = null
      this.age = null
      this.accept = false
    },
    beforeEnter: function (el) {
      el.style.opacity = 0
    },
    enter: function (el, done) {
      var vm = this
      Velocity(el,
        { opacity: 1 },
        {
          duration: this.fadeInDuration,
          complete: function () {
            done()
            if (!vm.stop) vm.show = false
          }
        }
      )
    },
    leave: function (el, done) {
      var vm = this
      Velocity(el,
        { opacity: 0 },
        {
          duration: this.fadeOutDuration,
          complete: function () {
            done()
            vm.show = true
          }
        }
      )
    },
    shuffle: function () {
      this.items = _.shuffle(this.items)
    }
  }
}
</script>

<style scoped>
.q-tab-panel {
}
.flip-list-move {
  transition: transform 1s;
}
</style>
