package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVO;

public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	MemberServiceI memberService;
	
	@Before
	public void setup() {
		memberService = new MemberService(); 
		String userid = "cmj";
		memberService.deleteMember(userid);
	}
	
	@Test
	public void getMembertest() {
		/***Given***/
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
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVO memberVo = new MemberVO("cmj","1234","최민준","dal","대전 중구 중앙로","영민빌딩","34960","D:\\profile\\cony.png","cony.png");
		/***When***/
		int insertCnt = memberService.insertMember(memberVo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
}
