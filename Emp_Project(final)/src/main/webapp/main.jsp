<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/views/include/head.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script src="${pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script>
	function task_del(task_idx) {
		
		var url = "task_del.do";
		var param = "task_idx="+task_idx;
		
		sendRequest(url,param,task_delFn,"POST");
	}
	
	function enterkey(f) {
		if(event.keyCode==13){
			task_insert(f);
			location.href='main.do';
		}
	}
	
	function task_delFn() {
		if(xhr.readyState==4 && xhr.status==200){
			var result = xhr.responseText;
			
			if(result=="no"){
				alert("삭제에 실패했습니다. 관리자에게 문의해주세요.");
			}
			
			location.href="main.do";
		}	 
	}
	
	function task_insert(f) {
		
		var emp_idx = ${loginVO.emp_idx};
		var task = f.task.value;
		
		var url = "task_insert.do";
		var param = "emp_idx="+emp_idx+"&task="+task;
		
		sendRequest(url,param,task_insertFn,"POST");
	}
	
	function task_insertFn() {
		if(xhr.readyState==4 && xhr.status==200){
			var result = xhr.responseText;
			
			if(result=='no'){
				alert("등록에 실패했습니다. 관리자에게 문의해주세요.");
			}
			
			location.href="main.do";
		}
	}
</script>

<body id="main">
	<!-- skip navigation -->
	<div id="skip">
		<h2 class="hidden">Skip Navigation</h2>
		<ul>
		  <li><a href="#_content" onclick="document.getElementById('_content').tabIndex = -1;document.getElementById('_content').focus();return false;">본문 바로가기</a></li>
		  <li><a href="#_nav" onclick="document.getElementById('_nav').tabIndex = -1;document.getElementById('_nav').focus();return false;">주메뉴 바로가기</a></li>
		</ul>
	  </div>
	  <!--// skip navigation -->
	
<%@ include file="WEB-INF/views/include/header.jsp" %>

	<article id="_mainArticle">
		<div id="_content">
			<div class="widgetBox">
				<div class="_wrap">
					<div class="boardWrap">
						<div class="boardArea">
							<h3>공지사항</h3>
							<ul>
							<c:forEach var="list" items="${board_list}" begin="0" end="2">
									<li>
										<a href="view.do?idx=${ list.board_idx }">
											<p>${ list.title }</p>
											<fmt:parseDate value="${list.regdate}" var="dateFmt" pattern="yyyy-MM-dd"/>
											<span class="date"><fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/></span>
										</a>
									</li>
							</c:forEach>
							</ul>

							<a href="list.do" class="board-moreBtn">전체보기</a>
						</div>
					</div>
				</div><!-- End _wrap -->
			</div><!-- End widgetBox -->
		</div><!-- End _content -->
	</article>
    <div class="task-box">
    <div class="task-wrap">
    <article class="task">
    <h2>My-Task</h2>
    
  	<table class="postTable"> 
					<colgroup>
						<col class="auto">
						<col class="w7per">
					</colgroup>
						<thead>
							<tr>
									<th colspan="2" coscope="col">내용</th>
								</tr>
									</thead>
									<c:forEach var="task" items="${ task }">
									<tbody>
										<tr>
											<td>${ task.task }</td>
											<td><img class="delimg" src="resources/images/pop-close.png" onclick="task_del('${task.task_idx}');"></td>
										</tr>
   
									</tbody>
									</c:forEach>
									
				</table>
				<form>
                	<div class="in-task">
								<input type="text" title="task입력" class="task-input" name="task" onkeydown="enterkey(this.form);">
                                <input type="button" class="stb-box-btn" value="입력" onclick="task_insert(this.form);">
					</div>
				</form>
        </article>
        </div>
        </div>


	<footer id="footer">
		<div class="_wrap">
			<div class="main_footer_info">
				<address>서울 마포구 서강로 136 아이비타워 2,3층</address>
				<p class="copy">Copyrights (C) 2020 KOREA IT ACADEMY. ALL rights reserved.</p>
			</div>
		</div>
	</footer>
</body>
</html>
    