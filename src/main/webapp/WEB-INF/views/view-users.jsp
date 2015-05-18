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
			window.onload = activateHeaderLink('see-all-users-link');
		</script>
       
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
							<td><a href="/aminternship/tweet-page/${user.getId()}">${user.getId()}</a></td>
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
			  
			  	<tr>
				   	<td><a href="${prevUserLink}">Prev</a></td>
				    <td><a href="${nextUserLink}">Next</a></td>
				</tr>
              </tbody>
            </table>
      </div>
    </div>
</body>
</html>