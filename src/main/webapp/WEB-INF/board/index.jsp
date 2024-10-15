<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp" %>

	<c:if test="${empty postList}">
		<h1>등록된 게시물이 없습니다.</h1>
	</c:if>

	<c:forEach var="post" items="${postList.content}"> <!--var:변수,items:반복문 돌릴 --> 
	   <div class="container mt-3">
	      <div class="card">
	         <div class="card-body">
	            <h4 class="card-title">${post.title}</h4>
	            <a href="/post/${post.id}" class="btn btn-secondary">상세보기</a>
	         </div>
	      </div>
	   </div>
	</c:forEach>
	
	<br>
      <ul class="pagination justify-content-between">
         <li class="page-item <c:if test = "${postList.first}"> disabled</c:if>">
            <a class="page-link" href="?page=${postList.number - 1}">이전</a>
         </li>
         <li class="page-item <c:if test = "${postList.last}"> disabled </c:if>">
            <a class="page-link" href="?page=${postList.number + 1}">다음</a>
         </li>
      </ul>
      
      <br>
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item ${pageDTO.prev ? '' : 'disabled'}">
            <a class="page-link" href="?page=${pageDTO.startPage-2}" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <c:forEach var ="i" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
          <li class="page-item ${i == pageDTO.page.number + 1 ? 'active' : ''}"><a class="page-link" href="?page=${i-1}">${i}</a></li>
		  </c:forEach>	
          <li class="page-item ${pageDTO.next ? '':'disabled'}">
            <a class="page-link" href="?page=${pageDTO.endPage}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
      
<%@ include file="./layout/footer.jsp" %>