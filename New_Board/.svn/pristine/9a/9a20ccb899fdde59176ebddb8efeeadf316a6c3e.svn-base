<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% String id = (String)session.getAttribute("user_id"); %>
<!DOCTYPE html>
<html>
		<head>
		<meta charset="UTF-8">
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css">
		<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
		
		</head>
	<body>
		<!-- content Start -->
		<div id="content">
			<div id="bbs_wrap">
				<div class="board_write">
					<dl>
						<%-- 새로운 글 작성 OR 답글 제목 설정 --%>
						<dt>제목</dt>
						<c:choose>
							<c:when test="${route eq 'reply' }">
								<dd>
									<input class="tit" type="text" id="titleTemp" value="[Re:]">
								</dd>
							</c:when>
							<c:otherwise>
								<dd>
									<input class="tit" type="text" id="titleTemp"
										placeholder="제목을 입력해주세요.">
								</dd>
							</c:otherwise>
						</c:choose>
						<dt>첨부파일</dt>
						<dd>
							<label for="file"> <input type="text" id="fileTemp"
								name="fileTemp" value="파일첨부하기" readonly> <img
								src="/resources/img/ico_file.gif">
							</label>
						</dd>
						<dd class="write_cont">
							<textarea rows="15" cols="10" id="contentTemp"
								placeholder="내용을 입력해주세요.">${map.boardDto.bcontents }</textarea>
						</dd>
					</dl>
	
				</div>
				<c:choose>
					<%-- 새로운 게시글 작성 --%>
					<c:when test="${route eq 'add'}">
	
						<form method="post" enctype="multipart/form-data" name="writeForm"
							id="writeForm">
							<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
							<input type="hidden" name="title" id="title"> <input
								type="hidden" name="bcontents" id="bcontents"> <input
								type="file" name="file" id="file" style="display: none;"
								onchange="javascript: document.getElementById('fileTemp').value = this.value">
							<input type="hidden" name="rmt" id="rmt" value="add">
	
							<div class="btn_c">
								<span class="bbtn_bg1"><input type="button"
									onclick="addChk(this.form)" value="등록"></span> <span
									class="bbtn_bg2"><a
									onclick="paging('${map.category}','${map.search}','${map.page }')">취소</a></span>
							</div>
						</form>
	
					</c:when>
	
					<%-- 새로운 답글 작성 --%>
					<c:when test="${route eq 'reply' }">
	
						<form method="post" enctype="multipart/form-data" name="replyForm"
							id="replyForm">
							<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
							<input type="hidden" name="bid" value="${map.boardDto.bid }">
							<input type="hidden" name="title" id="title"> <input
								type="hidden" name="bcontents" id="bcontents"> <input
								type="hidden" name="bgroup" id="bgroup"
								value="${map.boardDto.bgroup}"> <input type="hidden"
								name="bstep" id="bstep" value="${map.boardDto.bstep}"> <input
								type="hidden" name="bindent" id="bindent"
								value="${map.boardDto.bindent}"> <input type="file"
								name="file" id="file" style="display: none;"
								onchange="javascript: document.getElementById('fileTemp').value = this.value">
							<input type="hidden" name="rmt" id="rmt" value="reply">
	
							<div class="btn_c">
								<span class="bbtn_bg1"><input type="button"
									onclick="addChk(this.form)" value="답글등록"></span> <span
									class="bbtn_bg2"><a
									onclick="view('${map.category}','${map.search}','${map.page }','${map.boardDto.bid}')">취소</a></span>
							</div>
						</form>
	
					</c:when>
				</c:choose>
				<!-- bbs wrap -->
			</div>
	
		</div>
	</body>
</html>