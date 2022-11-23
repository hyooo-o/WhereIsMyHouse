<template>
  <v-main>
    <v-container>
      <v-row style="padding-top: 5%;">
        <v-col>
          <h1 class="underline">관심 지역 목록</h1>
        </v-col>
        <v-row>
          <v-col>
            <v-card :loading="loading" class="mx-auto my-12" max-width="374">

              <v-img
                height="250"
                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
              ></v-img>

              <v-card-title>아파트 이름</v-card-title>
              <v-divider class="mx-4"></v-divider>
              <v-card-text>

                <div class="my-4 text-subtitle-1">
                  가격 정보
                </div>

              </v-card-text>

              <v-divider class="mx-4"></v-divider>

              <v-card-title>Tonight's availability</v-card-title>

              <v-card-text>
                <v-chip-group
                  v-model="selection"
                  active-class="deep-purple accent-4 white--text"
                  column
                >
                  <v-chip>5:30PM</v-chip>

                  <v-chip>7:30PM</v-chip>

                  <v-chip>8:00PM</v-chip>

                  <v-chip>9:00PM</v-chip>
                </v-chip-group>
              </v-card-text>

              <v-card-actions>
                <v-btn
                  color="deep-purple lighten-2"
                  text
                  @click="reserve"
                >
                  보러 가기
                </v-btn>
              </v-card-actions>
            </v-card>
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