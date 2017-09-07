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
        var obj = $("#detail_top_" + swiper.realIndex);
        if (obj[0]) {

            if (obj[0].scrollHeight > obj[0].clientHeight || obj[0].offsetHeight > obj[0].clientHeight) {
                $("#btnUp").show();
            }
        }
        

    },
    onSlideChangeStart: function(swiper){
        $("#btnUp").hide();
    }
})

$(".detail_top").scroll(function () {
    var h = $(this).height();
    var sh = $(this)[0].scrollHeight;
    var st = $(this)[0].scrollTop;
    if (h + st >= sh-1) {
        $("#btnUp").hide();
    }
    else {
        $("#btnUp").show();
    }
})