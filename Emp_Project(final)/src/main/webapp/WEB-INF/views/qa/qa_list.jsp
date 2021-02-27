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
			function send(qa_idx) {
				location.href="qa_view.do?qa_idx="+qa_idx+"&page=${empty param.page ? 1 : param.page}";
			}
			
			function qa_insert_form(mgr) {	
				location.href="qa_insert_form.do";
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
                            <h2>Q & A</h2>
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
											<td>${vo.qa_idx }</td>
                                            <td class="_title">	
                                                <c:forEach begin="1" end="${ vo.depth }">
                                                &nbsp;
                                                </c:forEach>

                                                <!-- 댓글 기호 -->
                                                <c:if test="${ vo.depth ne 0 }">
                                                    ㄴ
                                                </c:if>

                                                <c:if test="${ vo.del_info eq -1}">
                                                    <a href="">
                                                        ${ vo.title }
                                                    </a>
                                                </c:if>

                                                <c:if test="${ vo.del_info eq 0 }">

                                                    <a href="#" onclick="send('${vo.qa_idx}');">${vo.title}</a>
                                                </c:if>
                                            </td>

                                            <td>${vo.dept_name} ${vo.emp_name}</td>
                                            <td>${vo.regdate }</td>
                                            <td>${vo.readhit }</td>
                                        </tr>
                                    </c:forEach>
                                     
                                    <c:if test="${ empty list }">
                                            <tr>
                                                <td align="center" colspan="5">
                                              	      현재 등록된 글이 없습니다.
                                                </td>
                                            </tr>
                                    </c:if>
                                     
                                        	
									</tbody>
								</table>
								<div class="alignRP">
                                        <input type="button" class="_btn _write emp_list" onclick="qa_insert_form('${loginVO.mgr}');" value="등록">
								</div>
								
								<c:if test="${ not empty list }">
				                        <div class="pagingArea">
                                           
                                            <ul class="paging">
                                                <li class="_active">${ pageMenu }</li>
                                            </ul>
                                          
                                        </div><!-- End pagingWrap -->
									</div>
								</c:if>
							</div><!-- End postWrap -->
	
	
						</div><!-- End _contentArea _formArea -->
						
					</div><!-- End _inner -->
	
				</div><!-- End _content -->
			</div><!-- End _wrap -->
		</article>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
