<template>
  <v-container fluid>
    <AlertMessage
      :message="mensagem"
      :show="showMessage"
      :type="alertType"
      @showErrorAlert="showMessage = true"
    />

    <v-card
      class="bg-card mx-auto pa-4 mt-6 rounded-lg"
      elevation="0"
      shaped
      max-width="1300"
    >
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
        <v-form ref="form" v-model="validDadosPublicos">
          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field
                prepend-icon="mdi-whatsapp"
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
                prepend-icon="mdi-web"
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
                prepend-icon="mdi-facebook"
                v-model="empresa.facebook"
                label="Facebook"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                v-model="empresa.instagram"
                prepend-icon="mdi-instagram"
                label="Instagram"
                outlined
                dense
              >
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
        </v-form>
      </v-card-text>
    </v-card>

    <v-card
      class="bg-card mx-auto pa-4 mt-6 rounded-lg"
      elevation="0"
      shaped
      max-width="1300"
    >
      <v-card-title class="mb-4 title">Endereço</v-card-title>
      <v-card-text>
        <v-form ref="form">
          <v-row>
            <v-col cols="12" lg="4" sm="6">
              <v-text-field
                v-model="endereco.cep"
                label="CEP"
                outlined
                dense
                v-mask="'#####-###'"
                @change="buscarCEP"
              ></v-text-field>
            </v-col>
            <v-col cols="12" lg="4" sm="6">
              <v-text-field
                v-model="endereco.estado"
                label="Estado"
                outlined
                dense
                disabled
              ></v-text-field>
            </v-col>
            <v-col cols="12" lg="4" sm="6">
              <v-text-field
                v-model="endereco.cidade"
                label="Cidade / Município"
                outlined
                dense
                disabled
              ></v-text-field>
            </v-col>

            <v-col cols="12" lg="4" sm="6">
              <v-text-field
                v-model="endereco.logradouro"
                label="Endereço"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" lg="2" sm="3">
              <v-text-field
                v-model="endereco.numero"
                label="Número"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" lg="2" sm="3">
              <v-text-field
                v-model="endereco.complemento"
                label="Complemento"
                outlined
                dense
              ></v-text-field>
            </v-col>
            <v-col cols="12" lg="4" sm="6">
              <v-text-field
                v-model="endereco.bairro"
                label="Bairro"
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
      <v-card-title class="mb-4 title">Horários de atendimento</v-card-title>
      <v-card-text>
        <v-form ref="form">
          <v-row v-for="(linhaDia, idxDia) in diasHorarios" :key="idxDia">
            <v-col cols="2">
              <v-text-field
                class="label-horario"
                v-model="descricaoDiaSemana[idxDia]"
              ></v-text-field>
            </v-col>
            <v-col
              v-for="(horario, idxHorario) in linhaDia"
              :key="idxHorario"
              cols="12"
              sm="1"
            >
              <v-text-field
                class="centered-input"
                v-model="diasHorarios[idxDia][idxHorario]"
                outlined
                dense
                type="time"
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(idxDia)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(idxDia)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>

    <v-row class="d-flex flex-row-reverse mt-4">
      <v-card-actions>
        <v-btn
          color="primary"
          :disabled="!valid || !validDadosPublicos"
          @click="atualizar"
          elevation="0"
          >Salvar Alterações</v-btn
        >
      </v-card-actions>
    </v-row>

    <!-- <v-footer fixed class="ma-2">
      <v-row class="d-flex flex-row-reverse mt-4">
        <v-card-actions>
          <v-btn
            color="primary"
            fab
            :disabled="!valid || !validDadosPublicos"
            @click="atualizar"
          >
            <v-icon>mdi mdi-content-save</v-icon>
          </v-btn>
        </v-card-actions>
      </v-row>
    </v-footer> -->
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Empresa } from "@/components/Empresa/EmpresaModel";
import { Endereco } from "@/components/Endereco/EnderecoModel";
import { HorarioFuncionamento } from "@/components/HorarioFuncionamento/HorarioFuncionamentoModel";
import EmpresaService from "@/components/Empresa/EmpresaService";
import EnderecoService from "../Endereco/EnderecoService";
import vueMask from "v-mask";
import { AlertType } from "../Enums/AlertType";
import AlertMessage from "../ComponentesGerais/alerts/AlertMessage.vue";

