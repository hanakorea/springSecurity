<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>

<c:forEach var="post" items="${posts}">
   <div class="container mt-3">
     <div class="card">
        <div class="card-body">
           <h4 class="card-title">${post.title}</h4>
           <a href="/post/${post.id}" class="btn btn-secondary">상세보기</a>
        </div>
     </div>
  </div>
</c:forEach>

<%@ include file = "../layout/footer.jsp" %>