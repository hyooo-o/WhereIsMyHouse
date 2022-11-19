<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow">
		<div class="container-fluid">
			<a class="navbar-brand text-primary" href="/">
            <img
                src="https://play-lh.googleusercontent.com/nza1fOdLWUtyN1Z-rJQA7XrwHux0dGs2x5wdNpwZNaY677OMVD-4Lm-QDgFuY-XvZVbB=w600-h300-pc0xffffff-pd"
                width="100"
                alt=""/></a>
            <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end"
                id="navbarSupportedContent">
                <ul class="navbar-nav" id="logout-menu">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'map' }">지도</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'board' }">공지사항</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'qna' }">Q & A</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'userlogin' }">로그인</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {

}
</script>

<style>

</style>