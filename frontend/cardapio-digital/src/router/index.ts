import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginComponent from '../components/Login/LoginComponent.vue'
import EmpresaComponent from '../components/Empresa/EmpresaComponent.vue'
import index from '../store/index'


Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginComponent
  },
  {
    path: '/empresa',
    name: 'Empresa',
    component: EmpresaComponent,
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
  debugger
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
