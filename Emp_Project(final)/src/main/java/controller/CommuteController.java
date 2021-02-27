package controller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CommuteDAO;
import javafx.scene.input.DataFormat;
import util.Util;
import vo.CommuteVO;
import vo.EmpVO;



@Controller
public class CommuteController {

	CommuteDAO commute_dao;
	

	public CommuteController(CommuteDAO dao) {
		this.commute_dao = dao;
	}

	//조회
	@RequestMapping("/commute_list.do")
	public String select(Model model, HttpSession session) {

		EmpVO vo = (EmpVO)session.getAttribute("loginVO");

		List<CommuteVO> list = commute_dao.selectList(vo.getEmp_idx());
		model.addAttribute("list", list);
		return Util.Commute.VIEW_PATH_COMMUTE + "commute_record.jsp";
	}


	//출근
	@RequestMapping("/commute_insert.do")
	@ResponseBody
	public String insert(int emp_idx, Integer commute_idx) {

		String result = "no";
		int res = 0;

		if(commute_idx==null) {
			// 아무 데이터도 없으니까 한방에 들어간다.
			CommuteVO vo_null = new CommuteVO();
			vo_null.setEmp_idx(emp_idx);
			res = commute_dao.insert(vo_null);

			if(res==1) {				
				result = "yes";
			}
			
		}else {

			CommuteVO vo = new CommuteVO();
			vo.setEmp_idx(emp_idx);
			res = commute_dao.insert(vo);
			
			if(res == 1) {
				result = "yes";
			}


		}

		return result;
	}

	//퇴근
	@RequestMapping("/commute_update.do")
	@ResponseBody
	public String modify( Integer commute_idx, int emp_idx ) throws ParseException {
		
		//퇴근 중복방지
		String check = "uncheck";
		
		String result = "no";
		int res = 0;
		String resultStr = "";

		// COMMUTE_IDX가 NULL인 것은 기록이 아예 없다는 뜻이 되니 아예 출근을 먼저 찍도록 해야한다.
		// NO_DATE : FALSE 는 오늘자의 출근 기록이 없습니다. 라고 표시
		if(commute_idx==null) {
			check = "true";
			result = "no_date";
			resultStr = String.format("[{'result':'%s'},{'check':'%s'}]", result, check);
			return resultStr;
		}
		
		// COMMUTE_IDX가 있다는 뜻은 기록이 있다는 뜻이니 출근 일자가 오늘자와 일치하는지 아닌지 봐야한다.
		CommuteVO baseVO = commute_dao.selectOne(commute_idx);
		
		// 검색한 COMMUTE_IDX의 GTW를 STRING > DATE > STRING 
		String s_date = baseVO.getGtw();
		Date df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s_date);
		String gtw_date = new SimpleDateFormat("yyy-MM-dd").format(df);
		
		// 오늘 날짜(표준 시간)를 DATE > STRING > DATE > STRING 
		Date date = new Date();
		String t_date = date.toString();
		Date df2 = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(t_date);
		String today_date = new SimpleDateFormat("yyyy-MM-dd").format(df2);
		
		// System.out.println(today_date);
		// GTW의 연도, 월, 일만 들어가있는 N_DATE와 오늘의 연도, 월, 일이 일치하는지 확인
		// 일치하지 않는 경우
		// NO_DATE : FALSE 는 오늘자의 출근 기록이 없습니다. 라고 표시
		if(!gtw_date.equals(today_date)){
			check = "true";
			result = "no_date";
			resultStr = String.format("[{'result':'%s'},{'check':'%s'}]", result, check);
			return resultStr;
		}
		
		
		// 일치하는 경우
		// DATE : FALSE 는 출근이 완료되었습니다. 라고 표시
		if(gtw_date.equals(today_date)) {
			res = commute_dao.update(commute_idx);
		}
		
		if(res == 1) {

			check = "false";
			result = "date";
			resultStr = String.format("[{'result':'%s'},{'check':'%s'}]", result, check);
		}
		
		
		// 일치하지만 만약 저녁 시간이 이미 찍혀있는 상태라면 여기에서 못하게 막는다.
		// DATE : UNCHECK 는 이미 퇴근이 완료되었습니다.
		if(baseVO.getGow()!=null) {
			check = "uncheck";
			result = "date";
			resultStr = String.format("[{'result':'%s'},{'check':'%s'}]", result, check);
		}
		
		
		return resultStr;

	}

}
