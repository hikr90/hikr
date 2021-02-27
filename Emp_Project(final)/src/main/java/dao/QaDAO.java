package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.QaVO;

public class QaDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체 게시물 조회
	public List<QaVO> selectList(){
		List<QaVO> list = sqlSession.selectList("qa.qa_list");
		return list;
	}
	
	//게시물 등록
	public int insert(QaVO vo) {
		int res = sqlSession.insert("qa.qa_insert", vo);
		return res;
	}
	
	//게시물 한 건 조회
	public QaVO selectOne(int qa_idx) {
		QaVO vo = null;
		vo = sqlSession.selectOne("qa.qa_one",qa_idx);
		return vo;
	}
	
	//조회수 증가
	public int qa_update_readhit(int idx) {
		int res = sqlSession.update("qa.qa_readhit", idx);
		return res;
	}
	
	//게시글 삭제된 것처럼
	public int qa_del(QaVO vo) {
		int res = sqlSession.update("qa.qa_del", vo);
		return res;
	}
	
	//게시물 업데이트
	public int qa_update(QaVO vo) {
		int res = sqlSession.update("qa.qa_update", vo);
		return res;
	}
	
	//페이지별 게시물 조회
	public List<QaVO> selectlist(HashMap<String, Integer> map){
		List<QaVO> list = sqlSession.selectList("qa.qa_list_page", map);
		return list;
	} 
	
	//전체 게시물 수 구하기
	public int getRowTotal() {
		int count = sqlSession.selectOne("qa.qa_count");
		return count;
	}
	
	//step 수정(증가)
		public int update_step(QaVO baseVO) {
			int res = sqlSession.update("qa.qa_update_step", baseVO);
			return res;
		}
		
	//댓글 추가
		public int reply(QaVO vo) {
			int res = sqlSession.insert("qa.qa_reply", vo);
			return res;
		}
		
	// 삭제가 가능한 글인지 비번을 대조할 selectone 오버로딩 메서드 (idx, pwd)
		public QaVO selectone(int idx, String pwd) {
			QaVO vo = null;
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("qa_idx", idx);
			map.put("pwd", pwd);
			
			vo = sqlSession.selectOne("qa.qa_idx_pwd", map);
			
			return vo;
			
		}
}