Vue.use(vueMask);

@Component({
  components: {
    AlertMessage,
  },
})
export default class DadosEmpresa extends Vue {
  alertType: AlertType | null = null;
  mensagem: string = "";
  showMessage: boolean = false;
  valid: boolean = false;
  validDadosPublicos: boolean = false;
  empresa = {} as Empresa;
  endereco = {} as Endereco;
  horarios = {} as HorarioFuncionamento;

  descricaoDiaSemana: string[] = [
    "Domingo",
    "Segunda-feira",
    "Terça-feira",
    "Quarta-feira",
    "Quinta-feira",
    "Sexta-feira",
    "Sábado",
  ];

  diasHorarios: string[][] = Array(7)
    .fill("")
    .map(() => Array(4).fill(""));

  requiredRule = (v: any) => !!v || "Campo obrigatório";
  telefoneRule = (v: string) => (v && v.length >= 10) || "Número inválido";

  async mounted() {
    //TODO - tratar erro
    this.empresa = await EmpresaService.buscar();
    this.endereco =
      this.empresa.endereco != null ? this.empresa.endereco : this.endereco;

    if (this.empresa.horario != null) {
      this.horarios = this.empresa.horario;
      this.diasHorarios = this.carregarMatrizHorarios(this.empresa.horario);
    }
  }

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  async buscarCEP() {
    if (this.endereco.cep != null) {
      this.endereco = await EnderecoService.carregarCEP(this.endereco.cep);
    }
  }

  limparHorarios(idxDia: number) {
    let matrizAux: string[][] = this.diasHorarios.map((row) => [...row]);

    for (let i = 0; i < matrizAux[idxDia].length; i++) {
      matrizAux[idxDia][i] = "";
    }

    this.diasHorarios = matrizAux;
  }

  replicarParaDiaSeguinte(idxDia: number) {
    let matrizAux: string[][] = this.diasHorarios.map((row) => [...row]);

    for (let i = 0; i < matrizAux[idxDia].length; i++) {
      if (idxDia < matrizAux.length - 1) {
        matrizAux[idxDia + 1][i] = matrizAux[idxDia][i];
      } else {
        matrizAux[0][i] = matrizAux[idxDia][i];
      }
    }

    this.diasHorarios = matrizAux;
  }

  carregarMatrizHorarios(objeto: HorarioFuncionamento): string[][] {
    const horarios: string[][] = Array(7)
      .fill("")
      .map(() => Array(4).fill(""));

    horarios[0] = [
      objeto.domingoInicial || "",
      objeto.domingoFinal || "",
      objeto.domingoInicial2 || "",
      objeto.domingoFinal2 || "",
    ];
    horarios[1] = [
      objeto.segundaInicial || "",
      objeto.segundaFinal || "",
      objeto.segundaInicial2 || "",
      objeto.segundaFinal2 || "",
    ];
    horarios[2] = [
      objeto.tercaInicial || "",
      objeto.tercaFinal || "",
      objeto.tercaInicial2 || "",
      objeto.tercaFinal2 || "",
    ];
    horarios[3] = [
      objeto.quartaInicial || "",
      objeto.quartaFinal || "",
      objeto.quartaInicial2 || "",
      objeto.quartaFinal2 || "",
    ];
    horarios[4] = [
      objeto.quintaInicial || "",
      objeto.quintaFinal || "",
      objeto.quintaInicial2 || "",
      objeto.quintaFinal2 || "",
    ];
    horarios[5] = [
      objeto.sextaInicial || "",
      objeto.sextaFinal || "",
      objeto.sextaInicial2 || "",
      objeto.sextaFinal2 || "",
    ];
    horarios[6] = [
      objeto.sabadoInicial || "",
      objeto.sabadoFinal || "",
      objeto.sabadoInicial2 || "",
      objeto.sabadoFinal2 || "",
    ];

    return horarios;
  }

