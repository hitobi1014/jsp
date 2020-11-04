

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration	//스프링 컨테이너를 웹기반에서 동작하는 컨테이너로 생성하는 옵션 => @Controller, @RequestMapping을 썻기때문에
public class WebTestConfig {
	
//테스트 대상 클래스 : LoginController
	//						-->  MemberService	주입이 필요
	//							--> MemberDao(Repository)	
	//LoginController 스프링 빈을 생성하기위해서는 MemberService, MemberDao (Repository) 스프링 빈이 필요
	//즉 Service, repository 빈을 스캔하는 설정과 controller를 스캔하는 설정 두 개가 필요
	
	//스프링 프레임워크의 컨트롤러 테스트 시나리오
	//1. 웹기반의 스프링 컨테이너를 구성후
	//2. dispatcherServlet역할을 하는 객체 생성
	//3. dispatcherServlet역할을 하는 객체를 통해 url, 파라미터 등을 첨부하여 요청 전송
	//4. 응답이 원하는 형태로 나오는지 체크(viewName, model에 담긴 속성)
	
	@Autowired
	protected WebApplicationContext context;
	protected MockMvc mockMvc; 	//dispatcher servlet 역할을 하는 객체
	
	// Before(setup) => Test => After(tearDown) <- 많이 쓰는 함수명
	@Before
	public void setup()	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//get(),post() : get/post 요청
	//param(파라미터명, 파라미터값) : 요청시 보낼 파라미터

	//status() : 스프링 프레임워크에 의해 요청이 처리되고 생성된 응답 코드
	//view() : 스프링 프레임워크에 의해 요청이 처리되는 과정에서 반환된 viewName
	//model() : 컨트롤러에서 설정한 속성값을 담는 객체
	//request() : 요청 객체
		
	@Ignore
	@Test
	public void test() {
		
	}

}
