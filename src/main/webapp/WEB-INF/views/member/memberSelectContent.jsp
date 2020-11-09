<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(document).ready(function() {
		$("#profileDownBtn").on('click', function() {
			document.location = "/filedownView?userid=${memberVo.userid}";
		});
	});
</script>

<div class="row">
tiles
	<form id="frm" class="form-horizontal" role="form"
		action="${cp}/member/updateView" method="get">

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
			<div class="col-sm-10">
				<!-- webapp폴더안에 파일이 있을경우 -->
				<%-- 						<img src="${cp}/profile/${memberVo.filename}"> --%>
				<!-- 사용자 아이디로 조회할때..이미지 -->
				<img src="${cp}/profileImgView?userid=${memberVo.userid}"><br>
				<button id="profileDownBtn" type="button" class="btn btn-default">다운로드
					: ${memberVo.realfilename}</button>
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.userid}" name="userid" />${memberVo.userid}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.usernm}" name="usernm" />${memberVo.usernm}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">별명</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.alias}" name="alias" />${memberVo.alias}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.pass}" name="pass" />*********</label>
			</div>
		</div>

		<div class="form-group">
			<label for="addr1" class="col-sm-2 control-label">주소</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.addr1}" name="addr1" />${memberVo.addr1}</label>
			</div>
		</div>

		<div class="form-group">
			<label for="addr2" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.addr2}" name="addr2" />${memberVo.addr2 }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<label class="control-label"><input type="hidden"
					value="${memberVo.zipcode}" name="zipcode" />${memberVo.zipcode }</label>
			</div>
		</div>

		<div class="form-group">
			<label for="reg_dt" class="col-sm-2 control-label">등록일자</label>
			<div class="col-sm-10">
				<label class="control-label"> <input type="hidden"
					value="${memberVo.reg_dt}" name="reg_dt" /> <fmt:formatDate
						value="${memberVo.reg_dt}" pattern="YYYY-MM-dd" />
				</label>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="btn" type="submit" class="btn btn-default">사용자
					수정</button>
			</div>
		</div>
	</form>
</div>