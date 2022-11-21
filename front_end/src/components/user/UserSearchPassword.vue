<template>
	<v-container>
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">비밀번호 찾기</h2>
			</div>

			<v-row>
        <v-col>
					<div class="form-group row" style="padding-top: 5%;">
						<label for="userid" class="col-sm-2 col-form-label">아이디 : </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid" placeholder="아이디..." v-model="user.userId" />
						</div>
					</div>
					<div class="form-group row" style="padding-top: 5%;">
						<div>
							<v-btn type="button" id="btn-search-pwd" @click="findpwd">비밀번호 찾기</v-btn>
						</div>
					</div>
				</v-col>
			</v-row>
		</div>
	</v-container>
</template>

<script>
import http from "@/api/http";

export default {
	data() {
		return {
			user: {
				userId: null,
				userPwd: null,
			},
		};
	},
	methods: {
		findpwd() {
			http.get(`user/user/password/${this.user.userId}`).then(({ data }) => {
				this.user.userPwd = data;
				alert("당신의 비밀번호는: " + data + "입니다.");
			})
		}
	}
}

</script>

<style>

</style>