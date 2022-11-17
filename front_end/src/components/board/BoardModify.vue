<template>
  <div class="regist">
    <h1 class="underline">게시글 수정</h1>
    <div class="regist_form">
      <label for="userid">작성자</label>
      <input type="text" id="userid" v-model="article.userId" ref="userId" readonly/><br />
      <label for="subject">제목</label>
      <input type="text" id="subject" v-model="article.subject" ref="subject" /><br />
      <label for="content">내용</label>
      <br />
      <textarea id="content" v-model="article.content" ref="content" cols="35" rows="5"></textarea><br />
      <button @click="checkValue">수정</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardModify",
  data() {
    return {
      article: Object,
    };
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기.
    http.get(`/board/modify/${this.$route.params.articleNo}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.article.userId && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userId.focus());
      err && !this.article.subject && ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.modifyArticle();
    },
    modifyArticle() {
      console.log("글 수정하러");
      // 비동기
      // TODO : 글번호에 해당하는 글정보 수정.
      http.put("/board/modify", this.article).then(({ data }) => {
        let msg = "등록 처리 중 문제 발생";
        if (data === "SUCCESS") msg = "수정 완료";
        alert(msg);
        this.moveList();
      });
    },

    moveList() {
      console.log("글 목록으로");
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
