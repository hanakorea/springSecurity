$("#btn-login").on("click", (e)=>{
	// 버튼 눌렀을 때 요청 
	e.preventDefault();
	
	let user = {
		username : $("#username").val(),
		password : $("#password").val()
	}
	$.ajax({
		type : "POST",
		url : "/auth/login",
		data : JSON.stringify(user),
		contentType : "application/json; charset=utf-8"
	}).done(function(reponse){
		// 로그인 성공했을때 처리할 코드
		alert(reponse.data);
		
		if(reponse.status == 200)
			location.href ="/";
	}).fail(function(error){
		console.log(error);
	})
})

// 로그인 처리(서버쪽 데이터 보내줌)