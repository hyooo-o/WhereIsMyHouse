<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow">
    <div class="container-fluid">
        <a class="navbar-brand text-primary" href="/">
            <img
            src="https://play-lh.googleusercontent.com/nza1fOdLWUtyN1Z-rJQA7XrwHux0dGs2x5wdNpwZNaY677OMVD-4Lm-QDgFuY-XvZVbB=w600-h300-pc0xffffff-pd"
            width="150"
            alt=""/>
        </a>

        <ul class="navbar-nav" id="login-menu">
            <li class="nav-item" style="padding-right: 30px">
                <router-link class="nav-link" :to="{ name: 'map' }">지도</router-link>
            </li>
            <li class="nav-item" style="padding-right: 30px">
                <router-link class="nav-link" :to="{ name: 'board' }">공지사항</router-link>
            </li>
            <li class="nav-item" style="padding-right: 30px">
                <router-link class="nav-link" :to="{ name: 'qna' }">Q & A</router-link>
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
                                <v-list-item-title @click="mypage">마이페이지</v-list-item-title>
                            </v-list-item>
                            <v-list-item link>
                                <v-list-item-title @click="favorite">관심지역</v-list-item-title>
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
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
computed: {
    ...mapState(userStore, ["userInfo"]),
    name() {
        return this.userInfo.userName;
    }
},
methods: {
    ...mapActions(userStore, ["userLogout"]),
    logout() {
        // this.SET_IS_LOGIN(false);
        // this.SET_USER_INFO(null);
        // sessionStorage.removeItem("access-token");
        // if (this.$route.path != "/") this.$router.push({ name: "main" });
        //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
        //+ satate에 isLogin, userInfo 정보 변경)
        // this.$store.dispatch("userLogout", this.userInfo.userid);
        this.userLogout(this.userInfo.userId);
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기;
        if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
    moveToLogin() {
        this.$router.push({name : "userlogin"});
    },
    moveToJoin() {
        this.$router.push({name : "userjoin"});
    },
    mypage() {
        this.$router.push({ name: "userdetail" });
    },
    favorite() {
        this.$router.push({name : "useraptfavorite"});
    }
}
}
</script>

<style>

</style>