<template>
  <div class="col-md-12">
    <div id="jumbotron"
      class="mt-4 p-5 jumbotron text-white jumbotron-image shadow"
      style="background-image: url(https://thumb.photo-ac.com/82/82aa26eb7c35a8a4491661920a692ece_t.jpeg)">
      <h2 class="mb-4" style="text-align: center">집을 찾고 계신가요?</h2>
      <hr />
      <p class="mb-4" style="text-align: center">우리를 위한 집</p>
  <div class="content-search">
    <div class="container">
      <form id="search-form" action="apt/list" method="post" >
        <div class="row col-md-12 justify-content-center mb-1">
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light"
              name="gubun" id="gubun">
              <option value="">구분선택</option>
              <option value="1">동별</option>
              <option value="2">아파트별</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light"
              name="sido" id="sido">
              <option value="">시/도</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light"
              name="gugun" id="gugun">
              <option value="">구/군</option>
            </select>
          </div>
          <div class="form-group col-md-1" id="dongDiv">
            <select class="form-select bg-secondary text-light"
              name="dong" id="dong">
              <option value="">동</option>
            </select>
          </div>
          <div class="form-group col-md-2" style="display: none"
            id="aptNameDiv">
            <input class="form-control" id="aptName" name="aptName"
              type="text" placeholder="아파트명 입력" />
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light"
              name="year" id="year"></select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light"
              name="month" id="month">
              <option value="">매매월</option>
            </select>
          </div>
          <input type="hidden" id="gugugun" name="gugugun">
          <input type="hidden" id="dododong" name="dododong">
          <div style="display: flex" class="form-group col-md-1">
            <button type="button" class="btn btn-outline-secondary me-4"
              id="list-btn" @click="searchApt">
              <v-icon>mdi-home-search</v-icon>
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
  </div>
  </div>
</template>

<script>
export default {
  methods: {
    searchApt() {
      console.log("아파트 검색");
      this.$router.push({ name: "map" });
    }
  }
};
</script>

<style>

</style>