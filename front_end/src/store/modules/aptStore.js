import { getAptList } from "@/api/apt";

const aptStore = {
  namespaced: true,
  state: {
    aptList: null,
  },
  getters: {
    checkAptList: function (state) {
      return state.aptList;
    },
  },
  mutations: {
    SET_APT_LIST: (state, aptList) => {
      state.aptList = aptList;
    },
  },
  actions: {
    async getAptList({ commit }) {
      await getAptList(
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
  },
};

export default aptStore;
