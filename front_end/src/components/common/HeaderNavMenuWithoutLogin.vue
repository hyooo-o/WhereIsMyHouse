<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow">
		<div class="container-fluid">
			<a class="navbar-brand text-primary" href="${root}/index.jsp">
            <img
                src="https://play-lh.googleusercontent.com/nza1fOdLWUtyN1Z-rJQA7XrwHux0dGs2x5wdNpwZNaY677OMVD-4Lm-QDgFuY-XvZVbB=w600-h300-pc0xffffff-pd"
                width="150"
                alt=""/></a>
            <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end"
                id="navbarSupportedContent">
                <ul class="navbar-nav mb-5" id="logout-menu">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page"
                        @click="moveToBoard">공지사항</a>
                        <a class="nav-link" aria-current="page"
                        @click="moveToQNA">Q&A</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    methods: {
        moveToBoard() {
            this.$router.push({name : "board"});
        },
        moveToQNA() {
            this.$router.push({name : "qna"});
        }
    }
}
</script>

<style>

</style>