<template>
  <q-field :square="fieldSquare" :dense="fieldDense" outlined :style="fieldStyle">
    <template v-slot:control>
      <div class="self-center full-width no-outline" tabindex="0">
        {{ formatStartAndEndDate }}
      </div>
    </template>
    <template v-slot:append>
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
  </q-field>
</template>

<script>
import { date } from 'quasar'

export default {
  name: 'ScStartEndDate',
  props: {
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
      }
    }
  },
  methods: {
    formatDate(dateFormat) {
      return date.formatDate(dateFormat, this.dateFormat)
    }
  },
  computed: {
    formatStartAndEndDate() {
      if (!this.startAndEndDate) {
        return ''
      }
      return (
        this.formatDate(this.startAndEndDate.from) +
        '~' +
        this.formatDate(this.startAndEndDate.to)
      )
    }
  }
}
</script>

<style scoped></style>
