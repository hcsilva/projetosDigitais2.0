<template>
  <v-data-table
    disable-sort
    :headers="headers"
    :items="items"
    noDataText="Nenhum registro encontrado"
    loading="loadingTabela"
    class="mt-2 pa-3 dataTableBorder"
    style="font-size: 10px"
    :footer-props="{
      itemsPerPageOptions: [10, 50, 100],
      itemsPerPageText: 'Linhas por Página',
    }"
  >
    <template v-slot:[`item.situation`]="{ item }">
      <v-chip
        :color="item.situation === 'Ativo' ? 'green' : 'gray'"
        label
        style="font-weight: 500"
        text-color="white"
        class="chip"
      >
        {{ item.situation }}
      </v-chip>
    </template>

    <template v-slot:[`item.id`]="{ item }">
      <v-menu bottom left offset-y v-model="item.hover">
        <template v-slot:activator="{ on, attrs }">
          <v-hover v-slot="{ hover }">
            <v-btn
              dark
              block
              icon
              plain
              v-bind="attrs"
              v-on="on"
              :class="{
                active: hover || item.hover ? false : true,
              }"
            >
              <v-icon color="black">mdi-dots-vertical</v-icon>
            </v-btn>
          </v-hover>
        </template>

        <v-list>
          <v-list-item link dense class="dot-menu">
            <v-icon class="pr-1" color="black" small> mdi-eye </v-icon>

            <v-btn text @click="visualizar"> Visualizar </v-btn>
          </v-list-item>

          <v-list-item link dense class="dot-menu">
            <v-icon class="pr-1" color="black" small> mdi-pencil </v-icon>

            <v-btn text @click="editar"> Editar </v-btn>
          </v-list-item>

          <v-list-item link dense class="dot-menu">
            <v-icon class="pr-1" color="black" small> mdi-delete </v-icon>

            <v-btn text @click="deletar"> Excluir </v-btn>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
  </v-data-table>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class DataTable extends Vue {
  @Prop({ required: true }) items!: any[];
  @Prop({ required: true }) headers!: any[];
  @Prop({ required: true }) loadingTabela!: boolean;

  visualizar() {
    this.$emit("visualizar");
  }

  editar() {
    this.$emit("editar");
  }

  deletar() {
    this.$emit("deletar");
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
