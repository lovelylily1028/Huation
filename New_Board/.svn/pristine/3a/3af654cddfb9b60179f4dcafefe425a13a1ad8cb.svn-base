/**
 * 
 */
/* 리스트 가져오기 */
		  $(function list(){
			$.ajax({
				url:"ajax",
				type:"POST",
				data:{},							
				dataType:"html",
				success:function(data){
					$('.tempDiv').html(data)
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			})
		})
		
		/* 리스트 페이징 */
		function paging(categoryT,searchT,pageT){
			$.ajax({
				url:"ajax",
				type:"POST",
				data:{category:categoryT,
					search:searchT,
					page:pageT
				},
				dataType:"html",
				success:function(data){
					$('.tempDiv').html(data)
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			}) 
		}
		
		
		/* 상세보기 */
		function view(categoryT,searchT,pageT,bidT){
			$.ajax({
				url:"view",
				type:"POST",
				data:{category:categoryT,
					search:searchT,
					page:pageT,
					bid:bidT
				},
				dataType:"html",
				success:function(data){
					$('.tempDiv').html(data)
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			}) 
		}
		
		
		/* 새글 및 답글 작성페이지로 연결 */
		 function add_view(bidT){
			$.ajax({
				url:"add_view",
				type:"POST",
				data:{
					bid:bidT
				},
				dataType:"html",
				success:function(data){
					$('.tempDiv').html(data)
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
			})
		}
		
		/* 새글,답글,수정 db업로드 */
		/* 이후 페이징 처리 작업 필요 */
		function addChk(f){
						
			  if ($('#titleTemp').val() == ''){
				  alert('제목을 입력해주세요.')
				  $('#titleTemp').focus()
				  return;
			  }
			  
			  if ($('#contentTemp').val() == ''){
				  alert('내용을 입력해주세요.')
				  $('#contentTemp').focus()
				  return;
			  }
			  
			  $('#title').val ( $('#titleTemp').val())
			  $('#bcontents').val ( $('#contentTemp').val())
			
			  // rmt를 받아옴
			  var rmtT = $(f.rmt).val()
			  
			  // 요청에 따라 각각 다른 Form을 담아준다.
			  var form
			  
			  switch (rmtT){
			  case "add" :
				  form =$('#writeForm')[0];
				  break
			  case "reply" :
				  form =$('#replyForm')[0];
				  break
		       default :
		    	   alert("잘못된 접근입니다.")
		    	   return false
		    	   break
			  }
			  
			  // FormData object 생성
			  var data = new FormData(form);
			  				  
			  $.ajax({
				  url:"addChk2",
				  type:"POST",
				  enctype:"multipart/form-data",
				  data: data,
				  processData: false,
				  contentType: false,
				  cache: false,
				  timeout: 600000,
				  success:function(data){
					  $('.tempDiv').html(data)
				  },
				  error:function(){
					  alert('게시물 등록에 실패하였습니다.')
				  }
			  })
			
		}
		
		
		/* 게시물 삭제 */
		function del(bidT){
			if(confirm('삭제하시겠습니까?')){
				$.ajax({
					url:"del",
					type:"POST",
					data:{bid:bidT},
					dataType:"html",
					success:function(data){
						$('.tempDiv').html(data)
						alert(bidT+"번 게시물이 삭제 되었습니다.")
					},
					error:function(){
						alert('게시물 삭제에 실패하였습니다.')
					}
				})
			}else{
				return false;
			}
			
		}
		
		/* 댓글 처리 이후 paging */
		function AfterPaging(bidTemp){
			
			$.ajax({
				url:"view",
				type:"POST",
				data:{
					bid:bidTemp
				},
				dataType:"html",
				success:function(data){
					$('.tempDiv').html(data)
				},
				error:function(){
					alert('페이지를 불러오는데 실패하였습니다.')
				}
				
			})
		}
		
		
		/* 댓글 등록 및 수정*/
		function cmtAdd(f,bidT){
			
			if($("#recontent").val()==""){
				alert("내용이 작성되지 않았습니다.!");
				$(f.recontent).focus();
				return false;
			}
			
			var crmtT = $(f.crmt).val()
			
			switch (crmtT){
			case "add" :
				var form = $('#cmtAddForm').serialize();
				break;
			case "edit" :
				var form = $('#cmtEditForm').serialize();
				break;
			default :
				alert("잘못된 접근입니다.")
				return false
				break;
			}
			
			$.ajax({
				url:"cmtAdd",
				type:"POST",
				data: form,
				dataType:"JSON",
				success:function(data){
					if(data.result == 'true'){
						AfterPaging(bidT)
					}else{
						alert('댓글 등록에 실패하였습니다.')
					}
				},
				error:function(){
					alert('오류발생.')
				}
			})
		
		}
		
		/* 댓글 삭제 */
		function cmtDel(bidT,cidT){
			if(confirm('댓글을 삭제하시겠습니가?')){
				
				$.ajax({
					url:"cmtDel",
					type:"POST",
					data: {
						bid:bidT,
						cid:cidT
					},
					dataType:"JSON",
					success:function(data){
						if(data.result == 'true'){
							AfterPaging(bidT)
						}else{
							alert('댓글 삭제에 실패하였습니다.')
						}
					},
					error:function(){
						alert('오류발생.')
					}
				})
			}else{
				return false;
			}
			
		}
		