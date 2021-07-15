<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	String id = (String)session.getAttribute("user_id"); %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
	
	/* 작성 후 db 업로드 전 체크 */
	function addCheck(f){
		
		 if ($('#titleTemp').val() == ''){
			  alert('제목을 입력해주세요.')
			  $('#titleTemp').focus();
			  return;
		  }
		  
		  if ($('#contentTemp').val() == ''){
			  alert('내용을 입력해주세요.')
			  $('#contentTemp').focus();
			  return;
		  }
		  
		  $('#title').val ( $('#titleTemp').val() );
		  $('#bcontents').val ( $('#contentTemp').val() );
		  
		  f.submit(); 
	}
	
	/* 취소 클릭시 페이징 처리 */
	 function cancelpage(num,bid){
		if(num =='all'){
			location.href="/NewList";
		}else if(num == 'reply'){
			location.href = 'Board/reply_view?bid='+bid;
		}
	}
	
	</script>
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css"	>
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
	<title>HUATION😂😂</title>
	</head>
	<body>
	<jsp:include page="../util/Header.jsp"></jsp:include>
	<!-- warp_content start -->
	<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
			<div class="sub_container">
				<jsp:include page="../util/submenu.jsp"></jsp:include>
				<%-- 내용 작성 페이지 시작 --%>
				<div id="contents">
					<div class="sub_top" id="sub_top01">
						<!-- 페이지 메뉴별로 바뀌께-->
						<h2>게시판 작성</h2>
					</div>
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
												<input class="tit" type="text" id="titleTemp" placeholder="제목을 입력해주세요.">
											</dd>
										</c:otherwise>
									</c:choose>
									<dt>첨부파일</dt>
									<dd>
										<label for="file">
												<input type="text" id="fileTemp" name="fileTemp" value="파일첨부하기" readonly>
												<img src="/resources/img/ico_file.gif">
										</label>
									</dd>
									<dd class="write_cont">
										<textarea  rows="15" cols="10" id="contentTemp" placeholder="내용을 입력해주세요." >${map.boardDto.bcontents }</textarea>
									</dd>
								</dl>

							</div>
								<c:choose>
								<%-- 새로운 게시글 작성 --%>
								<c:when test="${route eq 'add'}">
									
									<form action="/board/add" method="post"	enctype="multipart/form-data" name="writeForm" id="writeForm">
										<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
										<input type="hidden" name="title" id="title"> 
										<input type="hidden" name="bcontents" id="bcontents">
										<input type="file" name="file" id="file" style="display: none;" onchange="javascript: document.getElementById('fileTemp').value = this.value">
				
										<div class="btn_c">
											<span class="bbtn_bg1"><input type="button" onclick="addCheck(this.form)" value="등록"></span>
											<span class="bbtn_bg2"><a onclick="cancelpage('all','')">취소</a></span>
										</div>	
									</form>
									
								</c:when>
							
								<%-- 새로운 답글 작성 --%>
								<c:when test="${route eq 'reply' }">
				
									<form action="/board/boardReply" method="post"
										enctype="multipart/form-data" name="ReplyForm" id="ReplyForm">
										<input type="hidden" name="user_id" id="user_id" value="<%= id %>">
										<input type="hidden" name="bid" value="${map.boardDto.bid }">
										<input type="hidden" name="title" id="title"> 
										<input type="hidden" name="bcontents" id="bcontents">
										<input type="hidden" name="bgroup" id="bgroup" value="${map.boardDto.bgroup}"> 
										<input type="hidden" name="bstep" id="bstep" value="${map.boardDto.bstep}"> 
										<input type="hidden" name="bindent" id="bindent" value="${map.boardDto.bindent}"> 
										<input type="file" name="file" id="file" style="display: none;"	onchange="javascript: document.getElementById('fileTemp').value = this.value">
				
										<div class="btn_c">
											<span class="bbtn_bg1"><input type="button" onclick="addCheck(this.form)" value="등록"></span>
											<span class="bbtn_bg2"><a onclick="cancelPage('reply','${map.boardDto.bid}')">취소</a></span>
										</div>
									</form>
				
								</c:when>
							</c:choose>
							<!-- bbs wrap -->
							
							<div class="btn_all">
								<div class="fR">
									<span class="bbtn">
									<a onclick="location.href='NewList?category=${map.category}&page=${map.page }&search=${map.search }'" title="일반게시판">목록</a></span>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- CONTENTS END -->
			</div>
			<!-- sub_contaioner End -->
		</div>
		<!-- subPadding End -->
	</div>
	<!-- Wrap End -->

	<!-- footer 입장 -->
	<jsp:include page="../util/Footer.jsp"></jsp:include>
</body>
</html>