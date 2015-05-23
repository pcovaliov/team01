<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="styles/head.jsp" %>
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
			window.onload = activateHeaderLink('home-link');
		</script>

      <div class="jumbotron">
      	
        <h1>Twitter Practice</h1>
        <p>The following project will be a simple "twitter" alike project, to improve our Java/Spring skills while at the internship</p>
      </div>

    </div>
</body>
</html>