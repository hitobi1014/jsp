<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/sumCalculation" method="post">
	<label for="start">
		<input id="start" type="text" name="start" placeholder="숫자입력">
	</label><br>
	<label for="end">
		<input id="end" type="text" name="end" placeholder="숫자입력">
	</label><br>
	<input type="submit">
</form>
</body>
</html>