import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    logged: false,
    authToken: null,
    idTenant: null,
  },
  getters: {},

  mutations: {
    setAuthToken(state, payload) {
      window.localStorage.authToken = payload.token;
      window.localStorage.idTenant = payload.empresaId;
      state.authToken = payload.token;
      state.idTenant = payload.empresaId;
      state.logged = Boolean(state.authToken);
    },

    setLogged(state, payload) {
      state.logged = payload;
    },
  },
  actions: {
    clearToken() {
      window.localStorage.clear();
    },
  },
  modules: {},
});
