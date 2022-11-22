<template>
  <v-sheet height="400" class="overflow-hidden" style="position: absolute; top: 0px; width:100%; height: 100%">
    <v-container class="fill-height">
      <v-row text-align="center" justify="center">
      </v-row>
    </v-container>

    <v-navigation-drawer v-model="drawer" absolute style="width: 500px">
      <v-list-item>
        <v-list-item-content>
          <v-row>
            <v-col justify=center>
              <v-list-item-title >아파트 정보</v-list-item-title>
            </v-col>
            <v-col justify=end>
              <v-btn @click="turnOffDrawer">X</v-btn>
            </v-col>
          </v-row>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>
      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ apt.apartmentName }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ apt.buildYear }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ apt.roadName }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ apt.dong }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="item in items"
          :key="item.title">  
          <v-list-item-content>
            <v-list-item-title>{{ apt.jibun }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-sheet>
</template>

<script>
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
  }
}
</script>

<style>

</style>