<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 	String id = (String)session.getAttribute("user_id"); %>  
<!DOCTYPE html>
<html>
	<head>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript">
		
		/* 수정 토글 */
		function editToggle(){
			  if ($('#updateBtn').val() == '수정'){
				  updateOpen();
				  $('#updateBtn').val('취소')
			  }else {
				  updateClose()
				  $('#updateBtn').val('수정')
			  }
		}
		
		function updateOpen(){
			  $('#titleTemp').attr('contenteditable',true);
			  $('#titleTemp').css('background','orange');
			  $('#contentTemp').attr('contenteditable',true);
			  $('#contentTemp').css('background','orange');	
				
			  $('#updateSubmit').attr('type','button');
			  $('#file').attr('type','file');
		}

		function updateClose(){
			  $('#titleTemp').attr('contenteditable',false);
			  $('#titleTemp').css('background','none');
			  $('#contentTemp').attr('contenteditable',false);
			  $('#contentTemp').css('background','none');
				
			  document.getElementById("titleTemp").innerHTML =$('#title').val();
			  document.getElementById("contentTemp").innerHTML =$('#bcontents').val();
			  
			  $('#file').attr('type','hidden');
			  
			  $('#updateSubmit').attr('type','hidden');
		}

		/* 수정 적용된 값 db로 보내기  */
		function editCheck(f){
			
			var titleTemp = document.getElementById('titleTemp').innerText
			var contentTemp = document.getElementById('contentTemp').innerText
			
			  if (titleTemp == ''){
				  alert('제목을 입력해주세요.')
				  $('#titleTemp').focus();
				  return;
			  }
			  
			  if (contentTemp == ''){
				  alert('내용을 입력해주세요.')
				  $('#contentTemp').focus();
				  return;
			  }
			  
			  $('#title').val ( titleTemp );
			  $('#bcontents').val (contentTemp);


			  f.submit(); 
		  }
		
		/* 게시물 삭제 */
		function DeleteCheck(f){
			
			if(confirm ('삭제하시겠습니까?')){
				
				location.href="/board/delete?bid="+$(f.bid).val();
			}else{
				return false;
			}
			
		}
		
		/* 답글 작성페이지로 이동 */
		function replyView(bid){
			location.href="./reply_view?bid="+bid;
		}
		
		/* 댓글 등록 수정,삭제 */
		function commentCRUD(num,f){
			if(num == '1'){
				if($("#cmtCon").val()==""){
					alert("내용이 작성되지 않았습니다.!");
					return false;
				}
				f.submit();
				
			}else if(num =='2'){
				if ( $(f.contentTemp).val()=='' ){
					alert('내용을 입력해주세요.')
					$(f.contentTemp).focus();
					return;
				}
				
				$(f.recontent).val( $(f.contentTemp).val() )
				f.submit();	
				
			}else if(num == '3'){
				if(confirm('댓글을 삭제하시겠습니까?')){
					location.href= './commentDelete?bid='+$(f.bid).val()+'&cid='+$(f.cid).val();
				}else{
					return false;
				}
			}
		}
		
		/* 댓글 수정하기 토글 */
		function Edit(f){
			if ( $(f.commentToggle).val() == '수정' ){
			
			$(f).css("border","3px solid red");
			$(f.contentTemp).attr("readonly",false)
			$(f.commentHidden).attr("type","button")			
			$(f.commentToggle).val('취소')
			
		}else {
			$(f.contentTemp).val( $(f.recontent).val() )
			
			$(f).css("border","1px solid #e8e8e8");
			$(f.contentTemp).attr("readonly",true)
			$(f.commentHidden).attr("type","hidden")
			$(f.commentToggle).val('수정')
		}
		
		}
		
		</script>
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css">
		<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
		
		<title>HUATION</title>
	</head>
	<body>
		<jsp:include page="../util/Header.jsp"></jsp:include>
		<!-- header 끝 -->
		
		<!-- warp_content start -->
		<div id="wrap">
			<!-- sub start -->
			<div class="sub_padding">
				<div class="sub_container">
					<jsp:include page="../util/submenu.jsp"></jsp:include>
					<div id="contents">
						<div class="sub_top" id="sub_top01">
							<!-- 페이지 메뉴별로 바뀌께-->
							<h2>상세보기</h2>
						</div>
						<!-- content Start -->
						<div id="content">
							<div id="bbs_wrap">
								<div class="board_view">
									<dl class="tit_view">
										<dt>제목</dt>
										<dd id="titleTemp" contenteditable="false">${map.boardDto.title}</dd>
									</dl>
									<dl class="info_view2">
										<dt class="writer">작성자</dt>
										<dd class="writer">${map.boardDto.user_id }</dd>
										<dt>작성일</dt>
										<dd> <%-- ${map.boardDto.day } --%></dd>
										<dt>조회수</dt>
										<dd>${map.boardDto.hit }</dd>
									</dl>
	
									<dl class="file_view">
										<dt>첨부파일목록</dt>
										<dd>
											<a href="/board/fileDown?bid=${map.boardDto.bid}">${map.boardDto.fileName }</a>
										</dd>
									</dl>
	
									<div class="view_cont" id="contentTemp" contenteditable="false">
										${map.boardDto.bcontents }
									</div>
									 <c:set var="id" value="<%=id %>"/>
										<%-- 게시물 수정/삭제 버튼 --%>	
												<c:if test="${id eq map.boardDto.user_id }">	
													<div class="bbs_reply">							
														<div class="fR">
																<form action="/board/edit" method="post" enctype="multipart/form-data" id="editForm">
											       				 <input type="hidden" id="user_id"name="user_id" id="user_id" value="<%= id %>">
																 <input type="hidden" id="bid" name="bid" value="${map.boardDto.bid }">
																 <input type="hidden" id="title" name="title" value="${map.boardDto.title }">
																 <input type="hidden" id="bcontents" name="bcontents" value="${map.boardDto.bcontents }">
																 <input type="hidden" id="fileName" name="fileName" value="${map.boardDto.fileName}">
																	
																 <input type="hidden" id="file" name="file">
																 
																<input type="hidden" id="updateSubmit" onclick="editCheck(this.form)" value="수정완료">
								       							<input type="button" id="updateBtn" value="수정" onclick="editToggle()">
								       							<input type="button" value="삭제" onclick="DeleteCheck(this.form)">
								       							</form>
														</div>
													</div>
												</c:if>
										
										<%-- 답글 버튼--%>
										<c:if test="${not empty id }">
											<div class="btn_all">
												<span class="bbtn"><a onclick="replyView(${map.boardDto.bid})">답글달기</a></span>	
											</div>
										</c:if>			
									
									<%-- 댓글 출력 부분 --%>	
									<c:set var="user_id" value="<%=id%>"/>
									<c:set var="list" value="${cmap.clist }"/>
										<div class="bbs_reply">
											<h4>${fn:length(list)}개의 댓글이 있습니다.</h4>
											<div class="reply type1">
												<c:if test="${empty cmap.clist }">
													<div>#. 현재 작성된 댓글이 없습니다.</div>
												</c:if>
												<c:forEach var="cdto" items="${cmap.clist }">
													<form class="commentBox" method="post" action="/board/commentEdit">
														<input type="hidden" name="bid" value="${map.boardDto.bid }">
														<input type="hidden" name="cid" value="${cdto.cid }">
														<input type="hidden" name="recontent" value="${cdto.recontent }">
													<strong>${cdto.user_id }</strong> 
													<span class="date">${cdto.cdate }
													
													<%-- 로그인된 아이디와 작성자가 같을 경우 --%>
													<c:if test="${id eq cdto.user_id }">
															<input type="hidden" id="commentHidden" onclick="commentCRUD('2',this.form)" value="수정완료">
															<input type="button" id="commentToggle" onclick="Edit(this.form)" value="수정">			        
															<input type="button" onclick="commentCRUD('3',this.form)" value="삭제">			        
													</c:if>
													</span>	
													<p class="reply_cont"><input class="insert" type="text" id="contentTemp" value="${cdto.recontent }" readonly></p>
													</form>
												</c:forEach>
											</div>
									<%-- // --%>
									<%-- 댓글 작성 부분 --%>
									<div class="comment">
										<div class="comment_inp">
											<form action="/board/CommentCheck" method="post" name="commentForm">
	
												<input type="hidden" name="user_id" id="user_id" value="<%=id%>">
			 									<input type="hidden" name="bid" id="bid" value="${map.boardDto.bid }">
												
												<textarea rows="0" cols="0" maxlength="2000"  name="recontent" id="" placeholder="댓글을 입력하세요."></textarea>
												<span class="bbtn_input">
													<input type="button" onclick="commentCRUD('1',this.form)" value="등록"></span>
												<p>최대 2,000자까지 입력가능합니다.</p>
											</form>
										</div>
									</div>
									<%-- // --%>
								</div><!-- bbs wrap -->
								
								
								<%-- 이전글 다음글 페이징 처리 --%>
								<div class="preview">
									<dl>
										<dt>이전글 ▲</dt>
										<dd>
											${map.boardDto.pre_title }
										</dd>
									</dl>
								</div>
								<div class="preview">
									<dl>
										<dt>다음글 ▼</dt>
										<dd>
											<c:choose>
												<c:when test="${map.boardDto.next_title eq '' && map.boardDto.next_title == null }">
														 마지막 게시글 입니다.
												</c:when>
												<c:otherwise>
														${map.boardDto.next_title }
												</c:otherwise>											
											</c:choose>
										</dd>
									</dl>
								</div>
								<div class="btn_all">
									<div class="fL"></div>
									<div class="fR">
										<span class="bbtn"><a onclick="location.href='/board/list?category=${map.category}&page=${map.page }&search=${map.search }'" title="일반게시판">목록</a></span>
									</div>
								</div>
							</div>
						</div>
	
					</div>
				</div>
				<!-- sub end -->
			</div>
		</div>
		</div>
		<!-- //wrap_content start -->
		<!-- footer 입장 -->
		<jsp:include page="../util/Footer.jsp"></jsp:include>
	</body>