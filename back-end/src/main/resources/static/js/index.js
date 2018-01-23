
$(function () {
    $('body').addClass('loaded');
    $.ajax({
        type: "GET",
        url: "/user/info",
        dataType : "json",
        success : function(data) {
            if (data.resultCode == "fail") {
                // TO DO
            }
        }
    });
});