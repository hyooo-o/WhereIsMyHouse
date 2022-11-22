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
    async delete() {
      console.log("corona");
      await this.userDelete(this.userInfo.userId);
      this.$router.push('/');
    },
  },
  mounted() {
    this.delete();
    alert("탈퇴 완료!");
  },
};
</script>

<style></style>
