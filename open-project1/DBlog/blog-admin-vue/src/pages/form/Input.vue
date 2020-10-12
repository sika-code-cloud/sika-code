<template>
  <div class="q-pa-md">
    <div class="q-gutter-md" style="max-width: 300px">
      <q-input v-model="text" label="Standard" />
      <q-input filled v-model="text" label="Filled" />
      <q-input outlined v-model="text" label="Outlined" />
      <q-input standout v-model="text" label="Standout" />
      <q-input
        standout="bg-teal text-white"
        v-model="text"
        label="Custom standout"
      />
      <q-input borderless v-model="text" label="Borderless" />
      <q-input rounded filled v-model="text" label="Rounded filled" />
      <q-input rounded outlined v-model="text" label="Rounded outlined" />
      <q-input rounded standout v-model="text" label="Rounded standout" />
      <q-input square filled v-model="text" label="Square filled" />
      <q-input square outlined v-model="text" label="Square outlined" />
      <q-input square standout v-model="text" label="Square standout" />
      <q-separator />
    </div>
    <div class="q-gutter-y-md column" style="max-width: 300px">
      <q-input color="purple-12" v-model="text" label="Label">
        <template v-slot:prepend>
          <q-icon name="event" />
        </template>
      </q-input>

      <q-input color="teal" filled v-model="text" label="Label">
        <template v-slot:prepend>
          <q-icon name="event" />
        </template>
      </q-input>
      <q-toolbar class="bg-primary text-white rounded-borders">
        <q-btn round dense flat icon="menu" class="q-mr-xs" />
        <q-avatar class="gt-xs">
          <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
        </q-avatar>

        <q-space />

        <q-input
          dark
          borderless
          v-model="text"
          input-class="text-right"
          class="q-ml-md"
        >
          <template v-slot:append>
            <q-icon v-if="text === ''" name="search" />
            <q-icon
              v-else
              name="clear"
              class="cursor-pointer"
              @click="text = ''"
            />
          </template>
        </q-input>
      </q-toolbar>
      <q-input
        color="grey-3"
        label-color="orange"
        outlined
        v-model="text"
        label="Label"
      >
        <template v-slot:append>
          <q-icon name="event" color="orange" />
        </template>
      </q-input>

      <q-input
        color="lime-11"
        bg-color="green"
        filled
        v-model="text"
        label="Label"
      >
        <template v-slot:prepend>
          <q-icon name="event" />
        </template>
      </q-input>

      <q-input color="teal" outlined v-model="text" label="Label">
        <template v-slot:append>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
        </template>
      </q-input>

      <q-input
        color="orange"
        standout
        bottom-slots
        v-model="text"
        label="Label"
        counter
        clearable
      >
        <template v-slot:prepend>
          <q-icon name="place" />
        </template>
        <template v-slot:append>
          <q-icon name="favorite" />
        </template>

        <template v-slot:hint> Field hint </template>
      </q-input>
      <q-separator />
      <div class="q-gutter-md row items-start">
        <q-input v-model="password" filled type="password" hint="Password" />

        <q-input
          v-model="password"
          filled
          :type="isPwd ? 'password' : 'text'"
          hint="Password with toggle"
        >
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>

        <q-input v-model="email" filled type="email" hint="Email" />

        <q-input v-model="search" filled type="search" hint="Search">
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>

        <q-input v-model="tel" filled type="tel" hint="Telephone number" />

        <q-input v-model="url" filled type="url" hint="URL" />

        <q-input v-model="time" filled type="time" hint="Native time" />

        <q-input v-model="date" filled type="date" hint="Native date" />
      </div>
      <q-separator />
      <template>
        <div class="q-pa-md" style="max-width: 300px">
          <q-input
            ref="input"
            filled
            v-model="model"
            label="Type here"
            bottom-slots
            :error="!isValid"
            hint="Max 3 characters"
          >
            <template v-slot:error> Please use maximum 3 characters. </template>
          </q-input>
        </div>
      </template>
      <q-separator />
      <div class="q-pa-md bg-grey-10 text-white">
        <q-input
          dark
          outlined
          v-model="text"
          :readonly="readonly"
          :disable="disable"
        >
          <template v-slot:append>
            <q-avatar>
              <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
            </q-avatar>
          </template>
        </q-input>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Input',
  data() {
    return {
      text: '',
      password: '',
      isPwd: true,

      email: '',
      search: '',
      tel: '',
      url: '',
      time: '',
      date: '',
      model: '',
      readonly: false,
      disable: false
    }
  },
  computed: {
    isValid() {
      return this.model.length <= 3
    }
  }
}
</script>

<style scoped></style>
