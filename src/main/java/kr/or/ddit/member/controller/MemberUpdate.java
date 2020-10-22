package kr.or.ddit.member.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileupload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/memberUpdate")
@MultipartConfig()
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberUpdate.class);
	private MemberServiceI memberService;
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberVO mvo = memberService.getMember(userid);
		
		logger.debug("parameter : {}, {}, {}, {}, {},{}, {}, {}, {}, {}",mvo.getUserid(),mvo.getUsernm(),mvo.getPass(),mvo.getReg_dt(),mvo.getAlias(),mvo.getAddr1(),
				mvo.getAddr2(),mvo.getZipcode(),mvo.getFilename(),mvo.getRealfilename());
		request.setAttribute("memberVo", mvo);
		request.getRequestDispatcher("/member/memberUpdate.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String usernm = request.getParameter("usernm");
		String alias = request.getParameter("alias");
		String pass = request.getParameter("pass");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		logger.debug("parameterUpdate : {},{},{},{},{},{},{}", userid, usernm, alias, pass, addr1, addr2, zipcode);
		
		Part profile = request.getPart("realfilename");
		logger.debug("profile : {} ",profile.getHeader("Content-Disposition"));
		
		String realFilename = FileUploadUtil.getFileName(profile.getHeader("Content-Disposition"));
		logger.debug("리얼파일이름: {}",realFilename );
		
		String fileName = UUID.randomUUID().toString();
		logger.debug("파일 이름: {}",fileName );
		
		String extension = FileUploadUtil.getExtension(realFilename);
		logger.debug("파일 확장자: {}",extension);
		
		String filePath = "";
		
		if(profile.getSize() > 0) { 
			filePath = "D:\\profile\\" + fileName + "."+extension;
			profile.write(filePath);
		}
		logger.debug("파일 경로 : {}",filePath);
		if (filePath == null || filePath.equals("")) {
			realFilename = request.getParameter("realfilename2");
			filePath = request.getParameter("filename");
		}
		// 사용자 정보 등록
		MemberVO memberVo = new MemberVO( userid, pass, usernm, alias, addr1, addr2, zipcode,filePath, realFilename);
		int updateCnt = memberService.updateMember(memberVo);
		
		//1건이 입력되었을때 : 정상 - memberList페이지로 이동
		if(updateCnt >0) {
			logger.debug("정보수정 성공");
			response.sendRedirect(request.getContextPath() + "/member?userid="+userid);
		}
		//1건이 아닐때 : 비정상 - 사용자가 데이터를 다시 입력할 수 있도록 등록페이지로 이동
		else {
			logger.debug("정보수정 실패");
		}
		
	}
	
}
