<template>
  <v-container>
    <h1>hh</h1>
    <!-- 여기서 아파트를 클릭하면 해당하는 아파트의 디테일이 오른쪽에 뜸 -->
    <apt-detail></apt-detail>
  </v-container>
</template>

<script>
import AptDetail from "./AptDetail.vue";

export default {
  components: {
    AptDetail,
  }
}
</script>

<style>

</style>