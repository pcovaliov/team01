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
              <li class="active"><a href="/aminternship/register-user">Register User</a></li>
              <li><a href="/aminternship/view-users">See all users</a></li>
              <li><a href="user_tweet_page.html">User tweet page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="login.html">Login</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

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