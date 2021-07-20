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
		/* 페이지 불러오기와 동시에 일반게시판 등록 차트가 띄워짐*/
		$(function(){
			readyChart()
		})
		
		/* 만들어진 데이터 차트에 뿌리기 */
		function makeChart(ctx,type,labels, data, label) {
			var myChart = new Chart(ctx, {
			    type: type,
			    data: {
			        labels: labels,
			        datasets: [{
			            label: label,
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
			})
			
		}
		
		/*일반게시판 일별 등록수*/
		function readyChart(){

			$.ajax({
				type: "GET",
				url: "dobar",
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
					var ctx = $('#myChartbar');
					$('#myChartline').hide();
					$('#myChartbar').show();
					makeChart(ctx,'bar', newLabels, newMyData,'일반게시판 날짜별 게시물 등록수');
				}
			});
			
		}
		
		/*AJAX 게시판 일별 등록수 */
		function readyAjaxChart(){
			
			$.ajax({
				type: "GET",
				url: "doline",
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

					// Chart.js 선그래프 그리기
					var ctx = $('#myChartline');
					$('#myChartbar').hide();
					$('#myChartline').show();
					
					makeChart(ctx,'line', newLabels, newMyData,'AJAX 게시판 날짜별 게시물 등록수');
				}
			});
			
		}
		
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
			<h2>Chart 게시판</h2>
		</div>
		<!-- 게시판 부분 시작 -->
		<div id="content">
		<%-- 내용이 들어가는 부분 --%>
		<div class="btn_c">
			<div class="fR">
				<span class="bbtn_bg1"><input type="button" value="일반게시판" onclick="readyChart()"></span>
				<span class="bbtn_bg2"><input type="button" value="AJAX게시판" onclick="readyAjaxChart()"></span>
			</div>				
		</div>
		<canvas id="myChartbar"></canvas>
		<canvas id="myChartline"></canvas>

	</div><!-- //content -->
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>
