<template>
  <v-container fluid>
    <v-card
      class="bg-card mx-auto pa-4 mt-6 rounded-lg"
      elevation="0"
      shaped
      max-width="1300"
    >
      <SuccessAlert :message="mensagem" :show="showMessagem" />

      <v-card-title class="mb-4 title">Dados da empresa</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="empresa.nomeEstabelecimento"
                label="Nome Estabelecimento"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="empresa.cnpj"
                label="CNPJ"
                v-mask="'##.###.###/####-##'"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="empresa.telefoneContato"
                label="Telefone Contato"
                :rules="[telefoneRule]"
                v-mask="'(##) #####-####'"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="empresa.email"
                label="Email"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>

    <v-card
      class="bg-card mx-auto pa-4 mt-6 rounded-lg"
      elevation="0"
      shaped
      max-width="1300"
    >
      <v-card-title class="mb-4 title">Dados Públicos</v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="empresa.whatsapp"
              label="WhatsApp"
              :rules="[telefoneRule]"
              v-mask="'(##) #####-####'"
              outlined
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="empresa.site"
              label="Website"
              outlined
              dense
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="empresa.facebook"
              label="Facebook"
              outlined
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field v-model="empresa.instagram" label="Instagram" outlined dense>
            </v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12">
            <v-textarea
              outlined
              dense
              label="Descrição"
              v-model="empresa.descricao"
            ></v-textarea>
          </v-col>
        </v-row>

        <v-row class="d-flex flex-row-reverse mt-4">
          <v-card-actions>
            <v-btn color="primary" @click="atualizar" elevation="0"
              >Salvar Alterações</v-btn
            >
          </v-card-actions>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Empresa } from "@/components/Empresa/EmpresaModel";
import EmpresaService from "@/components/Empresa/EmpresaService";
import vueMask from "v-mask";
import SuccessAlert from "@/components/ComponentesGerais/alerts/SuccessAlert.vue";

Vue.use(vueMask);

@Component({
  components: {
    SuccessAlert,
  },
})
export default class DadosEmpresa extends Vue {

  mensagem: string = "";
  showMessagem: boolean = false;
  tipoMessagem: string = "";
  valid: boolean = false;
  empresa = {} as Empresa;

  requiredRule = (v: any) => !!v || "Campo obrigatório";
  telefoneRule = (v: string) => (v && v.length >= 10) || "Número inválido";

  async mounted() {
    this.empresa = await EmpresaService.buscar();
  }

  atualizar() {
    const empresa: Empresa = this.empresa;

    EmpresaService.atualizar(empresa)
      .then((response: any) => {
        this.mensagem = "Dados atualizados com sucesso!";
        this.showMessagem = true;

        setTimeout(() => {
          this.$router.push("/admin");
        }, 2000);
      })
      .catch((error) => {
        this.mensagem = error.response.data.errors.join("\n");
        this.showMessagem = true;
        setTimeout(() => {
          this.showMessagem = false;
        }, 30000);
      });
  }
}
</script>

<style scoped>
.bg-card {
  background-color: #f7f9fa;
}
.title {
  color: #191c1f;
  font-family: Nunito Sans, -apple-system, BlinkMacSystemFont, Segoe UI,
    Helvetica, Arial, sans-serif;
  font-size: 1.5rem;
  font-weight: 400;
  line-height: 1.5;
}
.v-text-field--outlined >>> fieldset {
  border-color: #c9ced4;
  background-color: #ffffff;
}
.v-btn {
  text-transform: unset !important;
  font-size: 0.9rem;
  font-weight: 350;
  line-height: 1;
}
.col-sm-6,
.col-12 {
  padding: 0px 8px;
}
</style>
