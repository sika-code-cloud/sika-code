<template>
  <q-field square dense outlined style="width: 100%">
    <template v-slot:control>
      <div class="self-center full-width no-outline" tabindex="0">
        {{ formatQueryDate }}
      </div>
    </template>
    <template v-slot:append>
      <q-icon name="event" class="cursor-pointer" color="orange">
        <q-menu
          square
          :offset="[12, 10]"
          transition-show="jump-down"
          transition-hide="jump-up"
        >
          <q-date v-model="beginAndEndDate" square mask="YYYY-MM-DD" range />
        </q-menu>
      </q-icon>
    </template>
  </q-field>
</template>

<script>
import { date } from 'quasar'

export default {
  name: 'ScStartEndDate',
  data() {
    return {
      queryDate: {
        from: date.startOfDate(Date.now(), 'day'),
        to: date.startOfDate(Date.now(), 'day')
      },
      visitQuery: 'currentDay',
      beginAndEndDate: {}
    }
  },
  methods: {
    formatDate(dateFormat) {
      return date.formatDate(dateFormat, 'YYYY-MM-DD')
    }
  },
  computed: {
    formatQueryDate() {
      return (
        this.formatDate(this.queryDate.from) +
        '~' +
        this.formatDate(this.queryDate.to)
      )
    }
  },
  watch: {
    beginAndEndDate(newSelected, oldSelected) {
      if (!newSelected) {
        newSelected = date.startOfDate(Date.now(), 'day')
      }
      if (!newSelected.from || !newSelected.to) {
        this.queryDate.from = newSelected
        this.queryDate.to = newSelected
      } else if (newSelected) {
        this.queryDate.from = newSelected.from
        this.queryDate.to = newSelected.to
      }
    }
  }
}
</script>

<style scoped></style>
