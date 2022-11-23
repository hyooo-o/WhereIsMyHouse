<template>
  <div class="col-md-12">
    <div id="jumbotron"
      class="mt-4 p-5 jumbotron text-white jumbotron-image shadow"
      style="background-image: url(https://thumb.photo-ac.com/82/82aa26eb7c35a8a4491661920a692ece_t.jpeg)">
      <h2 class="mb-4" style="text-align: center">집을 찾고 계신가요?</h2>
      <hr />
      <p class="mb-4" style="text-align: center">찾고 있는 아파트를 검색해 보세요.</p>

        <v-container>
          <div class="row col-md-12 justify-content-center mb-1">
            <div class="form-group col-md-6">
              <v-autocomplete
                v-model="select"
                :loading="loading"
                :items="items"
                :search-input.sync="search"
                append-item
                cache-items
                class="mx-4"
                flat
                clearable
                hide-no-data
                hide-details
                label="동 / 아파트 검색"
                solo></v-autocomplete>
            </div>
            <div style="display: flex" class="col-md-1">
              <button type="button" class="btn btn-outline-secondary me-4"
                id="list-btn" @click="searchApt">
                <v-icon>mdi-home-search</v-icon>
              </button>
            </div>
          </div>
        </v-container>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: false,
      items: [],
      search: null,
      select: null,
      states: [],
    }
  },
  watch: {
    search (val) {
      val && val !== this.select && this.querySelections(val)
    },
  },
  methods: {
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
  },
};
</script>

<style>

</style>