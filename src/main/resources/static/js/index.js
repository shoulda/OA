/**
 * Created by Johnny on 17/11/19.
 * @type {string}
 */

//测试数据
// var JsonData = '{"weekId": 1511712000000,"work":[{"projectName": "这是xujin","projectId": 1,"tasks": [{"taskId": 1,"taskName": "这是项目一的任务1","stamp": 1511764230000,"hour": 0.30},{"taskId": 2,"taskName": "这是项目一的任务2","stamp": 1511836200000,"hour": 0.30}]},{"projectName": "这是项目二","projectId": 2,"tasks": [{"taskId": 3,"taskName": "这是项目二的任务，任务Id是3","stamp": 1511940600000,"hour": 0.30},{"taskId": 4,"taskName": "这是项目二的任务，任务Id是4","stamp": 1512023400000,"hour": 1.30}]},{"projectName": "这是项目三","projectId": 3,"tasks": [{"taskId": 5,"stamp": 1511764230000,"taskName": "这是属于项目三的任务，任务id是5","hour": 1.30},{"taskId": 6,"taskName": "这是属于项目三的任务，任务id是6","stamp": 1511836200000,"hour": 1.30}]},{"projectName": "这是项目四","projectId": 4,"tasks": [{"taskId": 7,"stamp": 1511940600000,"taskName": "这是属于项目四的任务，任务Id是7","hour": 2.30},{"taskId": 8,"stamp": 1512023400000,"taskName": "这是属于项目四的任务，任务Id是8","hour": 2.30}]}]}';

/**
 * 初始化表格上面的文字内容
 * @param daysList 最近5个工作日的日期
 */
function initTitle(daysList) {
    // 每周5个工作日，这里硬编码了，daysList的最后一个下标是4
    $("#timeRanger").html(getFormattedTimeSir(daysList[0]) + "-" + getFormattedTimeSir(daysList[4]));
    for (var i = 0; i < daysList.length; i++) {
        var d = daysList[i];
        $("#label_day_" + (i + 1)).html((d.getMonth() + 1) + "/" + d.getDate());
    }
}

/**
 * 用时间Date对象，构造表格前面的字符串
 * @param date
 * @returns {*}
 */
function getFormattedTimeSir(date) {
    if (date instanceof Date) {
        return (date.getMonth() + 1) + "/" + (date.getDate()) + "/" + (date.getFullYear());
    }
    return "Class Type Not Match Date";
}

/**
 * 设置某天的工作时长
 * @param hours 表示工作时长，小时整数
 * @param dayIndexStartFromOne 表示周几，用 1-5 代表 周一到周五
 */
function appendHoursSelect(hours, dayIndexStartFromOne) {
    var hoursTitle = '';
    if (hours > 1) {
        hoursTitle = hours + ' hours';
    } else {
        hoursTitle = hours + 'hour';
    }
    $("#sel_hour_day_" + dayIndexStartFromOne).append("<option value=" + hours + ">" + hoursTitle + "</option>");
}

/**
 * 清空 工作时长 的select组件
 */
function initHoursSelector() {
    var dayIndexStartFromOne = 1;
    while (dayIndexStartFromOne <= 5) {
        $("#sel_hour_day_" + dayIndexStartFromOne++).empty();
    }
}

/**
 * 返回一个时间戳，是每周一的零点时刻，作为weekId给后台查询该周的5个工作日
 * @param daysList 目前所在的星期 的五个工作日的 五个Date对象
 * @returns {number} weekId
 *
 */
function getWeekId(daysList) {
    var weekId = (new Date(daysList[0].setHours(0, 0, 0, 0))).getTime();
    return weekId;
}

function setUpTable(data) {
    if (data['work'].length !== 0) {
        var work = data.work;
        // for (var j=0;j<work[0]) {
        //
        // }
        setUpRowWithData(1, work[0]);

        for (var i = 1; i < work.length; i++) {
            addProject();
            var project = work[i];
            setUpRowWithData(i + 1, project);
        }
    } else {
        initSelectProject(1);
        initSelectTask(1, 1);
    }
}


