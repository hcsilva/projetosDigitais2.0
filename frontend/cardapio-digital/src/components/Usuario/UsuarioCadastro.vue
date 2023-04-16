<template>
  <v-card class="mx-auto d-flex flex-column align-center" max-width="500">
    <v-card-title>Cadastro de Usuário</v-card-title>
    <v-card-text>
      <v-form ref="form" v-model="valid" lazy-validation>
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
          :rules="[requiredRule, senhaRule, confirmarSenhaRule]"
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
import { Component, Vue } from "vue-property-decorator";
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

  private requiredRule = (v: any) => !!v || "Campo obrigatório";
  private emailRule = (v: string) => /.+@.+\..+/.test(v) || "E-mail inválido";
  private senhaRule = (v: string) =>
    (v && v.length >= 8) ||
    "Senha inválida, deve conter no mínimo 8 caracteres";
  private confirmarSenhaRule = (v: string) =>
    v === this.senha || "Senhas não estão iguais";
  private telefoneRule = (v: string) =>
    (v && v.length >= 10) || "Telefone inválido";

  private cadastrar() {
    // Lógica de cadastro aqui
  }
}
</script>