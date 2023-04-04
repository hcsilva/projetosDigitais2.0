<template>
  <v-btn @click="fetchData">{{ buttonText }}</v-btn>
</template>

<script>
import api from "../api/api";

export default {
  data() {
    return {
      buttonText: "Clique aqui para carregar dados",
    };
  },
  methods: {
    async fetchData() {
      try {
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
};
</script>
