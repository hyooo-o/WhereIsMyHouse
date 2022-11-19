<template>
    <div>
        <the-search></the-search>
        <BarChart />
        <the-map></the-map>
        <the-news></the-news>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
import BarChart from '@/components/chart/BarChart'
import TheMap from '@/components/map/TheMap';

export default {
    components: {
        TheNews,
        TheSearch,
        BarChart,
        TheMap,
    },
}
</script>

<style>

</style>