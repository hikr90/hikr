<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-type">
	<meta http-equiv="X-UA-Compatible" content="Chrome">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

	<title>EMP SYSTEM LOGIN</title>
	
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/font/font.css" media="all">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/login.css" media="all">	
	<style>
	.mgr-btn { display: block; border:none; color: #fff; font-size: 1.2rem; text-align: center; width: 20%; height: 30px; margin: 10px 0; background-color:#214b97; float:right;}
	</style>

	<script src="${ pageContext.request.contextPath }/resources/js/jquery-1.12.4.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/login_layout.js"></script>
<script src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script type="text/javascript">
   function enterkey(f) {
		if(event.keyCode==13){
			login(f);
		}
}
   
   function login(f) {
      
      var id = f.id.value;
      var pwd = f.pwd.value;
      var alphanum_pattern = /^[a-zA-Z0-9]+$/;
      var url = "login_check.do";
      var param = "id="+encodeURIComponent(id)+"&pwd="+encodeURIComponent(pwd);

      if(id==''){
         alert("아이디를 입력하세요.");
         return;
      }
      
      if(!alphanum_pattern.test(id)){
         alert("아이디는 영문과 숫자 조합만 가능합니다.");
         return;
      }
      
      if(pwd==''){
         alert("비밀번호를 입력하세요.");
         return;
      }

      sendRequest(url,param,login_resultFn,"POST");   
   }
   
   function login_resultFn() {
      if(xhr.readyState==4 && xhr.status==200){
        var data = xhr.responseText;
		var json = eval(data);

		if(json[0].result=="no_id"){
			alert("아이디가 없습니다.");
			return;
		}

		if(json[0].result=="no_pwd"){
			alert("비밀번호가 틀렸습니다.");
			return;
		}
		
		alert("로그인 성공");
		location.href="main.do";
		
      }
   }
   
	function find_info_form(f) {
	   location.href = "find_info_form.do";
	}
   
	// 관리자 회원가입
	function mgr_reg() {
		var mgr_input = prompt("관리자 인증번호를 입력해주세요. (#1234)");
		var mgr_key = "#1234";
		
		if(mgr_key!=mgr_input && mgr_input!=null){
			alert("인증번호를 다시 확인해주세요.");
			return;
		}
		
		if(mgr_input==null){
			return;
		}
		
		alert("인증이 완료되었습니다.");
		location.href='mgr_reg.do';
	}
</script>
</head>
<body id="sub">
<!-- skip navigation -->
	<div id="skip">
		<h2 class="hidden">Skip Navigation</h2>
		<ul>
			<li><a href="#_content" onclick="document.getElementById('_content').tabIndex = -1;document.getElementById('_content').focus();return false;">본문 바로가기</a></li>
		</ul>
	</div>
	<!--// skip navigation -->
<form>
      <div class="_body">
		<div class="_tbl">
			<div class="_wrap _sty2">
				<div class="login-wrap">
					<div class="login-area">
						<h3>LOGIN</h3>
						<input type="button" class="mgr-btn" value="관리자 회원가입" onclick="mgr_reg();">
						<input type="text" placeholder="아이디" title="아이디 입력" name="id">
						<input type="password" name="pwd" placeholder="비밀번호" title="비밀번호 입력" onkeydown="enterkey(this.form);">
						<input type="button" class="login-btn" value="로그인" onclick="login(this.form);">
                        <input type="button" class="btn-find-id" value="아이디/비밀번호 찾기" onclick="find_info_form(this.form);">

						<!--<a class="btn-find-id" href="#none" onclick="find_info_form(this.form);">아이디/비밀번호 찾기</a>-->
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<footer id="footer">
		<div class="footerWrap">
			<div class="footer_info">
				<address>서울 마포구 서강로 136 아이비타워 2,3층</address>
				<p class="copy">Copyrights (C) 2020 KOREA IT ACADEMY. ALL rights reserved.</p>
			</div>
		</div>
	</footer>
</body>
</html>