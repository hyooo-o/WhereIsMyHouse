<template>
  <div>
    <hr>
    <h2>답변</h2>
    {{answer.content}}

    <div style="padding-top: 15px">
      <router-link :to="{name: 'answermodify', params: {questionId: answer.questionId}}" class="btn">수정</router-link>
      <router-link :to="{name: 'answerdelete', params: {questionId: answer.questionId}}" class="btn">삭제</router-link>
    </div>
    <router-view></router-view>

  </div>
</template>

<script>
// import QnaAnswerModifyVue from "./QnaAnswerModify.vue";

export default {
  props: {
    answer: Object,
  },
  components: {
    // QnaAnswerModifyVue,
  }
}
</script>

<style>

</style>