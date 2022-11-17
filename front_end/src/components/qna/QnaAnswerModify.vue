<template>
  <div>
    <hr>
    <div>답변</div>
    <input type="text" class="form-control" id="content" name="content" v-model="content" ref="content">
    <button @click="checkValue">수정 완료</button>
    <button @click="reset">수정 취소</button>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  data(){
    return {
      answer: Object,
    };
  },
  method: {
    checkValue() {
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";
      !this.answer.content && ((msg = "내용을 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.modifyAnswer();
    },
    modifyAnswer() {
      console.log("글 수정하러");
      // 비동기
      // TODO : 글번호에 해당하는 글정보 수정.
      http.put("/qna/modify/answer", this.answer).then(({ data }) => {
        let msg = "등록 처리 중 문제 발생";
        if (data === "SUCCESS") msg = "수정 완료";
        alert(msg);
        this.moveList();
      });
    },
    moveList() {
      console.log("댓글 확인하러");
      this.$router.push({ name: "qnaview" , params: {questionId: this.$route.params.questionId }});
    },
    reset() {
      this.$router.push({ name: "qnaview", params: { questionId: this.$route.params.questionId }});
    }
  }
}
</script>

<style>

</style>