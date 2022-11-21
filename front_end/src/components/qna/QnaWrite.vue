<template>
  <v-container>
    <v-row style="padding-top: 5%;">
      <v-col>
        <h1 class="underline">QnA 작성</h1>
      </v-col>

      <v-row>
        <v-col>
          <div class="form-group row">
            <label for="userid" class="col-sm-2 col-form-label">작성자</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userid" v-model="userInfo.userId" ref="userid" readonly/><br />
            </div>
          </div>
          <div class="form-group row">
            <label for="title" class="col-sm-2 col-form-label">제목</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="title" v-model="title" ref="title" /><br />
            </div>
          </div>
          <div class="form-group row">
          <label for="content" class="col-sm-2 col-form-label">내용</label>
            <div class="col-10">
              <textarea class="form-control" id="content" v-model="content" ref="content" cols="35" rows="5"></textarea><br />
            </div>
          </div>
          <div class="form-group row" style="padding-top: 5%;">
            <div>
              <v-btn @click="checkValue">등록</v-btn>
              <v-btn @click="moveList">목록</v-btn>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-row>
  </v-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "QnaWrite",
  data() {
    return {
      userid: null,
      title: null,
      content: null,
    };
  },
  created() {
    this.userid = this.userInfo.userId;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.registArticle();
    },
    registArticle() {
      // 비동기
      // TODO : 글번호에 해당하는 글정보 등록.
      console.log("글작성 하러가자!!!!");
      let article = {
        userId: this.userid,
        title: this.title,
        content: this.content,
      };
      http.post("/qna/write/question", article).then(({ data }) => {
        let msg = "등록 처리 중 문제 발생";
        if (data === "SUCCESS") msg = "등록 성공!!";
        alert(msg);
        this.moveList();
      });
    },

    moveList() {
      console.log("글목록 보러가자!!!");
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style></style>
