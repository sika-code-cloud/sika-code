<template>
  <div>
    <div class="row">
      <div
        class="q-pa-sm"
        :class="itemClass"
        :key="index"
        v-for="(item, index) in perPageItems"
      >
        <slot name="item" :item="item"></slot>
      </div>
    </div>
    <div class="q-mt-sm">
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
            <q-btn
              label="跳至"
              class="q-mr-sm"
              dense
              unelevated
              flat
              color="primary"
              @click="changePage"
            />
            <span class="inline-block q-mr-sm sc-design-page">
              <q-input
                @keyup.enter="changePage"
                v-model="inputPage"
                outlined
                dense
                style="width: 46px"
              /> </span
            ><span>页</span>
          </div>
          <div class="col-auto">
            <span class="q-pl-sm q-pr-xs text-grey-7">每页数</span>
            <q-chip square color="primary" outline dense class="bg-blue-1"
              >{{ perNumber }}
            </q-chip>
          </div>
          <div class="col-auto">
            <span class="q-ml-sm q-mr-xs text-grey-7">总条数</span>
            <q-chip square color="primary" outline dense class="bg-blue-1"
              >{{ total }}
            </q-chip>
          </div>
        </div>
        <div class="justify-center row items-center lt-sm">
          <div class="col-auto">
            <q-pagination v-model="current" :max="maxPage" :input="true">
            </q-pagination>
          </div>
          <div class="col-sm-auto col-xs-12">
            <div class="row justify-center">
              <div class="col-auto">
                <span class="q-pl-sm q-pr-xs text-grey-7">每页数</span>
                <q-chip
                  square
                  color="primary"
                  outline
                  dense
                  class="bg-blue-1 q-mt-none"
                  >{{ perNumber }}
                </q-chip>
              </div>
              <div class="col-auto">
                <span class="q-pl-sm q-pr-xs text-grey-7">总条数</span>
                <q-chip
                  square
                  color="primary"
                  outline
                  dense
                  class="bg-blue-1 q-mt-none"
                  >{{ total }}
                </q-chip>
              </div>
            </div>
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
      default: 6
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
    },
    total() {
      return this.items.length
    }
  },
  watch: {
    current(newValue, oldValue) {
      this.inputPage = newValue
    }
  }
}
</script>

<style lang="sass">
.sc-design-page
  .q-item__section--side
    padding-right: 0

  .q-field--dense
    .q-field__control
      height: 28px

    .q-field__marginal
      height: 28px

  .q-field--auto-height.q-field--dense
    .q-field__control, .q-field__native
      min-height: 28px
</style>
