<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
	<title>HUATION😂😂</title>
	
	<script type="text/javascript">
		alert($(map.glist.length))
	
	</script>
	
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
			<h2>막대그래프</h2>
		</div>
		<!-- 게시판 부분 시작 -->
		<div id="content" style="border: 3px solid red;">
		<%-- 내용이 들어가는 부분 --%>
		<canvas id="myChart"></canvas>

		
		<script type="text/javascript">
		
	// 우선 컨텍스트를 가져옵니다. 
	var ctx = document.getElementById("myChart").getContext('2d');
	
	/*
	- Chart를 생성하면서, 
	- ctx를 첫번째 argument로 넘겨주고, 
	- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
	*/
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	    	/* 일자 부분 */
	        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
	        datasets: [{
	            label: '# 최근 일주일 일자별 게시물 등록 수',
	            /* 데이터 */
	            data: [15, 19, 3, 5, 2, 3],
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	</script>
		
	</div><!-- //content -->
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>
