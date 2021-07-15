// 아이디 중복 체크
function idCheck(idT) {
	
	if(idT == ""){
		alert('아이디를 먼저 입력해주세요.')
		return false
	}
	
	$.ajax({
		url:"joinIdCheck",
		type:"POST",
		data:{
			user_id:idT
		},
		dataType:"JSON",
		success:function(data){
			if(data.result == '0'){
				$('#idCheckResult').val(0);
				$('#user_id').attr('readonly',true);
				$('#idCheckbtn').attr('type','hidden');
				$('#idCheckOk').css('visibility',"visible");
			}else{
				alert('이미 사용중인 아이디입니다.')
			}
		},
		error:function(){
			alert('에러발생')
		}
	})
	
}


// 회원가입 유효성 검사
function joinFormCheck() {

	var idPtn = /^[a-z]{1}[a-zA-Z0-9]{2,7}$/;
	if (idPtn.test($("#user_id").val()) != true) {
		alert("아이디를 형식에 맞게 입력해 주세요!");
		$("#user_id").next().text("아이디 첫 글자는 영문 소문자만, 영어 대/소문자+숫자의 조합으로 3~8자리까지 입력가능합니다.");
		return false;
	}

	var pwPtn = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{3,10}$/;
	if (pwPtn.test($("#user_pw").val()) != true) {
		alert("비밀번호를 형식에 맞게 입력해 주세요!");
		$("#user_pw").next().text("비밀번호는 영어대문자, 영어소문자, 숫자가 각각 1개이상 포함된 조합으로 3~10자리까지 입력가능합니다.");
		return false;
	}

	var namePtn = /^[가-힣\sA-Za-z]{1,}$/;
	if (namePtn.test($("#user_name").val()) != true) {
		alert("이름은 한글, 영어만 입력가능합니다.(공백 포함)");
		$("#user_name").next().text("이름은 한글, 영어만 입력가능합니다.(공백 포함)");
		return false;
	}

	if ($('#user_pwcheck').val() == '') {
		alert("비밀번호 확인을 입력해주세요.");
		return false;
	}
	if ($('#user_pwcheck').val() !== $('#user_pw').val()) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	if ($('#email').val() == '') {
		alert("이메일을 입력해주세요.");
		return false;
	}
	if ($('#email_adr').val() == '') {
		alert("이메일을 입력해주세요.");
		return false;
	}
	if ($('#idCheckResult').val() != 0) {
		alert('아이디를 확인해주세요.!');
		return false;
	}

	this.form.submit();
}

// 로그인 폼 체크
function loginFormCheck(f) {

	if ($('#user_id').val() == '') {
		alert("아이디를 입력해주세요.");
		return false;
	}

	if ($('#user_pw').val() == '') {
		alert("비밀번호를 입력해주세요.");
		return false;
	}

	f.submit();


}//

$(function() {
	$('#alert-success').hide();
	$('#alert-danger').hide();

	$('input').keyup(function() {

		var pw1 = $('#user_pw').val();
		var pw2 = $('#user_pwcheck').val();

		if (pw1 != "" || pw2 != "") {
			if (pw1 == pw2) {
				$('#alert-success').show();
				$('#alert-danger').hide();
			} else {
				$('#alert-danger').show();
				$('#alert-success').hide();
			}
		}
	})
})



