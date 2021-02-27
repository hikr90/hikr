package dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import vo.EmpVO;

public class LoginDAO {

	SqlSession sqlSession; 

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession; 
	}

	// id를 통한 로그인 정보 확인
	public EmpVO login_one(String id) {
		EmpVO vo = sqlSession.selectOne("login.login_one", id);
		return vo;
	}
	
	// 아이디를 찾는 메서드
	public EmpVO find_id(HashMap<String, String> map) {
		EmpVO BaseVO = sqlSession.selectOne("login.find_id", map);
		return BaseVO;
	}
	
	// 비밀번호 찾는 메서드
	public EmpVO find_pwd(HashMap<String, String> map) {
		EmpVO BaseVO = sqlSession.selectOne("login.find_pwd", map);
		return BaseVO;
	}

	// 관리자 회원가입
	public int mgr_insert(EmpVO vo) {
		int res = sqlSession.insert("login.mgr_insert", vo);
		return res;
	}
	
}
