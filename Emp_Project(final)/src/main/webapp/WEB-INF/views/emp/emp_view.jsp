<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	function emp_del(f) {
		var emp_idx = f.emp_idx.value;
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}
		
		var url = "emp_del.do";
		var param = "emp_idx="+emp_idx;
		
		sendRequest(url,param,resultFn,"POST");
	}
	
	function resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
			var result = xhr.responseText;
			
			if(result=="no"){
				alert("삭제에 실패했습니다. 관리자에게 문의해주세요.");
				return;
			}
			
			alert("삭제에 성공했습니다.");
			location.href="emp_list.do?page=${param.page}";
		}
	}
</script>
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
	
	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
                        <div class="postCon">
						<div class="postWrap">
							<!-- Form postWriteWrap  -->
                            <h2>사원 정보</h2>
                            <input type="hidden" name="emp_idx" value="${ vo.emp_idx }">
							<div class="postView">
                                
								<dl>
									<dt>부서</dt>
									<dd>${ vo.dept_name }</dd>
								</dl>
								<dl>
									<dt>이름</dt>
									<dd>${ vo.emp_name }</dd>
								</dl>				
								<dl>
									<dt>성별</dt>
									<dd>${ vo.gender }</dd>
								</dl>
								<dl>
									<dt>전화번호</dt>
									<dd>${ vo.phone }</dd>
								</dl>
								<dl>
									<dt>주민등록번호</dt>
									<dd>${ vo.reg_num }</dd>
								</dl>
								<dl>
									<dt>이메일</dt>
									<dd>${ vo.email }</dd>
								</dl>
								<dl>
									<dt>주소</dt>
									<dd>${ vo.addr } ${ vo.addr_info }</dd>
								</dl>
								<dl>
									<dt>입사일</dt>
									<dd><fmt:formatDate value="${ vo.hire_date }" pattern="yyyy-MM-dd"/></dd>
								</dl>
								<dl>
									<dt>아이디</dt>
									<dd>${ vo.id }</dd>
								</dl>
                                <dl>
									<dt>비밀번호</dt>
									<dd>${ vo.pwd }</dd>
								</dl>
                                <dl>
									<dt>관리자 여부</dt>
									<dd>
									<c:if test="${ vo.mgr eq 1}">
										예
									</c:if>
									<c:if test="${ vo.mgr ne 1}">
										아니오
									</c:if>
									</dd>
								</dl>

							</div><!-- End postWriteWrap -->
							<div class="btnWrap alignR">
								<div class="floatL">
									<input type="button" class="_btn _grey" value="삭제"  onclick="emp_del(this.form);">
									<input type="button" onclick="location.href='emp_update_form.do?emp_idx=${vo.emp_idx}&page=${param.page}'" class="_btn _line" value="수정">
								</div>
								<div class="floatR">
									<input type="button" class="_btn _blue" value="취소" onclick="location.href='emp_list.do?page=${param.page}'">
								</div>
							</div>
                            </div>
						</div><!-- End form-innerArea -->

					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>


	</form>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>