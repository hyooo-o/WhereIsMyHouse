<template>
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">수정</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<!-- <form id="form-modify" method="POST" action="">
					<input type="hidden" name="act" value="modify" /> -->
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label>
            <input type="text" class="form-control" id="username" v-model="user.userName" />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label>
            <input type="text" class="form-control" name="userid" v-model="user.userId" readonly/>
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label>
            <input type="password" class="form-control" id="userpwd" name="userpwd" v-model="user.userPwd" />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="password" class="form-control" id="pwdcheck"/>
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="emailid" name="emailid" v-model="user.emailId" />
                <span class="input-group-text">@</span>
                <select class="form-select" id="emaildomain" name="emaildomain" aria-label="이메일 도메인 선택" v-model="user.emailDomain">
                  <option selected>선택</option>
                  <option value="ssafy.com">싸피</option>
                  <option value="google.com">구글</option>
                  <option value="naver.com">네이버</option>
                  <option value="kakao.com">카카오</option>
                </select>
						</div>
					</div>
					<div class="col-auto text-center">
						<v-btn @click="modifyUser">회원수정</v-btn>
						<v-btn @click="reset">초기화</v-btn>
					</div>
				<!-- </form> -->
			</div>
		</div>
	</div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      user: {
        userName: null,
        userId: null,
        userPwd: null,
        emailId: null,
        emailDomain: null,
      }
    }
  },
  created() {
    this.user.userName = this.userInfo.userName;
    this.user.userId = this.userInfo.userId;
    this.user.emailId = this.userInfo.emailId;
    this.user.emailDomain = this.userInfo.emailDomain;
  },
  methods: {
    ...mapActions(userStore, ["userModify"]),
    async modifyUser() {
      // 비밀번호 입력 안하면 DB에서 사라지나??..
      this.userModify(this.user);
      this.$router.push({name : "userdetail"});
    },

  //   moveMypage() {
  //     console.log("내 정보로");
  //     this.$router.push({ name: "userdetail" });
  //   },
  }
};
</script>

<style>

</style>