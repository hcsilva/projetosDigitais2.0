<template>
  <adm-navbar />
</template>

<script lang="ts">
  import Vue from 'vue'
  import AdmNavbar from '../components/Layout/AdmNavbar.vue'

  export default Vue.extend({
    name: 'Adm',

    components: {
      AdmNavbar,
    },
  })
</script>
