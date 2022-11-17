<template>
  <div>
    <h1 class="underline">공지사항 목록</h1>
    <div style="text-align: right">
      <button @click="moveWrite">글 작성</button>
    </div>
    <table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회 수</th>
				</tr>
			</thead>
      <tbody>
        <board-list-item v-for="article in articles" :key="article.articleNo" :article="article"></board-list-item>
      </tbody>
		</table>
  </div>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/BoardListItem.vue";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
    };
  },
  components: {
    BoardListItem
  },
  created() {
    http.get(`/board/list`)
      .then(({ data }) => (this.articles = data));
  },
  methods: {
    moveWrite() {
      console.log("글쓰러 가자!!!");
      this.$router.push({ name: "boardwrite" });
    },
  },
}
</script>

<style>

</style>