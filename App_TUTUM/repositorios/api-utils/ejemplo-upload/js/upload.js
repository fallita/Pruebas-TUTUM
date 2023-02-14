$(function(){
    $('#upload_btn').click(upload);
});

function upload(){
    var file = $('input[name="upload_file"]').get(0).files[0];

    var formData = new FormData();
    formData.append('file', file);

    $.ajax({
        // url: 'http://localhost:8080/api-utils/UploadFile',
        url: 'https://utils-andanenes.kaizentec.com.mx/api/UploadFile',
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function(response){
            alert('Se subio la imagen con exito!');
            console.log('respuesta', response);
        },
        error: function(response){
            var error = "error";
            if (response.status === 409){
                error = response.responseText;
            }
            alert(error);
        },
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', progress, false);
            } else {
                console.log('Upload progress is not supported.');
            }
            return myXhr;
        }
    });
}

function progress(e) {
    if (e.lengthComputable) {
        $('#progress_percent').text(Math.floor((e.loaded * 100) / e.total));
        $('progress').attr({value:e.loaded,max:e.total});
    }
}