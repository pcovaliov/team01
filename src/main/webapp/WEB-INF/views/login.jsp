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
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">TwitterPractice</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="/aminternship/">Home</a></li>
              <li ><a href="/aminternship/register-user">Register User</a></li>
              <li><a href="/aminternship/view-users">See all users</a></li>
              <li><a href="user_tweet_page.html">User tweet page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li class="active"><a href="/aminternship/login">Login</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

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
			<div class="error">${error}</div>
	
        <div style="color:red">
                Login Failed!!!<br />
                Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
         </div>
    
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
      </div>
      

    </div>
</body>
</html>