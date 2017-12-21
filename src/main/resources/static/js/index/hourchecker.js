/**
 * 判断一个字符串是否是浮点数
 * @param val
 * @returns {boolean}
 */
function isFloat(val) {
    var floatRegex = /^-?\d+(?:[.,]\d*?)?$/;
    if (!floatRegex.test(val))
        return false;
    val = parseFloat(val);
    if (isNaN(val))
        return false;
    return true;
}

/**
 * 从一个字符串中获取小时数
 * @param hoursStr
 * @returns {number}
 */
function getHours(hoursStr) {
    if (isFloat(hoursStr)) {
        return parseFloat(hoursStr);
    } else {
        return -1;
    }
}

/**
 * 一天不能超过8小时,  一周不能超过40个小时
 * @returns {number}  0 代表通过验证，-1代表某天超过了8个小时，-2 代表这个周的全部工作小时数超过40个小时
 */
function checkHours() {
    var projectNum = getProjectsCount();
    var weekHoursArr = [0,0,0,0,0];
    for (var p = 1; p <= projectNum; p++) {
        var tasksNum = getTaskNumFromProjectNum(p);
        for (var t = 1; t <= tasksNum; t++) {
            for (var d = 1; d <= 5; d++) {
                var hours = getHours($("#input_day_" + d + "_" + t + "_" + p).val().trim());
                if (hours >= 0) {
                    weekHoursArr[d - 1] += hours;
                }
            }
        }
    }

    var weekHoursSum = 0;
    for (var day = 1; day <= 5; day++) {
        if (weekHoursArr[day - 1] > 8) {
            return -1;
        }
        weekHoursSum += weekHoursSum[day - 1];
    }
    if(weekHoursSum>40){
        return -2;
    }
    return 0;
}

