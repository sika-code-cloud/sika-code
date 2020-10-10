<template>
  <q-list>
    <q-item-label header class="text-grey-8"> Essential Links </q-item-label>
    <q-item
      v-for="linkData in linksData"
      :key="linkData.title"
      clickable
      :to="linkData.link"
    >
      <q-item-section v-if="linkData.icon" avatar>
        <q-icon :name="linkData.icon" />
      </q-item-section>

      <q-item-section>
        <q-item-label>{{ linkData.title }}</q-item-label>
        <q-item-label linkData.caption>
          {{ linkData.caption }}
        </q-item-label>
      </q-item-section>
    </q-item>
  </q-list>
</template>

<script>
const linksData = [
  {
    title: 'Button Demo',
    caption: 'button',
    icon: 'favorite',
    link: '/button'
  },
  {
    title: 'Ajax Demo',
    caption: 'ajax',
    icon: 'favorite',
    link: '/ajax'
  },
  {
    title: 'Avatar Demo',
    caption: 'avatar',
    icon: 'favorite',
    link: '/avatar'
  },
  {
    title: 'Badge Demo',
    caption: 'badge',
    icon: 'favorite',
    link: '/badge'
  },
  {
    title: 'Card Demo',
    caption: 'card',
    icon: 'favorite',
    link: '/card'
  },
  {
    title: 'Carousel Demo',
    caption: 'carousel',
    icon: 'favorite',
    link: '/carousel'
  },
  {
    title: 'ChatMessage Demo',
    caption: 'chatMessage',
    icon: 'favorite',
    link: '/chatMessage'
  },
  {
    title: 'Color Demo',
    caption: 'color',
    icon: 'favorite',
    link: '/color'
  },
  {
    title: 'Dialog Demo',
    caption: 'dialog',
    icon: 'favorite',
    link: '/dialog'
  },
  {
    title: 'Editor Demo',
    caption: 'editor',
    icon: 'favorite',
    link: '/editor'
  },
  {
    title: 'ExpansionItem Demo',
    caption: 'expansionItem',
    icon: 'favorite',
    link: '/expansionItem'
  },
  {
    title: 'Form/Input Demo',
    caption: 'Form input',
    icon: 'favorite',
    link: '/form/input'
  },
  {
    title: 'Form/Form Demo',
    caption: 'Form Form',
    icon: 'favorite',
    link: '/form/form'
  }
]

export default {
  name: 'EssentialLink',
  data() {
    return {
      linksData
    }
  },
  computed: {
    isHttp: function (link) {
      return link.startsWith('http')
    }
  },
  props: {
    title: {
      type: String,
      default: ''
    },

    caption: {
      type: String,
      default: ''
    },

    link: {
      type: String,
      default: '#'
    },

    icon: {
      type: String,
      default: ''
    }
  }
}
</script>
