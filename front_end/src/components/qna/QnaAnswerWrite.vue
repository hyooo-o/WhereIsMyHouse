<template>
  <div style="padding-top: 5%;">
    <textarea rows="5" cols="10" type="text" class="form-control" id="content" name="content"
    v-model="content" placeholder="내용을 입력하세요." v-show="userInfo !== null && userInfo.userId === `admin`">
    </textarea>
    <textarea rows="5" cols="10" type="text" class="form-control" id="content" name="content"
    v-model="content" placeholder="관리자만 답변이 가능합니다." v-show="userInfo === null || userInfo.userId !== `admin`" readonly>
    </textarea>
    <v-row style="padding-top: 5%;" v-show="userInfo !== null && userInfo.userId === `admin`">
      <v-col>
        <v-btn @click="createAnswer">확인</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const userStore = "userStore";

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
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
}
</script>

<style>

</style>