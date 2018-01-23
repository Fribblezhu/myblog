
$(function() {
    $('#login #password').focus(function() {
        $('#owl-login').addClass('password');
    }).blur(function() {
        $('#owl-login').removeClass('password');
    });
    $("#doLogin").click(function (e) {
        var formData = {
            username:$("#userName").val(),
            password: $("#password").val()
        };
        doLogin(formData);
    });
});
function doLogin(loginData) {
    $.ajax({
        type : "POST",
        url : "/api/login",
        data : JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success : function(data) {
            setCookie(data.token);
            window.location.href ="/index";
        },
        error:function () {
            alert("登录失败!");
        }
    });
}
function setCookie(value, days) {
    days = days || 1;
    var expires = new Date();
    expires.setTime(expires.getTime() + days*24*60*60*1000);
    document.cookie = "token" + "="+ escape (value) + ";path=/" + ";expires=" + expires.toGMTString();
}
function getJwtToken() {
    return localStorage.getItem("token");
}

function setJwtToken(token) {
    setCookie("token", token);
    localStorage.setItem("token", token);
}

function removeJwtToken() {
    removeCookie("token");
    localStorage.removeItem(TOKEN_KEY);
}
