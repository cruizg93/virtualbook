<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="nav-sidebar">
     <ul class="nav">
		<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
		<!--<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>-->
		<sec:authorize access="hasRole('ADMIN')">
		<li><a href="${pageContext.request.contextPath}/settings">Settings</a></li>
		</sec:authorize>
	</ul>
</nav>