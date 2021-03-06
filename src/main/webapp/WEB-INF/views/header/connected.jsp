<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authentication var="principal" property="principal" />

<br>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="cursor:default">TwitterPractice</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li id="home-link"><a href="/aminternship/">Home</a></li>
				<li id="see-all-users-link"><a href="/aminternship/view-users">See
						all users</a></li>
				<li id="user-tweet-link"><a href="/aminternship/tweet-page">User
						tweet page</a></li>
				<li id="news-feed"><a href="/aminternship/news-feed">News
						Feed</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>Hello,<br> ${principal.userObject.firstname}
					${principal.userObject.lastname}
				</li>
				<c:set var="avatarUrl" value="${principal.userObject.imageUrl}" />
				<li><img
					src="
				<c:choose>
					<c:when test="${empty avatarUrl}">
						/aminternship/resources/images/user.png
					</c:when>
						
					<c:otherwise>${avatarUrl}
					</c:otherwise>
				</c:choose>
				" class="img-thumbnail" alt="Avatar" width="50" height="50"></li>

				<li><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>

