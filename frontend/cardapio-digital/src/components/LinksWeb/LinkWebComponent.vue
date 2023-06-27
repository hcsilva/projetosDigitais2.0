<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="900">
      <AlertMessage
        :message="mensagem"
        :show="showMessage"
        :type="alertType"
        @showErrorAlert="showMessage = true"
      />
      <v-card-title>Cadastro de links</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="link.descricao"
            label="Descrição"
            :rules="descricaoRules"
          ></v-text-field>
          <v-text-field v-model="link.url" :rules="urlRules" label="Link">
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
import LinkWebService from "./LinkWebService";
import { LinkWeb } from "./LinkWebModel";
import { AlertType } from "../Enums/AlertType";
import AlertMessage from "../ComponentesGerais/alerts/AlertMessage.vue";

@Component({
  components: {
    AlertMessage,
  },
})
export default class LinkWebComponent extends Vue {
  valid: boolean = false;
  linkValido = true;
  id: string | null = null;
  link = {} as LinkWeb;
  alertType: AlertType | null = null;
  mensagem: string = "";
  showMessage: boolean = false;
  shouldValidate: boolean = false;
  urlRules = [(value: string) => !!value || "O link é obrigatório"];
  descricaoRules = [(value: string) => !!value || "A descrição é obrigatório"];

  created() {
    this.id = this.$route.params.id;
  }

  salvar(this: any) {
    if (this.$refs.form.validate()) {
      LinkWebService.salvarLink(this.link)
        .then((response: any) => {
          this.mensagem = "Link salvo com sucesso!";
          this.alertType = AlertType.Success;
          this.showMessage = true;
          this.link = {};
        })
        .catch((error) => {
          this.mensagem = error.response.data.errors.join("\n");
          this.showMessage = true;
          this.alertType = AlertType.Error;
        });
    }
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
