<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Index</title>
	<!-- ================================== CSS ================================== -->
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
	
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

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      <form:form method="post" commandName="user" cssClass="form-signup">
	      <form:label path="firstname">First Name</form:label>
	      <form:input path="firstname" cssClass="form-control" />
	      
	      <form:label path="lastname">Last name</form:label>
	      <form:input path="lastname" cssClass="form-control" />
	      
	      <form:label path="email">Email</form:label>
	      <form:input path="email" cssClass="form-control" />
	      <br>
	      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form:form>
      </div>

    </div>
</body>
</html>