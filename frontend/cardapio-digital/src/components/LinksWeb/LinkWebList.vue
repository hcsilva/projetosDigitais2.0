<template>
  <v-container fluid>
    <v-row class="mt-4">
      <v-col col="12">
        <v-data-table
          ref="dataTable"
          disable-sort
          :headers="headers"
          style="font-size: 10px"
          noDataText="Nenhum registro encontrado"
          loading-text="Aguarde, carregando Links..."
          :loading="tableLoading"
          :items="items"
          class="elevation-1"
          :footer-props="{
            itemsPerPageOptions: [10, 50, 100],
            itemsPerPageText: 'Itens por Página',
          }"
        >
          <template #item.link="{ item }">
            {{ item.link }}
          </template>

          <template #item.acoes="{ item }">
            <v-icon @click="editarItem(item)" small class="mr-2"
              >mdi-pencil</v-icon
            >
            <v-icon @click="excluirItem(item)" small>mdi-delete</v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ModalComponent from "@/components/Modal/ModalComponent.vue";
import LinkWebComponent from "@/components/LinksWeb/LinkWebComponent.vue";

@Component({
  components: {
    ModalComponent,
    LinkWebComponent,
  },
})
export default class LinkWeb extends Vue {
  tableLoading = false;
  headers = [
    { text: "Descrição", value: "descricao" },
    { text: "Link", value: "link" },
    { text: "Ações", value: "acoes", sortable: false },
  ];

  items = [
    {
      id: 1,
      descricao: "Teste 1",
      link: "www.teste1.com.br",
    },
    {
      id: 2,
      descricao: "Teste 2",
      link: "www.teste2.com.br",
    },
    {
      id: 3,
      descricao: "Teste 3",
      link: "www.teste3.com.br",
    },
  ];

  editarItem(item: string) {
    console.log("editar");
  }

  excluirItem(item: string) {}

  created() {}
}
</script>

<style scoped>
::v-deep .v-data-table > .v-data-table__wrapper > table > thead > tr > th {
  font-size: 15px !important;
}
</style>
