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
            <v-col cols="9" style="text-align: center;">
              <h4>{{ apt.apartmentName }} &nbsp; <heart-button></heart-button> </h4>
            </v-col>
            <v-col >
              <v-btn @click="turnOffDrawer" absolute style="top:0px; right:0px">X</v-btn>
            </v-col>
          </v-row>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-row>
        <v-col style="height: 200px;">크롤링 해서 사진 붙일 자리</v-col>
      </v-row>

      <v-row>
        <table class="tbAdd">
          <tr>
            <th>준공년도</th>
            <td>{{ apt.buildYear }}</td>
          </tr>
          <tr>
            <th>주소</th>
            <td>{{ apt.roadName }} {{ apt.dong }} {{ apt.jibun }}</td>
          </tr>
          <tr>
            <th>유형</th>
            <td>아파트</td>
          </tr>
          <tr>
            <th>최지성</th>
            <td>공주</td>
          </tr>
          <tr>
            <th>김정효</th>
            <td>공주</td>
          </tr>
        </table>
      </v-row>
      <hr>
      <v-row style="padding-bottom: 10%;">
        <deal-list></deal-list>
      </v-row>
      <v-row justify="center">
          <price-chart style="width: 27vw; height: 40vh;"></price-chart>
      </v-row>
      
    </v-navigation-drawer>
  </v-sheet>
</template>

<script>
import PriceChart from "@/components/chart/PriceChart.vue";
import HeartButton from "@/components/map/HeartButton.vue";
import DealList from "@/components/map/DealList.vue";
import { mapActions, mapState } from "vuex";

const aptStore = "aptStore"

export default {
  data () {
    return {

    }
  },
  computed: {
    ...mapState(aptStore, ["apt", "drawer"]),
  },
  methods: {
    ...mapActions(aptStore, ["turnOffDrawer"]),
  },
  components: {
    PriceChart,
    HeartButton,
    DealList
  }
}
</script>

<style>

</style>