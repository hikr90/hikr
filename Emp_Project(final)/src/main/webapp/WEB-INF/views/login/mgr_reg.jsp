<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script
	src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	var id_check = false;
	var addr_check = false;
	
	// 등록
	function mgr_register(f) {

		// 유효성 검사
		var dept_name = f.dept_name.value;
		var emp_name = f.emp_name.value;
		var gender = f.gender.value;
		var reg_num = f.reg_num.value.trim();
		var phone = f.phone.value.trim();
		var email = f.email.value.trim();
		var id = f.id.value.trim();
		var pwd = f.pwd.value.trim();
		var name_pattern = /^[가-힣a-zA-Z]+$/;
		var reg_pattern = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
		var phone_pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		var alphanum_pattern = /^[a-zA-Z0-9]+$/;
		var addr = f.addr_api.value;
		var addr_info = f.addr_info.value;
		var mail_pattern = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
		var addr_api = document.getElementById("addr_api").value;
		var c_pwd = f.c_pwd.value;	
		
		// 유효성 검사
		if (emp_name == "") {
			alert("이름을 입력해주세요.");
			return;
		}

		// 이름은 한글과 영문만 가능한 패턴
		if (!name_pattern.test(emp_name)) {
			alert("이름은 영문과 한글만 가능합니다.");
			return;
		}

		if (phone == "") {
			alert("핸드폰 번호를 입력해주세요.");
			return;
		}

		// 핸드폰 패턴
		if (!phone_pattern.test(phone)) {
			alert("핸드폰 번호는 000-0000-0000의 형태로 입력해주세요.");
			return;
		}
		
		if (reg_num == "") {
			alert("주민등록번호를 입력해주세요.");
			return;
		}
		
		// 주민번호 패턴
		if (!reg_pattern.test(reg_num)) {
			alert("주민등록번호는 숫자 -를 포함하여 14자리만 가능합니다. \n예) 900408-1234567");
			return;
		}

		if (email == "") {
			alert("메일 주소를 입력해주세요.");
			return;
		}

		// id, 패스워드, 이메일은 숫자와 알파벳만 가능한 패턴
		if (!mail_pattern.test(email)) {
			alert("메일 형식에 맞지 않습니다.");
			return;
		}
		
		if(e_check==false){
			alert("이메일 인증을 해주세요.");
			return;
		}

		// 주소 검색 완료 체크
		if (addr_check == "false") {
			alert("주소를 검색해주세요.");
			return;
		}

		if (addr == "") {
			alert("주소를 검색해주세요.");
			return;
		}

		if (addr_info == "") {
			alert("상세 주소를 검색해주세요.");
			return;
		}

		// 아이디 중복체크
		if (id_check == false) {
			alert("아이디 중복 체크 먼저 해주세요.");
			return;
		}
		
		if (pwd == "") {
			alert("비밀번호를 입력해주세요.");
			return;
		}

		if (!alphanum_pattern.test(pwd)) {
			alert("비밀번호는 영문과 숫자 조합만 가능합니다.");
			return;
		}
		
		if(c_pwd==''){
			alert("비밀번호 확인을 입력해주세요.");
			return;
		}
		
		if(c_pwd!=pwd){
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		
		
		if(!confirm("위 정보대로 관리자를 등록하시겠습니까?")){
			return;
		}
		
		// 등록 결과를 알기위한 ajax
		var url = "mgr_insert.do";
		var reg_param = "dept_name="+dept_name+"&emp_name="+emp_name+"&gender="+gender+"&reg_num="+reg_num+"&phone="+phone+"&email="+email+"&addr="+addr+"&addr_info="+addr_info+"&id="+id+"&pwd="+pwd;
		
		sendRequest(url,reg_param,reg_resultFn,"POST");
		
	}
	
	function reg_resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var reg_result = xhr.responseText;
			
			if(reg_result==0){
				alert("등록에 실패했습니다. 관리자에게 문의해주세요.");
				}
				alert("등록이 완료되었습니다. 로그인 해주세요.");
				location.href="login.do";
			
			}
	}

	// 아이디 중복체크
	function check_id(f) {
		var id = f.id.value.trim();
		var alphanum_pattern = /^[a-zA-Z0-9]+$/;

		if (id == '') {
			alert("아이디를 입력하세요.");
			return;
		}

		if (!alphanum_pattern.test(id)) {
			alert("아이디는 영문과 숫자 조합만 가능합니다.");
			return;
		}

		// ajax
		var url = "id_check.do";
		var param = "id=" + encodeURIComponent(id);

		sendRequest(url, param, resultFn, "POST");
	}

	function resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var json = eval(data);

			if (json[0].result == 'no') {
				alert(json[1].id + "는(은) 이미 사용중인 아이디입니다.");
				return;
			}

			alert(json[1].id + "는(은) 사용가능한 아이디입니다.");

			if (!confirm(json[1].id + "를 사용하시겠습니까?")) {
				return;
			}

			// 사용하겠다고 클릭한 경우
			id_check = true;

			// 입력상자 검색 후 readonly true로 변경
			var id = document.getElementById("id").readOnly = "true";
		}
	}

	// 주소 검색 버튼 누를 경우
	function search_addr() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            var address = data.address;
				var addr = document.getElementById("addr_api");
				
				addr.value = address;
	        }
	    }).open();
	}

	// EMAIL 인증번호 발송 버튼 누를 시
	function email_check(f) {
		var email = f.email.value;
		var url = "email_check.do";
		var param = "email="+email;		
		var mail_pattern = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
		
		if(email==""){
			alert("이메일을 입력해주세요.");
			return;
		}

		if (!mail_pattern.test(email)) {
			alert("메일 형식에 맞지 않습니다.");
			return;
		}
		
		
		sendRequest(url,param,email_resultFn,"POST");
	}
	
	function email_resultFn() {
		
		var check_button = document.getElementById("check_button");
		var code = document.getElementById("code");
		
		code.value = "";
		
		check_button.disabled=false;
		
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
	
	function email_code_check(f) {
		var email_code = f.code.value;
		var check_button = document.getElementById("check_button");
		
		if( xhr.readyState == 4 && xhr.status==200 ){
			
			 var data = xhr.responseText;
			 var json = eval(data);
	
			 if(json[1].joinCode!=email_code){
				 alert("인증번호가 틀렸습니다.");
				 return;
			 }
 			 
			 e_check = true;
			 check_button.disabled="disabled"; 
			 alert("인증이 완료되었습니다.");
			 
			}
		
	}

	// 전화번호 번호 입력시 자동으로 - 처리
	function input_phone(obj_phone) {

		// /[^0-9]/g : 숫자가 아닌 값
		// 입력창에 입력된 값
	    var input = obj_phone.value.replace(/[^0-9]/g, '');
	    var phone = "";
	
		 // 함수에따라서 입력창에서 처리할 값
	    if(input.length < 4) {
	        return input;
	    } else if(input.length < 7) {
	        phone += input.substr(0, 3);
	        phone += "-";
	        phone += input.substr(3);
	    } else if(input.length < 11) {
	        phone += input.substr(0, 3);
	        phone += "-";
	        phone += input.substr(3, 3);
	        phone += "-";
	        phone += input.substr(6);
	    } else {
	        phone += input.substr(0, 3);
	        phone += "-";
	        phone += input.substr(3, 4);
	        phone += "-";
	        phone += input.substr(7);
	    }
	    obj_phone.value = phone;
	}
	
	// 주민 등록 번호 - 처리
	function input_reg(obj_reg) {
		var input = obj_reg.value.replace(/[^0-9]/g,'');
		var reg_num = "";
		
		if(input.length < 6){
			return input;
		}else if(input.length < 13){
			reg_num += input.substr(0,6);
			reg_num += "-";
			reg_num += input.substr(6,7);
		}else{
			reg_num += input.substr(0,6);
			reg_num += "-";
			reg_num += input.substr(6);
		}
		
		obj_reg.value = reg_num;
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
	
	<form>
	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<div class="postCon">
						<div class="postWrap">
							<!-- Form postWriteWrap  -->
                             <div class="postCon">
                            	 <h2>관리자 회원가입</h2>
                                <div class="postWrite">
                                    <dl>
                                        <dt><label>부서</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="부서" name="dept_name" value="ADMIN" readonly="readonly">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이름</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이름" name="emp_name" >
                                        </dd>
                                    </dl>

                                    <dl>
                                        <dt><label>성별</label></dt>
                                
                                            <dd class="sel_2part">
                                                <div class="_radioBox enter-chkBox">
                                            <span class="radio-area">
                                                <input type="radio" id="chk-local" name="gender" checked="checked" value="남자">
                                                <label for="chk-local">남자<span></span></label>
                                            </span>
                                            <span class="radio-area">
                                                <input type="radio" id="chk-foreign" name="gender" value="여자" >
                                                <label for="chk-foreign">여자<span></span></label>
                                            </span>
                                                </div>
                                            </dd>          
                                    </dl>
                                    <dl>
                                        <dt><label>전화번호</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="전화번호" name="phone" maxlength="13" onkeyup="input_phone(this);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>주민등록번호</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="주민등록번호 입력" name="reg_num" maxlength="14" onkeyup="input_reg(this);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일</label></dt>
                                        <dd class="sel_2part">
                                           <input type="text" name="email" id="email">
                                            <input type="button" class="stb-box-btn email_btn" value="인증 코드 발송" onclick="email_check(this.form);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일 인증</label></dt>
                                        <dd class="sel_2part">
                                           <input type="text" name="code" id="code">
                                            <input type="button" id="check_button" class="stb-box-btn" value="인증하기" onclick="email_code_check(this.form);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>주소</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="주소 검색" readonly="readonly" id="addr_api" name="addr">
                                            <input type="button" class="stb-box-btn" value="주소 검색" onclick="search_addr();">

                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>상세주소</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="상세주소 입력" id="addr_info" name="addr_info">
                                        </dd>
                                    </dl>
                                      <dl>
                                        <dt><label>아이디</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="아이디 입력" id="id" name="id">
                                            <input type="button" class="stb-box-btn" value="중복확인" onclick="check_id(this.form);">
                                        </dd>
                                    </dl>
                                     <dl>
                                        <dt><label>비밀번호</label></dt>
                                        <dd class="sel_2part">
                                            <input type="password" title="비밀번호 입력" name="pwd">
                                        </dd>
                                    </dl>
                                     <dl>
                                        <dt><label>비밀번호 확인</label></dt>
                                        <dd class="sel_2part">
                                             <input type="password" title="비밀번호 확인" name="c_pwd">
                                        </dd>
                                    </dl>

                                </div><!-- End postWriteWrap -->
                                <div class="btnWrap alignR">
                                        <input type="button" class="_btn _grey" onclick="mgr_register(this.form);" value="등록">
                                        <a onclick="location.href='login.do'" class="_btn _line">취소</a>
                                    
                                    <input type="hidden" value="${ loginVO.emp_idx }" name="emp_idx">
                                </div>
                            </div><!-- End postWrap -->
                            </div>
                        </div>
					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>	

	</form>
	
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>