<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	==> contextPath : jsp로 설정되어있음
	http://localhost/jsp/test.jsp
	jsp2 프로젝트의 webapp/test.jsp<br>
	
	jsp 프로젝트(contextPath ROOT(/))의 webapp/jsp/test.jsp를 접근하기 위해서는	http://localhost/jsp/test.jsp로 요청<br>
	
	jsp2 프로젝트(contextPath jsp))의 webapp/test.jsp를 접근하기 위해서는 http://localhost/jsp/test.jsp로 요청.<br>
	
	즉 두개의 리소스가 동일한 url을 갖는다
	이럴 때 contextPath를 우선시하여 처리 (이게 중요한것은 x)<br>
	
	url만 보고서 경로에 나오는 path가 contextPath인지, webapp 폴더 하위의 디렉토링 인지는 알 수가 없음<br>
</body>
</html>