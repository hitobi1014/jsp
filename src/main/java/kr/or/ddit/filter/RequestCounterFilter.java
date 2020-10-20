package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestCounterFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(RequestCounterFilter.class);
	private Map<String, Integer> requestCounterMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("RequestCounterFilter.init()");
		requestCounterMap = new HashMap<>();
		// request, session, application
		ServletContext sc = filterConfig.getServletContext();
		sc.setAttribute("requestCounterMap", requestCounterMap);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("RequestCounterFilter.doFilter()");
		
		HttpServletRequest req = (HttpServletRequest)request;
		logger.debug("uri : {}",req.getRequestURI());
		
		//uri별 요청 횟수
		//어떤 자료구조를 써야할지 .. 요구사항 ex) /memberList : 12 .... /main.jsp : 20 ... 
		//List set Map 
		//map객체에서 uri에 해당하는 요청이 있었는지 확인 , 없으면 값을 1로 해서 새로운 key로 추가, 있으면 기존 값에서 +1 더해서 값을 수정
		Integer value = requestCounterMap.get(req.getRequestURI());
		if(value == null) {
			requestCounterMap.put(req.getRequestURI(),1);
		}
		else {
			requestCounterMap.put(req.getRequestURI(),value+1);
		}
		
		request.setAttribute("requestCounterMap",requestCounterMap);
		
//		logger.debug("요청횟수 : {}",requestCounterMap.get(req.getRequestURI()));
		//등록된 다른 필터로 요청을 위임
		//만약 더 이상 등록된 필터가 없을경우 요청을 처리할 서블릿 / jsp으로 요청을 전달
		// 전처리 : 요청이 서블릿 실행되기전
		logger.debug("RequestCounterFilter 전처리 부분-chain.doFilter 호출전");
		chain.doFilter(request, response);
		// 후처리 : 요청이 서블릿 실행된후
		logger.debug("RequestCounterFilter 전처리 부분-chain.doFilter 호출후");
	}

	@Override
	public void destroy() {
	}

}
