<template>
    <div class="container">
      <ul class="list-group list-group-flush mt-4">
          <li class="list-group-item bo"><h2>부동산 뉴스</h2></li>
          <li v-for="news in newsList" :key="news.news_id">
              <a class="list-group-item" :href="news.news_url"
              >{{news.news_title}}</a
              >
          </li>
      </ul>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

const newsStore = "newsStore";

export default {
    computed: {
        ...mapState(newsStore, ["newsList"]),
    },
    methods: {
        ...mapActions(newsStore, ["getAllNews"]),
        print(){
            console.log(this.newsList);
        }
    },
    created() {
        this.getAllNews();
    }
}
</script>

<style>

</style>