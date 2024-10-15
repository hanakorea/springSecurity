$("#btn-save-reply").on("click",(e)=>{
	e.preventDefault();
	
	const postId = $("#postId").val();
	const reply ={
		content : $("#reply-content").val()
	};
	$.ajax({
		type : "POST",
		url : "/reply/"+postId,
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8"
	}).done(function(response){
		alert(response.data);
		location.href ="/post/" + postId;
	}).fail(function(error){
		console.log(error);
	})
})

$(".reply-delete").on("click", (e)=>{
	e.preventDefault();
	/* e.target-내가 선택한 태그&dataset이용*/
  /*console.log(e.target.dataset.id);*/
	const replyId =e.target.dataset.id;
	
	$.ajax({
		type : "DELETE",
		url : "/reply/" + replyId
	}).done((response)=>{
		alert(response.data)
		location.reload(); /*새로고침*/
	}).fail((error)=>{
		console.log(error);
	})
	
})