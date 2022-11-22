<template>
    <div>
        <the-search></the-search>
        <v-container col-sm-8>
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
        </v-container>
    </div>
</template>

<script>
import TheNews from "@/components/main/TheNews.vue";
import TheSearch from "@/components/main/TheSearch.vue";
import TheCard from "@/components/main/TheCard.vue";

export default {
    components: {
        TheNews,
        TheSearch,
        TheCard,
    },
}
</script>

<style>

</style>