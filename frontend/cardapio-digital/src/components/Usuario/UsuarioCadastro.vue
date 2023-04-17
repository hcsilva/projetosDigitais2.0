<template>
  <v-card class="mx-auto d-flex flex-column align-center" max-width="500">
    <v-snackbar
      v-model="snackbar.visible"
      :timeout="2000"
      top
      color="success"
      dark
    >
      {{ snackbar.message }}
    </v-snackbar>

    <v-card-title>Cadastro de Usuário</v-card-title>
    <v-card-text>
      <v-form ref="form" v-model="valid">
        <v-text-field
          v-model="nome"
          label="Nome"
          :rules="[this.requiredRule]"
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
      <v-btn :disabled="!valid" color="primary" @click="cadastrar"
        >Cadastrar</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Usuario } from "@/components/Usuario/UsuarioModel";
import UsuarioService from "@/components/Usuario/UsuarioService";
import vueMask from "v-mask";

Vue.use(vueMask);

@Component({})
export default class CadastroUsuario extends Vue {
  private nome: string = "";
  private telefone: string = "";
  private email: string = "";
  private senha: string = "";
  private confirmarSenha: string = "";
  private termosDeUso: boolean = false;
  private valid: boolean = false;
  private snackbar = {
    visible: false,
    message: "",
  };

  private showSnackbar(message: string) {
    this.snackbar.message = message;
    this.snackbar.visible = true;
  }

  private requiredRule = (v: any) => !!v || "Campo obrigatório";
  private emailRule = (v: string) => /.+@.+\..+/.test(v) || "E-mail inválido";
  private senhaRule = (v: string) =>
    (v && v.length >= 8) ||
    "Senha inválida, deve conter no mínimo 8 caracteres";

  private get confirmarSenhaRule() {
    return [
      this.requiredRule,
      (v: string) => v === this.senha || "Senhas não estão iguais",
    ];
  }

  private telefoneRule = (v: string) =>
    (v && v.length >= 10) || "Telefone inválido";

  private cadastrar() {
    const usuario: Usuario = {
      nome: this.nome,
      telefone: this.telefone,
      login: this.email,
      senha: this.senha,
    };

    UsuarioService.salvar(usuario)
      .then((response: any) => {
        this.showSnackbar("Usuário criado com sucesso");
        setTimeout(() => {
          this.$router.push("/login");
        }, 3000);
      })
      .catch((e: Error) => {
        console.log(e);
      });
  }
}
</script>