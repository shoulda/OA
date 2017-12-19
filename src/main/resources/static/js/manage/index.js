
function showAll() {

    $('#showOne').attr('class','show');
    $('#showTwo').attr('class','show');
    $('#table').attr('class','show');
    $('#project').attr('class','show');
    $('#task').attr('class','show');
}
function hideAll() {
    $('#showOne').attr('class','hide');
    $('#showTwo').attr('class','hide');
    $('#table').attr('class','hide');
    $('#project').attr('class','hide');
    $('#task').attr('class','hide');
}
function showOne() {
    $('#showOne').attr('class','show');
    $('#showTwo').attr('class','hide');
    $('#table').attr('class','hide');
    $('#project').attr('class','hide');
    $('#task').attr('class','hide');
}
function showTwo() {
    $('#showOne').attr('class','hide');
    $('#showTwo').attr('class','show');
    $('#table').attr('class','hide');
    $('#project').attr('class','hide');
    $('#task').attr('class','hide');
}
function tbo() {

    $('#showOne').attr('class','hide');
    $('#showTwo').attr('class','hide');
    $('#table').attr('class','show');
    $('#project').attr('class','hide');
    $('#task').attr('class','hide');
}

function po() {
    $('#showOne').attr('class','hide');
    $('#showTwo').attr('class','hide');
    $('#table').attr('class','hide');
    $('#project').attr('class','show');
    $('#task').attr('class','hide');
}

function to() {
    $('#showOne').attr('class','hide');
    $('#showTwo').attr('class','hide');
    $('#table').attr('class','hide');
    $('#project').attr('class','hide');
    $('#task').attr('class','show');
}