package kr.or.ddit.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

@Repository("memberDao")
public class Memberdao implements MemberDaoI {

	@Override
	public MemberVo getMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		MemberVo mvo = sqlSession.selectOne("member.getMember",userid);
		sqlSession.close();
		return mvo;
	}

	@Override
	public List<MemberVo> selectAllMember() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<MemberVo> memberList = sqlSession.selectList("member.selectAllMember");
		sqlSession.close();
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		try {
			insertCnt = sqlSession.insert("member.insertMember",memberVo);
		} catch (Exception e) {
		}
		if(insertCnt ==1 ) {
			sqlSession.commit();
		}
		else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int deleteMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("member.deleteMember",userid);
		
		if(deleteCnt ==1) {
			sqlSession.commit();
		}
		else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("member.updateMember",memberVo);
		
		if(updateCnt ==1) {
			sqlSession.commit();
		}
		else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateCnt;
	}

}
