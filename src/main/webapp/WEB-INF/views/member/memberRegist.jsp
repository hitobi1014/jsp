<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Jsp</title>
<%@ include file="/WEB-INF/views/layout/commonLib.jsp"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	$("#zipcodeBtn").on('click',function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            $("#addr1").val(data.roadAddress);
	            $("#zipcode").val(data.zonecode);
	        }
	    }).open();
	});

	$("#regBtn").on('click',function(){
		//client side - validation
		//server side - validation
		//validation 로직은 일단 생략
		$("#frm").submit();
	});
	
// 	initData();
	
});

function initData(){
	$("#userid").val("cmj");
	$("#usernm").val("최민준");
	$("#alias").val("dalsam");
	$("#pass").val("1234");
	$("#addr1").val("대전 중구 중앙로 76");
	$("#addr2").val("영민빌딩 4층 404호");
	$("#zipcode").val("34940");
}

</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="/WEB-INF/views/layout/left.jsp" %>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form id="frm" class="form-horizontal" role="form" action="${cp}/member/regist" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="realfilename" class="col-sm-2 control-label">사용자 사진</label>
					<div class="col-sm-10">
						<input type="file" name="file"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="userid" name="userid" placeholder="아이디" value="${param.userid}"/>
					</div>
				</div>

				<div class="form-group">
					<label for="usernm" class="col-sm-2 control-label">사용자 이름</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="usernm" name="usernm" placeholder="이름" value="${param.usernm}"/>
						<span style="color:red;"><form:errors path="memberVo.usernm"/></span>
						<span style="color:red;"><form:errors path="jSRMemberVo.usernm"/></span>
					</div>
				</div>
				<div class="form-group">
					<label for="alias" class="col-sm-2 control-label">별명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="alias" name="alias" placeholder="별명" value="${param.alias}"/>
					</div>
				</div>
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" value="${param.pass}"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="addr1" class="col-sm-2 control-label">주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="addr1" name="addr1" readonly="readonly"/>
						<button id="zipcodeBtn" type="button" class="btn btn-default" value="${param.addr1}">우편번호 찾기</button>
					</div>
				</div>
				
				<div class="form-group">
					<label for="addr2" class="col-sm-2 control-label">상세주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value="${param.addr2}"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="zipcode" name="zipcode" readonly="readonly" value="${param.zipcode}"/>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="regBtn" type="button" class="btn btn-default">사용자 등록</button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
	</div>
</body>
</html>
