$(document).ready(function(){
    // form 기간 체크
    $('.period-chk').click(function(){
        $(this).addClass('active').siblings().removeClass('active');
    });

    // form :: 방문단리스트 & 예방 전열인사 del
    $('.list-del').click(function(){
        $(this).parent('._fileArea .fileAdd, ._visitArea, .ventPart, .vent-r-div').remove();
    });
    

    // form write :: file del
    $('.file-upload .file-del').click(function(){
        $(this).parent('.file-upload').remove();
    });

 
    // form fileAdd
    var fileTarget = $('.input-file-div .file_input_hidden');
    fileTarget.on('change', function(){  // 값 change
        if(window.FileReader){  // other
            var filename = $(this)[0].files[0].name;
        } 
        else { // ie
            var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
        }
        // 추출한 파일명 삽입
        $(this).parent().parent().find('.file_input_textbox').val(filename);
    });

    
    // form datepicker
    $(".datepicker").datepicker({
        dateFormat: "yy-mm-dd"
    });

    
});