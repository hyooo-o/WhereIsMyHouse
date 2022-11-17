<template>
    <div>
        <header-nav-user-info-with-login></header-nav-user-info-with-login>
        <header-nav-menu-with-login></header-nav-menu-with-login>
    </div>
</template>

<script>
import HeaderNavUserInfoWithLogin from "@/components/common/HeaderNavUserInfoWithLogin.vue";
import HeaderNavMenuWithLogin from "@/components/common/HeaderNavMenuWithLogin.vue";

export default {
    components: {
        HeaderNavUserInfoWithLogin,
        HeaderNavMenuWithLogin,
    }
}
</script>

<style>

</style>