package kr.or.ddit.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class sumCalculation
 */
@WebServlet("/sumCalculation")
public class SumCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SumCalculation.class);
	
	// 입력화면요청처리(inputData.jsp)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/inputData.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int num[] =  new int[Integer.parseInt(request.getParameter("number"))];
		int start =  Integer.parseInt(request.getParameter("start"));
		int end =  Integer.parseInt(request.getParameter("end"));
		logger.debug("start : {} / end : {}" ,start,end);
		int sum=0;
		for(int i=start ; i<=end; i++) {
			sum += i;
		}
		logger.debug("sum : {}",sum);
		HttpSession session = request.getSession();
		session.setAttribute("result", sum);
		request.getRequestDispatcher("/jsp/sumResult.jsp").forward(request, response);
	}

}
