<template>
  <v-sheet height="400" class="overflow-hidden" style="position: relative;">
    <v-container class="fill-height">
      <v-row text-align="center" justify="center">
      </v-row>
    </v-container>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>아파트 정보</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>
      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-sheet>
</template>

<script>
import { mapState } from "vuex"

const aptStore = "aptStore"

export default {
  data () {
    return {
      items: [
        { title: '단지 정보', icon: 'mdi-view-dashboard' },
        { title: '거래 가격 차트', icon: 'mdi-forum' },
      ],
    }
  },
  computed: {
    ...mapState(aptStore, ["drawer"]),
  },
}
</script>

<style>

</style>