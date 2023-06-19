<template>
  <v-app>
    <AdmNavbar></AdmNavbar>
    <v-main class="ma-3">
      <transition mode="out-in">
        <router-view />
      </transition>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import AdmNavbar from "@/components/Layout/AdmNavbar.vue";

@Component({
  components: {
    AdmNavbar,
  },
})
export default class LayoutBaseAdminComponent extends Vue {}
</script>
