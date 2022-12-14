import jwtDecode from "jwt-decode";
import router from "@/router";
import { modify, login, findById, tokenRegeneration, logout, deleteUser } from "@/api/user";
import { getFavorite, addFavorite, deleteFavorite, getFavoriteInfo } from "@/api/favorite"
//choijiseong babo
const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
    isFavorite: false,
    favorite: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
    checkIsLogin: function(state) {
      return state.isLogin;
    }
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_FAVORITE: (state, isFavorite) => {
      state.isFavorite = isFavorite;
    },
    SET_FAVORITE: (state, favorite) => {
      state.favorite = favorite;
    }
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      console.log(token);
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      console.log(decodeToken);
      findById(
        decodeToken.userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
            return 1;
          } else {
            console.log("?????? ?????? ??????!!!!");
            return 0;
          }
        },
        async (error) => {
          console.log("getUserInfo() error code [?????? ???????????? ?????? ?????????.] ::: ", error.response.status);
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
          return -1;
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log("?????? ????????? >> ?????? ?????? ?????? : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("????????? ?????? >> ????????? ?????? : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken ?????? ?????? >> ?????? ?????????!!!!
          if (error.response.status === 401) {
            console.log("?????? ??????");
            // ?????? ????????? ??? DB??? ????????? RefreshToken ??????.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("???????????? ?????? ?????? ??????");
                } else {
                  console.log("???????????? ?????? ?????? ??????");
                }
                alert("RefreshToken ?????? ??????!!! ?????? ???????????? ?????????.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
            commit("SET_IS_LOGIN", false);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userModify({ commit }, user) {
      await modify(
        user,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", user);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log("?????? ?????? ??? ??????!!!!", error);
        }
      );
    },
    async userDelete({ commit }, userid) {
      await deleteUser(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            console.log("?????? ?????? ??????!!");
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
            commit("SET_IS_LOGIN", false);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log("?????? ?????? ??? ??????!!!!", error);
        }
      );
    },
    async setFavorite({ getters, commit }, aptCode) {
      let isLogin = getters.checkIsLogin;
  
      if (isLogin) {
        let userInfo = getters.checkUserInfo;

        let favorite = {
          userId: userInfo.userId,
          aptCode,
        }

        await getFavorite(
          favorite,
          ({ data }) => {
            if (data.message === "success") {
              console.log("?????? ??????!!");
              commit("SET_IS_FAVORITE", true);
            } else {
              commit("SET_IS_FAVORITE", false);
              console.log("?????? ?????? ??????!!!!");
            }
          },
          (error) => {
            console.log("?????? ?????? ?????? ??? ?????? ??????!!!!", error);
          }
        )
      }
    },
    async addUserFavorite({ getters, commit }, aptCode) {
      let isLogin = getters.checkIsLogin;
      
      console.log(isLogin);

      if (isLogin) {
        let userInfo = getters.checkUserInfo;

        let favorite = {
          userId: userInfo.userId,
          aptCode,
        }

        await addFavorite(
          favorite,
          ({ data }) => {
            if (data.message === "success") {
              console.log("?????? ?????? ?????? ??????!!");
              commit("SET_IS_FAVORITE", true);
            } else {
              commit("SET_IS_FAVORITE", false);
              console.log("?????? ?????? ?????? ??????!!!!");
            }
          },
          (error) => {
            console.log("?????? ?????? ?????? ??? ?????? ??????!!!!", error);
          }
        )
      }
    },
    async deleteUserFavorite({ getters, commit }, aptCode) {
      let isLogin = getters.checkIsLogin;
      
      if (isLogin) {
        let userInfo = getters.checkUserInfo;

        let favorite = {
          userId: userInfo.userId,
          aptCode,
        }

        await deleteFavorite(
          favorite,
          ({ data }) => {
            if (data.message === "success") {
              console.log("?????? ?????? ?????? ??????!!");
              commit("SET_IS_FAVORITE", false);
            } else {
              commit("SET_IS_FAVORITE", true);
              console.log("?????? ?????? ?????? ??????!!!!");
            }
          },
          (error) => {
            console.log("?????? ?????? ?????? ??? ?????? ??????!!!!", error);
          }
        )
      }
    },
    async setFavoriteInfo ({ commit }, userId) {
      await getFavoriteInfo(
        userId,
        ({ data }) => {
          if (data.message === "success") {
            console.log("?????? ?????? ?????? ??????!!");
            console.log(data.favoriteInfo);
            commit("SET_FAVORITE", data.favoriteInfo);
          } else {
            commit("SET_FAVORITE", null);
            console.log("?????? ?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log("?????? ?????? ?????? ??? ?????? ??????!!!!", error);
        }
      )
    }
  },
};

export default userStore;
