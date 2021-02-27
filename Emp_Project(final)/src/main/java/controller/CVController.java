package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.Commute_viewDAO;
import util.Paging;
import util.Util;
import vo.Commute_viewVO;
import vo.EmpVO;

@Controller
public class CVController {

	Commute_viewDAO commute_view_dao;

	public void setCommute_view_dao(Commute_viewDAO commute_view_dao) {
		this.commute_view_dao = commute_view_dao;
	}

	// 임시로 거치는 매핑
	@RequestMapping("/commute_view_list_check.do")
	public String commute_view_list_check(){

		return Util.Commute_view.VIEW_PATH_CV + "commute_view_list_check.jsp";
	} 

	// 하루치의 출퇴근 기록 조회
	@RequestMapping("/commute_view_list.do")
	public String cv_list(Model model, String dept_name, String emp_name, Integer page){


		// 첫 실행시(두 값 모두 NULL인 상태) (완)
		if(dept_name==null && emp_name==null) {
			// System.out.println("첫 실행 : "+dept_name+emp_name);

			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Cv.BLOCKLIST + 1;
			int end = start + Common.Cv.BLOCKLIST -1;

			// 전체 게시물 조회
			int row_total = commute_view_dao.getRowTotal();

			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);

			List<Commute_viewVO> commute_view_list = commute_view_dao.commute_view_list(se_map);
			model.addAttribute("cv_list",commute_view_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("commute_view_list.do", nowPage, row_total, Common.Cv.BLOCKLIST, Common.Cv.BLOCKPAGE);

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

			int start = (nowPage-1) * Common.Cv.BLOCKLIST + 1;
			int end = start + Common.Cv.BLOCKLIST -1;

			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);

			// 전체 게시물 조회 (오늘 날짜에해당하는 행수 파악)
			int row_total = commute_view_dao.getRowTotal();

			List<Commute_viewVO> commute_view_list = commute_view_dao.commute_view_list(se_map);
			model.addAttribute("cv_list",commute_view_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("commute_view_list.do", nowPage, row_total, Common.Cv.BLOCKLIST, Common.Cv.BLOCKPAGE);

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

			int start = (nowPage-1) * Common.Cv.BLOCKLIST + 1;
			int end = start + Common.Cv.BLOCKLIST -1;

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dept_name",dept_name);
			map.put("emp_name",emp_name);
			map.put("start",start);
			map.put("end",end);

			// 전체 게시물 조회
			int row_total = commute_view_dao.getRowTotal_both_notnull(map);

			List<Commute_viewVO> commute_view_list = commute_view_dao.both_notnull(map);
			model.addAttribute("cv_list",commute_view_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("commute_view_list.do", nowPage, row_total, Common.Cv.BLOCKLIST, Common.Cv.BLOCKPAGE);

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

			int start = (nowPage-1) * Common.Cv.BLOCKLIST + 1;
			int end = start + Common.Cv.BLOCKLIST -1;

			// 전체 게시물 조회
			int row_total = commute_view_dao.getRowTotal_deptname_nn(dept_name);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dept_name",dept_name);
			map.put("start",start);
			map.put("end",end);
			List<Commute_viewVO> commute_view_list = commute_view_dao.dept_name_notnull(map);
			model.addAttribute("cv_list",commute_view_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("commute_view_list.do", nowPage, row_total, Common.Cv.BLOCKLIST, Common.Cv.BLOCKPAGE);

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

			int start = (nowPage-1) * Common.Cv.BLOCKLIST + 1;
			int end = start + Common.Cv.BLOCKLIST -1;

			// 전체 게시물 조회
			int row_total = commute_view_dao.getRowTotal_empname_nn(emp_name);

			HashMap<String, Object> map = new HashMap<String, Object>();			
			map.put("start",start);
			map.put("end",end);
			map.put("emp_name",emp_name);


			List<Commute_viewVO> commute_view_list = commute_view_dao.emp_name_notnull(map);
			model.addAttribute("cv_list",commute_view_list);

			// 페이지 메뉴
			String pageMenu = Paging.getPaging("commute_view_list.do", nowPage, row_total, Common.Cv.BLOCKLIST, Common.Cv.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}

		return Util.Commute_view.VIEW_PATH_CV + "commute_view_list.jsp";
	}

	// commute_view에서 직원 개인의 퇴근을 찍어주는 경우
	@RequestMapping("/gow.do")
	@ResponseBody
	public String gow(int emp_idx) {
		
		// emp_idx로 일단 전체 정보를 조회
		Commute_viewVO cv_vo = commute_view_dao.cv_list_one(emp_idx);
		
		String result = "no";
		
		// 가져온 정보의 gow가 빈값인지 아닌지를 판단
		// System.out.println(cv_vo.getGow());
		// gow가 null이 아닌 경우
		if(cv_vo.getGow()!=null) {
			return result;
		}
		
		// gow가 값이 없는 경우 update로 찍어준다.
		commute_view_dao.gow_update(emp_idx);
		
		result = "yes";
		
		return result;
	}

}
