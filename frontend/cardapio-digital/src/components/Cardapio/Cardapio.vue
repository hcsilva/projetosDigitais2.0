<template>
  <v-container fluid>
    <AlertMessage
      :message="mensagem"
      :show="showMessage"
      :type="alertType"
      @showErrorAlert="showMessage = true"
    />

    <v-card
      class="bg-card mx-auto pa-4 mt-6 rounded-lg"
      elevation="0"
      shaped
      max-width="1300"
    >
      <!-- <v-card-title class="mb-4 title">Manutenção do Cardápio</v-card-title> -->

      <v-card-title class="mb-4 title">
        <v-layout align-center>
          <v-flex xs="9">
            <div class="title">Manutenção do Cardápio</div>
          </v-flex>
          <v-flex xs="3" class="text-right">
            <v-btn outlined>
              <v-icon left> <font-awesome-icon icon="vuejs" /> </v-icon>
              Preview
            </v-btn>

            <v-btn outlined>
              <v-icon left> <font-awesome-icon icon="vuejs" /> </v-icon>
              Cardapios
            </v-btn>

            <v-btn outlined>
              <v-icon left> <font-awesome-icon icon="vuejs" /> </v-icon>
              Categorias
            </v-btn>
          </v-flex>
        </v-layout>
      </v-card-title>

      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-tabs v-model="tab" fixed-tabs background-color="black" dark>
            <v-tab v-for="categoria in categorias" :key="categoria.tab">
              {{ categoria.tab }}
            </v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
            <v-tab-item v-for="categoria in categorias" :key="categoria.tab">
              <v-subheader>{{ categoria.desc }}</v-subheader>

              <v-row no-gutters>
                <v-col cols="16" sm="6">
                  <v-sheet class="ma-1">
                    <v-card class="custom-card" color="#FF5722" height="150">
                      <div class="inner-border">
                        <v-row
                          class="card-content"
                          align="center"
                          justify="center"
                        >
                          <v-icon class="icon" color="white">mdi-plus</v-icon>
                          <span class="text">Adicionar novo item</span>
                        </v-row>
                      </div>
                    </v-card>
                  </v-sheet>
                </v-col>

                <v-col
                  v-for="item in categoria.items"
                  :key="item.name"
                  cols="16"
                  sm="6"
                >
                  <v-sheet class="ma-1">
                    <v-card :color="gray">
                      <div class="d-flex flex-no-wrap justify-space-between">
                        <div class="menu-item-info">
                          <div class="menu-item-title ma-2">
                            {{ item.name }}
                          </div>

                          <div class="menu-item-desc ml-2">
                            {{ item.desc }}
                          </div>

                          <div class="menu-item-footer ma-2">
                            <div class="menu-item-price">
                              R$ {{ item.price }}
                            </div>
                            <div class="menu-item-link">Saiba mais</div>
                          </div>
                        </div>

                        <router-link to="detail">
                          <v-avatar
                            class="ma-2 rounded d-none d-sm-flex"
                            size="145"
                          >
                            <v-img :src="item.img"></v-img>
                          </v-avatar>

                          <v-avatar
                            class="ma-2 rounded d-flex d-sm-none"
                            size="115"
                          >
                            <v-img :src="item.img"></v-img>
                          </v-avatar>
                        </router-link>
                      </div>
                    </v-card>
                  </v-sheet>
                </v-col>
              </v-row>
            </v-tab-item>
          </v-tabs-items>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import vueMask from "v-mask";
import { AlertType } from "../Enums/AlertType";
import AlertMessage from "../ComponentesGerais/alerts/AlertMessage.vue";

Vue.use(vueMask);

