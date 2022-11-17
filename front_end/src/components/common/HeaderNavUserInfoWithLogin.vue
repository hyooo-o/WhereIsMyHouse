<template>
    <nav>
	<div class="container-fluid">
		<div class="row">
			<div class="top">
				<ul class="list-group list-group-horizontal" id="login-off  "
					style="padding-top: 13px">
                    <li class="nav-item nav-link">{{this.$store.state.loginUser.userName}}님 안녕하세요</li>
                    <li class="top-button list-group-item">
                        <a class="nav-link" href="#">마이페이지</a>
                    </li>
                    <button type="button" id="btn-logout"
                        class="btn btn-outline-danger mb-3 ms-1" @click="logout">로그아웃</button>
				</ul>
			</div>
		</div>
	</div>
	</nav>
</template>

<script>


export default {
	methods: {
		logout() {
			this.$store.dispatch("logout");
		}
	}

}
</script>

<style>

</style>