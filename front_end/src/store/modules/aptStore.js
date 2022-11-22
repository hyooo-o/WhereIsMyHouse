import { getAptList } from "@/api/apt";

const aptStore = {
  namespaced: true,
  state: {
    aptList: [],
    apt: null,
    drawer: false,
  },
  getters: {
    checkAptList: function (state) {
      return state.aptList;
    },
    checkApt: function (state) {
      return state.apt;
    },
    checkDrawer: function (state) {
      return state.drawer;
    },
  },
  mutations: {
    SET_APT_LIST: (state, aptList) => {
      state.aptList = aptList;
    },
    SET_APT: (state, apt) => {
      state.apt = apt;
    },
    TURN_ON_DRAWER: (state) => {
      state.drawer = true;
    },
    TURN_OFF_DRAWER: (state) => {
      state.drawer = false;
    },
  },
  actions: {
    async getAptList({ commit }, loc) {
      await getAptList(
        loc,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_APT_LIST", data.aptList);
            // console.log("3. getUserInfo data >> ", data);
        } else {
            console.log("아파트 정보 가져오지 못함!!!!");
          }
        },
        async (error) => {
          console.log("아파트 정보 가져오는 중 에러 발생!!! ", error.response.status);
        }
      );
    },
    setApt({ commit }, apt) {
      commit("SET_APT", apt);
    },
    turnOnDrawer({ commit }) {
      commit("TURN_ON_DRAWER");
    },
    turnOffDrawer({ commit }) {
      commit("TURN_OFF_DRAWER");
    },
  },
};

export default aptStore;
