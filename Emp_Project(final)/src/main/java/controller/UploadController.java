package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import common.Common;
import dao.UploadDAO;
import util.Paging;
import util.Util;
import vo.UploadVO;

@Controller
public class UploadController {
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpServletRequest request;
	
	UploadDAO upload_dao;
	
	public void setUpload_dao(UploadDAO upload_dao) {
		this.upload_dao = upload_dao;
	}
	
	// 자료실 출력
	@RequestMapping("/upload_list.do")
	public String upload_list(Model model, Integer page, String subject) {
		
		// 첫 실행시 (subject=자체가 없는 경우) 혹은 (subject=인 경우)
		if(subject==null || subject.isEmpty()) {
			
			// 페이징 처리
			int nowPage = 1;
			
			if(page!=null) {
				nowPage = page;
			}
			
			int start = (nowPage-1) * Common.Upload.BLOCKLIST + 1;
			int end = start + Common.Upload.BLOCKLIST - 1;
			
			// 전체 게시물 조회
			int row_total = upload_dao.getRowTotal();
			
			HashMap<String, Integer> se_map = new HashMap<String, Integer>();
			se_map.put("start",start);
			se_map.put("end",end);
			
			List<UploadVO> upload_list = upload_dao.selectList(se_map);
			model.addAttribute("upload_list", upload_list);
			
			// 페이지 메뉴
			String pageMenu = Paging.getPaging("upload_list.do", nowPage, row_total, Common.Upload.BLOCKLIST, Common.Upload.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}
		
		// 검색 단어가 있는 경우
		else {
			
			// 페이징 처리
			int nowPage = 1;

			if(page!=null) {
				nowPage = page;
			}

			int start = (nowPage-1) * Common.Upload.BLOCKLIST + 1;
			int end = start + Common.Upload.BLOCKLIST - 1;
						
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("subject",subject);
			map.put("start",start);
			map.put("end",end);
			
			// 검색 단어가 있는 경우의 게시물 조회
			int row_total = upload_dao.getRowTotal_sub_nn(map);
		
			List<UploadVO> upload_list = upload_dao.sub_nn_list(map);
			model.addAttribute("upload_list", upload_list);
			
			// 페이지 메뉴
			String pageMenu = Paging.getPaging("upload_list.do", nowPage, row_total, Common.Upload.BLOCKLIST, Common.Upload.BLOCKPAGE);

			model.addAttribute("pageMenu", pageMenu);
		}
		
		// 조회수가 다시 증가되도록 SHOW 영역의 데이터 삭제
		request.getSession().removeAttribute("show");
		
		return Util.Upload.VIEW_PATH_UPLOAD + "upload_list.jsp";
	} 
	
	// 자료실 업로드 페이지 이동
	@RequestMapping("/upload_insert_form.do")
	public String upload_insert_form() {
		return Util.Upload.VIEW_PATH_UPLOAD + "upload_insert_form.jsp"; 
	}
	
	// 업로드
	@RequestMapping("/upload_insert.do")
	public String upload_insert(UploadVO vo) throws IllegalStateException, IOException {
		
		// 업로드할 경로
		String webPath = "/resources/upload/"; // 절대 경로
		String savePath = application.getRealPath(webPath);
		System.out.println(savePath); // 절대 경로의 컴퓨터상 위치
		
		// 업로드된 파일의 정보 저장
		MultipartFile upload_file = vo.getUpload_file();
		String filename = "no_file";
		
		// 존재 여부 파악
		if(!upload_file.isEmpty()) {
			filename = upload_file.getOriginalFilename();
		
			// 파일명 중복 처리
			// 저장 파일 경로 생성 (SAVEPATH에 FILENAME으로 된 경로 있는지 확인)
			File saveFile = new File(savePath,filename);
			
			// 중복이 없는 경우
			if(!saveFile.exists()) {
				saveFile.mkdirs();
			// 중복이 있는 경우
			}else {
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time,filename);
				saveFile = new File(savePath,filename); 
			}
			
			// 업로드된 파일을 복사한 뒤, SAVEFILE 경로로 붙여넣기
			upload_file.transferTo(saveFile);
		}
		
		// 파일 정보는 VO에 담은 뒤, 저장경로 역시 지정했으니 파일명을 VO에 담는다.
		vo.setFilename(filename);
		
		// VO에 담았으니 서버에 저장시켜주자
		int res = upload_dao.upload_insert(vo);
		
		return "redirect:upload_list.do";
	}
	
