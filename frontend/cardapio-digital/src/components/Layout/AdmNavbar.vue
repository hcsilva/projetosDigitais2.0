<template>
  <div>
    <v-navigation-drawer v-model="drawer" app :permanent="isLargeScreen" dark>
      <v-list>
        <v-list-item link to="/meus-dados">
          <v-list-item-icon>
            <v-icon color="deep-orange">mdi-account</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Meus dados</v-list-item-title>
        </v-list-item>
        <v-list-item link to="/cardapio">
          <v-list-item-icon>
            <v-icon color="deep-orange">mdi-food</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Cardápio</v-list-item-title>
        </v-list-item>
        <v-list-item link to="/link">
          <v-list-item-icon>
            <v-icon color="deep-orange">mdi-link</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Links</v-list-item-title>
        </v-list-item>
      </v-list>
      <v-footer class="mt-8">
        <v-btn block color="white" text @click="logout()">Sair</v-btn>
      </v-footer>
    </v-navigation-drawer>

    <v-app-bar app color="deep-orange">
      <v-row align="center">
        <v-col cols="auto">
          <v-app-bar-nav-icon
            @click.stop="toggleDrawer"
            color="white"
          ></v-app-bar-nav-icon>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="auto">
          <span class="mr-2" style="color: white; font-size: 14px"
            >Bem vindo</span
          >
          <span style="color: white; font-size: 14px"
            ><strong>{{ usuario }}</strong>
          </span>
        </v-col>
        <v-col cols="auto">
          <v-btn color="white" text @click="logout()">sair</v-btn>
        </v-col>
      </v-row>
    </v-app-bar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class AdmNavbar extends Vue {
  drawer = false;
  usuario = "";

  mounted() {
    this.usuario = this.$store.getters.getUserName;
  }

  get isLargeScreen(): boolean {
    return this.$vuetify.breakpoint.mdAndUp;
  }

  toggleDrawer(): void {
    if (!this.isLargeScreen) {
      this.drawer = !this.drawer;
    }
  }

  logout() {
    this.$store.dispatch("clearToken");
    this.$router.push("/");
    location.reload();
  }
}
</script>
