<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="900">
      <v-card-title>Cadastro de links</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="descricao"
            label="Descrição"
            :rules="descricaoRules"
          ></v-text-field>
          <v-text-field v-model="link" :rules="urlRules" label="Link">
          </v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions class="text-center">
        <v-btn color="#F0A500" dark @click="salvar">Salvar</v-btn>
        <v-btn color="error" dark @click="cancelar">Cancelar</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class LinkWebComponent extends Vue {
  valid: boolean = false;
  descricao: string = "";
  link: string = "";
  linkValido = true;
  id: string | null = null;

  urlRules = [(value: string) => !!value || "O link é obrigatório"];

  descricaoRules = [(value: string) => !!value || "A descrição é obrigatório"];

  created() {
    this.id = this.$route.params.id;
  }

  salvar(this: any) {
    if (this.$refs.form.validate()) {
      console.log("salvar");
    }
  }

  openLink() {
    window.open(this.link, "_blank");
  }

  cancelar() {
    this.$router.push("/link");
  }
}
</script>

<style scoped>
.container {
  max-width: 1300px;
}
</style>
