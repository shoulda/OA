var w, h, className;

function getSrceenWH() {
    w = $(window).width();
    h = $(window).height();
    $('#dialogBg').width(w).height(h);
}

window.onresize = function () {
    getSrceenWH();
}
$(window).resize();

$(function () {
    getSrceenWH();

    //显示弹框
    $('#pop').click(function () {
        className = $(this).attr('class');
        $('#dialogBg').fadeIn(300);
        $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
    });

    //关闭弹窗
    $('.close').click(function () {
        $('#dialogBg').fadeOut(300, function () {
            $('#dialog').addClass('bounceOutUp').fadeOut();
        });
    });

    $('.btnSubmit').click(function () {
        var oldPassword = $("#oldPassword").val().trim();
        var newPassword1 = $("#newPassword1").val().trim();
        var newPassword2 = $("#newPassword2").val().trim();
        console.log(oldPassword + "++++" + newPassword1 + "++++" + newPassword2);
        modifyPassword($.md5(oldPassword), $.md5(newPassword1), $.md5(newPassword2));
    });

    function modifyPassword(oldPassword, newPassword1, newPassword2) {
        $.ajax({
            method: 'POST',
            url: '/user/modifyPassword',
            data: {
                "oldPassword": oldPassword,
                "newPassword1": newPassword1,
                "newPassword2": newPassword2
            },
            success: function (e) {
                if (e.code == 200) {
                    alert(e.message);
                    $('#dialogBg').fadeOut(300, function () {
                        $('#dialog').addClass('bounceOutUp').fadeOut();
                    });
                } else {
                    alert(e.message);
                }
            },
            error: function () {
                alert("Error!");
            }
        });
    }
});