<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$('input[type=radio]').on('input',function(){
		method = $(this).val();
		console.log(method);
		$('#fm').attr('method',method);
	})
	
})

</script>
</head>
<body>
	<%-- 
		파라미터 : client 서버로 요청을 보낼때 추가적으로 보낸 값 
					ex : 로그인 요청시 : 사용자 id, 비밀번호
		파라미터는 내부적으로 Map<String, String[]> 객체로 관리를 한다			
								파라미터이름, 파라미터 값들
								==> 동일한 이름의 파라미터를 여러개 보낼 수 있다
		request 객체에 있는 파라미터 관련 메소드 4가지
		String getParameter(String param) : param에 해당하는 파라미터 값을 조회한다								
											파라미터맵에서 첫번째 값을 반환
		String[] getParameterValues(String param) : param에 해당하는 모든 파라미터 값을 반환한다
		Map<String, String[]> getParameterMap() : request 객체에 생성된 파라미터 맵을 반환
		Enumeration<String> getParameterNames() : request 객체에 담긴 모든 파라미터 이름을 반환		 											
	--%>
	
	<%--
		 한글 파라미터 설정
		get : server.xml <Connector URIEncoding="utf-8" ... >
		post : request.setCharacterEncoding("utr-8");
				request.getParameter()메소드를 호출하기전에 설정해줘야함
	 --%>
	<%-- 
		action : 요청을 보낼 경로
		method : 요청 방식(form에서는 GET, POST 두가지만 가능하며 DEFAULT는 GET)
	 --%>
	GET POST 두 가지를 선택할 수 있는 라디오 버튼을 만들어서
	FORM 전송시 사용자가 GET, POST방식을 지정할 수 있도록
	<h2> method 방식 선택</h2>
	<input type="radio" name="method" value="GET">GET <br>
	<input type="radio" name="method" value="POST">POST <br>
	
	<form id="fm" action="${pageContext.request.contextPath }/request/getRequestResponse.jsp" method="">
		user id : <input type="text" name="userId" value="어피치"><br>
		user id : <input type="text" name="userId" value="라이언"><br>
		password : <input type="password" name="password" value="a1234"><br>
		<input type="submit" value="전송">
	</form>
	
	
</body>
</html>