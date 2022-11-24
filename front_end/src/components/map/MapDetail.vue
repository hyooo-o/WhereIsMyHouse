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
              <v-btn text large color="blue-grey" @click.prevent="turnOffDrawer" absolute style="top:0px; right:0px"><v-icon>mdi-close-circle-outline</v-icon></v-btn>
            </v-col>
          </v-row>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-row>
        <v-img height="250"
                :src="imgs[Math.floor(Math.random() * imgs.length)]"
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
      imgs: [
        "https://ic.zigbang.com/vp/BigData/7182/c55698b4cce810430fba3bc0179eb85c670e52a6.jpg?w=500&h=375&q=60&a=1",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKF1y55tpkLyz3mOQSDcGFrR9OrBG46-KtHQ&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-NFKXqpjlSr0NfBylYckVjLLK8AIp6CcRA&usqp=CAU",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA5MDFfMjI3%2FMDAxNjYxOTk2NTEyMjgx.VgBk0VPaRJHpFsy_2RRuZhpRUJAW8n_djxmxktNAOpkg.LhV2Xa-j_mus5nHxwkTsjXyyJdQENKq7waZOjLPyIvkg.JPEG.hogangjomyeong%2FKakaoTalk_20220831_174650923_10.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA2MjZfMTYy%2FMDAxNjI0NjczMDI2NzAy.1uHupt8u26QifJpPqqNsHYrIigzoE-r0NE6x5gF0mTEg.RpwR6rihiwDU4cD5ZsFPVBoNpBVljvw7lVhvHky79b8g.JPEG.star5164400%2F20210618_160653.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMTJfMTc4%2FMDAxNjE1NTEwNTIxMTA1.cu52Y2oCwP7H88Jncyc2GKp7zgn-ZfJLeet9iv6rGasg.v822NNur1ijyJvD_GggJLKnsyBSStub7_vJQC6vVhMsg.JPEG.cjh9726%2F20210204_105111.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA0MThfNDUg%2FMDAxNjUwMjc2NDcyNDkw.gs0NX5p7mIwwcwxzjRvb8QVxZcUYhSTRSRwzlwNMivAg.X9bJEtVeImNUDRZn5idb3CkH95chwXc0zySpTI98WSMg.JPEG.cottage66%2F20220418%25A3%25DF114724.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMDhfNjEg%2FMDAxNjE1MTcwNDMzODgz.xi5WwUdskhWps09RWnpieg-H3N4x2SqDJlwOTpTnIFAg.zXxk9jM9fuixgBl9e6mcXhbHV_ZEctcQDuNaGVHkniQg.JPEG.onnara0124%2F0.JPG&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzAzMThfMjk0%2FMDAxNDg5ODExNjQxMTU2.DObz4SE2gE1nrxGiqAvCp3MwjsBqSxSpRMQ_wL8iaxcg.Zfl13W68RwPgZEI7ktbwyWEz-l_87nwEgY2MD72wjdAg.JPEG.hd840%2F78%25B5%25BF.JPG&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA5MjRfMTk3%2FMDAxNjY0MDMwMzU2NTU4.lXNOSuiSeZclS-A7g5oCEmilUfo0h676FAdiaDvJ7TUg.aTHWISCC6GfXRgWAMrYG776TKU2QDKwAlMA5EPRf5qEg.PNG.beenoo78%2Fimage.png&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20151124_149%2Fkhya413_1448351426950yGL4W_JPEG%2F%25C7%25E0%25BA%25B9%25C7%25D1%25BC%25AD%25C7%25D8%25B1%25D7%25B6%25FB%25BA%25ED%25BE%25C6%25C6%25C4%25C6%25AE_00001.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MDVfMTgx%2FMDAxNTk2NTgwNDAwOTcz.oyLSlwqGiEms6y4rHdLwTXTPc2cXtPPh0S4-_KcD-18g.F-k0sxV_uvIC1F9YPN6I9C3frJZQh9LARmAnDO5PBH4g.JPEG.cjh9726%2F20200803_152446.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDAzMDVfMjY4%2FMDAxNTgzMzk4OTAzNjY0.dhI90S2IqJIbH5wV806zxkOCJ6nGKkK878avG-9IgEMg.8Z7JMN0Ax89DL6pog-6-se0NtPWersLX4LLlqEq7k-Mg.JPEG.tomato02624%2F20200222_145747.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20121210_83%2Fmiracle9707_1355118782510vmTUI_PNG%2F%25BD%25D6%25BF%25EB2.png&type=sc960_832",
        "https://search.pstatic.net/common/?src=https%3A%2F%2Flandthumb-phinf.pstatic.net%2F20110421_297%2Fland_system_1303375736719uUU2O_JPEG%2Fdetail_15778_9.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20091010_3%2Fmtv4321_1255159987574kgz34_jpg%2F%25BF%25C1%25BB%25EA2_mtv4321.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=https%3A%2F%2Flandthumb-phinf.pstatic.net%2F20110421_286%2Fland_system_1303374081535qsxGm_JPEG%2Fdetail_5904_2.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjEwMjVfNjQg%2FMDAxNjY2NjU2MTI4ODgw.P8px2jfohS6G-7GxjNFkKa72G3BfXCXpULfFdsvZfcEg.VNUxmceeM7ZV6xFWg-Lty3rhJRPLJI9L2i-fXQ2hfEsg.JPEG.yahotee%2F11.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjEwMzFfMTYy%2FMDAxNjY3MjEzNTg4Mzc3.n156PgyE_3C9QfHL4sO1jNoHyDI2n6LaXzaKTMfJj6og.BYDF1S3Q6MveP8tzDjRjFNlaaWDPyCkX2NhgkCdpaZMg.JPEG.tksk9273%2F20221019%25A3%25DF153453.jpg&type=sc960_832",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjExMThfNCAg%2FMDAxNjY4NzQ4MDkyNTI5.1vHqZ0WJ9HB2sBWVVM1hPCd2JRkVjUaWK9pZ0bM2vtAg.Dv9av0K-AcPVzru0U1nHV8hXL-moph4mw7mhY4bpAPsg.JPEG.ngngn333%2F%25C3%25A2%25B5%25BF_%25BE%25C6%25C6%25C4%25A4%25D1%25A4%25BC.jpg&type=sc960_832",
        
      ]
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