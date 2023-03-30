<template>


<v-app class="d-flex justify-center">
    <v-container>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="razaoSocial" label="Razão Social"></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="cnpj" label="CNPJ"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-textarea v-model="descricao" label="Descrição"></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field v-model="email" label="Email"></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-app>



</template>

<script>
export default {
  name: "HelloWorld",

  data() {
    return {
      razaoSocial: "",
      cnpj: "",
      descricao: "",
      email: "",
    };
  },
};
</script>
