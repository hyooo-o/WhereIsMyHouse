import { newsList } from "@/api/news";

const newsStore = {
  namespaced: true,
  state: {
    newsList: []
  },
  getters: {
    checkNews: function (state) {
      return state.newsList;
    },
  },
  mutations: {
    SET_NEWS: (state, newsList) => {
      state.newsList = newsList;
    },
  },
  actions: {
    async getAllNews({ commit }) {
      await newsList(
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_NEWS", data.newsList);
          } else {
            console.log("뉴스 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log("뉴스 가져오는 중 에러 발생 !!", error);
        }
      );
    },
  },
};

export default newsStore;
