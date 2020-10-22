package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

public class MemberDao implements MemberDaoI{

	@Override
	public MemberVO getMember(String userId) {
		// 원래는 db에서 데이터를 조회하는 로직이 있어야 하나
		// 우리는 controller기능에 집중 => 하드코딩을 통해 dao, service는 간략하게 넘어간다
		//	Mock (가짜)
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		MemberVO memberVO = sqlSession.selectOne("member.getMember", userId);
		sqlSession.close();
		return memberVO;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<MemberVO> memberList = sqlSession.selectList("member.selectAllMember");
//		sqlSession.commit();		// insert, update, delete할때는 이것을 수행해주고 그다음에 close를 수행해주어야 한다.
//		sqlSession.rollback();
		sqlSession.close();
		return memberList;
	}

	@Override
	public List<MemberVO> selectAllMemberPage(SqlSession sqlSession, PageVO pageVo) {
		return sqlSession.selectList("member.selectAllMemberPage", pageVo);
	}

	@Override
	public int selectMemberTotalCount(SqlSession sqlSession) {
		return sqlSession.selectOne("member.selectMemberTotalCount");
	}

	@Override
	public int insertMember(MemberVO memberVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		try {
			insertCnt = sqlSession.insert("member.insertMember",memberVo);
		} catch (Exception e) {
			
		}
		// 트랜잭션을 명시적으로 끝내줘야함
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
	public int updateMember(MemberVO memberVo) {
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
