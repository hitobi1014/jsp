package kr.or.ddit.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.PageVo;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@RequestMapping(path="list")
	public String memberList(Model model
			,@RequestParam(name="page",required = false,defaultValue = "1") int page
			,@RequestParam(name="pageSize",required = false,defaultValue = "7") int pageSize) {
		
		logger.debug("page : {} / pageSize : {}",page,pageSize);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		
		PageVo pageVo = new PageVo(page,pageSize);
		
		Map<String, Object> map = memberService.selectAllMemberPage(pageVo);
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "/member/memberList";
	}
	
	@RequestMapping(path="registView")
	public String registView(){
		return "/member/memberRegist";
	}
	
	@RequestMapping(path="regist")
	public String regist(MemberVo memberVo) {
		// 파일업로드
		if(memberVo.getFile() != null) {
			String filePath = "d:\\upload\\"+memberVo.getFile().getOriginalFilename();
			File uploadFile = new File(filePath);
			try {
				memberVo.getFile().transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberVo.setFilename(filePath);
			memberVo.setRealfilename(memberVo.getFile().getOriginalFilename());
		}
		logger.debug("memberVo : {}", memberVo);
		
		memberService.insertMember(memberVo);
		return "redirect:/member/list";
	}
	
	@RequestMapping(path="select")
	public String select(String userid, Model model) {
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("memberVo", memberVo);
		return "/member/memberSelect";
	}
	
	@RequestMapping(path="updateView")
	public String updateView(String userid, Model model) {
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("memberVo", memberVo);
		return "/member/memberUpdate";
	}
	
	@RequestMapping(path="update")
	public String update(MemberVo memberVo) throws UnsupportedEncodingException {
		if(memberVo.getFile()!=null) {
			String filePath = "d:\\upload\\"+memberVo.getFile().getOriginalFilename();
			File uploadFile = new File(filePath);
			try {
				memberVo.getFile().transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberVo.setFilename(filePath);
			memberVo.setRealfilename(memberVo.getFile().getOriginalFilename());
		}
//		logger.debug("수정memberVo : {}", memberVo);
		String userid=URLEncoder.encode(memberVo.getUserid(),"utf-8");
		memberService.updateMember(memberVo);
		return "redirect:/member/select?userid="+userid;
	}
	
	@RequestMapping(path="profileImg")
	public void profileImg(String userid, HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		MemberVo memberVo = memberService.getMember(userid);
		FileInputStream fis = new FileInputStream(memberVo.getFilename());
		ServletOutputStream sos = response.getOutputStream();
		byte[] buffer = new byte[512];
		while(fis.read(buffer)!= -1) {
			sos.write(buffer);
		}
		fis.close();
		sos.flush();
		sos.close();
	}
	
	@RequestMapping(path="filedown")
	public void fileDown(String userid, HttpServletResponse response) throws IOException {
		MemberVo memberVo = memberService.getMember(userid);
		FileInputStream fis = new FileInputStream(memberVo.getFilename());
		response.setHeader("Content-Disposition", "attachment; filename=\""+memberVo.getRealfilename()+"\"");
		response.setContentType("application/octet-stream");
		ServletOutputStream sos = response.getOutputStream();
		byte[] buffer = new byte[512];
		while(fis.read(buffer)!=-1) {
			sos.write(buffer);
		}
		fis.close();
		sos.flush();
		sos.close();
	}
}
