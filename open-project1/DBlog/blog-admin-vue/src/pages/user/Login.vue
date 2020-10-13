<template>
  <div>
    <q-tabs
      v-model="tab"
      active-color="primary"
      indicator-color="primary"
      align="left"
      :breakpoint="0"
      narrow-indicator
      class="text-black q-mb-lg"
    >
      <q-tab name="mails" label="用户密码登录" />
      <q-tab name="alarms" label="手机号登录" />
    </q-tabs>

    <div class="q-gutter-y-sm">
      <q-tab-panels v-model="tab" animated class="text-center">
        <q-tab-panel name="mails">
          <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
            <q-input
              outlined
              clearable
              clear-icon="cancel"
              v-model="name"
              :dense="dense"
              label="用户名"
              lazy-rules
              :rules="[(val) => (val && val.length > 0) || '请输入用户名']"
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
              :dense="dense"
              label="密码"
              lazy-rules
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

            <q-toggle v-model="accept" label="I accept the license and terms" />
            <div>
              <q-btn label="Submit" type="submit" color="primary" />
              <q-btn
                label="Reset"
                type="reset"
                color="primary"
                flat
                class="q-ml-sm"
              />
            </div>
          </q-form>
        </q-tab-panel>

        <q-tab-panel name="alarms">
          <div class="text-h6">Alarms</div>
          Ad molestiae non facere animi nobis, similique nemo velit reiciendis
          corporis impedit nam in.
        </q-tab-panel>

        <q-tab-panel name="movies">
          <div class="text-h6">Movies</div>
          Nostrum necessitatibus expedita dolores? Voluptatem repudiandae magni
          ea.
        </q-tab-panel>
      </q-tab-panels>

      <q-tab-panels
        v-model="tab"
        animated
        transition-prev="fade"
        transition-next="fade"
        class="bg-orange text-white text-center"
      >
        <q-tab-panel name="mails">
          <div class="text-h6">Mails</div>
          Lorem ipsum dolor sit amet consectetur adipisicing elit.
        </q-tab-panel>

        <q-tab-panel name="alarms">
          <div class="text-h6">Alarms</div>
          Ad molestiae non facere animi nobis, similique nemo velit reiciendis
          corporis impedit nam in.
        </q-tab-panel>

        <q-tab-panel name="movies">
          <div class="text-h6">Movies</div>
          Nostrum necessitatibus expedita dolores? Voluptatem repudiandae magni
          ea.
        </q-tab-panel>
      </q-tab-panels>

      <q-tab-panels
        v-model="tab"
        animated
        transition-prev="jump-up"
        transition-next="jump-down"
        class="bg-teal text-white text-center"
      >
        <q-tab-panel name="mails">
          <div class="text-h6">Mails</div>
          Lorem ipsum dolor sit amet consectetur adipisicing elit.
        </q-tab-panel>

        <q-tab-panel name="alarms">
          <div class="text-h6">Alarms</div>
          Ad molestiae non facere animi nobis, similique nemo velit reiciendis
          corporis impedit nam in.
        </q-tab-panel>

        <q-tab-panel name="movies">
          <div class="text-h6">Movies</div>
          Nostrum necessitatibus expedita dolores? Voluptatem repudiandae magni
          ea.
        </q-tab-panel>
      </q-tab-panels>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      tab: 'mails',
      name: null,
      password: null,
      accept: false,
      isPwd: true,
      dense: false
    }
  },
  methods: {
    onSubmit() {
      if (this.accept !== true) {
        this.$q.notify({
          color: 'red-5',
          textColor: 'white',
          icon: 'warning',
          message: 'You need to accept the license and terms first'
        })
      } else {
        this.$q.notify({
          color: 'green-4',
          textColor: 'white',
          icon: 'cloud_done',
          message: 'Submitted'
        })
      }
    },
    onReset() {
      this.name = null
      this.age = null
      this.accept = false
    }
  }
}
</script>

<style scoped></style>