	// 업로드 글 상세보기
	@RequestMapping("/upload_view.do")
	public String upload_view(Model model, int upload_idx) {
		
		// 특정 번호의 게시글 조회
		UploadVO vo = upload_dao.upload_view(upload_idx);
		
		// 게시글 중복 조회 방지
		HttpSession session = request.getSession();
		String show = (String)session.getAttribute("show");
		
		if(show==null) {
			// 조회수 증가
			upload_dao.update_readhit(upload_idx);
			
			// 새로고침하는 경우 조회수가 오르지 않도록 SHOW라는 세션영역에 HIT이라는 데이터 저장
			session.setAttribute("show", "hit");
		}
		
		// 바인딩
		model.addAttribute("vo", vo);
		
		return Util.Upload.VIEW_PATH_UPLOAD + "upload_view.jsp";
	}
	
	// 게시글 삭제
	@RequestMapping("/upload_del.do")
	@ResponseBody
	public String upload_del(int upload_idx) {
		
		String resultStr = "";
		String result = "no";
		
		int res = upload_dao.upload_del(upload_idx);
		
		if(res==1) {
			result = "yes";
		}
		
		resultStr = String.format("[{'res':'%s'}]", result);
		return resultStr;
	} 
	
	// 수정 페이지로 이동
	@RequestMapping("/upload_update_form.do")
	public String upload_update_form(Model model, int upload_idx) {
		
		// UPLOAD_IDX 통해서 VO 저장후 바인딩 & 포워딩
		UploadVO vo = upload_dao.upload_view(upload_idx);
		
		model.addAttribute("vo", vo);
		return Util.Upload.VIEW_PATH_UPLOAD + "upload_update_form.jsp";
	}
	
	// 게시글 수정
	@RequestMapping("/upload_update.do")
	public String upload_update(UploadVO vo, int page) throws IllegalStateException, IOException {
		
		// 업로드할 경로
		String webPath = "/resources/upload/"; // 절대 경로
		String savePath = application.getRealPath(webPath);
		System.out.println(savePath); // 절대 경로의 컴퓨터상 위치

		// 업로드된 파일의 정보 저장
		MultipartFile upload_file = vo.getUpload_file();
		String filename = "no_file";

		// 존재 여부 파악
		if(!upload_file.isEmpty()) {
			filename = upload_file.getOriginalFilename();

			// 파일명 중복 처리
			// 저장 파일 경로 생성 (SAVEPATH에 FILENAME으로 된 경로 있는지 확인)
			File saveFile = new File(savePath,filename);

			// 중복이 없는 경우
			if(!saveFile.exists()) {
				saveFile.mkdirs();
				// 중복이 있는 경우
			}else {
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time,filename);
				saveFile = new File(savePath,filename); 
			}

			// 업로드된 파일을 복사한 뒤, SAVEFILE 경로로 붙여넣기
			upload_file.transferTo(saveFile);
		}

		// 파일 정보는 VO에 담은 뒤, 저장경로 역시 지정했으니 파일명을 VO에 담는다.
		vo.setFilename(filename);

		// VO에 담았으니 서버에 저장시켜주자
		int res = upload_dao.upload_update(vo);

		return "redirect:upload_view.do?upload_idx="+vo.getUpload_idx()+"&page="+page;
	}
}
