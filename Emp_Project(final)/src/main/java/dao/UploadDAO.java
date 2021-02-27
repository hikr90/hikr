package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.UploadVO;

public class UploadDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 첫 실행시의 행수 파악
	public int getRowTotal() {	
		int count = sqlSession.selectOne("upload.upload_count");
		return count;
	}
	
	// 첫 실행시의 리스트
	public List<UploadVO> selectList(HashMap<String, Integer> map) {
		List<UploadVO> upload_list = sqlSession.selectList("upload.upload_list_condition", map);
		return upload_list;
	}
	
	// 검색 단어가 있는 경우의 행수
	public int getRowTotal_sub_nn(HashMap<String, Object> map) {
		int count = sqlSession.selectOne("upload.upload_count_sub_nn", map);
		return count;
	}
	
	// 검색 단어가 있는 경우의 리스트
	public List<UploadVO> sub_nn_list(HashMap<String, Object> map){
		List<UploadVO> upload_list = sqlSession.selectList("upload.sub_nn_list", map);
		return upload_list;
	}
	
	// 파일 업로드
	public int upload_insert(UploadVO vo) {
		int res = sqlSession.insert("upload.upload_insert", vo);
		return res;
	}
	
	// VIEW에서 상세조회
	public UploadVO upload_view(int upload_idx) {
		UploadVO vo = sqlSession.selectOne("upload.upload_view", upload_idx);
		return vo;
	}
	
	// 조회수 증가
	public int update_readhit(int upload_idx) {
		int res = sqlSession.update("upload.update_readhit", upload_idx);
		return res;
	}
	
	// 게시글 삭제
	public int upload_del(int upload_idx) {
		int res = sqlSession.delete("upload.upload_del", upload_idx);
		return res;
	}
	
	// 게시글 수정
	public int upload_update(UploadVO vo) {
		int res = sqlSession.update("upload.upload_update", vo);
		return res;
	}
}
