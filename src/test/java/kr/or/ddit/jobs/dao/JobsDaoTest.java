package kr.or.ddit.jobs.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.jobs.model.JobsVo;

public class JobsDaoTest {

	@Test
	public void getAllJobstest() {
		/***Given***/
		JobsDaoI jobsDao = new JobsDao(); 

		/***When***/
		List<JobsVo> jobsList = jobsDao.getAllJobs();
		/***Then***/
		assertEquals("President", jobsList.get(0).getJob_title());
	}

}
