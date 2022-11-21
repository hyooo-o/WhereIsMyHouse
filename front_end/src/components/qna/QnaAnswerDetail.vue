<template>
  <div style="padding-top: 5%;">
    <div class="row">
      <div style="text-align: right;">
        <div class="fw-bold">
          <v-icon>mdi-account</v-icon> 관리자
        </div> 
        <br /> 
      </div>
    </div>
    {{answer.content}}
    <div style="padding-top: 30px; text-align: right;" v-show="userInfo !== null && userInfo.userId === `admin`">
      <router-link :to="{name: 'answermodify', params: {questionId: answer.questionId}}" class="btn">수정</router-link>
      <router-link :to="{name: 'answerdelete', params: {questionId: answer.questionId}}" class="btn">삭제</router-link>
    </div>
    <router-view></router-view>

  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  props: {
    answer: Object,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  }
}
</script>

<style>

</style>