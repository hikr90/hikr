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
<script src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	function send(f) {
		
		var subject = f.subject.value;
		var content = f.content.value;	
		var upload_file = f.upload_file.value;
		
		// 유효성 검사
		if(subject==''){
			alert("제목을 입력하세요.");
			return;
		}
		
		if(content==''){
			alert("내용을 입력해주세요.");
			return;
		}

		if(upload_file==''){
			alert("파일을 선택해주세요.");
			return;
		}
		
		if(!confirm("게시글을 등록하시겠습니까?")){
			return;
		}
		
		f.action = "upload_insert.do";
		f.submit();
		
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
	
		<form method="POST" enctype="multipart/form-data">
		
				<article id="_subArticle">
		<div class="_wrap">
			
			<div id="_content">
				
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
					<div class="postCon">
						<div class="postWrap">
							<!-- Form postWriteWrap  -->
							<h2>자료실 등록</h2>
							<div class="postWrite">
								<dl>
									<dt><label for="post-title">제목</label></dt>
									<dd><input type="text" name="subject" placeholder="제목을 입력하세요."></dd>
								</dl>
                                <dl class="post-info">
									<dt>부서명</dt>
									<dd><input type="text" name="dept_name" value="${ loginVO.dept_name }" readonly="readonly"></dd>
								</dl>
								<dl class="post-info">
									<dt>작성자</dt>
									<dd><input type="text" name="emp_name" value="${ loginVO.emp_name }" readonly="readonly"></dd>
								</dl>
								<dl>
									<dt><label for="post_text">내용</label></dt>
									<dd class="post_text">
										<textarea id="post_text" name="content" placeholder="내용을 입력하세요."></textarea>
									</dd>
								</dl>
                                <dl class="post-info">
									<dt>파일</dt>
									<dd><input type="file" name="upload_file"></dd>
								</dl>

							</div><!-- End postWriteWrap -->
							<div class="btnWrap alignR">
									<input type="button" class="_btn _grey" onclick="send(this.form);" value="등록">
									<a onclick="location.href='upload_list.do?page=${param.page}'" class="_btn _line">취소</a>
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