<template>
  <div class="col-md-12">
    <div id="jumbotron"
      class="mt-4 p-5 jumbotron text-white jumbotron-image shadow"
      style="background-image: url(https://cdn.smedaily.co.kr/news/photo/202203/226046_163577_1155.jpg)">
      <h2 class="mb-4" style="text-align: center">집을 찾고 계신가요?</h2>
      <hr />
      <p class="mb-4" style="text-align: center">찾고 있는 아파트를 검색해 보세요.</p>
        <v-container>
          <v-row>
            <v-col cols="9">
              <v-autocomplete
                v-model="haha"
                :items="products"
                :loading="loading"
                append-item
                cache-items
                flat
                clearable
                hide-no-data
                hide-details
                label="동 / 아파트 검색"
                solo
                @focus="focus"
                @focusout="focusout"
                
                >
                
              </v-autocomplete>
            </v-col>
            <v-col style="display: flex;" cols="1">
              <button type="button" class="btn btn-outline-secondary me-4"
                id="list-btn" @click="searchApt">
                <v-icon>mdi-home-search</v-icon>
              </button>
            </v-col>
          </v-row>
        </v-container>
      </div>

      <v-card v-show="view === true" style="width: 700px; background-color: grey;">
        <v-col>
          <v-card-text style="height: 250px; width: 50%;" overflow-hidden>
            <v-virtual-scroll
              :items="dongSearch"
              item-height="40"
            >
            <template v-slot:default="{ item }">
              <v-list-item :key="item">
                <v-list-item-action>
                  <v-icon>mdi-home-city-outline</v-icon>
                </v-list-item-action>
    
                <v-list-item-content>
                  <v-list-item-title>
                    <strong>{{ item.dong }}</strong>
                  </v-list-item-title>
                </v-list-item-content>
    
                <v-list-item-action>
                  <v-icon small>
                    mdi-open-in-new
                  </v-icon>
                </v-list-item-action>
              </v-list-item>
            </template>
          </v-virtual-scroll>
        </v-card-text>
      </v-col>

        <v-col>
          <v-card-text style="height: 250px; width: 50%; background-color: grey;" overflow-hidden>
            <v-virtual-scroll
            :items="aptSearch"
            item-height="40"
            >
              <template v-slot:default="{ item }">
                <v-list-item :key="item">
                  <v-list-item-action>
                    <v-icon>mdi-home-city-outline</v-icon>
                  </v-list-item-action>
      
                  <v-list-item-content>
                    <v-list-item-title>
                      <strong>{{ item.apartmentName }}</strong>
                    </v-list-item-title>
                  </v-list-item-content>
      
                  <v-list-item-action>
                    <v-icon small>
                      mdi-open-in-new
                    </v-icon>
                  </v-list-item-action>
                </v-list-item>
              </template>
            </v-virtual-scroll>
          </v-card-text>
        </v-col>
    </v-card>

      
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore"

export default {
  data () {
    return {
      loading: false,
      states: [],
      view: false,
      haha: {},
      products: [
        'Samson', 'Wichita', 'Combustion', 'Triton',
        'Helios', 'Wimbeldon', 'Brixton', 'Iguana',
        'Xeon', 'Falsy', 'Armagedon', 'Zepellin'],
    }
  },
  watch: {
    search (val) {
      val && val !== this.select && this.querySelections(val)
    },
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
        this.items = this.states.filter(e => {
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
      this.setDongSearch(this.keyword);
      this.setAptSearch(this.keyword);
    }
  },
  computed: {
    ...mapState(aptStore, ["dongSearch", "aptSearch"]),
  },
};
</script>

<style>

</style>