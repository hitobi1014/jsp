package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest extends ModelTestConfig{

	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@Test
	public void insertMember_SUCCESS_Test() {
		/***Given***/
		MemberVo memberVo = new MemberVo("gana", "choco", "초콜릿", "가나", null, "아프리카", "가나", "", "", "", null);

		/***When***/
		int insertCnt = memberService.insertMember(memberVo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	//@Test 테스트를 더 이상 안함
	public void insertMember_FAIL_Test() {
		/***Given***/
		MemberVo memberVo = new MemberVo("gana", "choco", "초콜릿", "가나", null, "아프리카", "가나", "", "", "", null);
		
		/***When***/
		int insertCnt = memberService.insertMember(memberVo);
		/***Then***/
		assertEquals(1, insertCnt);
	}

}
