<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card>
          <v-card-title class="headline">Login</v-card-title>
          <v-card-text>
            <v-form @submit.prevent="login">
              <v-text-field label="Email" v-model="email"></v-text-field>
              <v-text-field
                label="Password"
                type="password"
                v-model="password"
              ></v-text-field>
              <v-btn type="submit" color="primary">Login</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import Vue from "vue";
import axios, { AxiosError, AxiosResponse } from "axios";
import api from "../api/api";
import router from "@/router";

export default Vue.extend({
  mixins: [api],
  data() {
    return {
      email: "",
      password: "",
    };
  },

  methods: {
    async login(): Promise<void> {
      try {
        debugger;

     axios
          .post("http://localhost:8081/api/login", {
            login: this.email,
            senha: this.password,
          })
          .then(function (response) {
           localStorage.setItem("token", response.data.token);
           
          });

        //const token = response.token;
        //localStorage.setItem("token", token);

        console.log("******************");
        console.log(localStorage.getItem("token"));

        api
          .get("/empresa")
          .then(function (response) {
            console.log(response.data);
            router.push({ path: "/about" });
          })
          .catch(function (error) {
            console.error(error);
          });
      } catch (error) {
        console.error(error);
      }
    },
  },
});
</script>