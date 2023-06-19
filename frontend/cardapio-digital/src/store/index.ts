import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    logged: false,
    authToken: null,
    idTenant: null,
    name: null,
  },
  getters: {
    getIdTenant: (state) => {
      if (!state.idTenant) {
        state.idTenant = window.localStorage.idTenant;
      }
      return state.idTenant;
    },

    getUserName: (state) => {
      if (!state.name) {
        state.name = window.localStorage.name;
      }
      return state.name;
    },
  },

  mutations: {
    setAuthToken(state, payload) {
      window.localStorage.authToken = payload.token;
      window.localStorage.idTenant = payload.empresaId;
      window.localStorage.name = payload.name;

      state.authToken = payload.token;
      state.idTenant = payload.empresaId;
      state.logged = Boolean(state.authToken);
      state.name = payload.name;
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
