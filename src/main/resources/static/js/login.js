/**
 * Created by Johnny on 17/11/19.
 */
// function emailCheck (emailStr) {
//     var emailPat=/^(.+)@siemens.com/;
//     var matchArray=emailStr.match(emailPat);
//     if (matchArray===null) {
//         $("#myModal").modal('show');
//         $(".modal-body").html('请输入合法的西门子邮箱');
//         return false;
//     }
//     return true;
// // }
function login(userName, password) {

    $.ajax({
        method: 'POST',
        url: '/login',
        data: {
            'userName': userName,
            'password': password
        },
        success: function (e) {
            console.log(e);
            console.log(e.auth);
            if (e.code == 200 && e.auth == "user") {
                alert("Login successfully!" + " Welcome " + userName);
                window.location.href = 'index';
            } else if (e.code == 200 && e.auth == "admin") {
                alert("Login successfully!" + " Welcome admin " + userName);
                window.location.href = 'admin';
            }
            else if (e.message) {
                alert("Account or password error");
            }
        },
        error: function () {
            alert("Error!");
            alert(userName);
            alert(password);
        }
    });
}

$(function () {
    $("#btnSubmit").click(function () {
        var userName = $("#userName").val().trim();
        var password = $("#userPassword").val().trim();
        // if (userName === undefined || userName.length < 1) {
        //     $("#myModal").modal('show');
        //     $(".modal-body").html('请输入用户名');
        //     return;
        // }
        // if (password === undefined || password.length < 1) {
        //     $("#myModal").modal('show');
        //     $(".modal-body").html('请输入密码');
        //     return;
        // }
        // if(emailCheck(userName)){
        // }
        login(userName, password);


    });

});

/**
 * 产生0-5随机数
 */
function render() {
    var num = Math.ceil(Math.random() * 5);
    return num;
}