<template>
  <v-container>
    <v-row style="padding-top: 5%;">
      <v-col>
        <h1 class="underline">공지사항 수정</h1>
        </v-col>

        <table class="tbAdd">
          <tr>
            <th>제목</th>
            <td>
              <input class="form-control" type="text" id="subject" v-model="article.subject" ref="subject" />
            </td>
          </tr>
          <tr>
            <th>작성자</th>
            <td ref="userId">{{article.userId}}</td>
          </tr>
          <tr>
						<th>내용</th>
						<td class="txt_cont">
              <textarea class="form-control" type="text" id="content" v-model="article.content" ref="subject" rows="10"/>
            </td>
					</tr>
        </table>
        <div class="form-group row" style="padding-top: 5%;">
          <div>
            <v-btn @click="checkValue">수정 완료</v-btn>
            <v-btn @click="moveList">목록</v-btn>
          </div>
        </div>
      </v-row>
  </v-container>
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

<style>
  .tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; text-align:left;}
	.tbAdd td.txt_cont{height:300px; vertical-align:top;}
</style>
