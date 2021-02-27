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

import dao.EmpDAO;
import dao.QaDAO;

import util.Paging;
import util.Util;
import vo.EmpVO;
import vo.QaVO;

@Controller
public class QaController {
	
	QaDAO qa_dao;
	EmpDAO emp_dao;
	
	public void setQa_dao(QaDAO qa_dao) {
		this.qa_dao = qa_dao;
	}
	
	public void setEmp_dao(EmpDAO emp_dao) {
		this.emp_dao = emp_dao;
	}
	
	//전체 게시물 조회
	@RequestMapping("/qa_list.do")
	public String qa_list(Model model, Integer page, HttpServletRequest request) {
		
		int nowPage = 1;
		
		if(page!=null) {
			nowPage = page;
		}
		
		int start = (nowPage - 1) * common.Common.Emp.BLOCKLIST + 1;
		int end = start + common.Common.Emp.BLOCKLIST -1;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<QaVO> list = null;
		
		list = qa_dao.selectlist(map);
		
		int row_total = qa_dao.getRowTotal();
		
		String pageMenu = Paging.getPaging("qa_list.do", nowPage, row_total, common.Common.Emp.BLOCKLIST, common.Common.Emp.BLOCKPAGE);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		request.getSession().removeAttribute("show");
		
		return Util.QA_PATH.VIEW_PATH_QA + "qa_list.jsp";
	}
	
	//등록폼으로 이동
	@RequestMapping("/qa_insert_form.do")
	public String insert_form() {
				
		return Util.QA_PATH.VIEW_PATH_QA + "qa_write.jsp";
	}
	
	//게시물 등록
	@RequestMapping("/qa_insert.do")
	public String qa_insert(QaVO vo) {

		int res = qa_dao.insert(vo);
		
		return "redirect:qa_list.do";
	}
	
	//게시글 조회
	@RequestMapping("/qa_view.do")
	public String qa_view(Model model, int qa_idx, HttpServletRequest request) {
		QaVO vo = qa_dao.selectOne(qa_idx);
		
		HttpSession session = request.getSession();
		String show = (String)session.getAttribute("show"); 
		
		if(show==null) {
			qa_dao.qa_update_readhit(qa_idx);
			session.setAttribute("show", "hit");
		}
		
		model.addAttribute("vo", vo);
		
		return Util.QA_PATH.VIEW_PATH_QA + "qa_view.jsp";
		
	}
	
	//게시글 삭제
	@RequestMapping("/qa_del.do")
	@ResponseBody
	public String qa_del(int qa_idx, String pwd) {
		QaVO vo = qa_dao.selectone(qa_idx, pwd);
		
		String resultStr="";
		String result = "no";
		
		System.out.println(vo);
		if(vo==null) {
			resultStr = String.format("[{'res':'%s'}]", result);
			return resultStr;
		}
		
		// 삭제가 가능한 경우, 지워진 것처럼 보이도록 내용을 수정
		vo.setTitle("삭제된 게시물입니다");
		vo.setEmp_name("unknown");
		
		int res = qa_dao.qa_del(vo);
		
		if(res==1) {
			result="yes";
		}
		
		resultStr = String.format("[{'res':'%s'}]", result);
		
		return resultStr;
		
	}
	
	//게시물 수정폼 이동
	@RequestMapping("/qa_update_form.do")
	public String qa_update_form(Model model, int qa_idx) {
		QaVO vo = qa_dao.selectOne(qa_idx);
		
		model.addAttribute("vo", vo);
		
		return Util.QA_PATH.VIEW_PATH_QA +"qa_update.jsp";
	}
	
	//게시물 수정
	@RequestMapping("/qa_update.do")
	@ResponseBody
	public String qa_update(int qa_idx, String title, String content) {
		QaVO baseVO = qa_dao.selectOne(qa_idx);
		
		String result = "no";
		
		if(baseVO==null) {
			return result;
		}
		
		QaVO vo = new QaVO();
		
		vo.setContent(content);
		vo.setDept_name(baseVO.getDept_name());
		vo.setEmp_name(baseVO.getEmp_name());
		vo.setId(baseVO.getId());
		vo.setPwd(baseVO.getPwd());
		vo.setTitle(title);
		vo.setQa_idx(baseVO.getQa_idx());
		
		int res = qa_dao.qa_update(vo);
		
		if(res==1) {
			result="yes";
		}
		
		return result;
	}
	
	//댓글 다는 폼
	@RequestMapping("/reply_form.do")
	public String reply_form() {
		return Util.QA_PATH.VIEW_PATH_QA +"qa_reply.jsp";
	}
	
	//댓글 달기
	@RequestMapping("/reply.do")
	public String reply(QaVO vo, Integer page, int mgr) {
		
		//게시글 정보
		QaVO baseVO = qa_dao.selectOne(vo.getQa_idx());
		
		//게시글 작성한 사원의 정보
		//String id = baseVO.getId();
		//EmpVO emp_vo = emp_dao.emp_one_id(id);
		
		//스텝 변경
		int res = qa_dao.update_step(baseVO);
		
		
		vo.setRef(baseVO.getRef());
		vo.setStep(baseVO.getStep()+1);
		vo.setDepth(baseVO.getDepth()+1);

		
		res = qa_dao.reply(vo);
		
		return "redirect:qa_list.do?page="+page;
	}
}
