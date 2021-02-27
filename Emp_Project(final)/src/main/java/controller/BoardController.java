package controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BoardDAO;
import dao.EmpDAO;

import util.Paging;
import util.Util;
import vo.BoardVO;
import vo.EmpVO;

@Controller
public class BoardController {
	
	@Autowired
	HttpServletRequest request;
	
	BoardDAO board_dao;
	EmpDAO emp_dao;
	
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}
	
	public void setEmp_dao(EmpDAO emp_dao) {
		this.emp_dao = emp_dao;
	}
	
	
	
	//전체 게시물 조회
	@RequestMapping("/list.do")
	public String list(Model model, Integer page, HttpSession session) {
		
		int nowPage = 1;
		
		if(page != null) {
			nowPage = page;
		}
		
		int start = (nowPage -1) * common.Common.Emp.BLOCKLIST + 1;
		int end = start + common.Common.Emp.BLOCKPAGE -1;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = null;
		list = board_dao.selectlist(map);
		
		int row_total = board_dao.getRowTotal();
		
		String pageMenu = Paging.getPaging("list.do", nowPage, row_total, common.Common.Emp.BLOCKLIST, common.Common.Emp.BLOCKPAGE);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		session.setAttribute("board_list", list);
		
		request.getSession().removeAttribute("show");
		
		return Util.BOARD_PATH.VIEW_PATH_BOARD+"board_list.jsp";
	}
	
	//공지사항 글 등록페이지
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return Util.BOARD_PATH.VIEW_PATH_BOARD +"board_write.jsp";
	}
	
	//공지사항 글 등록
	@RequestMapping("/insert.do")
	public String insert(BoardVO vo) {
		
		int res = board_dao.insert(vo);
		
		return "redirect:list.do";
	}
	
	//게시글 조회
	@RequestMapping("/view.do")
	public String view(Model model, int idx) {
		BoardVO vo = board_dao.selectOne(idx);
		
		HttpSession session = request.getSession();
		String show = (String)session.getAttribute("show");
		
		if(show==null) {
			board_dao.update_readhit(idx);
			session.setAttribute("show", "hit");
		}
		
		model.addAttribute("vo", vo);
		return Util.BOARD_PATH.VIEW_PATH_BOARD+"board_view.jsp";
	}
	
	//글 삭제
	@RequestMapping("/del.do")
	@ResponseBody
	public String delete(int idx) {
		BoardVO vo = board_dao.selectOne(idx);
		
		String resultStr = "";
		String result = "no";
		
		//삭제 불가능한 경우
		if(vo==null) {
			resultStr = String.format("[{'res':'%s'}]", result);
			return resultStr;
		}
		
		
		int res = board_dao.board_del(idx);
		
		if(res==1) {
			result = "yes";
		}
		
		resultStr = String.format("[{'res':'%s'}]", result);
		
		return resultStr;
		
	}
	
	//수정 페이지로 이동
	@RequestMapping("/update_form.do")
	public String update_form(Model model, int idx) {
		BoardVO vo = board_dao.selectOne(idx);
		model.addAttribute("vo", vo);
		
		return Util.BOARD_PATH.VIEW_PATH_BOARD+"board_update.jsp";
	}
	
	//게시물 수정
	@RequestMapping("/update.do")
	@ResponseBody
	public String update(int idx, String title, String content) {
		BoardVO baseVO = board_dao.selectOne(idx);
		
		String result = "no";
		
		if(baseVO==null) {
			return result;
		}
		
		BoardVO vo = new BoardVO();
		vo.setBoard_idx(baseVO.getBoard_idx());
		vo.setContent(content);
		vo.setDept_name(baseVO.getDept_name());
		vo.setEmp_name(baseVO.getEmp_name());
		vo.setId(baseVO.getId());
		vo.setMgr(baseVO.getMgr());
		vo.setPwd(baseVO.getPwd());
		vo.setReadhit(baseVO.getReadhit());
		vo.setTitle(title);

		int res = board_dao.update(vo);
		
		if(res==1) {
			result="yes";
		}
		return result;
		
	}
}
