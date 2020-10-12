package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVO;

public class MemberServiceTest {
	
	@Test
	public void getMembertest() {
		/***Given***/
		MemberServiceI memberService = new MemberService(); 
		String userId = "brown";
		
		MemberVO answerMemberVo = new MemberVO();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");
		/***When***/
		MemberVO memberVo = memberService.getMember(userId);
		/***Then***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());
		
		assertEquals(answerMemberVo, memberVo);
	}
	
}
