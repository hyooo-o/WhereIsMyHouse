<template>
  <div>
    <hr>
    <div>답변</div>
    <input type="text" class="form-control" id="content" name="content" v-model="content" placeholder="내용을 입력하세요.">
    <button @click="createAnswer">확인</button>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  data(){
    return{
      content:''
    }
  },
  methods:{
    createAnswer() {
      let answer = {
        questionId: this.$route.params.questionId,
        content: this.content,
      };

      http.post("/qna/write/answer", answer).then(({ data }) => {
        let msg = "등록 처리 중 문제 발생";
        if (data === "SUCCESS") msg = "등록 성공!!";
        alert(msg);
        this.moveList();
      });
    },

    moveList() {
      console.log("댓글 확인하러");
      console.log(this.$route.params.questionId);
      this.$router.push({ name: "qnaview", params: { questionId: this.$route.params.questionId }});
    },
  },
}
</script>

<style>

</style>