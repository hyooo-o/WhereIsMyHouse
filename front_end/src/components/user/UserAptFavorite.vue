<template>
  <v-main>
    <v-container>
      <v-row style="padding-top: 5%;">
        <v-col>
          <h1 class="underline">관심 지역 목록</h1>
        </v-col>

        <v-row v-for="(favoriteSubList,index) in this.favoriteList" :key="index">
          <v-col col-md-3 v-for="(favorite, subIndex) in favoriteSubList" :key="subIndex">
            <v-card :loading="loading" class="mx-auto my-12" max-width="300" @click="goToMap(favorite.aptCode)" :style="isNull(favorite) ? 'display:none;' : ''">
              <v-img
                height="250"
                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
              ></v-img>

              <v-card-title v-if="favorite !== null">{{favorite.apartmentName}}</v-card-title>
              <v-divider class="mx-4"></v-divider>
              <v-card-text>

                <div class="my-4 text-subtitle-1">
                  <div v-if="favorite !== null">{{favorite.minPrice}} ~ {{favorite.maxPrice}}</div>
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
    ...mapActions(userStore, ["setFavoriteInfo"]),
    ...mapActions(aptStore, ["getApt"]),
    goToMap(aptCode) {
      
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