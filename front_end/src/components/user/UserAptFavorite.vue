<template>
  <v-main>
    <v-container>
      <v-row style="padding-top: 5%;">
        <v-col>
          <h1 class="underline">관심 지역 목록</h1>
        </v-col>
        <v-row>
          <v-col
            v-for="n in 3"
            :key="n"
            cols="4"
          >
            <v-card height="200"></v-card>
          </v-col>
        </v-row>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {

}
</script>

<style>

</style>