<template>
    <div>
        <div v-show="!login">
            <header-nav-without-login></header-nav-without-login>
        </div>
        <div v-show="login">
            <header-nav-with-login></header-nav-with-login>
        </div>
    </div>
</template>

<script>
import HeaderNavWithLogin from "@/components/common/HeaderNavWithLogin.vue";
import HeaderNavWithoutLogin from "@/components/common/HeaderNavWithoutLogin.vue";

export default {
    components : {
        HeaderNavWithLogin,
        HeaderNavWithoutLogin,
    },
    computed: {
        login() {
            return this.$store.state.loginUser !== null;
        }
    }
}
</script>

<style>

</style>