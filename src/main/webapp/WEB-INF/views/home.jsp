<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

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
      	
        <h1>Twitter Practice</h1>
        <p>The following project will be a simple "twitter" alike project, to improve our Java/Spring skills while at the internship</p>
        <p>
          <a class="btn btn-lg btn-primary" href="#" role="button">Start here</a>
        </p>
      </div>

    </div>
</body>
</html>