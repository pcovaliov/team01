<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="styles/head.jsp" %>
	<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
	
</head>
<body>
	<div class="container">

    <!-- Static navbar -->
     	<c:choose>
		  <c:when test="${pageContext.request.userPrincipal.authenticated}">
		  	<%@ include file="header/connected.jsp" %>
		  </c:when>
		  <c:otherwise>
		  	<%@ include file="header/disconnected.jsp" %>
		  </c:otherwise>
		</c:choose>

		<script type="text/javascript">
			window.onload = activateHeaderLink('login-link');
		</script>
       <!--Until here header stuff -->

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      <form method="post" action="<c:url value='j_spring_security_check' />" name='loginForm' class="form-signup">
	      <label path="email">Email</label>
	      <input path="email" class="form-control" name="email" />
	      <label><input id="j_remember" name="_spring_security_remember_me" type="checkbox" />Remember me</label>
	      <br>
	      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
 
		<c:if test="${not empty error}">
			<br>
			<div class="error">${error}</div>
	   
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
      </div>
      

    </div>
</body>
</html>