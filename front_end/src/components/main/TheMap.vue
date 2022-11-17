<template>
    <div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="jumbotron"
					class="mt-4 p-5 jumbotron text-white jumbotron-image shadow"
					style="background-image: url(https://thumb.photo-ac.com/82/82aa26eb7c35a8a4491661920a692ece_t.jpeg)">
					<h2 class="mb-4" style="text-align: center">구해줘 Home !!</h2>
					<hr />
					<p class="mb-4" style="text-align: center">우리를 위한 집</p>
					<div class="content-search">
						<div class="container">
							<form id="search-form" action="apt/list" method="post" >
								<div class="row col-md-12 justify-content-center mb-2">
									<div class="form-group col-md-2">
										<select class="form-select bg-secondary text-light"
											name="gubun" id="gubun">
											<option value="">구분선택</option>
											<option value="1">동별</option>
											<option value="2">아파트별</option>
										</select>
									</div>
									<div class="form-group col-md-2">
										<select class="form-select bg-secondary text-light"
											name="sido" id="sido">
											<option value="">시도선택</option>
										</select>
									</div>
									<div class="form-group col-md-2">
										<select class="form-select bg-secondary text-light"
											name="gugun" id="gugun">
											<option value="">구군선택</option>
										</select>
									</div>
									<div class="form-group col-md-2" id="dongDiv">
										<select class="form-select bg-secondary text-light"
											name="dong" id="dong">
											<option value="">동선택</option>
										</select>
									</div>
									<div class="form-group col-md-2" style="display: none"
										id="aptNameDiv">
										<input class="form-control" id="aptName" name="aptName"
											type="text" placeholder="아파트명 입력" />
									</div>
									<div class="form-group col-md-2">
										<select class="form-select bg-secondary text-light"
											name="year" id="year"></select>
									</div>
									<div class="form-group col-md-2">
										<select class="form-select bg-secondary text-light"
											name="month" id="month">
											<option value="">매매월선택</option>
										</select>
									</div>
								</div>
								<input type="hidden" id="gugugun" name="gugugun"> <input
									type="hidden" id="dododong" name="dododong">
								<div style="display: flex" class="justify-content-end">
									<button type="button" class="btn btn-outline-secondary me-4"
										id="list-btn" @click="searchApt">검색</button>
								</div>
							</form>
						</div>
					</div>

					<div class="row">
						<div class="col-md-3">
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
						</div>
						<div class="col-sm-9">
							<div id="map" style="width: 100%; height: 700px">
								<script>
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
								</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</template>

<script>
export default {
    methods: {
        searchApt() {
            let form = document.querySelector("#search-form");
			
            let gugun = document.querySelector("#gugun");
			//gugun.value = gugun.options[gugun.selectedIndex].text;
			//gugun.options[gugun.selectedIndex].text 

			let gugugun = document.querySelector("#gugugun");
			//gugugun.value=gugun.options[gugun.selectedIndex].innerText;

            let dong = document.querySelector("#dong");
			console.log(dong.selectedIndex.innerText);
			//dong.value = dong.options[dong.selectedIndex].innerText;

			let dododong = document.querySelector("#dododong");
			dododong.value = dong.options[dong.selectedIndex].innerText;

			let yearSel = document.querySelector("#year");
			let year = yearSel[yearSel.selectedIndex].value;
			let monthSel = document.querySelector("#month");
			let month = monthSel[monthSel.selectedIndex].value;
			
			if(gugun.options[gugun.selectedIndex].text == "구군선택" || month == "매매년도선택" || year == "매매월월선택") {
				alert("다시 입력하세요");
			} else {
				console.log("버튼 클릭!!");
				form.submit();
			}
        }
    }
}
</script>

<style>

</style>