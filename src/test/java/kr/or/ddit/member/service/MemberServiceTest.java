package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVO;

public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
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
		
		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		MemberServiceI memberService = new MemberService();
		PageVO pageVo = new PageVO(1,7);
		/***When***/
		Map<String, Object> map =  memberService.selectAllMemberPage(pageVo);
		List<MemberVO> memberList = (List<MemberVO>)map.get("memberList");
		int pages = (int)map.get("pages");
		/***Then***/
		assertEquals(7, memberList.size());
		assertEquals(3, pages);
	}
	
	@Test
	public void localeListTest() {
		Locale[] locales =  SimpleDateFormat.getAvailableLocales();
		for(Locale locale : locales) {
			logger.debug(locale.toString());
		}
	}
	
}
