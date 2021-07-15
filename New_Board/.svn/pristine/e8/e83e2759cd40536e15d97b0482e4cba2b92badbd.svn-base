<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<% 	String id = (String)session.getAttribute("user_id"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 상세보기</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="/resources/css/BoardContentView.css">
		<script type="text/javascript" src="/resources/js/BoardCRUD.js"></script>
	</head>
	<body>
		<jsp:include page="../util/mainHeader.jsp"></jsp:include>
			<h2>게시글 상세보기</h2>
			<div>
				<form action="/excel/uploadExcelListForm" id="form1" name="form1" method="post" 
				enctype="multipart/form-data">
					<input type="file" id="excelfile" name="excelfile" >
					<button type="submit">엑셀 업로드 작업</button>
				</form>				
		  	</div>    
		<jsp:include page="../util/mainFooter.jsp"></jsp:include>
	</body>
</html>