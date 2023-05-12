<template>
  <v-app>
    <v-container>
      <a @click="$router.go(-1)">
        <v-subheader> Voltar</v-subheader>
      </a>

      <v-card class="d-flex justify-start mb-6 mt-4" flat tile>
        <v-img
          class="rounded"
          src="https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/15572690-9670-11ed-91f6-d33652dc9064.jpg"
        ></v-img>

        <div class="menu-item-detail ml-5">
          <div class="menu-item-title">
            {{ name }}
          </div>
          <div class="menu-item-title">R$ {{ price }}</div>
          <div class="menu-item-desc mt-4">
            {{ desc }}
          </div>

          <figure>
            <figcaption class="mt-4 pa-2">Entrada</figcaption>
            <ul>
              <li class="my-3">Focaccia com sardela e caponata</li>
              <li class="my-3">Salada do dia</li>
            </ul>
          </figure>

          <figure>
            <figcaption class="mt-4 pa-2">Sobremesa</figcaption>
            <ul>
              <li class="my-3">Abacaxi com raspas de limão</li>
              <li class="my-3">Mini Brownie</li>
              <li class="my-3">Mini Pudim de leite</li>
            </ul>
          </figure>
        </div>
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      name: "Mignon & Baked Potato",
      desc: "Medalhao de filé mignon acompanha baked potato com brisket e molho de cogumelos.",
      price: "89,90",
      img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/15572690-9670-11ed-91f6-d33652dc9064.jpg&il&output=jpg",
    };
  },
};
</script>

<style scoped>
.container {
  max-width: 910px;
}

v-img {
  max-height: "375";
  max-width: "40%";
}

.v-subheader {
  background-color: #ececec;
  font-weight: 500;
  font-size: 20px;
}

.menu-item-detail {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: left;
  min-width: 250px;
}

.menu-item-title {
  font-size: 21px;
  font-weight: 500;
}

.menu-item-desc {
  font-weight: 300;
}

figcaption {
  background-color: #f3f3f3;
  font-size: 16px;
  font-weight: 500;
}

ul {
  list-style-type: none;
}

li {
  margin-left: -16px;
  font-weight: 500;
  border-bottom: 1px solid #f3f3f3;
}
</style>