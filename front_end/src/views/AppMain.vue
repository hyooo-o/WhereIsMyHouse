<template>
    <div>
        <the-search></the-search>
        <v-container col-sm-8>
            <div class="col-sm-12">
                <div class="row">
                    <div class="col-sm-6 text-center">
                        <!-- <the-card></the-card> -->
                        <the-board></the-board>
                    </div>
                    <div class="col-sm-6">
                        <the-news></the-news>
                    </div>
                </div>
            </div>
        </v-container>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
// import TheCard from "@/components/main/TheCard.vue";
import TheBoard from "@/components/main/TheBoard";

export default {
    components: {
        TheNews,
        TheSearch,
        // TheCard,
        TheBoard,
    },
}
</script>

<style>

</style>