<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script
	src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
	

	//화면 시작시 바로 진행할 부서 추가 메서드
	window.onload = function() {
		
		// ajax를 통해서 컨트롤러에서 부서 항목만 담겨있는 테이블을 전부 긁은 뒤, 그 행값(부서명들)을 그대로 가져와서 select의 id값에 넣는다.
		// 그럼 테이블의 내용에따라서 계속해서 부서의 종류는 계속 변화할 것이다.
		var url = "dept_mgr.do";

		sendRequest(url, null, dept_resultFn, "POST");
		
	}

	function dept_resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			var json = eval(data);
			var dept_name = document.getElementById("dept_name");

			for (var i = 0; i <= json.length; i++) {
				var opt = document.createElement("option");
				opt.innerHTML = json[i].dept_name;

				dept_name.appendChild(opt);				
				dept_name.value = '${vo.dept_name}';
			}

		}
	}

	
	var addr_check = false;

	// 등록
	function emp_update(f) {

		// 유효성 검사
		var dept_name = f.dept_name.value;
		var emp_name = f.emp_name.value;
		var gender = f.gender.value;
		var reg_num = f.reg_num.value.trim();
		var phone = f.phone.value.trim();
		var email = f.email.value.trim();
		var name_pattern = /^[가-힣a-zA-Z]+$/;
		var reg_pattern = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
		var phone_pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		var alphanum_pattern = /^[a-zA-Z0-9]+$/;
		var addr = f.addr.value;
		var addr_info = f.addr_info.value;
		var mail_pattern = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
		var addr_api = document.getElementById("addr_api").value;
		var emp_idx = f.emp_idx.value;

		// 유효성 검사
		if (dept_name == "부서를 선택하세요.") {
			alert("부서를 선택하세요.");
			return;
		}

		if (emp_name == "") {
			alert("이름을 입력해주세요.");
			return;
		}

		// 이름은 한글과 영문만 가능한 패턴
		if (!name_pattern.test(emp_name)) {
			alert("이름은 영문과 한글만 가능합니다.");
			return;
		}

		if (reg_num == "") {
			alert("주민등록번호를 입력해주세요.");
			return;
		}

		// 주민번호 패턴
		if (!reg_pattern.test(reg_num)) {
			alert("주민등록번호는 숫자 -를 포함하여 12자리만 가능합니다.");
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

		if (email == "") {
			alert("메일 주소를 입력해주세요.");
			return;
		}

		// id, 패스워드, 이메일은 숫자와 알파벳만 가능한 패턴
		if (!mail_pattern.test(email)) {
			alert("메일 형식에 맞지 않습니다.");
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

		if (!confirm("정말로 수정하시겠습니까?")) {
			return;
		}

		// 수정 결과를 알기위한 ajax
		var url = "emp_update.do";
		var update_param = "dept_name=" + dept_name + "&emp_name=" + emp_name
				+ "&gender=" + gender + "&reg_num=" + reg_num + "&phone="
				+ phone + "&email=" + email + "&addr=" + addr + "&addr_info="
				+ addr_info + "&emp_idx=" + emp_idx;

		sendRequest(url, update_param, update_resultFn, "POST");
	}

	function update_resultFn() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var update_result = xhr.responseText;
			if (update_result == 'no') {
				alert("수정 실패, 관리자에게 문의해주세요.");
			}

			alert("수정이 완료되었습니다.");
			location.href = "emp_list.do?page=${param.page}";
		}
	}

	// 검색 버튼 누를 경우
	function search_addr() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				// 예제를 참고하여 다양한 활용법을 확인해 보세요.
				var address = data.address;
				var addr = document.getElementById("addr_api");

				addr.value = address;
			}
		}).open();
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
<form>
<input type="hidden" value="${ param.emp_idx }" name="emp_idx">
	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<div class="postWrap">
							<!-- Form postWriteWrap  -->
                            <div class="postCon">
                            	 <h2>사원정보 수정</h2>
                                <div class="postWrite">
                                    <dl>
                                        <dt><label>부서</label></dt>
                                        <dd class="sel_3part">
                                            <div class="srchArea">
                                                <div class="srchBox">
														<select name="dept_name" id="dept_name">
															<option>부서를 선택하세요.</option>
														</select>
													</div>
                                            </div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이름</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이름" name="emp_name" value="${ vo.emp_name }">
                                        </dd>
                                    </dl>

                                    <dl>
                                        <dt><label>성별</label></dt>
                                        <c:if test="${ vo.gender eq '남자' }">
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
                                        </c:if>
                                            <c:if test="${ vo.gender eq '여자' }">
                                            <dd class="sel_2part">
                                                <div class="_radioBox enter-chkBox">
                                            <span class="radio-area">
                                                <input type="radio" id="chk-local" name="gender" value="남자">
                                                <label for="chk-local">남자<span></span></label>
                                            </span>
                                            <span class="radio-area">
                                                <input type="radio" id="chk-foreign" name="gender" checked="checked" value="여자">
                                                <label for="chk-foreign">여자<span></span></label>
                                            </span>
                                                </div>
                                            </dd>
                                        </c:if>
                                    </dl>

                                    <dl>
                                        <dt><label>전화번호</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="전화번호 입력" name="phone" value="${ vo.phone }" maxlength="13" onkeyup="input_phone(this);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>주민등록번호</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="주민등록번호 입력" name="reg_num" value="${ vo.reg_num }" maxlength="14" onkeyup="input_reg(this);">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>이메일</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="이메일 입력" name="email" value="${ vo.email }">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>주소</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="주소 검색" readonly="readonly" id="addr_api" value="${ vo.addr }" name="addr">
                                            <input type="button" class="stb-box-btn" value="주소 검색" onclick="search_addr();">

                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt><label>상세주소</label></dt>
                                        <dd class="sel_2part">
                                            <input type="text" title="상세주소 입력" id="addr_info" name="addr_info" value="${ vo.addr_info }">
                                        </dd>
                                    </dl>
                                      <dl>
                                        <dt><label>입사일</label></dt>
                                        <dd class="sel_2part">
                                            <fmt:formatDate value="${ vo.hire_date }" pattern="yyyy-MM-dd"/>
                                        </dd>
                                    </dl>

                                </div><!-- End postWriteWrap -->
                                <div class="btnWrap alignR">
                                        <input type="button" class="_btn _grey" onclick="emp_update(this.form);" value="수정">
                                        <a onclick="location.href='emp_list.do?page=${ param.page }'" class="_btn _line">취소</a>
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