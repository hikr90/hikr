package dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.CommuteVO;



public class CommuteDAO {

	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	//전체 게시물 조회
	public List<CommuteVO> selectList(int emp_idx){

		List<CommuteVO> list = sqlSession.selectList("commute.commute_select", emp_idx);
		return list;
	}

	//출근
	public int insert(CommuteVO vo) {		
		int res = sqlSession.insert("commute.commute_insert", vo);
		return res;
	}

	//글 수정을 위한 게시글 한 건 조회
	public CommuteVO selectOne(int idx) {
		CommuteVO vo = sqlSession.selectOne("commute.commute_one", idx);
		return vo;
	}

	//글 수정
	public int update( int idx ) {
		int res = sqlSession.update("commute.commute_update", idx);
		return res;
	}
	

	
}




































