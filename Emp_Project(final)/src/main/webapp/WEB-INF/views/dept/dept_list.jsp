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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		.dept_con {text-align:right; } 
	</style>
<script src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	function dept_search(f) {
		var dept_name = f.dept_name.value.trim();

		f.action = "dept_list.do";
		f.submit();
	}
	
	function dept_insert(f) {
		var insert_name = prompt("추가하고자하는 부서명을 입력해주세요.");
		
		if(insert_name.trim()=='' || insert_name==null){
			alert("부서명을 정확하게 입력해주세요.");
			return;
		}
		
		var url = "dept_insert.do";
		var param = "dept_name="+encodeURIComponent(insert_name);
		
		sendRequest(url,param,resultFn,"POST");
	}
	
	function resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;
			var json = eval(data);
			
			if(json[0].result=='no'){
				alert("부서 등록에 실패했습니다. 관리자에게 문의해주세요.");
				return;
			}
			
			if(json[0].result=="exists"){
				alert("이미 존재하는 부서입니다.");
				return;
			}
			
			// YES
			alert("부서 등록에 성공하셨습니다.");
			location.href="dept_list.do?page=${param.page}";
		}
	}
	
	// 부서 수정
	function dept_update(dept_name) {
		var update_name = prompt("수정하려는 부서명을 입력해주세요.");

		// 유효성 검사
		if(update_name.trim()=='' || update_name==null){
			alert("부서명을 정확하게 입력해주세요.");
			return;
		}
		
		// 부서 테이블중에서 수정하고자하는 명칭이 있는지 확인하고
		// 수정시에는 DEPT_NAME에 해당하는 부서 직원들의 부서명을 변경시켜줘야한다.
		if(!confirm("부서명 변경시 해당 부서 직원의 부서명이 변경됩니다.\n정말로 부서명을 변경하시겠습니까?")){
			return;
		}
		
		// AJAX로 이동
		var url = "dept_update.do";
		var param = "dept_name="+encodeURIComponent(dept_name)+"&update_name="+encodeURIComponent(update_name);
		
		sendRequest(url,param,update_resultFn,"POST");
	}
	
	function update_resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
			
			var data = xhr.responseText;
			var json = eval(data);
			
			if(json[0].result=='no'){
				alert("문제가 발생했습니다. 관리자에게 문의해주세요.");
				return;
			}

			if(json[0].result=='exists'){
				alert("이미 같은 명칭의 부서가 있습니다.");
				return;
			}
			
			// YES
			alert("부서 변경이 완료되었습니다.");
			location.href='dept_list.do?page=${param.page}';
			
		}
	}
	
	// 부서 삭제 
	function dept_del(dept_name) {
		if(!confirm("부서 삭제시 해당 부서 직원이 삭제됩니다.\n정말로 부서를 삭제하시겠습니까?")){
			return;
		}
		
		// AJAX
		var url = "dept_del.do";
		var param = "dept_name="+encodeURIComponent(dept_name);
		
		sendRequest(url,param,del_resultFn,"POST");
	}
	
	function del_resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
			
			var data = xhr.responseText;
			var json = eval(data);
			
			if(json[0].result=='no'){
				alert("삭제에 실패했습니다. 관리자에게 문의해주세요.");
				return;
			}
			
			alert("삭제에 성공했습니다.");
			location.href='dept_list.do?page=${param.page}';
			
		}
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
							<h2>부서 관리</h2>
                                <div class="f-srchWrap">
						
									<div class="dept_con">
                    	                <div class="srchArea">
                        	                <label class="srcLabel">부서 명</label>
                            	            <input type="text" name="dept_name" class="srch-cdt-text">
                                	    </div>

                                    	<div class="srchArea cdtArea noLabel">
                                        	<input type="button" class="stb-box-btn" value="검색" onclick="dept_search(this.form);">
                                        	<input type="button" class="stb-box-btn" onclick="dept_insert(this.form);" value="부서 등록">
                                    	</div>
									</div>

                                </div><!-- end Form srchWrap  -->
							<div class="postTableWrap">
								<table class="postTable">
									<caption>부서정보 목록페이지 입니다.</caption>
									<colgroup>
										<col class="auto">
										<col class="w15per">
                                        <col class="w15per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">부서명</th>
											<th scope="col">수정</th>
                                            <th scope="col">삭제</th>
										</tr>
									</thead>
									<tbody>
                                        <c:forEach var="vo" items="${ dept_list }">
                                            <tr>
                                                <td>${ vo.dept_name }</td>
                                                <td><input type="button" class="_btn _grey" onclick="dept_update('${vo.dept_name}');" value="수정"></td>
                                                <td><input type="button" class="_btn _line" onclick="dept_del('${vo.dept_name}');" value="삭제"></td>
                                            </tr>
                                        </c:forEach>
                                            <!-- 글이 없는 경우 -->
                                            <c:if test="${ empty dept_list }">
                                                <tr>
                                                    <td align="center" colspan="3">
                                                    	     부서가 존재하지 않습니다.
                                                    </td>
                                                </tr>
                                            </c:if>
									</tbody>
								</table>
                            </div>
						<c:if test="${ not empty dept_list }">	
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