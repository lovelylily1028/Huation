<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="application/vnd.ms-excel;charset=UTF-8">

<%

	Calendar cal = Calendar.getInstance();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    response.setHeader("Content-Disposition","attachment;filename="+sdf.format(cal.getTime())+"_excel_file.xls");

    response.setHeader("Content-Description", "JSP Generated Data");

 %>

 <style>

 .list_table{width:1000px;}

 .list_table tr td, .list_table tr th{border: 1px solid #000;}

 </style>

</head>

<body>

	<table class="list_table">

		<caption>리스트 목록</caption>

		<colgroup>

			<col width="10%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
	

		</colgroup>

		<thead>

			<tr>			

                    <th scope="col">글번호</th>

                    <th scope="col">작성자</th>

                    <th scope="col">제목</th>
                            
                    <th scope="col">작성일</th>
                            
                    <th scope="col">조회수</th>

			</tr>

		</thead>

		<tbody>


		<c:forEach items="${excelList}"  var="list" varStatus="count">

			<tr>
				<td class=""><c:out value="${list.bid }"/></td>

    			<td class=""><c:out value="${list.user_id}"/></td>

				<td class=""><c:out value="${list.title}" /></td>
				
				<td class=""><c:out value="${list.day}" /></td>
				
				<td class=""><c:out value="${list.hit}" /></td>

			</tr>

		</c:forEach>

		<c:if test="${empty excelList }">

			<tr>

				<td colspan="5">게시글이 없습니다.</td>

			</tr>

		</c:if>

		</tbody>

	</table>

</body>

</html>    
    
    
