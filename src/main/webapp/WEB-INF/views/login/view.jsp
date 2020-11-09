<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<script src="<%= request.getContextPath() %>/js/js.cookie-2.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	var rememberMe = Cookies.get("REMEMBERME");
	var userNm = Cookies.get("USERNM");
	if(rememberMe == 'Y'){
		$('#inputEmail').val(userNm);
		$('.checkbox input[type=checkbox]').prop('checked',true);
	}

	$('#btn').on('click',function(){
		if($('.checkbox input[type=checkbox]').prop('checked')){
			Cookies.set('REMEMBERME',"Y");
			Cookies.set('USERNM',$('#inputEmail').val());
		}else{
			Cookies.remove('REMEMBERME')
			Cookies.remove('USERNM');
		}

		// submit 하기
		$('form').submit();
	})
})
	function getCookieValue(cookieName){
		var cookies = document.cookie.split("; ");
		var cookieNames = null;
		var cookieValue = "";
		for(i=0; i<cookies.length; i++) {
			cookieNames = cookies[i].split("=");
			if(cookieName==cookieNames[0]) {
				cookieValue = cookieNames[1];
				return cookieValue;
			}
		}
// 		console.log(cookieValue);
	}

	function setCookie(cookieName, cookieValue, expires){
// 		"USERNM=brown; path=/; expires=Wed, 07 Oct 2020 00:39:19 GMT;"
		var today = new Date();

		// 현재날짜에서 (+) expires 더한 날짜만큼 구하기
		today.setDate(today.getDate() + expires)
		document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString();
		console.log(document.cookie);
	}

	// 해당 쿠키의 expires속성을 과거 날짜로 변경
	function deleteCookie(cookieName){
		setCookie(cookieName,"", -1);
	}
</script>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="icon" href="../../favicon.ico"> -->
    <title>Signin Template for Bootstrap</title>
    
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
  </head>

  <body>
  	msg : ${msg} <br>
  	msg_s : ${msg_s}<br>
  	msg_ra : ${msg_ra }<br>
  	msg_ra_attr : ${msg_ra_attr}<br>
  	<c:remove var="msg_s" scope="session"/>
    <div class="container">
      <form class="form-signin" action="<%=request.getContextPath() %>/login/process" method="post">
        <h2 class="form-signin-heading"><spring:message code="login.signin"/></h2>
        <label for="inputEmail" class="sr-only"><spring:message code="login.userid"/> </label>
        <input type="email" id="inputEmail" name="userid" class="form-control" placeholder="Email address" required autofocus value="brown">
        <label for="inputPassword" class="sr-only"><spring:message code="login.password"/> </label>
        <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Password" required value="brownPass">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> <spring:message code="login.rememberme"/>
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="btn" >Sign in</button>
      </form>
    </div> <!-- /container -->

  </body>
</html>
