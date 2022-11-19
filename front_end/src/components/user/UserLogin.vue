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
						<button type="button" id="btn-login"
							class="btn btn-outline-primary mb-3" @click="confirm">로그인</button>
						<button type="button" id="btn-mv-join"
							class="btn btn-outline-success mb-3">회원가입</button>
						<button type="button" id="btn-search-pwd"
							class="btn btn-outline-primary mb-3">비밀번호 찾기</button>
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
			// console.log("1. confirm() token >> " + token);
			if (this.isLogin) {
				await this.getUserInfo(token);
				// console.log("4. confirm() userInfo :: ", this.userInfo);
				this.$router.push('/');
			}
		},
		movePage() {
			this.$router.push({ name: "join" });
		},
	},
}
</script>

<style>

</style>