  carregaObjHorario(matriz: string[][]): HorarioFuncionamento {

    this.horarios.domingoInicial = matriz[0][0];
    this.horarios.domingoFinal = matriz[0][1];
    this.horarios.domingoInicial2 = matriz[0][2];
    this.horarios.domingoFinal2 = matriz[0][3];

    this.horarios.segundaInicial = matriz[1][0];
    this.horarios.segundaFinal = matriz[1][1];
    this.horarios.segundaInicial2 = matriz[1][2];
    this.horarios.segundaFinal2 = matriz[1][3];

    this.horarios.tercaInicial = matriz[2][0];
    this.horarios.tercaFinal = matriz[2][1];
    this.horarios.tercaInicial2 = matriz[2][2];
    this.horarios.tercaFinal2 = matriz[2][3];

    this.horarios.quartaInicial = matriz[3][0];
    this.horarios.quartaFinal = matriz[3][1];
    this.horarios.quartaInicial2 = matriz[3][2];
    this.horarios.quartaFinal2 = matriz[3][3];

    this.horarios.quintaInicial = matriz[4][0];
    this.horarios.quintaFinal = matriz[4][1];
    this.horarios.quintaInicial2 = matriz[4][2];
    this.horarios.quintaFinal2 = matriz[4][3];

    this.horarios.sextaInicial = matriz[5][0];
    this.horarios.sextaFinal = matriz[5][1];
    this.horarios.sextaInicial2 = matriz[5][2];
    this.horarios.sextaFinal2 = matriz[5][3];

    this.horarios.sabadoInicial = matriz[6][0];
    this.horarios.sabadoFinal = matriz[6][1];
    this.horarios.sabadoInicial2 = matriz[6][2];
    this.horarios.sabadoFinal2 = matriz[6][3];

    return this.horarios;

  }

  verificarMatrizPreenchida(matriz: string[][]): boolean {
  for (let i = 0; i < matriz.length; i++) {
    for (let j = 0; j < matriz[i].length; j++) {
      if (matriz[i][j] !== null && matriz[i][j] !== undefined && matriz[i][j] !== '') {
        return true;
      }
    }
  }
  return false;
}

  removeCaracter(codNum: number, caracter: string): number {
    if (codNum == null || caracter == null) return 0;

    const aux: string = codNum.toString();
    return +aux.replace(caracter, "");
  }

  atualizar() {
    const empresa: Empresa = this.empresa;
    const endereco: Endereco = this.endereco;
    this.scrollToTop();

    if (endereco.cep) {
      endereco.cep = this.removeCaracter(endereco.cep, "-");
      empresa.endereco = endereco;
    }

    if (this.verificarMatrizPreenchida(this.diasHorarios)) {
      empresa.horario = this.carregaObjHorario(this.diasHorarios);
    }

    EmpresaService.atualizar(empresa)
      .then((response: any) => {
        this.mensagem = "Dados atualizados com sucesso!";
        this.alertType = AlertType.Success;
        this.showMessage = true;

        setTimeout(() => {
          this.$router.push("/admin");
        }, 2000);
      })
      .catch((error) => {
        this.mensagem = error.response.data.errors.join("\n");
        this.showMessage = true;
        this.alertType = AlertType.Error;
      });
  }
}
</script>

<style scoped>
.container {
  max-width: 1300px;
}

.v-footer {
  background-color: transparent;
}

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
.label-horario {
  font-size: 1rem;
  font-weight: 400;
  margin-top: -15px;
}
.v-text-field--outlined >>> fieldset {
  border-color: #c9ced4;
  background-color: #ffffff;
  text-align: center;
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
.centered-input >>> input {
  text-align: center;
}
</style>
