<template>
  <div>
    <div class="row">
      <div class="q-pa-sm" :class="itemClass" :key="index" v-for="(item, index) in perPageItems">
        <slot name="item" :item="item"></slot>
      </div>
    </div>
    <div>
      <slot name="page">
        <div class="justify-center row items-center gt-xs">
          <div class="col-auto">
            <q-pagination
              v-model="current"
              :max="maxPage"
              :max-pages="maxPages"
              :direction-links="true"
            >
            </q-pagination>
          </div>
          <div class="col-auto">
            <q-btn label="跳至" class="q-mr-sm" dense unelevated flat color="primary" @click="changePage" />
            <span class="inline-block q-mr-sm">
              <q-input
                @keyup.enter="changePage"
                v-model="inputPage"
                outlined
                dense
                style="width: 46px"
              /> </span
            >页
          </div>
        </div>
        <div class="justify-center row items-center lt-sm">
          <div class="col-auto">
            <q-pagination v-model="current" :max="maxPage" :input="true">
            </q-pagination>
          </div>
        </div>
      </slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScPage',
  props: {
    items: {
      type: [Array],
      required: true
    },
    itemClass: {
      type: String,
      required: false,
      default: 'col-12'
    },
    max: {
      type: Number,
      required: false,
      default: 1
    },
    maxPages: {
      type: Number,
      required: false,
      default: 3
    },
    perNumber: {
      type: Number,
      required: false,
      default: 5
    }
  },
  data() {
    return {
      current: 1,
      inputPage: 1
    }
  },
  methods: {
    changePage() {
      this.current = parseInt(this.inputPage)
    }
  },
  computed: {
    maxPage() {
      return Math.ceil(this.items.length / this.perNumber)
    },
    perPageItems() {
      const start = (this.current - 1) * this.perNumber
      return this.items.slice(start, start + this.perNumber)
    }
  },
  watch: {
    current(newValue, oldValue) {
      this.inputPage = newValue
    }
  }
}
</script>

<style scoped></style>
