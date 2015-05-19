<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@ include file="styles/head.jsp" %>
</head>
<body>
	<div class="container">
    
    <c:choose>
      <c:when test="${pageContext.request.userPrincipal.authenticated}">
        <%@ include file="header/connected.jsp" %>
      </c:when>
      <c:otherwise>
        <%@ include file="header/disconnected.jsp" %>
      </c:otherwise>
    </c:choose>

    <script type="text/javascript">
      window.onload = activateHeaderLink('user-tweet-link');
    </script>

      <div class="row">
        <div class="col-lg-8">
          <h2>User Tweets</h2>
          <div class="table-responsive">
            <table  id="tweet-table" class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Tweet</th>
                  <th>Date</th>
                </tr>
              </thead>
              <tbody>
	              <c:forEach items="${tweetList}" var="tweet">
							<tr>
								<td>${tweet.getId()}</td>
								<td>${tweet.getTweet()}</td>
								<td><fmt:formatDate value="${tweet.getDate()}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
				  </c:forEach>
              </tbody>
            </table>
        </div>
       </div>
       
	<div class="col-lg-4">
			<table>
				<tr><td>Owner of the page</td></tr>
				<tr><td>${currentUser.firstname}</td></tr>
				<tr><td>${currentUser.lastname}</td></tr>
				<tr><td>
					            <c:set var="avatarUrl" value="${currentUser.imageUrl}" />
			<img src="
				<c:choose>
					<c:when test="${empty avatarUrl}">
						/aminternship/resources/images/user.png
					</c:when>
						
					<c:otherwise>${avatarUrl}
					</c:otherwise>
				</c:choose>
				" class="img-thumbnail" alt="Avatar" width="50" height="50">
				</td></tr>
				
				<tr><td>             <c:choose>
		      <c:when test="${pageContext.request.userPrincipal.authenticated}"> 
		      		            <c:choose>
								      <c:when test="${currentLoggedInUser.id ==currentUser.id}"> 
								     		You are the owner of this Page
								      </c:when>
							
								      <c:otherwise>
								      	<input type="submit" id="follow-button" class="btn btn-primary"
									      <c:choose>
										      <c:when test="${isFollowing}">
										      	 value="Unfollow" 
										      </c:when>
										
										      <c:otherwise>
										      	value="Follow"
										      </c:otherwise>
										  </c:choose>
										  onclick="changeFollowRelationship(${currentUser.id})"/>
								      </c:otherwise>
								</c:choose>
		      </c:when>
		
		      <c:otherwise>
		      		Please login to follow
		      </c:otherwise>
			</c:choose> </td></tr>
			</table>
        </div>

    </div>
    <script type="text/javascript" src="/aminternship/resources/js/tweetSubmitFormHandle.js"></script>
</body>
</html>