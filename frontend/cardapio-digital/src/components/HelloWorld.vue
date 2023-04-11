<template>
  <Navbar/>
</template>

<script lang="ts">
import Vue from "vue";
import Navbar from "./Layout/Navbar.vue";

export default Vue.extend({
  name: "HelloWorld",
  components: { Navbar }
});
</script>
