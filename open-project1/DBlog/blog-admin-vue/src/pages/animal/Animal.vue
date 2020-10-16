<template>
  <div>
    Fade In:
    <input
      type="range"
      v-model="fadeInDuration"
      min="0"
      v-bind:max="maxFadeDuration"
    />
    Fade Out:
    <input
      type="range"
      v-model="fadeOutDuration"
      min="0"
      v-bind:max="maxFadeDuration"
    />
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
      v-on:click="
        stop = false
        show = false
      "
    >
      Start animating
    </button>
    <button v-else v-on:click="stop = true">Stop it!</button>
    <q-btn @click="shuffle">Shuffle</q-btn>
    <transition-group name="flip-list" tag="ul">
      <li v-for="item in items" v-bind:key="item">
        {{ item }}
      </li>
    </transition-group>
  </div>
</template>

<script>
import Velocity from 'velocity-animate'
import _ from 'lodash'

export default {
  name: 'Animal',
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
      Velocity(
        el,
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
      Velocity(
        el,
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

<style scoped></style>
