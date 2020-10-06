<template>
  <div class="q-pa-md">
    <q-btn color="primary" label="Trigger" @click="trigger" />
  </div>
</template>

<script>
import { login } from 'api/user'

export default {
  name: 'q-ajax',
  methods: {
    // we manually trigger it (this is not needed if we
    // don't skip Ajax calls hijacking)
    trigger() {
      login()
        .then(response => {
          console.log(response.data)
        })
        .catch(() => {
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
