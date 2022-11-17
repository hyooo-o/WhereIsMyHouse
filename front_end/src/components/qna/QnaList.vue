<template>
  <div>
    <h1 class="underline">QnA 목록</h1>
    <div style="text-align: right">
      <button @click="moveWrite">글 작성</button>
    </div>
    <table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
      <tbody>
        <qna-list-item v-for="article in articles" :key="article.questionId" :article="article"></qna-list-item>
      </tbody>
		</table>
  </div>
</template>

<script>
import http from "@/api/http";
import QnaListItem from "@/components/qna/QnaListItem.vue";

export default {
  name: "QnaList",
  data() {
    return {
      articles: [],
    };
  },
  components: {
    QnaListItem,
  },
  created() {
    http.get(`/qna/list`)
      .then(({ data }) => (this.articles = data));
  },
  methods: {
    moveWrite() {
      console.log("글쓰러 가자!!!");
      this.$router.push({ name: "qnawrite" });
    },
  },
}
</script>

<style>

</style>