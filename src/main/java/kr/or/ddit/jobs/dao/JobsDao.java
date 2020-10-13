package kr.or.ddit.jobs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.jobs.model.JobsVo;

public class JobsDao implements JobsDaoI {
	private SqlSession sqlSession;
	
	public JobsDao() {
		sqlSession = MybatisUtil.getSqlSession();
	}
	
	@Override
	public List<JobsVo> getAllJobs() {
		List<JobsVo> jobsList = sqlSession.selectList("jobs.getAllJobs");
		sqlSession.close();
		return jobsList;
	}

}
