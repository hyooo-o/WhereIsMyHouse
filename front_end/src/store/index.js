import Vue from 'vue';
import Vuex from 'vuex';
import http from '@/api/http.js';
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginUser: null,
  },
  getters: {
  },
  mutations: {
    SET_LOGIN_USER(state, loginUser) {
      state.loginUser = loginUser;
    },
    LOG_OUT(state) {
      state.loginUser = null;

    }
  },
  actions: {
    login({ commit }, user) {
      let juser = JSON.stringify(user);
      http.post("/user/login", juser)
        .then(({ data }) => {
        if (data !== "") {
          console.log("로그인되었습니다.");
          commit('SET_LOGIN_USER', data);
        } else {
          console.log("로그인되지 않았습니다.");
        }
      })
    },
    logout({ commit }) {
      commit("LOG_OUT");
    }
  },
  modules: {
  },
  plugins: [
    createPersistedState(),
  ],
})
