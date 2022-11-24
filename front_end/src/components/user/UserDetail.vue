<template>
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">내 정보</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form action="">
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> 
            <span>{{userInfo.userName}}</span>
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label>
            <span>{{userInfo.userId}}</span>
					</div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label>
            <span>********</span>
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
            <span>{{userInfo.emailId}}</span>@<span>{{userInfo.emailDomain}}</span>
					</div>
					<div class="col-auto text-center">
						<v-btn type="button" id="btn-modify">
            <router-link class="nav-link" :to="{ name: 'usermodify' }">수정</router-link>
						</v-btn>
						<v-btn id="btn-ok">
							<router-link class="nav-link" :to="{ name: 'main' }">확인</router-link>
						</v-btn>
						<v-btn id="btn-del">
							<router-link class="nav-link" :to="{ name: 'userdelete' }">탈퇴</router-link>
						</v-btn>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
	name: "UserMyPage",
	components: {},
	computed: {
		...mapState(userStore, ["userInfo"]),
	},
	watch: {
		$route(to,from) {
			// store 좌표를 받아서
			// if(store 있을 때만) 옮기고
			// 없으면 안해
			// 그럼 스토어 울어... (；′⌒`)   .·´¯`(>▂<)´¯`·. 
		}
	}
};
</script>

<style>

</style>