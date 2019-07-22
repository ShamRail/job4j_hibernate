var lastId = 0;
var elems = [];

function hideOrShow(chkbx) {
    var start = chkbx.checked;
    if (start === true) {
        hide();
    } else {
        show();
    }
}

function hide() {
    for (var i = 0; i < elems.length; i++) {
        if (elems[i].hdn === true) {
            var trId = "tr-" + elems[i].id;
            document.getElementById(trId).style.display = "none";
        }
    }
}

function show() {
    for (var i = 0; i < elems.length; i++) {
        if (elems[i].hdn === true) {
            var trId = "tr-" + elems[i].id;
            document.getElementById(trId).style.display = "table-row";
        }
    }
}

$(document).ready(function () {
    $("#add").click(function () {
        addTask();
    });
});

function addTask() {
    var description = $("#desc").val();
    var d = new Date();
    var dt = parseDate(d);
    var row = "<tr id='tr-" + (++lastId) + "'>" +
        "<td>" + "<input id='inp-"+ lastId +"' type='checkbox' "
            + "onchange=\"handleCheckbox(this)\"></td>" +
        "<td>" + lastId + "</td>" +
        "<td>" + description + "</td>" +
        "<td>" + dt + "</td>" +
        "</tr>";
    $("tbody").append(row);
    var item = {
        id: lastId,
        description: description,
        creating_time: d.getTime(),
        done: false
    };
    elems.push(
        {id: lastId, hdn: false}
    );
    sendItem(item);
}

function sendItem(item) {
    var json = JSON.stringify(item);
    alert(json);
    $.ajax({
        url: '/todolist/add',
        dataType: 'json',
        type: 'post',
        contentType: 'application/json',
        data: json,
        processData: false
    });
}

$(document).ready(function() {
    $.get('/todolist/list', function(data) {
        var i = JSON.parse(data);
        for (var ind = 0; ind < i.items.length; ind++) {
            var id = i.items[ind].id;
            var description = i.items[ind].description;
            var date = new Date(i.items[ind].creatingTime);
            var done = i.items[ind].done;
            var row = "<tr id='tr-" + id + "'>" +
                        "<td>" + "<input id='inp-" + id + "' type='checkbox' class='chkbx'" + (done == true ? " checked" : "")
                                + " onchange=\"handleCheckbox(this)\"></td>" +
                        "<td>" + id + "</td>" +
                        "<td>" + description + "</td>" +
                        "<td>" + parseDate(date) + "</td>" +
                       "</tr>"
            ;
            lastId = Math.max(lastId, id);
            elems.push(
                {id: id, hdn: done}
            );
            $("tbody").append(row);
        }
    });
});

function handleCheckbox(chkbx) {
    var id = chkbx.getAttribute('id').split("-")[1];
    var checked = chkbx.checked;
    var sendingData = "" + id + " " + checked;
    $.ajax({
        url: '/todolist/update',
        data: sendingData,
        dataType: 'text',
        type: 'post',
        success: function () {
            console.log("item(" + id + ") is updated");
        },
        fail: function () {
            console.log("updating failed");
        }
    });
    for (var i = 0; i < elems.length; i++) {
        if (elems[i].id == id) {
            elems[i].hdn = checked;
        }
    }
}

function parseDate(d) {
    var yyyy = d.getFullYear();
    var m = d.getMonth() + 1;
    var mm = (m <= 9) ? ("0" + m) : "" + m;
    var hh = d.getHours();
    var mts = d.getMinutes();
    return "" + yyyy + "." + mm + "." + d.getDate() + " " + hh + ":" + mts;
}