<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	
<div class="container border py-3 mt-3">
	<c:if test="${empty postInfo}">
		<h1>등록된 게시물이 없습니다.</h1>
	</c:if>	
	
   <div>
      <h3>${postInfo.title}</h3>
   </div>
   <div>
      <p>${postInfo.content}</p>
   </div>
   <div>
      <p>포스트 번호 : <span id="id">${postInfo.id}</span></p>
      <p>작성자 : ${postInfo.user.username}</p>
   </div>
   <hr>
   <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
   <a href="/post/modify/${postInfo.id}" class="btn btn-warning">수정하기</a>
   <button id="btn-delete" class="btn btn-danger">삭제하기</button>
   
      <br><br>
   <div class="container mt-3">
      <input type="hidden" id="postId" value="${postInfo.id}">
      <table class="table">
         <thead>
            <tr>
               <th><h4>댓글 목록</h4></th>
     	<c:if test="${!empty postInfo.replyList}">
	      <div class="container mt-3">
	         <table class="table">
	            <thead>
	               <tr>
	                  <th width="75%">내용</th>
	                  <th width="10%">작성자</th>
	                  <th width="15%">삭제</th>
	               </tr>
	            </thead>
	            <tbody>
	               <c:forEach items="${postInfo.replyList}" var="reply">
	                  <tr>
	                     <td>${reply.content}</td>
	                     <td>${reply.user.username}</td>
	                     
	                     <td>
	                     <c:if test="${reply.user.username == principal.username}">
	                     <!-- 데이터셋 : 테그에 원하는 정보 저장해 사용 -->
	                     <button class="btn btn-danger reply-delete" data-id="${reply.id}">삭제</button>
	                     </c:if>
	                     </td>
	                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </div>
   </c:if>
            </tr>
         </thead>
         <tbody>
            <tr align="right">
               <td>
                  <textarea id="reply-content" rows="1" class="form-control"></textarea>
                  <button id="btn-save-reply" class="btn btn-secondary mt-3">댓글 등록</button>
               </td>
            </tr>
         </tbody>
      </table>
   </div>
</div>

<script src = "/js/post.js"></script>
<script src = "/js/reply.js"></script>
<%@ include file="../layout/footer.jsp" %>