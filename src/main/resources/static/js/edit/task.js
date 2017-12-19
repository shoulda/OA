/**
 * 触发增加函数
 */
$(function () {
    $("#addTas").click(function () {
        var taskname = $("#taskname").val().trim();
        console.log("=========="+projectname+"==========");
        if(taskname!=""){
            addTask(taskname);
        }else {
            alert("taskname不能为空!");
        }

    });
});


function addTask(taskname) {
    $.ajax({
        method: 'POST',
        url: '/task/insertTask',
        data: {
            'taskname': taskname,
        },
        success: function (e) {
            console.log(e);
            alert("插入成功！");
            $(".refreshT").load(location.href + " .refreshT");

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