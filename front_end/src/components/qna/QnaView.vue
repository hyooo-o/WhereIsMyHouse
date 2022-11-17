<template>
  <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-3 shadow-sm bg-light text-center">
        <mark class="sky">QnA 글보기</mark>
      </h2>
    </div>
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row my-2">
        <h2 class="text-secondary px-5">{{article.questionId}}.
          {{article.title}}</h2>
      </div>
      <div class="row">
        <div class="col-md-8">
          <div class="clearfix align-content-center">
            <img class="avatar me-2 float-md-start bg-light p-2"
              src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
              <div class="fw-bold">{{article.userId}}</div> 
              <br /> 
          </div>
          <div class="divider mb-3"></div>
          <div class="text-secondary">{{article.content}}</div>
          <div class="divider mt-3 mb-3"></div>
          <div style="padding-top: 15px">
            <router-link :to="{name: 'qnamodify', params: {questionId: article.questionId}}" class="btn">수정</router-link>
            <router-link :to="{name: 'qnadelete', params: {questionId: article.questionId}}" class="btn">삭제</router-link>
            <router-link :to="{name: 'qnalist'}" class="btn">목록</router-link>
          </div>
        </div>
      </div>
    </div>
    <div v-show="answer.length === 0">
      <qna-answer-write></qna-answer-write>
    </div>
    <div v-show="answer.length !== 0">
      <qna-answer-detail :answer="answer"></qna-answer-detail>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import QnaAnswerWrite from "@/components/qna/QnaAnswerWrite.vue";
import QnaAnswerDetail from "@/components/qna/QnaAnswerDetail.vue";

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
};

</script>

<style>

</style>