<template>
  <q-field :square="fieldSquare" :dense="fieldDense" outlined :style="fieldStyle">
    <template v-slot:control>
      <div class="self-center full-width no-outline" tabindex="0">
        {{ startAndEndDateFormat }}
      </div>
    </template>
    <template v-slot:prepend>
      <q-icon :name="dateIcon" class="cursor-pointer" :color="dateIconColor">
        <q-menu
          square
          :offset="offset"
          :transition-show="transitionShow"
          :transition-hide="transitionHide"
        >
          <q-date v-model="startAndEndDate" square mask="YYYY-MM-DD" range />
        </q-menu>
      </q-icon>
    </template>
    <template v-slot:append>
      <q-icon :name="clearIcon" class="cursor-pointer" :color="clearIconColor" v-on:click="clear()" />
    </template>
  </q-field>
</template>

<script>
import { date } from 'quasar'
const startAndEndDateDefault = {
  from: '',
  to: ''
}
export default {
  name: 'ScStartEndDate',
  props: {
    clearIcon: {
      type: String,
      default: 'cancel',
      required: false
    },
    clearIconColor: {
      type: String,
      default: 'grey',
      required: false
    },
    dateFormat: {
      type: String,
      default: 'YYYY-MM-DD',
      required: false
    },
    offset: {
      type: [Object, Array],
      default: () => [12, 10],
      required: false
    },
    transitionShow: {
      type: String,
      default: 'jump-down',
      required: false
    },
    transitionHide: {
      type: String,
      default: 'jump-up',
      required: false
    },
    dateIcon: {
      type: String,
      default: 'event',
      required: false
    },
    dateIconColor: {
      type: String,
      default: 'primary',
      required: false
    },
    fieldSquare: {
      type: Boolean,
      default: true,
      required: false
    },
    fieldDense: {
      type: Boolean,
      default: true,
      required: false
    },
    fieldStyle: {
      type: Object,
      required: false
    },
    fromDate: {
      type: Date,
      default: () => date.startOfDate(Date.now(), 'day'),
      required: false
    },
    toDate: {
      type: Date,
      default: () => date.startOfDate(Date.now(), 'day'),
      required: false
    }
  },
  data() {
    return {
      visitQuery: 'currentDay',
      startAndEndDate: {
        from: this.fromDate,
        to: this.toDate
      },
      startAndEndDateFormat: ''
    }
  },
  methods: {
    formatDate(dateFormat) {
      return date.formatDate(dateFormat, this.dateFormat)
    },
    formatStartAndAnd() {
      this.startAndEndDateFormat = this.formatDate(this.startAndEndDate.from) + '~' + this.formatDate(this.startAndEndDate.to)
    },
    buildNow() {
      return date.startOfDate(Date.now(), 'day')
    },
    clear() {
      this.startAndEndDate = startAndEndDateDefault
    }
  },
  mounted() {
    this.formatStartAndAnd()
  },
  watch: {
    startAndEndDate: {
      handler(newValue, oldValue) {
        if (!newValue) {
          newValue = startAndEndDateDefault
          this.startAndEndDate = startAndEndDateDefault
        }
        if (!newValue || newValue.from === '' || newValue.to === '') {
          this.startAndEndDateFormat = ''
          return
        }
        this.formatStartAndAnd()
      },
      immediate: true,
      deep: true
    }
  }
}
</script>

<style scoped></style>
