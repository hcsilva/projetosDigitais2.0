import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import LoginComponent from '../components/Login/LoginComponent.vue'
import EmpresaComponent from '../components/Empresa/EmpresaComponent.vue'
import AdminView from '../views/AdminView.vue'
import AdmMeusDadosView from '../views/AdmMeusDadosView.vue'
import UsuarioCadastro from '../components/Usuario/UsuarioCadastro.vue'
import Menu from '../components/Menu/Menu.vue'
import MenuItem from '../components/Menu/MenuItem.vue'
import ApresentacaoComponent from '../components/Apresentacao/ApresentacaoComponent.vue'
import index from '../store'


Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'inicio',
    component: ApresentacaoComponent
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginComponent
  },
  {
    path: '/casdastrarNovoUsuario',
    name: 'Cadastrar Usuário',
    component: UsuarioCadastro
  },
  {
    path: '/menu',
    name: 'Menu',
    component: Menu
  },
  {
    path: '/detail',
    name: 'MenuItem',
    component: MenuItem
  },
  {
    path: '/empresa',
    name: 'Empresa',
    component: EmpresaComponent,
    meta: {
      isAuthenticated: true
    }
  },
  {
    path: '/admin',
    name: 'AdminView',
    component: AdminView,
    meta: {
      isAuthenticated: true
    }
  },
  {
    path: '/meus-dados',
    name: 'Meus Dados',
    component: AdmMeusDadosView,
    meta: {
      isAuthenticated: true
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  let routerAuthCheck = index.state.logged;

  if (to.matched.some(record => record.meta.isAuthenticated)) {
    if(routerAuthCheck){
      next();

    }else{
      router.push('/login');
    }

  } else {
      next();
  }
});


export default router
