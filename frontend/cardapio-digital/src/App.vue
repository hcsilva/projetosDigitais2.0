<template>
  <v-app>
    <component :is="navbarComponent" />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import Navbar from "./components/Layout/Navbar.vue";
import AdmNavbar from "./components/Layout/AdmNavbar.vue";
import index from "./store";

export default Vue.extend({
  name: "App",
  components: { Navbar, AdmNavbar },
  computed: {
    navbarComponent(): string {
      if (
        (localStorage.getItem("authToken") == null ||
          localStorage.getItem("authToken") == "undefined") &&
        !index.state.logged
      ) {
        return "Navbar";
      } else {
        return "AdmNavbar";
      }
    },
  },
});
</script>

<style>
::-webkit-scrollbar {
  display: none;
}
</style>
