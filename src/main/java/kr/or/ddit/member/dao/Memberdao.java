package kr.or.ddit.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

@Repository("memberDao")
public class Memberdao implements MemberDaoI {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVo getMember(String userid) {
		MemberVo mvo = sqlSession.selectOne("member.getMember",userid);
		return mvo;
	}

	@Override
	public List<MemberVo> selectAllMember() {
		List<MemberVo> memberList = sqlSession.selectList("member.selectAllMember");
		return memberList;
	}

	@Override
	public List<MemberVo> selectAllMemberPage(SqlSession sqlSession, PageVo pageVo) {
		return sqlSession.selectList("member.selectAllMemberPage", pageVo);
	}

	@Override
	public int selectMemberTotalCount(SqlSession sqlSession) {
		return sqlSession.selectOne("member.selectMemberTotalCount");
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return sqlSession.insert("member.insertMember",memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		int deleteCnt = sqlSession.delete("member.deleteMember",userid);
		
		if(deleteCnt ==1) {
//			sqlSession.commit();
		}
		else {
//			sqlSession.rollback();
		}
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		int updateCnt = sqlSession.update("member.updateMember",memberVo);
		
		if(updateCnt ==1) {
//			sqlSession.commit();
		}
		else {
//			sqlSession.rollback();
		}
		return updateCnt;
	}

}
