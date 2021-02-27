<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script type="text/javascript">
	function recognize( f ) {
		var vac_idx = f.vac_idx.value;
		f.action = "recognize.do";
		f.submit();
	}
	function reject( f ) {
		var vac_idx = f.vac_idx.value;
		f.action = "reject.do";
		f.submit();
	}
</script>
</head>
<body id="main">

	<form>
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
						<!-- Form srchWrap  -->
						<!-- Form postWrap  -->
						<div class="postWrap">
                            <h2>휴가 관리</h2>
							<div class="postTableWrap">
								<table class="postTable">
									<caption>휴가 관리 페이지입니다.</caption>
									<colgroup>
										<col class="w15per">
										<col class="w15per">
										<col class="w25per">
										<col class="w25per">
										<col class="w20per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">이름</th>
											<th scope="col">부서</th>
											<th scope="col">휴가 시작날짜</th>
											<th scope="col">휴가 마지막날짜</th>
											<th scope="col">비고</th>
                            
										</tr>
                                    </thead>
									<tbody>
                                        <c:forEach var="vo" items="${list}">
										<tr>
											<td>${vo.emp_name}</td>
                                            <td> ${vo.dept_name} </td>
                                            <td>${fn:substring(vo.start,0,10) }</td>
                                            <td>${fn:substring(vo.end,0,10) }</td>
                                            <td><input type="button" class="stb-box-btn1" onclick="recognize(this.form);" value="승인">
                                            <input type="button" class="stb-box-btn1" onclick="reject(this.form);" value="거절"></td>
                                            <input type="hidden" value="${vo.vac_idx}" name="vac_idx"/>
                                        </tr>
                                    </c:forEach>
                                        	
									 <c:if test="${ empty list }">
                                            <tr>
                                                <td align="center" colspan="5">
                                              	      현재 등록된 신청이 없습니다.
                                                </td>
                                            </tr>
                                     </c:if>
									                        
                        
									</tbody>
								</table>
    
							</div>

						  </div><!-- End postWrap -->
        
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
		</form>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>