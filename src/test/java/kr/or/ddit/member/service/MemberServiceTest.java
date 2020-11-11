package kr.or.ddit.member.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

public class MemberServiceTest extends ModelTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
//	@Test
//	public void insertMember_SUCCESS_Test() {
//		/***Given***/
//		MemberVo memberVo = new MemberVo("gana2", "choco", "초콜릿", "가나", null, "아프리카", "가나", "", "", "", null);
//
//		/***When***/
//		int insertCnt = memberService.insertMember(memberVo);
//		/***Then***/
//		assertEquals(1, insertCnt);
//	}
	
	//@Test 테스트를 더 이상 안함
//	public void insertMember_FAIL_Test() {
//		/***Given***/
//		MemberVo memberVo = new MemberVo("gana", "choco", "초콜릿", "가나", null, "아프리카", "가나", "", "", "", null);
//		
//		/***When***/
//		int insertCnt = memberService.insertMember(memberVo);
//		/***Then***/
//		assertEquals(1, insertCnt);
//	}
	
	@Test
	public void getMemberTest() {
		/***Given***/
		String userid = "brown";
		/***When***/
		MemberVo memberVo = memberService.getMember(userid);
		/***Then***/
		assertEquals("브라운", memberVo.getUsernm());
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/

		/***When***/
		List<MemberVo> memberList = memberService.selectAllMember();
		/***Then***/
		assertEquals(16, memberList.size());
	}
	
	@Test
	public void selectAllMemberPage() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		/***When***/
		Map<String, Object> map = memberService.selectAllMemberPage(pageVo);
		
		/***Then***/
		logger.debug("맵페이지 : {}",map.get("pages"));
		assertEquals(4, map.get("pages"));
		assertEquals(5, ((List<MemberVo>)map.get("memberList")).size());
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("testunit", "testunit", "테스트", "테스트", null
					, "", "", "", "", "", null);

		/***When***/
		int insertCnt = memberService.insertMember(memberVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid="gana";

		/***When***/
		int deleteCnt = memberService.deleteMember(userid);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("gana", "123", "가", "나", null
				, "", "", "", "", "", null); 

		/***When***/
		int updateCnt = memberService.updateMember(memberVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
