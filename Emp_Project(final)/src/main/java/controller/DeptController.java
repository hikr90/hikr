package controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.DeptDAO;
import dao.EmpDAO;
import util.Paging;
import util.Util;
import vo.DeptVO;

@Controller
public class DeptController {
	
	DeptDAO dept_dao;
	EmpDAO emp_dao;
	
	public void setDept_dao(DeptDAO dept_dao) {
		this.dept_dao = dept_dao;
	}
	
	public void setEmp_dao(EmpDAO emp_dao) {
		this.emp_dao = emp_dao;
	}
	
	// 부서 관리 권한 체크
	@RequestMapping("/dept_list_check.do")
	public String dept_list_check(){

		return Util.Dept.VIEW_PATH_DEPT + "dept_list_check.jsp";
	}

	// 부서 관리 페이지
	@RequestMapping("/dept_list.do")
	public String dept(Model model, String dept_name, Integer page) {

		if(dept_name==null || dept_name=="") {
			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page; 
			}

			int start = (nowPage-1) * Common.Dept.BLOCKLIST + 1;
			int end = start + Common.Dept.BLOCKLIST - 1;

			int row_total = dept_dao.getRowTotal();

			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);

			List<DeptVO> dept_list = dept_dao.dept_list(se_map);
			model.addAttribute("dept_list", dept_list);

			String pageMenu = Paging.getPaging("dept_list.do", nowPage, row_total, Common.Dept.BLOCKLIST, Common.Dept.BLOCKPAGE);
			model.addAttribute("pageMenu",pageMenu);
		}else
		
		
		// // DEPT_NAME이 있는 경우 
		if(dept_name!="") {
		  
		// 페이징 처리 
		int nowPage = 1;

		if(page!=null) { 
			nowPage = page;
		}

		int start = (nowPage-1) * Common.Dept.BLOCKLIST + 1; 
		int end = start + Common.Dept.BLOCKLIST - 1;

		int row_total = dept_dao.getRowTotal_dept_name_nn(dept_name);

		HashMap<String, Object> se_map = new HashMap<String, Object>();
		se_map.put("start",start); 
		se_map.put("end",end);
		se_map.put("dept_name",dept_name);
		
		List<DeptVO> dept_list = dept_dao.dept_list_condition(se_map);
		model.addAttribute("dept_list", dept_list);

		String pageMenu = Paging.getPaging("dept_list.do", nowPage, row_total, Common.Dept.BLOCKLIST, Common.Dept.BLOCKPAGE);
		model.addAttribute("pageMenu",pageMenu);
		
		}

		return Util.Dept.VIEW_PATH_DEPT + "dept_list.jsp";
	}
	
	
	// 부서 추가
	@RequestMapping("/dept_insert.do")
	@ResponseBody
	public String dept_insert(String dept_name) {
		
		String result = "no";
		String resultStr = "";
		
		// 받은 부서 이름과 SELECTONE으로 해당 부서명이 있는지 확인하고 없으면 넣고 있으면 있다고 처리
		DeptVO BaseVO = dept_dao.dept_one(dept_name);
		
		// BASEVO가 NULL이 아니면 추가 X (중복)
		if(BaseVO!=null && BaseVO.getDept_name().equalsIgnoreCase(dept_name)) {
			result = "exists";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}
		
		// BASEVO가 NULL (해당 부서명칭이 없는 경우)
		int res = dept_dao.dept_insert(dept_name);
		
		if(res!=1) {
			result = "no";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}
		
		result = "yes";
		resultStr = String.format("[{'result':'%s'}]", result);
		return resultStr;		
	}
	
	// 부서명칭 수정
	@RequestMapping("/dept_update.do")
	@ResponseBody
	public String dept_update(String dept_name, String update_name) {
		
		String result = "no";
		String resultStr = "";
		
		// 먼저 SELECTONE으로 해당 부서가 있는지 검색
		DeptVO BaseVO = dept_dao.dept_one(update_name);
		
		// !=NULL : 같은 명칭의 부서가 있으므로 수정 불가능
		// 파라미터로 받아온 DEPT_NAME과 UPDATE_NAME이 같은 경우 이미 존재한다고 표시
		if(dept_name.equalsIgnoreCase(update_name) || BaseVO!=null) {
			result = "exists";
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}
		
		// 부서 테이블 수정
		// 수정할 내용 > HASHMAP에 저장
		HashMap<String, String> dept_map = new HashMap<String, String>();
		dept_map.put("update_name",update_name);
		dept_map.put("dept_name",dept_name);
		
		// ==NULL : 수정 가능
		int dept_res = dept_dao.dept_update(dept_map);
		
		// 부서 테이블이 수정되지 않은 경우
		if(dept_res!=1) {
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}		
		
		// 직원 테이블 수정
		int emp_res = emp_dao.emp_dept_update(dept_map);
		
		result = "yes";
		resultStr = String.format("[{'result':'%s'}]", result);
		
		return resultStr;
	}
	
	// 삭제
	@RequestMapping("/dept_del.do")
	@ResponseBody
	public String dept_del(String dept_name) {
		
		String result = "no";
		String resultStr = "";
		
		// 부서 테이블에서 해당 DEPT_NAME 바로 삭제
		int dept_res = dept_dao.dept_del(dept_name);
		
		if(dept_res!=1) {
			resultStr = String.format("[{'result':'%s'}]", result);
			return resultStr;
		}
		
		// 직원 테이블에서  부서가 DEPT_NAME와 같은 사람 전부 삭제 
		int emp_res = emp_dao.emp_dept_del(dept_name);
		
		result = "yes";
		resultStr = String.format("[{'result':'%s'}]", result);
		
		return resultStr;
	}
}
