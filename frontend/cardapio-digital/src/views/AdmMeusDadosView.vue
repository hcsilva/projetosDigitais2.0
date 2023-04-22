<template>
  <div>
    <adm-navbar />
    <empresa />
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import AdmNavbar from "../components/Layout/AdmNavbar.vue";
import Empresa from "../components/Empresa/Empresa.vue";

export default Vue.extend({
  name: "MeusDados",

  components: {
    AdmNavbar,
    Empresa,
  },
});
</script>
