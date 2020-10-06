<template>
  <div class="q-pa-md q-gutter-sm">
    <div class="row">
      <div class="col-12  q-ma-sm">
        <q-avatar color="red" text-color="white" icon="directions" />
        <q-avatar color="primary" text-color="white">J</q-avatar>
        <q-avatar
          size="100px"
          font-size="52px"
          color="teal"
          text-color="white"
          icon="directions"
        />
        <q-avatar size="24px" color="orange">J</q-avatar>
        <q-avatar>
          <img src="https://cdn.quasar.dev/img/avatar.png" />
        </q-avatar>
      </div>
      <q-separator />
      <div class="col-12 q-ma-sm">
        <q-avatar
          v-for="size in ['xs', 'sm', 'md', 'lg', 'xl']"
          :key="size"
          :size="size"
          color="primary"
          text-color="white"
          icon="directions"
        />
      </div>
      <q-separator />
      <div class="col-12 q-ma-sm">
        <q-avatar square color="green" text-color="white" icon="directions" />
        <q-avatar square color="primary" text-color="white">J</q-avatar>
        <q-avatar
          square
          size="100px"
          font-size="82px"
          color="teal"
          text-color="white"
          icon="directions"
        />
        <q-avatar square size="24px" color="orange">J</q-avatar>
        <q-avatar square>
          <img src="https://cdn.quasar.dev/img/avatar.png" />
        </q-avatar>
      </div>
    </div>
    <q-separator />
    <div class="col-12 q-ma-sm">
      <q-avatar rounded color="red" text-color="white" icon="directions" />
      <q-avatar rounded color="primary" text-color="white">J</q-avatar>
      <q-avatar
        rounded
        size="100px"
        font-size="82px"
        color="teal"
        text-color="white"
        icon="directions"
      />
      <q-avatar rounded size="24px" color="orange">J</q-avatar>
      <q-avatar rounded>
        <img src="https://cdn.quasar.dev/img/avatar.png" />
        <q-badge floating color="teal">new</q-badge>
      </q-avatar>
    </div>
    <q-separator />
    <div class="col-12 q-ma-sm">
      <div class="q-gutter-sm">
        <q-chip>
          <q-avatar color="red" text-color="white">50</q-avatar>
          Emails
        </q-chip>
        <q-chip>
          <q-avatar>
            <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
          </q-avatar>
          John
        </q-chip>
        <q-chip>
          <q-avatar>
            <img src="https://cdn.quasar.dev/img/avatar.png" />
          </q-avatar>
          Zhangsan
        </q-chip>
      </div>
      <div class="q-gutter-x-sm">
        <q-btn round>
          <q-avatar size="28px">
            <img src="https://cdn.quasar.dev/app-icons/icon-128x128.png" />
          </q-avatar>
        </q-btn>
        <q-btn round>
          <q-avatar size="32px">
            <img src="https://cdn.quasar.dev/app-icons/icon-128x128.png" />
          </q-avatar>
        </q-btn>
        <q-btn round>
          <q-avatar size="40px">
            <img src="https://cdn.quasar.dev/app-icons/icon-128x128.png" />
          </q-avatar>
        </q-btn>
      </div>

      <q-item clickable v-ripple>
        <q-item-section side>
          <q-avatar rounded size="48px">
            <img src="https://cdn.quasar.dev/img/avatar.png" />
            <q-badge floating color="teal">new</q-badge>
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-item-label>Mary</q-item-label>
          <q-item-label caption>2 new messages</q-item-label>
        </q-item-section>
        <q-item-section side>
          3 min ago
        </q-item-section>
      </q-item>

      <q-banner rounded class="bg-primary text-white">
        <template v-slot:avatar>
          <q-avatar>
            <img src="https://cdn.quasar.dev/app-icons/icon-128x128.png" />
          </q-avatar>
          <!-- <q-avatar icon="signal_wifi_off" color="white" text-color="primary" /> -->
        </template>
        You have lost connection to the internet. This app is offline.
        <template v-slot:action>
          <q-btn flat color="white" label="Turn ON Wifi" />
        </template>
      </q-banner>
    </div>
  </div>
</template>
