<template>
    <div>
        <the-search></the-search>
        <!-- <chart></chart> -->
        <the-news></the-news>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
// import Chart from "@/components/chart/Chart";

export default {
    components: {
        TheNews,
        TheSearch,
        // Chart,
    },
}
</script>

<style>

</style>