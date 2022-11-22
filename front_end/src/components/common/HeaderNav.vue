<template>
    <div>
        <div v-if="userInfo">
            <header-nav-with-login></header-nav-with-login>
        </div>
        <div v-else>
            <header-nav-without-login></header-nav-without-login>
        </div>
    </div>
</template>

<script>
import HeaderNavWithLogin from "@/components/common/HeaderNavWithLogin.vue";
import HeaderNavWithoutLogin from "@/components/common/HeaderNavWithoutLogin.vue";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
    components : {
        HeaderNavWithLogin,
        HeaderNavWithoutLogin,
    },
    computed: {
        ...mapState(userStore, ["userInfo"]),
    }
}
</script>

<style>

</style>