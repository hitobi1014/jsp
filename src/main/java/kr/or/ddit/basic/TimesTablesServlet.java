package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimesTablesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("	<head></head>");
		writer.println("	<body>");
		for(int j=1;j<10;j++) {
			writer.println("		<table style='border-collapse: collapse;'>");
			writer.println("			<tr>");
			for(int i=2;i<10;i++) {
				writer.println("				<td style='border:1px solid black;width:70px;text-align:center;font-weight:bold;'>"+i+"*"+j +"= "+(i*j)+"</td>");
			}
			writer.println("			</tr>");
			writer.println("		</table>");
		}
		writer.println("	</body>");
		writer.println("</html>");
	}
}
