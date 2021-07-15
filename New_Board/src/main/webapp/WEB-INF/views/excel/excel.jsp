<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%	String id = (String)session.getAttribute("user_id"); %>  
<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
<title>엑셀 업로드 페이지 입니다.</title>
	<script type="text/javascript" src="/resources/js/BoardCRUD.js"></script>
	<link rel="stylesheet" href="/resources/css/BoardContentView.css">
</head>
<body>
	<jsp:include page="../util/mainHeader.jsp"></jsp:include>
      <h2>엑셀 업로드</h2>
    	<form action="/excel/read" method="POST" enctype="multipart/form-data">
    		<input type="file" name="file">
    		<input type="submit" value="제출">
    	
    	
    	</form>  
     <jsp:include page="../util/mainFooter.jsp"></jsp:include>
   </body>
</html>