<template>
    <div>
        <the-search></the-search>
        <the-news></the-news>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";


export default {
    components: {
        TheNews,
        TheSearch,
    },
}
</script>

<style>

</style>