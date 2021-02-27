package controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.MyPageDAO;
import mail.MailService;
import mail.MailServiceImpl;
import util.Util;
import vo.EmpVO;

@Controller
public class MyPageController {
	
	private MailService mailService;
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	MyPageDAO mypage_dao;

	public void setMypage_dao(MyPageDAO mypage_dao) {
		this.mypage_dao = mypage_dao;
	}

	// 내정보 페이지로 이동
	@RequestMapping("/mypage.do")
	public String mypage() {

		return Util.Mypage.VIEW_PATH_MYPAGE + "mypage.jsp";
	}

	// 내 정보 수정
	@RequestMapping("/mypage_update.do")
	@ResponseBody
	public String mypage_update(EmpVO loginVO, HttpSession session) {

		session.removeAttribute("loginVO");

		String update_result = "no";
		int res = mypage_dao.mypage_update(loginVO);

		session.setAttribute("loginVO", loginVO);

		if(res==1) {
			update_result = "yes";
		}

		return update_result;
	}

	// 이메일 인증버튼 눌렀을 시
	@RequestMapping(value="/email_check.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	private String sendMail(HttpSession session, @RequestParam String email) {
		int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
		String joinCode = String.valueOf(ran);
		session.setAttribute("joinCode", joinCode);

		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
		
		boolean result = mailService.send(subject, sb.toString(), "hikr90@gmail.com", email);
		String resultStr = String.format("[{'res':'%s'},{'joinCode':'%s'}]", ""+result,joinCode);
		
		return resultStr;

	}
}
