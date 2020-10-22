<template>
  <div class="q-pa-md" style="max-width: 350px">
    <q-list bordered separator>
      <q-slide-item @left="onLeft" @right="onRight">
        <template v-slot:left>
          <q-icon name="done" style="width: 50px" />
        </template>
        <template v-slot:right>
          <q-icon name="alarm" style="width: 50px" />
        </template>

        <q-item>
          <q-item-section avatar>
            <q-avatar color="primary" text-color="white" icon="bluetooth" />
          </q-item-section>
          <q-item-section>Icons only</q-item-section>
        </q-item>
      </q-slide-item>

      <q-slide-item @left="onLeft" @right="onRight">
        <template v-slot:left>
          <div>Left</div>
        </template>
        <template v-slot:right>
          <div style="color: springgreen; max-width: 50px">
            Right content.. long
          </div>
        </template>

        <q-item>
          <q-item-section avatar>
            <q-avatar>
              <img
                src="https://cdn.quasar.dev/img/avatar6.jpg"
                draggable="false"
              />
            </q-avatar>
          </q-item-section>
          <q-item-section>Text only</q-item-section>
        </q-item>
      </q-slide-item>

      <q-slide-item @left="onLeft" @right="onRight">
        <template v-slot:left>
          <div class="row items-center">
            <q-icon left name="done" />
            Left
          </div>
        </template>
        <template v-slot:right>
          <div class="row items-center">
            Right content.. long
            <q-icon right name="alarm" />
          </div>
        </template>

        <q-item>
          <q-item-section avatar>
            <q-avatar>
              <img
                src="https://cdn.quasar.dev/img/avatar4.jpg"
                draggable="false"
              />
            </q-avatar>
          </q-item-section>
          <q-item-section>Text and icons</q-item-section>
        </q-item>
      </q-slide-item>
    </q-list>
    <q-separator />
    <template>
      <tree :data="treeData"></tree>
    </template>

    <div class="q-pa-md q-gutter-sm">
      <q-tree :nodes="simple" node-key="label" />
    </div>
  </div>
</template>

<script>
import Tree from 'components/tree/Tree'
const myData = [
  {
    name: 'Web秀',
    children: [
      {
        name: 'web前端',
        children: [
          {
            name: 'CSS'
          },
          {
            name: 'JavaScript'
          },
          {
            name: 'Vue'
          },
          {
            name: '小程序'
          },
          {
            name: 'Three.js'
          }
        ]
      },
      {
        name: '服务器'
      },
      {
        name: '工具类'
      }
    ]
  },
  {
    name: '今日头条',
    children: [
      {
        name: '图片'
      },
      {
        name: '新闻',
        children: []
      }
    ]
  },
  {
    name: 'Angular'
  }
]
export default {
  name: 'Button',
  components: { Tree },
  data() {
    return {
      treeData: myData,
      simple: [
        {
          label: 'Satisfied customers (with avatar)',
          avatar: 'https://cdn.quasar.dev/img/boy-avatar.png',
          children: [
            {
              label: 'Good food (with icon)',
              icon: 'restaurant_menu',
              children: [
                { label: 'Quality ingredients' },
                { label: 'Good recipe' }
              ]
            },
            {
              label: 'Good service (disabled node with icon)',
              icon: 'room_service',
              disabled: true,
              children: [
                { label: 'Prompt attention' },
                { label: 'Professional waiter' }
              ]
            },
            {
              label: 'Pleasant surroundings (with icon)',
              icon: 'photo',
              children: [
                {
                  label: 'Happy atmosphere (with image)',
                  img: 'https://cdn.quasar.dev/img/logo_calendar_128px.png'
                },
                { label: 'Good table presentation' },
                { label: 'Pleasing decor' }
              ]
            }
          ]
        }
      ]
    }
  },
  methods: {
    onLeft({ reset }) {
      this.$q.notify('Left action triggered. Resetting in 1 second.')
      this.finalize(reset)
    },
    onRight({ reset }) {
      this.$q.notify('Right action triggered. Resetting in 1 second.')
      this.finalize(reset)
    },
    finalize(reset) {
      this.timer = setTimeout(() => {
        reset()
      }, 10000)
    }
  },
  beforeDestroy() {
    clearTimeout(this.timer)
  }
}
</script>
