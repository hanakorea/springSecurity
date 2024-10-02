<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp" %>

	<h1>index페이지</h1>
	<h1>${sessionScope.principal.username}</h1>
	<h1>${sessionScope.principal}</h1>

<%@ include file="./layout/footer.jsp" %>