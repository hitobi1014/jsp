package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

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
		
		assertEquals(answerMemberVo, memberVo);
	}
	
	@Test
	public void getAllMemberTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();

		/***When***/
		List<MemberVO> memList = memberDao.getAllMember();
		/***Then***/
		assertEquals(5, memList.size());
		
	}

}
