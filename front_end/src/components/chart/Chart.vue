<template>
  <div>
    <line-chart :options="options" :chartData="chartData"></line-chart>
  </div>
</template>

<script>
import LineChart from "@/components/chart/LineChart.js";

export default {
  name: "line-chart",
  type: "line",
  components: {
    LineChart
  },
  data() {
    return {
      chartData: {
        labels: ['4 / 8', '4 / 9', '4 / 10', '4 / 11', '4 / 12', '4 / 13', '4 / 14', '4 / 15', '4 / 16', '4 / 17'],
        datasets: [
          {
            borderWidth: 1, // 라인 넓이
            label: '총 수면 시간', // 데이터 라벨
            backgroundColor: 'rgb(255,255,255, 0)',
            pointBackgroundColor: '#64CCA2',
            fill: true, // 채우기
            // barPercentage: 1, 바 안쪽 넓이
            // categoryPercentage: 0.5, 바 외부 넓이
            tension: 0.1,
            borderColor: '#64CCA2',
            pointBorderColor: '#64CCA2',
            pointBorderWidth: 1,
            data: [45, 50, 60, 50, 45, 80, 50, 40, 68, 70],
          },
        ],
      },
      options: {
        scales: {
          xAxes: [
            {
              stacked: false, // 쌓임
              display: true, // x 축 show
              scaleLabel: {
                display: true,
                labelString: '날짜',
              },
              grid: {
                drawBorder: false,
                color: '#323232',
                lineWidth: 1,
              },
              ticks: {
                beginAtZero: true, // 0부터 시작
                padding: 0,
              },
            },
          ],
          // y축 옵션
          yAxes: [
            {
              stacked: false, // 쌓임
              display: true, // y 축 show
              ticks: {
                stepSize: 20, // 증가범위
                beginAtZero: true,
                max: 100, // 최대범위
                min: 0, // 최소범위
                padding: 10, // 오른쪽 간격
              },
              grid: {
                drawBorder: false,
                color: '#323232',
                lineWidth: 1,
              },
            },
          ],
        },
      },
    }
  },
}
</script>

<style>
  
</style>