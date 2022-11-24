<template>
  <v-container style="padding-bottom: 3%;">
    <mark><strong>실거래가</strong></mark>
    <v-row>
      <v-col>
        <v-sheet>
          <v-data-table hide-default-footer :headers="headers" :items="totalDealList" :items-per-page=this.endPage></v-data-table>
        </v-sheet>
      </v-col>
    </v-row>
    <v-row v-if="this.dataFull === false && totalDealList.length > 5">
      <v-btn @click="addList">더보기 ( {{ this.endPage }} / {{totalDealList.length}} )<v-icon>mdi-chevron-down</v-icon></v-btn>
    </v-row>
    <v-row v-if="this.dataFull === true">
      <v-btn @click="closeList" color="#E0E0E0">접기 ( {{ totalDealList.length }} / {{totalDealList.length}} )<v-icon>mdi-chevron-up</v-icon></v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore"

export default {
  data () {
    return {
      headers: [
        { text: '계약일', value: 'dealDate', align: 'center', sortable: false},
        { text: '거래가격 (만원)', value: 'dealAmount', align: 'center' },
        { text: '타입', value: 'area', align: 'center' },
        { text: '층수', value: 'floor', align: 'center' },
      ],
      endPage: 5,
      itemsPerPage: 5,
      dataFull: false,
      subDealList: [
        {
          dealDate: null,
          dealAmount: null,
          area: null,
          floor: null,
        }
      ],
    }
  },
  computed: {
    ...mapState(aptStore, ["aptDeal", "apt"]),
    totalDealList() {
      let totalDealList = this.aptDeal;

      for (let i = 0; i < totalDealList.length; i++) {
        totalDealList[i].dealDate = this.aptDeal[i].dealYear + '.' + this.aptDeal[i].dealMonth + "." + this.aptDeal[i].dealDay;
      }

      return totalDealList;
    },
  },
  methods: {
    ...mapActions(aptStore, ["setAptDeal"]),
    print() {
      console.log(this.aptDeal);
    },
    addList() {
      this.endPage += 5;
      this.subDealList = this.totalDealList;

      if (this.endPage < this.subDealList.length) {
        let list = [];
        
        for (let i = 0; i < this.endPage; i++){
          list.push(this.subDealList[i]);
        }
        this.subDealList = list;
      } else {
        this.dataFull = true;
      }
    },
    closeList() {
      this.endPage = 5;
      this.subDealList = this.totalDealList;
      this.dataFull = false;
    },
  },
  created() {
    this.getAptDeal(this.apt.aptCode);
    
  }
}
</script>

<style>
  .tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; text-align: center;}
	.tbAdd td.txt_cont{height:300px; vertical-align:top;}
</style>