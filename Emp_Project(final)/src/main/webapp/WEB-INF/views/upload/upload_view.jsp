<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	function upload_del(f) {
		var r_pwd = f.r_pwd.value;
		var upload_idx = f.upload_idx.value;
		var pwd = f.pwd.value;
		
		// 유효성 검사
		if(pwd==""){
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		if(pwd!=r_pwd){
			alert("비밀번호를 확인해주세요.");
			return;
		}
		
		if(!confirm("게시글을 삭제하시겠습니까?")){
			return;
		}
		
		var url = "upload_del.do";
		var param = "upload_idx="+upload_idx;
		
		sendRequest(url,param,del_resultFn,"POST");
	}
	
	function del_resultFn() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			var json = eval(data);
			
			if(json[0].res=='no'){
				alert("삭제에 실패했습니다. 관리자에게 문의해주세요.");
				return;
			}else{
				alert("삭제되었습니다.");
				location.href='upload_list.do?page=${param.page}';
			}	
		}
	}
	
	function upload_update_form(f) {
		var upload_idx = f.upload_idx.value;
		var r_pwd = f.r_pwd.value;
		var pwd = f.pwd.value;
		
		// 유효성 검사
		if(pwd==""){
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		if(pwd!=r_pwd){
			alert("비밀번호를 확인해주세요.");
			return;
		}
		
		if(!confirm("수정 페이지로 이동하시겠습니까?")){
			return;
		}
		
		location.href="upload_update_form.do?upload_idx="+upload_idx+"&page=${param.page}";
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
		
		<input type="hidden" name="upload_idx" value="${ vo.upload_idx }">
        <input type="hidden" name="r_pwd" value="${ loginVO.pwd }">
            
			<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<div class="postCon">
						<div class="postWrap">
                            <h2>자료실 상세</h2>
                            
							<!-- Form postViewWrap  -->
							<div class="postView">
								<dl>
									<dt>제목</dt>
									<dd>${ vo.subject }</dd>
								</dl>
								<dl class="post-info">
									<dt>작성자</dt>
									<dd>${ vo.dept_name } ${ vo.emp_name }</dd>
								</dl>


								<dl>
									<dt>내용</dt>
									<dd class="post_text">
										<pre>${ vo.content }</pre>
									</dd>
								</dl>
                                
                                <dl class="post-info">
									<dt>파일</dt>
									<dd><a href="download.do?dir=/upload/&filename=${ vo.filename }">${ vo.filename }</a></dd>
								</dl>
                                
                                <dl class="post-info">
									<dt>비밀번호</dt>
									<dd><input type="password" name="pwd"></dd>
								</dl>


							</div><!-- End postViewWrap -->
							<div class="btnWrap alignR">
								<div class="floatL">
									<input type="button" class="_btn _grey" onclick="upload_update_form(this.form);" value="수정">
									<input type="button" class="_btn _line" onclick="upload_del(this.form);" value="삭제">
								</div>
								<div class="floatR">
									<a href="#none" class="_btn _blue" onclick="location.href='upload_list.do?page=${param.page}'">목록</a>
								</div>
							</div>
						</div><!-- End postWrap -->
						</div>

					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
		</form>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
