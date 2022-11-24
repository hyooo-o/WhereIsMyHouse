<template>
  <div class="col-md-12">
    <div id="jumbotron"
      class="mt-4 p-5 jumbotron text-white jumbotron-image shadow"
      style="height: 470px; background-image: url(https://i.pinimg.com/736x/35/78/1d/35781da12a4142281d9d0b45fafca461.jpg);
      background-size: 100% 160%">
      <h1 class="mb-4" style="text-align: center; color: grey;">집을 찾고 계신가요?</h1>
      <hr />
      <p class="mb-4" style="text-align: center; color: darkslategrey;">찾고 있는 아파트를 검색해 보세요.</p>
        <v-container>
          <v-row>
            <v-col style="text-align: center;">
              <v-btn large class="ma-2" depressed color="#BCAAA4" @click="searchApt">
                <v-icon>mdi-home-search</v-icon> 집 찾으러 가기
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore"

export default {
  data () {
    return {
      loading: false,
      items: [],
      view: false,
      search: null,
      keyword: null,
    }
  },
  watch: {
    search (val) {
      val && val !== this.keyword && this.querySelections(val)
    },
  },
  computed: {
    ...mapState(aptStore, ["dongSearch", "aptSearch"]),
  },
  methods: {
    ...mapActions(aptStore, ["setDongSearch", "setAptSearch"]),
    searchApt() {
      console.log("아파트 검색");
      this.$router.push({ name: "map" });
    },
    querySelections (v) {
      this.loading = true
      // Simulated ajax query
      setTimeout(() => {
        this.items = this.dongSearch.dong.filter(e => {
          return (e || '').toLowerCase().indexOf((v || '').toLowerCase()) > -1
        })
        this.loading = false
      }, 500)
    },
    focus() {
      this.view = true;
      console.log(this.view);
    },
    focusout() {
      this.view = false;
      console.log(this.view);
    },
    key() {
      console.log(this.keyword);
      this.setDogSearch(this.keyword);
      this.setAptSearch(this.keyword);
    }
  },
  created() {
    console.log(this.keyword);
    this.setDongSearch(this.keyword);
  },
};
</script>

<style>

</style>