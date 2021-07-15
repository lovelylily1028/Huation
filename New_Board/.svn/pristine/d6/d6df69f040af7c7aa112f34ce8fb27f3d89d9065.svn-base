<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	String id = (String)session.getAttribute("user_id"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HUATION</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="/resources/css/ajax/view.css">
	</head>
	<body>
	 	<%-- 새로운 게시글 작성 및 답글 작성 페이지 --%>
		<div>
			<div class="contentInnerleft">
				<%-- 새로운 글 작성 OR 답글 제목 설정 --%>
				<c:choose>
					<c:when test="${route eq 'reply' }">
						<h2>
							<input type="text" id="titleTemp" value="[Re:]">
						</h2>
					</c:when>
					<c:otherwise>
						<h2>
							<input type="text" id="titleTemp" value="${map.boardDto.title}" placeholder="제목을 입력해주세요.">
						</h2>
					</c:otherwise>
				</c:choose>
			</div>
		<hr>
			<div class="commentBody">
				<textarea rows="15" cols="10" id="contentTemp"  placeholder="내용을 입력해주세요.">${map.boardDto.contents }</textarea>
			</div>
		<hr>	
			<div class="xright">
				<input type="text" id="fileTemp" name="fileTemp" value="파일첨부하기" readonly> 
				<label for="file"><img class="img_button" alt="파일첨부 이미지" src="/resources/img/fileUpload.png"></label>
			</div>
			<br>
	
			<div class="xright">
				<c:choose>
					<%-- 새로운 게시글 작성 --%>
					<c:when test="${route eq 'add'}">
	
						<form method="post" enctype="multipart/form-data" name="writeForm" id="writeForm">
							<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
							<input type="hidden" name="title" id="title"> 
							<input type="hidden" name="contents" id="contents">
							<input type="file" name="file" id="file" style="display: none;"	onchange="javascript: document.getElementById('fileTemp').value = this.value">
							<input type="hidden" name="rmt" id="rmt" value="add">
	
							<input type="button" value="작성완료" onclick="addChk(this.form)">
							<input type="button" value="취소" onclick="paging('${map.category}','${map.search}','${map.page }')">
						</form>
	
					</c:when>
	
					<%-- 새로운 답글 작성 --%>
					<c:when test="${route eq 'reply' }">
	
						<form method="post"	enctype="multipart/form-data" name="replyForm" id="replyForm">
							<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
							<input type="hidden" name="bid" value="${map.boardDto.bid }">
							<input type="hidden" name="title" id="title"> 
							<input type="hidden" name="contents" id="contents">
							<input type="hidden" name="bgroup" id="bgroup" value="${map.boardDto.bgroup}"> 
							<input type="hidden" name="bstep" id="bstep" value="${map.boardDto.bstep}"> 
							<input type="hidden" name="bindent" id="bindent" value="${map.boardDto.bindent}">
							<input type="file" name="file" id="file" style="display: none;"	onchange="javascript: document.getElementById('fileTemp').value = this.value">
							<input type="hidden" name="rmt" id="rmt" value="reply"> 
	
							<input type="button" value="답글 달기" onclick="addChk(this.form)">
							<button type="button" onclick="view('${map.category}','${map.search}','${map.page }','${map.boardDto.bid}')">취소</button>
						</form>
	
					</c:when>
	
				</c:choose>
			</div>
	
		</div>
	</body>
</html>