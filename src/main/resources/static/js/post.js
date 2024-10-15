$(document).ready(function() {
	$("#content").summernote({
		height: 400
	});
})
const postObject = {
	/*버튼에 기능 심기*/
	init: function() {
		$("#btn-insert").on("click", (e) => {
			e.preventDefault();
			this.insertPost();
		}),
		$("#btn-update").on("click", (e)=>{
			e.preventDefault();
			this.modify();
		}),
		$("#btn-delete").on("click", (e) => {
			e.preventDefault();
			this.delete();
		})	
	},
	
	insertPost: function() {
		const post = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "POST",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			if(response.status == 200){
			alert(response.data);
			location.href = "/";
			}else{
				
				alert(response.data)
/*				const result=response.data
				let msg =""
				if(result.title != null)
					msg += result.title + "\n";
				if(result.content != null)
					msg += result.content;
				
				alert(msg);*/
			}	
		}).fail(function(error) {
			console.log(error);
		}
		)
	},
	
	modify : function(){
		const post={
			id :$("#id").val(), // 빼먹지 말기
			title : $("#title").val(),
			content : $("#content").val()
		}
		$.ajax({
			type : "PUT",
			url : "/post",
			data : JSON.stringify(post),
			contentType : "application/json; charset=uft-8"
		}).done(function(response){
			console.log(response)
			location.href = "/post/" + post.id;
		}).fail(function(error){
		console.log(error)
		})
	},
	
	delete : function(){
		
		if(!confirm("게시물 삭제 하시겠습니까?"))
			return;
		/*id심어주고 가져와야됨 span에 들어있는 것은 text*/
		const id = $("#id").text()
		
		$.ajax({
			type : "DELETE",
			url : `/post/${id}`,
			data : JSON.stringify(id),
			contentType : "application/json; charset=utf-8"
		}).done(function(response){
			console.log(response)
			location.href = "/";
		}).fail(function(error){
			console.log(error);
		})
		
	}
};

postObject.init();