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
			function del(f) {
				var qa_idx = f.qa_idx.value;
				var pwd = f.pwd.value;
				var r_pwd = f.r_pwd.value;
				
				if(pwd==""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(r_pwd!=pwd){
					alert("비밀번호를 다시 입력하세요");
					return;
				}
				
				if(!confirm("정말로 삭제하시겠습니까?")){
					return;
				}
				
				var url="qa_del.do";
				var param = "qa_idx="+qa_idx+"&pwd="+pwd;
				
				sendRequest(url, param, resultFn, "POST");
			}
			
			function resultFn() {
				if(xhr.readyState == 4 && xhr.status == 200){
					var data = xhr.responseText;
					var json = eval(data);
					
					if(json[0].res=='yes'){
						alert("삭제가 완료되었습니다");
						location.href="qa_list.do?page=${ param.page }";
					}
				}
				
			}
			
			function modify(f) {
				var pwd = f.pwd.value;
				var r_pwd = f.r_pwd.value;
				
				if(pwd==""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(pwd != r_pwd){
					alert("비밀번호가 틀립니다");
					return;
				}
				
				location.href= "qa_update_form.do?qa_idx=${param.qa_idx}&page=${param.page}";
			}
			
			function reply(f) {
				var mgr = f.mgr.value;
				
				if(mgr!=1){
					alert("권한이 없습니다");
					return;
				}
				
				location.href="reply_form.do?qa_idx=${param.qa_idx}&page=${param.page}";
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
			<input type="hidden" value="${ vo.qa_idx }" name="qa_idx">
			<input type="hidden" value="${loginVO.pwd }" name="r_pwd">
			<input type="hidden" value="${loginVO.mgr }" name="mgr">			
			
			<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
					 <div class="postCon">
						<div class="postWrap">
                            <h2>Q & A 상세</h2>
							<!-- Form postViewWrap  -->
							<div class="postView">
								<dl>
									<dt>제목</dt>
									<dd>${ vo.title }</dd>
								</dl>
								<dl class="post-info">
									<dt>작성자</dt>
									<dd>${ vo.emp_name }</dd>
								</dl>


								<dl>
									<dt>내용</dt>
									<dd class="post_text">
										<pre>${ vo.content }</pre>
									</dd>
								</dl>
                                <dl>
									<dt>비밀번호</dt>
									<dd><input type="password" name="pwd"></dd>
								</dl>

							</div><!-- End postViewWrap -->
							<div class="btnWrap alignR">
								<div class="floatL">
									<input type="button" class="_btn _grey" onclick="modify(this.form);" value="수정">
									<input type="button" class="_btn _line" onclick="del(this.form);" value="삭제">
								</div>
								<div class="floatR">
									<a href="#none" class="_btn _blue"  onclick="location.href='qa_list.do?page=${ param.page }'">목록으로</a>
                                    <c:if test="${vo.depth lt 1}">
                                        <input type="button" class="_btn _grey" onclick="reply(this.form);" value="답변하기">
                                    </c:if>
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