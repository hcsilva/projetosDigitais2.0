<template>
  <v-container>
    <v-row class="pl-5">
      <v-col>
        <v-btn color="#F0A500" dark @click="adicionar()">Adicionar</v-btn>
      </v-col>
    </v-row>
    <v-row justify="center" class="ma-2">
      <v-col cols="12">
        <DataTable :items="links" :headers="headers" :loadingTabela="true"
      /></v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ModalComponent from "@/components/ComponentesGerais/Modal/ModalComponent.vue";
import LinkWebComponent from "@/components/LinksWeb/LinkWebComponent.vue";
import DataTable from "../ComponentesGerais/DataTable/DataTable.vue";
import LinkWebService from "./LinkWebService";

@Component({
  components: {
    ModalComponent,
    LinkWebComponent,
    DataTable,
  },
})
export default class LinkWeb extends Vue {
  tableLoading = false;
  links = new Array<LinkWeb>();
  loadingDataTable: boolean = false;

  mounted() {
    LinkWebService.buscarTodosLinks()
      .then((response: any) => {
        this.links = response.content;
      })
      .catch((error) => {
        console.log(error);
      });

    this.loadingDataTable = false;
  }

  headers = [
    { text: "Descrição", value: "descricao", width: "40%" },
    { text: "Link", value: "url" },
    { text: "Ações", value: "acoes", width: "10%" },
  ];

  editarItem(item: any) {
    const id = item.id;
    this.$router.push(`/link/${id}/editar`);
  }

  excluirItem(item: string) {}

  adicionar() {
    this.$router.push({ name: "novoLink" });
  }
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
