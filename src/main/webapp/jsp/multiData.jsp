<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/mulCalculation" method="POST">
	<input type="text" name="param1" placeholder="첫번째 숫자입력"><br>
	<input type="text" name="param2" placeholder="첫번째 숫자입력"><br>
	<input type="submit">
</form>
</body>
</html>