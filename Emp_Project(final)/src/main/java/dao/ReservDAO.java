package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.ReservVO;

public class ReservDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 예약DB 전체정보 가져오기
	public List<ReservVO> res_apply(){
		List<ReservVO> res_apply = sqlSession.selectList("reserv.res_apply");
		
		return res_apply;
	}
	
	// 예약신청하기
	public int res_insert(ReservVO vo) {
		int res = sqlSession.insert("reserv.res_insert", vo);
		
		return res;
	}
	
	// 예약 관리 페이지 대기상태 모두 가져오기
	public List<ReservVO> res_manage(){
		List<ReservVO> res_manage = sqlSession.selectList("reserv.res_manage");
		
		return res_manage;
	}
	
	// 예약 관리 페이지 승인/대기 설정
	public int res_choose(String res_state, int res_idx) {
		Map<String, Object> res_map = new HashMap<String, Object>();
		res_map.put("res_state", res_state);
		res_map.put("res_idx", res_idx);

		int res = sqlSession.update("reserv.res_choose", res_map);
		
		return res;
	}
	
	// 예약DB 전체정보 가져오기
	public List<ReservVO> res_user(HashMap<String, Object> se_map){
		List<ReservVO> res_user = sqlSession.selectList("reserv.res_user", se_map);
		
		return res_user;
	}
	
	// 예약DB 삭제하기
	public int res_del(int res_idx) {
		int res = sqlSession.delete("reserv.res_del", res_idx);
		
		return res;
	}
	
	// 페이징 처리를 위한 행 수 처리
	public int getRowTotal(String res_name) {
		int count = sqlSession.selectOne("reserv.reserv_count",res_name);
		return count;
	}
}
