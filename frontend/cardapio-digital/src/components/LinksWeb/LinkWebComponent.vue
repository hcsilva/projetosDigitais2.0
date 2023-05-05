<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="900">
      <v-card-title>Cadastro de links</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="descricao"
            label="Descrição"
            required
          ></v-text-field>
          <v-text-field v-model="link" :rules="urlRules" label="Link">
            <template #append>
              <v-icon v-if="true">mdi-arrow-right</v-icon>
            </template>
          </v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions class="text-center">
        <v-btn color="primary" @click="salvar">Salvar</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import LinkWebService from "./LinkWebService";

@Component
export default class LinkWeb extends Vue {
  valid: boolean = false;
  descricao: string = "";
  link: string = "";
  linkValido = true;
  urlRules = [
    (value: string) => !!value || "O link é obrigatório",
    (value: string) =>
      /^https?:\/\/\S+$/.test(value) || "O link precisa ser uma URL válida",
  ];

  salvar(this: any) {
    // if (this.$refs.form.validate()) {
    //   console.log("salvar");
    // }

    LinkWebService.getById();
  }

  openLink() {
    window.open(this.link, "_blank");
  }

  updated() {
    this.linkValido =
      this.link.length > 0 &&
      this.urlRules.every((rule) => rule(this.link) === true);
    console.log(this.linkValido);
  }
}
</script>
