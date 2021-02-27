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
			function acc(res_idx){
				location.href = "res_choose.do?res_state=승인&&res_idx=" + res_idx;
			}
			
			function dec(res_idx){
				location.href = "res_choose.do?res_state=거절&&res_idx=" + res_idx;
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
						<!-- Form srchWrap  -->
						<!-- Form postWrap  -->
						<div class="postWrap">
                            <h2>회의실 예약 관리</h2>
							<div class="postTableWrap">
								<table class="postTable">
									<caption>회의실 예약 관리 페이지입니다.</caption>
									<colgroup>
										<col class="w10per">
										<col class="w10per">
										<col class="w10per">
										<col class="w10per">
										<col class="w10per">
                                        <col class="w10per">
										<col class="auto">
                                        <col class="w15per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">예약자</th>
											<th scope="col">전화번호</th>
											<th scope="col">회의실</th>
											<th scope="col">예약날짜</th>
											<th scope="col">예약시간</th>
                                            <th scope="col">예약인원</th>
                                            <th scope="col">세부설명</th>
                                            <th scope="col">비고</th>
                            
										</tr>
                                    </thead>
                                    <c:if test="${empty res_manage}">
                                        <tr>
                                            <td colspan="8">승인 대기상태인 예약이 없습니다</td>
                                        </tr>
                                    </c:if>
									<tbody>
                                       <c:forEach var="admin" items="${res_manage}" >
										<tr>
											<td>${admin.res_name}</td>
                                            <td>${admin.res_tel}</td>
                                            <td>${admin.res_ridx}번 회의실</td>
                                            <td>
                                                <fmt:formatDate value="${admin.res_date}" pattern="yyyy-MM-dd"/>
                                            </td>
                                            <td>${admin.res_btime} ~ ${admin.res_etime}</td>
                                            <td>${admin.res_people}명</td>
                                            <td>${admin.res_content}</td>
                                            <td><input type="button" class="stb-box-btn1" onclick="acc(${admin.res_idx});" value="승인">
                                            <input type="button" class="stb-box-btn1" onclick="dec(${admin.res_idx});" value="거절"></td>   
                                        </tr>
                                    </c:forEach>
                                        	
                        
									</tbody>
								</table>
    
							</div>

						  </div><!-- End postWrap -->
        
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>