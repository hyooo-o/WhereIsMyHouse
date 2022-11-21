<template>
    <div>
        <the-search></the-search>
        <!-- <BarChart /> -->
        <div class="col-sm-12">
            <div class="row">
                <div class="col-sm-6 text-center">
                    <the-card></the-card>
                </div>
                <div class="col-sm-6">
                    <the-news></the-news>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
import TheCard from "@/components/main/TheCard.vue";
// import BarChart from '@/components/chart/BarChart'

export default {
    components: {
        TheNews,
        TheSearch,
        TheCard,
        // BarChart,
    },
}
</script>

<style>

</style>