<template>
	<!-- <div class="container">
		<div class="row">
			<div class="col-md-12">
				
					<div @click="print">
						아파트 확인
					</div>
					<div class="row"> -->
						<!-- <div class="col-md-3">
							<div
								style="width: 100%; height: 700px; overflow: auto; background-color: beige;">
								<table class="table table-hover text-center">
									<tbody id="aptList">
										<c:if test="${aptList ne null}">
											<c:forEach items="${aptList}" var="apt">
												<tr>
													<td>아파트 : ${apt.getName()} <br> 면적 :
														${apt.getArea()} <br> 금액 :
														${apt.getDealAmount().trim()} 만원
													</td>

												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div> -->
            <div style="width: 100%; height: 80%">
				<div id="map" style="width: 100%; height: 70%">
					<!-- <script>
						var container = document
								.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
						var options = { //지도를 생성할 때 필요한 기본 옵션
							center : new kakao.maps.LatLng(
									37.5012743, 127.039585), //지도의 중심좌표.
							level : 3
						//지도의 레벨(확대, 축소 정도)
						};

						if (navigator.geolocation) {
							// GeoLocation을 이용해서 접속 위치를 얻어옵니다
							navigator.geolocation
									.getCurrentPosition(function(
											position) {
										var lat = position.coords.latitude, // 위도
										lon = position.coords.longitude; // 경도

										var locPosition = new kakao.maps.LatLng(
												lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
										message = '<div style="padding:5px;"> 여기에 계신가요?! </div>'; // 인포윈도우에 표시될 내용입니다

										// 마커와 인포윈도우를 표시합니다
										displayMarker(locPosition,
												message);
									});
						} else {
							// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

							var locPosition = new kakao.maps.LatLng(
									33.450701, 126.570667), message = "geolocation을 사용할수 없어요..";

							displayMarker(locPosition, message);
						}

						var map = new kakao.maps.Map(container,
								options); //지도 생성 및 객체 리턴

						// 마커가 표시될 위치입니다 
						var markerPosition = new kakao.maps.LatLng(
								37.5012743, 127.039585);

						// 마커를 생성합니다
						var marker = new kakao.maps.Marker({
							position : markerPosition
						});

						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
						map.setCenter(locPosition);
					</script> -->
            </div>
				<div style="width: 100%; height: 10%;">
					<map-detail></map-detail>
				</div>
            </div>
					<!-- </div>
				</div>
		</div>

	</div> -->
</template>

<script>
import MapDetail from "@/components/map/MapDetail.vue";
import { mapState, mapActions } from "vuex";

const aptStore = "aptStore";

export default {
	data() {
		return {
			map: null,
			markers: [],
		}
	},
	components: {
		MapDetail,
	},
	computed: {
		...mapState(aptStore, ["apt", "aptList", "drawer"]),
	},
    methods: {
		...mapActions(aptStore, ["getAptList", "setApt", "switchDrawer"]),
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

			let neLat = this.map.getBounds().getNorthEast().getLat();
			let neLng = this.map.getBounds().getNorthEast().getLng();
			let swLat = this.map.getBounds().getSouthWest().getLat();
			let swLng = this.map.getBounds().getSouthWest().getLng();
			vueInstance.displayMarker(neLat, neLng, swLat, swLng);

			kakao.maps.event.addListener(this.map, "zoom_changed", function() {
				var neLat = this.getBounds().getNorthEast().getLat();
				var neLng = this.getBounds().getNorthEast().getLng();
				var swLat = this.getBounds().getSouthWest().getLat();
				var swLng = this.getBounds().getSouthWest().getLng();
				vueInstance.setMarkers(null);
				vueInstance.displayMarker(neLat, neLng, swLat, swLng);
			});

			kakao.maps.event.addListener(this.map, 'dragend', function() {        
				var neLat = this.getBounds().getNorthEast().getLat();
				var neLng = this.getBounds().getNorthEast().getLng();
				var swLat = this.getBounds().getSouthWest().getLat();
				var swLng = this.getBounds().getSouthWest().getLng();
				vueInstance.setMarkers(null);
				vueInstance.displayMarker(neLat, neLng, swLat, swLng);
			});
		},
		createMarker() {
			const vueInstance = this;

			this.aptList.forEach(function(apt) {
				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					position: new kakao.maps.LatLng(apt.lat, apt.lng),
				});

				let iwContent = document.createElement("div")

				let companyName = document.createElement("div")
				companyName.textContent = `아파트명 : ${apt.name}`

				iwContent.append(
					companyName,
				);

				// var iwContent = `<button style="padding:5px;" id="${element.id}">${element.name}</button>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content : iwContent,
				});
				
				kakao.maps.event.addListener(marker, 'click', function() {
					// 뷰엑스의 apt = element
					// drawer = !drawer
					vueInstance.setApt(apt);
					console.log(vueInstance.drawer);
					vueInstance.switchDrawer(!vueInstance.drawer);
				});

				kakao.maps.event.addListener(marker, 'mouseover', function() {
					// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
					infowindow.open(this.map, marker);
				});

				// // 마커에 마우스아웃 이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'mouseout', function() {
					// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
					infowindow.close();
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
			this.setMarkers(this.map);
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
			`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services`;
			document.head.appendChild(script);
		}
	},
}
</script>

<style>

</style>