$(function () {
    $('body').addClass('loaded');
    var name = ${userName};
    $.ajax({
        type: "GET",
        url: "/user/info/",
        dataType : "json",
        success : function(data) {
            if (data.resultCode == "fail") {
                // TO DO
            }
        }
    });
});