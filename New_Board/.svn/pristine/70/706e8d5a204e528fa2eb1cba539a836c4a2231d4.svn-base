<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat sf2 = new SimpleDateFormat("a hh:mm:ss");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
 </head>
<body>
	<jsp:include page="../util/mainHeader.jsp"></jsp:include>
    
    	<div style="text-align: left; margin-top:19%;">
        <h1>현재시각</h1>
        <h1><%= sf.format(nowTime) %>  <%= sf2.format(nowTime) %></h1>
    	</div>
    	
    <jsp:include page="../util/mainFooter.jsp"></jsp:include>
</body>
</html>