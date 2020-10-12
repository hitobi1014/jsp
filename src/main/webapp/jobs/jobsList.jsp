<%@page import="kr.or.ddit.jobs.model.JobsVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<JobsVo> jobsList = (List<JobsVo>) request.getAttribute("jobsList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>job_id</th>
			<th>job_title</th>
		</tr>
		<%
			for(int i=0; i<jobsList.size(); i++){
		%>
		<tr>
			<td><%=jobsList.get(i).getJob_id() %></td>
			<td><%=jobsList.get(i).getJob_title() %></td>
		</tr>
		<%		
			}
		%>
	</table>
</body>
</html>