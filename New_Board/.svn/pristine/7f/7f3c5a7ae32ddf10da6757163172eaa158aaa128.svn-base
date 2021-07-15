<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%	String id = (String)session.getAttribute("user_id"); %>  
<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
<title>엑셀 리스트 페이지 입니다.</title>
	<link rel="stylesheet" href="/resources/css/board.css">
</head>
<body>
	<jsp:include page="../util/mainHeader.jsp"></jsp:include>
      <h2>엑셀 리스트 출력</h2>
      <!-- 리스트 출력부분 -->
		  <table>
		    <thead>
		    	<tr>
		    	  <th scope="col">번호</th>
		    	  <th scope="col">이름</th>
		    	  <th scope="col">이메일</th>
		    	</tr>
		    </thead>
		    <tbody>
		    	<c:if test="${empty datas }">
		    		<td colspan="3">
			    		#. 읽어온 엑셀 파일이 없습니다.
		    		</td>	
		    	</c:if>
		    	<c:forEach var="data" items="${datas }">
			   	<tr>
			      <td>${data.num}</td>
			      <td>${data.name}</td>
			      <td>${data.email}</td>
			    </tr>
		    	</c:forEach>
		    </tbody>
		  </table>
     <jsp:include page="../util/mainFooter.jsp"></jsp:include>
   </body>
</html>