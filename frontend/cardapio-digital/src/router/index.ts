import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import LoginComponent from "../components/Login/LoginComponent.vue";
import Empresa from "../components/Empresa/Empresa.vue";
import Cardapio from "../components/Cardapio/Cardapio.vue";
import AdminHome from "../components/Admin/AdminHome.vue";
import UsuarioCadastro from "../components/Usuario/UsuarioCadastro.vue";
import Menu from "../components/Menu/Menu.vue";
import MenuItem from "../components/Menu/MenuItem.vue";
import ApresentacaoComponent from "../components/Apresentacao/ApresentacaoComponent.vue";
import LinkWebList from "@/components/LinksWeb/LinkWebList.vue";
import LinkWebComponent from "@/components/LinksWeb/LinkWebComponent.vue";
import LayoutBaseAdminComponent from "@/components/Layout/LayoutBaseAdminComponent.vue"

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "inicio",
    component: ApresentacaoComponent,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginComponent,
  },
  {
    path: "/home",
    name: "home",
    component: LayoutBaseAdminComponent,
    children: [
      {
        path: "cadastrarNovoUsuario",
        name: "cadastrar Usuário",
        component: UsuarioCadastro,
      },
      {
        path: "menu",
        name: "menu",
        component: Menu,
      },
      {
        path: "detail",
        name: "menuItem",
        component: MenuItem,
      },
      {
        path: "admin",
        name: "adminHome",
        component: AdminHome,
        meta: {
          isAuthenticated: true,
        },
      },
      {
        path: "meus-dados",
        name: "empresa",
        component: Empresa,
        meta: {
          isAuthenticated: true,
        },
      },
      {
        path: "cardapio",
        name: "cardapio",
        component: Cardapio,
        meta: {
          isAuthenticated: true,
        },
      },
      {
        path: "link",
        name: "link",
        component: LinkWebList,
        meta: {
          isAuthenticated: true,
        },
      },
      {
        path: "link/:id/editar",
        name: "editarLink",
        component: LinkWebComponent,
        meta: {
          isAuthenticated: true,
        },
      },
      {
        path: "link/novo",
        name: "novoLink",
        component: LinkWebComponent,
        meta: {
          isAuthenticated: true,
        },
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  let routerAuthCheck = localStorage.getItem("authToken");
  if (to.matched.some((record) => record.meta.isAuthenticated)) {
    if (routerAuthCheck) {
      next();
    } else {
      next({ name: "Login" });
    }
  } else {
    next();
  }
});

export default router;
