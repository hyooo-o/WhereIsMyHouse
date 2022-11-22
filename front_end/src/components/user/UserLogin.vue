<template>
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">로그인</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-login" method="POST" action="">
					<!-- <div class="form-check mb-3 float-end">
						<input class="form-check-input" type="checkbox" value="ok"
							id="saveid" name="saveid" ${idck} /> <label
							class="form-check-label" for="saveid"> 아이디저장 </label>
					</div> -->
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid" v-model="user.userId"
							placeholder="아이디..." />
					</div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd" v-model="user.userPwd"
							placeholder="비밀번호..." />
					</div>
					<div class="text-danger mb-2"></div>
					<div class="col-auto text-center">
						<v-btn type="button" id="btn-login" @click="confirm">로그인</v-btn>
						<v-btn type="button" id="btn-mv-join" @click="moveJoin">회원가입</v-btn>
						<v-btn type="button" id="btn-search-pwd" @click="pwdsearch">비밀번호 찾기</v-btn>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
    name: "UserLogin",
	data() {
		return {
			// isLoginError: false,
			user: {
				userId: "",
				userPwd: "",
			},
		};
	},
	computed: {
		...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
	},
	methods: {
		...mapActions(userStore, ["userConfirm", "getUserInfo"]),
		async confirm() {
			console.log(this.user);
			await this.userConfirm(this.user);
			let token = sessionStorage.getItem("access-token");
			if (this.isLogin) {
				await this.getUserInfo(token)
				this.$router.push({ name: "main" });
			}
		},
		moveJoin() {
			this.$router.push({ name: "userjoin" });
		},
		pwdsearch() {
			this.$router.push({ name: "usersearchpwd" });
		}
	},
}
</script>

<style>

</style>