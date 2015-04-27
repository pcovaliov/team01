<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="principal" property="principal" />

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
              <li><a href="/aminternship/admin/view-users">See all users</a></li>
              <li><a href="/aminternship/tweet-page">User tweet page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            	<li>Hello,<br> ${principal.firstname} ${principal.lastname} <br> ${principal.role}</li>
              <li><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      
      