function setUpRowWithData(rowIndex, projectData) {

    var projectName = projectData['projectName'];
    var projectId = projectData['projectId'];
    var tasks = projectData['tasks'];
    $("#input_project_" + rowIndex).append('<option value="' + projectId + '">' + projectName + '</option>');
    for (var t = 1; t <= tasks.length; t++) {
        if (t > 1) {
            addTask($("#addTask_" + rowIndex));
        }
        var taskName = tasks[t - 1]['taskName'];
        var taskId = tasks[t - 1]['taskId'];
        $('#input_task_' + t + '_' + rowIndex).append('<option value="' + taskId + '">' + taskName + '</option>');
        var dateObj = new Date(parseInt(tasks[t - 1]['stamp']));
        var day = dateObj.getDay();
        var dayId = "#input_day_" + day + "_" + t + "_" + rowIndex;
        var hours = tasks[t - 1]['hour'];
        console.log(hours);
        $(dayId).val(hours);
    }
}

/**
 * 查询最近的5个工作日的项目和任务清空
 * @param weekId 这个参数用来定位某一周，它要传給后台
 */
function initDaysFromWebData(weekId) {
    $.getJSON('/work/selectWorkByScope', {weekId: weekId}, function (data) {
        console.log(data);
        setUpTable(data);
    });
    // setUpTable(JSON.parse(JsonData));
}

/**
 * 填充模拟数据，用于测试
 */
function testDaysSelector() {
    initHoursSelector();
    for (var i = 1; i <= 5; i++) {
        for (var hours = 1; hours <= 8; hours++) {
            appendHoursSelect(hours, i);
        }
    }
}

function init(daysList) {
    initTitle(daysList);
    var weekId = getWeekId(daysList);
    console.log(weekId);
    initDaysFromWebData(weekId);
}


/***
 * 统计目前一共显示了多少个项目，用于在添加新项目的时候，给新项目设置id
 * @returns {number}
 */
function getProjectsCount() {
    var projectNum = 0;
    while ($('#input_project_' + (projectNum + 1)).length > 0) {
        projectNum++;
    }
    return projectNum;
}

/**
 * 添加新项目时，需要构造一个project 行
 */
function getProjectTr(newProjectIndex) {

    var projectTr =
        '<tr id="row_1_' + newProjectIndex + '">' +
        '<td id="td_project_' + newProjectIndex + '" rowspan="2">' +
        '<select class="projectName" id="input_project_' + newProjectIndex + '"></select> ' +
        '</td>' +
        '<td class="taskColumn"> ' +
        '<div class="input-group"> ' +
        '<select class="taskName" id="input_task_1_' + newProjectIndex + '"></select>' +
        '<span  onclick="removeTask(this)" class="removeTask glyphicon glyphicon-remove-circle">' +
        '</span>' +
        '</div> ' +
        '</td>' +
        '<td>' +
        '<input class="day form-control" id="input_day_1_1_' + newProjectIndex + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_2_1_' + newProjectIndex + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_3_1_' + newProjectIndex + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_4_1_' + newProjectIndex + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_5_1_' + newProjectIndex + '" type="text" placeholder="hours"></td> ' +
        '<td id="action_' + newProjectIndex + '" colspan="2" rowspan="2" class="actionColumn"> ' +
        '<button onclick="editRow(this)" class="btn btn-sm btn-warning pull-left">Edit</button> ' +
        '<button onclick="removeProject(this)" class="btn btn-sm btn-danger pull-right">Delete</button> ' +
        '</td>' +
        '</tr>';
    return projectTr;

}

/***
 * 添加新任务 html
 * @param newTaskNum
 * @param projectNum
 * @returns {string}
 */
function getNewTaskTr(newTaskNum, projectNum) {

    var taskTr =
        '<tr id="row_' + newTaskNum + '_' + projectNum + '">' +

        '<td class="taskColumn"> ' +
        '<div class="input-group"> ' +
        '<select class="taskName" id="input_task_' + newTaskNum + '_' + projectNum + '"></select>' +
        '<span onclick="removeTask(this)" class="removeTask glyphicon glyphicon-remove-circle">' +
        '</span>' +
        '</div></td>' +
        '<td>' +
        '<input class="day form-control" id="input_day_1_' + newTaskNum + '_' + projectNum + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_2_' + newTaskNum + '_' + projectNum + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_3_' + newTaskNum + '_' + projectNum + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_4_' + newTaskNum + '_' + projectNum + '" type="text" placeholder="hours"></td> ' +
        '<td>' +
        '<input class="day form-control" id="input_day_5_' + newTaskNum + '_' + projectNum + '" type="text" placeholder="hours"></td> ' +

        '</tr>';

    return taskTr;
}

