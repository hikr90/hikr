<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
		<div class="_wrap">
			<div class="utilWrap">
				<div class="login_state">
				
					<div class="login_state_text">
						<strong>${loginVO.emp_name}</strong>님이 로그인 하셨습니다. 
					</div>
				</div>

				<ul class="utilList">
					<li><a href="main.do">home</a></li>
					<li><a href="mypage.do">my page</a></li>
					<li><a href="logout.do">logout</a></li>
				</ul>
			</div>
			
			<div id="gnbWrap">
					<div class="logo">
						<a href="main.do"><img id="_logo" src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
					</div>
				<nav id="_nav">
					<ul class="ul_1">
						<li class="li_1">
							<a class="a_1">커뮤니티</a>
							<ul class="ul_2">
								<li class="li_2">
									<a class="a_2" href="list.do">공지사항</a>
								</li>
								<li class="li_2">
									<a class="a_2" href="qa_list.do">Q &amp; A </a>
								</li>
								<li class="li_2">
									<a class="a_2" href="upload_list.do">자료실</a>
								</li>
							</ul>
						</li>
						<li class="li_1">
							<a class="a_1">회의실 예약</a>
							<ul class="ul_2">
								<li class="li_2">
									<a class="a_2" href="res_apply.do">예약 신청</a>
								</li>
								<li class="li_2">
									<a class="a_2" href="res_user.do">예약 확인</a>
								</li>
                                <li class="li_2">
									<a class="a_2" href="res_manage_check.do">예약 관리</a>
								</li>
							</ul>
						</li>
						<li class="li_1">
							<a class="a_1" href="commute_list.do">출퇴근 기록</a>
						</li>
						<li class="li_1">
							<a class="a_1">휴가 관리</a>
							<ul class="ul_2">
								<li class="li_2">
									<a class="a_2" href="vacation_apply.do">휴가 신청</a>
								</li>
                                <li class="li_2">
									<a class="a_2" href="calendar.do">휴가 확인</a>
								</li>
                                <li class="li_2">
									<a class="a_2" href="vacation_management_check.do">휴가 관리</a>
								</li>
							</ul>
						</li>
                        <li class="li_1">
							<a class="a_1">직원관리</a>
							<ul class="ul_2">
								<li class="li_2">
									<a class="a_2" href="emp_list_check.do">직원DB</a>
								</li>
                                <li class="li_2">
									<a class="a_2" href="dept_list_check.do">부서관리</a>
								</li>
                                <li class="li_2">
									<a class="a_2" href="commute_view_list_check.do">근태관리</a>
								</li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>