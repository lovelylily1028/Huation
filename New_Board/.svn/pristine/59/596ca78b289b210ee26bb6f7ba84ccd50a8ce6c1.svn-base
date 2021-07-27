<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			 if(dd<10){
			        dd='0'+dd
			    } 
			    if(mm<10){
			        mm='0'+mm
			    } 
	
			today = yyyy+'-'+mm+'-'+dd;
			
			lastweek = yyyy+'-'+mm+'-'+ (dd-6);
			
			document.getElementById("start").setAttribute("value", lastweek);
			document.getElementById("start").setAttribute("max", today);
			
			document.getElementById("end").setAttribute("value", today);
			document.getElementById("end").setAttribute("max", today);
			
			
			readyChart()
			
		})
		
		/* 기간별 검색 */
		function searchChartTerm(f){
			
			// 막대그래프인지 선그래프인지 결정
			var urlT = f.chooseGraph.value
			var startT = f.start.value
			var endT = f.start.value
		
			switch(urlT){
			
			case "bar":
				readyChart(startT,endT)
				break;
			case "line":
				readyAjaxChart(startT,endT)
				break;
			default :
				alert('잘못된 접근입니다.')
				break;
			}
		
		}
		
		function randomColor(labels) {
			var colors = [];
			for (let i = 0; i < labels.length; i++) {
				colors.push("#" + Math.round(Math.random() * 0xffffff).toString(16));
			}
			return colors;
		}
		
		/* 만들어진 데이터 차트에 뿌리기 */
		function makeChart(ctx,type,labels, data, label) {
			
			var myChart = new Chart(ctx, {
			    type: type,
			    data: {
			        labels: labels,
			        datasets: [{
			            label: label,
			            data: data,
			            backgroundColor: randomColor(labels)
			            	
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
		function readyChart(startT,endT){
			
			$('#canvasArea').remove($('myChartbar'))
			$('#canvasArea').html("<canvas id='myChartbar'></canvas>")
			
			
			var startT = $('#start').val()
			var endT = $('#end').val()

			$.ajax({
				type: "GET",
				url: "dobar",
				data:{
					start : startT,
					end : endT
				},
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

					
					var newLabels = labels
					var newMyData = myData
					

					// Chart.js 막대그래프 그리기
					var ctx = $('#myChartbar');
					$('#myChartline').hide();
					$('#myChartbar').show();
					$('#chooseGraph').val('bar');
					
					makeChart(ctx,'bar', newLabels, newMyData,'일반게시판 날짜별 게시물 등록수');
				}
			});
			
		}
		
		/*AJAX 게시판 일별 등록수 */
		function readyAjaxChart(startT,endT){
			
			$('#canvasArea').remove($('myChartline'))
			$('#canvasArea').html("<canvas id='myChartline'></canvas>")
			
			
			var startT = $('#start').val()
			var endT = $('#end').val()
			
			$.ajax({
				type: "GET",
				url: "doline",
				data:{
					start : startT,
					end : endT
				},
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

						var newLabels = labels
						var newMyData = myData
					
					// Chart.js 선그래프 그리기
					var ctx = $('#myChartline');
					$('#myChartbar').hide();
					$('#myChartline').show();
					$('#chooseGraph').val('line');
					
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
		<div id="bbs_search">
		
			<%-- 기본값은 최근 일주일 조회 --%>
			<form name="search" method="post">
				<input type="date" id="start" name="start" min="2021-06-01" max="" value="">	
				<strong>부터</strong>
				<input type="date" id="end" name="end" min="2021-06-01" max="" value="">	
				<strong>까지</strong>
				<input type="hidden" id="chooseGraph" value="bar" >
				<span class="bbtn_s"><input type="button" value="조회" title="지정기간 조회" onclick="searchChartTerm(this.form)"></span>
			</form>
		</div>		
		
		
		<%-- 내용이 들어가는 부분 --%>
		<div class="btn_c">
			<div class="fR">
				<span class="bbtn_bg1"><input type="button" value="일반게시판" onclick="readyChart()"></span>
				<span class="bbtn_bg2"><input type="button" value="AJAX게시판" onclick="readyAjaxChart()"></span>
			</div>				
		</div>
		<div id="canvasArea">
			<!--  일반게시판 그래프가 출력 될 곳 -->
			<canvas id="myChartbar"></canvas>
			<!-- AJAX 게시판 그래프가 출력 될 곳 -->
			<canvas id="myChartline"></canvas>
		</div>




	</div><!-- //content -->
</div>
</div>
<!-- sub end -->
</div>
</div>
<!-- //wrap_content start -->
		
<jsp:include page="../util/Footer.jsp"></jsp:include>
