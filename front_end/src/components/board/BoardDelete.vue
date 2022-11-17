<template>
  <div class="regist">
    <h1>글 삭제</h1>
    <div>삭제중....</div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardDelete",
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글을 삭제.
    http.delete(`/board/delete/${this.$route.params.articleNo}`).then(({ data }) => {
      let msg = "등록 처리 중 문제 발생";
      if (data === "SUCCESS") msg = "삭제 성공!!";
      alert(msg);
      this.$router.push({ name: "boardlist" });
    });
  },
};
</script>

<style></style>
