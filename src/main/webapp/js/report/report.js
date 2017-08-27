var swiper = new Swiper('.swiper-container', {
    loop: true,
    pagination: '.swiper-pagination',
    paginationClickable: true,
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    spaceBetween: 30,
    onSlideChangeEnd: function(swiper) {
        if (swiper.realIndex == 0) {
            $("#btnNext").show();
            $("#btnPrev").hide();
        }
        else {
            $("#btnNext").show();
            $("#btnPrev").show();
        }
    }
});