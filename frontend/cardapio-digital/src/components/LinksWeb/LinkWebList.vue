<template>
  <v-container>
    <v-row justify="center" class="ma-2">
      <v-col cols="12">
        <DataTable
          :items="items"
          :headers="headers"
          :loadingTabela="false"
          @editar="editarItem"
      /></v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ModalComponent from "@/components/ComponentesGerais/Modal/ModalComponent.vue";
import LinkWebComponent from "@/components/LinksWeb/LinkWebComponent.vue";
import DataTable from "../ComponentesGerais/DataTable/DataTable.vue";

@Component({
  components: {
    ModalComponent,
    LinkWebComponent,
    DataTable,
  },
})
export default class LinkWeb extends Vue {
  tableLoading = false;
  headers = [
    { text: "Descrição", value: "descricao", width: "40%" },
    { text: "Link", value: "link" },
    { text: "Ações", value: "acoes", width: "10%" },
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

  editarItem(item: any) {
    const id = item.id;
    this.$router.push(`/link/${id}/editar`);
    console.log(item);
  }

  excluirItem(item: string) {}

  created() {}
}
</script>

<style scoped>
::v-deep .v-data-table > .v-data-table__wrapper > table > thead > tr > th {
  font-size: 15px !important;
}

.container {
  max-width: 1300px;
}
</style>
