package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(MemberListServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberServiceI memberService = new MemberService();
		List<MemberVO> memberList =  memberService.getAllMember();
		request.setAttribute("memberList", memberList);
		logger.debug("memberList[0] : {}",memberList.get(0).getUserid());
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
	}

}
