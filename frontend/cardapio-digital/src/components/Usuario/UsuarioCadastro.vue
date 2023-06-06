<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="400">
      <AlertMessage
        :message="mensagem"
        :show="showMessagem"
        :type="alertType"
        @showErrorAlert="showMessagem = false"
      />

      <v-card-title>Cadastro de Usuário</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="nome"
            label="Nome Completo"
            :rules="[requiredRule]"
          ></v-text-field>
          <v-text-field
            v-model="telefone"
            label="Telefone"
            :rules="[requiredRule, telefoneRule]"
            v-mask="'(##) #####-####'"
          ></v-text-field>
          <v-text-field
            v-model="email"
            label="E-mail"
            :rules="[requiredRule, emailRule]"
          ></v-text-field>
          <v-text-field
            v-model="nomeEstabelecimento"
            label="Nome Estabelecimento"
            :rules="[requiredRule]"
          ></v-text-field>
          <v-text-field
            v-model="senha"
            label="Senha"
            :rules="[requiredRule, senhaRule]"
            type="password"
          ></v-text-field>
          <v-text-field
            v-model="confirmarSenha"
            label="Confirmar Senha"
            :rules="confirmarSenhaRule"
            type="password"
          ></v-text-field>
          <v-checkbox
            v-model="termosDeUso"
            :rules="[requiredRule]"
            label="Eu li e concordo com os termos de uso"
          ></v-checkbox>
        </v-form>
      </v-card-text>
      <v-card-actions class="text-center">
        <v-btn
          :disabled="!valid"
          color="primary"
          @click="cadastrar"
          :loading="loading"
          >Cadastrar</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Usuario } from "@/components/Usuario/UsuarioModel";
import { Empresa } from "@/components/Empresa/EmpresaModel";
import UsuarioService from "@/components/Usuario/UsuarioService";
import vueMask from "v-mask";
import { AlertType } from "../Enums/AlertType";
import AlertMessage from "../ComponentesGerais/alerts/AlertMessage.vue";

Vue.use(vueMask);

@Component({
  components: {
    AlertMessage,
  },
})
export default class CadastroUsuario extends Vue {
  alertType: AlertType | null = null;
  nome: string = "";
  telefone: string = "";
  email: string = "";
  senha: string = "";
  confirmarSenha: string = "";
  termosDeUso: boolean = false;
  valid: boolean = false;
  mensagem: string = "";
  showMessagem: boolean = false;
  tipoMessagem: string = "";
  nomeEstabelecimento: string = "";
  loading: boolean = false;

  requiredRule = (v: any) => !!v || "Campo obrigatório";
  emailRule = (v: string) => /.+@.+\..+/.test(v) || "E-mail inválido";
  senhaRule = (v: string) =>
    (v && v.length >= 8) ||
    "Senha inválida, deve conter no mínimo 8 caracteres";

  get confirmarSenhaRule() {
    return [
      this.requiredRule,
      (v: string) => v === this.senha || "Senhas não estão iguais",
    ];
  }

  telefoneRule = (v: string) => (v && v.length >= 10) || "Telefone inválido";

  cadastrar() {
    this.loading = true;
    const empresa: Empresa = {
      nomeEstabelecimento: this.nomeEstabelecimento,
      telefoneContato: this.telefone,
      email: this.email,
    };

    const usuario: Usuario = {
      nome: this.nome,
      login: this.email.toLowerCase(),
      senha: this.senha,
      empresa: empresa,
    };

    UsuarioService.salvar(usuario)
      .then((response: any) => {
        this.mensagem = "Usuário criado com sucesso";
        this.alertType = AlertType.Success;
        this.showMessagem = true;

        this.loading = false;
        this.limparCampos();

        setTimeout(() => {
          this.$router.push("/login");
        }, 3000);
      })
      .catch((error) => {
        this.loading = false;
        this.mensagem = error.response.data.errors.join("\n");
        this.alertType = AlertType.Error;
        this.showMessagem = true;
      });
  }

  limparCampos(this: any) {
    this.$refs.form.reset();
  }
}
</script>
