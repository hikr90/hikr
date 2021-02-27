package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체 게시물 조회
	public List<BoardVO> selectList(){
		List<BoardVO> list = sqlSession.selectList("board.board_list");
		return list;
	}
	
	//공지사항 글 등록
	public int insert(BoardVO vo) {
		int res = sqlSession.insert("board.board_insert",vo);
		return res;
	}
	
	//게시물 한건 조회
	public BoardVO selectOne(int idx) {
		BoardVO vo = null;
		vo = sqlSession.selectOne("board.board_one",idx);
		
		return vo;
	}
	
	//조회수 증가
	public int update_readhit(int idx) {
		int res = sqlSession.update("board.board_readhit", idx);
		return res;
	}
	
	//게시물 삭제
	public int board_del(int idx) {
		int res = sqlSession.delete("board.board_del", idx);
		return res;
	}
	
	//게시물 수정
	public int update(BoardVO vo) {
		int res = sqlSession.update("board.board_update", vo);
		return res;
	}
	
	//페이지별 게시물 조회
	public List<BoardVO> selectlist(HashMap<String, Integer> map){
		List<BoardVO> list = sqlSession.selectList("board.board_list_page", map);
		return list;
	}
	
	//전체 게시물 수 구하기
	public int getRowTotal() {
		int count = sqlSession.selectOne("board.board_count");
		return count;
	}
	
	
	
	
	
}
