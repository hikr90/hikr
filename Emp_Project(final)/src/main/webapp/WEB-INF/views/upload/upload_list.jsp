<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		.dept_con {text-align:right; } 
	</style>
<script type="text/javascript">
	function upload_insert_form() {
		location.href="upload_insert_form.do?page=${param.page}";
	}
	
	// 엔터키 함수
	function enterkey(f) {
		if(event.keyCode==13){
			upload_search(f);
		}
	}
	
	function upload_search(f) {
		f.action = "upload_list.do";
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
<form>
		
		<input type="hidden" name="mgr" value="${ loginVO.mgr }">
			<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<!-- Form srchWrap  -->

						<!-- Form postWrap  -->
						<div class="postWrap">
							<h2>자료실</h2>
                            <div class="f-srchWrap">
						
									<div class="dept_con">
                    	                <div class="srchArea">
                    	                	 <label class="srcLabel">제목&nbsp;검색</label>
                            	            <input type="text" name="subject" class="srch-cdt-text">
                                	    </div>

                                    	<div class="srchArea cdtArea noLabel">
                                
                                        	<input type="button" class="stb-box-btn" value="검색" onclick="upload_search(this.form);" onkeydown="enterkey(this.form);">
                                    	</div>
									</div>
							</div>
							<div class="postTableWrap">
								<table class="postTable">
									<caption>자료실 목록페이지 입니다. 번호, 제목, 작성자, 작성일자를 안내합니다.</caption>
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
                                       <c:forEach var="vo" items="${ upload_list }"> 
										<tr>
											<td class="first-td">${ vo.upload_idx }</td>
											<td class="_title">
                                                <a href="upload_view.do?upload_idx=${ vo.upload_idx }&page=${ empty param.page ? 1 : param.page }">${ vo.subject }</a>
                                            </td>
											<td>${ vo.dept_name } ${ vo.emp_name }</td>
											<td>
                                                <fmt:parseDate value="${vo.reg_date}" var="dateFmt" pattern="yyyy-MM-dd"/>
                                            	<fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/>
                                            </td>
											<td>${ vo.readhit }</td>
                                        </tr>
                                        </c:forEach>
                                        <!-- 글이 없는 경우 -->
                                        <c:if test="${ empty upload_list }">
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
							<input type="button" class="_btn _write emp_list" onclick="upload_insert_form(${param.page});" value="등록">
							</div>
						
						<c:if test="${ not empty upload_list }">	
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
