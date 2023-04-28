<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="900">
      <SuccessMessage :message="mensagem" :show="showMessagem" />

      <v-card-title>Dados da empresa</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="nomeEstabelecimento"
            label="Nome Estabelecimento"
          ></v-text-field>
          <v-text-field
            v-model="cnpj"
            label="CNPJ"
          ></v-text-field>
          <v-text-field
            v-model="email"
            label="Email"
          ></v-text-field>
          <v-text-field
            v-model="telefone"
            label="Telefone Contato"
          ></v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions class="text-center">
        <v-btn color="primary" @click="cadastrar"
          >Cadastrar</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Empresa } from "@/components/Empresa/EmpresaModel";
import EmpresaService from "@/components/Empresa/EmpresaService";
import vueMask from "v-mask";
import SuccessMessage from "@/components/alerts/SuccessMessage.vue";

Vue.use(vueMask);

@Component({
  components: {
    SuccessMessage,
  },
})
export default class CadastroEmpresa extends Vue {
  nomeEstabelecimento: string = "";
  cnpj: number = 0;
  email: string = "";
  telefone: string = "";
  mensagem: string = "";
  showMessagem: boolean = false;
  tipoMessagem: string = "";
  valid:boolean = false;

  cadastrar() {
    const empresa: Empresa = {
      nomeEstabelecimento: this.nomeEstabelecimento,
      cnpj: this.cnpj,
      email: this.email,
      telefoneContato: this.telefone,
    };

    EmpresaService.salvar(empresa)
      .then((response: any) => {
        this.mensagem = "Empresa salva com sucesso";
        this.showMessagem = true;

        setTimeout(() => {
          this.$router.push("/admin");
        }, 3000);
      })
      .catch((error) => {
        this.mensagem = error.response.data.errors.join("\n");
        this.showMessagem = true;
        setTimeout(() => {
          this.showMessagem = false;
        }, 2000);
      });
  }
}
</script>
