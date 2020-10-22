package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

public class MemberDaoTest {
	
	//테스트 메소드 : @Test 
	// @Before => @Test => @After 순서
	// @BeforeClass(static) / @AfterClass (static) 테스트 코드가 실행되기전/후 한번만실행되는 어노테이션
	// 
	MemberDaoI memberDao;
	@Before
	public void setup() {
		memberDao = new MemberDao();
		String userid = "cmj";
		memberDao.deleteMember(userid);
	}

	@Test
	public void getMembertest() {
		/***Given***/
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

		/***When***/
		List<MemberVO> memList = memberDao.selectAllMember();
		/***Then***/
		assertEquals(15, memList.size());
		
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		/***When***/
		int totalCnt = memberDao.selectMemberTotalCount(sqlSession);
		/***Then***/
		assertEquals(15, totalCnt);
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVO memberVo = new MemberVO("cmj","1234","최민준","dal","대전 중구 중앙로","영민빌딩","34960","D:\\profile\\cony.png","cony.png");
		/***When***/
		int insertCnt = memberDao.insertMember(memberVo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
}
