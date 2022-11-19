<template>
    <div>
        <the-search></the-search>
        <!-- <BarChart /> -->
        <the-news></the-news>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
// import BarChart from '@/components/chart/BarChart'

export default {
    components: {
        TheNews,
        TheSearch,
        // BarChart,
    },
}
</script>

<style>

</style>