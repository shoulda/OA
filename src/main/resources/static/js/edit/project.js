/**
 * 触发增加函数
 */
$(function () {
    $("#addPrj").click(function () {
        var projectname = $("#projectname").val().trim();
        console.log("=========="+projectname+"==========");
        if(projectname!=""){
            addPrj(projectname);
        }else {
            alert("projectname不能为空!");
        }

    });
});

/**
 * 通过projectname添加
 * @param projectname
 */
function addPrj(projectname) {
    $.ajax({
        method: 'POST',
        url: '/project/insertProject',
        data: {
            'projectname': projectname,
        },
        success: function (e) {
            console.log(e);
            alert("插入成功！");
            $(".refresh").load(location.href + " .refresh");

        },
        error: function (e) {
            console.log(e);
            alert("插入失败！")
        }
    });
}

/**
 * 通过projectname删除
 * @param projectname
 */
function delPrj() {

}


/**
 * 刷新
 */
$(function () {
    $("#updPrj").click(function () {
        $(".refresh").load(location.href + " .refresh");
    });
});
