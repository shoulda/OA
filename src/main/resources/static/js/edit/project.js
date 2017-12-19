
$(function () {
    $("#addPrj").click(function () {
        var projectname = $("#projectname").val().trim();
        console.log(projectname);
        addPrj(projectname);
    });
});

function addPrj(projectname) {
    $.ajax({
        method: 'POST',
        url: '/project/insertProject',
        data: {
            'projectname': projectname,
        },
        success: function (e) {
            console.log(e);
            alert("插入成功！")
        },
        error: function (e) {
            console.log(e);
            alert("插入失败！")
        }
    });
}


function delPrj() {

}