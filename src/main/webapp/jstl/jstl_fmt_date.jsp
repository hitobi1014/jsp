<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Date now = new Date();
		request.setAttribute("now"	, now);
	%>
	<%--
		us : mm-dd-yyyy
		ko : yyyy.mm.dd ==> DB에서 주로 사용방식 yyyy-mm-dd, yyyy/mm/dd
	 --%>
<%-- 	<fmt:setLocale value="en_us"/> --%>
	now : ${now }<br>
	now - formatDate : <fmt:formatDate value="${now }" /><br>
	now - formatDate-pattern : <fmt:formatDate value="${now }" pattern="YYYY-MM-dd"/><br>
	now - formatDate-dateStyle: <fmt:formatDate value="${now }" dateStyle="short"/><br><br>
	
	<%-- 문자 ==> 날짜 
		요구사항 : "2020-10-19 10:15" 문자열을 날짜 타입의 객체로 변환
	--%>
	<% request.setAttribute("nowStr", "2020-10-19 10:15"); %>
	parse Date : <fmt:parseDate value="${nowStr}" pattern="yyyy-MM-dd HH:mm"/><br>
</body>
</html>