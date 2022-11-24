import { getChartData, getAptList, getAptImg, getAptDeal, getAptSearch, getDongSearch } from "@/api/apt";

const aptStore = {
  namespaced: true,
  state: {
    aptList: [],
    apt: null,
    aptImg: null,
    chartDatas: null,
    drawer: false,
    aptDeal: [],
    dongSearch: [],
    aptSearch: [],
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
    checkAptDeal: function (state) {
      return state.aptDeal;
    },
    checkDongSearch: function (state) {
      return state.dongSearch;
    },
    checkAptSearch: function (state) {
      return state.aptSearch;
    },
  },
  mutations: {
    SET_APT_LIST: (state, aptList) => {
      state.aptList = aptList;
    },
    SET_APT: (state, apt) => {
      state.apt = apt;
    },
    SET_CHART_DATA: (state, chartDatas) => {
      state.chartDatas = chartDatas;
    },
    SET_DRAWER: (state, isDrawer) => {
      state.drawer = isDrawer;
    },
    SET_APT_DEAL: (state, aptDeal) => {
      state.aptDeal = aptDeal;
    },
    SET_APT_IMG: (state, aptImg) => {
      state.aptImg = aptImg
    },
    SET_DONG_SEARCH: (state, dongSearch) => {
      state.dongSearch = dongSearch
    },
    SET_APT_SEARCH: (state, aptSearch) => {
      state.aptSearch = aptSearch
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
    async setChartData({ commit }, aptCode) {
      await getChartData(
        aptCode,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_CHART_DATA", data.chartData);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("아파트 차트 정보 가져오지 못함!!!!");
          }
        },
        async (error) => {
          console.log("아파트 차트 정보 가져오는 중 에러 발생!!! ", error.response.status);
        }
      )
    },
    async setAptDeal({ commit }, aptCode) {
      await getAptDeal(
        aptCode,
        ({ data }) => {
          console.log(data.message);
          if (data.message === "success") {
            commit("SET_APT_DEAL", data.dealList);
            console.log("getAptDeal data >> ", data);
        } else {
            console.log("아파트 거래내역을 가져오지 못함!!!!");
          }
        },
        async (error) => {
          console.log("아파트 거래내역 가져오는 중 에러 발생!!! ", error.response.status);
        }
      );
    },
    setApt({ commit }, apt) {
      commit("SET_APT", apt);
    },
    async setAptImg({commit, getters}) {
      let aptcode = getters.checkApt.aptCode;
      let kw = getters.checkApt.apartmentName;
      
      await getAptImg(
        aptcode,
        kw,
        ({ data }) => {
            commit("SET_APT_IMG", data.image);
        },
        async (error) => {
          console.log("아파트 이미지 가져오는 중 에러 발생!!! ", error.response.status);
        }
      )
    },
    turnOnDrawer({ commit }) {
      commit("SET_DRAWER", true);
    },
    turnOffDrawer({ commit }) {
      commit("SET_DRAWER", false);
    },
    async setDongSearch({ commit }, dong) {
      await getDongSearch(
        dong,
        ({ data }) => {
          console.log(data.message);
          if (data.message === "success") {
            commit("SET_APT_SEARCH", data.dongSearch);
            console.log("getAptSearch data >> ", data);
        } else {
            console.log("동 검색내역을 가져오지 못함!!!!");
          }
        },
        async (error) => {
          console.log("동 검색내역 가져오는 중 에러 발생!!! ", error.response.status);
        }
      );
    },
    async setAptSearch({ commit }, apartmentName) {
      await getAptSearch(
        apartmentName,
        ({ data }) => {
          console.log(data.message);
          if (data.message === "success") {
            commit("SET_APT_SEARCH", data.aptSearch);
            console.log("getAptSearch data >> ", data);
        } else {
            console.log("아파트 검색내역을 가져오지 못함!!!!");
          }
        },
        async (error) => {
          console.log("아파트 검색내역 가져오는 중 에러 발생!!! ", error.response.status);
        }
      );
    },
  },
};

export default aptStore;
