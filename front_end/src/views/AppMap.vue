<template>
  <div class="map" style="height:100%">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: "AppMap",
}
</script>

<style>
.map::-webkit-scrollbar { 
    display: none;
    width: 0 !important;
}
</style>