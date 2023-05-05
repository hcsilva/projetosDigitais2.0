<template>
  <div>
    <v-app-bar color="indigo" dark app>
      <v-toolbar-title> <router-link to="/admin"> X-App </router-link> </v-toolbar-title>
      <v-btn text class="ml-2" to="/meus-dados">Meus Dados</v-btn>
      <v-btn text class="ml-2" to="/">Cardápio</v-btn>
      <v-btn text class="ml-2" to="/link">Links</v-btn>
      <v-spacer></v-spacer>
      <v-btn text @click="logout()">sair</v-btn>
    </v-app-bar>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {
    logout() {
      this.$store.dispatch("clearToken");
      this.$router.push("/");
      location.reload();
    },
  },
});
</script>