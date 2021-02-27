package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVO;

public class DeptDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 부서 테이블 DEPT_PROJECT에서 부서명만 배열 형태로 싹 다가져오는 메서드
	public List<DeptVO> dept_mgr() {
		List<DeptVO>  dept_mgr = sqlSession.selectList("dept.dept_mgr");
		return dept_mgr;
	}
	
	// DEPT_NAME이 없는 경우의 행의 수 파악
	public int getRowTotal() {
		int count = sqlSession.selectOne("dept.dept_count");
		return count;
	}
	
	// DEPT_NAME이 없는 경우 페이지 파라미터로 부서 목록 출력
	public List<DeptVO> dept_list(HashMap<String, Integer> se_map){
		List<DeptVO> dept_list = sqlSession.selectList("dept.dept_name_null", se_map);
		return dept_list;
	}
	
	// DEPT_NAME이 NN인 경우의 행수 파악
	public int getRowTotal_dept_name_nn(String dept_name) {
		int count = sqlSession.selectOne("dept.dept_count_nn", dept_name);
		return count;
	}
	
	// DEPT_NAME이 NN인 경우의 목록 출력
	public List<DeptVO> dept_list_condition(HashMap<String, Object> se_map){
		List<DeptVO> dept_list = sqlSession.selectList("dept.dept_name_nn", se_map);
		return dept_list;
	}
	
	// 해당 부서가 존재하는지 하나 검색
	public DeptVO dept_one(String dept_name) {
		DeptVO vo = sqlSession.selectOne("dept.dept_one", dept_name);
		return vo;
	}
	
	// 부서명칭 삽입
	public int dept_insert(String dept_name) {
		int res = sqlSession.insert("dept.dept_insert", dept_name);
		return res;
	}
	
	// 부서테이블의 부서 명칭 변경
	public int dept_update(HashMap<String, String> dept_map) {
		int res = sqlSession.update("dept.dept_update", dept_map);
		return res;
	}
	
	// 부서테이블의 부서 삭제
	public int dept_del(String dept_name) {
		int res = sqlSession.delete("dept.dept_del", dept_name);
		return res;
	}
}
