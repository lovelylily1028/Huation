<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
	<%-- 여기에 검색 스크립트 넣어야댐!! --%>
		<script type="text/javascript">
		function searchCheck(f){
			if($('#search').val() ==""){
				alert("검색어를 입력해주세요.!");
				return false;
			}
			f.submit();
		}
		</script>
	<title>HUATION</title>
</head>
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
			<h2>일반게시판</h2>
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
					<div class="fR">
						<form action="/excelUp" name="excelUpForm" id="excelUpForm" method="post" enctype="multipart/form-data">
							<input type="file" name="file" id="file" accept=".xlsx, .xls">
							<span class="bbtn_bg1"><input type="submit" value="엑셀 업로드"></span>
							<span class="bbtn_bg2"><input type="button" value="엑셀 다운로드" onclick="location.href='/downExcel?page=${map.page }&search=${map.search}&category=${map.category}'"></span>
						</form>
					</div>				
				</div>
				
				<div id="" >
					<table summary="이 표는 게시판 목록으로
						번호,
						제목
						작성자,
						파일,
						작성일,
						조회수 항목으로 구성되어 있습니다"
					 class="chart">
					<thead>
						<tr>
							<th class="num" scope="col">번호</th>
							<th class="tit" scope="col">제목</th>
							<th class="writer" scope="col">작성자</th>
		 					<th class="file" scope="col">파일</th>
							<th class="date" scope="col">작성일</th>
							<th class="hits" scope="col">조회수</th>
						</tr>					
					</thead>
					<!-- 리스트 반복 부분 -->
					<tbody>
					<%-- 작성된 게시물이 없을때 / 검색 후 list가  없을 때 --%>
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
							<td class="num">${dto.bid }</td>
							<td class="tit">
						     	<c:forEach begin="1" end="${dto.bindent }" >
									└▶
        						</c:forEach>
		                        <a href="/board/view?category=${map.category }&search=${map.search }&page=${map.page}&bid=${dto.bid }">${dto.title }</a> &nbsp;
							</td>
							<td class="writer">
								${dto.user_id }
							</td>
							<c:choose>
								<c:when test="${dto.fileName != null }">
									<td><img src="/resources/img/ico_file.gif"></td>
								</c:when>
								<c:otherwise>
									<td class="file">-</td>
								</c:otherwise>
							</c:choose>
							<td class="date">${dto.day }</td>
							<td class="hits">${dto.hit }</td>
						</tr>
					</c:forEach>
					<%-- // map.list --%>
					</tbody>
				</table>				
			</div>
			<%-- 글쓰기 버튼 --%>
			<div class="btn_all">
				<div class="fR">
					<span class="bbtn_confirm2"><input type="button" onclick="location.href='/board/addView'" value="글쓰기"></span>
				</div>				
			</div>
			<%-- // 글쓰기 버튼 --%>
		<%-- 페이징 부분--%>
		<div id="paging">
			<%-- 페이지가 1개 뿐일 경우--%>
			<c:choose>
				<c:when test="${ map.page<=1 }">
					<strong class="current">${nowpage }</strong>
				</c:when>
				<c:otherwise>
					 <a href="./list?category=${map.category }&search=${map.search }&page=${map.page-1}" class="page" title="이전페이지로 이동">◀이전페이지</a>				
				</c:otherwise>
			</c:choose>
			<%-- 현재 페이지 외 다른 페이지 --%>	
			<c:forEach var="nowpage" begin="${map.startpage }" end="${map.endpage }">
				<c:choose>
			       <c:when test="${map.page==nowpage}">
			    	    <strong class="current">${nowpage}</strong>
				   </c:when>
				<c:otherwise>
				   <a href="./list?category=${map.category }&search=${map.search }&page=${nowpage}" class="page">${nowpage}</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<%-- 다음 페이지 --%>
			<c:choose>
				 <c:when test="${map.page >= map.maxpage }">
				   	<strong class="page"></strong>
				  </c:when>
				  <c:otherwise>
				        <a href="./list?category=${map.category }&search=${map.search }&page=${map.page+1}" class="page" title="다음페이지로 이동" >다음페이지▶</a>
				  </c:otherwise>
			</c:choose>
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