/***
 * 添加新项目时，需要构造一个 添加任务行，taskNum等于0，因为它是添加按钮
 * @param newProjectIndex
 * @returns {string}
 */
function getAddTaskTr(newProjectIndex) {
    var addTaskTr =
        '<tr id="row_addTask_' + newProjectIndex + '"> ' +
        '<td><span id="addTask_' + newProjectIndex + '" onclick="addTask(this)" class="addTask glyphicon glyphicon-play-circle glyphicon-plus-sign" style="margin-top: 5%;cursor: hand"></span>' +
        '</td> ' +
        '<td colspan="5"></td> ' +
        '</tr>';
    return addTaskTr;
}


/**
 * 添加新项目 时，表格行html代码是字符串拼接起来的
 *
 */
function addProject() {
    var projectNum = getProjectsCount();
    var newProjectIndex = projectNum + 1;

    var newProjectTr = getProjectTr(newProjectIndex);
    var addTaskTr = getAddTaskTr(newProjectIndex);

    $('#row_0').before(newProjectTr);
    $('#row_0').before(addTaskTr);
    initSelectProject(newProjectIndex);
    initSelectTask(1, newProjectIndex);
}

/**
 *
 * 从一个id字符串中获取 projectNum
 *
 * 由于所有的idStr都型如'***_projectNum'，所以可以使用本方法获取projectNum
 * @param idStr
 * @returns {number}
 */
function getProjectNumFromIdStr(idStr) {
    var arr = idStr.split('_');
    var id = Number(arr[arr.length - 1]);
    if (!isNaN(id)) {
        return id;
    } else {
        alert('error from getting row number!');
        return -1;
    }
}

/***
 * 根据项目的个数，获取该项目的任务的个数
 */
function getTaskNumFromProjectNum(projectNum) {
    var taskNum = 1;
    while ($('#input_task_' + taskNum + '_' + projectNum).length > 0) {
        taskNum++;
    }
    //返回的是任务的个数
    return taskNum - 1;
}

/**
 * 添加任务，其实是添加了 任务、周一、周二、...、周五 这几列，在实现中，其实是添加了一行
 * 然后再使得project列和action列的rowSpan，增加一
 * @param obj
 */
function addTask(obj) {
    var tastTr = $(obj).parent().parent();
    var id = $(tastTr).attr('id');
    var projectNum = getProjectNumFromIdStr(id);
    var taskNum = getTaskNumFromProjectNum(projectNum) + 1;
    var newTaskTr = getNewTaskTr(taskNum, projectNum);
    $(tastTr).before(newTaskTr);
    modifyRowSpan(projectNum, 1);
    initSelectTask(taskNum, projectNum);
}

/**
 * 修改 项目列 和  操作列  的 rowSpan
 * @param projectNum
 * @param oneWithFlag  1 或 －1  分别代表自增1和自减1
 */
function modifyRowSpan(projectNum, oneWithFlag) {
    // 调整projectName、action列的rowSpan，要自增1
    var pObj = $('#td_project_' + projectNum);
    var actionObj = $('#action_' + projectNum);
    var pRowSpan = Number(pObj.attr('rowSpan'));
    var actionRowSpan = Number(actionObj.attr('rowSpan'));
    pObj.attr('rowSpan', (pRowSpan + oneWithFlag));
    actionObj.attr('rowSpan', (actionRowSpan + oneWithFlag));
}

/**
 * 删除一个任务
 * @param obj
 */
