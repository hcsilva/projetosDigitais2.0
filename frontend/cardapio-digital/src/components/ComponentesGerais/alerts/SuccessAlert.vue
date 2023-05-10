<template>
  <v-alert type="success" top v-if="show">
    {{ message }}
  </v-alert>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class SuccessMessage extends Vue {
  @Prop({ required: true }) message!: string;
  @Prop({ default: 2000 }) timeout!: number;
  @Prop({ required: true }) show!: boolean;
}
</script>
