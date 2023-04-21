<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="400">
      <SuccessMessage :message="mensagem" :show="showMessagem" />

      <v-card-title>Cadastro de Usuário</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="nome"
            label="Nome"
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
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { Usuario } from "@/components/Usuario/UsuarioModel";
import UsuarioService from "@/components/Usuario/UsuarioService";
import vueMask from "v-mask";
import SuccessMessage from "@/components/Snackbars/SuccessMessage.vue";

Vue.use(vueMask);

@Component({
  components: {
    SuccessMessage,
  },
})
export default class CadastroUsuario extends Vue {
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
    const usuario: Usuario = {
      nome: this.nome,
      telefone: this.telefone,
      login: this.email,
      senha: this.senha,
    };

    UsuarioService.salvar(usuario)
      .then((response: any) => {
        this.mensagem = "Usuário criado com sucesso";
        this.showMessagem = true;

        setTimeout(() => {
          this.$router.push("/login");
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
