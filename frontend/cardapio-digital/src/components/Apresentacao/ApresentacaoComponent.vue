<template>
  <Navbar />
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Navbar from "../Layout/Navbar.vue";

@Component({
  components: {
    Navbar,
  },
})
export default class Apresentacao extends Vue {}
</script>