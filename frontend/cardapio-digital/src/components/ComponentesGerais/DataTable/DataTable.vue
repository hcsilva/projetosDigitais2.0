<template>
  <v-data-table
    disable-sort
    :headers="headers"
    :items="items"
    noDataText="Nenhum registro encontrado"
    class="mt-2 pa-3 dataTableBorder"
    style="font-size: 10px"
    :footer-props="{
      itemsPerPageOptions: [10, 50, 100],
      itemsPerPageText: 'Linhas por Página',
    }"
  >
    <template #item.acoes="{ item }">
      <v-icon @click="editarItem(item)" small class="mr-2">mdi-pencil</v-icon>
      <v-icon @click="excluirItem(item)" small>mdi-delete</v-icon>
    </template>
  </v-data-table>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class DataTable extends Vue {
  @Prop({ default: [] }) items!: any[];
  @Prop({ required: true }) headers!: any[];

  editarItem(item: any) {
    this.$emit("editar", item);
  }

  excluirItem(item: any) {
    this.$emit("deletar", item);
  }
}
</script>

<style>
.btn {
  border-radius: 8px;
}

.active {
  opacity: 0%;
}

.dot-menu {
  min-height: 30px;
}

.chip {
  width: 60px;
}

.dataTableBorder {
  border-style: solid;
  border-width: 1px;
  border-color: #cccccc;
  border-radius: 8px;
}
</style>
