
function showAll() {
    $('#table').attr('class','show');
    $('#project').attr('class','show');
    $('#task').attr('class','show');
}

function tbo() {
    $('#table').attr('class','show');
    $('#project').attr('class','hide');
    $('#task').attr('class','hide');
}

function po() {
    $('#table').attr('class','hide');
    $('#project').attr('class','show');
    $('#task').attr('class','hide');
}

function to() {
    $('#table').attr('class','hide');
    $('#project').attr('class','hide');
    $('#task').attr('class','show');
}