<template>
  <div class="container mt-3">
		<h2>공지사항</h2>
		<div style="display: flex;" class="justify-content-center">
			<div class="col-md-7 offset-1">
				<form class="d-flex" id="form-search" action="">
					<select class="form-select form-select-sm ms-5 me-1 w-50"
						name="key" aria-label="검색조건">
						<option value="" selected>검색조건</option>
						<option value="subject">제목</option>
						<option value="userid">작성자</option>
					</select>
					<div class="input-group input-group-sm">
						<input type="text" class="form-control" name="word"
							placeholder="검색어..." />
						<button id="btn-search" class="btn btn-dark" type="button">검색</button>
					</div>
				</form>
			</div>
		</div>
  </div>
</template>

<script>
// import http from "@/api/http";

export default {
  name: "BoardSearch",
  data() {
    return {
      
    }
  }
}
</script>

<style>

</style>