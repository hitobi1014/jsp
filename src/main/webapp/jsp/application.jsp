<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("getServerInfo() : " + application.getServerInfo()+"<br>");
	out.println("application.getContextPath() : " + application.getContextPath()+"<br>");
	out.println("application.getEffectiveMajorVersion() : " + application.getEffectiveMajorVersion()+"<br>");
	out.println("application.getEffectiveMinorVersion() : " + application.getEffectiveMinorVersion()+"<br>");
	
	out.println("<br>");
	out.println("web.xml에 설정 정보(초기화 파라미터 - initparameter)를 불러 올 수도 있다<br>");
	String dbUserId = application.getInitParameter("dbUserId");
	out.println("dbUserId : " + dbUserId);
%>
</body>
</html>