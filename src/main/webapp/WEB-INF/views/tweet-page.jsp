<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
      window.onload = activateHeaderLink('user-tweet-link');
    </script>
       <!--Until here header stuff -->
      

      <!-- Main component for a primary marketing message or call to action -->
      <div class="row">
        <div class="col-lg-4">
          <form role="form">
            <div class="form-group">
              <label for="comment">Tweet:</label>
              <textarea class="form-control" rows="5" id="comment"></textarea>
            </div>
            <button type="button" class="btn btn-primary">Tweet</button>
          </form>
        </div>
        <div class="col-lg-8">
          <h2>User Tweets</h2>
          <div class="table-responsive">
            <table class="table table-striped">
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
								<td>${tweet.getDate()}</td>
							</tr>
				  </c:forEach>
              </tbody>
            </table>
        </div>
       </div>

    </div>
</body>
</html>