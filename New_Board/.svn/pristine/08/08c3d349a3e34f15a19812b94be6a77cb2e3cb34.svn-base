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
<style>
</style>
</head>

<script type="text/javascript">
	var ws;
	window.onload = function() {
		getRoom();
		createRoom();
	}

	/* 공통 AJAX 묶어서 표현 */
	function commonAjax(url, parameter, type, calbak, contentType) {
		$.ajax({
			url : url,
			data : parameter,
			type : type,
			contentType : contentType != null ? contentType
					: 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(res) {
				calbak(res);
			},
			error : function(err) {
				console.log('error');
				calbak(err);
			}
		});
	}
	
	/* 방 리스트 가져오기 */
	function getRoom() {
		commonAjax('/getRoom', "", 'post', function(result) {
			createChatingRoom(result);
		});
	}
	
	/* 방 리스트 테이블에 뿌리기 */
	function createChatingRoom(res) {
		
		if (res != null) {
			var tag ="";
			
			res.forEach(function(d, idx) {
				var rn = d.roomName.trim();
				var roomNumber = d.roomNumber;
				tag += "<tr>"
					+ "<td>"
					+ (idx + 1)
					+ "</td>"
					+ "<td>"
					+ rn
					+ "</td>"
					+ "<td><span class='bg_color02' onclick='goRoom(\""
					+ roomNumber + "\", \"" + rn
					+ "\")'>입장하기</span></td>" + "</tr>";
				});
				
				
			$("#chattingRoomList").empty().append(tag)
		}
		
	}
	
	/* 방 만들기 */
	function createRoom() {
		$("#createRoom").click(function() {
			
			if($('#roomName').val()== ""){
				alert("방제목을 입력하세요!")
				 return false
			}
			
			var msg = {
				roomName : $('#roomName').val()
			};

			commonAjax('/createRoom', msg, 'post', function(result) {
				createChatingRoom(result);
			});

			$("#roomName").val("");
		});
	}

	
	// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
	var popupX = (window.screen.width / 2) - (600 / 2);
	// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
	var popupY= (window.screen.height /2) - (400 / 2);
	
	/* 채팅방 팝업 페이지 */
	function goRoom(number,name){

		window.open("/moveChating?roomName=" + name + "&" + "roomNumber="+ number, "_blank", "status=no, width=500, height=800, left="+popupX+", top="+popupY+", screenX="+popupX+", screenY="+popupY);
	
	}
	
	/* function goRoom(number, name) {
		location.href = "/moveChating?roomName=" + name + "&" + "roomNumber="+ number;
	} */
	
</script>
<body>
	<jsp:include page="../util/Header.jsp"></jsp:include>
	<!-- warp_content start -->
	<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
			<div class="sub_container">
				<jsp:include page="../util/submenu.jsp"></jsp:include>
				<div id="contents">
					<div class="sub_top" id="sub_top01">
						<!-- 페이지 메뉴별로 바뀌께-->
						<h2>채팅</h2>
					</div>

					<div id="content">
						<div id="bbs_wrap">
							<table summary="채팅방 리스트" class="schedule_list">
								<thead>
									<tr>
										<th style="width: 55px">번호</th>
										<th style="width: 400px">방제목</th>
										<th style="width: 55px">상태</th>
									</tr>
								</thead>
								<tbody id="chattingRoomList">

								</tbody>
							</table>
							
							
							<%-- 방만들기 버튼 --%>
							<div class="btn_c">
								<div class="fR">
									<strong>방제목</strong>
									<input type="text" name="roomName" id="roomName">
									<span class="bbtn_bg1"><input type="button" id="createRoom" value="방만들기"></span>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<!-- sub end -->
		</div>
	</div>
	<!-- //wrap_content start -->

	<jsp:include page="../util/Footer.jsp"></jsp:include>