function removeTask(obj) {
    // 型如 input_task_2_1
    var idStr = $(obj).parent().children('select').attr('id');

    var projectNum = Number(idStr.split('_')[3]);
    var allTaskCount = getTaskNumFromProjectNum(projectNum);
    if (1 == allTaskCount) {//如果
        alert('Can not remove this task! Since You Have to remain one at least!');
        return;
    }
    var currentTaskNum = Number(idStr.split('_')[2]);
    var cond = "[id=row_" + currentTaskNum + '_' + projectNum + "]";
    if (currentTaskNum == 1) {
        alert("you can not remove first task!");
        return;
    }
    $("tr" + cond).remove();
    if (currentTaskNum < allTaskCount) {
        for (var i = currentTaskNum + 1; i <= allTaskCount; i++) {
            var taskTrObj = $("#row_" + i + '_' + projectNum);
            taskTrObj.attr('id', "row_" + (i - 1) + '_' + projectNum);

            var taskInputTd = $('#input_task_' + i + '_' + projectNum);
            taskInputTd.attr('id', "input_task_" + (i - 1) + '_' + projectNum);
        }
    }
    modifyRowSpan(projectNum, -1);

}

/**
 * 删除表格的一行
 * @param obj
 */
function removeProject(obj) {
    var projectNum = getProjectsCount();
    if (projectNum == 1) {
        alert('Can not remove last one!');
        return;
    }
    var id = obj.parentNode.parentNode.id;
    var removeIndex = getProjectNumFromIdStr(id);
    // var removeIndex = Number(id.split('_')[1]);
    if (removeIndex == projectNum) {
        removeProjectByProjectNum(removeIndex);
    } else {
        removeProjectByProjectNum(removeIndex);
        for (var r = removeIndex + 1; r <= projectNum; r++) {
            // 修改之前，先获取该项目一共有多少个任务行
            var taskNum = getTaskNumFromProjectNum(r);

            // 项目行 tr 修改id
            var projectTr = $('#row_1_' + r);
            modifyId(projectTr);

            //任务行 tr 修改id，注意任务行的taskNum从2开始的
            for (var t = 2; t <= taskNum; t++) {
                var taskTr = $('#row_' + t + '_' + r);
                modifyId(taskTr);
            }

            //添加任务的按钮行 也要修改id
            var addTaskTr = $('#row_addTask_' + r);
            modifyId(addTaskTr);
        }
    }
}


function removeProjectByProjectNum(projectNum) {
    // 删除所有 跟该项目有关的任务，按钮等
    var tail = '[id$=' + '_' + projectNum + "]";
    $("tr" + tail).remove();
}


function modifyId(obj) {
    var childNodes = $(obj).children();
    for (var i = 0; i < childNodes.length; i++) {
        modifyId(childNodes[i]);
    }
    var idStr = $(obj).attr('id');
    if (idStr && idStr.length > 0) {
        var projectNum = getProjectNumFromIdStr(idStr);
        if (projectNum > 0) {
            var arr = idStr.split('_');
            var newId = arr.slice(0, arr.length - 1).join('_') + "_" + (projectNum - 1);
            $(obj).attr('id', newId);
        }
    }
}

function editRow() {
    $('select').removeAttr('disabled');
    $('.addTask,.removeTask').show();
}

/**
 * 提交数据 一周的数据，目前，不管如何改动，都会把这一周的数据全部提交
 * @param isSave
 */
function submit(isSave, WeekDaysList) {
    var jsonobj = getInfoToJson(WeekDaysList);
    console.log(jsonobj);
    if (isSave) {
        $.ajax({
            method: 'POST',
            url: '/work/submit',
            contentType: "application/json",
            data: JSON.stringify(jsonobj),
            success: function (e) {
                console.log(e);
                if (e.code == 200) {
                    $('select').attr('disabled', 'disabled');
                    $('.addTask,.removeTask').hide();
                    $('td[id ^= action_]').hide();
                    alert("Submit Success!");
                } else if (e.message) {
                    alert(e.message);
                }
            },
            error: function () {
                alert("Submit Failed!");
            }
        });
    } else {
        $.ajax({
            method: 'POST',
            url: '/work/save',
            contentType: "application/json",
            data: JSON.stringify(jsonobj),
            success: function (e) {
                console.log(e);
                if (e.code == 200) {
                    $('select').attr('disabled', 'disabled');
                    $('.addTask,.removeTask').hide();
                    alert("Save Success!");
                } else if (e.message) {
                    alert(e.message);
                }
            },
            error: function () {
                alert("Save Failed!");
            }
        });
    }

}

