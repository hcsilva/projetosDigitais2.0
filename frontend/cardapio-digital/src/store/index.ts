import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    logged: false,
    authToken: "",
    empresaId: "",
  },
  getters: {},
  mutations: {
    setAuthToken(state, payload) {
      window.localStorage.authToken = payload.token;
      window.localStorage.empresaId = payload.empresaId;
      state.authToken = payload;
      state.logged = Boolean(state.authToken);
    },

    setLogged(state, payload) {
      state.logged = payload;
    },

    clearToken(state) {
      window.localStorage.removeItem("authToken");
      window.localStorage.removeItem("empresaId");
      state.authToken = "";
      state.logged = false;
    },
  },
  actions: {},
  modules: {},
});
