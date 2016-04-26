$(document).ready(function () {
    //$('.ershou-samll-photo').children("img").addClass("cur");
    $(".bigger-slide-photo").children(".bigger-photo").last().removeClass("hide");

    $(".ershou-samll-photo").mouseover(function () {
        index = $(this).index();
        console.log(index);
        $(this).children("img").addClass("cur");
        $(".bigger-photo").removeClass("show").eq(index).addClass("show");
    });
    $(".ershou-samll-photo").mouseout(function () {
        $(this).children("img").removeClass("cur");
    });
});