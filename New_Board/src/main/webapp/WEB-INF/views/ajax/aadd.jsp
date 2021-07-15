<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
		<script type="text/javascript">
		
		/* 페이지가 불러오자마자 해야할 일*/
		$(function readyWrite(){
		
			var rmtT = getParameterByName('rmt')
		
			switch (rmtT){
			case 'add':
				addReady()
				break;
			case 'reply':
				replyReady()
				break;
			default :
				alert('잘못된 접근입니다.')
				self.close()
				break;
			}
			
		})
		
		/* URL 파라미터 값을 읽어오는 정규식 */
		function getParameterByName(name) {
			name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
			var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
					.exec(location.search);
			return results == null ? "" : decodeURIComponent(results[1].replace(
					/\+/g, " "));
		}
		
		
		/* 답글 작성페이지 준비 */
		function replyReady(){
			
			var bidT = getParameterByName('bid')
			var rmtT = getParameterByName('rmt')
			
			$.ajax({
				url:"doAddView",
				type:"POST",
				data:{
					bid : bidT
				},
				dataType : "JSON",
				success : function(data) {
					
					var login_id = data.login_id
					document.getElementById("writerTemp").innerHTML = login_id;
					
						html ="";
					
						html +="<form method='post' enctype='multipart/form-data' name='replyForm' id='replyForm'>";
						html +="<input type='hidden' name='user_id' id='user_id' value='"+login_id+"'>";
						html +="<input type='hidden' name='bid' value='"+bidT+"'>";
						html +="<input type='hidden' name='title' id='title' value=''>";
						html +="<input type='hidden' name='bcontents' id='bcontents' value=''>";
						html +="<input type='hidden' name='bgroup' id='bgroup' value='"+data.boardDto.bgroup+"'>";
						html +="<input type='hidden' name='bstep' id='bstep' value='"+data.boardDto.bstep+"'>";
						html +="<input type='hidden' name='bindent' id='bindent' value='"+data.boardDto.bindent+"'>";
						html +="<input type='file'   name='file' id='file' style='display: none;' onchange='javascript: document.getElementById('fileTemp').value = this.value'>";
						html +="<input type='hidden' name='rmt' id='rmt' value='"+rmtT+"'>";
						html +="<div class='btn_c'>";
						html +="<span class='bbtn_bg1'><input type='button' onclick='add(this.form)' value='등록'></span> ";
						html +="<span class='bbtn_bg2'><a onclick=''>취소</a></span>";
						html +="</div>";
						html +="</form>";
						
						$('#writeForm').html(html)
					
				},
				error : function() {
					alert('작성페이지 준비에 실패하였습니다.')
				}
			})
			
			}
		
		
			/* 새글 작성페이지 준비 */
			function addReady(){
				
				var bidT = getParameterByName('bid')
				var rmtT = getParameterByName('rmt')
				
				$.ajax({
				url:"doAddView",
				type:"POST",
				data:{
					bid : bidT
				},
				dataType : "JSON",
				success : function(data) {
					
					var login_id = data.login_id
					
					document.getElementById("writerTemp").innerHTML = login_id;
					
						html ="";
						html +="<form method='post' enctype='multipart/form-data' name='addForm' id='addForm'>";
						html +="<input type='hidden' name='user_id' id='user_id' value='huation'>";
						html +="<input type='hidden' name='title' id='title' value=''>";
						html +="<input type='hidden' name='bcontents' id='bcontents' value=''>";
						html +="<input type='file'   name='file' id='file' style='display: none;' onchange='javascript: document.getElementById('fileTemp').value = this.value'>";
						html +="<input type='hidden' name='rmt' id='rmt' value='"+rmtT+"'>";
						html +="<div class='btn_c'>";
						html +="<span class='bbtn_bg1'><input type='button' onclick='add(this.form)' value='등록'></span> ";
						html +="<span class='bbtn_bg2'><a onclick=''>취소</a></span>";
						html +="</div>";
						html +="</form>";

					$('#writeForm').html(html)
				
				},
				error : function() {
					alert('작성페이지 준비에 실패하였습니다.')
				}
			})
					
			}
		
		
		/* 새글 답글 db로 글 넘기기 */
		function add(){
			
		if ($('#titleTemp').val() == ''){
			  alert('제목을 입력해주세요.')
			  $('#titleTemp').focus()
			  return;
			 }
			  
		if ($('#contentTemp').val() == ''){
			  alert('내용을 입력해주세요.')
			  $('#contentTemp').focus()
			  return;
		 }	
		
		$('#title').val ( $('#titleTemp').val())
		$('#bcontents').val ( $('#contentTemp').val())
		
		var rmtT = getParameterByName('rmt')
		
		var addform
		var replyform
		var data
		
		switch (rmtT){
		case "add":
			alert(" add 여기 들어옴");
			addform =$('#addForm')[0];
			data = new FormData(addform);
			break;
			
		case "reply":
			replyform = $('#replyForm')[0];
			data = new FormData(replyform);
			break; 
			
		default :
			alert('잘못된 접근입니다.');
			return false
			break;
		}

		  var pageT = getParameterByName('page');
		
			$.ajax({
				 url:"doAdd",
				  type:"POST",
				  enctype:"multipart/form-data",
				  data: data,
				  processData: false,
				  contentType: false,
				  cache: false,
				  timeout: 600000,
				  success:function(data){
					  if (data.result == "true") {
						  alert('게시글 작성이 완료 되었습니다.')
						  window.opener.paging(pageT)
						  self.close()
						}
				  },
				  error:function(){
					  alert('게시물 등록에 실패하였습니다.')
				  }
			})
			
			
		}
		
		</script>
		
		
		</head>
	<body>
	<div id="wrap">
		<div class="sub_padding">
			<div class="sub_container">
				<div id="contents">
					<!-- content Start -->
					<div id="content">
						<div class="sub_top" id="sub_top01">
							<h2>글 작성하기</h2>
						</div>
						<div id="bbs_wrap">
							<div class="board_write">
								<dl>
									<%-- 새로운 글 작성 OR 답글 제목 설정 --%>
									<dt>제목</dt>
									<dd>
										<input class="tit" type="text" id="titleTemp" placeholder="제목을 입력해주세요.">
									</dd>
									<dt>작성자</dt>
									<dd id="writerTemp"></dd>
									<dt>첨부파일</dt>
									<dd>
										<label for="file"> <input type="text" id="fileTemp"
											name="fileTemp" value="파일첨부하기" readonly> <img
											src="/resources/img/ico_file.gif">
										</label>
									</dd>
									<dd class="write_cont">
										<textarea rows="15" cols="10" id="contentTemp"
											placeholder="내용을 입력해주세요."></textarea>
									</dd>
								</dl>

							</div>
							<div id="writeForm"></div>
							<!-- bbs wrap -->
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>