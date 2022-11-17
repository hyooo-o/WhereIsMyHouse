<template>
  <tr class="text-center">
    <th>{{article.articleNo}}</th>
    <td>
      <router-link :to="`/board/view/${article.articleNo}`">
        {{ article.subject }}
      </router-link>
    </td>
    <td>{{article.userId}}</td>
    <td>{{article.hit}}</td>
    <td>{{article.registerTime}}</td>
  </tr>
</template>

<script>
export default {
  name: 'BoardListItem',
  props: {
    article:  Object,
  },
};
</script>

<style>

</style>