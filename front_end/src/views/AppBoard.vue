<template>
  <div>
    <router-view></router-view>
    <v-row>
      <div class="text-center">
        <page-link></page-link>
      </div>
    </v-row>
  </div>
</template>

<script>
import PageLink from "@/components/board/PageLink";

export default {
  name: "AppBoard",
  components: {
    PageLink,
  }
};
</script>

<style>

</style>