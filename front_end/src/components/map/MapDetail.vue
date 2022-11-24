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
            <v-col>
              <v-btn text large color="blue-grey" @click="turnOffDrawer" absolute style="top:0px; right:0px"><v-icon>mdi-close-circle-outline</v-icon></v-btn>
            </v-col>
          </v-row>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-row>
        <v-img height="250"
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-NFKXqpjlSr0NfBylYckVjLLK8AIp6CcRA&usqp=CAU"
              ></v-img>
      </v-row>
      <hr style="height: 15px; color: lightsteelblue;">
      <v-row>
        <table class="tbAdd">
          <tr>
            <th>준공년도</th>
            <td>{{ apt.buildYear }}</td>
          </tr>
          <tr>
            <th>도로명 주소</th>
            <td>{{ apt.roadName }}</td>
          </tr>
          <tr>
            <th>지번 주소</th>
            <td>{{ apt.dong }} {{ apt.jibun }}</td>
          </tr>
        </table>
      </v-row>
      <v-row><hr style="height: 15px; color: lightsteelblue;"></v-row>
      <v-row>
        <deal-list></deal-list>
      </v-row>
      <v-row><hr style="height: 15px; color: lightsteelblue;"></v-row>
      <v-row justify="center">
          <price-chart style="width: 90%; height: 40vh;"></price-chart>
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