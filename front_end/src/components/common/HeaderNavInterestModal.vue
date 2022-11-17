<template>
    <div class="modal" id="interestingEnrollModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">관심지역 등록</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="POST" action="${root}/info">
                        <input type="hidden" name="act" value="registArea">
                        <div class="content-search">
                            <div class="container">
                                <div class="row col-md-12 justify-content-center mb-2">
                                    <div class="form-group col-md-4">
                                        <select class="form-select bg-secondary text-light" id="sido-modal" name="sido">
                                            <option value="">시도선택</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <select class="form-select bg-secondary text-light" id="gugun-modal" name="gugun">
                                            <option value="">구군선택</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <select class="form-select bg-secondary text-light" id="dong-modal" name="dong">
                                            <option value="">동선택</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" >등록</button>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>