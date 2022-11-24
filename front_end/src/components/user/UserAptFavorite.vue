<template>
  <v-main>
    <v-container>
      <v-row style="padding-top: 5%;">
        <v-col>
          <h1 class="underline">관심 지역 목록</h1>
        </v-col>

        <v-row v-for="(favoriteSubList,index) in this.favoriteList" :key="index">
          <v-col col-md-3 v-for="(favorite, subIndex) in favoriteSubList" :key="subIndex">
            <v-card :loading="loading" class="mx-auto my-12" max-width="250" @click="goToMap(favorite.aptCode)" :style="isNull(favorite) ? 'display:none;' : ''">

              <v-card-title v-if="favorite !== null">{{favorite.apartmentName}}</v-card-title>
              <v-divider class="mx-4"></v-divider>
              <v-card-text>

                <div class="my-4 text-subtitle-1">
                  <div v-if="favorite !== null">{{favorite.minPrice}} ~ {{favorite.maxPrice}} (만원)</div>
                </div>

              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions, mapState } from "vuex";

const userStore = "userStore";
const aptStore = "aptStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo", "favorite"]),
    favoriteList() {
      let favoriteList = [];

      for(let i = 0; i < this.favorite.length; i += 4) {
        let temp = [];

        for(let j = i; j < i + 4; j++) {
          if(j >= this.favorite.length) {
            temp.push(null);
          }else{
            temp.push(this.favorite[j]);
          }
        }

        favoriteList.push(temp);
      }

      return favoriteList;
    }
  },
  methods: {
    ...mapActions(userStore, ["setFavoriteInfo", "setFavorite"]),
    ...mapActions(aptStore, ["setChartData", "getApt", "turnOnDrawer", "setAptDeal"]),
    async goToMap(aptCode) {
      await this.getApt(aptCode);
      // 유저의 데이터와 아파트의 데이터를 비교해서 userStore의 favorite 상태 변경
      // vueInstance.setAptImg();
      await this.setChartData(aptCode);
      await this.setAptDeal(aptCode);
      await this.setFavorite(aptCode);
      this.turnOnDrawer();

      this.$router.push("/map/list");
    },
    isNull(favorite) {
      if(favorite == null) {
        return true;
      } else {
        return false;
      }
    },
  },
  created() {
    this.setFavoriteInfo(this.userInfo.userId);
  },
}
</script>

<style>

</style>