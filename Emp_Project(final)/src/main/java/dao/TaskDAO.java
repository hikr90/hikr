package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.TaskVO;

public class TaskDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// EMP_IDX에 따른 TASK를 불러오기
	public List<TaskVO> task_one(int emp_idx) {
		
		List<TaskVO> task_list = sqlSession.selectList("task.task_one", emp_idx);
		
		return task_list;
	}
	
	// TASK_IDX에 따른 TASK내용 삭제
	public int task_del(int task_idx) {
		int res = sqlSession.delete("task.task_del", task_idx);
		return res;
	}
	
	// EMP_IDX를 통해서 추가
	public int task_insert(TaskVO vo) {
		int res = sqlSession.insert("task.task_insert", vo);
		return res;
	}
	
}
