package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

public class MemberDaoTest {

	@Test
	public void getMembertest() {
		/***Given***/
		MemberDao memberDao = new MemberDao(); 
		String userId = "brown";
		
		MemberVO answerMemberVo = new MemberVO();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");
		/***When***/
		MemberVO memberVo = memberDao.getMember(userId);
		/***Then***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());
		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();

		/***When***/
		List<MemberVO> memList = memberDao.selectAllMember();
		/***Then***/
		assertEquals(15, memList.size());
		
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();
		PageVO paveVo = new PageVO(1,7);
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		//int page = 1;
		/***When***/
		List<MemberVO> memberList = memberDao.selectAllMemberPage(sqlSession,paveVo);
		/***Then***/
		assertEquals(7, memberList.size());
	}

	@Test
	public void getMemberTotalCntTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		/***When***/
		int totalCnt = memberDao.selectMemberTotalCount(sqlSession);
		/***Then***/
		assertEquals(15, totalCnt);
	}
}
