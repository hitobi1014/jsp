package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

@Service("memberService")
public class MemberService implements MemberServiceI {

	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	@Override
	public MemberVo getMember(String userid) {
		return memberDao.getMember(userid);
	}

	@Override
	public List<MemberVo> selectAllMember() {
		return memberDao.selectAllMember();
	}

	@Override
	public Map<String, Object> selectAllMemberPage(PageVo pageVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberList", memberDao.selectAllMemberPage(sqlSession, pageVo));

		// 15건, 페이지 사이즈를 7로 가정했을때 3개의 페이지가 나와야한다
		// 15/7 = 2.14... 올림을 하여 3개의 페이지가 필요
		int totalCount = memberDao.selectMemberTotalCount(sqlSession);
		int pages = (int)Math.ceil((double)totalCount/pageVo.getPageSize());
		map.put("pages", pages);

		sqlSession.close();
		return map;
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return memberDao.insertMember(memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		return memberDao.deleteMember(userid);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return memberDao.updateMember(memberVo);
	}

}
