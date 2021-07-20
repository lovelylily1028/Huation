<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
	<title>HUATION</title>
	<style type="text/css">
		.chating{
			background-color: #000;
			width: 100%;
			height: 100%;
			overflow: auto;
		}
		.chating p{
			color: #fff;
			text-align: left;
		}
	</style>
</head>

<script type="text/javascript">
	var ws;

	function wsOpen(){
		ws = new WebSocket("ws://" + location.host + "/chating");
		wsEvt();
	}
		
	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 초기화 세팅하기
		}
		
		ws.onmessage = function(data) {
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				$("#chating").append("<p>" + msg + "</p>");
			}
		}

		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
	}

	function chatName(){
		var userName = $("#userName").val();
		if(userName == null || userName.trim() == ""){
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		}else{
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var uN = $("#userName").val();
		var msg = $("#chatting").val();
		ws.send(uN+" : "+msg);
		$('#chatting').val("");
	}
</script>


<body>
	<jsp:include page="../util/Header.jsp"></jsp:include>
<!-- warp_content start -->
<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
		<div class="sub_container">
			<jsp:include page="../util/submenu.jsp"></jsp:include>
		<div id="contents" >
		<div class="sub_top" id="sub_top01"> 
		<!-- 페이지 메뉴별로 바뀌께-->
			<h2>채팅</h2>
		</div>
		<!-- 게시판 부분 시작 -->
		<div id="content" style="border: 3px solid red;">
		
		<%-- 내용이 들어가는 부분 --%>
		
		<div id="chating" class="chating">
			<div> 채팅방 입니다.</div>
		</div>
		
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName"></th>
					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
				</tr>
			</table>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
				</tr>
			</table>
		</div>
		
		
		
		
		
		
		
		
		
		
		
	</div>
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>
