/**
 * 获取饼状图数据
 * @param userid
 * @param weekid
 * @param weekConut
 * @constructor
 */
function GetData(userid, weekid, weekConut) {
    $.getJSON('/work/selectWorkSeries', {userid: userid, weekid: weekid, weekConut: weekConut}, function (data) {
        testOne(data);
    })
}

/**
 * 饼状图框架
 * @param data
 */
function testOne(data) {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Someone Work In a Week Chart'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                states: {
                    hover: {
                        enabled: false
                    }
                },
                slicedOffset: 20, // 突出间距
                point: { // 每个扇区是数据点对象，所以事件应该写在 point 下面
                    events: {
                        // 鼠标滑过是，突出当前扇区
                        mouseOver: function () {
                            this.slice();
                        },
                        // 鼠标移出时，收回突出显示
                        mouseOut: function () {
                            this.slice();
                        },
                        // 默认是点击突出，这里屏蔽掉
                        click: function () {
                            return false;
                        }
                    }
                }
            }
        },
        series: [data]
    });
}

function InitWeekSelect() {
    $.getJSON("work/selectAllWeekID", function (data) {
        var select = document.getElementById("weekid");
        for (var i = 1; i <= data.length; i++) {
            select.options.add(new Option("第" + i + "周", data[i - 1]));
        }
        $("#weekid").val(data[i - 2]);
    })
}

function InitUserSelect() {
    $.getJSON("user/getAllUser", function (data) {
        var select = document.getElementById("userid");
        for (var i = 1; i <= data.length; i++) {
            select.options.add(new Option(i + "." + data[i - 1]["displayname"], data[i - 1]["userid"]));
        }
    })
}

/**
 * 点击触发事件
 */
$(function () {
    InitUserSelect();
    InitWeekSelect();
    $("#btnGet").click(function () {
        var userobj = document.getElementById("userid");
        var userid = userobj.options[userobj.selectedIndex].value;
        var weekobj = document.getElementById("weekid");
        var weekid = weekobj.options[weekobj.selectedIndex].value;
        var weekCount = weekobj.options[weekobj.selectedIndex].text;
        GetData(userid, weekid, weekCount);
    });
});
//