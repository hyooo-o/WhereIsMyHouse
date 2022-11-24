<template>
  <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item>
      <v-sheet
        height="100%"
      >
        <v-row
          class="fill-height"
          text-align="center"
          justify="center"
        >
          <div>
            <img src="@/assets/img/apt1.png" style="height: 80%;">
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
    <v-carousel-item>
      <v-sheet
        height="100%"
      >
        <v-row
          class="fill-height"
          text-align="center"
          justify="center"
        >
          <div>
            <img src="@/assets/img/apt2.png" style="height: 80%;">
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
    <v-carousel-item>
      <v-sheet
        height="100%"
      >
        <v-row
          class="fill-height"
          text-align="center"
          justify="center"
        >
          <div>
            <img src="@/assets/img/apt3.png" style="height: 80%;">
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data () {
    return {
    }
  },
}
</script>

<style>

</style>