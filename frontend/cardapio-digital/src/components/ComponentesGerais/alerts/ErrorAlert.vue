<template>
  <v-alert type="error" top v-if="show">
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

  private watcherShow(newValue: boolean, oldValue: boolean): void {
    setTimeout(() => {
      this.$emit("showErrorAlert");
    }, 2000);
  }

  private mounted(): void {
    this.$watch("show", this.watcherShow);
  }
}
</script>
