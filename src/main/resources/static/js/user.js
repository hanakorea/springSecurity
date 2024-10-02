const userObject = {
	init:function(){
		$('#btn-save').on('click', (e)=>{
			// send로 되어있어 정보들 보내버림, 기본동작 X하게 만들어야함
			e.preventDefault();
			this.insertUser();
		})/*object 초기화 - init눌렀을때 이벤트를 모두 적용시켜줌(init함수 호출필요)*/
	} ,
	
	insertUser:function(){
		alert("회원가입요청");
		// 회원가입 시 입력한 정보를 추출
		let user = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		
		$.ajax({
			type : "POST",
			url : "/auth/insertuser",
			data : JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response){
			console.log(response)
			alert(response.data);
			//원래 페이지로 이동
			if(response.status == 200) //정상적으로 회원가입됬을 때
				location.href = "/";
		}).fail(function(error){
			console.log(error);
		})
	},
}

$("#btn-update").on('click',(e)=>{
	e.preventDefault();
	if(!confirm("회원 정보를 수정하시겠습니까?"))
		return;
	
	alert('수정처리 진행할 예정');
	let user ={ 
		id : $("#id").val(),
		password : $("#password").val(),
		email : $("#email").val()
	};
	
	$.ajax({
		type : "PUT",
		url : "/auth/update",
		data : JSON.stringify(user),
		contentType : "application/json; charset=utf-8"
	}).done(function(reponse){
		alert(reponse.data);
		
		location.href = "/";
	}).fail(function(error){
		console.log(error);
	})
})
$("#btn-delete").on('click',(e)=>{
	e.preventDefault();
	if(!confirm("회원을 탈퇴 하시겠습니까?"))
		return;
	
	alert('탈퇴처리 진행할 예정');
	let user ={ 
		id : $("#id").val(),
	};
	
	$.ajax({
		type : "DELETE",
		url : "/auth/delete",
		data : JSON.stringify(user),
		contentType : "application/json; charset=utf-8"
	}).done(function(reponse){
		alert(reponse.data);
		
		location.href = "/";
	}).fail(function(error){
		console.log(error);
	})
})

userObject.init();