package dao;

import org.apache.ibatis.session.SqlSession;

import vo.EmpVO;

public class MyPageDAO {
	
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	// 내정보에 입력된 데이터로 수정
	public int mypage_update(EmpVO vo) {
		
		int res = sqlSession.update("mypage.mypage_update", vo);
		
		return res;
	}
}
