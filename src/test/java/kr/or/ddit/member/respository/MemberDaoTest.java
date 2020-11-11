package kr.or.ddit.member.respository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

public class MemberDaoTest extends ModelTestConfig{

	
	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		
		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();

		/***Then***/
//		assertTrue(memberList.size() > 13);
		assertEquals(16, memberList.size());
	}
	
	@Test
	public void getMemberTest() {
		/***Given***/
		String userid="brown";

		/***When***/
		MemberVo memberVo = memberDao.getMember(userid);

		/***Then***/
		assertEquals("브라운", memberVo.getUsernm());
	}
	
	@Test
	public void selectAllMemberPageTest() {
		/***Given***/
		int page = 1;
		int pageSize = 5;
		PageVo pageVo = new PageVo(page, pageSize);

		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMemberPage(pageVo);

		/***Then***/
		assertEquals(5, memberList.size());
	}
	
	@Test
	public void selectMemberTotalCountTest() {
		/***Given***/

		/***When***/
		int totalCount = memberDao.selectMemberTotalCount();

		/***Then***/
//		assertTrue(totalCount >= 16);
		assertEquals(16, totalCount);
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("testunit", "testunit", "테스트", "테스트", null
					, "", "", "", "", "", null);

		/***When***/
		int insertCnt = memberDao.insertMember(memberVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid="gana";

		/***When***/
		int deleteCnt = memberDao.deleteMember(userid);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("gana", "123", "가", "나", null
				, "", "", "", "", "", null); 

		/***When***/
		int updateCnt = memberDao.updateMember(memberVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
