<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<c:if test="${ empty sessionScope.loginVO }">
	<script>
		alert("로그인 후 이용가능한 페이지입니다.");
		// 로그인 페이지로 이동
		location.href="login.do";
	</script>
</c:if>

<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>

<script type="text/javascript">
	
	var d = new Date();
	var today = getTimeStamp(d);
	
	// 오늘 날짜만 구하기위한 함수
	function getTimeStamp(d) {
		// GETMONTH + 1 : MONTH의 경우 0 ~ 11로 표현되기 때문이다.
		var s =  leadingZeros(d.getFullYear(), 4) + '-' + leadingZeros(d.getMonth() + 1, 2) + '-' + leadingZeros(d.getDate(), 2);
		
	    return s;
	
	}
	
	// N(날짜 단위 값의 길이)숫자가 DIGITS(연<4>,월<2>,일<2>의 길이) 보다 작은 경우 그 앞에 0 붙이는 함수
	function leadingZeros(n, digits) {
	
	    var zero = '';
	    n = n.toString();
	
	    if (n.length < digits) {
	        for (i = 0; i < digits - n.length; i++)
	            zero += '0';
	    }
	    return zero + n;
	}
	
	// 출근
	function gtw(f) {
		var commute_idx;
		var emp_idx = f.emp_idx.value;
		var url = "commute_insert.do";
		var param = "emp_idx="+emp_idx+"&commute_idx=";
		var h_gtw;
		var t_gtw;		
	
		try {
			
			h_gtw = f.h_gtw.value;
			t_gtw = new Date(h_gtw);
			
			if(!confirm("정말로 출근하시겠습니까?")){
				return;
			}

			if(getTimeStamp(t_gtw)==today){
				alert("이미 출근하셨습니다.");
				return;
			}
			
			var commute_idx = f.commute_idx.value;
			param = "emp_idx="+emp_idx+"&commute_idx="+commute_idx;
			sendRequest(url, param, gtw_resultFn, "POST");
			
		} catch (e) {
			
			if(!confirm("정말로 출근하시겠습니까?")){
				return;
			}
			
			sendRequest(url, param, gtw_resultFn, "POST");
		}		
	}
	
	// 퇴근
	function gow(f) {
		
		var commute_idx;
		var emp_idx = f.emp_idx.value;
		var url = "commute_update.do";
		var param = "emp_idx="+emp_idx+"&commute_idx=";
		
		if (!confirm("퇴근시간을 기록하시겠습니까?")) {
			return;
		}
		
		try {
			
			var commute_idx = f.commute_idx.value;
			param = "emp_idx="+emp_idx+"&commute_idx="+commute_idx;
			sendRequest(url, param, gow_resultFn, "POST");
			
		} catch (e) {
			sendRequest(url, param, gow_resultFn, "POST");
		}				
	}

	function gtw_resultFn() {

		if (xhr.readyState == 4 && xhr.status == 200) {

			var data = xhr.responseText;

			if (data == 'no') {
				alert("오늘은 이미 출근하셨습니다.");
				return;
			}
			
			alert("출근이 완료되었습니다.")
			location.href = "commute_list.do";
		}

	}
 
	function gow_resultFn() {

		if (xhr.readyState == 4 && xhr.status == 200) {

			var data = xhr.responseText;
			var json = eval(data);
			
			// 두가지 변수 (date/no_date) )(true/false)
			
			// NO_DATE : TRUE (오늘날짜와 GTW가 일치하지 않는 경우, COMMUTE_IDX가 NULL인 아예 출근 기록이 없는 상태인 경우 = 오늘자 출근 기록이 없습니다.)
			// DATE : FALSE (퇴근이 정확하게 찍힌 경우 = 퇴근이 완료되었습니다.)
			// DATE : UNCHECK (GOW가 이미 있는 경우 = 이미 퇴근한 상태입니다.)
			
			// NO DATE : TRUE
			if(json[0].result=='no_date' && json[1].check=="true"){
				alert("오늘자 출근이 없습니다.");
				return;
			}else 

			// NO : UNCHECK
			if(json[0].result=="date" && json[1].check=="uncheck"){
				alert("이미 퇴근한 상태입니다.");
				return;
			}else
			
			// DATE : FALSE			
			if(json[0].result=="date" && json[1].check=="false"){
				alert("퇴근이 완료되었습니다.");
				location.href='commute_list.do';
				return;
			}
			else{
					alert("문제가 있습니다. 관리자에게 문의해주세요.")
					return;
				}
		}

	}
	
</script>
</head>
<body id="main">

	<form>
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
	<article id="_subArticle">
		<div class="_wrap">
			<div id="_content">
				<div id="sub_content" class="_inner">					
					<div class="_contentArea _formArea">
						<!-- Form srchWrap  -->
						<div class="f-srchWrap">
                            
							 <div class="alignC">
								<input type="button" class="stb-box-btn" value="출근" onclick="gtw(this.form);">
                                <input type="button" class="stb-box-btn" value="퇴근" onclick="gow(this.form);">
                                <input type="hidden" name="emp_idx" value="${loginVO.emp_idx}">
							</div>
                       

						</div><!-- end Form srchWrap  -->

						<!-- Form postWrap  -->
						<div class="postWrap">
							<div class="postTableWrap">
								<table class="postTable vrT">
									<caption>출퇴근 기록 페이지입니다. 나의 출퇴근 정보를 표시합니다.</caption>
									<colgroup>
										<col class="w10per">
                                        <col class="w20per">
										<col class="w25per">
										<col class="w25per">
										<col class="w20per">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">번호</th>
											<th scope="col">날짜</th>
											<th scope="col">출근시간</th>
											<th scope="col">퇴근시간</th>
											<th scope="col">근무시간</th>
										</tr>
									</thead>
									<tbody>
                                    <c:if test="${ empty list }">
                                        <tr>
						                  <td colspan="5" align="center">출퇴근 기록이 없습니다.</td>
                                        </tr>
                                    </c:if>  
                                    <c:forEach var="vo" items="${ list }" varStatus="status">		
                                        <tr>
											<td>
                                                ${ vo.commute_idx }
                                            </td>
                                            
											<td>
                                                <fmt:parseDate var="c_date" value="${ vo.c_date }" pattern="yyyy-MM-dd"/>
                                          		<fmt:formatDate value="${c_date}" pattern="yyyy-MM-dd"/>
                                            </td>
                                            
											<td>
                                                <fmt:parseDate var="gtw" value="${ vo.gtw }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                          		<fmt:formatDate value="${gtw}" pattern="HH:mm"/>
                                            </td>
                                            
											<td>
                                                <fmt:parseDate var="gow" value="${ vo.gow }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                          		<fmt:formatDate value="${gow}" pattern="HH:mm"/>
                                            </td>
                                            
											<td>
                                                <fmt:formatNumber value="${ vo.diffhour / 60 }" pattern="0" />시간
                                                <fmt:formatNumber value="${ vo.diffhour % 60 }" />분
                                            </td>
                                            <c:if test="${ status.first }">
                                                <td style="border:0 solid white;">
                                                    <input type="hidden" value="${ list[0].commute_idx }" name="commute_idx">
                                                    <input type="hidden" value="${ list[0].gtw }" name="h_gtw">
                                                </td>
                                            </c:if>
										</tr>	
                                    </c:forEach>
									</tbody>
								</table>

						</div><!-- End postWrap -->


					</div><!-- End _contentArea _formArea -->
					
				</div><!-- End _inner -->

			</div><!-- End _content -->
		</div><!-- End _wrap -->
	</article>
	</form>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>