@Component({
  components: {
    AlertMessage,
  },
})
export default class Cardapio extends Vue {
  alertType: AlertType | null = null;
  mensagem: string = "";
  showMessage: boolean = false;

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  data() {
    return {
      tab: null,
      categorias: [
        {
          tab: "Cardapio",
          desc: "Almoço executivo para pedidos das 12 às 15h.",
          items: [
            {
              name: "Mignon & Baked Potato",
              desc: "Medalhao de filé mignon acompanha baked potato com brisket e molho de cogumelos.",
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/15572690-9670-11ed-91f6-d33652dc9064.jpg&il&output=jpg",
            },
            {
              name: "Mignon com Gorgonzola",
              desc: "Medalhão de file mignon com purê de batata e molho de gorgonzola",
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/38229f10-9670-11ed-9111-f3acf2d214aa.jpg&il&output=jpg",
            },
            {
              name: "Mignon ao Poivre",
              desc: "Medalhao de filé mignon com o clássico molho francês de pimenta verde fresca e batata frita.",
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/b7b84430-969a-11ed-958b-69052412ea0b.jpg&il&output=jpg",
            },
            {
              name: "Fish and Chips",
              desc: "Peixe e fritas, peixe e fritos, fish and chips ou fish n chips é um prato típico da culinária do Reino Unido. Consiste em peixe frito empanado, acompanhado por batatas fritas.",
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/eda13b10-969a-11ed-9111-f3acf2d214aa.jpg&il&output=jpg",
            },
            {
              name: "Tilápia a Belle Meuniere",
              desc: "File de tilápia com molho de camarões e alcaparras, servido com purê de mandioquinha e brócolis salteados",
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/31860f40-969b-11ed-a350-390a544be641.jpg&il&output=jpg",
            },
            {
              name: "Frango Laqueado com Arroz de Cogumelos",
              desc: 'O termo "laqueado" é utilizado para designar uma técnica específica da gastronomia chinesa, por meio da qual, o filé, é revestido por uma camada brilhante, criando uma "casquinha" na parte externa e uma carne bastante suculenta na parte interna. Aqui no Meet este prato acompanha arroz com cogumelos e legumes salteados.',
              price: "89,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/80141350-969b-11ed-a8e9-b9fb531b6766.jpg&il&output=jpg",
            },
          ],
        },
        {
          tab: "Sobremesa",
          desc: "Sobremesa",
          items: [
            {
              name: "Croux Cream",
              desc: "O choux cream é uma massa típica francesa recheada com mousse de chocolate, sorvete de creme e calda de chocolate.",
              price: "46,90",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/a5632f10-3467-11ed-9389-2dee2727ae06.jpg&il&output=jpg",
            },
            {
              name: "Torta De Chocolate",
              desc: "Base de chocolate crocante e recheio de chocolate cremoso.",
              price: "41,00",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/b4eead10-3467-11ed-bf42-25f0a98a9cf4.jpg&il&output=jpg",
            },
            {
              name: "Brownie De Chocolate e Caramelo Salgado",
              desc: "Acompanha sorvete.",
              price: "41,00",
              img: "https://tagmeimages.azureedge.net/?q=90&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/f573b470-3467-11ed-9389-2dee2727ae06.jpg&il&output=jpg",
            },
            {
              name: "French Toast",
              desc: "Rabanada de brioche com calda de amarena e sorvete de iogurte.",
              price: "39,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/18606bd0-4035-11ed-84b7-b750c89212f4.jpg&w=80&h=80&output=jpg&dpr=2",
            },
            {
              name: "Abacaxi Com Espuma De Limão Siciliano",
              desc: "",
              price: "26,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/MenuItem/76e49b50-3468-11ed-9433-9184dd394338.jpg&w=80&h=80&output=jpg&dpr=2",
            },
          ],
        },
        {
          tab: "Bebidas",
          desc: "Sucos",
          items: [
            {
              name: "Abacaxi",
              desc: "Copo 350ml ou jarra 750ml",
              price: "18,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/Product/965e4200-346e-11ed-b58a-2750f4f9d47f.jpg&w=80&h=80&output=jpg&dpr=2",
            },
            {
              name: "Melancia",
              desc: "Copo 350ml ou jarra 750ml",
              price: "18,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/Product/b36bd970-346e-11ed-9433-9184dd394338.jpg&w=80&h=80&output=jpg&dpr=2",
            },
            {
              name: "Laranja",
              desc: "Copo 350ml ou jarra 750ml",
              price: "18,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/Product/c463dfc0-346e-11ed-afb5-d9315c5ada84.jpg&w=80&h=80&output=jpg&dpr=2",
            },
            {
              name: "Limonada",
              desc: "Copo 350ml ou jarra 750ml",
              price: "18,00",
              img: "https://tagmeimages.azureedge.net/?q=70&url=https://tagmepub.azureedge.net/pubimg/thumbs/Product/d16caa80-346e-11ed-92fd-4f5eebefacc1.jpg&w=80&h=80&output=jpg&dpr=2",
            },
          ],
        },
      ],
    };
  }
}
</script>

<style scoped>
.container {
  max-width: 1300px;
}

.required-field >>> label::before {
  content: "*";
  color: red;
  margin-right: 4px;
}

.v-footer {
  background-color: transparent;
}

.bg-card {
  background-color: #f7f9fa;
}
.title {
  color: #191c1f;
  font-family: Nunito Sans, -apple-system, BlinkMacSystemFont, Segoe UI,
    Helvetica, Arial, sans-serif;
  font-size: 1.5rem;
  font-weight: 400;
  line-height: 1.5;
}
.label-horario {
  font-size: 1rem;
  font-weight: 400;
}
.v-text-field--outlined >>> fieldset {
  border-color: #c9ced4;
  background-color: #ffffff;
  text-align: center;
}
.v-btn {
  text-transform: unset !important;
  font-size: 0.9rem;
  font-weight: 350;
  line-height: 1;
}
.col-sm-6,
.col-12 {
  padding: 0px 8px;
}
.centered-input >>> input {
  text-align: center;
}

.custom-card {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.inner-border {
  position: absolute;
  top: 8px;
  bottom: 8px;
  left: 8px;
  right: 8px;
  border: 1px dashed white;
}

.card-content {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.icon {
  font-size: 24px;
  margin-bottom: 10px;
}

.text {
  font-size: 16px;
  color: white;
}
</style>
