/**
 * 获取饼状图数据
 * @param userid
 * @param weekid
 * @param weekConut
 * @constructor
 */
function GetData(userid, weekid, weekConut) {
    $.getJSON('/work/selectWorkSeries', {userid: userid, weekid: weekid, weekConut: weekConut}, function (data) {
        console.log(userid + "+++++++++" + weekid + "+++++++++" + weekConut);
        console.log(data);
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

/**
 * 点击触发事件
 */
$(function () {
    $("#btnGet").click(function () {
        var userid = $("#userid").val().trim();
        var weekid = $("#weekid").val().trim();
        var weekConut = $("#weekConut").val().trim();
        console.log(userid + "----------" + weekid + "----------" + weekConut);
        GetData(userid, weekid, weekConut);
    });

});