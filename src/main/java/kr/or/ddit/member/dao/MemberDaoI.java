package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;

public interface MemberDaoI {
	MemberVo getMember(String userid);
	
	List<MemberVo> selectAllMember();
	
	List<MemberVo> selectAllMemberPage(PageVo pageVo);
	
	int selectMemberTotalCount();
	
	int insertMember(MemberVo memberVo);
	
	int deleteMember(String userid);
	
	int updateMember(MemberVo memberVo);
}
