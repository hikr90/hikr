package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.Commute_viewVO;

public class Commute_viewDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 하루치 출퇴근 기록 출력
	public List<Commute_viewVO> commute_view_list(){
		List<Commute_viewVO> commute_view_list = sqlSession.selectList("commute_view.commute_view_selectList");
		return commute_view_list;
	}
	
	// 검색시 부서명 사원명 둘다 있는 경우
	public List<Commute_viewVO> both_notnull(HashMap<String, Object> map){
		List<Commute_viewVO> commute_view_list = sqlSession.selectList("commute_view.both_notnull", map);
		return commute_view_list;
	}
	
	// 검색시 부서명만 있는 경우
	public List<Commute_viewVO> dept_name_notnull(HashMap<String, Object> map){
		List<Commute_viewVO> commute_view_list = sqlSession.selectList("commute_view.emp_name_null", map);
		return commute_view_list;
	}
	
	// 검색시 사원명만 있는 경우
	public List<Commute_viewVO> emp_name_notnull(HashMap<String, Object> map){
		List<Commute_viewVO> commute_view_list = sqlSession.selectList("commute_view.dept_name_null", map);
		return commute_view_list;
	}
	
	// 페이지대로 항목 조회
	public List<Commute_viewVO> commute_view_list(HashMap<String, Integer> map){
		List<Commute_viewVO> commute_view_list = sqlSession.selectList("commute_view.cv_list_condition", map);
		return commute_view_list;
	}
	
	// 첫 실행시의 전체 항목 수 구하기
	public int getRowTotal() {
		int count = sqlSession.selectOne("commute_view.cv_count");
		return count;	
	}
	
	// 두 값이 전부 있는 경우의 전체 항목 수 구하기
	public int getRowTotal_both_notnull(HashMap<String, Object> map) {
		int count = sqlSession.selectOne("commute_view.cv_count_both_notnull", map); 
		return count;
	}
	
	// 부서명만 있는 경우의 전체 항목 수 구하기
	public int getRowTotal_deptname_nn(String dept_name) {
		int count = sqlSession.selectOne("commute_view.cv_count_deptname_nn",dept_name);
		return count;
	}
	
	// 직원명만 있는 경우의 전체 항목 수 구하기
	public int getRowTotal_empname_nn(String emp_name) {
		int count = sqlSession.selectOne("commute_view.cv_count_empname_nn", emp_name);
		return count;
	}
	
	// 퇴근 처리를 위한 emp_idx를 통해서 오늘에 해당하는 출퇴근 기록의 전체 정보 가져오기
	public Commute_viewVO cv_list_one(int emp_idx) {
		Commute_viewVO cv_list = sqlSession.selectOne("commute_view.cv_list_one", emp_idx);
		return cv_list;
	}
	
	// gow값이 없는 경우 emp_idx를 통해서 update로 sysdate를 찍어준다.
	public int gow_update(int emp_idx) {
		int res = sqlSession.update("commute_view.gow_update", emp_idx);
		return res;
	}
}
