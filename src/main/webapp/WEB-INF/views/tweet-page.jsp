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
        <div class="col-lg-4">
            <div class="form-group">
              <label for="tweet">Tweet:</label>
                <form:form method="post" id="add-tweet-form" commandName="tweetObject">
                  <form:input path="tweet" cssClass="form-control" />
                  <form:errors path="tweet" cssClass="error" />
                  <br>
                  <input type="submit" class="btn btn-primary" value="Tweet" />
                </form:form>
            </div> 
        </div>
        
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

    </div>
    <script type="text/javascript" src="/aminternship/resources/js/tweetSubmitFormHandle.js"></script>
</body>
</html>