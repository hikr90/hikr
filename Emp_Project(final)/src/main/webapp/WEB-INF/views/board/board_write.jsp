<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/head.jsp" %>

		<script type="text/javascript">
			function send(f) {
				var title = f.title.value;
				var content = f.content.value;			
				
				if(title==""){
					alert("제목을 입력하세요");
				}
				
				if(content==""){
					alert("내용을 입력하세요");
				}
				
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
	
		<form name="ff" method="POST" action="insert.do">
		<input type="hidden" name="id" value="${loginVO.id }">
		<input type="hidden" name="pwd" value="${loginVO.pwd }">
		<input type="hidden" name="mgr" value="${loginVO.mgr }">
		<input type="hidden" name="dept_name" value="${loginVO.dept_name}">
		
				<article id="_subArticle">
		<div class="_wrap">
			
			<div id="_content">
				
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
					<div class="postCon">
						<div class="postWrap">
							<!-- Form postWriteWrap  -->
							<h2>공지사항 등록</h2>
							<div class="postWrite">
								<dl>
									<dt><label for="post-title">제목</label></dt>
									<dd><input type="text" name="title" placeholder="제목을 입력하세요."></dd>
								</dl>
								<dl class="post-info">
									<dt>작성자</dt>
									<dd><input id="noticeName" name="emp_name" value="${ loginVO.emp_name }" readonly="readonly"></dd>
								</dl>
								<dl>
									<dt><label for="post_text">내용</label></dt>
									<dd class="post_text">
										<textarea id="post_text" name="content" placeholder="내용을 입력하세요."></textarea>
									</dd>
								</dl>

							</div><!-- End postWriteWrap -->
							<div class="btnWrap alignR">
									<input type="button" class="_btn _grey" onclick="send(this.form);" value="등록">
									<a onclick="location.href='list.do?page=${param.page}'" class="_btn _line">취소</a>
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