/**
 * 获取表单数据转换成json
 * @returns {{}}
 */
function getInfoToJson(WeekDaysList) {
    var projectNum = getProjectsCount();
    console.log(WeekDaysList);
    var weekId = getWeekId(WeekDaysList);
    var json = {};
    var work = [];
    var pid = "input_project_";
    var tid = "input_task_";
    var did = "#input_day_";
    for (var i = 1; i <= projectNum; ++i) {
        var projtext = {};
        projtext["projectName"] = getTextById(pid + i);
        projtext["projectId"] = getIndexById(pid + i);
        var tasktext = [];
        for (var j = 1; j <= getTaskNumFromProjectNum(i); j++) {
            var index = getIndexById(tid + j + '_' + i);
            var text = getTextById(tid + j + '_' + i);
            for (var m = 1; m <= 5; m++) {
                if ($(did + m + '_' + j + '_' + i).val() != "") {
                    var tk = {};
                    tk["taskId"] = index;
                    tk["taskName"] = text;
                    tk["stamp"] = getDayId(WeekDaysList, m);
                    tk["hour"] = $(did + m + '_' + j + '_' + i).val();
                    tasktext.push(tk);
                }
            }
        }
        projtext["tasks"] = tasktext;
        work.push(projtext);
    }

    json["weekId"] = weekId;
    json["work"] = work;
    return json;
}

/**
 * 根据id查询select索引
 * @param id
 * @returns {*|number}
 */
function getIndexById(id) {
    var obj = document.getElementById(id);
    var index = obj.selectedIndex;
    return index + 1;
}

/**
 * 根据id查询select文本
 * @param id
 */
function getTextById(id) {
    var obj = document.getElementById(id);
    var text = obj.options[obj.selectedIndex].text;
    return text;
}

/**
 * 获取指定周 i 的时间戳
 * @param daysList
 * @param i
 * @returns {number}
 */
function getDayId(daysList, i) {
    var dayId = (new Date(daysList[i - 1].setHours(0, 0, 0, 0))).getTime();
    return dayId;
}

/**
 * 清空表格数据
 */
function cleanData() {
    var projectNum = getProjectsCount();
    console.log("------");
    console.log(projectNum);
    for (var i = 1; i <= projectNum; i++) {
        removeProjectByProjectNum(i);
    }
    var newProjectTr = getProjectTr(1);
    var addTaskTr = getAddTaskTr(1);
    $('#row_0').before(newProjectTr);
    $('#row_0').before(addTaskTr);
}

/**
 * 初始化project选择框
 * @param project_id
 */
function initSelectProject(project_id) {
    $.getJSON('/project/getProject', function (data) {
        console.log(data);
        var selid = document.getElementById("input_project_" + project_id);
        for (var i = 0; i < data.length; i++) {
            selid.options.add(new Option(data[i].projectname, data[i].projectid));
        }
    });
}

/**
 * 初始化task选择框
 * @param task_id
 * @param project_id
 */
function initSelectTask(task_id, project_id) {
    $.getJSON('/task/selectAllTask', function (data) {
        console.log(data);
        var selid = document.getElementById("input_task_" + task_id + "_" + project_id);
        for (var i = 0; i < data.length; i++) {
            selid.options.add(new Option(data[i].taskname, data[i].taskid));
        }
    });
}

$(function () {
    var dayApp = new DayApp();
    var daysList = dayApp.getWorkDaysList(new Date());
    init(daysList);

    $("#btnPreWeek").click(function () {
        var preWeekDaysList = dayApp.getPreWorkDaysList();
        cleanData();
        init(preWeekDaysList);
    });

    $("#btnNextWeek").click(function () {
        var nextWeekDaysList = dayApp.getNextWorkDaysList();
        cleanData();
        init(nextWeekDaysList);
    });
    $('#btnAdd').click(function () {
        addProject();
    });

    $('#btnSave').click(function () {
        submit(false, daysList);
    });
    $('#btnSubmit').click(function () {
        submit(true, daysList);
    });
});
