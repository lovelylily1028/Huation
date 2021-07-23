<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% 	String user_name = (String)session.getAttribute("user_name"); %>  
<!DOCTYPE html>
<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css">
<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
<link rel="stylesheet" href="/resources/css/chatRoom.css"
	type="text/css">

<title>HUATION</title>
<style type="text/css">
.chating {
	background-color: #b2c7d9;
	width: 100%;
	height: 500px;
	overflow: auto;
}

.chating .me {
	width: 100px;
	background-color: #ffeb33;
	color: #000;
	text-align: right;
}

.chating .others {
	color: #FFE400;
	text-align: left;
}

.chating p {
	background-color: #ffffff;
	color: #000;
	text-align: left;
}
</style>
</head>

<script type="text/javascript">
	var ws;

	function wsOpen() {
		//웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/chating/"
				+ $("#roomNumber").val());
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작
		}

		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			
			if (msg != null && msg.trim() != '') {
				
				var d = JSON.parse(msg);
				
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : "";
					
					if (si != '') {
						$("#sessionId").val(si);
					}
					
				} else if (d.type == "message") {
					if (d.sessionId == $("#sessionId").val()) {
						$("#chatBody").append(
								"<div class='myChattingBox'><div class='myChattingTxt'>" 
								+ d.msg 
								+ "</div></div>");
						
					} else {
						$("#chatBody").append(
								"<div class='yourChattingBox'><div class='yourChattingName'>" 
								+ d.userName 
								+"</div><div class='yourChattingTxt'>"	
								+ d.msg
								+"</div></div>");
					}

				} else {
					console.warn("unknown type!")
				}
			}
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}
	
	
	$(function(){
			wsOpen();
/* 		var userName = prompt('채팅에 사용할 닉네임을 작성해주세요.');
		
		if(userName != null && userName.trim() != '' ){
			$("#userName").val(userName)
			wsOpen();
		}else{
			alert('잘못된 입력으로 창이 종료됩니다.')
			self.close()
		}
 */	})

	function send() {
		var option = {
			type : "message",
			roomNumber : $("#roomNumber").val(),
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#userChatText").val()
		}
		ws.send(JSON.stringify(option))
		$('#userChatText').val("");
	}
	
	/* function chatName() {
		var userName = $("#userChatText").val();
		
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userChatText").focus();
		} else {
			 $("#userChatText").attr('onclick','send()');
		}
	}
	 */
</script>

<body>
	<!-- warp_content start -->
	<input type="hidden" id="sessionId" value="">
	<input type="hidden" id="roomNumber" value="${roomNumber}">
	<input type="hidden" id="userName" value="<%=user_name %>">


	<%-- 채팅방 --%>
	<div class="chattingDiv">
		<div>
			<div class="chatHeader">
				<img src="/resources/img/huation_CI_2.png">
				<div class="roomName">${roomName}</div>
			</div>

			<div class="chatBody" id="chatBody">

			
				<%-- 채팅 메시지 담김 --%>

			</div>
				
			<div class="chatInput">
				<textarea id="userChatText" placeholder="내용을 입력해주세요 !" ></textarea>
				<input type="button" value="전송" id="ChatSubmitBtn" onclick="send()">
			</div>

		</div>
	</div>

	<%-- 내용이 들어가는 부분 --%>
	<!-- 
			<div id="chating" class="chating"></div>
					<div id="yourName">
					<table class="inputTable">
						<tr>
						<th>사용자명</th>
						<th><input type="text" name="userName" id="userName"></th>
						<th><button onclick="chatName()" id="startBtn">이름등록</button></th>
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
 -->
</body>
</html>