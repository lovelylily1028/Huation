<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" async="" src="https://www.google-analytics.com/analytics.js"></script>
		<script async="" src="//www.google-analytics.com/analytics.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript">
			// 로그인 폼 체크
			function loginFormCheck(f) {
	
				if ($('#user_id').val() == '') {
					alert("아이디를 입력해주세요.");
					return false;
				}
	
				if ($('#user_pw').val() == '') {
					alert("비밀번호를 입력해주세요.");
					return false;
				}
	
				f.submit();
	
			}//			
		</script>
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
	
		<title>로그인 페이지</title>
	</head>
	<body>
		<jsp:include page="../util/Header.jsp"></jsp:include>
		<!-- warp_content start -->
		<div id="wrap">
			<div id="login_page">
		<div id="content">
		<div class="login_box">
			<%-- 로그인 --%>
			<div class="smartshool login">
				<h2>로그인</h2>
	
				<p>로그인이 필요한 서비스입니다. 가입하신 아이디와 비밀번호를 이용하여 로그인 해주세요.</p>
				<p> <strong class="org">회원이 아닌 분은 회원가입</strong>후 로그인해주십시오.</p>
				<p>아이디 입력 시 대/소문자를 확인해 주세요.</p>
				<div class="login_inp">
					<form action="loginCheck" name="form" method="post" onsubmit="return loginFormCheck(this.form)">
						<fieldset>
									<span>
										<label for="id"><h3>아이디</h3></label>
										<input type="text" id="user_id" name="user_id" value="" class="inp" title="아이디">
									</span>
									<span>
										<label for="pwd"><h3>패스워드</h3></label>
										<input type="password" id="user_pw" name="user_pw" value="" class="inp" title="패스워드">
									</span>
							<input type="image" src="/resources/img/btn_login.gif" alt="로그인" class="btn_login" onclick="loginFormCheck(this.form)">
						</fieldset>
					</form>
					<div class="checkArea">
						<span><a href="">아이디찾기</a></span>
						<span><a href="">패스워드찾기</a></span>
						<span><a href="/joinPage">회원가입</a></span>
					</div>
				</div>
			</div>
		</div>

	


















		
	
		
				</div>
			</div>
	
	
	
	
	
	
	
		</div>
		<jsp:include page="../util/Footer.jsp"></jsp:include>