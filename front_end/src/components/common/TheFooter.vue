<template>
  <v-footer color="grey lighten-2" padless style="margin-top: 30px ;">
    <v-row justify="center">
      <v-card-text style="padding-bottom: 0;">
        <v-btn v-for="link in links" :key="link" color="black" text rounded class="my-2">
          {{ link }}
        </v-btn>
      </v-card-text>
      <v-card-text style="padding: 0;">
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 grey--text" icon>
          <v-icon size="27px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>
      <v-card-text style="padding: 0;" class="grey lighten-2 py-4 text-center grey--text">
        Copyright © jeonghyo
      </v-card-text>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    links: [
      '회사소개',
      '이용약관',
      '개인정보처리방침',
      'Services',
    ],
    icons: [
      'mdi-facebook',
      'mdi-twitter',
      'mdi-instagram',
    ],
  }),
};
</script>

<style>
footer {
  position: absolute;
  bottom: 0;
}
</style>