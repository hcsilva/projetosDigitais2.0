<template>
  <div>
    <v-app-bar color="indigo" dark app>
      <v-toolbar-title> Logado no sistema </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text @click="logout()">sair</v-btn>
    </v-app-bar>

    <v-btn color="primary" @click="buscarEmpresas()">Buscar empresas</v-btn>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {
    buscarEmpresas() {
      


    },

    logout() {
      this.$store.commit("setAuthToken", "");
      this.$router.push("/");
      location.reload();
    },
  },
});
</script>