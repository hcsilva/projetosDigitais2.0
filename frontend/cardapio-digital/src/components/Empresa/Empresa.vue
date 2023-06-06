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
          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Segunda-feira</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.segundaIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.segundaFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.segundaIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.segundaFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(2)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(2)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Terça-feira</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.tercaIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.tercaFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.tercaIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.tercaFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(3)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(3)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Quarta-feira</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quartaIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quartaFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quartaIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quartaFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(4)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(4)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Quinta-feira</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quintaIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quintaFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quintaIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.quintaFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(5)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(5)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Sexta-feira</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sextaIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sextaFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sextaIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sextaFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(6)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(6)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Sábado</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sabadoIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sabadoFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sabadoIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.sabadoFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(7)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(7)"
              >
                <v-icon dark> mdi-eraser </v-icon>
              </v-btn>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="2">
              <v-subheader class="label-horario">Domingo</v-subheader>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.domingoIni"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.domingoFim"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.domingoIni2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-text-field
                class="centered-input"
                v-model="horaFunc.domingoFim2"
                placeholder="__:__"
                outlined
                dense
                v-mask="'##:##'"
              >
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="1">
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="replicarParaDiaSeguinte(1)"
              >
                <v-icon dark> mdi mdi-arrow-collapse-down </v-icon>
              </v-btn>
              <v-btn
                elevation="0"
                icon
                color="primary"
                @click="limparHorarios(1)"
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
import { Endereco } from "@/components/Empresa/EnderecoModel";
import { HorarioFuncionamento } from "@/components/Empresa/HorarioFuncionamentoModel";
import EmpresaService from "@/components/Empresa/EmpresaService";
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
  horaFunc = {} as HorarioFuncionamento;

  requiredRule = (v: any) => !!v || "Campo obrigatório";
  telefoneRule = (v: string) => (v && v.length >= 10) || "Número inválido";

  async mounted() {
    //TODO - tratar erro
    this.empresa = await EmpresaService.buscar();
    this.endereco = this.empresa.endereco != null ? this.empresa.endereco : this.endereco;
  }

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  async buscarCEP() {
    if (this.endereco.cep != null) {
      this.endereco = await EmpresaService.carregarCEP(this.endereco.cep);
    }
  }

  limparHorarios(numDiaSemana: number) {
    if (numDiaSemana === 2) {
      this.horaFunc.segundaIni = "";
      this.horaFunc.segundaFim = "";
      this.horaFunc.segundaIni2 = "";
      this.horaFunc.segundaFim2 = "";
    } else if (numDiaSemana === 3) {
      this.horaFunc.tercaIni = "";
      this.horaFunc.tercaFim = "";
      this.horaFunc.tercaIni2 = "";
      this.horaFunc.tercaFim2 = "";
    } else if (numDiaSemana === 4) {
      this.horaFunc.quartaIni = "";
      this.horaFunc.quartaFim = "";
      this.horaFunc.quartaIni2 = "";
      this.horaFunc.quartaFim2 = "";
    } else if (numDiaSemana === 5) {
      this.horaFunc.quintaIni = "";
      this.horaFunc.quintaFim = "";
      this.horaFunc.quintaIni2 = "";
      this.horaFunc.quintaFim2 = "";
    } else if (numDiaSemana === 6) {
      this.horaFunc.sextaIni = "";
      this.horaFunc.sextaFim = "";
      this.horaFunc.sextaIni2 = "";
      this.horaFunc.sextaFim2 = "";
    } else if (numDiaSemana === 7) {
      this.horaFunc.sabadoIni = "";
      this.horaFunc.sabadoFim = "";
      this.horaFunc.sabadoIni2 = "";
      this.horaFunc.sabadoFim2 = "";
    } else if (numDiaSemana === 1) {
      this.horaFunc.domingoIni = "";
      this.horaFunc.domingoFim = "";
      this.horaFunc.domingoIni2 = "";
      this.horaFunc.domingoFim2 = "";
    }
  }

  replicarParaDiaSeguinte(numDiaSemana: number) {
    if (numDiaSemana === 2) {
      this.horaFunc.tercaIni = this.horaFunc.segundaIni;
      this.horaFunc.tercaFim = this.horaFunc.segundaFim;
      this.horaFunc.tercaIni2 = this.horaFunc.segundaIni2;
      this.horaFunc.tercaFim2 = this.horaFunc.segundaFim2;
    } else if (numDiaSemana === 3) {
      this.horaFunc.quartaIni = this.horaFunc.tercaIni;
      this.horaFunc.quartaFim = this.horaFunc.tercaFim;
      this.horaFunc.quartaIni2 = this.horaFunc.tercaIni2;
      this.horaFunc.quartaFim2 = this.horaFunc.tercaFim2;
    } else if (numDiaSemana === 4) {
      this.horaFunc.quintaIni = this.horaFunc.quartaIni;
      this.horaFunc.quintaFim = this.horaFunc.quartaFim;
      this.horaFunc.quintaIni2 = this.horaFunc.quartaIni2;
      this.horaFunc.quintaFim2 = this.horaFunc.quartaFim2;
    } else if (numDiaSemana === 5) {
      this.horaFunc.sextaIni = this.horaFunc.quintaIni;
      this.horaFunc.sextaFim = this.horaFunc.quintaFim;
      this.horaFunc.sextaIni2 = this.horaFunc.quintaIni2;
      this.horaFunc.sextaFim2 = this.horaFunc.quintaFim2;
    } else if (numDiaSemana === 6) {
      this.horaFunc.sabadoIni = this.horaFunc.sextaIni;
      this.horaFunc.sabadoFim = this.horaFunc.sextaFim;
      this.horaFunc.sabadoIni2 = this.horaFunc.sextaIni2;
      this.horaFunc.sabadoFim2 = this.horaFunc.sextaFim2;
    } else if (numDiaSemana === 7) {
      this.horaFunc.domingoIni = this.horaFunc.sabadoIni;
      this.horaFunc.domingoFim = this.horaFunc.sabadoFim;
      this.horaFunc.domingoIni2 = this.horaFunc.sabadoIni2;
      this.horaFunc.domingoFim2 = this.horaFunc.sabadoFim2;
    } else if (numDiaSemana === 1) {
      this.horaFunc.segundaIni = this.horaFunc.domingoIni;
      this.horaFunc.segundaFim = this.horaFunc.domingoFim;
      this.horaFunc.segundaIni2 = this.horaFunc.domingoIni2;
      this.horaFunc.segundaFim2 = this.horaFunc.domingoFim2;
    }
  }

  removeCaracter(codNum: number, caracter: string): number{

    if(codNum == null || caracter == null) return 0;

    const aux: string = codNum.toString();
    return +aux.replace(caracter,"");
  }

  atualizar() {
    const empresa: Empresa = this.empresa;
    const endereco: Endereco = this.endereco;
    this.scrollToTop();

    if(endereco.cep != null){
      endereco.cep = this.removeCaracter(endereco.cep, "-");
      empresa.endereco = endereco;
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
