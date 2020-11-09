<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(function() {
		$("#zipcodeBtn").on('click', function() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					$("#addr1").val(data.roadAddress);
					$("#zipcode").val(data.zonecode);
				}
			}).open();
		});

	});
</script>
<div class="row">
tiles
	<form id="frm" class="form-horizontal" role="form"
		action="${cp}/member/update" method="post"
		enctype="multipart/form-data">
		<div class="form-group">
			<label for="realfilename" class="col-sm-2 control-label">사용자
				사진</label>
			<div class="col-sm-10">
				<img src="${cp}/profileImg?userid=${memberVo.userid}"> 
				<input type="hidden" id="filename" name="filename" value="${memberVo.filename}" /> 
				<input type="hidden" id="realfilename2" name="realfilename2" value="${memberVo.realfilename}" /> 
				<input type="file" name="file" />
			</div>
		</div>

		<div class="form-group">
			<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userid" name="userid"
					placeholder="아이디" value="${param.userid}" readonly="readonly" />
			</div>
		</div>

		<div class="form-group">
			<label for="usernm" class="col-sm-2 control-label">사용자 이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="usernm" name="usernm"
					placeholder="이름" value="${param.usernm}" />
			</div>
		</div>
		<div class="form-group">
			<label for="alias" class="col-sm-2 control-label">별명</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="alias" name="alias"
					placeholder="별명" value="${param.alias}" />
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="pass" name="pass"
					placeholder="비밀번호" value="${param.pass}" />
			</div>
		</div>

		<div class="form-group">
			<label for="addr1" class="col-sm-2 control-label">주소</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="addr1" name="addr1"
					readonly="readonly" />
				<button id="zipcodeBtn" type="button" class="btn btn-default"
					value="${param.addr1}">우편번호 찾기</button>
			</div>
		</div>

		<div class="form-group">
			<label for="addr2" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="addr2" name="addr2"
					placeholder="상세주소" value="${param.addr2}" />
			</div>
		</div>

		<div class="form-group">
			<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="zipcode" name="zipcode"
					readonly="readonly" value="${param.zipcode}" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="regBtn" type="submit" class="btn btn-default">사용자
					정보 수정</button>
			</div>
		</div>

	</form>
</div>
