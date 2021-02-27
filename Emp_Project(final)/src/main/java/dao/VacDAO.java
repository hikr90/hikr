package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;


import vo.EmpVO;
import vo.Emp_Vac_ViewVO;
import vo.VacVO;


public class VacDAO {

	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(VacVO vo) {

		int res = sqlSession.insert("vac.vac_insert", vo);
		return res;
	}

	//휴가 관리 뷰
	public List<Emp_Vac_ViewVO> selectViewList(){

		List<Emp_Vac_ViewVO> list = sqlSession.selectList("vac.view_select");
		return list;
	}

	//휴가 승인,거절을 위한 컬럼하나 선택
	public VacVO selectOne(int idx) {
		VacVO vo = sqlSession.selectOne("vac.vac_selectone", idx);
		return vo;
	}

	//휴가 승인
	public int recognize( VacVO vo ) {
		int res = sqlSession.update("vac.vac_recognize", vo);
		return res;
	}

	//휴가 거절
	public int reject( VacVO vo ) {
		int res = sqlSession.update("vac.vac_reject", vo);
		return res;
	}

	//휴가 승인된 뷰(달력)
	public List<Emp_Vac_ViewVO> selectViewRecList(){

		List<Emp_Vac_ViewVO> list = sqlSession.selectList("vac.view_select_rec");
		return list;
	}		
	
	public List<VacVO> selectOverlapList(HashMap<String, Object> se_map){
		
		List<VacVO> list = sqlSession.selectList("vac.vac_selectOverlaplist",se_map);
		return list;
	}
	
	// EMP_IDX에대한 행수 파악
	public int getRowTotal(int emp_idx) {
		int count = sqlSession.selectOne("vac.vac_count", emp_idx);
		return count;
	}
}
