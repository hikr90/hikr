package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BoardDAO;
import dao.DeptDAO;
import dao.LoginDAO;
import dao.MyPageDAO;
import dao.TaskDAO;
import mail.MailService;
import util.Util;
import vo.BoardVO;
import vo.EmpVO;
import vo.TaskVO;

@Controller
public class LoginController {

	LoginDAO login_dao;
	TaskDAO task_dao;
	BoardDAO board_dao;
	DeptDAO dept_dao;
	
	public void setDept_dao(DeptDAO dept_dao) {
		this.dept_dao = dept_dao;
	}
	
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}

	public void setTask_dao(TaskDAO task_dao) {
		this.task_dao = task_dao;
	}
	
	public void setLogin_dao(LoginDAO login_dao) {
		this.login_dao = login_dao;
	}
	
	// 로그인 화면으로 이동
	@RequestMapping(value= {"/","/login.do"})
	public String login() {

		return Util.Login.VIEW_PAHT_LOGIN + "login.jsp";
	}

	// 로그 아웃
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("loginVO");
		return "login.do";
	}
	
	// 메인으로 이동
	@RequestMapping("/main.do")
	public String main(HttpSession session) {
		
		try {

			// 로그인한 사람의 emp_idx를 가져온다.
			EmpVO testvo = (EmpVO)session.getAttribute("loginVO");
			
			// 직원의 emp_idx를 통해서 task_one동작
			List<TaskVO> task_list = task_dao.task_one(testvo.getEmp_idx());
			
			//공지사항 추가
			List<BoardVO> board_list = board_dao.selectList();
			
			session.setAttribute("task", task_list);
			session.setAttribute("board_list", board_list);
			
		} catch (Exception e) {
			return "main.jsp";
		}

		
		return "main.jsp";
	}
	

	@RequestMapping("/login_check.do")
	@ResponseBody
	public String login(String id, String pwd, HttpSession session) {
		// System.out.println(pwd);
		String result = "no_info";
		String resultStr = "";

		// 아이디를 통한 로그인 확인
		EmpVO loginVO = login_dao.login_one(id);

		// id가 없는 경우
		if(loginVO==null) {
			result = "no_id";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}

		// 비밀번호가 다를시
		if(!loginVO.getPwd().equalsIgnoreCase(pwd)) {
			result = "no_pwd";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;

		}

		// 로그인 정보를 세션에서 저장
		session.setAttribute("loginVO", loginVO);
		// 세션 유지기간
		session.setMaxInactiveInterval(60*60);

		// 둘다 일치
		result = "clear";
		resultStr = String.format("[{'result':'%s'}]", result);

		return resultStr;
	}



	// ID/PWD찾기 화면 이동
	@RequestMapping("/find_info_form.do") 
	public String find_info_form() {
		return Util.Login.VIEW_PAHT_LOGIN + "find_info_form.jsp"; 
	}


	// ID찾기
	@RequestMapping("/find_id.do")
	@ResponseBody
	public String find_id(String emp_name, String email) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("emp_name",emp_name);
		map.put("email",email);

		EmpVO BaseVO = login_dao.find_id(map);

		String result = "no_info";
		String resultStr = "";

		if(BaseVO==null) {
			result = "no_info";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}

		// 정보 모두 일치
		result = "clear";
		resultStr = String.format("[{'result':'%s'},{'id':'%s'}]", result,BaseVO.getId());

		return resultStr;
	}


	// PWD찾기
	@RequestMapping("/find_pwd.do")
	@ResponseBody
	public String find_pwd(String id, String email) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("email",email);

		// ID와 주민등록번호로 일치 (basevo:진짜 있으면 불러오는 기존 정보)
		EmpVO BaseVO = login_dao.find_pwd(map);

		String result = "no_info";
		String resultStr = "";

		if(BaseVO==null) {
			result = "no_info";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}

		// 정보 모두 일치
		result = "clear";
		resultStr = String.format("[{'result':'%s'},{'pwd':'%s'}]", result,BaseVO.getPwd());

		return resultStr;
	}
	
	// TASK 글 삭제
	@RequestMapping("/task_del.do")
	@ResponseBody
	public String task_del(int task_idx) {
		
		String result = "no";
		
		int res = task_dao.task_del(task_idx);
		
		if(res==1) {
			result = "yes";
		}
		
		return result;
	}
	
	// TASK 글 추가
	@RequestMapping("/task_insert.do")
	@ResponseBody
	public String task_insert(TaskVO vo) {
		
		String result = "no";
		
		int res = task_dao.task_insert(vo);
		
		if(res==1) {
			result = "yes";
		}
		
		return result;
	}
	
	// 관리자 회원가입 페이지
	@RequestMapping("/mgr_reg.do")
	public String mgr_reg() {
		return Util.Login.VIEW_PAHT_LOGIN + "mgr_reg.jsp";
	}
	
	// 관리자 회원가입
	@RequestMapping("/mgr_insert.do")
	@ResponseBody
	public String mgr_insert(EmpVO vo) {
		
		String reg_result = "no";
		
		// 관리자부터 먼저 추가
		int res = login_dao.mgr_insert(vo);
		
		if(res==1) {
			dept_dao.dept_insert(vo.getDept_name());
			reg_result = "yes";
		}

		return reg_result; 
	}
}
