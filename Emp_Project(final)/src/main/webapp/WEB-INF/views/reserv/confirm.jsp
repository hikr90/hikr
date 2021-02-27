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

		<script type="text/javascript">
			function del(res_idx, page, state){
				
				if(state!="대기"){
					alert("이미 승인/거절 처리된 예약입니다.");
					return;
				}
				
				location.href="res_del.do?page="+page+"&res_idx=" + res_idx;
			}
		</script>
	</head>
	
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
			<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<!-- Form srchWrap  -->
						<!-- Form postWrap  -->
						<div class="postWrap">
                            <h2>회의실 예약 확인</h2>
							<div class="postTableWrap">
								<table class="postTable">
									<caption>시설물 예약 페이지입니다.</caption>
									<colgroup>
										<col class="w10per">
										<col class="w10per">
										<col class="w10per">
										<col class="w10per">
										<col class="auto">
                                        <col class="w10per">
										<col class="w10per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">회의실 번호</th>
											<th scope="col">예약날짜</th>
											<th scope="col">예약시간</th>
											<th scope="col">예약인원</th>
											<th scope="col">내용</th>
                                            <th scope="col">승인여부</th>
                                            <th scope="col">비고</th>
										</tr>
                                    </thead>
                                        
                                        <c:if test="${empty res_user}">
                                        <tr>
                                            <td colspan="7" align="center">등록한 예약이 없습니다</td>
                                        </tr>
                                        </c:if>
									
									<tbody>
                                      <c:forEach var="user" items="${res_user}">
										<tr>
											<td>${user.res_ridx}번 회의실</td>
                                            <td>
                                                <fmt:formatDate value="${user.res_date}" pattern="yyyy-MM-dd"/>
                                            </td>
                                            <td>${user.res_btime} ~ ${user.res_etime}</td>
                                            <td>${user.res_people}명</td>
                                            <td>${user.res_content}</td>
                                            <td>${user.res_state}</td>
                                            <td><input type="button" class="stb-box-btn1" onclick='del(${user.res_idx}, ${ empty param.page ? 1 : param.page}, "${ user.res_state }");' value="예약취소"></td>
                                        </tr>
                                    </c:forEach>
									</tbody>
								</table>
								</div>

									<c:if test="${ not empty res_user }">
										<div class="pagingArea">
										<!-- <a class="pageBtn _prev" href="#none">이전 페이지로 이동</a> -->
										<ul class="paging"><!-- ${ pageMenu } -->
											<li class="_active">${ pageMenu }</li>
										</ul>
										<!-- <a class="pageBtn _next" href="#none">다음 페이지로 이동</a> -->
										</div><!-- End pagingWrap -->
									</c:if> 

						  </div><!-- End postWrap -->
                        
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>