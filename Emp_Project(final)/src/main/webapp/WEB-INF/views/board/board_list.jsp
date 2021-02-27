<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

		
		<script type="text/javascript">
			function send(idx) {
				var f = document.ff;
				var mgr = f.mgr.value;
				location.href="view.do?idx="+idx+"&page=${param.page}";
			}
			
			function send_mgr() {
				var f = document.ff;
				var mgr = f.mgr.value;
				
				
				if(mgr != 1){
					alert("권한이 없습니다");
					return;
				}
				
				location.href="insert_form.do?page=${param.page}";
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
	
		<form name="ff" method="POST">
		
		<input type="hidden" name="mgr" value="${ loginVO.mgr }">
			<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<!-- Form srchWrap  -->

						<!-- Form postWrap  -->
						<div class="postWrap">
							<h2>공지사항</h2>
							<div class="postTableWrap">
								<table class="postTable">
									<caption>공지사항 목록페이지 입니다. 번호, 제목, 작성자, 작성일자를 안내합니다.</caption>
									<colgroup>
										<col class="w7per">
										<col class="auto">
										<col class="w15per">
										<col class="w15per">
										<col class="w7per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">번호</th>
											<th scope="col">제목</th>
											<th scope="col">작성자</th>
											<th scope="col">등록일자</th>
											<th scope="col">조회수</th>
										</tr>
									</thead>
									<tbody>
                                       <c:forEach var="vo" items="${ list }"> 
										<tr>
											<td class="first-td">${vo.board_idx }</td>
											<td class="_title"><a href="#none" onclick="send('${vo.board_idx}');">${vo.title }</a></td>
											<td>${vo.dept_name} ${vo.emp_name }</td>
											<td>${vo.regdate }</td>
											<td>${vo.readhit }</td>
                                        </tr>
                                        </c:forEach>
                                        <!-- 글이 없는 경우 -->
                                        <c:if test="${ empty list }">
                                            <tr>
                                                <td align="center" colspan="5">
                                              	      현재 등록된 글이 없습니다.
                                                </td>
                                            </tr>
                                        </c:if>
                                        
                                        
									</tbody>
								</table>
							</div>
							<div class="alignRP">
							<input type="button" class="_btn _write emp_list" onclick="send_mgr();" value="등록">
							</div>
						
						<c:if test="${ not empty list }">	
						<div class="pagingArea">
								<!-- <a class="pageBtn _prev" href="#none">이전 페이지로 이동</a> -->
								<ul class="paging"><!-- ${ pageMenu } -->
									<li class="_active">${ pageMenu }</li>
								</ul>
								<!-- <a class="pageBtn _next" href="#none">다음 페이지로 이동</a>
 -->
							</div><!-- End pagingWrap -->
						</c:if>	

						</div><!-- End postWrap -->


					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
		</form>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
