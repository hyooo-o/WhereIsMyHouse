<template>
  <v-container>
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          공지사항 글보기
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <v-card-title>
          <div class="row my-2">
            <h2 class="text-secondary px-5">{{article.articleNo}}.
              {{article.subject}}</h2>
          </div>
        </v-card-title>
        <div class="row" >
            <div style="text-align: right;">
              <div class="fw-bold">
                <v-icon>mdi-account</v-icon> {{article.userId}}
              </div> 
              <br /> 
              <div class="text-secondary fw-light"> {{article.registerTime}}
            <br />
                  조회 : {{article.hit}} </div>
            </div>
          <v-card-text style="padding-top: 10%;">
            <div class="divider mb-3"></div>
            <div class="text-secondary">{{article.content}}</div>
            <div class="divider mt-3 mb-3"></div>
          </v-card-text>
          <div style="padding-top: 50px; text-align: right;">
            <router-link :to="{name: 'boardmodify', params: {articleNo: article.articleNo}}" class="btn" v-show="this.userInfo !== null && this.id === `admin`">수정</router-link>
            <router-link :to="{name: 'boarddelete', params: {articleNo: article.articleNo}}" class="btn" v-show="this.userInfo !== null && this.id === `admin`">삭제</router-link>
            <router-link :to="{name: 'boardlist'}" class="btn">목록</router-link>
          </div>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardView",
  data() {
    return {
      article: Object,
    };
  },
  created() {
    http.get(`/board/view/${this.$route.params.articleNo}`).then(({ data }) => {
      this.article = data;
    })
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.userId;
    }
  }
};

// document.querySelector("#btn-list").addEventListener("click", function () {
//   let form = document.querySelector("#form-param");
//   document.querySelector("#act").value = "list";
//   form.setAttribute("action", "${root}/board");
//     form.submit();
// });

// document.querySelector("#btn-mv-modify").addEventListener("click", function () {
//   let form = document.querySelector("#form-no-param");
  
//   form.setAttribute("action", "${root}/board");
//     form.submit();
// });

// document.querySelector("#btn-delete").addEventListener("click", function () {
//   if(confirm("정말 삭제하시겠습니까?")) {
//     let form = document.querySelector("#form-no-param");
//     document.querySelector("#nact").value = "delete";
//     form.setAttribute("action", "${root}/board");
//     form.submit();
//   }
// });
</script>

<style>

</style>