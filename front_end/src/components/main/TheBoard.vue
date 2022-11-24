<template>
  <div class="container">
      <ul class="list-group list-group-flush mt-4">
          <li class="list-group-item bo"><h2>공지 사항</h2></li>
          <li v-for="article in articles" :key="article.articleNo">
            <a class="list-group-item" :href="`board/view/${article.articleNo}`"
              >{{article.subject}}</a
              >
            </li>
      </ul>
    </div>
</template>

<script>
import http from "@/api/http";

export default {
  data() {
    return {
      articles: []
    }
  },
  mounted() {
    this.initComponent();
  },
  watch: {
    '$route.query': function(){
      this.initComponent();
    }
  },
  methods: {
    initComponent() {
      http.get('/board/list', {
        params: { limit: 6, offset: 0 }
      })
        .then(({ data }) => {
          this.articles = data;
        })
    }
  }
}
</script>

<style>

</style>