<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
		<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">

		<script type="text/javascript">
				
			// 아이디 중복 체크
			function idCheck(idT) {
				if(idT == ""){
					alert('아이디를 먼저 입력해주세요.')
					return false
				}
				
				$.ajax({
					url:"joinIdCheck",
					type:"POST",
					data:{
						user_id:idT
					},
					dataType:"JSON",
					success:function(data){
						if(data.result == '0'){
							$('#idCheckResult').val(0);
							$('#user_id').attr('readonly',true);
							$('#idCheckbtn').attr('type','hidden');
							$('#idCheckOk').css('visibility',"visible");
						}else{
							alert('이미 사용중인 아이디입니다.')
						}
					},
					error:function(){
						alert('에러발생')
					}
				})
				
			}

			// 회원가입 유효성 검사
			function joinFormCheck() {

				var idPtn = /^[a-z]{1}[a-zA-Z0-9]{2,8}$/;
				if (idPtn.test($("#user_id").val()) != true) {
					alert("아이디를 형식에 맞게 입력해 주세요!");
					$("#user_id").next().text("아이디 첫 글자는 영문 소문자만, 영어 대/소문자+숫자의 조합으로 3~8자리까지 입력가능합니다.");
					return false;
				}

				var pwPtn = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{3,10}$/;
				if (pwPtn.test($("#user_pw").val()) != true) {
					alert("비밀번호를 형식에 맞게 입력해 주세요!");
					$("#user_pw").next().text("비밀번호는 영어대문자, 영어소문자, 숫자가 각각 1개이상 포함된 조합으로 3~10자리까지 입력가능합니다.");
					return false;
				}

				var namePtn = /^[가-힣\sA-Za-z]{1,}$/;
				if (namePtn.test($("#user_name").val()) != true) {
					alert("이름은 한글, 영어만 입력가능합니다.(공백 포함)");
					$("#user_name").next().text("이름은 한글, 영어만 입력가능합니다.(공백 포함)");
					return false;
				}

				if ($('#user_pwcheck').val() == '') {
					alert("비밀번호 확인을 입력해주세요.");
					return false;
				}
				if ($('#user_pwcheck').val() !== $('#user_pw').val()) {
					alert("비밀번호가 일치하지 않습니다.");
					return false;
				}
				if ($('#email').val() == '') {
					alert("이메일을 입력해주세요.");
					return false;
				}
				if ($('#email_adr').val() == '') {
					alert("이메일을 입력해주세요.");
					return false;
				}
				if ($('#idCheckResult').val() != 0) {
					alert('아이디를 확인해주세요.!');
					return false;
				}

				this.form.submit();
			}

			//비밀번호가 일치하지 않습니다 글자 출력 부분
			$(function() {
				$('#alert-success').hide();
				$('#alert-danger').hide();

				$('input').keyup(function() {

					var pw1 = $('#user_pw').val();
					var pw2 = $('#user_pwcheck').val();

					if (pw1 != "" || pw2 != "") {
						if (pw1 == pw2) {
							$('#alert-success').show();
							$('#alert-danger').hide();
						} else {
							$('#alert-danger').show();
							$('#alert-success').hide();
						}
					}
				})
			})
			
			</script>
		
		
	
		<title>회원가입 페이지</title>
	</head>
	<body>
		<jsp:include page="../util/Header.jsp"></jsp:include>
		<div id="wrap">
		<div class="sub_padding">
		<div class="sub_container">
			<jsp:include page="../util/submenu.jsp"></jsp:include>
			<div id="contents">
		<div class="sub_top" id="sub_top01"> <!-- id값 sub_top01~ sub_top10 메뉴별로 바뀌께-->
			<h2>
					회원가입
			</h2>
		</div>
		<div id="content">

		<div class="userInsert">
			<div class="common_txt">
				<h2>회원가입</h2>
				<ul>
					<li>신상정보를 입력 또는 선택하시기 바랍니다.</li>
				</ul>
				<p>'<span class="s_col_blue">*</span>'은 필수 입력항목입니다.</p>
			</div>
			<div class="userInsert_box btn_c">
				<form name="form" id="form" method="post" action="/user/joinCheck">
					<input type="hidden" name="idCheckResult" id="idCheckResult" value="1" readonly>
					<table class="userInsert_table">
						<caption>회원정보 입력폼</caption>
						<colgroup>
							<col style="width:20%;">
							<col style="width:30%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">아이디<span class="essential_icon">*</span></th>
								<td>
									<input type="text" id="user_id" name="user_id" style="ime-mode:disabled">
								</td>
								<td style="text-align: right; padding-right: 10px; color: #0f76b3; font-weight:bold;">
									<span id="idCheckOk" style="visibility:hidden">사용가능한 아이디 입니다.</span>
									<input type="button" id="idCheckbtn" onclick="idCheck($('#user_id').val())" value="아이디 중복확인">
		                       </td>
							</tr>
							<tr>
								<th scope="row">성명<span class="essential_icon">*</span> </th>
								<td colspan="2">
									<input type="text" id="user_name" name="user_name" style="ime-mode:disabled">
								</td>
							</tr>
							<tr>
								<th scope="row"><label>비밀번호<span class="essential_icon">*</span></label></th>
								<td colspan="2">
									<input type="password" id="user_pw" name="user_pw" style="ime-mode:disabled">
								</td>
							</tr>
							<tr>
								<th scope="row"><label>비밀번호 확인<span class="essential_icon">*</span></label></th>
								<td>
									<input type="password" id="user_pwcheck" name="user_pwcheck" style="ime-mode:disabled">
								</td>
								<td id ="alert-success" style="text-align: right; font-size: 12px; color: #ce1616">
									* 비밀번호가 일치합니다.
								</td>
								<td id ="alert-danger" style="text-align: right; font-size: 12px; color: #ce1616">
									* 비밀번호가 일치하지않습니다.
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="email1"> 이메일<span class="essential_icon">*</span></label></th>
								<td colspan="2">
									<input type="text" id="email" name="email" style="ime-mode:disabled">
									 @
									<input type="text" id="email_adr" name="email_adr" style="ime-mode:disabled" class="null_false inpTextAuto">
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<div class="btn_c ue_s_new mT30">
					<span class="btnG dblue"><a onclick="joinFormCheck()">회원가입</a></span>
					<span class="btnG"><a href="/">취소</a></span>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<!-- sub end -->
	</div><!-- sub_container -->
	</div><!-- //sub_paddig -->
	</div><!-- //wrap -->
<jsp:include page="../util/Footer.jsp"></jsp:include>