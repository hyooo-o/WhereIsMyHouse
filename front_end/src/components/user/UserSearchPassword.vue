<template>
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">비밀번호 찾기</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-search" method="POST" action="">
					<input type="hidden" name="act" value="search" />
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디..." value="${saveid}" />
					</div>
					<button type="button" id="btn-search-pwd"
						class="btn btn-outline-primary mb-3">비밀번호 찾기</button>
				</form>
            </div>
		</div>
	</div>
</template>

<script>
export default {

}
document.querySelector("#btn-search-pwd").addEventListener("click",
				function() {
					let userid = document.querySelector("#userid").value;
					location.href="${root}/user?act=search&userid="+userid;
				});

</script>

<style>

</style>