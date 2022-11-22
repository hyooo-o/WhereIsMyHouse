<template>
  <v-sheet height="400" class="overflow-hidden" style="position: absolute; top: 0px; width:100%; height: 100%">
    <v-container class="fill-height">
      <v-row text-align="center" justify="center">
      </v-row>
    </v-container>

    <v-navigation-drawer v-model="drawer" absolute style="width: 500px">
      <v-list-item>
        <v-list-item-content>
          <v-row justify="center">
            <v-col></v-col>
            <v-col style="text-align: center;">
              <h3>{{ apt.apartmentName }}</h3>
            </v-col>
            <v-col >
              <v-btn @click="turnOffDrawer" absolute style="top:0px; right:0px">X</v-btn>
            </v-col>
          </v-row>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-row>
        <v-col>사진 자리</v-col>
      </v-row>

      <p>{{ apt.buildYear }}</p>
      <p>{{ apt.roadName }}</p>
      <p>{{ apt.dong }}</p>
      <p>{{ apt.jibun }}</p>
      <price-chart></price-chart>
      
    </v-navigation-drawer>
  </v-sheet>
</template>

<script>
import PriceChart from "@/components/chart/PriceChart.vue"
import { mapActions, mapState } from "vuex"

const aptStore = "aptStore"

// 사라질 때 무조건 drawer가 false로 바뀌면 가능!

export default {
  data () {
    return {
      items: [
        { title: '이름', icon: 'mdi-view-dashboard' },
        { title: '년도', icon: 'mdi-forum' },
        { title: '길', icon: 'mdi-view-dashboard' },
        { title: '동', icon: 'mdi-forum' },
        { title: '지번', icon: 'mdi-view-dashboard' },
      ],
    }
  },
  computed: {
    ...mapState(aptStore, ["apt", "drawer"]),
  },
  methods: {
    ...mapActions(aptStore, ["turnOffDrawer"]),
  },
  components: {
    PriceChart
  }
}
</script>

<style>

</style>