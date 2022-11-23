<template>
	<div style="width: 100%; height: 100%">
		<div id="map" style="width: 100%; height: 100%"></div>
		<map-detail></map-detail>
	</div>
</template>

<script>
import MapDetail from "@/components/map/MapDetail.vue";
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore";
const userStore = "userStore";

export default {
	data() {
		return {
			map: null,
			markers: [],
			clusterer: null,
		}
	},
	components: {
		MapDetail,
	},
	computed: {
		...mapState(aptStore, ["apt", "aptList", "drawer"]),
	},
    methods: {
		...mapActions(userStore, ["setFavorite"]),
		...mapActions(aptStore, ["getAptList", "setChartData", "setApt", "turnOnDrawer"]),
        // searchApt() {
        //     let form = document.querySelector("#search-form");
			
        //     let gugun = document.querySelector("#gugun");
		// 	//gugun.value = gugun.options[gugun.selectedIndex].text;
		// 	//gugun.options[gugun.selectedIndex].text 

		// 	let gugugun = document.querySelector("#gugugun");
		// 	//gugugun.value=gugun.options[gugun.selectedIndex].innerText;

        //     let dong = document.querySelector("#dong");
		// 	console.log(dong.selectedIndex.innerText);
		// 	//dong.value = dong.options[dong.selectedIndex].innerText;

		// 	let dododong = document.querySelector("#dododong");
		// 	dododong.value = dong.options[dong.selectedIndex].innerText;

		// 	let yearSel = document.querySelector("#year");
		// 	let year = yearSel[yearSel.selectedIndex].value;
		// 	let monthSel = document.querySelector("#month");
		// 	let month = monthSel[monthSel.selectedIndex].value;
			
		// 	if(gugun.options[gugun.selectedIndex].text == "구군선택" || month == "매매년도선택" || year == "매매월월선택") {
		// 		alert("다시 입력하세요");
		// 	} else {
		// 		console.log("버튼 클릭!!");
		// 		form.submit();
		// 	}
        // }
		initMap() {
			const container = document.getElementById("map");
			const options = {
				center: new kakao.maps.LatLng(37.555, 127),
				level: 5,
			};
			// var markers=[];
			const vueInstance = this;

			//지도 객체를 등록합니다.
			//지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
			this.map = new kakao.maps.Map(container, options);

			// 마커 클러스터러를 생성합니다 
			vueInstance.clusterer = new kakao.maps.MarkerClusterer({
				map: vueInstance.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
				averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
				minLevel: 2, // 클러스터 할 최소 지도 레벨
				gridSize: 200 
			});

			let neLat = this.map.getBounds().getNorthEast().getLat();
			let neLng = this.map.getBounds().getNorthEast().getLng();
			let swLat = this.map.getBounds().getSouthWest().getLat();
			let swLng = this.map.getBounds().getSouthWest().getLng();
			vueInstance.displayMarker(neLat, neLng, swLat, swLng);

			kakao.maps.event.addListener(this.map, "zoom_changed", function() {
				var level = vueInstance.map.getLevel();

				if(level <= 5){
					var neLat = this.getBounds().getNorthEast().getLat();
					var neLng = this.getBounds().getNorthEast().getLng();
					var swLat = this.getBounds().getSouthWest().getLat();
					var swLng = this.getBounds().getSouthWest().getLng();
					// vueInstance.setMarkers(null);
					vueInstance.displayMarker(neLat, neLng, swLat, swLng);
				} else {
					vueInstance.markers = [];

					vueInstance.clusterer.clear();
			
					vueInstance.clusterer.addMarkers(vueInstance.markers);
				}
			});

			kakao.maps.event.addListener(this.map, 'dragend', function() {   
				var level = vueInstance.map.getLevel();
				
				if(level <= 5){
					var neLat = this.getBounds().getNorthEast().getLat();
					var neLng = this.getBounds().getNorthEast().getLng();
					var swLat = this.getBounds().getSouthWest().getLat();
					var swLng = this.getBounds().getSouthWest().getLng();
					// vueInstance.setMarkers(null);
					vueInstance.displayMarker(neLat, neLng, swLat, swLng);
				} else {
					vueInstance.markers = [];

					vueInstance.clusterer.clear();
			
					vueInstance.clusterer.addMarkers(vueInstance.markers);
				}
			});
		},
		createMarker() {
			const vueInstance = this;

			this.aptList.forEach(function(apt) {
				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					position: new kakao.maps.LatLng(apt.lat, apt.lng),
				});
				
				kakao.maps.event.addListener(marker, 'click', function() {
					// 뷰엑스의 apt = element
					vueInstance.setApt(apt);
					// 유저의 데이터와 아파트의 데이터를 비교해서 userStore의 favorite 상태 변경
					vueInstance.setChartData(apt.aptCode);
					vueInstance.setFavorite(apt.aptCode);
					vueInstance.turnOnDrawer();
				});

				// vueInstance.markers.push(marker);
				vueInstance.markers.push(marker);
			})    
		},
		async displayMarker(neLat, neLng, swLat, swLng) {
			const loc = {
				neLat,
				neLng,
				swLat,
				swLng
			};

			const vueInstance = this;

			await this.getAptList(loc);

			// console.log(neLat, neLng, swLat, swLng);

			vueInstance.markers = [];

			this.createMarker();

			// vueInstance.markers.forEach((marker) => {
			// 	marker.setMap(vueInstance.map);	
			// });

			vueInstance.clusterer.clear();
			
			// this.setMarkers(this.map);

			vueInstance.clusterer.addMarkers(vueInstance.markers);
		},
		
		setMarkers(show) {
			for (var i = 0; i < this.markers.length; i++) {
				this.markers[i].setMap(show);	
			}            
		}
	},
	mounted() {
		if (window.kakao && window.kakao.maps) {
			this.initMap();
		} else {
			const script = document.createElement("script");
			/* global kakao */
			script.onload = () => kakao.maps.load(this.initMap);
			script.src =
			`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services,clusterer,drawing`;
			document.head.appendChild(script);
		}
	},
}
</script>

<style>

</style>