<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
		
		<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
		<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
		
		<title>AJAX-Board</title>
		
		<script type="text/javascript">
		
		/* 페이지 불러오기와 동시에 처리해야할 일 */
		$(function parentPage(){
			paging()
		})

		function paging(pageT){
			
			$.ajax({
				url:"doList",
				type:"POST",
				data:{
					page:pageT
				},
				dataType:"JSON",
				success:function(data){
					
					<%--페이지 번호에 따라서 tbody 부분이 변경되서 출력--%>
					list(pageT)					
					
					<%--페이지부분도 클릭에 따라서 값이 바뀌어야 됨--%>
					htmlPg ="";
					
					<%--현재 선택된 페이지가 1일 경우 이전페이지 버튼이 없음--%>
					if(data.page <= 1){
						htmlPg += "<strong class='current'></strong>"
					}else{
						htmlPg +="<a onclick='paging("+(data.page-1)+")'>◀이전페이지</a>"
					}
					
					<%-- 현재 선택된 페이지 외에는 a태그 설정--%>
					for(i=data.startpage; i<=data.endpage; i++){
						
						if(data.page == i){
							htmlPg +="<strong class='current'>"+i+"</strong>"
						}else{
							htmlPg += "<a onclick='paging("+i+")'>"+i+"</a>"
						}
					} 
					
					<%--마지막 페이지에서는 다음페이지 버튼 사라짐--%>
					if(data.page >= data.maxpage){
						htmlPg +="<strong class='page'></strong>"
					}else{
						htmlPg +="<a onclick='paging("+(data.page+1)+")'>다음페이지▶</a>"	
					}
					$('#paging').html(htmlPg)
					
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			}) 
		}//
		
			// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
  			var popupX = (window.screen.width / 2) - (600 / 2);
  			// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
  			var popupY= (window.screen.height /2) - (400 / 2);
		
		/* 상세보기 페이지 */
		function view(bidT,pageT){
  			
  			window.open("/aview?bid="+bidT+"&page="+pageT, "_blank", "status=no, width=800, height=800, left="+popupX+", top="+popupY+", screenX="+popupX+", screenY="+popupY);
  			
		}
		
		function add_view(){
			
  			window.open("/add_view", "_blank", "status=no, width=800, height=600, left="+popupX+", top="+popupY+", screenX="+popupX+", screenY="+popupY);
			
		}
		
		
		<%-- 리스트를 불러오는 메소드--%>
		function list(pageT){
			$.ajax({
				url:"doList",
				type:"POST",
				data:{
					page:pageT
				},							
				dataType:"JSON",
				success:function(data){
					html ="";

					if(data.list.length == 0){
						 html +="<tr><td colspan=6 class='writer'> # 현재 작성된 글이 없습니다.	</td></tr>"				
					}else{
						html="";
						for(i=0; i<data.list.length; i++){
							html += "<tr>";
							html += "<td class='num'>"+data.list[i].bid+"</td>";
							html += "<td class='tit'>";
						    html += "<div class='box' onclick='view("+data.list[i].bid+","+data.page+")'>"; 
						    	if(data.list[i].bindent !=0){
						    		for(j=0; j<data.list[i].bindent; j++){
						    			html += "└▶";
						    		}
						    	}
						    html += data.list[i].title;
		                    html += "</div></td>";
		                    html += "<td class='writer'>"+data.list[i].user_id+"</td>";
		                    html += "<td class='file'>";
		                    if(data.list[i].fileName != null ){
		                    	html +="<img src='/resources/img/ico_file.gif'>";
		                    }else{
				                html +="-";
		                    }
		                    html +="</td>";
		                    html += "<td class='date'>"+data.list[i].day+"</td>";
		                    html += "<td class='hits'>"+data.list[i].hit+"</td></tr>";
						}
					}

					$('#tbody').html(html)
					$('#all').html(data.listCount) // 총게시물 갯수
					$('#nowpage').html(data.page) // 현재페이지 
					$('#maxpage').html(data.maxpage) // 최대 페이지

				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			})
		}//
		
		</script>
	</head>
	<body>
		<jsp:include page="../util/Header.jsp"></jsp:include>
		
		<div id="wrap">
		<!-- sub start -->
		<div class="sub_padding">
		<div class="sub_container">
			<jsp:include page="../util/submenu.jsp"></jsp:include>
		<div id="contents">
		<div class="sub_top" id="sub_top01"> 
			<h2>AJAX 게시판</h2>
		</div>
			<%-- 게시판이 들어가는 부분 --%>
	        	  
			<form id="listForm" name="listForm" method="post" action="">
				
				<div class="bg_btn">
					<div class="fL">총 게시물 <strong id="all"></strong>건 ㅣ 현재페이지 <strong id="nowpage"></strong> / <strong id="maxpage"></strong> </div>
				</div>
				
				<div id="" >
					<table summary="이 표는 게시판 목록으로
						번호,
						제목
						작성자,
						파일,
						작성일,
						조회수 항목으로 구성되어 있습니다"
					 class="chart1">
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
					<%-- 리스트가 들어가는 부분--%>
					<tbody id="tbody">
					
					</tbody>
					<%-- // 리스트가 들어가는 부분--%>
				</table>				
			</div>
			
			
			
			<%-- 글쓰기 버튼 --%>
			<div class="btn_all">
				<div class="fR">
					<span class="bbtn_confirm2"><input type="button" onclick="add_view()" value="글쓰기"></span>
				</div>				
			</div>
			<%-- // 글쓰기 버튼 --%>
		</form>
		
		
		<%-- 페이징 부분--%>
		<div id="paging">
			
		</div>
		<%-- 페이징 끝 --%>
		
		
		
		</div> <!-- #bbsWrap end -->
		</div>		
			<%-- --%>
		</div>
		</div>
		<!-- sub end -->
		</div>
		</div>
<!-- //wrap_content start -->
	
	
	
	
	
	
		<jsp:include page="../util/Footer.jsp"></jsp:include>