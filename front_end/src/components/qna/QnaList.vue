<template>
  <v-container>
    <v-row style="padding-top: 5%;">
      <v-col>
        <h1 class="underline">Q & A</h1>
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
                </tr>
              </thead>
              <tbody>
                <qna-list-item v-for="article in articles" :key="article.questionId" :article="article">
                </qna-list-item>
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