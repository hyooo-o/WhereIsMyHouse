<template>
  <v-container>
    <p>실거래가</p>
    <v-row>
      <v-col>
        <v-sheet rounded="lg">
          <v-simple-table>
            <thead>
              <tr>
                <th class="text-center">계약일</th>
                <th class="text-center">거래가격</th>
                <th class="text-center">타입</th>
                <th class="text-center">층수</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="aptDealItem in aptDeal" :key="aptDealItem.aptCode">
                <td>{{ aptDealItem.dealYear }}</td>
                <td>{{ aptDealItem.dealYear }}</td>
                <td>{{ aptDealItem.dealYear }}</td>
                <td>{{ aptDealItem.dealYear }}</td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-sheet>
      </v-col>
    </v-row>
    <v-row>
      <v-btn>더보기</v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore"

export default {
  data () {
    return {
      dealList: []
    }
  },
  computed: {
    ...mapState(aptStore, ["aptDeal"]),
  },
  methods: {
        ...mapActions(aptStore, ["getAptDeal"]),
        print(){
            console.log(this.aptDeal);
        }
    },
    created() {
        this.getAptDeal();
    }
}
</script>

<style>
  .tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; text-align: center;}
	.tbAdd td.txt_cont{height:300px; vertical-align:top;}
</style>