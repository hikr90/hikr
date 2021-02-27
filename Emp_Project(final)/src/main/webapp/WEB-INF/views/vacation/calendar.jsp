<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<link
	href='${ pageContext.request.contextPath }/resources/packages/core/main.css'
	rel='stylesheet' />
<link
	href='${ pageContext.request.contextPath }/resources/packages/daygrid/main.css'
	rel='stylesheet' />
<link
	href='${ pageContext.request.contextPath }/resources/packages/timegrid/main.css'
	rel='stylesheet' />
<link
	href='${ pageContext.request.contextPath }/resources/packages/list/main.css'
	rel='stylesheet' />
<script
	src='${ pageContext.request.contextPath }/resources/packages/core/main.js'></script>
<script
	src='${ pageContext.request.contextPath }/resources/packages/interaction/main.js'></script>
<script
	src='${ pageContext.request.contextPath }/resources/packages/daygrid/main.js'></script>
<script
	src='${ pageContext.request.contextPath }/resources/packages/timegrid/main.js'></script>
<script
	src='${ pageContext.request.contextPath }/resources/packages/list/main.js'></script>
<style type="text/css">

/* 달력에 이벤트 입력시간 없애줌 */
.fc-time{
   display : none;
}
</style>

<script>

 console.log('${list}')

 console.log('${jsonList}')



 var list = '${list}';

 var arrayList = new Array('${list}');

 
 console.log(list);

 console.log(arrayList);



 var json = JSON.parse('${jsonList}');

 console.log(json);

</script>

<script>

var date = new Date();

var json = JSON.parse('${jsonList}');
    
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      defaultDate: date,
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      events: json
    });

    calendar.render();
  });

</script>
<!-- <style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
} -->
<!-- </style> -->
<body id="main">
		<div id="skip">
		<h2 class="hidden">Skip Navigation</h2>
		<ul>
		  <li><a href="#sub_content" onclick="document.getElementById('sub_content').tabIndex = -1;document.getElementById('sub_content').focus();return false;">본문 바로가기</a></li>
		  <li><a href="#_sideNav" onclick="document.getElementById('_sideNav').tabIndex = -1;document.getElementById('_sideNav').focus();return false;">메뉴 바로가기</a></li>
		</ul>
	</div>
	<!--// skip navigation -->
	
	<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<form>
	
	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
                        <div class="postCon">
						<div class="postWrap">
	
							<div id='calendar'></div>
						</div><!-- End postWriteWrap -->
						</div><!-- End form-innerArea -->

					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
	</form>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>