<template>
  <v-container>
    <v-row style="padding-top: 5%;">
      <v-col>
        <h1 class="underline">공지사항</h1>
      </v-col>
      
      <v-row>
        <v-col style="text-align: right">
          <v-btn tile color="secondary" @click="moveWrite">
            <v-icon left> mdi-pencil </v-icon>
            글 작성
          </v-btn>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-sheet min-height="70vh" rounded="lg">
            <v-simple-table>
              <thead>
                <tr>
                  <th class="text-center">번호</th>
                  <th class="text-center">제목</th>
                  <th class="text-center">작성자</th>
                  <th class="text-center">조회 수</th>
                  <th class="text-center">작성일</th>
                </tr>
              </thead>
              <tbody>
                <board-list-item v-for="article in articles" :key="article.articleNo" :article="article">
                </board-list-item>
              </tbody>
            </v-simple-table>
          </v-sheet>
        </v-col>
      </v-row>
    </v-row>
    <v-row>
      <div class="text-center">
        <v-pagination
          :length="3"
        ></v-pagination>
      </div>
    </v-row>
  </v-container>
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
    BoardListItem,
  },
  created() {
    http.get(`/board/list`).then(({ data }) => (this.articles = data));
  },
  methods: {
    moveWrite() {
      console.log("글쓰러 가자!!!");
      this.$router.push({ name: "boardwrite" });
    },
  },
};
</script>

<style>
</style>