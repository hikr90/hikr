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
		
		<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
		
		<script type="text/javascript">
			function send(f) {
				var title = f.title.value;
				var content = f.content.value;
				var qa_idx = f.qa_idx.value;
				var pwd = f.pwd.value;
				var r_pwd = f.r_pwd.value;
				
				if(title==""){
					alert("제목을 입력하세요");
					return;
				}
				
				if(content==""){
					alert("내용을 입력하세요");
					return;
				}
				
				if(pwd==""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(r_pwd!=pwd){
					alert("비밀번호를 다시 입력하세요");
					return;
				}
				
				if(!confirm("내용을 수정하시겠습니까?")){
					return;
				}
				
				var url = "qa_update.do";
				var param = "qa_idx="+qa_idx+"&title="+title+"&content="+content;
				
				sendRequest(url, param, resultFn, "POST");
			}
			
			function resultFn() {
				var f = document.ff;
				
				
				if(xhr.readyState == 4 && xhr.status == 200){
					var data = xhr.responseText;
					
					if(data=='yes'){
						alert("수정을 완료하였습니다");
						location.href="qa_list.do";
					}else{
						alert("수정을 실패하였습니다");
					}
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
	
	
	
		<form name="ff">
		
		<input type="hidden" value="${ vo.qa_idx }" name="qa_idx">
			<input type="hidden" value="${ vo.pwd }" name="r_pwd">
			
		
	<article id="_subArticle">
		<div class="_wrap">
			
			<div id="_content">
				
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<div class="postCon">
						<div class="postWrap">
                            <h2>Q & A 수정</h2>
							<!-- Form postWriteWrap  -->
							<div class="postWrite">
								<dl>
									<dt><label for="post-title">제목</label></dt>
									<dd><input type="text" name="title" value="${ vo.title }"></dd>
								</dl>
								<dl>
									<dt><label for="post_text">내용</label></dt>
									<dd class="post_text">
										<textarea id="post_text" name="content">${vo.content }</textarea>
									</dd>
								</dl>
                                <dl>
									<dt><label for="post_text">비밀번호</label></dt>
									<dd><input type="text" name="pwd" id="pwd"></dd>
								</dl>

							</div><!-- End postWriteWrap -->
							<div class="btnWrap alignR">
									<input type="button" class="_btn _grey" onclick="send(this.form);" value="수정">
									<a onclick="location.href='qa_list.do?page=${param.page}'" class="_btn _line">취소</a>
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