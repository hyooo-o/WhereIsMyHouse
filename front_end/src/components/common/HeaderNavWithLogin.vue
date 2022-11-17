<template>
        <nav class="navbar navbar-expand-lg navbar-light bg-white shadow">
		<div class="container-fluid">
			<a class="navbar-brand text-primary" href="${root}/index.jsp">
            <img
                src="https://play-lh.googleusercontent.com/nza1fOdLWUtyN1Z-rJQA7XrwHux0dGs2x5wdNpwZNaY677OMVD-4Lm-QDgFuY-XvZVbB=w600-h300-pc0xffffff-pd"
                width="150"
                alt=""/></a>

            <ul class="navbar-nav" id="login-menu">
                <li class="nav-item" style="padding-right: 30px">
                    <div class="dropdown">
                        <button type="button" class="nav-link border-0 bg-white dropdown-toggle" data-bs-toggle="dropdown">관심지역</button>
                        <ul class="dropdown-menu">
                            <li>
                                <a
                                class="dropdown-item"
                                href="#"
                                onclick="sendRequest('sido-modal', '*00000000');"
                                data-bs-toggle="modal"
                                data-bs-target="#interestingEnrollModal"
                                >관심지역 등록</a
                                >
                            </li>
                            <li><a class="dropdown-item" href="#">관심지역 정보</a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item" style="padding-right: 30px">
                    <a class="nav-link" aria-current="page" href="/board">공지사항</a>
                </li>
                <li class="nav-item" style="padding-right: 30px">
                    <a class="nav-link" aria-current="page" href="/qna">Q&A</a>
                </li>
                <li class="nav-item" style="padding-right: 30px">
                    <a class="nav-link" aria-current="page" href="${root}/apt/mvRegist">아파트 관리</a>
                </li>
                <li class="nav-item" style="padding-right: 30px">
                    <div class="text-center">
                        <v-menu
                            open-on-hover
                            offset-y>
                            <template v-slot:activator="{ on, attrs }">
                                <v-text class="nav-link" v-bind="attrs" v-on="on">
                                    <b>{{ name }}</b>님 안녕하세요<v-icon>mdi-menu-down</v-icon>
                                </v-text>
                            </template>
                            <v-list>
                                <v-list-item link>
                                    <v-list-item-title @click="logout">마이페이지</v-list-item-title>
                                </v-list-item>
                                <v-list-item link>
                                    <v-list-item-title @click="logout">관심지역</v-list-item-title>
                                </v-list-item>
                                <v-list-item link>
                                    <v-list-item-title @click="logout">로그아웃</v-list-item-title>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                    </div>
                    <!-- <a class="nav-link" aria-current="page" href="${root}/apt/mvRegist">{{this.$store.state.loginUser.userName}}님 안녕하세요</a> -->
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
export default {
    computed: {
        name() {
            return this.$store.state.loginUser.userName;
        }
    },
    methods: {
		logout() {
			this.$store.dispatch("logout");
		},
        moveToLogin() {
            this.$router.push({name : "userlogin"});
        },
        moveToJoin() {
            this.$router.push({name : "userjoin"});
        }
	}
}
</script>

<style>

</style>