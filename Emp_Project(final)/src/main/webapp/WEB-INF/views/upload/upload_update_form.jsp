<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function update(f) {
		var upload_idx = f.upload_idx.value;
		var subject = f.subject.value;
		var content = f.content.value;	
		var upload_file = f.upload_file.value;
		var page = f.page.value;
		
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
		
		if(!confirm("수정하시겠습니까?")){
			return;
		}
		
		// 수정
		f.action = "upload_update.do";
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
		<form method="POST" enctype="multipart/form-data">
		
		
                <input type="hidden" name="upload_idx" value="${ vo.upload_idx }">
		        <input type="hidden" name="page" value="${ param.page }">
				
				<article id="_subArticle">
		<div class="_wrap">
			
			<div id="_content">
				
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<div class="postCon">
						<div class="postWrap">
                            <h2>자료실 수정</h2>
							<!-- Form postWriteWrap  -->
							<div class="postWrite">
								<dl>
									<dt><label for="post-title">제목</label></dt>
									<dd><input type="text" name="subject" value="${ vo.subject }"></dd>
								</dl>
								<dl class="post-info">
									<dt>작성자</dt>
									<dd>
                                        <input type="hidden" name="dept_name" value="${ vo.dept_name }">
                                        <input type="hidden" name="emp_name" value="${ vo.emp_name }">
				                        <input type="text" value="${ vo.dept_name } ${ vo.emp_name }" readonly="readonly">
                                    </dd>
								</dl>
								<dl>
									<dt><label for="post_text">내용</label></dt>
									<dd class="post_text">
										<textarea id="post_text" name="content">${ vo.content }</textarea>
									</dd>
								</dl>
                                <dl>
									<dt><label for="post-title">파일</label></dt>
									<dd><input type="file" name="upload_file"></dd>
								</dl>

							</div><!-- End postWriteWrap -->
							<div class="btnWrap alignR">
									<input type="button" class="_btn _grey" onclick="update(this.form);" value="등록">
									<a onclick="location.href='upload_view.do?upload_idx=${vo.upload_idx}'" class="_btn _line">취소</a>
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