<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
		<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
		<title>HUATION</title>
		<script type="text/javascript">
		// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
  		
		var popupX = (window.screen.width / 2) - (600 / 2);
  		// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
  		var popupY= (window.screen.height /2) - (400 / 2);
		
		/* 새글 쓰기 창 불러오기 */
		function add_view(){
			
  			window.open("/H2_jpa/add", "_blank", "status=no, width=700, height=800, left="+popupX+", top="+popupY+", screenX="+popupX+", screenY="+popupY);
			
		}
		</script>
	</head>
	
	<body>
		<jsp:include page="../util/Header.jsp"></jsp:include>
		<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
		<div class="sub_container">
			<jsp:include page="../util/submenu.jsp"></jsp:include>
		<div id="contents" >
		<div class="sub_top" id="sub_top01"> 
		<!-- 페이지 메뉴별로 바뀌께-->
			<h2>JPA 게시판(H2)</h2>
		</div>
		<!-- 게시판 부분 시작 -->
		<div id="content">
			<div id="bbs_search">
				<form name="search" method="post" action="/board/list">
					<fieldset>
						<legend>검색조건 입력폼</legend>
                              <select name="category" id="category">
                                  <option value="all" ${map.category eq 'all'? 'selected':'' } >카테고리를 선택해주세요.</option>
                                  <option value="title" ${map.category eq 'title'? 'selected':'' } >제목</option>
                                  <option value="writer" ${map.category eq 'writer'? 'selected':'' } >작성자</option>
                                  <option value="content" ${map.category eq 'content'? 'selected':'' } >내용</option>
                               </select>
						<input name="search" id="search"  type="text" class="inp_s">
						<span class="bbtn_s"><input type="button" value="검색" title="검색" onclick="searchCheck(this.form)"></span>
					</fieldset>
				</form>
			</div>			
			<div id="bbs_wrap">
				<div class="btn_c">
					<div class="fL">총 게시물 <strong>${map.listCount}</strong>건 ㅣ 현재페이지 <strong> ${map.page }</strong>/${map.maxpage }</div>
				</div>
				
				<div id="" >
					<table summary="이 표는 게시판 목록으로
						번호,
						제목
						작성자,
						파일,
						작성일,
						조회수 항목으로 구성되어 있습니다"
					 class="basic_chart">
					<thead>
						<tr>
							<th class="num" scope="col">번호</th>
							<th class="tit" scope="col">제목</th>
							<th class="writer" scope="col">작성자</th>
							<th class="date" scope="col">작성일</th>
						</tr>					
					</thead>
					<!-- 리스트 반복 부분 -->
					<tbody>
						<c:if test="${empty map.list}">
						<tr>
							<td colspan=6 class="writer">
								 # 현재 작성된 글이 없습니다.
							</td>
						</tr>
					</c:if>
					<%-- map.list 반복 출력 부분 --%>
					<c:forEach var="dto" items="${map.list}">
						<tr>
							<td class="num">${dto.id }</td>
							<td class="tit">
		                        <a href="/H2_jpa/post/${dto.id}">${dto.title }</a> &nbsp;
							</td>
							<td class="writer">
								${dto.writer }
							</td>
							<td class="date">${dto.createdDate }</td>
						</tr>
					</c:forEach>
					<%-- // map.list --%>		
					</tbody>
				</table>				
			</div>
			<%-- 글쓰기 버튼 --%>
			<div class="btn_all">
				<div class="fR">
					<span class="bbtn_confirm2"><input type="button" onclick="location.href='/H2_jpa/add'" value="글쓰기"></span>
				</div>				
			</div>
			<%-- // 글쓰기 버튼 --%>
		<%-- 페이징 부분--%>
		<div id="paging">
			<%-- 페이지가 1개 뿐일 경우--%>
		</div>
		<%-- 페이징 끝 --%>
		</div> <!-- #bbsWrap end -->
	</div>
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>