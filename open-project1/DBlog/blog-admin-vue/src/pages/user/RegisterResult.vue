<template>
  <div>RegisterResult-{{ this.$route.query.email }}</div>
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
    this.getParams()
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
