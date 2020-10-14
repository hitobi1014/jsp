<%@page import="kr.or.ddit.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");%>
<table class="table table-striped">
	<tr>
		<th>사용자 아이디</th>
		<th>사용자 이름</th>
		<th>사용자 별명</th>
		<th>등록일시</th>
	</tr>
	<c:forEach items="${memberList}" var="member">
		<tr>
			<td>${member.userid }</td>
			<td>${member.usernm }</td>
			<td>${member.alias }</td>
			<td>${member.reg_dt }</td>
		</tr>
	</c:forEach>
</table>