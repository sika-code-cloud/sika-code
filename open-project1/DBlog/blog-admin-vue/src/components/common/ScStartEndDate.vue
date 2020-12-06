<template>
  <q-field :square="fieldSquare"
           :dense="fieldDense"
           outlined
           :style="fieldStyle"
           v-model="startAndEndDateFormat"
           :rules="rules">
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

export default {
  name: 'ScStartEndDate',
  props: {
    rules: {
      type: [Object, Array],
      required: false
    },
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
      required: false
    },
    toDate: {
      type: Date,
      required: false
    }
  },
  data() {
    return {
      visitQuery: 'currentDay',
      startAndEndDateForRet: {
        from: this.fromDate,
        to: this.toDate
      },
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
    buildStartAndEndDate(from, to) {
      if (from === to) {
        this.startAndEndDate = from
        return
      }
      this.startAndEndDate = this.defaultValue()
      if (date.isValid(from)) {
        this.startAndEndDate.from = this.formatDate(from)
      }
      if (date.isValid(to)) {
        this.startAndEndDate.to = this.formatDate(to)
      }
    },
    formatStartAndAnd() {
      const startEndDate = this.startAndEndDateForRet
      if (!startEndDate.from) {
        startEndDate.from = this.formatDate(Date.now())
      }
      if (!startEndDate.to) {
        startEndDate.to = this.formatDate(Date.now())
      }
      if (startEndDate.from === startEndDate.to) {
        this.startAndEndDate = startEndDate.from
      }
      this.startAndEndDateFormat = startEndDate.from + '~' + startEndDate.to
    },
    buildNow() {
      return date.startOfDate(Date.now(), 'day')
    },
    clear() {
      this.startAndEndDate = this.defaultValue()
    },
    defaultValue() {
      return {
        from: '',
        to: ''
      }
    }
  },
  mounted() {
    this.formatStartAndAnd()
  },
  watch: {
    startAndEndDate: {
      handler(newValue, oldValue) {
        console.log(JSON.stringify(newValue))
        this.startAndEndDateForRet = newValue
        if (!newValue) {
          newValue = this.defaultValue()
          this.startAndEndDateForRet = this.defaultValue()
          this.startAndEndDate = this.defaultValue()
        }
        if (!newValue || newValue.from === '' || newValue.to === '') {
          this.startAndEndDateForRet = null
          this.startAndEndDateFormat = ''
          return
        }
        if (date.isValid(newValue)) {
          this.startAndEndDateForRet = {
            from: newValue,
            to: newValue
          }
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
