<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container text-center">
	<h1 style="display:inline-block">VirtualBook
	<sec:authorize access="isAuthenticated()">
	    <span style="font-size: 12pt;"><a href="<c:url value="/logout" />">Logout</a></span>
	</sec:authorize>
	</h1>
</div>
