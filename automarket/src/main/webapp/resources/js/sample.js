function previewFile(){

    var preview = document.querySelector('img'); //selects the query named img
    var previousSource = preview.getAttribute("src");
    var file    = document.querySelector('input[type=file]').files[0]; //sames as here
    var reader  = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
    };

    if (file) {
        reader.readAsDataURL(file); //reads the data as a URL
    } else {
        preview.src = previousSource;
    }
}

var formId;
var requestURL;

$(function() {
    $(formId).on('submit', uploadFile);
});

function uploadFile(event) {
    event.stopPropagation();
    event.preventDefault();
    var form = document.getElementById(formId);
    var data = new FormData(form);
    postFilesData(data);
}

function postFilesData(data) {
    $.ajax({
        url :  requestURL,
        type : 'POST',
        data : data,
        cache : false,
        dataType : 'text',
        processData : false,
        contentType : false
    });
}