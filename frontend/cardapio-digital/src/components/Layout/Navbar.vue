<template>
  <v-app-bar color="primary" dark app>
    <v-toolbar-title>
      <img src="../../assets/logo-fanta.png"  height="50px" />
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn text @click="goToLoginPage">Entrar</v-btn>
    <v-btn text @click="goToCadastrarNovoUsuarioPage">Casdastre-se</v-btn>
  </v-app-bar>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {
    goToLoginPage() {
      this.$router.push("/login");
    },

    goToCadastrarNovoUsuarioPage() {
      this.$router.push("/casdastrarNovoUsuario");
    },
  },
});
</script>