<template>
  <v-container fluid>
    <v-row align="center">
      <v-col cols="12" sm="6" md="8" class="text-right">
        <v-row no-gutters>
          <v-col cols="auto">
            <v-btn text @click="goToLoginPage">Entrar</v-btn>
          </v-col>
          <v-col cols="auto">
            <v-btn text @click="goToCadastrarNovoUsuarioPage">Cadastre-se</v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>




<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Navbar from "../Layout/Navbar.vue";

@Component({
  components: {
    Navbar,
  },
})
export default class Apresentacao extends Vue {
  goToLoginPage() {
    this.$router.push("/login");
  }

  goToCadastrarNovoUsuarioPage() {
    this.$router.push("/casdastrarNovoUsuario");
  }
}
</script>

<style scoped>
.container {
  max-width: 1300px;
}
</style>
