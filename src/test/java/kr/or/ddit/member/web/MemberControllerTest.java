package kr.or.ddit.member.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;


public class MemberControllerTest extends WebTestConfig{
	
	@Test
	public void MemberListTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/member/list"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("/member/memberList", mav.getViewName());
				
	}
	
	@Test
	public void registViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/member/registView"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("/member/memberRegist", mav.getViewName());
	}
	
	@Test
	public void registTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/member/regist")
				.param("userid","test1111" )
				.param("pass", "sadfasf")
				.param("usernm", "testsetse")
				.param("alias", "fdczxbzxbc")
				.param("addr1", "dasagczxbb")
				.param("addr2", "1421235235")
				.param("zipcode", "12412421"))
				.andExpect(status().is3xxRedirection())
				.andDo(print())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("redirect:/member/list", mav.getViewName());
	}
	
	@Test
	public void selectTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/member/select").param("userid", "brown"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("/member/memberSelect", mav.getViewName());
	}
	
	@Test
	public void updateViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/member/updateView").param("userid", "brown"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("/member/memberUpdate", mav.getViewName());
	}
	
	@Test
	public void updateTest() throws Exception {
		String userid="brown";
		MvcResult result = mockMvc.perform(post("/member/update")
				.param("userid", userid)
				.param("pass", "adsgasdg"))
				.andExpect(status().is3xxRedirection())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("redirect:/member/select?userid="+userid, mav.getViewName());
	}
	
}
