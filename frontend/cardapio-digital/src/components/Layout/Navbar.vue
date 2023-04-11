<template>
  <v-app-bar color="indigo" dark app>
    <v-toolbar-title>
      <img src="logo.png" height="30px" />
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn text @click="goToLoginPage">Entrar</v-btn>
  </v-app-bar>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {

    goToLoginPage(){
        this.$router.push('/login');
    }

  },
});
</script>