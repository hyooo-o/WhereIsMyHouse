<template>
    <div>
        <header-nav-user-info-without-login></header-nav-user-info-without-login>
        <header-nav-menu-without-login></header-nav-menu-without-login>
    </div>
</template>

<script>
import HeaderNavUserInfoWithoutLogin from "@/components/common/HeaderNavUserInfoWithoutLogin.vue";
import HeaderNavMenuWithoutLogin from "@/components/common/HeaderNavMenuWithoutLogin.vue";

export default {
    components : {
        HeaderNavUserInfoWithoutLogin,
        HeaderNavMenuWithoutLogin,
    }
}
</script>

<style>

</style>