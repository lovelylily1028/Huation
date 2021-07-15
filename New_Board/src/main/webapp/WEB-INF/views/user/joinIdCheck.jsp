<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>아이디 중복 체크</title>
		<script type="text/javascript" src="/resources/js/UserFormCheck.js"></script>
	</head>
	<body>
		<h2>아이디 중복체크 </h2>
		<% 	String id = request.getParameter("user_id"); %>
		
		 <c:choose>
		 	<c:when test="${result == 0 }">
		 	<form action="" id="IdCheck" name="IdCheck">
		 	<input type="hidden" name="result" id="result" value="ok">
		 	<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
		 		사용 가능한 아이디 입니다.
		 		<button type="button" onclick="joinIdOK(${result})">아이디 사용하기</button>
		 	</form>
		 	</c:when>
			<c:otherwise>
				#. 중복된 아이디 입니다.
				<button type="button" onclick="window.close()">다른아이디 사용</button>	
			</c:otherwise>		 
		 </c:choose>
	</body>
</html>