import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import LoginComponent from "../components/Login/LoginComponent.vue";
import Empresa from "../components/Empresa/Empresa.vue";
import AdminHome from "../components/Admin/AdminHome.vue";
import UsuarioCadastro from "../components/Usuario/UsuarioCadastro.vue";
import Menu from "../components/Menu/Menu.vue";
import MenuItem from "../components/Menu/MenuItem.vue";
import ApresentacaoComponent from "../components/Apresentacao/ApresentacaoComponent.vue";
import LinkWeb from "@/components/LinksWeb/LinkWebComponent.vue";
import LinkWebList from "@/components/LinksWeb/LinkWebList.vue";

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
    path: "/casdastrarNovoUsuario",
    name: "Cadastrar Usuário",
    component: UsuarioCadastro,
  },
  {
    path: "/menu",
    name: "Menu",
    component: Menu,
  },
  {
    path: "/detail",
    name: "MenuItem",
    component: MenuItem,
  },
  {
    path: "/admin",
    name: "AdminHome",
    component: AdminHome,
    meta: {
      isAuthenticated: true,
    },
  },
  {
    path: "/meus-dados",
    name: "Empresa",
    component: Empresa,
    meta: {
      isAuthenticated: true,
    },
  },
  {
    path: "/link",
    name: "Link",
    component: LinkWebList,
    meta: {
      isAuthenticated: true,
    },
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
