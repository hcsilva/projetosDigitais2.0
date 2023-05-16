<template>
  <v-container fluid>
    <v-card class="mx-auto" max-width="400">
      <AlertMessage
        :message="mensagem"
        :show="showMessagem"
        :type="alertType"
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
import { AlertType } from "@/components/Enums/AlertType";
import AlertMessage from "../ComponentesGerais/alerts/AlertMessage.vue";

@Component({
  components: {
    AlertMessage,
  },
})
export default class LoginPage extends Vue {
  alertType: AlertType | null = null;
  email: string = "admin@cardapiodigital.com";
  password: string = "123";
  showPassword: boolean = false;
  isFormValid: boolean = true;
  mensagem: string = "";
  showMessagem: boolean = false;

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
        this.alertType = AlertType.Error;
        this.showMessagem = true;
      });
  }

  forgotPassword() {}
}
</script>
