<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
//만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
var popupX = (window.screen.width / 2) - (600 / 2);
// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
var popupY = (window.screen.height / 2) - (400 / 2);

function admin_view() {

	window.open("/admin/admin", "_blank", "status=no, width=700, height=800, left=" + popupX + ", top=" + popupY + ", screenX=" + popupX + ", screenY=" + popupY);

}


</script>

<!-- HeaderBox start -->
<div id="header">
	<div class="container">
		<div class="header">
			<h1>
				<a href="/board/list"><img src="../resources/img/huation_CI_5.png" alt="휴에이션 마크"></a>
			</h1>
			<div class="gnb">
				<ul>
					<%-- 로그인/ 로그아웃 --%>
					<c:choose>
						<c:when test="${empty sessionScope.user_id }">
							<li><a href="/board/list">Home</a><span>|</span></li>
							<li><a href="/user/loginPage">로그인</a><span>|</span></li>
							<li><a onclick="location.href='../joinPage'">회원가입</a></li>
						</c:when>
						<c:when test="${sessionScope.user_id eq 'admin' }">
							<li><strong>관리자</strong> 님 환영합니다.<span>|</span></li>
							<li><a href="/board/list">Home</a><span>|</span></li>
							<li><a onclick="admin_view()">관리자페이지</a><span>|</span></li>
							<li><a href ="/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><strong>${user_id }</strong> 님 환영합니다.<span>|</span></li>
							<li><a href="/board/list">HOME</a><span>|</span></li>
							<li><a href ="/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!-- //header-->
	</div>
	<!-- //container -->
	<div id="lnb">
		<div class="container">
			<div class="lnb">
				<ul>
					<li><a class="mMenu"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- // lnb -->
</div>
<!--// HeaderBox -->
