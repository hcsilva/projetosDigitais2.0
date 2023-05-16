<template>
  <v-alert :type="type" top v-if="show">
    {{ message }}
  </v-alert>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { AlertType } from "@/components/Enums/AlertType";

@Component
export default class AlertMessage extends Vue {
  @Prop({ required: true }) message!: string;
  @Prop({ default: 3000 }) timeout!: number;
  @Prop({ required: true }) show!: boolean;
  @Prop({ required: true }) type!: AlertType;

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
