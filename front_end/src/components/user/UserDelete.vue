<template>
  <div class="regist">
    <h1>회원 탈퇴</h1>
    <div>탈퇴중....</div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserDelete",
  components: {},
  computed: {
		...mapState(userStore, ["isLogin", "isValidToken", "userInfo"]),
	},
  methods: {
    ...mapActions(userStore, ["userDelete"]),
    async userDelete() {
      console.log(this.userInfo);
      await this.userDelete(this.userInfo);
      if (this.userInfo == null) {
        this.$router.push('/');
      }
    },
  }
};
</script>

<style></style>
