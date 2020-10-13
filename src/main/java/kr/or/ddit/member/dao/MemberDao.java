package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

public class MemberDao implements MemberDaoI {
	
	@Override
	public MemberVO getMember(String userId) {
		// 원래는 DB에서 데이터를 조회하는 로직이 있어야 하나 controller 기능에 집중 하기 위해
		// 하드코딩을 통해 dao, service는 간략하게 넘어감 ==> Mock 객체 (가짜)
		
//		MemberVO memberVo = new MemberVO();
//		memberVo.setUserId("brown");
//		memberVo.setPassword("passBrown");
		
		//select (조회시) 
		//한 건 : selectOne
		//여러 건 : selectList
		SqlSession sqlSession =  MybatisUtil.getSqlSession();
		MemberVO memberVo =  sqlSession.selectOne("member.getMember", userId);
		sqlSession.close();
		return memberVo;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession sqlSession =  MybatisUtil.getSqlSession();
		List<MemberVO> memList = sqlSession.selectList("member.getAllMember");
		//insert, update, delete 데이터 정보가 변경되는 부분이 있으면 commit이나 rollback 작업으로 트랜잭션 확정처리를 해야한다
		sqlSession.commit(); 
		sqlSession.close();
		return memList;
	}

	
}
