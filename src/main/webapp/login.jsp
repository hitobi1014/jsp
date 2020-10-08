<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <link rel="icon" href="../../favicon.ico">
    <title>Signin Template for Bootstrap</title>
    
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/css/signin.css" rel="stylesheet">
  </head>

  <body>
  	
    <div class="container">
      <form class="form-signin" action="<%=request.getContextPath()%>/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="userId" class="form-control" placeholder="Email address" required autofocus value="brown">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required value="passBrown">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="btn">Sign in</button>
      </form>
    </div> <!-- /container -->

  </body>
</html>
