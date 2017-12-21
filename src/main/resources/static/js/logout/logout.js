$(function () {
    $("#logout").click(function () {
        console.log("正在退出。。。。");
        $.ajax({
            method: 'GET',
            url: '/logout',
            success: function () {
                window.location.href = '../login';
            },
            error: function () {
                alert("Error!");
            }
        });
    });
});