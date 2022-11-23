<template>
  <div>
    <p>년도별 평균 거래가</p>
    <LineChartGenerator
      :chart-options="chartOptions"
      :chart-data="chartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    />
  </div>
</template>

<script>
import { Line as LineChartGenerator } from 'vue-chartjs/legacy'

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  CategoryScale,
  PointElement
} from 'chart.js'

import { mapState } from "vuex"

const aptStore = "aptStore"

// import http from "@/api/http";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  CategoryScale,
  PointElement
)

export default {
  name: 'LineChart',
  components: {
    LineChartGenerator
  },
  props: {
    chartId: {
      type: String,
      default: 'line-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 400
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  computed: {
    ...mapState(aptStore, ["chartDatas"]),
    chartData() {
      let la = [];
      let d = [];

      this.chartDatas.forEach(data => {
        la.push(data.dealYear);
        d.push(data.pricePerYear);
      });

      return {
        labels: la,
        datasets: [
          {
            label: '년',
            backgroundColor: '#f87979',
            data: d
          }
        ]
      }
    },
  },
  methods: {
    print(){
      console.log(this.chartData);
    }
  }
}
</script>
