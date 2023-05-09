<template>
    <v-dialog v-model="dialog" max-width="800px" persistent>
      <v-container class="white">
        <v-row align="center">
          <v-col cols="auto">
            <slot name="modalTitle"></slot>
          </v-col>
  
        </v-row>
  
        <v-divider class="mt-2"></v-divider>
  
        <v-row class="pa-3">
          <v-col>
            <slot></slot>
          </v-col>
        </v-row>
  
        <v-divider />
  
        <v-row justify="end" class="pa-5">
          <slot name="modalButtons"></slot>
        </v-row>
      </v-container>
    </v-dialog>
  </template>
  
  <script lang="ts">
  import { Vue, Component, Prop } from "vue-property-decorator";
  
  @Component
  export default class Modal extends Vue {
    @Prop({ required: false }) value!: boolean;
  
    get dialog() {
      return this.value;
    }
  
    set dialog(value: boolean) {
      this.$emit("value", value);
    }
  }
  </script>
  
  <style scoped>
  .btn {
    border-radius: 8px;
  }
  </style>
  