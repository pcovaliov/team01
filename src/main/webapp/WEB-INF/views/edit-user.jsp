<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Index</title>
	<!-- ================================== CSS ================================== -->
	<link rel="stylesheet" type="text/css" href="/aminternship/resources/css/bootstrap.css">
	
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
       <form class="form-signup" method="post" th:object="${user}">
        <h2 class="form-signup-heading">Edit user form</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" th:field="*{email}" class="form-control" value="${user.getEmail()}">

        <label for="firstname" class="sr-only">First name</label>
        <input type="text" th:field="*{firstname}" class="form-control" >

        <label for="lastname" class="sr-only">Last name</label>
        <input type="text" th:field="*{lastname}" class="form-control" value="${user.getLastname()}" >

        <button class="btn btn-lg btn-primary btn-block" type="submit">Edit</button>
      </form>
      

      </div>

    </div>
</body>
</html>