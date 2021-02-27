<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-type">
<meta http-equiv="X-UA-Compatible" content="Chrome">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">


<title>Insert title here</title>

	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/font/font.css" media="all">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/common.css" media="all">	
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css" media="all">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/main.css" media="all">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/styForm.css" media="all">
	<!-- vendor css-->
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resourccss/css/vendor/slick.css" media="all">

	<script src="${ pageContext.request.contextPath }/resources/js/jquery-1.12.4.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/vendor/slick.min.js"></script>

	<!-- form script & css-->
	<script src="${ pageContext.request.contextPath }/resources/js/vendor/jquery-ui-1.12.1.js"></script>
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/vendor/jquery-ul-1.12.1.css" media="all">

	<!-- layout script-->
	<script src="${ pageContext.request.contextPath }/resources/js/layout.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/main.js"></script>


<script src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	var id_e_check = false;
	var pwd_e_check = false;
	
	function find_id(f) {
		
		var emp_name = f.id_emp_name.value.trim();
		var id_email = f.id_email.value.trim();
		var mail_pattern = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;

		
		
		if(emp_name==''){
			alert("이름을 입력해주세요.");
			return;
		}

		if(id_email==''){
			alert("이메일을 입력해주세요.");
			return;
		}
		
		if (!mail_pattern.test(id_email)) {
			alert("이메일 형식에 맞지 않습니다.");
			return;
		}
		
		if(id_e_check==false){
			alert("이메일 인증을 해주세요.");
			return;
		}
		
		var url = "find_id.do";
		var param = "emp_name="+encodeURIComponent(emp_name)+"&email="+encodeURIComponent(id_email);
		
		sendRequest(url,param,id_resultFn,"POST");
	}
	
	function id_resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
		       var data = xhr.responseText;
		       var json = eval(data);

		       if(json[0].result=='no_info'){
		    	   alert("존재하지 않는 회원입니다. 정보를 확인해주세요.");
		    	   return;
		       }
		       		      
		       alert("찾으시는 아이디는 "+json[1].id+"입니다.\n로그인을 시도해주세요.");
		       location.href='login.do';
	  	}
	}
	
	function find_pwd(f) {
		
		var id = f.pwd_id.value.trim();
		var pwd_email = f.pwd_email.value.trim();
		var alphanum_pattern = /^[a-zA-Z0-9]+$/;
		var mail_pattern = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
		
		if(id==''){
			alert("아이디를 입력해주세요.");
			return;
		}

		if(pwd_email==''){
			alert("이메일을 입력해주세요.");
			return;
		}
		
		if(!alphanum_pattern.test(id)){
			alert("ID는 숫자와 영문의 조합으로만 검색 가능합니다.");
			return;
		}
		
		if (!mail_pattern.test(pwd_email)) {
			alert("이메일 형식에 맞지 않습니다.");
			return;
		}
		
		if(pwd_e_check==false){
			alert("이메일 인증을 해주세요.");
			return;
		}
		
		var url = "find_pwd.do";
		var param = "id="+encodeURIComponent(id)+"&email="+encodeURIComponent(pwd_email);
		
		sendRequest(url,param,pwd_resultFn,"POST");
	}
	
	function pwd_resultFn() {
		if(xhr.readyState==4 && xhr.status==200){
		       var data = xhr.responseText;
		       var json = eval(data);

		       if(json[0].result=='no_info'){
		    	   alert("존재하지 않는 회원입니다. 정보를 확인해주세요.");
		    	   return;
		       }
		       		      
		       alert("찾으시는 비밀번호는 "+json[1].pwd+"입니다.\n로그인을 시도해주세요.");
		       location.href='login.do';
	  	}
	}

	// id의 인증하기 버튼 누를 시
	function id_email_check(f) {
		var email = f.id_email.value;
		
		var url = "email_check.do";
		var param = "email="+email;
		
		sendRequest(url,param,id_email_resultFn,"POST");
	}
	
	function id_email_resultFn() {
		
		var check_button = document.getElementById("id_check_button");
		var code = document.getElementById("id_code");
		
		code.value = "";
		
		id_check_button.disabled=false;
		
		if( xhr.readyState == 4 && xhr.status==200 ){
		
		 var data = xhr.responseText;
		 var json = eval(data);
		 
		 if(json[0].res!='true'){
			 alert("메일 전송이 실패했습니다. 관리자에게 문의해주세요.");
			 return;
		 }
		 
		 alert("인증 메일이 전송되었습니다. 메일을 확인해주세요.");
		 
		 
		}
	}
	
	function id_code_check(f) {
		var email_code = f.id_code.value;
		var check_button = document.getElementById("id_check_button");
		
		if( xhr.readyState == 4 && xhr.status==200 ){
			
			 var data = xhr.responseText;
			 var json = eval(data);
	
			 if(json[1].joinCode!=email_code){
				 alert("인증번호가 틀렸습니다.");
				 return;
			 }
 			 
			 id_e_check = true;
			 id_check_button.disabled="disabled"; 
			 alert("인증이 완료되었습니다.");
			 
			}
		
	}
	
	// pwd의 인증하기 버튼 누를 시
	function pwd_email_check(f) {
		var email = f.pwd_email.value;
		
		var url = "email_check.do";
		var param = "email="+email;
		
		sendRequest(url,param,pwd_email_resultFn,"POST");
	}
	
	function pwd_email_resultFn() {
		
		var check_button = document.getElementById("pwd_check_button");
		var code = document.getElementById("pwd_code");
		
		code.value = "";
		
		pwd_check_button.disabled=false;
		
		if( xhr.readyState == 4 && xhr.status==200 ){
		
		 var data = xhr.responseText;
		 var json = eval(data);
		 
		 if(json[0].res!='true'){
			 alert("메일 전송이 실패했습니다. 관리자에게 문의해주세요.");
			 return;
		 }
		 
		 alert("인증 메일이 전송되었습니다. 메일을 확인해주세요.");
		 
		 
		}
	}
	
	function pwd_code_check(f) {
		var email_code = f.pwd_code.value;
		var check_button = document.getElementById("pwd_check_button");
		
		if( xhr.readyState == 4 && xhr.status==200 ){
			
			 var data = xhr.responseText;
			 var json = eval(data);
	
			 if(json[1].joinCode!=email_code){
				 alert("인증번호가 틀렸습니다.");
				 return;
			 }
 			 
			 pwd_e_check = true;
			 pwd_check_button.disabled="disabled"; 
			 alert("인증이 완료되었습니다.");
			 
			}
		
	}

	
