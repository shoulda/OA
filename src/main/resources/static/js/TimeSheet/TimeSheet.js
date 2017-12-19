/**
 * 数据格式
 * @param data
 * @constructor
 */
// var dataBody = [
//     {
//         "name": "xujin",
//         "age": 24,
//         "sex": 1,
//         "address": "sichuan"
//     },
//     {
//         "name": "liyuhui",
//         "age": 24,
//         "sex": 2,
//         "address": "sichuan"
//     },
//     {
//         "name": "kobe",
//         "age": 25,
//         "sex": 3,
//         "address": "sichuan"
//     },
//     {
//         "name": "niryinghao",
//         "age": 24,
//         "sex": 4,
//         "address": "sichuan"
//     }
// ];
//
// var dataHead = [
//     {
//         "projectid": 1,
//         "projectname": "oa"
//     },
//     {
//         "projectid": 2,
//         "projectname": "edgebox"
//     },
//     {
//         "projectid": 3,
//         "projectname": "ui"
//     }
// ];
/**
 * 初始化weekid选择框
 * @constructor
 */
function InitSelect() {
    $.getJSON("work/selectAllWeekID", function (data) {
        var select = document.getElementById("weekid");
        for (var i = 1; i <= data.length; i++) {
            select.options.add(new Option("第" + i + "周", data[i - 1]));
        }
        $("#weekid").val(data[i - 2]);
    })
}

/**
 * 填充表头
 * @param data
 * @constructor
 */
function FeedTableHead(data) {
    var thead = document.getElementById("head");
    var th = document.createElement("th");
    th.innerHTML = "name";
    thead.appendChild(th);
    for (var p in data) {
        var th = document.createElement("th");
        th.innerHTML = data[p]["projectname"];
        thead.appendChild(th);
    }
}

/**
 * 填充表体
 * @param data
 * @returns {{}}
 * @constructor
 */
function FeedTableContent(data) {
    var tbody = document.getElementById("body");
    var trr = document.getElementById("head");
    var count = {};
    for (var k = 1; k < trr.childNodes.length; k++) {
        count[trr.childNodes[k].innerHTML] = 0;
    }
    for (var i in data) {
        var tr = document.createElement("tr");
        tr.align = "center";
        tbody.appendChild(tr);
        var j = 0;
        for (var text in data[i]) {
            var td = tr.insertCell(tr.cells.length);
            var index = trr.childNodes[j].innerHTML;
            console.log(index);
            td.innerHTML = data[i][index];
            count[index] += parseInt(data[i][index]);
            j = j + 1;
        }
    }
    count["name"] = "总和";
    return count;
}

/**
 * 填充表尾
 * @param data
 * @constructor
 */
function FeedTableFoot(data) {
    var tfoot = document.getElementById("foot");
    var trr = document.getElementById("head");
    var j = 0;
    for (var text in data) {
        var td = tfoot.insertCell(tfoot.cells.length);
        var index = trr.childNodes[j].innerHTML;
        console.log(index);
        td.innerHTML = data[index];
        j = j + 1;
    }
}

/**
 * 清空表
 */
function cleanTable() {
    var thead = document.getElementById("head");
    var tbody = document.getElementById("body");
    var tfoot = document.getElementById("foot");
    thead.innerHTML = "";
    tbody.innerHTML = "";
    tfoot.innerHTML = "";
}

/**
 * 获取后台数据并填充表
 * @param weekid
 * @constructor
 */
function FeedTable(weekid) {
    cleanTable();
    $.getJSON("/project/getProject", function (data) {
        FeedTableHead(data);
        $.getJSON("/work/getTableData", {weekid: weekid}, function (data) {
            console.log(data)
            var count = FeedTableContent(data);
            FeedTableFoot(count);
        });
    });
}

/**
 * js的提交input值的方式
 * @constructor
 */
// function GetTable() {
//     var weekid = document.getElementById("weekid").value;
//     FeedTable(weekid);
// }

/**
 * jquary的提交input值的方式
 */
$(function () {
    InitSelect();
    $("#GetTable").click(function () {
        var obj = document.getElementById("weekid");
        var weekid = obj.options[obj.selectedIndex].value;
        FeedTable(weekid);
    });

});


/**
 * 以上两种方法原理是一样的，都是当点击时间发生后，通过查询input的id来获取input的值，然后执行函数
 * 不同的就是语法不同
 */
function download() {
    $('#OATable').tableExport({
        type: 'excel',
        excelstyles: ['border-bottom', 'border-top', 'border-left', 'border-right', 'font-size:20px']
    });
}


