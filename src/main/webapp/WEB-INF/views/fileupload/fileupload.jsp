<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- client form method : post
					 encType : multipart/form-data
		  server - servlet @MultipartConfig
		  		 - spring Framework multipartResolver -->
	<form action="${pageContext.request.contextPath}/fileupload/upload" enctype="multipart/form-data" method="post">
		<input type="text" name="userid" value="브라운"><br>
		<input type="file" name="file"><br>
		<input type="submit" value="전송"/>
	</form>
</body>
</html>