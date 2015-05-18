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
	.btn-file {
    position: relative;
    overflow: hidden;
}
.btn-file input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
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
			window.onload = activateHeaderLink('register-user-link');
		</script>
       <!--Until here header stuff -->


      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      <form:form method="post" commandName="user" cssClass="form-signup" enctype="multipart/form-data">
	      <form:label path="firstname">First Name</form:label>
	      <form:input path="firstname" cssClass="form-control" />
	      
	      <form:label path="lastname">Last name</form:label>
	      <form:input path="lastname" cssClass="form-control" />
	      
	      <form:label path="email">Email</form:label>
	      <form:input path="email" cssClass="form-control" />
	      <form:errors path="email" cssClass="error" />
	      
	      <span class="btn btn-default btn-file">
			    Browse <input type="file" name="image"> </input>	
			</span>

	      <br>
	      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form:form>
      </div>

    </div>
</body>
</html>