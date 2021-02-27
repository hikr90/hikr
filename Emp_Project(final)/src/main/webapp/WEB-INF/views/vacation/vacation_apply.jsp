<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script>
	
	function apply( f ) {
		
		//휴가 중복 체크
		<c:forEach var="vo" items="${list}">
			if (${loginVO.emp_idx} == ${vo.emp_idx}) {
				if (${vo.check_vac} == 0) {
					alert("이미 휴가 신청이 되어있습니다");
					return;
				}
			} 
		</c:forEach>
		
		var start_date = f.start.value;
		var end_date = f.end.value;
		
		<c:forEach var="vo" items="${list}">
		if (${loginVO.emp_idx} == ${vo.emp_idx} && ${vo.check_vac} != 2) {
			if ( ("${vo.start}" >= start_date && "${vo.start}" <= end_date ) ||
					start_date >= "${vo.start}" && start_date <= "${vo.end}")  {
				alert("휴가 날짜가 겹치는 구간이 있습니다.");
				return;
				}
			} 
		</c:forEach>
		
		
		//유효성 체크
		if( start_date == ""){
			alert("시작 날짜를 등록하세요")
			return;
		}
		
		if( end_date == ""){
			alert("마지막 날짜를 등록하세요")
			return;
		}
		
		/* 시작날짜가 마지막날짜 보다 큰경우 */
		if(end_date < start_date){
			alert("시작 날짜가 마지막 날짜보다 나중입니다")
			return;
		}
		
		alert(start_date + "일부터 " + end_date + "일까지 휴가신청 되었습니다.");
		
		f.action="vacation_insert.do";
		f.submit();
	}
	
	
</script>
</head>
	<body id="main">
	<!-- skip navigation -->
	<div id="skip">
		<h2 class="hidden">Skip Navigation</h2>
		<ul>
		  <li><a href="#sub_content" onclick="document.getElementById('sub_content').tabIndex = -1;document.getElementById('sub_content').focus();return false;">본문 바로가기</a></li>
		  <li><a href="#_sideNav" onclick="document.getElementById('_sideNav').tabIndex = -1;document.getElementById('_sideNav').focus();return false;">메뉴 바로가기</a></li>
		</ul>
	</div>
	<!--// skip navigation -->
	
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
					
						<!-- Form postWrap  -->
                        <div class="postCon" style="padding-bottom:30px;">
                        <form>
                                 <h2>휴가 신청</h2>
                                <div class="postWrite" >
								<dl>
									<dt><label for="post-title">이름</label></dt>
									<dd>${loginVO.emp_name}</dd>
								</dl>
                                <dl>
									<dt><label for="post-title">부서</label></dt>
									<dd>${loginVO.dept_name}</dd>
								</dl>
                                <dl>
									<dt><label for="post-title">휴가 시작날짜</label></dt>
									<dd>
                                        <input type="date" name="start" id="res_date_s">
                                    </dd>
								</dl>
                                <dl>
									<dt><label for="post-title">휴가 종료날짜</label></dt>
									<dd><input type="date" name="end" id="res_date_s"></dd>
								</dl>
							</div><!-- End postWriteWrap -->
                        		<div class="alignC">
									<input type="button" class="_btn _grey" onclick="apply(this.form);" value="신청하기">
                                </div>    

							</form>
						 <div class="postWrap">
						 <h2>휴가 확인</h2>
							<div class="postTableWrap">
								<table class="postTable">
									<colgroup>
										<col class="w7per">
										<col class="auto">
										<col class="auto">
										<col class="w15per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">번호</th>
											<th scope="col">휴가 시작날짜</th>
											<th scope="col">휴가 종료날짜</th>
											<th scope="col">승인여부</th>
										</tr>
                                    </thead>
									<tbody>
                                    <c:forEach var="v" items="${list}">
										<tr>
											<td>${v.vac_idx}</td>
                                            <td>
                                                <fmt:parseDate value="${v.start}" var="s_dateFmt" pattern="yyyy-MM-dd"/>
					                            <fmt:formatDate value="${s_dateFmt}"  pattern="yyyy-MM-dd"/>
                                            </td>
                                            <td>
                                               <fmt:parseDate value="${v.end}" var="e_dateFmt" pattern="yyyy-MM-dd"/>
					                           <fmt:formatDate value="${e_dateFmt}"  pattern="yyyy-MM-dd"/>
                                            </td>
                                            <c:if test="${v.check_vac eq 0}">
                                                <td>대기</td>
                                            </c:if>
                                            <c:if test="${v.check_vac eq 1}">
                                                <td>승인</td>
                                            </c:if>
                                            <c:if test="${v.check_vac eq 2}">
                                                <td>거절</td>
                                            </c:if>

                                    </c:forEach>       
									
									 <c:if test="${ empty list }">
                                            <tr>
                                                <td align="center" colspan="4">
                                              	      현재 등록된 신청이 없습니다.
                                                </td>
                                            </tr>
                                     </c:if>
									
									
									</tbody>
								</table>
    						
							</div>
							
							<c:if test="${ not empty list }">
							<div class="pagingArea">
								<!-- <a class="pageBtn _prev" href="#none">이전 페이지로 이동</a> -->
								<ul class="paging"><!-- ${ pageMenu } -->
									<li class="_active">${ pageMenu }</li>
								</ul>
								<!-- <a class="pageBtn _next" href="#none">다음 페이지로 이동</a>-->
							</div><!-- End pagingWrap -->
							</c:if>
							
							
						  </div><!-- End postWrap -->
						  </div>
                        </div>
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
	
	</article>
	
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>