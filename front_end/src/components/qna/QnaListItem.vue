<template>
  <tr class="text-center">
    <th>{{article.questionId}}</th>
    <td>
      <router-link :to="`/qna/view/${article.questionId}`">
        {{ article.title }}
      </router-link>
    </td>
    <td>{{article.userId}}</td>
  </tr>
</template>

<script>
export default {
  name: 'QnaListItem',
  props: {
    article:  Object,
  },
};
</script>

<style>

</style>