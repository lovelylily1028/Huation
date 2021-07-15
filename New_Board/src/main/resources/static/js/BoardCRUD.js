
// 게시글 리스트
	 function boardList(bid){
			location.href = './boardList_view';
	}
	
	
function searchCheck(f){
	if($('#search').val() ==""){
		alert("검색어를 입력해주세요.!");
		return false;
	}
	f.submit();
}
	


// 게시물 수정, 삭제
	function boardCRUD(num,bid){
			if(num == '1'){
				if($("#title").val()==""){
					alert("제목을 입력해주세요.");
					return false;
				}
				if($("#contents").val()==""){
					alert("등록 될 내용을 입력해주세요.");
					return false;
				}
				document.writeForm.submit();
				
			}else if(num == '2'){
				if(confirm('수정하시겠습니까?')){
					location.href = './edit_view?bid='+bid;
				}else{
					return false;
				}
			}else if(num == '3'){
				if(confirm('삭제하시겠습니까?')){
					location.href = './delete?bid='+bid;
				}else{
					return false;
				}
			}else if(num == '4'){
				location.href = '/NewView?bid='+bid;
				
			}else if(num == 'all'){
				location.href = '/Newlist';
				
			}else if(num == 'reply'){
				location.href = 'Board/reply_view?bid='+bid;
			}
			
}//

function addCheck(f){
	
	 if ($('#titleTemp').val() == ''){
		  alert('제목을 입력해주세요.')
		  $('#titleTemp').focus();
		  return;
	  }
	  
	  if ($('#contentTemp').val() == ''){
		  alert('내용을 입력해주세요.')
		  $('#contentTemp').focus();
		  return;
	  }
	  
	  $('#title').val ( $('#titleTemp').val() );
	  $('#bcontents').val ( $('#contentTemp').val() );
	  
	  f.submit(); 
	
}


function updateOpen(){
	  $('#titleTemp').attr('contenteditable',true);
	  $('#titleTemp').css('background','orange');
	  $('#contentTemp').attr('contenteditable',true);
	  $('#contentTemp').css('background','orange');	
		
	  $('#updateSubmit').attr('type','button');
	  $('#file').attr('type','file');
}

function updateClose(){
	  $('#titleTemp').attr('contenteditable',false);
	  $('#titleTemp').css('background','none');
	  $('#contentTemp').attr('contenteditable',false);
	  $('#contentTemp').css('background','none');
		
	  document.getElementById("titleTemp").innerHTML =$('#title').val();
	  document.getElementById("contentTemp").innerHTML =$('#bcontents').val();
	  
	  $('#file').attr('type','hidden');
	  
	  $('#updateSubmit').attr('type','hidden');
}


function editToggle(){
	  if ($('#updateBtn').val() == '수정'){
		  updateOpen();
		  $('#updateBtn').val('취소')
	  }else {
		  updateClose()
		  $('#updateBtn').val('수정')
	  }
}

function editCheck(f){
	
	var titleTemp = document.getElementById('titleTemp').innerText
	var contentTemp = document.getElementById('contentTemp').innerText
	
		  
	  if (titleTemp == ''){
		  alert('제목을 입력해주세요.')
		  $('#titleTemp').focus();
		  return;
	  }
	  
	  if (contentTemp == ''){
		  alert('내용을 입력해주세요.')
		  $('#contentTemp').focus();
		  return;
	  }
	  
	  $('#title').val ( titleTemp );
	  $('#bcontents').val (contentTemp);



	  f.submit(); 
  }



function DeleteCheck(f){
	
	if(confirm ('삭제하시겠습니까?')){
		
		location.href="/board/delete?bid="+$(f.bid).val();
	}else{
		return false;
	}
	
}



// 댓글 CRUD
	function commentCRUD(num,f){
		if(num == '1'){
			if($("#cmtCon").val()==""){
				alert("내용이 작성되지 않았습니다.!");
				return false;
			}
			
			f.submit();
			
		}else if(num =='2'){
			if ( $(f.contentTemp).val()=='' ){
				alert('내용을 입력해주세요.')
				$(f.contentTemp).focus();
				return;
			}
			
			$(f.recontent).val( $(f.contentTemp).val() )
			
			f.submit();	
			
		}else if(num == '3'){
			if(confirm('댓글을 삭제하시겠습니까?')){
				location.href= './commentDelete?bid='+$(f.bid).val()+'&cid='+$(f.cid).val();
			}else{
				return false;
			}
			
		}

}

// 댓글 수정하기Toggle
	function Edit(f){
		if ( $(f.commentToggle).val() == '수정' ){
		
		$(f).css("border","3px solid red");
		$(f.contentTemp).attr("readonly",false)
		$(f.commentHidden).attr("type","button")			
		$(f.commentToggle).val('취소')
		
	}else {
		$(f.contentTemp).val( $(f.recontent).val() )
		
		$(f).css("border","1px solid #e8e8e8");
		$(f.contentTemp).attr("readonly",true)
		$(f.commentHidden).attr("type","hidden")
		$(f.commentToggle).val('수정')
	}
}






