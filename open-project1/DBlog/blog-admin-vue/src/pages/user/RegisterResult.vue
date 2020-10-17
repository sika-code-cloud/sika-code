<template>
  <div>RegisterResult-{{email }}</div>
</template>

<script>
export default {
  name: 'RegisterResult',
  data() {
    return {
      email: ''
    }
  },
  mounted() {
    this.email = this.$q.localStorage.getItem('name')
  },
  methods: {
    getParams() {
      // console.log('12121----' + JSON.stringify(this.$route.params))
      // this.email = this.$route.params.email
    }
  },
  watch: {
    $route: 'getParams'
  }
}
</script>

<style scoped></style>
