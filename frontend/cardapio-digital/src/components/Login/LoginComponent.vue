<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="400">
      <ErrorAlert
        :message="mensagem"
        :show="showMessagem"
        :AlertType="AlertType.Success"
        @showErrorAlert="showMessagem = false"
      />

      <v-card-title class="text-center">Faça login</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="isFormValid">
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="Email"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :rules="senhaRules"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            @click:append="showPassword = !showPassword"
            label="Senha"
            required
          ></v-text-field>

          <v-row class="mt-4">
            <v-col>
              <v-btn color="primary" block @click="login()">Entrar</v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-row class="mx-auto">
          <v-col>
            <small>
              <a href="#" @click.prevent="forgotPassword"
                >Esqueci minha senha</a
              >
            </small>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import LoginService from "./LoginService";
import ErrorAlert from "@/components/ComponentesGerais/alerts/ErrorAlert.vue";
import { AlertType } from "@/components/Enums/AlertType";

@Component({
  components: {
    ErrorAlert,
  },
})
export default class LoginPage extends Vue {
  AlertType = AlertType;
  email: string = "";
  password: string = "";
  showPassword: boolean = false;
  isFormValid: boolean = true;
  mensagem: string = "";
  showMessagem: boolean = false;
  snackbar = {
    visible: false,
    message: "",
  };

  private showSnackbar(message: string) {
    this.snackbar.message = message;
    this.snackbar.visible = true;
  }

  get emailRules() {
    return [
      (v: string) => !!v || "O email é obrigatório",
      (v: string) => /.+@.+\..+/.test(v) || "Por favor insira um email válido",
    ];
  }

  get senhaRules() {
    return [(v: string) => !!v || "A senha é obrigatória"];
  }

  login(this: any) {
    if (!this.$refs.form.validate()) {
      return null;
    }

    LoginService.getLogin(this.email, this.password)
      .then((response) => {
        this.$store.commit("setAuthToken", response.data);
        this.$router.push("/admin");
      })
      .catch((e: Error) => {
        this.mensagem = "Usuário ou senha inválidos";
        this.showMessagem = true;
      });
  }

  forgotPassword() {}
}
</script>
