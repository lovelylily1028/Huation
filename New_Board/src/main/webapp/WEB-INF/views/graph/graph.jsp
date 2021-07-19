<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	<link rel="stylesheet" href="/resources/css/newCss.css" type="text/css" >
	<link rel="stylesheet" href="/resources/css/NewCSS2.css" type="text/css">
	
	<title>HUATION</title>
	
	<script>
		$(function() {

			function makeChart(ctx,labels, data) {
				var myChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: labels,
				        datasets: [{
				            label: '날짜별 게시글 등록 수',
				            data: data,
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
			}
			
			$.ajax({
				type: "GET",
				url: "dograph",
				success: function(data, status, xhr) {

					// JSON 객체 배열 데이터를 Javascript 배열로 변환
					console.log(data);
					var labels = [];
					var myData = [];
					data.map(function(item) {
						labels.push(item.wrtday);
					});
					data.map(function(item) {
						myData.push(item.bcount);
					});

					var newLabels = labels.slice(-5);
					var newMyData = myData.slice(-5);

					// Chart.js 막대그래프 그리기
					var ctx = $('#myChart');
					makeChart(ctx, newLabels, newMyData);
				}
			});
			
		});
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
		<div id="content">
		<%-- 내용이 들어가는 부분 --%>
		<canvas id="myChart"></canvas>

		
	</div><!-- //content -->
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>
