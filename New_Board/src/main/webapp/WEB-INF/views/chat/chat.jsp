<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
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
		background-color: #ffffff ;
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
						$("#chating").append(
								"<p class='me'>나 :" + d.msg + "</p>");
					} else {
						$("#chating").append(
								"<p class='others'>" + d.userName + " :"
										+ d.msg + "</p>");
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

	function chatName() {
		var userName = $("#userName").val();
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		} else {
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var option = {
			type : "message",
			roomNumber : $("#roomNumber").val(),
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting").val()
		}
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}
	
</script>

<body>
	<!-- warp_content start -->
	<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
			<div class="sub_container">
				<div id="contents">
					<div class="sub_top" id="sub_top01">
						<!-- 페이지 메뉴별로 바뀌께-->
						<h2>채팅</h2>
						<div class="location">	${roomName}	</div>
					</div>
					<!-- 게시판 부분 시작 -->
					<div id="content" style="border: 3px solid red;">
						<input type="hidden" id="sessionId" value="">
						<input type="hidden" id="roomNumber" value="${roomNumber}">
							

						<%-- 내용이 들어가는 부분 --%>

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

				
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>