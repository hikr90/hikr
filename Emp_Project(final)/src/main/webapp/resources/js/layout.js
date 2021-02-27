$(document).ready(function(){
    // top navigation
    $('header #_nav .li_1 .ul_2.hide .a_2').attr('tabindex', '-1');

    $('header #_nav .li_1').on('mouseenter focusin', function () {        
        $(this).addClass('_open').siblings('.li_1').removeClass('_open');
    });
    $('header').on('mouseleave focusout', function () {
        $('header #_nav .li_1').removeClass('_open');
    });

    // sub navigtion
    var getNavActive = $("#_nav").html();
    $('#_sideNav').html(getNavActive);
    var getLogo = $('#_logo').html();
    $('aside h1').html(getLogo);


    // sub pageTitle
    var subPgTitle = $("#_nav .li_2._active .a_2").text();
    $('.pageTitle button').text(subPgTitle);
    $('.pageTitle button').click(function(){
        $('#_subArticle aside').toggle();
    });


    $('.srchBox button').on('click', function(){
        $('.srchBox-sel').removeClass('_on');
        if ($(this).parent('.srchBox').hasClass('_on')) {
            $('.srchBox').removeClass('_on');
            $('.srchBox button').attr('title', '검색구분 열기');
            $('.src_list').slideUp(200);
        } else {
            $('.srchBox').removeClass('_on');
            $(this).parent('.srchBox').addClass('_on');
            $('.src_list').slideUp(200);
            $('.srchBox button').attr('title', '검색구분 열기');
            $(this).attr('title', '검색구분 닫기');
            $(this).parent().find('.src_list').slideDown(200);
        }
    });

    $('.src_list > li > a').on('click', function(){
        var srchText = $(this).text();
        $(this).parent().parent().parent().find('.srcName').text(srchText);
        $(this).parent().parent().slideUp(300);
        $('.srchBox').removeClass('_on');
        $('.srchBox button').attr('title', '검색구분 열기');
        $(this).parent().parent().next('.srchBox button').focus();
        return false;
    });


   // popup Layer
   $('.popShow').click(function(){
        $('.popList').hide();
        $(this).siblings('.popList').show();
        $(this).before('<div class="shade"></div>');
    });
    $('.pop-close').click(function(){
        $('.popList').hide();
        $('.shade').hide();
    });


    // form select
    $('select').wrap('<div class="selStyle">');




});

