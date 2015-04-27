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
     	<c:choose>
		  <c:when test="${pageContext.request.userPrincipal.authenticated}">
		  	<%@ include file="header/connected.jsp" %>
		  </c:when>
		  <c:otherwise>
		  	<%@ include file="header/disconnected.jsp" %>
		  </c:otherwise>
		</c:choose>

      <!-- Main component for a primary marketing message or call to action -->
     <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th>Avatar</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
              
              <c:forEach items="${usersList}" var="user">
						<tr>
							<td>${user.getId()}</td>
							<td>${user.getFirstname()}</td>
							<td>${user.getLastname()}</td>
							<td>${user.getEmail()}</td>
							<td>Avatar will be here</td>
							<td>
								<a href="/aminternship/admin/delete-user/${user.getId()}">delete</a>
									/
								<a href="/aminternship/admin/edit-user/${user.getId()}">edit</a>
							</td>
						</tr>
			  </c:forEach>
              </tbody>
            </table>
      </div>

    </div>
</body>
</html>