<template>
  <v-container>
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">질문</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="row my-2">
          <h2 class="text-secondary px-5">{{article.questionId}}.
            {{article.title}}</h2>
        </div>
        <div class="row">
          <div style="text-align: right;">
            <div class="fw-bold">
              <v-icon>mdi-account</v-icon> {{article.userId}}
            </div> 
            <br /> 
          </div>
          <v-card-text style="padding-top: 10%;">
            <div class="divider mb-3"></div>
            <div class="text-secondary">{{article.content}}</div>
            <div class="divider mt-3 mb-3"></div>
          </v-card-text>
          <div style="padding-top: 50px; text-align: right;  padding-bottom: 10%;">
            <router-link :to="{name: 'qnamodify', params: {questionId: article.questionId}}" 
            class="btn" v-show="this.userInfo !== null && article.userId !== null && (this.id === `admin` || this.id === article.userId)">수정</router-link>
            <router-link :to="{name: 'qnadelete', params: {questionId: article.questionId}}" 
            class="btn" v-show="this.userInfo !== null && article.userId !== null && (this.id === `admin` || this.id === article.userId)">삭제</router-link>
            <router-link :to="{name: 'qnalist'}" class="btn">목록</router-link>
          </div>
        </div>
      </div>
      <hr>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">답변</mark>
        </h2>
      </div>
      <qna-answer-write v-show="answer.length === 0"></qna-answer-write>
      <qna-answer-detail class="col-lg-8 col-md-10 col-sm-12" v-show="answer.length !== 0" :answer="answer"></qna-answer-detail>
    </div>
  </v-container>
</template>

<script>
import http from "@/api/http";
import QnaAnswerWrite from "@/components/qna/QnaAnswerWrite.vue";
import QnaAnswerDetail from "@/components/qna/QnaAnswerDetail.vue";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "QnaView",
  data() {
    return {
      article: Object,
      answer: Object,
    };
  },
  components: {
    QnaAnswerWrite,
    QnaAnswerDetail,
  },
  created() {
    http.get(`/qna/view/${this.$route.params.questionId}`).then(({ data }) => {
      this.article = data;
    });
    http.get(`/qna/answer/${this.$route.params.questionId}`).then(({ data }) => {
      this.answer = data;
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.userId;
    }
  },
};

</script>

<style>

</style>