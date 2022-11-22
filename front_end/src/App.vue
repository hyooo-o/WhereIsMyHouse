<template>
  <v-app>
    <header-nav></header-nav>
      <v-main style="height: 91.8%;">
        <router-view/>
      </v-main>
    <the-footer v-if="this.$route.path !== '/map/list'"></the-footer>
  </v-app>
</template>

<script>
import HeaderNav from "@/components/common/HeaderNav.vue";
import TheFooter from "@/components/common/TheFooter.vue";

export default {
  name: 'App',

  data: () => ({
    
  }),
  components: {
    HeaderNav,
    TheFooter
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* nav {
  padding: 30px;
} */

/* nav a {
  font-weight: bold;
  color: #2c3e50;
} */

/* nav a.router-link-exact-active {
  color: #42b983;
}

* {
	margin: 0;
	padding: 0;
	list-style: none;
} */

li {
	/*margin-top:20px;*/
}

.content-list {
	display: inline-block;
	width: 40%;
}

/* .top {
	background-color: rgb(62, 62, 62);
	height:40px;
}*/

/*.top-button {
	background-color: rgb(0, 0, 0);
	color: white;
	padding-left: 5px;
	padding-right: 7px;
	margin-right: 5px;
	height: 50px;
} */

.top>ul {
	padding-top: 5px;
	padding-right: 10px;
	float: right;
	height: 80px;
}

.top>ul>li {
	display: inline;
}

#jumbotron {
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

a{
  text-decoration: none;
  color: black;
}

footer{
  position: static;
  bottom: 0;
}

::-webkit-scrollbar {
  width: 7px;
}

::-webkit-scrollbar-track {
  background: #e6e6e6;
  border-left: 1px solid #dadada;
}

::-webkit-scrollbar-thumb {
  background: #f1f1f1;
  border: solid 3px #e6e6e6;
  border-radius: 7px;
}

::-webkit-scrollbar-thumb:hover {
  background: rgb(204, 204, 204);
}
</style>
