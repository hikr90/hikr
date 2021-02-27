package controller;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.Common;
import dao.EmpDAO;
import util.Paging;
import util.Util;
import vo.DeptVO;
import vo.EmpVO;

@Controller
public class EmpController {

	EmpDAO emp_dao;

	public void setEmp_dao(EmpDAO emp_dao) {
		this.emp_dao = emp_dao;
	}

	// 임시로 거치는 매핑
	@RequestMapping("/emp_list_check.do")
	public String emp_list_check(){

		return Util.Emp.VIEW_PATH + "emp_list_check.jsp";
	}

	// 사원 목록 맵핑
	@RequestMapping("/emp_list.do")
	public String emp_list(Model model, Integer page, String dept_name, String emp_name) {
		
		// 첫 실행시(두 값 모두 NULL인 상태) (완)
		if(dept_name==null && emp_name==null) {
			// System.out.println("첫 실행 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Emp.BLOCKLIST + 1;
			int end = start + Common.Emp.BLOCKLIST - 1;

			// 전체 게시물 조회
			int row_total = emp_dao.getRowTotal();

			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);

			List<EmpVO> emp_list = emp_dao.selectList(se_map);
			model.addAttribute("emp_list",emp_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("emp_list.do", nowPage, row_total, Common.Emp.BLOCKLIST, Common.Emp.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		// 한번 검색 후에 다시 검색 
		else if(dept_name.equals("부서를 선택하세요.") && (emp_name.isEmpty())) {
			// System.out.println("검색 후 실행 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Emp.BLOCKLIST + 1;
			int end = start + Common.Emp.BLOCKLIST -1;

			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);

			// 전체 게시물 조회 (오늘 날짜에해당하는 행수 파악)
			int row_total = emp_dao.getRowTotal();

			List<EmpVO> emp_list = emp_dao.selectList(se_map);
			model.addAttribute("emp_list",emp_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("emp_list.do", nowPage, row_total, Common.Emp.BLOCKLIST, Common.Emp.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		// 두 값이 전부 있는 상황 (완)
		else if(!dept_name.isEmpty() && !emp_name.isEmpty() && !(dept_name.equals("부서를 선택하세요."))) {
			// System.out.println("두 값 다 있음 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Emp.BLOCKLIST + 1;
			int end = start + Common.Emp.BLOCKLIST -1;

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dept_name",dept_name);
			map.put("emp_name",emp_name);
			map.put("start",start);
			map.put("end",end);

			// 전체 게시물 조회
			int row_total = emp_dao.getRowTotal_both_notnull(map);

			List<EmpVO> emp_list = emp_dao.both_notnull(map);
			model.addAttribute("emp_list",emp_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("emp_list.do", nowPage, row_total, Common.Emp.BLOCKLIST, Common.Emp.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		// 부서명만 있는 경우 (완)
		else if(dept_name!=null && emp_name.isEmpty()) {
			// System.out.println("부서명만 있음 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Emp.BLOCKLIST + 1;
			int end = start + Common.Emp.BLOCKLIST -1;

			// 전체 게시물 조회
			int row_total = emp_dao.getRowTotal_deptname_nn(dept_name);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dept_name",dept_name);
			map.put("start",start);
			map.put("end",end);
			List<EmpVO> emp_list = emp_dao.dept_name_notnull(map);
			model.addAttribute("emp_list",emp_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("emp_list.do", nowPage, row_total, Common.Emp.BLOCKLIST, Common.Emp.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		// 직원명만 있는 경우
		else if(dept_name.equals("부서를 선택하세요.") && !emp_name.isEmpty()) {
			// System.out.println("직원명만 있음 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Emp.BLOCKLIST + 1;
			int end = start + Common.Emp.BLOCKLIST -1;

			// 전체 게시물 조회
			int row_total = emp_dao.getRowTotal_empname_nn(emp_name);

			HashMap<String, Object> map = new HashMap<String, Object>();			
			map.put("start",start);
			map.put("end",end);
			map.put("emp_name",emp_name);


			List<EmpVO> emp_list = emp_dao.emp_name_notnull(map);
			model.addAttribute("emp_list",emp_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("emp_list.do", nowPage, row_total, Common.Emp.BLOCKLIST, Common.Emp.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		return Util.Emp.VIEW_PATH + "emp_list.jsp";

	}

	// 아이디 중복 체크를 위한 selectOne
	@RequestMapping("/id_check.do")
	@ResponseBody
	public String emp_one_id(String id) {
		String result = "no";
		String resultStr = "";
		EmpVO vo = emp_dao.emp_one_id(id);

		if (vo == null) {
			result = "yes";
			resultStr = String.format("[{'result':'%s'},{'id':'%s'}]", result, id);
		} else {
			resultStr = String.format("[{'result':'%s'},{'id':'%s'}]", result, id);
		}

		return resultStr;
	}

	// 직원 등록
	@RequestMapping("/emp_insert.do")
	@ResponseBody
	public String emp_insert(EmpVO vo) {
		int res = emp_dao.emp_insert(vo);
		String reg_result = "no";

		if(res==1) {
			reg_result = "yes";
		}

		return reg_result; 
	}

	// 직원 상세보기 페이지
	@RequestMapping("/emp_view.do")
	public String emp_view(int emp_idx, Model model) {

		// 직원 검색
		EmpVO vo = emp_dao.emp_one_idx(emp_idx);
		model.addAttribute("vo",vo);

		return Util.Emp.VIEW_PATH + "emp_view.jsp";
	}

	// 직원 삭제를 위한 ajax 맵핑
	@RequestMapping("/emp_del.do")
	@ResponseBody
	public String emp_del(int emp_idx) {

		String result = "no";
		// 해당 직원이 있는지 idx로 검색
		EmpVO vo = emp_dao.emp_one_idx(emp_idx);

		// 직원이 없는 경우
		if(vo==null) {
			return result; 
		}

		// 삭제
		int res = emp_dao.emp_del(emp_idx);

		if(res==1) {
			result = "yes";
		}

		return result;
	}

	// 회원 수정 페이지 이동
	@RequestMapping("/emp_update_form.do")
	public String emp_update_form(int emp_idx, Model model) {

		// 파라미터로 가져온 값을 이용하여 EmpVO로드
		EmpVO vo = emp_dao.emp_one_idx(emp_idx);
		model.addAttribute("vo", vo);

		return Util.Emp.VIEW_PATH + "emp_update_form.jsp";
	}

	// 회원 정보 수정
	@RequestMapping("/emp_update.do")
	@ResponseBody
	public String emp_update(EmpVO vo, HttpSession session) {

		String update_result = "no";
		int res = emp_dao.emp_update(vo);
		
		// 정보 수정뒤에 로그인 정보를 해당 정보로 다시 세팅
		session.removeAttribute("loginVO");
		session.setAttribute("loginVO", vo);
		
		if(res==1) {
			update_result = "yes";
		}

		return update_result;
	}
	
	// 등록 페이지 이동
	@RequestMapping("/emp_reg_form.do")
	public String emp_reg_form(int page) {
		
		return Util.Emp.VIEW_PATH + "emp_reg.jsp";
	}
		
	// EMP_LIST 페이지가 동작하자마자 부서명을 가져오는 메서드
	@RequestMapping(value="/dept_mgr.do", method=RequestMethod.POST)
	@ResponseBody
	public List<DeptVO> dept_mgr() {
		
		// 일단 먼저 SELECT문으로 부서명 전체를 긁어온다. (배열)
		List<DeptVO> dept_name = emp_dao.dept_mgr();
		
		return dept_name;
	}
}
