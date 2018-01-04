/**
 * 工作日类，用于获取五个工作日，以及上周、下周五个工作日的逻辑
 * Created by Johnny on 17/11/19.
 */
var DayApp = function () {
    this.workDaysPerWeek = 5;
    this.secondsPerDay = 24 * 60 * 60 * 1000;
    // 用于保存五个工作日的时间对象
    this.currentWorkDaysList = new Array();
};


/**
 *
 * 根据某个时间，获取它所在的周的周一到周五的五个工作日时间对象
 *（以便于后续根据时间去请求接口，获取该周的五个工作日的全部项目和任务信息）。
 * @param date:传入一个Date时间对象。
 * @returns {Array}:获取date这个时间所在的周 ：周一至周五的五个工作日时间对象。
 */
DayApp.prototype.getWorkDaysList = function (date) {
    var milliseconds = date.getTime();
    var repackDays = date.getDay() - 1;
    if (repackDays == -1) {
        milliseconds += this.secondsPerDay;
    } else {
        while (repackDays) {
            milliseconds -= this.secondsPerDay;
            repackDays--;
        }
    }
    date.setTime(milliseconds);
    for (var i = 0; i < this.workDaysPerWeek; i++) {
        this.currentWorkDaysList[i] = new Date(date);
        milliseconds += this.secondsPerDay;
        date.setTime(milliseconds);
    }
    return this.currentWorkDaysList;
};
/**
 * 获取下一周的周一到周五的五个工作日时间对象。不需要传入任何参数 ，因为DayApp自身会更新时间的起点。
 * @returns {Array}：获取下一周五个工作日时间对象。
 */
DayApp.prototype.getNextWorkDaysList = function () {
    for (var i = 0; i <= this.currentWorkDaysList.length; i++) {
        if (this.currentWorkDaysList[i] instanceof Date) {
            var m = this.currentWorkDaysList[i].getTime();
            for (var w = 1; w <= 7; w++) {
                m += this.secondsPerDay;
            }
            this.currentWorkDaysList[i] = new Date(m);
        }
    }
    return this.currentWorkDaysList;
};
/**
 * 获取上一周的周一到周五的五个工作日时间对象。不需要传入任何参数 ，因为DayApp自身会更新时间的起点。
 * @returns {Array}：获取上一周五个工作日时间对象。
 */
DayApp.prototype.getPreWorkDaysList = function () {
    for (var i = 0; i <= this.currentWorkDaysList.length; i++) {
        if (this.currentWorkDaysList[i] instanceof Date) {
            var m = this.currentWorkDaysList[i].getTime();
            for (var w = 1; w <= 7; w++) {
                m -= this.secondsPerDay;
            }
            this.currentWorkDaysList[i] = new Date(m);
        }
    }
    return this.currentWorkDaysList;

};



