<template>
  <q-page class="flex flex-center">
    Button
    <q-btn color="primary" label="TriggerAjax" @click="login" />
    <q-ajax></q-ajax>
  </q-page>
</template>

<script>
import qAjax from 'pages/Ajax.vue'
import { login } from 'api/user'

export default {
  name: 'Button',
  components: {
    qAjax
  },
  methods: {
    triggerAjax () {
      const bar = this.$refs.bar
      bar.start()
      this.timer = setTimeout(() => {
        if (this.$refs.bar) {
          this.$refs.bar.stop()
        }
      }, Math.random() * 3000 + 1000)
    },
    login () {
      login().then((response) => {
        console.log(response.data)
      }).catch(() => {
        this.$q.notify({
          color: 'negative',
          position: 'top',
          message: 'Loading failed',
          icon: 'report_problem'
        })
      })
    }
  }
}
</script>