</script>
</head>
<body id="sub" class="sub-1">

<form>
<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
                        <div class="idWrap">
                            <div class="postWrap">
                                <h2>아이디 찾기</h2>
                                <!-- Form postWriteWrap  -->
                                <div class="postWrite">
                                    <dl>
                                        <dt><label>이름</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이름 입력" name="id_emp_name">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이메일 입력" name="id_email">
                                            <input type="button" class="stb-box-btn email_btn" value="인증번호 발송" onclick="id_email_check(this.form);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일 인증</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="인증번호 입력" name="id_code" id="id_code">
                                            <input type="button" class="stb-box-btn" value="인증하기" id="id_check_button" onclick="id_code_check(this.form);">
                                        </dd>
                                    </dl>
                                </div><!-- End postWriteWrap -->
                                    <div class="btn_center">
                                        <input type="button" class="_btn _grey" value="찾기" onclick="find_id(this.form);">
                                        <input type="button" class="_btn _line" value="취소" onclick="location.href='login.do'">
                                    </div>
                            </div><!-- End postWrap -->
                          </div>      
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
   
    	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
                        <div class="idWrap">
                            <div class="postWrap">
                                <h2>비밀번호 찾기</h2>
                                <!-- Form postWriteWrap  -->
                                <div class="postWrite">
                                    <dl>
                                        <dt><label>ID</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="아이디 입력" name="pwd_id">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이메일 입력" name="pwd_email">
                                            <input type="button" class="stb-box-btn email_btn" value="인증번호 발송" onclick="pwd_email_check(this.form);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일 인증</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="인증번호 입력" name="pwd_code" id="pwd_code">
                                            <input type="button" class="stb-box-btn" value="인증하기" id="pwd_check_button" onclick="pwd_code_check(this.form);">
                                        </dd>
                                    </dl>
                                </div><!-- End postWriteWrap -->
                                    <div class="btn_center">
                                        <input type="button" class="_btn _grey" value="찾기" onclick="find_pwd(this.form);">
                                        <input type="button" class="_btn _line" value="취소" onclick="location.href='login.do'">
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