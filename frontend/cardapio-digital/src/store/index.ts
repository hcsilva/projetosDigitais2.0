import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    logged: false,
    authToken: "",
  },
  getters: {
  },
  mutations: {
    setAuthToken(state, payload) {
      debugger
      window.localStorage.authToken = payload;
      state.authToken = payload;
      state.logged = Boolean(state.authToken);
    },

    setLogged(state, payload){
      state.logged = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
